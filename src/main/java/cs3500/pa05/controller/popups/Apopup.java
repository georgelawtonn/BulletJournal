package cs3500.pa05.controller.popups;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Abstract class for Popups in the app.
 */
public abstract class Apopup {
  protected Stage stage;
  protected Popup popup;
  protected WarningPopupController warningPopupController;

  /**
   * initializes the popup abstract class
   *
   * @param stage the stage where the popups will appear
   */
  public Apopup(Stage stage) {
    this.stage = stage;
    this.warningPopupController = new WarningPopupController(stage,
        "File Failed To Load",
        "Verify Files Are Present In Resources");
    warningPopupController.handlePopup("warning.fxml");
  }

  /**
   * Shows the popup.
   */
  @FXML
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
      warningPopupController.showPopup();
    }
    popup.getContent().add((Node) s.getRoot());
    hidePopup(popup);
  }

  /**
   * hides the given popup
   *
   * @param popup the popup to hide
   */
  protected void hide(Popup popup) {
    popup.hide();
  }

  /**
   * Triggers the actions after the submit button is pressed on the popup
   *
   * @param popup the popup in interest
   */
  protected abstract void hidePopup(Popup popup);
}
