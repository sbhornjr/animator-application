package cs3500.animator.provider.view.visual;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JFormattedTextField;
import javax.swing.Box;
import javax.swing.BorderFactory;
import javax.swing.Timer;
import javax.swing.text.NumberFormatter;

import cs3500.animator.provider.model.ShapeOperations;
import cs3500.animator.provider.view.AbstractView;
import cs3500.animator.provider.view.AnimationViewOperations;
import cs3500.animator.provider.view.ShapeViewOperations;
import cs3500.animator.provider.view.visitors.AnimationVisitor;
import cs3500.animator.provider.view.visitors.ShapeVisitor;

/**
 * Used to animate the given state using java swing and have an interface to control said view.
 */
public class HybridView extends AbstractView implements InteractiveViewOperations {

  private int ticks;
  private boolean loop;
  private float lastElementTime;
  private ArrayList<JButton> buttons;
  private ArrayList<JCheckBox> shapeCheckBoxes;
  private JPanel shapeList;
  private JScrollPane shapes;
  private JTextField tempoField;
  private JTextField svgField;
  JFrame thisFrame;

  /**
   * Used to construct this Hybrid View.
   */
  public HybridView(ShapeVisitor<ShapeViewOperations> sv,
                    AnimationVisitor<AnimationViewOperations> av) {
    super(sv, av);
    this.ticks = 1;
    this.loop = false;
    this.buttons = new ArrayList<>();
    this.shapeCheckBoxes = new ArrayList<>();
    this.lastElementTime = 0;
    this.shapeList = new JPanel(new BorderLayout());

    thisFrame = new JFrame();
    thisFrame.setContentPane(new Container());

    thisFrame.setTitle("Animator Program");
    thisFrame.setSize(500,500);
    thisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel animationPanel;
    Container contentPane = thisFrame.getContentPane();
    contentPane.setLayout(new BorderLayout());

    animationPanel = new AnimationPanel();

    JButton play = new JButton("PLAY");
    JButton pause = new JButton("PAUSE");
    JButton stop = new JButton("RESET");
    JButton loop = new JButton("LOOP");
    JButton svg = new JButton("SVG");

    this.svgField = new JTextField("Enter File Location", 20);
    svgField.setMaximumSize(svgField.getPreferredSize());
    svgField.setMinimumSize(svgField.getPreferredSize());
    svgField.setActionCommand("SVG FIELD");

    JButton speedUp = new JButton("SPEED UP");

    NumberFormat format = NumberFormat.getInstance();
    NumberFormatter formatter = new NumberFormatter(format);
    formatter.setValueClass(Integer.class);
    formatter.setMinimum(0);
    formatter.setMaximum(Integer.MAX_VALUE);
    formatter.setAllowsInvalid(true);
    this.tempoField = new JFormattedTextField(formatter);

    this.tempoField.setColumns(3);
    tempoField.setMaximumSize(tempoField.getPreferredSize());
    tempoField.setMinimumSize(tempoField.getPreferredSize());
    tempoField.setActionCommand("SPEED");

    JButton speedDown = new JButton("SLOW DOWN"); //PRINT OUT VALUE OF SPEED NEXT TO IT

    this.buttons.add(play);
    this.buttons.add(pause);
    this.buttons.add(stop);
    this.buttons.add(loop);
    this.buttons.add(svg);
    this.buttons.add(speedUp);
    this.buttons.add(speedDown);

    Box horizontalBox = Box.createHorizontalBox();

    for (JButton b: this.buttons) {
      b.setActionCommand(b.getName());
      horizontalBox.add(b);
      if (b.equals(svg)) {
        horizontalBox.add(Box.createRigidArea(new Dimension(5, 0)));
        horizontalBox.add(svgField);
        horizontalBox.add(Box.createRigidArea(new Dimension(5, 0)));
        horizontalBox.add(Box.createHorizontalGlue());
      }
      else if (b.equals(speedUp)) {
        horizontalBox.add(Box.createRigidArea(new Dimension(5, 0)));
        horizontalBox.add(tempoField);
        horizontalBox.add(Box.createRigidArea(new Dimension(5, 0)));
      }
      else {
        horizontalBox.add(Box.createRigidArea(new Dimension(5, 0)));
      }
    }

    JPanel animatorController = new JPanel(new BorderLayout());
    animatorController.add(horizontalBox);
    animatorController.setBorder(BorderFactory.createTitledBorder("Controls"));

    JScrollPane s = new JScrollPane(animationPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


    contentPane.add(animatorController, BorderLayout.PAGE_END);
    contentPane.add(s);

    thisFrame.pack();
    thisFrame.setVisible(true);
  }

  /**
   * The panel containing our animation.
   */
  class AnimationPanel extends JPanel {

    AnimationPanel() {
      // set a preferred size for the custom panel.
      this.setLayout(new BorderLayout());
      this.setBorder(BorderFactory.createTitledBorder("View Animation"));
      this.setPreferredSize(new Dimension(800, 800));
    }


    /**
     * Used to paint our animation.
     * @param g The graphics used by java swing.
     */
    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);

      for (ShapeViewOperations s : state) {
        s.animate(ticks);
        s.draw(g, ticks);
      }

    }
  }

  /**
   * The action listener called by our timer.
   */
  ActionListener taskPerformer = new ActionListener() {

    /**
     * The action to be performed for each timer increment.
     * @param evt The action event.
     */
    public void actionPerformed(ActionEvent evt) {
      tm.setDelay(500 / tempo);

      ticks++;
      if (loop && ticks > lastElementTime) {
        while (ticks > 0) {
          for (ShapeViewOperations sv: state) {
            sv.animate(ticks);
          }
          ticks--;
        }
        ticks = 1;
        thisFrame.repaint();
      }
      else if (ticks <= lastElementTime) {
        thisFrame.repaint();
      }

    }

  };

  private Timer tm = new Timer(1, taskPerformer);

  /**
   * plays the animation for the given state.
   * @param tempo the rate we want to view our animation at.
   * @param state the animation we want to play.
   */
  @Override
  public void playAnimation(int tempo, ArrayList<ShapeOperations> state) {
    tm.stop();
    this.tempo = tempo;
    tm.setDelay(500 / this.tempo);
    initializeState(state);
    ActionListener a = buttons.get(0).getActionListeners()[0];
    tempoField.setText("" + tempo);
    ActionListener b = tempoField.getActionListeners()[0];
    tempoField.setActionCommand("SPEED");

    for (ShapeViewOperations s: this.state) {
      if (s.getDisappearTime() > this.lastElementTime) {
        this.lastElementTime = s.getDisappearTime();
      }
    }

    Box verticalBox = Box.createVerticalBox();
    shapeCheckBoxes.clear();
    for (ShapeViewOperations sv: this.state) {
      JCheckBox cb = new JCheckBox(sv.getName());
      cb.setSelected(true);
      if (a != null) {
        cb.addActionListener(a);
      }
      cb.setActionCommand(sv.getName());
      verticalBox.add(cb);
      shapeCheckBoxes.add(cb);
    }

    this.shapeList = new JPanel();
    this.shapeList.add(verticalBox);
    shapeList.setBorder(BorderFactory.createTitledBorder("Shapes"));
    Container contentPane = thisFrame.getContentPane();
    if (contentPane.getComponentCount() > 3) {
      contentPane.remove(shapes);
    }
    JScrollPane shapes = new JScrollPane(shapeList);
    shapes.setPreferredSize(new Dimension(200, 800));
    this.shapes = shapes;

    contentPane.add(this.shapes, BorderLayout.EAST);
    thisFrame.pack();
    thisFrame.setVisible(true);
    ticks = 1;
    tm.start();
  }

  /**
   * Used to get this animation as an svg.
   * @param tempo - the rate we are running our animation at.
   * @param state - the state of shapes and animations we want to animate.
   * @param svgHeight - the height of the svg animation
   * @param svgWidth - the width of the svg animation
   * @return the svg format of this animation as a string.
   */
  @Override
  public String getSVG(int tempo, ArrayList<ShapeOperations> state, int svgHeight, int svgWidth) {
    this.tempo = tempo;
    this.initializeState(state);

    String svgOutput = "<svg width=\"" + svgHeight + "\" height=\"" + svgWidth +
            "\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n";

    svgOutput += "<rect>\n" +
            "<animate id=\"base\" begin=\"0;base.end\" dur=\" " +
            this.lastElementTime / this.tempo +
            "s\" " +
            "attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n" +
            "</rect>\n";

    for (ShapeViewOperations s : this.state) {
      svgOutput += s.printAsSVG(this.tempo, loop) + "\n";
    }
    svgOutput += "</svg>";

    return svgOutput;
  }

  /**
   * This method is used by a controller to add an action listener to our buttons.
   * @param controller - the controller we are using to control our view.
   */
  @Override
  public void setActionListener(ActionListener controller) {
    for (JButton b: this.buttons) {
      b.addActionListener(controller);
    }
    for (JCheckBox cb: this.shapeCheckBoxes) {
      cb.addActionListener(controller);
    }
    this.tempoField.addActionListener(controller);
    this.svgField.addActionListener(controller);
  }

  /**
   * This method resets all the shapes in the animation and runs it at the beginning.
   */
  @Override
  public void reset() {
    while (ticks > 0) {
      for (ShapeViewOperations sv: state) {
        sv.animate(ticks);
      }
      ticks--;
    }
    ticks = 1;
    thisFrame.repaint();
  }

  /**
   * This method is used to set the tempo of our animation.
   * @param tempo - the tempo this view is running at.
   */
  @Override
  public void setTempo(int tempo) {
    this.tempo = tempo;
    tempoField.setText("" + tempo);
  }

  /**
   * This method plays the animation if its paused.
   */
  @Override
  public void play() {
    if (!this.tm.isRunning()) {
      tm.start();
    }
    if (this.tm.isRunning()) {
      this.buttons.get(1).setBackground(new Button().getBackground());
    }
    else {
      this.buttons.get(1).setBackground(Color.RED);
    }
  }

  /**
   * This method pauses the animation if its running.
   */
  @Override
  public void pause() {
    if (this.tm.isRunning()) {
      tm.stop();
    }
    if (this.tm.isRunning()) {
      this.buttons.get(1).setBackground(new Button().getBackground());
    }
    else {
      this.buttons.get(1).setBackground(Color.RED);
    }
  }

  /**
   * This method is used to toggle the loop function of the view.
   */
  @Override
  public void loop() {
    this.loop = !loop;
    if (this.loop) {
      this.buttons.get(3).setBackground(Color.GREEN);
    }
    else {
      this.buttons.get(3).setBackground(new Button().getBackground());
    }
  }

  /**
   * This method is used to get the tempo of this animation.
   * @return The tempo of this animation.
   */
  @Override
  public int getTempo() {
    return this.tempo;
  }

}
