package cs3500.animator;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JOptionPane;

import cs3500.animator.model.model.AnimatorModel;
import cs3500.animator.model.model.IAnimatorOperations;
import cs3500.animator.view.AnimationFileReader;
import cs3500.animator.view.IAnimationView;
import cs3500.animator.view.SVGAnimationView;
import cs3500.animator.view.TextualView;
import cs3500.animator.view.VisualAnimationView;

/**
 * The class that facilitates running an animation using input from the command line.
 */
public final class EasyAnimator {

  /**
   * Runs an animation using arguments passed on the command line.
   *
   * @param args  The arguments
   */
  public static void main(String[] args) {
    String inputFile = "";
    String viewType = "";
    int speed = 1;
    String errorMsg = "";

    for (int i = 0; i < args.length; i += 2) {
      String identifier = args[i];
      String param = args[i + 1];

      switch (identifier) {
        case "-if":
          inputFile = "resources/" + param;
          break;
        case "-iv":
          viewType = param;
          break;
        case "-speed":
          speed = Integer.parseInt(param);
          break;
        default:
      }
      showError(errorMsg);
    }

    AnimationFileReader afr = new AnimationFileReader();
    IAnimatorOperations model = new AnimatorModel();
    try {
      model = afr.readFile(inputFile, model.builder());
    } catch (FileNotFoundException e) {
      errorMsg = e.getMessage();
    }
    showError(errorMsg);

    String output;
    if (Arrays.asList(args).contains("-o")) {
      int i;
      for (i = 0; i < args.length - 1; i++) {
        if (args[i].equals("-o")) {
          break;
        }
      }
      output = args[i + 1];
    }
    else {
      output = "out";
    }

    if (output.equals("out")) {
      viewFactory(viewType, System.out, model, speed);
    }
    else {
      try {
        FileWriter writer = new FileWriter("resources/" + output);
        viewFactory(viewType, writer, model, speed);
        writer.close();
      } catch (IOException e) {
        errorMsg = e.getMessage();
      }
      showError(errorMsg);
    }
  }

  /**
   * Shows a pop-up error message.
   *
   * @param errorMsg  The message to be displayed
   */
  private static void showError(String errorMsg) {
    if (!errorMsg.equals("")) {
      JFrame error = new JFrame("Error");
      error.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      JOptionPane.showMessageDialog(error, errorMsg);
    }
  }

  private static IAnimationView viewFactory(String type, Appendable out, IAnimatorOperations model,
                                     int speed) {
    String errorMsg;
    switch (type) {
      case "text":
        return new TextualView(out, model, speed);
      case "visual":
        return new VisualAnimationView(model, speed);
      case "svg":
        return new SVGAnimationView(out, model, speed);
      default:
        errorMsg = "Invalid view type provided";
    }
    showError(errorMsg);
    return null;
  }
}
