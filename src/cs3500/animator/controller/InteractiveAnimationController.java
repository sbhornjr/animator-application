package cs3500.animator.controller;

import java.awt.event.ActionEvent;

import javax.swing.*;

import cs3500.animator.model.shapes.IShape;
import cs3500.animator.provider.controller.ControllerOperations;
import cs3500.animator.provider.model.AnimatorModelOperations;
import cs3500.animator.provider.view.visual.InteractiveViewOperations;
import cs3500.animator.view.IInteractiveView;

/**
 * The controller for the interactive animation:
 * transmits inputs between the user and the view.
 */
public class InteractiveAnimationController implements IAnimationController,
        IInteractiveAnimationController, ControllerOperations, ProviderController {

  private IInteractiveView view;

  /**
   * Constructor for the InteractiveAnimationController.
   * @param view  The view.
   */
  public InteractiveAnimationController(IInteractiveView view) {
    this.view = view;
    this.view.setActionListener(this);
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

  @Override
  public void addModel(AnimatorModelOperations m) {

  }

  @Override
  public void addView(InteractiveViewOperations v) {

  }

  @Override
  public void startAnimator(int tempo) {

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "START":
        start();
        break;
      case "TOGGLE PAUSE":
        pauseToggled();
        break;
      case "TOGGLE LOOP":
        loopToggled();
        break;
      case "EXPORT":
        export(((JTextField)e.getSource()).getText() + ".svg");
        break;
      case "SPEED CHANGE":
        speedChanged(Integer.parseInt(((JTextField)e.getSource()).getText()));
        break;
      case "SHAPE REMOVED":
        shapeToggled((IShape) ((JComboBox)e.getSource()).getSelectedItem());
        break;

      case "PLAY":
        pPlay();
        break;
      case "PAUSE":
        pPause();
        break;
      case "RESET":
        p
      case "LOOP":
      case "SVG":
      case "SVG FIELD":
      case "SPEED":
    }
  }

  @Override
  public void pPlay() {

  }

  @Override
  public void pPause() {

  }

  @Override
  public void pReset() {

  }

  @Override
  public void pLoop() {

  }

  @Override
  public void psvg() {

  }

  @Override
  public void psvgField() {

  }

  @Override
  public void pSpeed() {

  }
}
