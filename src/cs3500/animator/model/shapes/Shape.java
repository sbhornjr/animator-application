package cs3500.animator.model.shapes;

import java.awt.*;
import java.util.ArrayList;

import cs3500.animator.model.actions.ColorChange;
import cs3500.animator.model.actions.IAction;
import cs3500.animator.model.actions.Move;
import cs3500.animator.model.actions.Scale;
import cs3500.animator.model.misc.IMyColor;
import cs3500.animator.model.misc.IPosn;
import cs3500.animator.model.misc.MyColor;
import cs3500.animator.model.misc.Posn;
import cs3500.animator.provider.model.AnimationOperations;
import cs3500.animator.provider.model.IDimension;
import cs3500.animator.provider.model.ShapeOperations;
import cs3500.animator.provider.view.visitors.ShapeVisitor;

/**
 * Represents a polygon in an animation.
 */
public abstract class Shape implements IShape, ShapeOperations {

  protected String name;
  protected IPosn location;
  protected IPosn dimensions;
  protected IMyColor color;
  protected IPosn lifetime;
  protected ShapeType type;
  protected ArrayList<IAction> actions;
  protected IPosn initLocation;
  protected IPosn initDimensions;
  protected IMyColor initColor;
  protected boolean isVisible;

  /**
   * Represents a generic shape.
   *
   * @param name        This shape's name
   * @param location    The xy coorinates of this shape
   * @param dimensions  The dimensions of this shape
   * @param color       This shape's color
   * @param lifetime    The times that this shape appears and disappears
   */
  public Shape(String name, IPosn location, IPosn dimensions, IMyColor color, IPosn lifetime) {
    this.name = name;
    this.dimensions = dimensions;
    this.color = color;
    this.lifetime = lifetime;
    this.location = location;
    this.actions = new ArrayList<>();
    this.initDimensions = dimensions;
    this.initLocation = location;
    this.initColor = color;
    this.isVisible = true;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public int getX() {
    return this.location.getIntX();
  }

  @Override
  public int getY() {
    return this.location.getIntY();
  }

  @Override
  public Color getColor() {
    return (Color) this.color;
  }

  @Override
  public int getWidth() {
    return this.dimensions.getIntX();
  }

  @Override
  public int getHeight() {
    return this.dimensions.getIntY();
  }

  @Override
  public double getAppear() {
    return this.lifetime.getX();
  }

  @Override
  public double getDisappear() {
    return this.lifetime.getY();
  }

  @Override
  public ShapeType getType() {
    return this.type;
  }

  @Override
  public String getPosLocation() {
    return "Center";
  }

  @Override
  public String[] getWLTypes() {
    String[] arr = {"Width", "Height"};
    return arr;
  }

  @Override
  public void setLocation(IPosn newLocation) {
    location = newLocation;
  }

  @Override
  public void setDimensions(IPosn newDimensions) {
    dimensions = newDimensions;
  }

  @Override
  public void setColor(IMyColor newColor) {
    color = newColor;
  }

  @Override
  public void addAction(IAction a) {
    this.actions.add(a);
  }

  @Override
  public ArrayList<IAction> getActions() {
    return this.actions;
  }

  @Override
  public String getColorAsInt() {
    return this.color.asInt();
  }

  @Override
  public void setDefault() {
    this.dimensions = initDimensions;
    this.location = initLocation;
    this.color = initColor;
  }

  @Override
  public boolean isVisible() {
    return this.isVisible;
  }

  @Override
  public void setVisible() {
    isVisible = !isVisible;
  }

  @Override
  public String toString() {
    String shapeType = type.toString();
    shapeType = shapeType.charAt(0) + shapeType.substring(1).toLowerCase();
    String s = shapeType + " " + name;

    if (isVisible) {
      return "✓" + s;
    }
    else {
      return s;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Shape) {
      return this.name.equals(((Shape) o).name)
              && this.type == ((Shape) o).type;
    }
    else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    int result = 0;
    if (this.type == ShapeType.RECTANGLE) {
      result += 1000;
    }
    return result + this.getHeight() + this.getWidth() + this.getX() + this.getY()
            + this.getColor().getRGB();
  }

  //======================================================================================================

  @Override
  public void changeColor(Color fromColor, Color toColor, float from, float to, float currentTime) {
    ColorChange cc = new ColorChange(this, (MyColor) fromColor, (MyColor) toColor, new Posn(from, to));
    cc.execute(currentTime);
    actions.add(cc);
  }

  @Override
  public void changeAngle(float fromAngle, float toAngle, float from, float to, float currentTime) {

  }

  @Override
  public void changePosition(IPosn fromPosn, IPosn toPosn, float from, float to, float currentTime) {
    Move m = new Move(this, (Posn) fromPosn, (Posn) toPosn, new Posn(from, to));
    m.execute(currentTime);
    actions.add(m);
  }

  @Override
  public void changeDimension(ArrayList<IDimension> fromDimension, ArrayList<IDimension> toDimension,
                              float from, float to, float currentTime) {
    Scale s = new Scale(this, )
  }

  @Override
  public ArrayList<IDimension> getDimensions() {
    return null;
  }

  @Override
  public float getAngle() {
    return 0;
  }

  @Override
  public cs3500.animator.provider.model.IPosn getPosition() {
    return null;
  }

  @Override
  public float getAppearTime() {
    return 0;
  }

  @Override
  public float getDisappearTime() {
    return 0;
  }

  @Override
  public ArrayList<AnimationOperations> getAnimations() {
    return null;
  }

  @Override
  public void addAnimation(AnimationOperations... toBeAdded) {

  }

  @Override
  public void animate(float currentTime) {

  }

  @Override
  public void removeAnimation(AnimationOperations... toBeRemoved) {

  }

  @Override
  public ShapeOperations makeClone() {
    return null;
  }

  @Override
  public <T> T accept(ShapeVisitor<T> v) {
    return null;
  }
}
