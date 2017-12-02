package cs3500.animator.model.shapes;

import java.awt.Color;
import java.util.ArrayList;

import cs3500.animator.model.actions.IAction;
import cs3500.animator.model.misc.Dimension;
import cs3500.animator.model.misc.IMyColor;
import cs3500.animator.model.misc.MyColor;
import cs3500.animator.model.misc.Posn;
import cs3500.animator.provider.model.AnimationOperations;
import cs3500.animator.provider.model.IDimension;
import cs3500.animator.provider.model.IPosn;
import cs3500.animator.provider.model.ShapeOperations;

/**
 * Represents a polygon in an animation.
 */
public abstract class Shape implements IShape {

  protected String name;
  protected Posn location;
  protected Posn dimensions;
  protected MyColor color;
  protected Posn lifetime;
  protected ShapeType type;
  protected ArrayList<AnimationOperations> actions;
  protected Posn initLocation;
  protected Posn initDimensions;
  protected MyColor initColor;
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
  public Shape(String name, Posn location, Posn dimensions, MyColor color, Posn lifetime) {
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
    return this.color;
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
  public void setLocation(cs3500.animator.model.misc.IPosn newLocation) {
    location = (Posn) newLocation;
  }

  @Override
  public void setDimensions(cs3500.animator.model.misc.IPosn newDimensions) {
    dimensions = (Posn) newDimensions;
  }

  @Override
  public void setColor(IMyColor newColor) {
    color = (MyColor) newColor;
  }

  @Override
  public void addAction(IAction a) {
    this.actions.add(a);
  }

  @Override
  public ArrayList<AnimationOperations> getActions() {
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

  //================================================================================================

  @Override
  public void changeColor(Color fromColor, Color toColor, float from, float to, float currentTime) {
    // not used
  }

  @Override
  public void changeAngle(float fromAngle, float toAngle, float from, float to, float currentTime) {
    // not used
  }

  @Override
  public void changePosition(IPosn fromPosn,
                             IPosn toPosn, float from, float to,
                             float currentTime) {
    // not used
  }

  @Override
  public void changeDimension(ArrayList<IDimension> fromDimension,
                              ArrayList<IDimension> toDimension, float from, float to,
                              float currentTime) {
    // not used
  }

  @Override
  public ArrayList<IDimension> getDimensions() {
    ArrayList<IDimension> result = new ArrayList<>();
    result.add(new Dimension("width", dimensions.getX()));
    result.add(new Dimension("height", dimensions.getY()));
    return result;
  }

  @Override
  public float getAngle() {
    throw new UnsupportedOperationException();
  }

  @Override
  public IPosn getPosition() {
    return new Posn(location.getX(), location.getY());
  }

  @Override
  public float getAppearTime() {
    return lifetime.getX();
  }

  @Override
  public float getDisappearTime() {
    return lifetime.getY();
  }

  @Override
  public ArrayList<AnimationOperations> getAnimations() {
    return actions;
  }

  @Override
  public void addAnimation(AnimationOperations... toBeAdded) {
    for (AnimationOperations a : toBeAdded) {
      actions.add(a);
    }
  }

  @Override
  public void animate(float currentTime) {
    for (AnimationOperations a : actions) {
      a.animate(this, currentTime);
    }
  }

  @Override
  public void removeAnimation(AnimationOperations... toBeRemoved) {
    for (AnimationOperations a : toBeRemoved) {
      if (actions.contains(a)) {
        actions.remove(a);
      }
    }
  }

  @Override
  public ShapeOperations makeClone() {
    throw new UnsupportedOperationException("Shapes cannot be initialized.");
  }

  @Override
  public void setName(String newName) {
    name = newName;
  }
}
