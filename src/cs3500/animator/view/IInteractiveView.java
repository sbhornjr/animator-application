package cs3500.animator.view;

import cs3500.animator.model.shapes.IShape;
import cs3500.animator.provider.view.visual.InteractiveViewOperations;

/**
 * The interface that dictates what methods a view for an interactive animation application must
 * implement.
 */
public interface IInteractiveView extends InteractiveViewOperations {

  /**
   * Runs an animation.
   */
  void run();

  /**
   * Displays an animation in SVG text format.
   */
  void display();

  /**
   * Toggles whether or not the animation is paused.
   */
  void togglePause();

  /**
   * Toggles whether or not the animation will loop back to the start when it finishes.
   */
  void toggleLoop();

  /**
   * Sets the animation's speed to the given new speed.
   *
   * @param newSpeed  The new speed
   */
  void setSpeed(int newSpeed);

  /**
   * Outputs the animation as a SVG animation to the specified output file.
   *
   * @param ofile   The output file as a string.
   */
  void export(String ofile);

  /**
   * Toggles whether or not the given shape is visible in the animation.
   *
   * @param s The shape
   */
  void toggleShape(IShape s);
}
