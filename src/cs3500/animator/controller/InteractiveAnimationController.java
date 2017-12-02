package cs3500.animator.controller;

import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

import cs3500.animator.model.model.AnimatorModel;
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
  private FileWriter fw;
  private int tempo;
  private AnimatorModelOperations m;

  /**
   * Constructor for the InteractiveAnimationController.
   * @param view  The view.
   */
  public InteractiveAnimationController(AnimatorModelOperations model, InteractiveViewOperations view, int speed) {
    view.setActionListener(this);
    this.theirView = view;

    this.model = model;
    this.fw = null;
    this.tempo = speed;
    this.m = new AnimatorModel();

    try {
      this.ourView = (IInteractiveView) view;
    } catch (ClassCastException e) {
      // the given view is a provider hybrid view
      startAnimator(tempo);
    }
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
    this.model = m;
  }

  @Override
  public void addView(InteractiveViewOperations v) {
    this.theirView = v;
  }

  @Override
  public void startAnimator(int tempo) {
    theirView.playAnimation(tempo, model.getState());
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
        pReset();
        break;
      case "LOOP":
        pLoop();
        break;
      case "SVG":
        psvg();
        break;
      case "SVG FIELD":
        psvgField(((JTextField)e.getSource()).getText());
        break;
      case "SPEED":
        pSpeed(Integer.parseInt(((JTextField)e.getSource()).getText()));
        break;
      case "SPEED UP":
        pSpeedUp();
        break;
      case "SLOW DOWN":
        pSpeedDown();
        break;
      default:
        pShape(e.getActionCommand());
    }
  }

  @Override
  public void pPlay() {
    theirView.play();
  }

  @Override
  public void pPause() {
    theirView.pause();
  }

  @Override
  public void pReset() {
    theirView.reset();
  }

  @Override
  public void pLoop() {
    theirView.loop();
  }

  @Override
  public void psvg() {
    String svg = theirView.getSVG(tempo, model.getState(), 1000, 1000);
    try {
      fw.append(svg);
      fw.close();
    } catch (IOException e) {
      System.out.println("ERROR: " + e.getMessage());
    }
  }

  @Override
  public void psvgField(String ofile) {
    try {
      fw = new FileWriter(ofile);
    } catch (IOException e) {
      System.out.println("ERROR: " + e.getMessage());
    }
  }

  @Override
  public void pSpeed(int newSpeed) {
    this.tempo = newSpeed;
    theirView.setTempo(tempo);
  }

  @Override
  public void pSpeedUp() {
    this.tempo += 1;
    theirView.setTempo(tempo);
  }

  @Override
  public void pSpeedDown() {
    this.tempo -= 1;
    theirView.setTempo(tempo);
  }

  @Override
  public void pShape(String shape) {
    if (model.contains(shape)) {
      m.addShape(model.getShape(shape));
      model.removeShape(shape);
      startAnimator(tempo);
    } else {
      model.addShape(m.getShape(shape));
      m.removeShape(shape);
      startAnimator(tempo);
    }
  }
}