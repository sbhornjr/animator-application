import org.junit.Before;
import org.junit.Test;

import cs3500.animator.model.actions.ActionType;
import cs3500.animator.model.model.AnimatorModel;
import cs3500.animator.model.misc.MyColor;
import cs3500.animator.model.misc.Posn;
import cs3500.animator.model.shapes.ShapeType;

import static org.junit.Assert.assertEquals;

/**
 * Tests that ensure the methods in an Animator Model work correctly.
 */
public class AnimatorModelTest {
  AnimatorModel am;
  AnimatorModel mt;
  AnimatorModel am2;

  /**
   * Initializes the example model's shapes and actions.
   */
  @Before
  public void init() {
    am = new AnimatorModel();
    mt = new AnimatorModel();
    am2 = new AnimatorModel();

    am.createShape(ShapeType.RECTANGLE,"R", new Posn(200, 200),
            new Posn(50, 100), 4, new MyColor(1,0,0),
            new Posn(1, 100));
    am.createShape(ShapeType.OVAL, "C", new Posn(500, 100), new Posn(60, 30),
            1, new MyColor(0,0,1), new Posn(6, 100));
    am.createAction(ActionType.MOVE, "R", new Posn(200, 200),
            new Posn(300, 300), new Posn(10, 50));
    am.createAction(ActionType.MOVE, "C", new Posn(500, 100),
            new Posn(500, 400), new Posn(20, 70));
    am.createAction(ActionType.COLOR_CHANGE, "C", new MyColor(0,0,1),
            new MyColor(0,1,0), new Posn(50, 80));
    am.createAction(ActionType.SCALE, "R", new Posn(50, 100),
            new Posn(25, 100), new Posn(51, 70));
    am.createAction(ActionType.MOVE, "R", new Posn(300, 300),
            new Posn(200, 200), new Posn(70, 100));
  }

  /**
   * Tests that the model correctly creates shapes and actions and is able to describe them.
   */
  @Test
  public void testGetDescription() {
    assertEquals(
            "Shapes:\n" +
                    "Name: R\n" +
                    "Type: rectangle\n" +
                    "Lower-left corner: (200,200), Width: 50, Height: 100, Color: (1.0,0.0,0.0)\n" +
                    "Appears at t=1.0\n" +
                    "Disappears at t=100.0\n" +
                    "\n" +
                    "Name: C\n" +
                    "Type: oval\n" +
                    "Center: (500,100), X radius: 60, Y radius: 30, Color: (0.0,0.0,1.0)\n" +
                    "Appears at t=6.0\n" +
                    "Disappears at t=100.0\n" +
                    "\n" +
                    "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10.0 to t=50.0\n" +
                    "Shape C moves from (500.0,100.0) to (500.0,400.0) from t=20.0 to t=70.0\n" +
                    "Shape C changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) from t=50.0 to" +
                    " t=80.0\n" +
                    "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0" +
                    " from t=51.0 to t=70.0\n" +
                    "Shape R moves from (300.0,300.0) to (200.0,200.0) from t=70.0 to t=100.0",
            am.getDescription());
  }

  /**
   * Tests that calling getDescription on an empty animation is handled correctly.
   */
  @Test
  public void testEmptyAnimation() {
    assertEquals("Shapes:\n", mt.getDescription());
  }

  /**
   * Tests that the model correctly throws an exception if an action is created with a shape name
   * that doesn't exist.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testGetShape() {
    am.createAction(ActionType.SCALE, "S", new Posn(10, 20),
            new Posn(20, 40), new Posn(10, 20));
  }

  /**
   * Tests that the model correctly throws an exception if an action is created that conflicts
   * with an existing one.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testCheckDuration() {
    am.createAction(ActionType.MOVE, "R", new Posn(200, 200),
            new Posn(20, 40), new Posn(20, 40));
  }
}
