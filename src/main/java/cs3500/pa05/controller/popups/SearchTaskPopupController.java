package cs3500.pa05.controller.popups;

import cs3500.pa05.controller.BujoController;
import cs3500.pa05.model.plannerentry.Task;
import java.util.List;
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
 * The SearchTaskPopupController class controls the search task popup window.
 */
public class SearchTaskPopupController extends Apopup {

  private final BujoController bujoController;
  private final WarningPopupController warningPopupController;

  @FXML
  private TextField input;
  @FXML
  private Button search;
  @FXML
  private TextArea result;
  @FXML
  private Button exit;

  /**
   * Constructs a SearchTaskPopupController object with the given stage and BujoController.
   *
   * @param stage          the JavaFX stage for the popup window
   * @param bujoController the BujoController instance for communication
   */
  public SearchTaskPopupController(Stage stage, BujoController bujoController) {
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
    exit.setOnAction(e -> hide(popup));
  }

  /**
   * Triggers the actions after the submit button is pressed on the popup
   *
   * @param popup the popup in interest
   */
  @Override
  protected void hidePopup(Popup popup) {
    search.setOnAction(e -> {
      String input = this.input.getText();
      List<Task> tasks = bujoController.searchTask(input);
      StringBuilder stringBuilder = new StringBuilder();
      for (Task task : tasks) {
        stringBuilder.append(task.makeTaskTitle()).append("\n");
      }
      result.setText(stringBuilder.toString());
    });
  }
}


