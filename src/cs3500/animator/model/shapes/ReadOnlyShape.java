package cs3500.animator.model.shapes;

import cs3500.animator.model.actions.IAction;
import cs3500.animator.model.misc.IMyColor;
import cs3500.animator.provider.model.AnimationOperations;
import cs3500.animator.provider.model.IDimension;
import cs3500.animator.provider.model.IPosn;
import cs3500.animator.provider.model.ShapeOperations;
import cs3500.animator.provider.view.visitors.ShapeVisitor;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A read-only version of the Shape class that cannot be mutated by the view.
 */
public abstract class ReadOnlyShape implements IShape {

  protected IShape shape;

  /**
   * Represents a generic read-only shape.
   *
   * @param sh  The regular shape that should be represented in a read-only way here.
   */
  public ReadOnlyShape(IShape sh) {
    this.shape = sh;
  }

  @Override
  public String getName() {
    return shape.getName();
  }

  @Override
  public int getX() {
    return shape.getX();
  }

  @Override
  public int getY() {
    return shape.getY();
  }

  @Override
  public Color getColor() {
    return shape.getColor();
  }

  @Override
  public int getWidth() {
    return shape.getWidth();
  }

  @Override
  public int getHeight() {
    return shape.getHeight();
  }

  @Override
  public double getAppear() {
    return shape.getAppear();
  }

  @Override
  public double getDisappear() {
    return shape.getDisappear();
  }

  @Override
  public ShapeType getType() {
    return shape.getType();
  }

  @Override
  public String getPosLocation() {
    return shape.getPosLocation();
  }

  @Override
  public String[] getWLTypes() {
    return shape.getWLTypes();
  }

  @Override
  public void setLocation(cs3500.animator.model.misc.IPosn newLocation) {
    throw new UnsupportedOperationException("Can't mutate shapes in a read-only model.");
  }

  @Override
  public void setDimensions(cs3500.animator.model.misc.IPosn newDimensions) {
    throw new UnsupportedOperationException("Can't mutate shapes in a read-only model.");
  }

  @Override
  public void setColor(IMyColor newColor) {
    throw new UnsupportedOperationException("Can't mutate shapes in a read-only model.");
  }

  @Override
  public void addAction(IAction a) {
    throw new UnsupportedOperationException("Can't mutate shapes in a read-only model.");
  }

  @Override
  public ArrayList<AnimationOperations> getActions() {
    return shape.getActions();
  }

  @Override
  public String getColorAsInt() {
    return shape.getColorAsInt();
  }

  @Override
  public void setDefault() {
    shape.setDefault();
  }

  @Override
  public boolean isVisible() {
    return shape.isVisible();
  }

  @Override
  public void setVisible() {
    shape.setVisible();
  }

  @Override
  public String toString() {
    return shape.toString();
  }

  @Override
  public boolean equals(Object o) {
    return shape.equals(o);
  }

  @Override
  public int hashCode() {
    return shape.hashCode();
  }

  @Override
  public void setName(String newName) {
    throw new UnsupportedOperationException("Can't mutate shapes in a read-only model.");
  }

  @Override
  public void changeColor(Color fromColor, Color toColor, float from, float to, float currentTime) {
    shape.changeColor(fromColor, toColor, from, to, currentTime);
  }

  @Override
  public void changeAngle(float fromAngle, float toAngle, float from, float to, float currentTime) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void changePosition(IPosn fromPosn, IPosn toPosn, float from, float to,
                             float currentTime) {
    shape.changePosition(fromPosn, toPosn, from, to, currentTime);
  }

  @Override
  public void changeDimension(ArrayList<IDimension> fromDimension,
                              ArrayList<IDimension> toDimension, float from, float to,
                              float currentTime) {
    shape.changeDimension(fromDimension, toDimension, from, to, currentTime);
  }

  @Override
  public ArrayList<IDimension> getDimensions() {
    return shape.getDimensions();
  }

  @Override
  public float getAngle() {
    throw new UnsupportedOperationException();
  }

  @Override
  public IPosn getPosition() {
    return shape.getPosition();
  }

  @Override
  public float getAppearTime() {
    return shape.getAppearTime();
  }

  @Override
  public float getDisappearTime() {
    return shape.getDisappearTime();
  }

  @Override
  public ArrayList<AnimationOperations> getAnimations() {
    return shape.getAnimations();
  }

  @Override
  public void addAnimation(AnimationOperations... toBeAdded) {
    throw new UnsupportedOperationException("Can't mutate shapes in a read-only model.");
  }

  @Override
  public void animate(float currentTime) {
    throw new UnsupportedOperationException("Can't mutate shapes in a read-only model.");
  }

  @Override
  public void removeAnimation(AnimationOperations... toBeRemoved) {
    throw new UnsupportedOperationException("Can't mutate shapes in a read-only model.");
  }

  @Override
  public ShapeOperations makeClone() {
    return shape.makeClone();
  }

  @Override
  public <T> T accept(ShapeVisitor<T> v) {
    throw new UnsupportedOperationException("Can't get here.");
  }
}
