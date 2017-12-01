import cs3500.animator.controller.IInteractiveAnimationController;
import cs3500.animator.controller.InteractiveAnimationController;
import cs3500.animator.model.model.AnimatorModel;
import cs3500.animator.model.model.IAnimatorOperations;
import cs3500.animator.view.IInteractiveView;

import cs3500.animator.view.TestInteractiveView;
import org.junit.Test;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.event.ActionEvent;

import static org.junit.Assert.assertEquals;

/**
 * Tests that the methods in an Interactive Animation Controller are called when they are supposed
 * to and work as they should.
 */
public class ControllerTest {
  StringBuffer ap = new StringBuffer();
  IInteractiveView view = new TestInteractiveView(ap);
  IAnimatorOperations model = new AnimatorModel();
  InteractiveAnimationController controller = new InteractiveAnimationController(model, view, 5);

  /**
   * Checks that all the methods called in the controller's
   * contructor are successfully called and in the correct order.
   */
  @Test
  public void testControllerConstructor() {
    assertEquals("setButtonListener called\nsetTextFieldListener called\n"
            + "setComboBoxListener called\naddActionListeners called\n",
            ap.toString());
  }

  /**
   * Checks that when the start button is pushed, it calls the correct view method.
   */
  @Test
  public void testStart() {
    ap.delete(0, ap.length());
    JButton startButton = new JButton("Start");
    startButton.addActionListener(controller);
    ActionEvent e = new ActionEvent(startButton, 1, "Start");
    controller.actionPerformed(e);
    assertEquals("run called\n", ap.toString());
  }

  /**
   * Checks that after the start button turns into the restart
   * button and it is pushed, it calls the correct view method.
   */
  @Test
  public void testRestart() {
    ap.delete(0, ap.length());
    JButton restartButton = new JButton("Restart");
    restartButton.addActionListener(controller);
    ActionEvent e = new ActionEvent(restartButton, 1, "Restart");
    controller.actionPerformed(e);
    assertEquals("run called\n", ap.toString());
  }

  /**
   * Checks that when the pause button is a "pause" button
   * and it is pushed, it calls the correct view method.
   */
  @Test
  public void testPause() {
    ap.delete(0, ap.length());
    JButton pauseButton = new JButton("Pause");
    pauseButton.addActionListener(controller);
    ActionEvent e = new ActionEvent(pauseButton, 1, "Pause");
    controller.actionPerformed(e);
    assertEquals("togglePause called\n", ap.toString());
  }

  /**
   * Checks that when the pause button is a "play" button
   * and it is pushed, it calls the correct view method.
   */
  @Test
  public void testPlay() {
    ap.delete(0, ap.length());
    JButton playButton = new JButton("Play");
    playButton.addActionListener(controller);
    ActionEvent e = new ActionEvent(playButton, 1, "Play");
    controller.actionPerformed(e);
    assertEquals("togglePause called\n", ap.toString());
  }

  /**
   * Checks that when the toggle looping button is pushed, it calls the correct view method.
   */
  @Test
  public void testLoop() {
    ap.delete(0, ap.length());
    JButton loopButton = new JButton("Toggle Looping");
    loopButton.addActionListener(controller);
    ActionEvent e = new ActionEvent(loopButton, 1, "Toggle Loop");
    controller.actionPerformed(e);
    assertEquals("toggleLoop called\n", ap.toString());
  }

  /**
   * Checks that when the export field has a file name
   * written to it, the correct view method is called.
   */
  @Test
  public void testExport() {
    ap.delete(0, ap.length());
    JTextField export = new JTextField("Enter SVG file name", 11);
    export.setToolTipText("Enter a filename here to export this animation.");
    export.addActionListener(controller);
    ActionEvent e = new ActionEvent(export, 1, "Export");
    controller.actionPerformed(e);
    assertEquals("export called\n", ap.toString());
  }

  /**
   * Checks that when the speed change field has a new
   * speed written to it, the correct view method is called.
   */
  @Test
  public void testSpeedChange() {
    ap.delete(0, ap.length());
    JTextField speedChanger = new JTextField("Enter new speed", 9);
    speedChanger.setToolTipText("Change the speed of the animation by entering a new speed value "
            + "here.");
    speedChanger.addActionListener(controller);
    speedChanger.setText("10");
    ActionEvent e = new ActionEvent(speedChanger, 1, "Change Speed");
    controller.actionPerformed(e);
    assertEquals("setSpeed called\n", ap.toString());
  }

  /**
   * Checks that when a shape's visibility is toggled, the correct view method is called.
   */
  @Test
  public void testShapeVisibility() {
    ap.delete(0, ap.length());
    JComboBox shapeRemover = new JComboBox();
    shapeRemover.addActionListener(controller);
    ActionEvent e = new ActionEvent(shapeRemover, 1, "Remove or Add Shape");
    controller.actionPerformed(e);
    assertEquals("toggleShape called\n", ap.toString());
  }
}
