package cs3500.animator.provider.model;

import java.util.ArrayList;

/**
 * represents the functionality of an animator model object.
 */
public interface AnimatorModelOperations {

  /**
   * Used to add a new animation to a model.
   * @param shapeName - the name of the shape we are adding the animation to.
   * @param anim - represents the animation to be added.
   */
  void addAnimation(String shapeName, AnimationOperations anim);

  /**
   * Used to remove an animation from this model.
   * @param shapeName - the name of the shape we are removing the animation from.
   * @param anim - represents the animation to be removed.
   */
  void removeAnimation(String shapeName, AnimationOperations anim);

  /**
   * Used to add a shape to this model.
   * @param s adds s to the list of shapes in this model.
   */
  void addShape(ShapeOperations s);

  /**
   * Used to add a shape to this model at the given position.
   * @param s adds s to the list of shapes in this model at the given position.
   * @param position the position we want to put the shape.
   */
  void addShape(ShapeOperations s, int position);

  /**
   * Used to remove a shape from this model.
   * @param name removes the shape with the given name from the list of shapes in this model.
   */
  void removeShape(String name);

  /**
   * Returns the shape with the given name from the model.
   * @param name The shape we are looking to return.
   * @return The shape with the given name.
   */
  ShapeOperations getShape(String name);

  /**
   * Checks if the given shape is in this model.
   * @param name The name of the shape we are looking for in the model.
   * @return true if the shape is in the model, false if not.
   */
  boolean contains(String name);

  /**
   * Used to return this model as a string.
   */
  String toString();

  /**
   * Used to return the current state of our animation.
   */
  ArrayList<ShapeOperations> getState();

}
