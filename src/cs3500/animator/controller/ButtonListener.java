package cs3500.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * An ActionListener class that deals exclusively with JButtons.
 */
public class ButtonListener implements ActionListener {
  private AnimationController controller;

  /**
   * Constructor for the ButtonListener.
   * @param cont  The controller that created this listener.
   */
  public ButtonListener(AnimationController cont) {
    controller = cont;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch(((JButton)e.getSource()).getText()) {
      case "Start":
        controller.start();
        break;
      case "Pause":
        controller.pauseToggled();
        break;
      case "Restart":
        controller.restart();
        break;
      case "Toggle Looping":
        controller.loopToggled();
        break;
      case "Export as SVG":
        controller.export();
        break;
    }
  }
}
