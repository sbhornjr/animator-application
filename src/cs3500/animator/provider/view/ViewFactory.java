package cs3500.animator.provider.view;

/**
 * Used so the views can be changed from a command line.
 */
public class ViewFactory {

  private  Appendable ap;

  public ViewFactory(Appendable ap) {
    this.ap = ap;
  }

  /**
   * Creates the proper model according to the given String.
   * @param viewType The type of the view to be created.
   * @return return the proper model
   */
  public AnimatorViewOperations getViewModel(String viewType) {
    if (viewType == null) {
      return null;
    }
    if (viewType.equalsIgnoreCase("text")) {
      return new TextualView(ap);

    } else if (viewType.equalsIgnoreCase("visual")) {
      return new VisualView();

    } else if (viewType.equalsIgnoreCase("interactive")) {
      return new HybridView();
    }
    else if (viewType.equalsIgnoreCase("svg")) {
      return new SVGView(800, 800, ap);
    }
    throw new IllegalArgumentException("Illegal model type!");
  }


}
