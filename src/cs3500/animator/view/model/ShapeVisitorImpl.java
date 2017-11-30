package cs3500.animator.view.model;

import cs3500.animator.model.shapes.IOval;
import cs3500.animator.model.shapes.IRectangle;
import cs3500.animator.provider.view.ShapeViewOperations;
import cs3500.animator.provider.view.visitors.ShapeVisitor;

public class ShapeVisitorImpl implements ShapeVisitor<ShapeViewOperations> {

  @Override
  public ShapeViewOperations visit(IRectangle rect) {
    return null;
  }

  @Override
  public ShapeViewOperations visit(IOval oval) {
    return null;
  }
}
