package cs3500.pa05.controller.popups;

import cs3500.pa05.Driver;
import cs3500.pa05.controller.BujoController;
import cs3500.pa05.model.Format;
import cs3500.pa05.model.Reader;
import cs3500.pa05.model.records.MultiplePasswordRecord;
import java.io.File;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Represents an Open popup that asks for the path to load an existing bujo Week
 */
public class OpenPopupController extends Apopup {
  @FXML
  private Button submitFile;
  @FXML
  private TextField path;
  @FXML
  private PasswordField password;
  private final BujoController controller;

  /**
   * Constructs an OpenPopupController object with the given stage and BujoController.
   *
   * @param stage      the JavaFX stage for the popup window
   * @param controller the BujoController instance for communication
   */
  public OpenPopupController(Stage stage, BujoController controller) {
    super(stage);
    this.controller = controller;
  }

  /**
   * Triggers the actions after the submit button is pressed on the popup
   *
   * @param popup the popup in interest
   */
  @Override
  protected void hidePopup(Popup popup) {
    submitFile.setOnAction(e -> {
      try {
        hide(popup);
        File file = new File(path.getText());
        MultiplePasswordRecord multiplePasswordRecord = Reader.readPassword();
        Map<String, String> passwords = Format.passwordJsonToMap(multiplePasswordRecord);
        String password = this.password.getText();
        boolean isValid = true;
        if (passwords.containsKey(file.getName())) {
          if (!passwords.get(file.getName()).equals(password)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Incorrect password");
            alert.show();
            isValid = false;
          }
        }
        if (isValid) {
          Driver.showThirdScene(stage, controller);
          controller.setupLoad(file);
        }
      } catch (Exception ex) {
        warningPopupController.showPopup();
      }
    });
  }
}
