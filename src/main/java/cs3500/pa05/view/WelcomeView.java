package cs3500.pa05.view;

import cs3500.pa05.controller.WelcomeController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * The view for the start-up view of the Bujo program, responsible for loading the GUI layout.
 */
public class WelcomeView {
  FXMLLoader loader;

  /**
   * Constructs a StartView object with the specified controller.
   *
   * @param welcomeController the controller to associate with the view
   */
  public WelcomeView(WelcomeController welcomeController) {
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource("welcome.fxml"));
    this.loader.setController(welcomeController);
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
