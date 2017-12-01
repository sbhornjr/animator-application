package cs3500.animator.view.model;

import cs3500.animator.model.actions.Move;
import cs3500.animator.model.misc.Posn;
import cs3500.animator.provider.model.AnimationOperations;
import cs3500.animator.provider.model.IMoveAnimation;
import cs3500.animator.provider.model.ShapeOperations;
import cs3500.animator.provider.view.model.AnimationViewOperations;
import cs3500.animator.provider.view.visitors.AnimationVisitor;

/**
 * Represents a move that the view can interact with.
 */
public class MoveView implements AnimationViewOperations {

  private Move move;

  /**
   * Constructor for the move view.
   * @param m   The move that this move view represents.
   */
  public MoveView(IMoveAnimation m) {
    this.move = (Move) m;
  }

  @Override
  public String printAsSVG(int tempo, boolean loop) {
    String str = "";
    if (getFrom().getX() != getTo().getX()) {
      str += "    <animate attributeType=\"xml\" begin=\""
              + (int) ((move.getDuration().getX() / tempo) * 1000) + "ms\" dur=\""
              + (int) (((move.getDuration().getY() - move.getDuration().getX()) / tempo) * 1000)
              + "ms\" attributeName=\"x\" from=\"" + (int) getFrom().getX() + "\" to=\""
              + (int) getTo().getX() + "\" fill=\"freeze\" />\n";
    }
    if (getFrom().getY() != getTo().getY()) {
      str += "    <animate attributeType=\"xml\" begin=\""
              + (int) ((move.getDuration().getX() / tempo) * 1000) + "ms\" dur=\""
              + (int) (((move.getDuration().getY() - move.getDuration().getX()) / tempo) * 1000)
              + "ms\" attributeName=\"y\" from=\"" + (int) getFrom().getY() + "\" to=\""
              + (int) getTo().getY() + "\" fill=\"freeze\" />\n";
    }

    return str;
  }

  @Override
  public String toViewString(int tempo) {
    String s = "";
    s += "Shape " + move.getShapeName() + " ";
    String str = move.getDescription();
    s += str;
    s += "from t=" + move.getDuration().getX() / tempo + "s to t="
            + move.getDuration().getY() / tempo + "s";
    return s;
  }

  @Override
  public void animate(ShapeOperations shapeToBeChanged, float currentTime) {
    move.animate(shapeToBeChanged, currentTime);
  }

  @Override
  public float getFromTime() {
    return move.getFromTime();
  }

  @Override
  public float getToTime() {
    return move.getToTime();
  }

  @Override
  public <T> T accept(AnimationVisitor<T> v) {
    throw new UnsupportedOperationException("If you got here you messed up.");
  }

  @Override
  public Posn getFrom() {
    return move.getFrom();
  }

  @Override
  public Posn getTo() {
    return move.getTo();
  }

  @Override
  public boolean hasNoConflictsWith(ShapeOperations stateAtFromTime, ShapeOperations stateAtToTime, AnimationOperations a) {
    return move.hasNoConflictsWith(stateAtFromTime, stateAtToTime, a);
  }

  @Override
  public boolean noConflictsWithHelper(ShapeOperations givenFrom, ShapeOperations givenTo, float givenFromTime, float givenToTime) {
    return move.noConflictsWithHelper(givenFrom, givenTo, givenFromTime, givenToTime);
  }

  @Override
  public void setShapeToBeAnimated(String shapeName) {
    move.setShapeToBeAnimated(shapeName);
  }
}
