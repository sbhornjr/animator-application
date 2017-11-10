import org.junit.Test;

import cs3500.animator.model.misc.MyColor;
import cs3500.animator.model.shapes.MyOval;
import cs3500.animator.model.misc.Posn;
import cs3500.animator.model.shapes.MyRectangle;
import cs3500.animator.model.shapes.ShapeType;

import static org.junit.Assert.assertEquals;

/**
 * Tests the various shape classes and their methods.
 */
public class ShapeTest {
  MyRectangle r = new MyRectangle("r", new Posn(200, 200), new Posn(50, 100),
          new MyColor(1, 0, 0), new Posn(1, 100));
  MyOval c = new MyOval("c", new Posn(500, 100), new Posn(60, 30),
          new MyColor(0, 0, 1), new Posn(6, 100));

  /**
   * Tests that the getType method works correctly.
   */
  @Test
  public void testGetType() {
    assertEquals(c.getType(), ShapeType.OVAL);
  }

  /**
   * Tests the Rectangle class's getPosLocation method.
   */
  @Test
  public void testGetPosLocationRect() {
    assertEquals("Lower-left corner", r.getPosLocation());
  }

  /**
   * Tests the Oval class's getPosLocation method.
   */
  @Test
  public void testGetPosLocationOval() {
    assertEquals("Center", c.getPosLocation());
  }

  /**
   * Tests the Rectangle class's getWLTypes method.
   */
  @Test
  public void testGetWLTypesRect() {
    String[] expected = {"Width", "Height"};
    String[] actual = r.getWLTypes();
    assertEquals(true,
            expected[0].equals(actual[0]) && expected[1].equals(actual[1]));
  }

  /**
   * Tests the Oval class's getWLTypes method.
   */
  @Test
  public void testGetWLTypesOval() {
    String[] expected = {"X radius", "Y radius"};
    String[] actual = c.getWLTypes();
    assertEquals(true,
            expected[0].equals(actual[0]) && expected[1].equals(actual[1]));
  }

  /**
   * Tests the Shape class's getColorAsInt method.
   */
  @Test
  public void testGetColorAsIntRect() {
    assertEquals("(255,0,0)", r.getColorAsInt());
  }
}
