package cs3500.animator.model.shapes;

import cs3500.animator.model.misc.MyColor;
import cs3500.animator.model.misc.Posn;

/**
 * Type for the types of shapes that can be used in the animator.
 */
public enum ShapeType {
  RECTANGLE, OVAL;

  /**
   * Creates a shape based on the ShapeType that this enum represents.
   *
   * @param name        The name of the shape.
   * @param location    The location of the shape's center
   * @param dimensions  The dimensions of the shape.
   * @param sides       The number of sides that this shape has.
   * @param color       The color of the shape
   * @param lifetime    The times that the shape appears and disappears
   * @return            The shape
   */
  public IShape create(String name, Posn location, Posn dimensions, int sides, MyColor color,
                       Posn lifetime) {
    IShape s = null;
    if (this == OVAL) {
      s = new MyOval(name, location, dimensions, color, lifetime);
    }
    else {
      s = new MyRectangle(name, location, dimensions, color, lifetime);
    }
    return s;
  }
}
