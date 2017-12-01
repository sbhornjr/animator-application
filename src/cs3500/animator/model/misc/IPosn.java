package cs3500.animator.model.misc;

public interface IPosn extends cs3500.animator.provider.model.IPosn {

  /**
   * Changes the coordinates of this Posn based on the factors in the given Posn.
   *
   * @param changeInLocation  The change in x and y that the move will apply
   */
  void move(IPosn changeInLocation);

  /**
   * Returns this Posn's first number.
   *
   * @return  The number
   */
  double getDoubleX();

  /**
   * Returns this Posn's second number.
   *
   * @return  The number
   */
  double getDoubleY();

  /**
   * Returns this Posn's first number as an int.
   *
   * @return  The number
   */
  int getIntX();

  /**
   * Returns this Posn's second number as an int.
   *
   * @return  The number
   */
  int getIntY();
}
