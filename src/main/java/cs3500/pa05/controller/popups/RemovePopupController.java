package cs3500.pa05.controller.popups;

import cs3500.pa05.controller.BujoController;
import cs3500.pa05.model.enums.DayOfWeek;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * The RemovePopupController class controls the remove popup window in the bullet journal app.
 */
public class RemovePopupController extends Apopup {
  @FXML
  private TextField nameDelete;
  @FXML
  private ChoiceBox<DayOfWeek> dayOfWeekDelete;
  @FXML
  private ChoiceBox<String> typeDelete;
  @FXML
  private Button delete;

  private final BujoController bujoController;
  private final WarningPopupController warningPopupController;

  /**
   * Constructs a RemovePopupController object with the given stage and BujoController.
   *
   * @param stage          the JavaFX stage for the popup window
   * @param bujoController the BujoController instance for communication
   */
  public RemovePopupController(Stage stage, BujoController bujoController) {
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
    typeDelete.getItems().addAll("Event", "Task");
    DayOfWeek[] daysOfWeek = DayOfWeek.values();
    dayOfWeekDelete.getItems().addAll(daysOfWeek);
    hidePopup(popup);
  }

  /**
   * Triggers the actions after the submit button is pressed on the popup
   *
   * @param popup the popup in interest
   */
  @Override
  protected void hidePopup(Popup popup) {
    delete.setOnAction(e -> {
      hide(popup);
      String text = nameDelete.getText();
      DayOfWeek dayOfWeek = dayOfWeekDelete.getValue();
      String type = typeDelete.getValue();
      bujoController.deleteEntry(text, dayOfWeek, type);
    });
  }
}
