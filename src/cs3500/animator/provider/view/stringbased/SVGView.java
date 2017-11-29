package cs3500.animator.provider.view.stringbased;

import cs3500.animator.provider.model.ShapeOperations;
import cs3500.animator.provider.view.AbstractView;
import cs3500.animator.provider.view.ShapeViewOperations;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is used to print an animation in an svg format.
 */
public class SVGView extends AbstractView implements TextualViewOperations {
  private int svgWidth;
  private int svgHeight;
  Appendable ap;

  /**
   * The constructor for this SVGView.
   * @param svgWidth - the width we want our svg animation to be.
   * @param svgHeight - the height we want our svg animation to be.
   * @param ap - where we want to output this view;
   */
  public SVGView(int svgWidth, int svgHeight, Appendable ap) {
    this.svgWidth = svgWidth;
    this.svgHeight = svgHeight;
    this.ap = ap;
  }

  /**
   * Used to get the state printed as an svg.
   * @param tempo the rate we want to view our animation at.
   * @param state the state we want to view.
   * @return Returns the state of this view as an svg file.
   */
  public String getInstance(int tempo, ArrayList<ShapeOperations> state) {
    this.tempo = tempo;
    this.initializeState(state);
    String svgOutput = "<svg width=\"" + this.svgHeight + "\" height=\"" + this.svgWidth +
            "\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n";

    for (ShapeViewOperations s : this.state) {
      svgOutput += s.printAsSVG(this.tempo, false) + "\n";
    }
    svgOutput += "</svg>";

    return svgOutput;
  }

  /**
   * Used to play this animation (in this case get the svg output).
   * @param tempo the rate we want to view our animation at.
   * @param state the state we want to view.
   */
  public void playAnimation(int tempo, ArrayList<ShapeOperations> state) {
    this.tempo = tempo;
    this.initializeState(state);
    try {
      ap.append(this.getInstance(tempo, state));
    } catch (IOException e) {
      System.err.println("caught IOException: " + e.getMessage());
    }
  }

}