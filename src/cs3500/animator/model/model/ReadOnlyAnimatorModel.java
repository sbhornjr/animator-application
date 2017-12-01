package cs3500.animator.model.model;

import cs3500.animator.model.actions.ActionType;
import cs3500.animator.model.actions.IAction;
import cs3500.animator.model.misc.IMyColor;
import cs3500.animator.model.misc.IPosn;
import cs3500.animator.model.shapes.IShape;
import cs3500.animator.model.shapes.MyRectangle;
import cs3500.animator.model.shapes.MyOval;
import cs3500.animator.model.shapes.ShapeType;
import cs3500.animator.model.shapes.ReadOnlyRectangle;
import cs3500.animator.model.shapes.ReadOnlyOval;
import cs3500.animator.provider.model.AnimationOperations;
import cs3500.animator.provider.model.ShapeOperations;
import cs3500.animator.view.TweenModelBuilder;

import java.util.ArrayList;

/**
 * A read-only version of the AnimatorModel that cannot be mutated by any view.
 */
public class ReadOnlyAnimatorModel implements IAnimatorOperations {
  private IAnimatorOperations model;
  private ArrayList<IAction> actions;
  private ArrayList<IShape> shapes;

  /**
   * Constructs a ReadOnlyAnimatorModel from a given AnimatorModel.
   *
   * @param model The AnimatorModel
   */
  public ReadOnlyAnimatorModel(IAnimatorOperations model) {
    this.model = model;
    this.actions = new ArrayList<>();
    for (AnimationOperations ao : model.getActions()) {
      actions.add((IAction) ao);
    }
    this.shapes = new ArrayList<>();

    ArrayList<IShape> oldShapes = new ArrayList<>();
    for (ShapeOperations so : model.getShapes()) {
      oldShapes.add((IShape) so);
    }

    for (IShape sh : oldShapes) {
      if (sh instanceof MyRectangle) {
        this.shapes.add(new ReadOnlyRectangle(sh));
      }
      else if (sh instanceof MyOval) {
        this.shapes.add(new ReadOnlyOval(sh));
      }
    }
  }

  @Override
  public void createShape(ShapeType st, String name, IPosn location, IPosn dimensions, int sides,
                          IMyColor color, IPosn lifetime) {
    throw new UnsupportedOperationException("You can't mutate the read-only model.");
  }

  @Override
  public void createAction(ActionType at, String shapeName, Object oldTrait, Object newTrait,
                           IPosn duration) {
    throw new UnsupportedOperationException("You can't mutate the read-only model.");
  }

  @Override
  public void executeAction(int actionIndex, double time) {
    IAction a = actions.get(actionIndex);
    if (time >= a.getDuration().getX() && time <= a.getDuration().getDoubleY()) {
      a.execute(time);
    }
  }

  @Override
  public String getDescription() {
    return model.getDescription();
  }

  @Override
  public ArrayList<ShapeOperations> getShapes() {
    ArrayList<ShapeOperations> al = new ArrayList<>();
    for (IShape s : shapes) {
      al.add(s);
    }
    return al;
  }

  @Override
  public ArrayList<AnimationOperations> getActions() {
    ArrayList<AnimationOperations> al = new ArrayList<>();
    for (IAction a : actions) {
      al.add(a);
    }
    return al;
  }

  @Override
  public double getEndTime() {
    return model.getEndTime();
  }

  @Override
  public TweenModelBuilder<IAnimatorOperations> builder() {
    return model.builder();
  }

  //===================================================================================

  @Override
  public void addAnimation(String shapeName, AnimationOperations anim) {
    throw new UnsupportedOperationException("Can't mutate the read-only model");
  }

  @Override
  public void removeAnimation(String shapeName, AnimationOperations anim) {
    throw new UnsupportedOperationException("Can't mutate the read-only model");
  }

  @Override
  public void addShape(ShapeOperations s) {
    throw new UnsupportedOperationException("Can't mutate the read-only model");
  }

  @Override
  public void addShape(ShapeOperations s, int position) {
    throw new UnsupportedOperationException("Can't mutate the read-only model");
  }

  @Override
  public void removeShape(String name) {
    throw new UnsupportedOperationException("Can't mutate the read-only model");
  }

  @Override
  public ShapeOperations getShape(String name) {
    return model.getShape(name);
  }

  @Override
  public boolean contains(String name) {
    return model.contains(name);
  }

  @Override
  public ArrayList<ShapeOperations> getState() {
    return model.getState();
  }
}
