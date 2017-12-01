package cs3500.animator.view.model;

import cs3500.animator.model.shapes.IOval;
import cs3500.animator.model.shapes.IRectangle;
import cs3500.animator.provider.view.model.ShapeViewOperations;
import cs3500.animator.provider.view.visitors.ShapeVisitor;

public class ShapeVisitorImpl implements ShapeVisitor<ShapeViewOperations> {
  @Override
  public ShapeViewOperations visit(IRectangle rect) {
    return new RectangleView(rect);
  }

  @Override
  public ShapeViewOperations visit(IOval oval) {
    return new OvalView(oval);
  }
}
