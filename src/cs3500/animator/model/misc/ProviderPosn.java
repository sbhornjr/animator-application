package cs3500.animator.model.misc;

import cs3500.animator.provider.model.IProviderPosn;

public class ProviderPosn implements IProviderPosn {

  private float x;
  private float y;

  public ProviderPosn(float x, float y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public float getX() {
    return x;
  }

  @Override
  public float getY() {
    return y;
  }
}
