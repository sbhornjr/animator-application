package cs3500.animator.view.model;

import cs3500.animator.model.actions.Move;
import cs3500.animator.model.actions.Scale;
import cs3500.animator.model.misc.Posn;
import cs3500.animator.provider.model.AnimationOperations;
import cs3500.animator.provider.model.IMoveAnimation;
import cs3500.animator.provider.model.IScaleAnimation;
import cs3500.animator.provider.model.ShapeOperations;
import cs3500.animator.provider.view.model.AnimationViewOperations;
import cs3500.animator.provider.view.visitors.AnimationVisitor;

/**
 * Represents a scale that the view can interact with.
 */
public class ScaleView implements AnimationViewOperations {

  private Scale scale;

  /**
   * Constructor for the scale view.
   * @param s   The scale that this scale view represents.
   */
  public ScaleView(IScaleAnimation s) {
    this.scale = (Scale) s;
  }

  @Override
  public String printAsSVG(int tempo, boolean loop) {
    String str = "";
    if (getFrom().getX() != getTo().getX()) {
      str += "    <animate attributeType=\"xml\" begin=\""
              + (int) ((scale.getDuration().getX() / tempo) * 1000) + "ms\" dur=\""
              + (int) (((scale.getDuration().getY() - scale.getDuration().getX()) / tempo) * 1000)
              + "ms\" attributeName=\"width\" from=\"" + (int) getFrom().getX() + "\" to=\""
              + (int) getTo().getX() + "\" fill=\"freeze\" />\n";
    }
    if (getFrom().getY() != getTo().getY()) {
      str += "    <animate attributeType=\"xml\" begin=\""
              + (int) ((scale.getDuration().getX() / tempo) * 1000) + "ms\" dur=\""
              + (int) (((scale.getDuration().getY() - scale.getDuration().getX()) / tempo) * 1000)
              + "ms\" attributeName=\"height\" from=\"" + (int) getFrom().getY() + "\" to=\""
              + (int) getTo().getY() + "\" fill=\"freeze\" />\n";
    }

    return str;
  }

  @Override
  public String toViewString(int tempo) {
    String s = "";
    s += "Shape " + scale.getShapeName() + " ";
    String str = scale.getDescription();
    s += str;
    s += "from t=" + scale.getDuration().getX() / tempo + "s to t="
            + scale.getDuration().getY() / tempo + "s";
    return s;
  }

  @Override
  public void animate(ShapeOperations shapeToBeChanged, float currentTime) {
    scale.animate(shapeToBeChanged, currentTime);
  }

  @Override
  public float getFromTime() {
    return scale.getFromTime();
  }

  @Override
  public float getToTime() {
    return scale.getToTime();
  }

  @Override
  public <T> T accept(AnimationVisitor<T> v) {
    throw new UnsupportedOperationException("If you got here you messed up.");
  }

  @Override
  public Posn getFrom() {
    return scale.getFrom();
  }

  @Override
  public Posn getTo() {
    return scale.getTo();
  }

  @Override
  public boolean hasNoConflictsWith(ShapeOperations stateAtFromTime, ShapeOperations stateAtToTime, AnimationOperations a) {
    return scale.hasNoConflictsWith(stateAtFromTime, stateAtToTime, a);
  }

  @Override
  public boolean noConflictsWithHelper(ShapeOperations givenFrom, ShapeOperations givenTo, float givenFromTime, float givenToTime) {
    return scale.noConflictsWithHelper(givenFrom, givenTo, givenFromTime, givenToTime);
  }

  @Override
  public void setShapeToBeAnimated(String shapeName) {
    scale.setShapeToBeAnimated(shapeName);
  }

  @Override
  public String toString() {
    String s = "";
    s += "Shape " + scale.getShapeName() + " ";
    String str = scale.getDescription();
    s += str;
    s += "from t=" + scale.getDuration().getX() + " to t=" + scale.getDuration().getDoubleY();
    return s;
  }
}
