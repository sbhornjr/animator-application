package cs3500.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import cs3500.animator.model.shapes.IShape;

/**
 * An ActionListener that responds to the JComboBox in the InteractiveView
 */
public class ComboBoxListener implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent e) {
    IShape selected = (IShape) ((JComboBox) e.getSource()).getSelectedItem();
  }
}
