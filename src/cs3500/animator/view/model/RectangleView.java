package cs3500.animator.view.model;

import cs3500.animator.model.misc.MyColor;
import cs3500.animator.provider.model.ShapeOperations;
import cs3500.animator.provider.view.model.AnimationViewOperations;

import java.awt.*;

/**
 * Represents a rectangle that the view can interact with.
 */
public class RectangleView extends ShapeView {

  /**
   * Constructor for the rectangle view.
   * @param rect the rectangle that this rectangle view represents.
   */
  public RectangleView(ShapeOperations rect) {
    super(rect);
  }

  @Override
  public void draw(Graphics g, float currentTime) {
    g.setColor(getColor());
    g.fillRect((int) getPosition().getX(), (int) getPosition().getY(),
            (int) getDimensions().get(0).getValue(), (int) getDimensions().get(1).getValue());
  }

  @Override
  public String printAsSVG(int tempo, boolean loop) {
    MyColor color = (MyColor) getColor();
    String s = "\n";
    s += "<rect id=\"" + getName() + "\" x=\"" + getPosition().getX() + "\" y=\"" + getPosition().getY()
            + "\" width=\"" + getDimensions().get(0).getValue() + "\" height=\"" + getDimensions().get(1).getValue()
            + "\" fill=\"rgb" + color.asInt() + "\" visibility=\"visible\" >\n";
    for (AnimationViewOperations avo : animations) {
      s += avo.printAsSVG(tempo, loop);
    }
    s += "</rect>\n";
    return s;
  }

  @Override
  public String toViewString(int tempo) {
    MyColor color = (MyColor) getColor();
    String s = "";
    s += "Name: " + getName() + "\n";
    s += "Type: " + "Rectangle" + "\n";
    s += "Lower-left corner: (" + getPosition().getX()
            + "," + getPosition().getY() + "), ";
    s += "Width: " + getDimensions().get(0).getValue() + ", "
            + "Height: " + getDimensions().get(1).getValue() + ", ";
    s += "Color: " + color + "\n";
    s += "Appears at t=" + getAppearTime() / tempo + "s\n";
    s += "Disappears at t=" + getDisappearTime() / tempo + "s\n";
    return s;
  }
}
