package cs3500.animator.controller;

public interface IAnimationController {

  /**
   * A listener has notified the controller that the start button has been pushed:
   * Relay this information to the view.
   */
  void start();
}
