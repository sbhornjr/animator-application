package cs3500.animator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;


import cs3500.animator.model.model.IAnimatorOperations;

/**
 * Displays an animation visually inside a new window.
 */
public class VisualAnimationView extends JFrame implements IAnimationView {
  private IAnimatorOperations model;
  private int speed;

  private static int FRAME_WIDTH = 1000;
  private static int FRAME_HEIGHT = 800;

  /**
   * Constructs a VisualAnimationView.
   *
   * @param model The model where the necessary data is stored
   * @param speed The speed at which the animation plays
   */
  public VisualAnimationView(IAnimatorOperations model, int speed) {
    super();
    this.model = model;
    this.speed = speed;

    this.setTitle("Easy Animator Application");
    this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());
    JPanel animationPanel = new AnimationPanel(this.model.getShapes());
    animationPanel.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
    JScrollPane scrollPane = new JScrollPane(animationPanel);
    this.add(scrollPane, BorderLayout.CENTER);

    this.pack();
    this.setVisible(true);
    this.run();
  }

  @Override
  public void display() {
    throw new UnsupportedOperationException(
            "The visual view does not output any text descriptions.");
  }

  @Override
  public void run() {
    double waitTime = 1000 / (double) speed;

    while (true) {
      double t;
      for (t = 0; t <= model.getEndTime(); t += 1 / (double) speed) {
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
  }
}
