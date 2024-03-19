package cs3500.pa05.controller.popups;

import cs3500.pa05.Driver;
import cs3500.pa05.controller.BujoController;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Represents a New popup that asks for the specifics of starting a new Week
 */
public class NewPopupController extends Apopup {
  @FXML
  private TextField nameOfWeek;
  @FXML
  private TextField maxEvent;
  @FXML
  private TextField maxTask;
  @FXML
  private Button submitNewFile;
  @FXML
  private TextField fileDest;
  @FXML
  private Button setPassword;
  private final BujoController controller;
  private final WarningPopupController warningPopupController;
  private final SetPasswordPopupController setPasswordPopupController;

  /**
   * Constructs a NewPopupController object with the given stage and BujoController.
   *
   * @param stage      the JavaFX stage for the popup window
   * @param controller the BujoController instance for communication
   */
  public NewPopupController(Stage stage, BujoController controller) {
    super(stage);
    this.controller = controller;
    this.warningPopupController = new WarningPopupController(stage,
        "Invalid Max Event/Task",
        "Default Max Set To 2");
    warningPopupController.handlePopup("warning.fxml");
    this.setPasswordPopupController = new SetPasswordPopupController(stage, controller);
  }

  /**
   * Triggers the actions after the submit button is pressed on the popup
   *
   * @param popup the popup in interest
   */
  @Override
  protected void hidePopup(Popup popup) {
    submitNewFile.setOnAction(e -> {
      int maxTaskAmount = 2;
      int maxEventAmount = 2;
      try {
        if (!maxTask.getText().isEmpty()) {
          maxTaskAmount = Integer.parseInt(maxTask.getText());
        }
        if (!maxEvent.getText().isEmpty()) {
          maxEventAmount = Integer.parseInt(maxEvent.getText());
        }
        if (maxEvent.getText().isEmpty() || maxTask.getText().isEmpty()) {
          throw new IllegalArgumentException("Invalid Input");
        }
        File file = makeFile();

        String nameWeek = nameOfWeek.getText();
        hide(popup);
        Driver.showThirdScene(stage, controller);
        controller.setupNew(file, maxTaskAmount, maxEventAmount, nameWeek);
      } catch (Exception ex) {
        ex.printStackTrace();
        warningPopupController.showPopup();
      }
    });
    setPasswordPopupController.handlePopup("setPassword.fxml");
    setPassword.setOnAction(e -> {
      setPasswordPopupController.showPopup();
    });
  }

  /**
   * make a file with the given string by the user
   *
   * @return A file to store the week
   */
  private File makeFile() {
    String pathString = fileDest.getText();
    Path path = Path.of(pathString);

    if (!Files.exists(path)) {
      File file = new File(fileDest.getText());
      try {
        file.createNewFile();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      return file;
      //throw new IllegalArgumentException("Not a valid path");
    } else {
      return new File(fileDest.getText());
    }
  }
}
