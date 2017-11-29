package cs3500.animator.provider.model;

import cs3500.animator.model.animations.ColorAnimation;
import cs3500.animator.model.animations.MoveAnimation;
import cs3500.animator.model.animations.ScaleAnimation;

/**
 * A visitor interface used to add functionality to an animation object.
 * @param <T> The type our visitor methods will return.
 */
public interface AnimationVisitor<T> {

  /**
   * Used to add functionality to a move animation.
   * @param move the move animation we are adding functionality to.
   * @return whatever we wanted to use the move animation for.
   */
  T visit(MoveAnimation move);

  /**
   * Used to add functionality to a scale animation.
   * @param scale the scale animation we are adding functionality to.
   * @return whatever we wanted to use the scale animation for.
   */
  T visit(ScaleAnimation scale);

  /**
   * Used to add functionality to a color animation.
   * @param changeColor the color animation we are adding functionality to.
   * @return whatever we wanted to use the color animation for.
   */
  T visit(ColorAnimation changeColor);

}
