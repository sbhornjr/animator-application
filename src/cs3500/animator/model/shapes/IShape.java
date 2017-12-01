package cs3500.animator.model.shapes;

import java.awt.Color;
import java.util.ArrayList;

import cs3500.animator.model.actions.IAction;
import cs3500.animator.model.misc.IMyColor;
import cs3500.animator.model.misc.IPosn;
import cs3500.animator.provider.model.AnimationOperations;
import cs3500.animator.provider.model.ShapeOperations;

/**
 * Details the methods that a shape in the animator must use.
 */
public interface IShape extends ShapeOperations {

  /**
   * Gets this shape's name.
   *
   * @return  The name
   */
  String getName();

  /**
   * Sets this shape's name to the given one.
   *
   * @param newName the name
   */
  void setName(String newName);

  /**
   * Gets this shape's x-coordinate.
   *
   * @return  The x-coordinate
   */
  int getX();

  /**
   * Gets this shape's y-coordinate.
   *
   * @return  The y-coordinate
   */
  int getY();

  /**
   * Gets this shape's color.
   *
   * @return  The color
   */
  Color getColor();

  /**
   * Gets this shape's width.
   *
   * @return  The width
   */
  int getWidth();

  /**
   * Gets this shape's height.
   *
   * @return  The height
   */
  int getHeight();

  /**
   * Gets the time that this shape appears.
   *
   * @return  The time
   */
  double getAppear();

  /**
   * Gets the time that this shape disappears.
   *
   * @return  The time
   */
  double getDisappear();

  /**
   * Gets this shape's type.
   *
   * @return  The type
   */
  ShapeType getType();

  /**
   * Returns the location of this shape's position coordinates.
   *
   * @return  Either "Center" or "Lower-left corner"
   */
  String getPosLocation();

  /**
   * Returns the type of dimensions that this shape has as an array of strings.
   *
   * @return  Either {"Width", "Height"} or {"X radius", "Y radius"}
   */
  String[] getWLTypes();

  /**
   * Sets this shape's location to the given pair of numbers.
   *
   * @param newLocation The new location
   */
  void setLocation(IPosn newLocation);

  /**
   * Sets this shape's dimensions to the given pair of numbers.
   *
   * @param newDimensions The new dimensions
   */
  void setDimensions(IPosn newDimensions);

  /**
   * Sets this shape's color to the given one.
   *
   * @param newColor  The new color
   */
  void setColor(IMyColor newColor);

  /**
   * Adds the given Action to this shape's list of actions.
   *
   * @param a   The action to be added to the list.
   */
  void addAction(IAction a);

  /**
   * Returns the list of actions that affects this shape.
   *
   * @return  The list of actions.
   */
  ArrayList<AnimationOperations> getActions();

  /**
   * Returns this shape's color with rgb components as ints instead of floats.
   *
   * @return  The color as a String.
   */
  String getColorAsInt();

  /**
   * Resets this shape to its original values.
   */
  void setDefault();

  /**
   * Is this shape currently visible or not?.
   *
   * @return  True if it's visible, false otherwise.
   */
  boolean isVisible();

  /**
   * Changes whether this shape is currently visible or not.
   */
  void setVisible();
}
