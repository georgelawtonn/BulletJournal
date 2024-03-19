package cs3500.pa05.controller.popups;

import cs3500.pa05.controller.BujoController;
import cs3500.pa05.model.enums.DayOfWeek;
import cs3500.pa05.model.plannerentry.Task;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Represents a Task popup that asks for specifications of a new Task
 */
public class TaskPopupController extends Apopup {
  @FXML
  private TextField nameTask;
  @FXML
  private TextArea descriptionTask;
  @FXML
  private ChoiceBox<DayOfWeek> dayOfWeekTask;
  @FXML
  private ChoiceBox<String> categoryTask;
  @FXML
  private Button submitTask;
  private final WarningPopupController warningPopupController;
  private final WarningPopupController warningLoadPopupController;
  private BujoController bujoController;

  /**
   * Constructs a TaskPopupController object with the given stage and BujoController.
   *
   * @param stage          the JavaFX stage for the popup window
   * @param bujoController the BujoController instance for communication
   */
  public TaskPopupController(Stage stage, BujoController bujoController) {
    super(stage);
    this.bujoController = bujoController;
    this.warningPopupController = new WarningPopupController(stage,
        "Invalid Task Specifications",
        "Name and Day of Week should not be empty,"
            + System.lineSeparator() + "    or exceeded the daily max Events");
    warningPopupController.handlePopup("warning.fxml");

    this.warningLoadPopupController = new WarningPopupController(stage,
        "File Failed To Load",
        "Verify Files Are Present In Resources");
    warningLoadPopupController.handlePopup("warning.fxml");
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
    DayOfWeek[] daysOfWeek = DayOfWeek.values();
    dayOfWeekTask.getItems().addAll(daysOfWeek);
    hidePopup(popup);
  }

  /**
   * Updates the category choices in the task category dropdown menu.
   *
   * @param categories the list of categories to update the dropdown menu with
   */
  public void updateCategory(ArrayList<String> categories) {
    categoryTask.getItems().clear();
    categoryTask.getItems().addAll(categories);
  }

  /**
   * Triggers the actions after the submit button is pressed on the popup
   *
   * @param popup the popup in interest
   */
  @Override
  protected void hidePopup(Popup popup) {
    submitTask.setOnAction(e -> {
      hide(popup);
      try {
        String name = nameTask.getText();
        String description = descriptionTask.getText();
        DayOfWeek dayOfWeek = dayOfWeekTask.getValue();
        String category = categoryTask.getValue();

        if (category == null) {
          category = "";
        }

        if (name.isEmpty()) {
          throw new IllegalArgumentException("Name must be present");
        }

        Task task = new Task(name, description, dayOfWeek, category, false);
        bujoController.addTask(task);

      } catch (Exception exc) {
        warningPopupController.showPopup();
      }
      nameTask.clear();
      descriptionTask.clear();
      dayOfWeekTask.setValue(DayOfWeek.SUNDAY);
    });
  }
}
