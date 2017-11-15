package cs3500.animator.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * An ActionListener class that deals exclusively with JTextFields.
 */
public class TextFieldListener implements ActionListener {
  private InteractiveAnimationController controller;

  /**
   * Constructor for the TextFieldListener.
   * @param cont  The controller that created this listener.
   */
  public TextFieldListener(InteractiveAnimationController cont) {
    controller = cont;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String text = ((JTextField)e.getSource()).getText();
    double speed = Double.parseDouble(text);
    controller.speedChanged(speed);
  }
}
