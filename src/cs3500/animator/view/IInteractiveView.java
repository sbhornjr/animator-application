package cs3500.animator.view;

import cs3500.animator.controller.ButtonListener;
import cs3500.animator.controller.TextFieldListener;

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
   * Adds the newly set listeners to each button.
   */
  void addActionListeners();

  /**
   *
   */
  void togglePause();

  /**
   *
   */
  void toggleLoop();

  /**
   *
   * @param newSpeed
   */
  void setSpeed(double newSpeed);

  /**
   *
   */
  void restart();
}
