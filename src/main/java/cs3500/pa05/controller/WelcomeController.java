package cs3500.pa05.controller;

import cs3500.pa05.Driver;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents a Start popup that asks the user to choose to make a new Week or load an old Week
 */
public class WelcomeController {
  @FXML
  private VBox welcome;
  private final Stage stage;
  private final StartController startController;

  /**
   * Constructs a StartPopupController object with the given stage and BujoController.
   *
   * @param stage      the JavaFX stage for the popup window
   * @param controller the BujoController instance for communication
   */
  public WelcomeController(Stage stage, BujoController controller) {
    this.stage = stage;
    this.startController = new StartController(stage, controller);
  }

  /**
   * Runs the start scene and directs the user to corresponding popups
   */
  public void run() {
    welcome.setStyle("-fx-background-color: #dea47b;");
    welcome.setOnMouseClicked(e -> Driver.showSecondScene(stage, startController));
  }
}
