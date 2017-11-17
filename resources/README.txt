DESIGN CHANGES FROM ASSIGNMENT 6:
 - Refactored the view interface to have a more comprehensive set of methods and implemented these
   methods as necessary.
 - Added a ViewType enum that represents each type of view.
    - Added a ViewType field to each view.
 - Re-wrote the visual view so it uses a Timer to run the animation.
 - Created a read-only model that is used by the views.
 - Added fields to the Shape class so shapes can keep track of their initial state and reset to it.