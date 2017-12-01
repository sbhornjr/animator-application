package cs3500.animator.view;

import cs3500.animator.model.model.IAnimatorOperations;
import cs3500.animator.model.shapes.IShape;
import cs3500.animator.model.shapes.ShapeType;
import cs3500.animator.provider.model.ShapeOperations;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Displays an animation with an interface that allows a user to edit the playback of the animation.
 */
public class InteractiveView extends JFrame implements IAnimationView, IInteractiveView,
        ActionListener {
  private IAnimatorOperations model;
  private int speed;
  private int tick;
  private ViewType type;
  private boolean paused;
  private boolean looping;
  private Timer timer;
  private FileWriter fw;

  private JButton startButton;
  private JButton pauseButton;
  private JButton loopButton;
  private JTextField export;
  private JTextField speedChanger;
  private JComboBox<IShape> shapeRemover;
  private JTextArea stats;

  private static int FRAME_WIDTH = 1000;
  private static int FRAME_HEIGHT = 700;

  /**
   * Constructs an InteractiveView.
   *
   * @param model The model containing the data for the animation
   * @param speed The speed at which the animation will start running
   */
  public InteractiveView(IAnimatorOperations model, int speed) {
    this.model = model;
    this.speed = speed;
    this.type = ViewType.INTERACTIVE;
    this.paused = true;
    this.looping = false;

    int delay = (int) (1000 / speed);
    timer = new Timer(delay, this);
    tick = 0;

    this.setTitle("Easy Animator Application");
    this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());
    JPanel animationPanel = new AnimationPanel();
    animationPanel.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
    JScrollPane scrollPane = new JScrollPane(animationPanel);
    this.add(scrollPane, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.SOUTH);

    startButton = new JButton("Start");
    pauseButton = new JButton("Pause");
    loopButton = new JButton("Toggle Looping");
    export = new JTextField("Enter SVG file name", 11);
    speedChanger = new JTextField("Enter new speed", 9);

    startButton.setActionCommand("START");
    pauseButton.setActionCommand("TOGGLE PAUSE");
    loopButton.setActionCommand("TOGGLE LOOP");
    export.setActionCommand("EXPORT");
    speedChanger.setActionCommand("SPEED CHANGE");

    buttonPanel.add(startButton);
    buttonPanel.add(pauseButton);
    buttonPanel.add(loopButton);
    buttonPanel.add(speedChanger);
    buttonPanel.add(export);
    buttonPanel.add(new JTextArea(".svg"));

    JPanel dropDownPanel = new JPanel();
    dropDownPanel.setLayout(new FlowLayout());
    this.add(dropDownPanel, BorderLayout.EAST);

    IShape[] shapeArray = this.model.getShapes().toArray(new IShape[this.model.getShapes().size()]);
    shapeRemover = new JComboBox<>(shapeArray);
    shapeRemover.setEditable(true);
    shapeRemover.setActionCommand("SHAPE REMOVED");
    dropDownPanel.add(shapeRemover);

    JPanel statsPanel = new JPanel();
    this.add(statsPanel, BorderLayout.NORTH);
    stats = new JTextArea(this.writeStats());
    stats.setEditable(false);
    statsPanel.add(stats);

    this.pack();
    this.setVisible(true);
  }

  /**
   * Returns a description of certain characteristics of the animation that the user may have
   * altered.
   *
   * @return  The description
   */
  private String writeStats() {
    String s = "Animation is";
    if (paused) {
      s += " not currently running.";
    }
    else {
      s += " currently running at a speed of " + this.speed + ".";
    }
    s += " Looping is";
    if (looping) {
      s += " on.";
    }
    else {
      s += " off.";
    }
    return s;
  }

  /**
   * Returns each shape in the model to their initial location, position, and color values.
   */
  private void initShapes() {
    for (ShapeOperations s : model.getShapes()) {
      IShape sh = (IShape) s;
      sh.setDefault();
    }
  }

  @Override
  public void display() {
    SVGAnimationView svgView = new SVGAnimationView(fw, model, speed);
    svgView.display();
    try {
      fw.close();
    } catch (IOException e) {
      System.out.println("ERROR: " + e.getMessage());
    }
  }

  @Override
  public void run() {
    if (timer.isRunning()) {
      timer.stop();
    }
    else {
      startButton.setText("Restart");
    }
    this.initShapes();
    tick = 0;
    this.paused = false;
    timer.start();
  }

  @Override
  public ViewType getViewType() {
    return this.type;
  }

  @Override
  public void togglePause() {
    paused = !paused;
    if (paused) {
      pauseButton.setText("Play");
      pauseButton.setActionCommand(pauseButton.getText());
    }
    else {
      pauseButton.setText("Pause");
      pauseButton.setActionCommand(pauseButton.getText());
    }
  }

  @Override
  public void toggleLoop() {
    looping = !looping;
  }

  @Override
  public void setSpeed(int newSpeed) {
    speed = newSpeed;
    int delay = 1000 / speed;
    timer.setDelay(delay);
  }

  @Override
  public void export(String ofile) {
    try {
      fw = new FileWriter(ofile);
    } catch (IOException e) {
      System.out.println("ERROR: " + e.getMessage());
    }
    this.display();
  }

  @Override
  public void toggleShape(IShape s) {
    s.setVisible();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    stats.replaceRange(this.writeStats(), 0, stats.getText().length());
    if (!paused) {
      for (int i = 0; i < model.getActions().size(); i++) {
        model.executeAction(i, tick);
      }
      this.repaint();
      tick++;
      if (tick >= model.getEndTime() && looping) {
        this.run();
      }
      else if (tick >= model.getEndTime()) {
        timer.stop();
      }
    }
  }

  /**
   * A JPanel with all of the shapes in the animation drawn on it.
   */
  private class AnimationPanel extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);

      for (ShapeOperations sh : model.getShapes()) {
        IShape s = (IShape)sh;
        if (s.isVisible()) {
          if (s.getType() == ShapeType.RECTANGLE) {
            g.setColor(s.getColor());
            g.fillRect(s.getX(), s.getY(), s.getWidth(), s.getHeight());
          } else {
            g.setColor(s.getColor());
            g.fillOval(s.getX(), s.getY(), s.getWidth(), s.getHeight());
          }
        }
      }
    }
  }

  @Override
  public int getTempo() {
    return this.speed;
  }

  @Override
  public void loop() {
    toggleLoop();
  }

  @Override
  public void pause() {
    this.paused = true;
  }

  @Override
  public void play() {
    this.paused = false;
  }

  @Override
  public void setTempo(int tempo) {
    speed = tempo;
  }

  @Override
  public void reset() {
    run();
  }

  @Override
  public void setActionListener(ActionListener controller) {
    startButton.addActionListener(controller);
    pauseButton.addActionListener(controller);
    loopButton.addActionListener(controller);
    export.addActionListener(controller);
    speedChanger.addActionListener(controller);
    shapeRemover.addActionListener(controller);
  }

  @Override
  public String getSVG(int tempo, ArrayList<ShapeOperations> state, int svgHeight, int svgWidth) {
    // unsupported
    return "";
  }

  @Override
  public void playAnimation(int tempo, ArrayList<ShapeOperations> state) {
    speed = tempo;
    run();
  }
}
