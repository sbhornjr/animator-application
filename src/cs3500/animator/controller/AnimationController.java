package cs3500.animator.controller;

import cs3500.animator.model.model.IAnimatorOperations;
import cs3500.animator.view.IAnimationView;
import cs3500.animator.view.ViewType;

/**
 * The controller for the animation application:
 * transmits inputs between the user and the view.
 */
public class AnimationController implements IAnimationController {
  private IAnimationView view;

  /**
   * Constructor for the AnimationController.
   * @param view  The view.
   */
  public AnimationController(IAnimationView view) {
    if (view.getViewType() == ViewType.INTERACTIVE) {
      view.setButtonListener(new ButtonListener(this));
      view.setTextFieldListener(new TextFieldListener(this));

      view.addActionListeners();
    }
  }

  @Override
  public void start() {
    view.run();
  }

  @Override
  public void pauseToggled() {

  }

  @Override
  public void restart() {

  }

  @Override
  public void loopToggled() {

  }

  @Override
  public void export() {

  }

  @Override
  public void speedChanged(int newSpeed) {

  }
}
