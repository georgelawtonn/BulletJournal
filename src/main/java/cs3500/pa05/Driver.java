package cs3500.pa05;

import cs3500.pa05.controller.BujoController;
import cs3500.pa05.controller.StartController;
import cs3500.pa05.controller.WelcomeController;
import cs3500.pa05.view.BujoView;
import cs3500.pa05.view.StartView;
import cs3500.pa05.view.WelcomeView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * The Driver class represents the entry point for a bullet journal application, responsible for
 * starting the GUI and displaying different scenes.
 */
public class Driver extends Application {

  /**
   * Starts the GUI for a bullet journal app
   *
   * @param stage the JavaFX stage to add elements to
   */
  @Override
  public void start(Stage stage) {
    BujoController controller = new BujoController(stage);
    WelcomeController welcomeController = new WelcomeController(stage, controller);
    WelcomeView welcomeView = new WelcomeView(welcomeController);

    stage.setTitle("Plants Bujo");
    Scene welcomeScene = welcomeView.load();
    welcomeController.run();
    stage.setScene(welcomeScene);
    stage.show();
  }

  /**
   * Displays the second scene of the bullet journal app.
   *
   * @param stage      the JavaFX stage to display the scene on
   * @param controller the StartController instance associated with the scene
   */
  public static void showSecondScene(Stage stage, StartController controller) {
    StartView view = new StartView(controller);
    Scene secondScene = view.load();
    stage.setScene(secondScene);
    controller.run();
  }

  /**
   * Displays the second scene of the bullet journal app.
   *
   * @param stage      the JavaFX stage to display the scene on
   * @param controller the BujoController instance associated with the scene
   */
  public static void showThirdScene(Stage stage, BujoController controller) {
    BujoView view = new BujoView(controller);
    Scene thirdScene = view.load();
    controller.addKeyBinds(thirdScene);
    stage.setScene(thirdScene);
    controller.run();
  }

  /**
   * Entry point for a bullet journal app
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch();
  }

}