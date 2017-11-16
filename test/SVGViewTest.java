import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import cs3500.animator.model.model.AnimatorModel;
import cs3500.animator.model.model.IAnimatorOperations;
import cs3500.animator.view.AnimationFileReader;
import cs3500.animator.view.SVGAnimationView;

import static org.junit.Assert.assertEquals;

/**
 * Tests to make sure the methods in the SVGAnimationView class work properly.
 */
public class SVGViewTest {
  StringBuffer out;
  StringBuffer out2;

  /**
   * Creates the SVGAnimationViews before testing.
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
    new SVGAnimationView(out, am, 2);

    IAnimatorOperations am2 = new AnimatorModel();
    out2 = new StringBuffer();
    new SVGAnimationView(out2, am2, 2);
  }

  /**
   * Tests that the SVGAnimationView class's display method works properly.
   */
  @Test
  public void testDisplay() {
    assertEquals(
            "<svg width=\"1000\" height=\"1000\" "
            + "version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n\n"
            + "<rect id=\"R\" x=\"200\" y=\"200\" width=\"50\" height=\"100\" "
            + "fill=\"rgb(255,0,0)\" visibility=\"visible\" >\n"
            + "    <animate attributeType=\"xml\" begin=\"5000ms\" dur=\"20000ms\" "
            + "attributeName=\"x\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"5000ms\" dur=\"20000ms\" "
            + "attributeName=\"y\" from=\"200\" to=\"300\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"35000ms\" dur=\"15000ms\" "
            + "attributeName=\"x\" from=\"300\" to=\"200\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"35000ms\" dur=\"15000ms\" "
            + "attributeName=\"y\" from=\"300\" to=\"200\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"25500ms\" dur=\"9500ms\" "
            + "attributeName=\"width\" from=\"50\" to=\"25\" fill=\"freeze\" />\n"
            + "</rect>\n\n"
            + "<ellipse id=\"C\" cx=\"500\" cy=\"100\" rx=\"60\" ry=\"30\" "
            + "fill=\"rgb(0,0,255)\" visibility=\"visible\" >\n"
            + "    <animate attributeType=\"xml\" begin=\"10000ms\" dur=\"25000ms\" "
            + "attributeName=\"cy\" from=\"100\" to=\"400\" fill=\"freeze\" />\n"
            + "    <animate attributeType=\"xml\" begin=\"25000ms\" dur=\"15000ms\" "
            + "attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,255,0)\" "
            + "fill=\"freeze\" />\n"
            + "</ellipse>\n\n"
            + "</svg>",
            out.toString());
  }

  /**
   * Tests that the SVGAnimationView class's display method works properly with an empty animation.
   */
  @Test
  public void testDisplayEmpty() {
    assertEquals(
            "<svg width=\"1000\" height=\"1000\" "
            + "version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n\n"
            + "</svg>",
            out2.toString());
  }
}
