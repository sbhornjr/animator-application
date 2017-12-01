package cs3500.animator.view.model;

import cs3500.animator.provider.model.IColorAnimation;
import cs3500.animator.provider.model.IMoveAnimation;
import cs3500.animator.provider.model.IScaleAnimation;
import cs3500.animator.provider.view.model.AnimationViewOperations;
import cs3500.animator.provider.view.visitors.AnimationVisitor;

public class AnimationVisitorImpl implements AnimationVisitor<AnimationViewOperations> {
  @Override
  public AnimationViewOperations visit(IMoveAnimation move) {
    return new MoveView(move);
  }

  @Override
  public AnimationViewOperations visit(IScaleAnimation scale) {
    return new ScaleView(scale);
  }

  @Override
  public AnimationViewOperations visit(IColorAnimation changeColor) {
    return new ColorChangeView(changeColor);
  }
}
