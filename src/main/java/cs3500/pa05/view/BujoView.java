package cs3500.pa05.view;

import cs3500.pa05.controller.BujoController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * The view component of the Bullet Journal (Bujo) application, responsible for loading the GUI
 * layout.
 */
public class BujoView {
  FXMLLoader loader;

  /**
   * Constructs a BujoView object with the specified controller.
   *
   * @param controller the controller to associate with the view
   */
  public BujoView(BujoController controller) {
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource("bujoScene.fxml"));
    this.loader.setController(controller);
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
