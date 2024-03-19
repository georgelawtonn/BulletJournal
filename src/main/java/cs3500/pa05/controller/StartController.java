package cs3500.pa05.controller;

import cs3500.pa05.controller.popups.NewPopupController;
import cs3500.pa05.controller.popups.OpenPopupController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Represents a Start popup that asks the user to choose to make a new Week or load an old Week
 */
public class StartController {
  @FXML
  private Button newBujo;
  @FXML
  private Button loadBujo;
  private final NewPopupController newPopupController;
  private final OpenPopupController openPopupController;
  private final Stage stage;

  /**
   * Constructs a StartPopupController object with the given stage and BujoController.
   *
   * @param stage      the JavaFX stage for the popup window
   * @param controller the BujoController instance for communication
   */
  public StartController(Stage stage, BujoController controller) {
    this.stage = stage;
    this.newPopupController = new NewPopupController(stage, controller);
    this.openPopupController = new OpenPopupController(stage, controller);
  }

  /**
   * Runs the start scene and directs the user to corresponding popups
   */
  public void run() {
    newBujo.setOnAction(e -> newPopupController.showPopup());
    newPopupController.handlePopup("new.fxml");

    loadBujo.setOnAction(e -> openPopupController.showPopup());
    openPopupController.handlePopup("open.fxml");
  }
}
