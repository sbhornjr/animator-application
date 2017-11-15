package cs3500.animator.view;

import sun.plugin.dom.exception.InvalidStateException;

import cs3500.animator.controller.ButtonListener;
import cs3500.animator.controller.TextFieldListener;
import cs3500.animator.model.model.IAnimatorOperations;

import javax.swing.*;

import java.awt.*;

public class InteractiveView extends JFrame implements IAnimationView, IInteractiveView {
  private SVGAnimationView svgView;
  private IAnimatorOperations model;
  private double speed;
  private ViewType type;
  private boolean paused;
  private boolean looping;
  private boolean go;

  JButton startButton;
  JButton pauseButton;
  JButton restartButton;
  JButton loopButton;
  JButton exportButton;
  JTextField speedChanger;
  private ButtonListener bl;
  private TextFieldListener tfl;

  private static int FRAME_WIDTH = 1000;
  private static int FRAME_HEIGHT = 800;

  public InteractiveView(Appendable ap, IAnimatorOperations model, double speed) {
    this.model = model;
    this.speed = speed;
    this.svgView = new SVGAnimationView(ap, model, speed);
    this.type = ViewType.INTERACTIVE;
    this.paused = false;
    this.looping = false;
    this.go = true;

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
    this.add(buttonPanel,BorderLayout.SOUTH);

    startButton = new JButton("Start");
    pauseButton = new JButton("Pause");
    restartButton = new JButton("Restart");
    loopButton = new JButton("Toggle Looping");
    exportButton = new JButton("Export as SVG");
    speedChanger = new JTextField("new speed", 10);

    bl = null;
    tfl = null;

    buttonPanel.add(startButton);
    buttonPanel.add(pauseButton);
    buttonPanel.add(restartButton);
    buttonPanel.add(loopButton);
    buttonPanel.add(speedChanger);
    buttonPanel.add(exportButton);

    this.pack();
    this.setVisible(true);
  }

  @Override
  public void display() {
    this.svgView.display();
  }

  /**
  @Override
  public void run() {
    double waitTime = 1000 / speed;

    while (!paused) {
      double t;
      for (t = 0; t <= model.getEndTime(); t += 1 / speed) {
        for (int i = 0; i < model.getActions().size(); i++) {
          model.executeAction(i, t * speed);
        }
        this.repaint();
        try {
          Thread.sleep((long) waitTime);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }*/

  @Override
  public void run() {
    go = true;
    int currTime = 0;

    while (go) {
      while (!paused) {
        double waitTime = 1000 / speed;
        if (currTime == model.getEndTime()) {
          if (looping) {
            currTime = 0;
          }
          else {
            break;
          }
        }
        for (int i = 0; i < model.getActions().size(); i++) {
          model.executeAction(i, currTime * speed);
        }
        this.repaint();
        try {
          Thread.sleep((long)waitTime);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        currTime++;
      }
    }
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
  public void addActionListeners() {
    if (bl == null || tfl == null) {
      throw new InvalidStateException("Listeners must be set before being added to buttons.");
    }

    startButton.addActionListener(bl);
    pauseButton.addActionListener(bl);
    restartButton.addActionListener(bl);
    loopButton.addActionListener(bl);
    exportButton.addActionListener(bl);
    speedChanger.addActionListener(tfl);
  }

  @Override
  public void togglePause() {
    paused = !paused;
  }

  @Override
  public void toggleLoop() {
    looping = !looping;
  }

  @Override
  public void setSpeed(double newSpeed) {
    speed = newSpeed;
  }

  @Override
  public void restart() {
    go = false;
    run();
  }
}
