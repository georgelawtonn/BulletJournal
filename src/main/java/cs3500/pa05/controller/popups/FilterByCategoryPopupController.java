package cs3500.pa05.controller.popups;

import cs3500.pa05.controller.BujoController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * The FilterByCategoryPopupController class controls the filter by category popup window.
 */
public class FilterByCategoryPopupController extends Apopup {
  private final BujoController bujoController;
  private final WarningPopupController warningPopupController;

  @FXML
  private TextField category;
  @FXML
  private Button confirm;
  @FXML
  private TextArea result;
  @FXML
  private Button quit;

  /**
   * Constructs a FilterByCategoryPopupController object with the given stage and BujoController.
   *
   * @param stage          the JavaFX stage for the popup window
   * @param bujoController the BujoController instance for communication
   */
  public FilterByCategoryPopupController(Stage stage, BujoController bujoController) {
    super(stage);
    this.bujoController = bujoController;
    this.warningPopupController = new WarningPopupController(stage,
        "File Failed To Load",
        "Verify Files Are Present In Resources");
    warningPopupController.handlePopup("warning.fxml");
  }

  /**
   * handles what happens in the popup
   *
   * @param fxmlFile what fxml to show as the popup
   */
  @Override
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
    quit.setOnAction(e -> hide(popup));
  }

  /**
   * Triggers the actions after the submit button is pressed on the popup
   *
   * @param popup the popup in interest
   */
  @Override
  protected void hidePopup(Popup popup) {
    confirm.setOnAction(e -> {
      String category = this.category.getText();
      result.setText(bujoController.filter(category));
    });
  }
}


