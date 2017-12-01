package cs3500.animator.model.shapes;

import cs3500.animator.provider.model.AnimationOperations;
import cs3500.animator.provider.model.IDimension;
import cs3500.animator.provider.model.IProviderPosn;
import cs3500.animator.provider.model.ShapeOperations;
import cs3500.animator.provider.view.model.AnimationViewOperations;
import cs3500.animator.provider.view.visitors.ShapeVisitor;

import java.awt.*;
import java.util.ArrayList;

/**
 * A read-only version of the MyRectangle class that cannot be mutated by the view.
 */
public class ReadOnlyRectangle extends ReadOnlyShape {

  /**
   * Constructs a ReadOnlyRectangle.
   *
   * @param sh  The Rectangle to be represented in a read-only way.
   */
  public ReadOnlyRectangle(IShape sh) {
    super(sh);
  }
}
