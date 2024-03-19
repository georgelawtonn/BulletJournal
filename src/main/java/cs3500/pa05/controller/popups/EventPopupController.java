package cs3500.pa05.controller.popups;

import cs3500.pa05.controller.BujoController;
import cs3500.pa05.model.enums.DayOfWeek;
import cs3500.pa05.model.plannerentry.Event;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Represents an Event popup controller that asks for specifics for adding a new Event
 */
public class EventPopupController extends Apopup {
  private final BujoController bujoController;
  private final WarningPopupController warningPopupController;
  private final WarningPopupController warningLoadPopupController;

  @FXML
  private TextField nameEvent;
  @FXML
  private TextArea descriptionEvent;
  @FXML
  private ChoiceBox<DayOfWeek> dayOfWeekEvent;
  @FXML
  private ChoiceBox<String> categoryEvent;
  @FXML
  private TextField startTimeEvent;
  @FXML
  private Slider durationEvent;
  @FXML
  private Button submitEvent;

  /**
   * Constructs an EventPopupController object with the given stage and BujoController.
   *
   * @param stage          the JavaFX stage for the popup window
   * @param bujoController the BujoController instance for communication
   */
  public EventPopupController(Stage stage, BujoController bujoController) {
    super(stage);
    this.bujoController = bujoController;
    this.warningPopupController = new WarningPopupController(stage,
        "Invalid Event Specifications",
        "Time should be 'XX:XX', Duration spans multiple days, "
            + System.lineSeparator() + "Name is not present, or exceeded the daily max Events");
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
    dayOfWeekEvent.getItems().addAll(daysOfWeek);
    hidePopup(popup);
  }

  /**
   * Updates the category choices in the event category dropdown menu.
   *
   * @param categories the list of categories to update the dropdown menu with
   */
  public void updateCategory(ArrayList<String> categories) {
    categoryEvent.getItems().clear();
    categoryEvent.getItems().addAll(categories);
  }

  /**
   * Triggers the actions after the submit button is pressed on the popup
   *
   * @param popup the popup in interest
   */
  @Override
  protected void hidePopup(Popup popup) {
    submitEvent.setOnAction(e -> {
      hide(popup);
      try {
        String category = categoryEvent.getValue();
        if (category == null) {
          category = "";
        }

        String name = nameEvent.getText();
        if (name.isEmpty()) {
          throw new IllegalArgumentException("Name must be present");
        }

        double duration = durationEvent.getValue();
        String startTime = startTimeEvent.getText();
        if (duration + Integer.parseInt(startTimeEvent.getText(0, 2)) >= 24) {
          throw new IllegalArgumentException("Duration extends over days");
        }

        String description = descriptionEvent.getText();
        DayOfWeek dayOfWeek = dayOfWeekEvent.getValue();
        Event event =
            new Event(name, description, dayOfWeek, category, (int) durationEvent.getValue(),
                LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HH:mm")));
        bujoController.addEvent(event);
      } catch (Exception exc) {
        warningPopupController.showPopup();
      }
      cleanWeek();
    });
  }

  /**
   * clear all the data in the week
   */
  private void cleanWeek() {
    nameEvent.clear();
    descriptionEvent.clear();
    dayOfWeekEvent.setValue(DayOfWeek.SUNDAY);
    startTimeEvent.clear();
    durationEvent.setValue(0);
  }

}
