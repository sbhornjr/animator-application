package cs3500.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * An ActionListener class that deals exclusively with JTextFields.
 */
public class TextFieldListener implements ActionListener {
  private AnimationController controller;

  /**
   * Constructor for the TextFieldListener.
   * @param cont  The controller that created this listener.
   */
  public TextFieldListener(AnimationController cont) {
    controller = cont;
  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }
}
