package cs3500.animator.provider.view.model;

import cs3500.animator.provider.model.ShapeOperations;

import java.util.ArrayList;

/**
 * Represents the functionality of an VisualView object.
 */
public interface AnimatorViewOperations {

  /**
   * Plays the animation according to the tick rate of the view.
   */
  void playAnimation(int tempo, ArrayList<ShapeOperations> state);

}
