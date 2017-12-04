package cs3500.animator.view;

import cs3500.animator.model.shapes.IShape;
import cs3500.animator.provider.model.ShapeOperations;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A mock interactive view used for testing purposes.
 */
public class TestInteractiveView implements IInteractiveView, IAnimationView {
  private ViewType type;
  private Appendable ap;

  /**
   * Constructs a TestInteractiveView.
   *
   * @param ap  Where the view will write its output
   */
  public TestInteractiveView(Appendable ap) {
    this.type = ViewType.INTERACTIVE;
    this.ap = ap;
  }

  @Override
  public ViewType getViewType() {
    return type;
  }

  @Override
  public void run() {
    try {
      ap.append("run called\n");
    } catch (IOException e) {
      System.out.println("ERROR: " + e.getMessage());
    }
  }

  @Override
  public void display() {
    try {
      ap.append("display called\n");
    } catch (IOException e) {
      System.out.println("ERROR: " + e.getMessage());
    }
  }

  @Override
  public void togglePause() {
    try {
      ap.append("togglePause called\n");
    } catch (IOException e) {
      System.out.println("ERROR: " + e.getMessage());
    }
  }

  @Override
  public void toggleLoop() {
    try {
      ap.append("toggleLoop called\n");
    } catch (IOException e) {
      System.out.println("ERROR: " + e.getMessage());
    }
  }

  @Override
  public void setSpeed(int newSpeed) {
    try {
      ap.append("setSpeed called\n");
    } catch (IOException e) {
      System.out.println("ERROR: " + e.getMessage());
    }
  }

  @Override
  public void export(String ofile) {
    try {
      ap.append("export called\n");
    } catch (IOException e) {
      System.out.println("ERROR: " + e.getMessage());
    }
  }

  @Override
  public void toggleShape(IShape s) {
    try {
      ap.append("toggleShape called\n");
    } catch (IOException e) {
      System.out.println("ERROR: " + e.getMessage());
    }
  }

  @Override
  public int getTempo() {
    return 0;
  }

  @Override
  public void loop() {
    // not used
  }

  @Override
  public void pause() {
    // not used
  }

  @Override
  public void play() {
    // not used
  }

  @Override
  public void setTempo(int tempo) {
    // not used
  }

  @Override
  public void reset() {
    // not used
  }

  @Override
  public void setActionListener(ActionListener controller) {
    // not used
  }

  @Override
  public String getSVG(int tempo, ArrayList<ShapeOperations> state, int svgHeight, int svgWidth) {
    // not used
    return null;
  }

  @Override
  public void playAnimation(int tempo, ArrayList<ShapeOperations> state) {
    // not used
  }
}
