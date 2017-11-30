package cs3500.animator.controller;

import java.util.ArrayList;

import cs3500.animator.provider.view.AnimatorViewOperations;

/**
 * The controller for the animation application:
 * transmits inputs between the user and the view.
 */
public class AnimationController implements IAnimationController {

  private AnimatorViewOperations view;
  private int speed;

  /**
   * Constructor for the AnimationController.
   * @param view  The view.
   */
  public AnimationController(AnimatorViewOperations view, int speed) {
    this.view = view;
    this.speed = speed;
  }

  @Override
  public void start() {
    view.playAnimation(speed, new ArrayList<>());
  }
}
