package cs3500.animator.controller;

import java.awt.event.ActionEvent;

import javax.swing.*;

import cs3500.animator.model.shapes.IShape;
import cs3500.animator.provider.controller.ControllerOperations;
import cs3500.animator.provider.model.AnimatorModelOperations;
import cs3500.animator.provider.view.model.AnimatorViewOperations;
import cs3500.animator.provider.view.visual.InteractiveViewOperations;
import cs3500.animator.view.IInteractiveView;

/**
 * The controller for the interactive animation:
 * transmits inputs between the user and the view.
 */
public class InteractiveAnimationController implements IAnimationController,
        IInteractiveAnimationController, ControllerOperations, ProviderController {

  private AnimatorModelOperations model;
  private IInteractiveView ourView;
  private InteractiveViewOperations theirView;

  /**
   * Constructor for the InteractiveAnimationController.
   * @param view  The view.
   */
  public InteractiveAnimationController(AnimatorModelOperations model, InteractiveViewOperations view) {
    this.theirView = view;
    this.theirView.setActionListener(this);
    this.ourView = (IInteractiveView) view;
    this.ourView.setActionListener(this);
    this.model = model;
  }

  @Override
  public void start() {
    ourView.run();
  }

  @Override
  public void pauseToggled() {
    ourView.togglePause();
  }

  @Override
  public void loopToggled() {
    ourView.toggleLoop();
  }

  @Override
  public void export(String ofile) {
    ourView.export(ofile);
  }

  @Override
  public void speedChanged(int newSpeed) {
    ourView.setSpeed(newSpeed);
  }

  @Override
  public void shapeToggled(IShape s) {
    ourView.toggleShape(s);
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
