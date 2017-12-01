package cs3500.animator.controller;

public interface ProviderController {

  /**
   * The provider's play button has been pushed:
   * relay this information to the view.
   */
  void pPlay();

  /**
   * The provider's pause button has been pushed:
   * relay this information to the view.
   */
  void pPause();

  /**
   * The provider's reset button has been pushed:
   * relay this information to the view.
   */
  void pReset();

  /**
   * The provider's loop button has been pushed:
   * relay this information to the view.
   */
  void pLoop();

  /**
   * The provider's svg button has been pushed:
   * relay this information to the view.
   */
  void psvg();

  /**
   * The provider's svg field has been activated:
   * relay this information to the view.
   */
  void psvgField();

  /**
   * The provider's speed functionality has been activated:
   * relay this information to the view.
   */
  void pSpeed();
}
