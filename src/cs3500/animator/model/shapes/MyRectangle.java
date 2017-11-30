package cs3500.animator.model.shapes;

import cs3500.animator.model.misc.IMyColor;
import cs3500.animator.model.misc.IPosn;

/**
 * Represents a rectangle in an animation.
 */
public class MyRectangle extends Shape implements cs3500.animator.model.shapes.IRectangle {

  /**
   * Constructs a MyRectangle without a given amount of sides because it must be 4.
   *
   * @param name        This MyRectangle's name
   * @param location    The location of this MyRectangle's lower-left corner
   * @param dimensions  The width and height of this MyRectangle
   * @param color       This MyRectangle's color
   * @param lifetime    The times that this MyRectangle appears and disappears
   */
  public MyRectangle(String name, IPosn location, IPosn dimensions, IMyColor color,
                     IPosn lifetime) {
    super(name, location, dimensions, color, lifetime);
    this.type = ShapeType.RECTANGLE;
  }

  @Override
  public String getPosLocation() {
    return "Lower-left corner";
  }
}
