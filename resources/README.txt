DESIGN CHANGES FROM ASSIGNMENT 5:
 - Added checkDuration method to AnimatorModel that checks if a new
   action conflicts with existing ones in the model before the new action is added.
    - Added a ActionType field in each action class used in the
      implementation of the aforementioned method.
    - Added a getType method in the IAction interface that gets the
      aforementioned field in each subclass.
    - Added a getter for Action classes' duration field.
    - Added a getter for Action classes' shape's name.
 - Organized the model classes into packages to improve organization.
 - Refactored Spencer's getDescription() method in the model to match Steven's because Steven's
   is easier to use in the TextualView.
    - Moved several helper methods and fields in each shape class from Steven's implementation to
      Spencer's.
 - Added setters to the shape classes so that the action classes could execute.
 - Filled in each action class's execute method so that it applies the interpolation formula to
   the traits of the shape and sets them accordingly.
 - Changed the action classes' getDescription() methods so they print each action's original trait
    from a new field fields instead of from its shape field.
 - Added a an argument representing the current time that is passed around to each action class
   so they can calculate the correct factor to change their shape's traits by.
 - Changed MyPolygon class so that it extends Java's
   Polygon class so it can be used by the Graphics.
   - Includes adding getters for the polygon's xpoints and ypoints.
 - Changed Posn class so that it extends Java's 2DPoint.Double class to reduce redundancy.
 - Added actions field to MyPolygon class so that each
   shape can keep track of the actions that affects it.
   - Added addAction method to MyPolygon to add a given action to
     the list. It gets called in ActionType upon creation of an Action.
   - Added getAction method to IShape to retrieve that list of actions.
 - Added and implemented the builder embedded class in the model.
 - Deleted the Square, and Circle classes because they did not fit in our SVG view.
 - Refactored the Polygon class into a Shape abstract class because polygons did not fin in out SVG
   view.
 - Changed the MyColor class so that its fields are floats instead of ints.
   - However, we have methods that return the color as a string with floats or ints.

OUR VIEW DESIGN
 - We have an interface that dictates what every view should implement.
    - Our views only have one method that runs the animation, so the interface for the views has
      one method that handles this.
 - We wrote an AnimationPanel class that draws all of the shapes for the visual view to display.
 - The rest of our design decisions occurred in refactoring the model from the last assignment
   as seen above.