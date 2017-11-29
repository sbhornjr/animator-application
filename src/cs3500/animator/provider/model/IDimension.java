package cs3500.animator.provider.model;

/**
 * Represents the functionality of a Dimension Object.
 */
public interface IDimension {

  /**
   * Gets the type of this dimension.
   * @return the type of this dimension.
   */
  String getType();

  /**
   * Gets the value of this dimension.
   * @return the value of this dimension.
   */
  float getValue();
}
