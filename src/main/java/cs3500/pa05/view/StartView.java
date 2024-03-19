package cs3500.pa05.view;

import cs3500.pa05.controller.StartController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * The view for the start-up view of the Bujo program, responsible for loading the GUI layout.
 */
public class StartView {
  FXMLLoader loader;

  /**
   * Constructs a StartView object with the specified controller.
   *
   * @param startController the controller to associate with the view
   */
  public StartView(StartController startController) {
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource("start.fxml"));
    this.loader.setController(startController);
  }

  /**
   * Loads the scene from a Bujo GUI layout file.
   *
   * @return the loaded scene
   * @throws IllegalStateException if the layout file fails to load
   */
  public Scene load() {
    try {
      return loader.load();
    } catch (IOException exc) {
      throw new IllegalStateException("Unable to load layout.");
    }
  }
}
