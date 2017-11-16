package cs3500.animator.controller;

/**
 * Outlines the methods for a Controller used with the Interactive view
 */
public interface IInteractiveAnimationController extends IAnimationController {

  /**
   * A listener has notified the controller that the user has toggled pause:
   * Relay this information to the view.
   */
  void pauseToggled();

  /**
   * A listener has notified the controller that the restart button has been pushed:
   * Relay this information to the view.
   */
  void restart();

  /**
   * A listener has notified the controller that the user has toggled looping:
   * Relay this information to the view.
   */
  void loopToggled();

  /**
   * A listener has notified the controller that the export button has been pushed:
   * Relay this information to the view.
   */
  void export();

  /**
   * A listener has notified the controller that the user has changed the speed:
   * Relay this information to the view.
   * @param newSpeed  The new speed that the user requested.
   */
  void speedChanged(double newSpeed);
}
