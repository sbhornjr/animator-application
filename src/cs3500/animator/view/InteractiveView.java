package cs3500.animator.view;

import cs3500.animator.controller.ComboBoxListener;
import cs3500.animator.model.shapes.IShape;
import sun.plugin.dom.exception.InvalidStateException;

import cs3500.animator.controller.ButtonListener;
import cs3500.animator.controller.TextFieldListener;
import cs3500.animator.model.model.IAnimatorOperations;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class InteractiveView extends JFrame implements IAnimationView, IInteractiveView, ActionListener {
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
  private ButtonListener bl;
  private TextFieldListener tfl;
  private ComboBoxListener cbl;
  private JTextArea stats;

  private static int FRAME_WIDTH = 1000;
  private static int FRAME_HEIGHT = 700;

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
    JPanel animationPanel = new AnimationPanel(this.model.getShapes());
    animationPanel.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
    JScrollPane scrollPane = new JScrollPane(animationPanel);
    this.add(scrollPane, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.SOUTH);

    startButton = new JButton("Start");
    pauseButton = new JButton("Pause");
    loopButton = new JButton("Toggle Looping");
    export = new JTextField("Enter SVG file name", 22);
    export.setToolTipText("Enter a filename here to export this animation.");
    speedChanger = new JTextField("Enter new speed", 15);
    speedChanger.setToolTipText("Change the speed of the animation by entering a new speed value "
            + "here.");

    bl = null;
    tfl = null;
    cbl = null;

    buttonPanel.add(startButton);
    buttonPanel.add(pauseButton);
    buttonPanel.add(loopButton);
    buttonPanel.add(speedChanger);
    buttonPanel.add(export);

    JPanel dropDownPanel = new JPanel();
    dropDownPanel.setLayout(new FlowLayout());
    this.add(dropDownPanel, BorderLayout.EAST);

    IShape[] shapeArray = this.model.getShapes().toArray(new IShape[this.model.getShapes().size()]);
    shapeRemover = new JComboBox<>(shapeArray);
    shapeRemover.setToolTipText("Select a shape in this drop box to make it invisible.");
    shapeRemover.setEditable(true);
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
    for (IShape sh : model.getShapes()) {
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
  public void setButtonListener(ButtonListener bl) {
    this.bl = bl;
  }

  @Override
  public void setTextFieldListener(TextFieldListener tfl) {
    this.tfl = tfl;
  }

  @Override
  public void setComboBoxListener(ComboBoxListener cbl) {
    this.cbl = cbl;
  }

  @Override
  public void addActionListeners() {
    if (bl == null || tfl == null) {
      throw new InvalidStateException("Listeners must be set before being added to buttons.");
    }

    startButton.addActionListener(bl);
    pauseButton.addActionListener(bl);
    loopButton.addActionListener(bl);
    export.addActionListener(tfl);
    speedChanger.addActionListener(tfl);
    shapeRemover.addActionListener(cbl);
  }

  @Override
  public void togglePause() {
    paused = !paused;
    if (paused) {
      pauseButton.setText("Play");
    }
    else {
      pauseButton.setText("Pause");
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
      fw = new FileWriter("resources/" + ofile);
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
}
