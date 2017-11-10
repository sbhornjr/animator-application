import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import cs3500.animator.model.model.AnimatorModel;
import cs3500.animator.model.model.IAnimatorOperations;
import cs3500.animator.view.AnimationFileReader;
import cs3500.animator.view.TextualView;

import static org.junit.Assert.assertEquals;

/**
 * Tests to make sure that the methods in the TextualView class work properly.
 */
public class TextualViewTest {
  StringBuffer out;
  StringBuffer out2;

  /**
   * Creates the TextualViews before testing.
   */
  @Before
  public void init() {
    IAnimatorOperations am = new AnimatorModel();
    out = new StringBuffer();
    AnimationFileReader afr = new AnimationFileReader();
    try {
      am = afr.readFile("resources/smalldemo.txt", am.builder());
    } catch (FileNotFoundException e) {
      out.append(e.getMessage());
    }
    new TextualView(out, am, 2);

    IAnimatorOperations am2 = new AnimatorModel();
    out2 = new StringBuffer();
    new TextualView(out2, am2, 2);
  }

  /**
   * Tests that the TextualView's display method outputs the correct description.
   */
  @Test
  public void testDisplay() {
    assertEquals(
            "Shapes:\n" +
                    "Name: R\n" +
                    "Type: rectangle\n" +
                    "Lower-left corner: (200.0,200.0), Width: 50.0, Height: 100.0, " +
                    "Color: (1.0,0.0,0.0)\n" +
                    "Appears at t=0.5s\n" +
                    "Disappears at t=50.0s\n" +
                    "\n" +
                    "Name: C\n" +
                    "Type: oval\n" +
                    "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: " +
                    "(0.0,0.0,1.0)\n" +
                    "Appears at t=3.0s\n" +
                    "Disappears at t=50.0s\n" +
                    "\n" +
                    "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=5.0s to t=25.0s\n" +
                    "Shape C moves from (500.0,100.0) to (500.0,400.0) from t=10.0s to t=35.0s\n" +
                    "Shape C changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) from t=25.0s to " +
                    "t=40.0s\n" +
                    "Shape R moves from (300.0,300.0) to (200.0,200.0) from t=35.0s to t=50.0s\n" +
                    "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, " +
                    "Height: 100.0 from t=25.5s to t=35.0s",
            out.toString());
  }

  /**
   * Tests that the TextualView's display method outputs the correct description for an empty model.
   */
  @Test
  public void testDisplayEmpty() {
    assertEquals(
            "Shapes:\n",
            out2.toString());
  }
}
