package cs3500.animator.view.model;

import cs3500.animator.provider.model.AnimationOperations;
import cs3500.animator.provider.model.IDimension;
import cs3500.animator.provider.model.IPosn;
import cs3500.animator.provider.model.ShapeOperations;
import cs3500.animator.provider.view.model.AnimationViewOperations;
import cs3500.animator.provider.view.model.ShapeViewOperations;
import cs3500.animator.provider.view.visitors.ShapeVisitor;

import java.awt.*;
import java.util.ArrayList;

/**
 * The version of a shape that the view will get to interact with.
 */
public abstract class ShapeView implements ShapeViewOperations {

  protected ShapeOperations shape;
  protected ArrayList<AnimationViewOperations> animations;

  /**
   * The constructor for a shape view.
   * @param sh  The shape from the model that this shape view will represent.
   */
  public ShapeView(ShapeOperations sh) {
    this.shape = sh;
    this.animations = new ArrayList<>();
  }

  @Override
  public void addAnimation(AnimationViewOperations... toBeAdded) {
    for (AnimationViewOperations avo : toBeAdded) {
      animations.add(avo);
    }
  }

  @Override
  public ArrayList<AnimationViewOperations> getViewAnimations() {
    return animations;
  }

  @Override
  public void changeColor(Color fromColor, Color toColor, float from, float to, float currentTime) {
    shape.changeColor(fromColor, toColor, from, to, currentTime);
  }

  @Override
  public void changeAngle(float fromAngle, float toAngle, float from, float to, float currentTime) {
    shape.changeAngle(fromAngle, toAngle, from, to, currentTime);
  }

  @Override
  public void changePosition(IPosn fromPosn, IPosn toPosn, float from, float to, float currentTime) {
    shape.changePosition(fromPosn, toPosn, from, to, currentTime);
  }

  @Override
  public void changeDimension(ArrayList<IDimension> fromDimension, ArrayList<IDimension> toDimension, float from, float to, float currentTime) {
    shape.changeDimension(fromDimension, toDimension, from, to, currentTime);
  }

  @Override
  public ArrayList<IDimension> getDimensions() {
    return shape.getDimensions();
  }

  @Override
  public float getAngle() {
    return shape.getAngle();
  }

  @Override
  public String getName() {
    return shape.getName();
  }

  @Override
  public IPosn getPosition() {
    return shape.getPosition();
  }

  @Override
  public Color getColor() {
    return shape.getColor();
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
    shape.addAnimation(toBeAdded);
  }

  @Override
  public void animate(float currentTime) {
    shape.animate(currentTime);
  }

  @Override
  public void removeAnimation(AnimationOperations... toBeRemoved) {
    shape.removeAnimation(toBeRemoved);
  }

  @Override
  public ShapeOperations makeClone() {
    return shape.makeClone();
  }

  @Override
  public <T> T accept(ShapeVisitor<T> v) {
    throw new UnsupportedOperationException("If this is happening you messed up");
  }
}
