package cs3500.animator.view.model;

import cs3500.animator.model.actions.ColorChange;
import cs3500.animator.model.misc.MyColor;
import cs3500.animator.model.misc.Posn;
import cs3500.animator.provider.model.AnimationOperations;
import cs3500.animator.provider.model.IColorAnimation;
import cs3500.animator.provider.model.ShapeOperations;
import cs3500.animator.provider.view.model.AnimationViewOperations;
import cs3500.animator.provider.view.visitors.AnimationVisitor;

import java.awt.*;

/**
 * Represents a color change that the view can interact with.
 */
public class ColorChangeView implements AnimationViewOperations {

  private ColorChange colChange;

  /**
   * Constructor for the color change view.
   * @param cc   The color change that this color change view represents.
   */
  public ColorChangeView(IColorAnimation cc) {
    this.colChange = (ColorChange) cc;
  }

  @Override
  public String printAsSVG(int tempo, boolean loop) {
    String str = "";
    str += "    <animate attributeType=\"xml\" begin=\""
            + (int) ((colChange.getDuration().getX() / tempo) * 1000) + "ms\" dur=\""
            + (int) (((colChange.getDuration().getY() - colChange.getDuration().getX()) / tempo) * 1000)
            + "ms\" attributeName=\"fill\" from=\"rgb" + getFrom().asInt() + "\" to=\"rgb"
            + getTo().asInt() + "\" fill=\"freeze\" />\n";

    return str;
  }

  @Override
  public String toViewString(int tempo) {
    String s = "";
    s += "Shape " + colChange.getShapeName() + " ";
    String str = colChange.getDescription();
    s += str;
    s += "from t=" + colChange.getDuration().getX() / tempo + "s to t="
            + colChange.getDuration().getY() / tempo + "s";
    return s;
  }

  @Override
  public void animate(ShapeOperations shapeToBeChanged, float currentTime) {
    colChange.animate(shapeToBeChanged, currentTime);
  }

  @Override
  public float getFromTime() {
    return colChange.getFromTime();
  }

  @Override
  public float getToTime() {
    return colChange.getToTime();
  }

  @Override
  public <T> T accept(AnimationVisitor<T> v) {
    throw new UnsupportedOperationException("If you got here you messed up.");
  }

  @Override
  public MyColor getFrom() {
    return colChange.getFrom();
  }

  @Override
  public MyColor getTo() {
    return colChange.getTo();
  }

  @Override
  public boolean hasNoConflictsWith(ShapeOperations stateAtFromTime, ShapeOperations stateAtToTime, AnimationOperations a) {
    return colChange.hasNoConflictsWith(stateAtFromTime, stateAtToTime, a);
  }

  @Override
  public boolean noConflictsWithHelper(ShapeOperations givenFrom, ShapeOperations givenTo, float givenFromTime, float givenToTime) {
    return colChange.noConflictsWithHelper(givenFrom, givenTo, givenFromTime, givenToTime);
  }

  @Override
  public void setShapeToBeAnimated(String shapeName) {
    colChange.setShapeToBeAnimated(shapeName);
  }
}
