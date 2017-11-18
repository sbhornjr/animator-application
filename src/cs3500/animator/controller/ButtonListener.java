package cs3500.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * An ActionListener class that deals exclusively with JButtons.
 */
public class ButtonListener implements ActionListener {
  private IInteractiveAnimationController controller;

  /**
   * Constructor for the ButtonListener.
   * @param cont  The controller that created this listener.
   */
  public ButtonListener(IInteractiveAnimationController cont) {
    controller = cont;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (((JButton)e.getSource()).getText()) {
      case "Start":
      case "Restart":
        controller.start();
        break;
      case "Pause":
      case "Play":
        controller.pauseToggled();
        break;
      case "Toggle Looping":
        controller.loopToggled();
        break;
      default:
        // unreachable, only the buttons with the above texts can trigger this listener.
    }
  }
}
