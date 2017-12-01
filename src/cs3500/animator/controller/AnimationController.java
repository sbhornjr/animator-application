package cs3500.animator.controller;

import java.util.ArrayList;

import cs3500.animator.model.model.AnimatorModel;
import cs3500.animator.provider.model.AnimatorModelOperations;
import cs3500.animator.provider.view.model.AnimatorViewOperations;

/**
 * The controller for the animation application:
 * transmits inputs between the user and the view.
 */
public class AnimationController implements IAnimationController {

  private AnimatorModelOperations model;
  private AnimatorViewOperations view;
  private int speed;

  /**
   * Constructor for the AnimationController.
   * @param view  The view.
   */
  public AnimationController(AnimatorModelOperations model, AnimatorViewOperations view, int speed) {
    this.model = model;
    this.view = view;
    this.speed = speed;
  }

  @Override
  public void start() {
    view.playAnimation(speed, model.getState());
  }
}
