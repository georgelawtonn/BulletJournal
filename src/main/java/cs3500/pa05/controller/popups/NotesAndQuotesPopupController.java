package cs3500.pa05.controller.popups;

import cs3500.pa05.controller.BujoController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * The NotesAndQuotesPopupController class controls the notes and quotes popup window.
 */
public class NotesAndQuotesPopupController extends Apopup {
  @FXML
  private Button submitNotes;
  @FXML
  private Button submitQuotes;
  @FXML
  private TextArea text;

  private final BujoController controller;

  /**
   * initializes the popup abstract class
   *
   * @param stage      the stage where the popups will appear
   * @param controller the controller
   */
  public NotesAndQuotesPopupController(Stage stage, BujoController controller) {
    super(stage);
    this.controller = controller;
  }

  @Override
  protected void hidePopup(Popup popup) {
    submitNotes.setOnAction(e -> {
      hide(popup);
      String notes = text.getText();
      controller.addNotes(notes);
      text.clear();
    });

    submitQuotes.setOnAction(e -> {
      hide(popup);
      String quotes = text.getText();
      controller.addQuotes(quotes);
      text.clear();
    });
  }
}
