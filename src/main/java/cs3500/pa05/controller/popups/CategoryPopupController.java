package cs3500.pa05.controller.popups;

import cs3500.pa05.controller.BujoController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * The controller for the category popup in the Bujo program, responsible for handling
 * category-related functionality.
 */
public class CategoryPopupController extends Apopup {
  @FXML
  private Button submitCategory;
  @FXML
  private TextField newCategory;
  private BujoController controller;

  /**
   * Constructs a CategoryPopupController object with the specified stage and BujoController.
   *
   * @param stage      the stage where the popups will appear
   * @param controller the BujoController instance for managing categories
   */
  public CategoryPopupController(Stage stage, BujoController controller) {
    super(stage);
    this.controller = controller;
  }

  /**
   * Hides the category popup and performs actions upon submission.
   *
   * @param popup the popup to hide
   */
  @Override
  protected void hidePopup(Popup popup) {
    submitCategory.setOnAction(e -> {
      hide(popup);
      String category = newCategory.getText();
      controller.addCategory(category);
      controller.updateEventTask();
      newCategory.clear();
    });
  }
}
