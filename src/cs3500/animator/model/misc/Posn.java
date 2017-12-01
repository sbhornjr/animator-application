package cs3500.animator.model.misc;

import cs3500.animator.provider.model.IProviderPosn;

import java.awt.geom.Point2D;

/**
 * Represents a position (x,y).
 * Can also be used to represent other pairs of numbers such as shape dimensions and time durations.
 */
public class Posn implements IPosn {
  private double x;
  private double y;

  /**
   * Constructs a cs3500.animator.model.misc.Posn.
   *
   * @param x The Posn's x-coordinate (or first number)
   * @param y The Posn's y-coordinate (or second number)
   */
  public Posn(double x, double y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public void move(IPosn changeInLocation) {
    this.x += changeInLocation.getX();
    this.y += changeInLocation.getY();
  }

  @Override
  public double getX() {
    return this.x;
  }

  @Override
  public double getY() {
    return this.y;
  }

  @Override
  public String toString() {
    return "(" + this.x + "," + this.y + ")";
  }

  @Override
  public int getIntX() {
    return (int) this.x;
  }

  @Override
  public int getIntY() {
    return (int) this.y;
  }
}
