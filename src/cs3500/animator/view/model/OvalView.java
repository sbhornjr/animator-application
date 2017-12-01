package cs3500.animator.view.model;

import cs3500.animator.model.misc.MyColor;
import cs3500.animator.provider.model.ShapeOperations;
import cs3500.animator.provider.view.model.AnimationViewOperations;

import java.awt.*;

/**
 * Represents an oval that the view can interact with,
 */
public class OvalView extends ShapeView {

  /**
   * The constructor for the oval view.
   * @param oval  The oval that this oval view represents.
   */
  public OvalView(ShapeOperations oval) {
    super(oval);
  }

  @Override
  public void draw(Graphics g, float currentTime) {
    g.setColor(getColor());
    g.fillOval((int) getPosition().getX(), (int) getPosition().getY(),
            (int) getDimensions().get(0).getValue(), (int) getDimensions().get(1).getValue());
  }

  @Override
  public String printAsSVG(int tempo, boolean loop) {
    MyColor color = (MyColor) getColor();
    String s = "\n";
    s += "<ellipse id=\"" + getName() + "\" cx=\"" + getPosition().getX() + "\" cy=\"" + getPosition().getY()
            + "\" rx=\"" + getDimensions().get(0).getValue() + "\" ry=\"" + getDimensions().get(1).getValue()
            + "\" fill=\"rgb" + color.asInt() + "\" visibility=\"visible\" >\n";
    for (AnimationViewOperations avo : animations) {
      s += avo.printAsSVG(tempo, loop);
    }
    s = s.replace("\"x\"", "\"cx\"");
    s = s.replace("\"y\"", "\"cy\"");
    s = s.replace("\"width\"", "\"rx\"");
    s = s.replace("\"height\"", "\"ry\"");
    s += "</ellipse>\n";
    return s;
  }

  @Override
  public String toViewString(int tempo) {
    MyColor color = (MyColor) getColor();
    String s = "";
    s += "Name: " + getName() + "\n";
    s += "Type: " + "Oval" + "\n";
    s += "Center: (" + getPosition().getX()
            + "," + getPosition().getY() + ")" + ", ";
    s += "X radius: " + getDimensions().get(0).getValue() + ", "
            + "Y radius: " + getDimensions().get(1).getValue() + ", ";
    s += "Color: " + color + "\n";
    s += "Appears at t=" + getAppearTime() / tempo + "s\n";
    s += "Disappears at t=" + getDisappearTime() / tempo + "s\n";
    return s;
  }
}
