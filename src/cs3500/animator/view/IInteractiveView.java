package cs3500.animator.view;

import cs3500.animator.controller.ButtonListener;
import cs3500.animator.controller.ComboBoxListener;
import cs3500.animator.controller.TextFieldListener;
import cs3500.animator.model.shapes.IShape;

/**
 * The interface that dictates what methods a view for an interactive animation application must
 * implement.
 */
public interface IInteractiveView {

  /**
   * Runs an animation.
   */
  void run();

  /**
   * Displays an animation in SVG text format.
   */
  void display();

  /**
   * Sets this view's button listener to the given one.
   *
   * @param bl  The button listener to be set
   */
  void setButtonListener(ButtonListener bl);

  /**
   * Sets this view's text field listener to the given one.
   *
   * @param tfl  The text field listener to be set
   */
  void setTextFieldListener(TextFieldListener tfl);

  /**
   * Sets this view's combo box listener to the given one.
   *
   * @param cbl  The combo box listener to be set
   */
  void setComboBoxListener(ComboBoxListener cbl);

  /**
   * Adds the newly set listeners to each button.
   */
  void addActionListeners();

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
