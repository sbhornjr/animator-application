package cs3500.animator.model.actions;

import cs3500.animator.model.shapes.IShape;
import cs3500.animator.model.misc.MyColor;
import cs3500.animator.model.misc.Posn;

/**
 * Type for the types of action that can be applied to a shape in the animator.
 */
public enum ActionType {
  MOVE, COLOR_CHANGE, SCALE;

  /**
   * Creates an action based on which Action this enum represents.
   *
   * @param s         The shape that the action will be applied to
   * @param oldTrait  The old trait that the shape has before the action
   * @param newTrait  The new trait that the shape will have after the action
   * @param duration  The duration of the action
   * @return          The action
   */
  public IAction create(IShape s, Object oldTrait, Object newTrait, Posn duration) {
    IAction a;
    switch (this) {
      case MOVE:
        a = new Move(s, (Posn) oldTrait, (Posn) newTrait, duration);
        break;
      case COLOR_CHANGE:
        a = new ColorChange(s, (MyColor) oldTrait, (MyColor) newTrait, duration);
        break;
      default:
        a = new Scale(s, (Posn) oldTrait,(Posn) newTrait, duration);
    }
    s.addAction(a);

    return a;
  }
}
