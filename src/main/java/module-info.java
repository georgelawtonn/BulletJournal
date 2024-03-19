module cs3500.pa05 {
  requires javafx.controls;
  requires javafx.fxml;

  requires org.controlsfx.controls;
  requires com.fasterxml.jackson.databind;

  opens cs3500.pa05 to javafx.fxml;
  exports cs3500.pa05;
  exports cs3500.pa05.model;
  exports cs3500.pa05.controller;
  exports cs3500.pa05.view;
  opens cs3500.pa05.model to javafx.fxml;
  exports cs3500.pa05.model.records;
  opens cs3500.pa05.model.records to javafx.fxml;
  opens cs3500.pa05.controller to javafx.fxml;
  exports cs3500.pa05.model.enums;
  opens cs3500.pa05.model.enums to javafx.fxml;
  exports cs3500.pa05.model.plannerentry;
  opens cs3500.pa05.model.plannerentry to javafx.fxml;
  exports cs3500.pa05.controller.popups;
  opens cs3500.pa05.controller.popups to javafx.fxml;
}