package cs3500.pa05.controller.popups;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * WarningPopupController controls warning popups,
 * additionally the reason this does not extend APopup is because we are not
 * able to initialize a subclass in it's abstract and thus would not be able to
 * throw errors in certain classes
 */
public class WarningPopupController {
  private final Stage stage;
  private Popup popup;
  @FXML
  private Button cont;
  @FXML
  private Label warning;
  @FXML
  private Label more;
  private final String message;
  private final String moreMessage;

  /**
   * Constructs a WarningPopupController object with the given stage, warning message,
   * and additional message.
   *
   * @param stage       the JavaFX stage for the popup window
   * @param warning     the warning message to display
   * @param moreMessage additional message to display
   */
  public WarningPopupController(Stage stage, String warning, String moreMessage) {
    this.stage = stage;
    this.message = warning;
    this.moreMessage = moreMessage;
  }

  /**
   * shows the popup
   */
  public void showPopup() {
    this.popup.show(this.stage);
  }

  /**
   * handles what happens in the popup
   *
   * @param fxmlFile what fxml to show as the popup
   */
  public void handlePopup(String fxmlFile) {
    this.popup = new Popup();
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(fxmlFile));
    loader.setController(this);
    Scene s = null;
    try {
      s = loader.load();
    } catch (Exception e) {
      // The program should never get to this point unless the resource file itself is not present
      // (If the warning itself is unable to load there are no other ways to warn the user of a
      // failure)
      throw new RuntimeException("Warning Popup Failed To Load, "
          + "verify that warning.fxml is present in resources");
    }
    popup.getContent().add((Node) s.getRoot());
    warning.setText(message);
    more.setText(moreMessage);
    hidePopup(popup);
  }

  /**
   * hides the given popup
   *
   * @param popup the popup to hide
   */
  public void hide(Popup popup) {
    popup.hide();
  }

  /**
   * Triggers the actions after the submit button is pressed on the popup
   *
   * @param popup the popup in interest
   */
  private void hidePopup(Popup popup) {
    cont.setOnAction(e -> hide(popup));
  }
}
