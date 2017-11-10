package cs3500.animator.view;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import cs3500.animator.model.shapes.IShape;
import cs3500.animator.model.shapes.ShapeType;

/**
 * A JPanel with all of the shapes in the animation drawn on it.
 */
public class AnimationPanel extends JPanel {

  private ArrayList<IShape> shapes;

  public AnimationPanel(ArrayList<IShape> shapes) {
    this.shapes = shapes;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    for (IShape s : shapes) {
      if (s.getType() == ShapeType.RECTANGLE) {
        g.setColor(s.getColor());
        g.fillRect(s.getX(), s.getY(), s.getWidth(), s.getHeight());
      }
      else {
        g.setColor(s.getColor());
        g.fillOval(s.getX(), s.getY(), s.getWidth(), s.getHeight());
      }
    }
  }
}
