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
 * The controller for the edit description popup in the Bujo program, responsible for handling
 * description editing functionality.
 */
public class EditDescriptionPopupController extends Apopup {
  private final BujoController bujoController;
  private final WarningPopupController warningPopupController;

  @FXML
  private TextField nameEdit;
  @FXML
  private ChoiceBox<DayOfWeek> dayOfWeekEdit;
  @FXML
  private ChoiceBox<String> typeEdit;
  @FXML
  private TextField newDescription;
  @FXML
  private Button confirm;

  /**
   * Constructs an EditDescriptionPopupController object with the specified stage and
   * BujoController.
   *
   * @param stage          the stage where the popups will appear
   * @param bujoController the BujoController instance for managing event description editing
   */
  public EditDescriptionPopupController(Stage stage, BujoController bujoController) {
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
    typeEdit.getItems().addAll("Event", "Task");
    DayOfWeek[] daysOfWeek = DayOfWeek.values();
    dayOfWeekEdit.getItems().addAll(daysOfWeek);
    hidePopup(popup);
  }

  /**
   * Triggers the actions after the submit button is pressed on the popup
   *
   * @param popup the popup in interest
   */
  @Override
  protected void hidePopup(Popup popup) {
    confirm.setOnAction(e -> {
      hide(popup);
      String name = nameEdit.getText();
      DayOfWeek dayOfWeek = dayOfWeekEdit.getValue();
      String type = typeEdit.getValue();
      String newDescription = this.newDescription.getText();
      bujoController.editEntry(name, dayOfWeek, type, newDescription, 2);
    });
  }
}


