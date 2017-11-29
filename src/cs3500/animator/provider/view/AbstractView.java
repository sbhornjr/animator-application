package cs3500.animator.provider.view;

import cs3500.animator.provider.model.AnimationOperations;
import cs3500.animator.provider.model.ShapeOperations;
import cs3500.animator.provider.view.visitors.ViewAnimationVisitor;
import cs3500.animator.provider.view.visitors.ViewShapeVisitor;

import java.util.ArrayList;

public abstract class AbstractView implements AnimatorViewOperations {

  /**
   * int tempo - Represents the rate this view is playing an animation at.
   * state - Represents the animation as an array list.
   */
  protected int tempo;
  protected ArrayList<ShapeViewOperations> state;

  /**
   * Used to initalize the given state of ShapeOperations into a state of ShapeViewOperations that
   * we can view.
   * @param modelState - the state we are given to view, presumably from the model.
   */
  protected void initializeState(ArrayList<ShapeOperations> modelState) {
    this.state = new ArrayList<>();
    ViewShapeVisitor sv = new ViewShapeVisitor();
    for (ShapeOperations s : modelState) {
      this.state.add(s.accept(sv));
    }

    ViewAnimationVisitor av = new ViewAnimationVisitor();
    ArrayList<AnimationViewOperations> listAnims = new ArrayList<>();

    for (int i = 0; i < modelState.size() ; i++) {
      for (AnimationOperations a : modelState.get(i).getAnimations()) {
        this.state.get(i).addAnimation(a.accept(av));
      }
    }

  }

}
