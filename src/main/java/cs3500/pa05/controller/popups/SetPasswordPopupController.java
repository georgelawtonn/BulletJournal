package cs3500.pa05.controller.popups;

import cs3500.pa05.controller.BujoController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Represents a New popup that asks for the specifics of starting a new Week
 */
public class SetPasswordPopupController extends Apopup {
  @FXML
  private PasswordField enter;
  @FXML
  private PasswordField reEnter;
  @FXML
  private Button confirm;
  @FXML
  private Button exit;
  private final BujoController controller;
  private final WarningPopupController warningPopupController;

  /**
   * Constructs a NewPopupController object with the given stage and BujoController.
   *
   * @param stage      the JavaFX stage for the popup window
   * @param controller the BujoController instance for communication
   */
  public SetPasswordPopupController(Stage stage, BujoController controller) {
    super(stage);
    this.controller = controller;
    this.warningPopupController = new WarningPopupController(stage,
        "Invalid password.",
        "Password length should be between 6 and 12.\n"
            + "Passwords entered should be identical.");
    warningPopupController.handlePopup("warning.fxml");
  }

  /**
   * Triggers the actions after the submit button is pressed on the popup
   *
   * @param popup the popup in interest
   */
  @Override
  protected void hidePopup(Popup popup) {
    confirm.setOnAction(e -> {
          String password = enter.getText();
          String passwordRe = enter.getText();
          if (password.length() < 6 || !password.equals(passwordRe) || password.length() > 12) {
            warningPopupController.showPopup();
            return;
          }
          hide(popup);
          this.controller.setPassword(password);
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("information");
          alert.setContentText("Your password has been set successfully!");
          alert.show();
        }
    );
    exit.setOnAction(e -> {
      hide(popup);
    });
  }
}
