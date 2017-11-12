package cs3500.animator.view;

import cs3500.animator.model.actions.IAction;
import cs3500.animator.model.model.IAnimatorOperations;
import cs3500.animator.model.shapes.IShape;
import cs3500.animator.model.shapes.ShapeType;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;

public class InteractiveView extends VisualAnimationView {
  private Appendable ap;
  private SVGAnimationView svgView;

  public InteractiveView(Appendable ap, IAnimatorOperations model, int speed) {
    super(model, speed);
    this.ap = ap;
    this.svgView = new SVGAnimationView(ap, model, speed);

    this.setTitle("Easy Animator Application");
    this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());
    JPanel animationPanel = new AnimationPanel(this.model.getShapes());
    animationPanel.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
    JScrollPane scrollPane = new JScrollPane(animationPanel);
    this.add(scrollPane, BorderLayout.CENTER);

    this.pack();
    this.setVisible(true);
  }

  @Override
  public void display() {
    this.svgView.display();
  }
}
