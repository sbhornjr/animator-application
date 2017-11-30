import cs3500.animator.controller.IInteractiveAnimationController;
import cs3500.animator.controller.InteractiveAnimationController;
import cs3500.animator.controller.ButtonListener;
import cs3500.animator.controller.TextFieldListener;
import cs3500.animator.controller.ComboBoxListener;
import cs3500.animator.view.IInteractiveView;

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
  IInteractiveAnimationController controller = new InteractiveAnimationController(view);
  ButtonListener bl = new ButtonListener(controller);
  TextFieldListener tfl = new TextFieldListener(controller);
  ComboBoxListener cbl = new ComboBoxListener(controller);

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
    startButton.addActionListener(bl);
    ActionEvent e = new ActionEvent(startButton, 1, "Start");
    bl.actionPerformed(e);
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
    restartButton.addActionListener(bl);
    ActionEvent e = new ActionEvent(restartButton, 1, "Restart");
    bl.actionPerformed(e);
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
    pauseButton.addActionListener(bl);
    ActionEvent e = new ActionEvent(pauseButton, 1, "Pause");
    bl.actionPerformed(e);
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
    playButton.addActionListener(bl);
    ActionEvent e = new ActionEvent(playButton, 1, "Play");
    bl.actionPerformed(e);
    assertEquals("togglePause called\n", ap.toString());
  }

  /**
   * Checks that when the toggle looping button is pushed, it calls the correct view method.
   */
  @Test
  public void testLoop() {
    ap.delete(0, ap.length());
    JButton loopButton = new JButton("Toggle Looping");
    loopButton.addActionListener(bl);
    ActionEvent e = new ActionEvent(loopButton, 1, "Toggle Loop");
    bl.actionPerformed(e);
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
    export.addActionListener(tfl);
    ActionEvent e = new ActionEvent(export, 1, "Export");
    tfl.actionPerformed(e);
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
    speedChanger.addActionListener(tfl);
    speedChanger.setText("10");
    ActionEvent e = new ActionEvent(speedChanger, 1, "Change Speed");
    tfl.actionPerformed(e);
    assertEquals("setSpeed called\n", ap.toString());
  }

  /**
   * Checks that when a shape's visibility is toggled, the correct view method is called.
   */
  @Test
  public void testShapeVisibility() {
    ap.delete(0, ap.length());
    JComboBox shapeRemover = new JComboBox();
    shapeRemover.addActionListener(cbl);
    ActionEvent e = new ActionEvent(shapeRemover, 1, "Remove or Add Shape");
    cbl.actionPerformed(e);
    assertEquals("toggleShape called\n", ap.toString());
  }
}
