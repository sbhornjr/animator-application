package cs3500.animator.controller;

import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * An ActionListener class that deals exclusively with JTextFields.
 */
public class TextFieldListener implements ActionListener {
  private IInteractiveAnimationController controller;

  /**
   * Constructor for the TextFieldListener.
   * @param cont  The controller that created this listener.
   */
  public TextFieldListener(IInteractiveAnimationController cont) {
    controller = cont;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    JTextField jtx = (JTextField)e.getSource();
    String text = jtx.getToolTipText();

    if (text.equals("Change the speed of the animation by entering a new speed value here.")) {
      String s = jtx.getText();
      int speed = Integer.parseInt(s);
      controller.speedChanged(speed);
    }
    else {
      controller.export(jtx.getText() + ".svg");
    }
  }
}
