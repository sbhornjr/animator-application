package cs3500.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import cs3500.animator.model.shapes.IShape;

/**
 * An ActionListener that responds to the JComboBox in the InteractiveView
 */
public class ComboBoxListener implements ActionListener {
  private InteractiveAnimationController controller;

  /**
   * Constructor for the ComboBoxListener.
   * @param cont  The controller that created this listener.
   */
  public ComboBoxListener(InteractiveAnimationController cont) {
    controller = cont;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    JComboBox<IShape> box = (JComboBox)e.getSource();
    IShape selected = (IShape)box.getSelectedItem();

    controller.shapeToggled(selected);
  }
}
