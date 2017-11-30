package cs3500.animator.model.misc;

import cs3500.animator.provider.model.IDimension;

public class Dimension implements IDimension {

  private String type;
  private float value;

  public Dimension(String type, float value) {
    this.type = type;
    this.value = value;
  }

  @Override
  public String getType() {
    return type;
  }

  @Override
  public float getValue() {
    return value;
  }
}
