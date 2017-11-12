package cs3500.animator.view;

import cs3500.animator.controller.ButtonListener;
import cs3500.animator.controller.TextFieldListener;

/**
 * Describes the methods that an animation view must implement.
 */
public interface IAnimationView {

  /**
   * Displays an animation in a certain text format.
   */
  void display();

  /**
   * Runs an animation.
   */
  void run();

  /**
   * Returns this view's type.
   *
   * @return  The type
   */
  ViewType getViewType();

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
}
