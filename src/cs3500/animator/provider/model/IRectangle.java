package cs3500.animator.model.shapes;

import cs3500.animator.provider.model.ShapeOperations;

/**
 * This interface is used for a visitor so that the visitor interface doesn't use any concrete
 * classes. It doesn't need any methods since the shape interface already defines all the
 * functionality of this shape!
 */

public interface IRectangle extends ShapeOperations {

}

