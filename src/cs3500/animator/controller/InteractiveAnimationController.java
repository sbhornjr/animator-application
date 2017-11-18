package cs3500.animator.controller;

import cs3500.animator.model.shapes.IShape;
import cs3500.animator.view.IInteractiveView;

/**
 * The controller for the interactive animation:
 * transmits inputs between the user and the view.
 */
public class InteractiveAnimationController implements IAnimationController,
        IInteractiveAnimationController {

  private IInteractiveView view;

  /**
   * Constructor for the InteractiveAnimationController.
   * @param view  The view.
   */
  public InteractiveAnimationController(IInteractiveView view) {
    this.view = view;

    view.setButtonListener(new ButtonListener(this));
    view.setTextFieldListener(new TextFieldListener(this));
    view.setComboBoxListener(new ComboBoxListener(this));
    view.addActionListeners();
  }

  @Override
  public void start() {
    view.run();
  }

  @Override
  public void pauseToggled() {
    view.togglePause();
  }

  @Override
  public void loopToggled() {
    view.toggleLoop();
  }

  @Override
  public void export(String ofile) {
    view.export(ofile);
  }

  @Override
  public void speedChanged(int newSpeed) {
    view.setSpeed(newSpeed);
  }

  @Override
  public void shapeToggled(IShape s) {
    view.toggleShape(s);
  }
}
