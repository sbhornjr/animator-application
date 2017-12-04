package cs3500.animator.model.actions;

import cs3500.animator.model.misc.Posn;
import cs3500.animator.model.shapes.IShape;
import cs3500.animator.provider.model.AnimationOperations;
import cs3500.animator.provider.model.IMoveAnimation;
import cs3500.animator.provider.model.ShapeOperations;
import cs3500.animator.provider.view.visitors.AnimationVisitor;

/**
 * Represents a move applied to a shape in the animator.
 */
public class Move implements IAction, IMoveAnimation {

  private IShape s;
  private Posn oldLocation;
  private Posn newLocation;
  private Posn duration;
  private final ActionType type;

  /**
   * Constructs a cs3500.animator.model.actions.Move object.
   *
   * @param s            The shape to be moved
   * @param oldLocation  The shape's location before the move
   * @param newLocation  The shape's location after the move
   * @param duration     The duration of this move
   */
  public Move(IShape s, Posn oldLocation, Posn newLocation, Posn duration) {
    this.s = s;
    this.oldLocation = oldLocation;
    this.newLocation = newLocation;
    type = ActionType.MOVE;

    if (s.getAppear() <= duration.getX() && s.getDisappear() >= duration.getDoubleY()) {
      this.duration = duration;
    }
    else {
      throw new IllegalArgumentException("The duration of this move must be within the shape's "
              + "lifetime.");
    }
  }

  @Override
  public void execute(double t) {
    double ta = duration.getX();
    double tb = duration.getDoubleY();

    double a = oldLocation.getX();
    double b = newLocation.getX();
    int ftX = (int) (a * ((tb - t) / (tb - ta)) + b * ((t - ta) / (tb - ta)));

    a = oldLocation.getDoubleY();
    b = newLocation.getDoubleY();
    int ftY = (int) (a * ((tb - t) / (tb - ta)) + b * ((t - ta) / (tb - ta)));

    ((IShape) s).setLocation(new Posn(ftX, ftY));
  }

  @Override
  public Posn getDuration() {
    return this.duration;
  }

  @Override
  public ActionType getType() {
    return this.type;
  }

  @Override
  public String getShapeName() {
    return this.s.getName();
  }

  @Override
  public String getDescription() {
    String str = "";
    str += "moves from " + oldLocation + " to " + newLocation + " ";
    return str;
  }

  @Override
  public String getSVGDescription(double speed) {
    String str = "";
    if (this.oldLocation.getIntX() != this.newLocation.getIntX()) {
      str += "    <animate attributeType=\"xml\" begin=\""
              + (int) ((this.duration.getX() / speed) * 1000) + "ms\" dur=\""
              + (int) (((this.duration.getDoubleY() - this.duration.getX()) / speed) * 1000)
              + "ms\" attributeName=\"x\" from=\"" + this.oldLocation.getIntX() + "\" to=\""
              + this.newLocation.getIntX() + "\" fill=\"freeze\" />\n";
    }
    if (this.oldLocation.getIntY() != this.newLocation.getIntY()) {
      str += "    <animate attributeType=\"xml\" begin=\""
              + (int) ((this.duration.getX() / speed) * 1000) + "ms\" dur=\""
              + (int) (((this.duration.getDoubleY() - this.duration.getX()) / speed) * 1000)
              + "ms\" attributeName=\"y\" from=\"" + this.oldLocation.getIntY() + "\" to=\""
              + this.newLocation.getIntY() + "\" fill=\"freeze\" />\n";
    }

    return str;
  }

  //================================================================================================

  @Override
  public void animate(ShapeOperations shapeToBeChanged, float currentTime) {
    s = (IShape) shapeToBeChanged;
    if (currentTime >= getDuration().getDoubleX() && currentTime <= getDuration().getDoubleY()) {
      execute(currentTime);
    }
  }

  @Override
  public float getFromTime() {
    return (float) duration.getX();
  }

  @Override
  public float getToTime() {
    return (float) duration.getDoubleY();
  }

  @Override
  public <T> T accept(AnimationVisitor<T> v) {
    return v.visit(this);
  }

  @Override
  public Posn getFrom() {
    return oldLocation;
  }

  @Override
  public Posn getTo() {
    return newLocation;
  }

  @Override
  public boolean hasNoConflictsWith(ShapeOperations stateAtFromTime, ShapeOperations stateAtToTime,
                                    AnimationOperations a) {
    // unsupported
    return false;
  }

  @Override
  public boolean noConflictsWithHelper(ShapeOperations givenFrom, ShapeOperations givenTo,
                                       float givenFromTime, float givenToTime) {
    // unsupported
    return false;
  }

  @Override
  public void setShapeToBeAnimated(String shapeName) {
    s.setName(shapeName);
  }
}
