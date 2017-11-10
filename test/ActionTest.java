import org.junit.Test;

import cs3500.animator.model.actions.ColorChange;
import cs3500.animator.model.actions.Move;
import cs3500.animator.model.misc.MyColor;
import cs3500.animator.model.misc.Posn;
import cs3500.animator.model.actions.Scale;
import cs3500.animator.model.shapes.MyRectangle;
import cs3500.animator.model.shapes.Shape;

import static org.junit.Assert.assertEquals;

/**
 * Tests that methods in the Action classes work correctly.
 */
public class ActionTest {
  Shape c = new MyRectangle("c", new Posn(200, 100), new Posn(50, 100),
          new MyColor(1,0,0), new Posn(30, 60));
  Move m = new Move(c, new Posn(c.getX(), c.getY()), new Posn(100, 200),
          new Posn(40, 50));
  ColorChange cc = new ColorChange(c, c.getColor(), new MyColor(1,1,0),
          new Posn(30, 40));
  Scale s = new Scale(c, new Posn(c.getWidth(), c.getHeight()), new Posn(100, 100),
          new Posn(50, 60));

  /**
   * Tests that the execute method in the Move class works properly.
   */
  @Test
  public void testMoveExecute() {
    m.execute(45);
    assertEquals(true, c.getX() == 150 && c.getY() == 150);
  }

  /**
   * Tests that the execute method in the ColorChange class works properly.
   */
  @Test
  public void testColorChangeExecute() {
    cc.execute(35);
    assertEquals("(1.0,0.5,0.0)", c.getColor().toString());
  }

  /**
   * Tests that the toString method in the Scale class works properly.
   */
  @Test
  public void testScaleToString() {
    s.execute(55);
    assertEquals(true, c.getWidth() == 75 && c.getHeight() == 100);
  }
}
