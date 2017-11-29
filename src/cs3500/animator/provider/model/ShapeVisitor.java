package cs3500.animator.provider.model;

import cs3500.animator.model.shapes.Oval;
import cs3500.animator.model.shapes.Rectangle;

/**
 * Visitor used to add functionality to a shape object.
 * @param <T> The type our visit methods will return.
 */
public interface ShapeVisitor<T> {

  /**
   * Used to add functionality to a Rectangle.
   * @param rect the rectangle we are adding functionality to.
   * @return whatever we wanted to use the rectangle for.
   */
  T visit(Rectangle rect);

  /**
   * Used to add functionality to an oval.
   * @param oval the oval we are adding functionality to.
   * @return whatever we wanted to use the oval for.
   */
  T visit(Oval oval);

}
