package cs3500.animator.view;

import cs3500.animator.controller.ButtonListener;
import cs3500.animator.controller.ComboBoxListener;
import cs3500.animator.controller.TextFieldListener;
import cs3500.animator.model.shapes.IShape;

import java.io.IOException;

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
  public void setButtonListener(ButtonListener bl) {
    try {
      ap.append("setButtonListener called\n");
    } catch (IOException e) {
      System.out.println("ERROR: " + e.getMessage());
    }
  }

  @Override
  public void setTextFieldListener(TextFieldListener tfl) {
    try {
      ap.append("setTextFieldListener called\n");
    } catch (IOException e) {
      System.out.println("ERROR: " + e.getMessage());
    }
  }

  @Override
  public void setComboBoxListener(ComboBoxListener cbl) {
    try {
      ap.append("setComboBoxListener called\n");
    } catch (IOException e) {
      System.out.println("ERROR: " + e.getMessage());
    }
  }

  @Override
  public void addActionListeners() {
    try {
      ap.append("addActionListeners called\n");
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
}
