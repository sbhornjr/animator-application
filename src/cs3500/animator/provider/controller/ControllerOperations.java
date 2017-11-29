package cs3500.animator.provider.controller;

import java.awt.event.ActionEvent;

import cs3500.animator.provider.model.AnimatorModelOperations;
import cs3500.animator.provider.view.visual.InteractiveViewOperations;

public interface ControllerOperations extends  java.awt.event.ActionListener {

  /**
   * This method is used to add the model.
   * @param m the model we are adding to our controller.
   */
  void addModel(AnimatorModelOperations m);

  /**
   * This method is used to add the view.
   * @param v the view we are adding to our controller.
   */
  void addView(InteractiveViewOperations v);

  /**
   * This method is used to start the animator.
   * @param tempo the tempo we are running at.
   */
  void startAnimator(int tempo);

  /**
   * This method is used to tell if a button has been pressed.
   * @param e The action event we are checking for action from.
   */
  void actionPerformed(ActionEvent e);

}
