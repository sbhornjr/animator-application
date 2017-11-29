package cs3500.animator.provider.view.visual;

import cs3500.animator.provider.model.ShapeOperations;
import cs3500.animator.provider.view.AnimatorViewOperations;

import java.awt.event.ActionListener;
import java.util.ArrayList;

public interface InteractiveViewOperations extends AnimatorViewOperations {

  /**
   * This method is used to get the tempo of this animation.
   * @return The tempo of this animation.
   */
  int getTempo();

  /**
   * This method is used to toggle the loop function of the view.
   */
  void loop();

  /**
   * This method pauses the animation if its running.
   */
  void pause();

  /**
   * This method plays the animation if its paused.
   */
  void play();

  /**
   * This method is used to set the tempo of our animation.
   * @param tempo - the tempo this view is running at.
   */
  void setTempo(int tempo);

  /**
   * This method resets all the shapes in the animation and runs it at the beginning.
   */
  void reset();

  /**
   * This method is used by a controller to add an action listener to our buttons.
   * @param controller - the controller we are using to control our view.
   */
  void setActionListener(ActionListener controller);

  /**
   * Used to get this animation as an svg.
   * @param tempo - the rate we are running our animation at.
   * @param state - the state of shapes and animations we want to animate.
   * @param svgHeight - the height of the svg animation
   * @param svgWidth - the width of the svg animation
   * @return the svg format of this animation as a string.
   */
  String getSVG(int tempo, ArrayList<ShapeOperations> state, int svgHeight, int svgWidth);

}
