package cs3500.pa05.controller;

import cs3500.pa05.controller.popups.CategoryPopupController;
import cs3500.pa05.controller.popups.EditCategoryPopupController;
import cs3500.pa05.controller.popups.EditDescriptionPopupController;
import cs3500.pa05.controller.popups.EditNamePopupController;
import cs3500.pa05.controller.popups.EventPopupController;
import cs3500.pa05.controller.popups.FilterByCategoryPopupController;
import cs3500.pa05.controller.popups.NewPopupController;
import cs3500.pa05.controller.popups.NotesAndQuotesPopupController;
import cs3500.pa05.controller.popups.OpenPopupController;
import cs3500.pa05.controller.popups.RemovePopupController;
import cs3500.pa05.controller.popups.SearchTaskPopupController;
import cs3500.pa05.controller.popups.TaskPopupController;
import cs3500.pa05.controller.popups.WarningPopupController;
import cs3500.pa05.model.BujoModel;
import cs3500.pa05.model.Week;
import cs3500.pa05.model.enums.DayOfWeek;
import cs3500.pa05.model.plannerentry.Event;
import cs3500.pa05.model.plannerentry.PlannerEntry;
import cs3500.pa05.model.plannerentry.Task;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

/**
 * Represents the Bullet Journal Week Spread Controller
 */
public class BujoController {
  @FXML
  private MenuItem addEvent;
  @FXML
  private MenuItem addTask;
  @FXML
  private MenuItem addCategory;
  @FXML
  private MenuItem open;
  @FXML
  private MenuItem save;
  @FXML
  private MenuItem newFile;
  @FXML
  private MenuItem addNq;
  @FXML
  private Label nameOfWeek;
  @FXML
  private MenuItem deleteMenu;
  @FXML
  private MenuItem editName;
  @FXML
  private MenuItem editDescription;
  @FXML
  private MenuItem editCategory;
  @FXML
  private MenuItem searchTask;
  @FXML
  private MenuItem filter;

  @FXML
  private ListView<String> sundayEvent;
  @FXML
  private ListView<CheckBox> sundayTask;
  @FXML
  private ListView<String> mondayEvent;
  @FXML
  private ListView<CheckBox> mondayTask;
  @FXML
  private ListView<String> tuesdayEvent;
  @FXML
  private ListView<CheckBox> tuesdayTask;
  @FXML
  private ListView<String> wednesdayEvent;
  @FXML
  private ListView<CheckBox> wednesdayTask;
  @FXML
  private ListView<String> thursdayEvent;
  @FXML
  private ListView<CheckBox> thursdayTask;
  @FXML
  private ListView<String> fridayEvent;
  @FXML
  private ListView<CheckBox> fridayTask;
  @FXML
  private ListView<String> saturdayEvent;
  @FXML
  private ListView<CheckBox> saturdayTask;
  @FXML
  private ListView<String> notesList;
  @FXML
  private ListView<String> quotesList;

  private String password;
  private final Stage stage;
  private BujoModel bujoModel;
  private final EventPopupController eventPopupController;
  private final TaskPopupController taskPopupController;
  private final NewPopupController newPopupController;
  private final OpenPopupController openPopupController;
  private final CategoryPopupController categoryPopupController;
  private final WarningPopupController warningSavePopupController;
  private final WarningPopupController warningLoadPopupController;
  private final WarningPopupController warningMaxPopup;
  private final NotesAndQuotesPopupController notesAndQuotesPopupController;
  private final RemovePopupController removePopupController;
  private final EditNamePopupController editNamePopupController;
  private final EditDescriptionPopupController editDescriptionPopupController;
  private final EditCategoryPopupController editCategoryPopupController;
  private final SearchTaskPopupController searchTaskPopupController;
  private final FilterByCategoryPopupController filterByCategoryPopupController;

  private int maxTask = 0;
  private int maxEvent = 0;
  private ArrayList<String> categories;
  private ArrayList<String> notes;
  private ArrayList<String> quotes;

  private final HashMap<Task, CheckBox> taskHashMap = new HashMap<>();
  private final HashMap<Event, String> eventHashMap = new HashMap<>();

  /**
   * Constructs a BujoController with the specified Stage object.
   *
   * @param stage the Stage object representing the application window
   */
  public BujoController(Stage stage) {
    this.stage = stage;
    this.eventPopupController = new EventPopupController(stage, this);
    this.taskPopupController = new TaskPopupController(stage, this);
    this.newPopupController = new NewPopupController(stage, this);
    this.categoryPopupController = new CategoryPopupController(stage, this);
    this.openPopupController = new OpenPopupController(stage, this);
    this.notesAndQuotesPopupController = new NotesAndQuotesPopupController(stage, this);
    this.removePopupController = new RemovePopupController(stage, this);
    this.warningSavePopupController = new WarningPopupController(stage,
        "Failed To Save",
        "");
    warningSavePopupController.handlePopup("warning.fxml");

    this.warningLoadPopupController = new WarningPopupController(stage,
        "Failed To Load",
        "");
    warningLoadPopupController.handlePopup("warning.fxml");

    this.warningMaxPopup = new WarningPopupController(stage,
        "Exceeds Max Task/Event Count",
        "");
    warningLoadPopupController.handlePopup("warning.fxml");
    this.editNamePopupController = new EditNamePopupController(stage, this);
    this.editDescriptionPopupController = new EditDescriptionPopupController(stage, this);
    this.editCategoryPopupController = new EditCategoryPopupController(stage, this);
    this.searchTaskPopupController = new SearchTaskPopupController(stage, this);
    this.filterByCategoryPopupController = new FilterByCategoryPopupController(stage, this);
  }

  /**
   * Binds keyboard shortcuts to specific actions within the application.
   *
   * @param scene the Scene object representing the application scene
   */
  public void addKeyBinds(Scene scene) {
    KeyCombination.Modifier control = KeyCombination.CONTROL_DOWN;
    KeyCombination addEventShortcut =
        new KeyCodeCombination(KeyCode.E, control);
    Runnable addEventAction = () -> eventPopupController.showPopup();
    scene.getAccelerators().put(addEventShortcut, addEventAction);

    KeyCombination addTaskShortcut = new KeyCodeCombination(KeyCode.T, control);
    Runnable addTaskAction = () -> taskPopupController.showPopup();
    scene.getAccelerators().put(addTaskShortcut, addTaskAction);

    KeyCombination addCategoryShortcut = new KeyCodeCombination(KeyCode.C, control);
    Runnable addCategoryAction = () -> categoryPopupController.showPopup();
    scene.getAccelerators().put(addCategoryShortcut, addCategoryAction);

    KeyCombination nqShortCut = new KeyCodeCombination(KeyCode.Q, control);
    Runnable nqAction = () -> notesAndQuotesPopupController.showPopup();
    scene.getAccelerators().put(nqShortCut, nqAction);

    fileKeyBinds(scene);
  }

  /**
   * Binds keyboard shortcuts to specific actions within the application for file related actions
   *
   * @param scene the Scene object representing the application scene
   */
  private void fileKeyBinds(Scene scene) {
    KeyCombination.Modifier control = KeyCombination.CONTROL_DOWN;
    KeyCombination saveShortcut = new KeyCodeCombination(KeyCode.S, control);
    Runnable saveAction = () -> {
      try {
        bujoModel.save();
      } catch (IOException ex) {
        warningSavePopupController.showPopup();
      }
    };
    scene.getAccelerators().put(saveShortcut, saveAction);

    KeyCombination openShortcut = new KeyCodeCombination(KeyCode.O, control);
    Runnable openAction = () -> {
      openPopupController.handlePopup("open.fxml");
      openPopupController.showPopup();
    };
    scene.getAccelerators().put(openShortcut, openAction);

    KeyCombination newFileShortcut = new KeyCodeCombination(KeyCode.N, control);
    Runnable newFileAction = () -> {
      newPopupController.handlePopup("new.fxml");
      newPopupController.showPopup();
    };
    scene.getAccelerators().put(newFileShortcut, newFileAction);
  }

  /**
   * Runs the interactions on the Week spread
   */
  public void run() {
    addEvent.setOnAction(e -> eventPopupController.showPopup());
    eventPopupController.handlePopup("popupEvent.fxml");

    addTask.setOnAction(e -> taskPopupController.showPopup());
    taskPopupController.handlePopup("popupTask.fxml");

    addCategory.setOnAction(e -> categoryPopupController.showPopup());
    categoryPopupController.handlePopup("addCategory.fxml");

    deleteMenu.setOnAction(e -> removePopupController.showPopup());
    removePopupController.handlePopup("delete.fxml");

    editName.setOnAction(e -> editNamePopupController.showPopup());
    editNamePopupController.handlePopup("editName.fxml");

    editDescription.setOnAction(e -> editDescriptionPopupController.showPopup());
    editDescriptionPopupController.handlePopup("editDescription.fxml");

    editCategory.setOnAction(e -> editCategoryPopupController.showPopup());
    editCategoryPopupController.handlePopup("editCategory.fxml");

    searchTask.setOnAction(e -> searchTaskPopupController.showPopup());
    searchTaskPopupController.handlePopup("searchTask.fxml");

    filter.setOnAction(e -> filterByCategoryPopupController.showPopup());
    filterByCategoryPopupController.handlePopup("filterByCategory.fxml");

    fileRun();
  }

  /**
   * Runs the interactions on the Week spread that are related to files
   */
  private void fileRun() {
    save.setOnAction(e -> {
      try {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("save");
        alert.setContentText("Your file has been successfully saved!");
        alert.show();
        bujoModel.save();
      } catch (IOException ex) {
        warningSavePopupController.showPopup();
      }
    });

    open.setOnAction(e -> {
      openPopupController.handlePopup("open.fxml");
      openPopupController.showPopup();
    });

    newFile.setOnAction(e -> {
      newPopupController.handlePopup("new.fxml");
      newPopupController.showPopup();
    });

    addNq.setOnAction(e -> notesAndQuotesPopupController.showPopup());
    notesAndQuotesPopupController.handlePopup("notesAndQuotes.fxml");
  }

  /**
   * Sets up the Week on the given file
   *
   * @param file the existing bujo file
   */
  public void setupLoad(File file) {
    bujoModel = new BujoModel(file);
    Week week = null;
    try {
      week = bujoModel.load();
      maxTask = week.getMaxTask();
      maxEvent = week.getMaxEvent();
      nameOfWeek.setText(week.getWeekName());
      categories = week.getCategories();
      notes = week.getNotes();
      quotes = week.getQuotes();
    } catch (IOException e) {
      warningLoadPopupController.showPopup();
    }
    ArrayList<Event> events = new ArrayList<>(week.getListOfEvents());
    ArrayList<Task> tasks = new ArrayList<>(week.getListOfTasks());
    for (Event event : events) {
      addEvent(event);
    }
    for (Task task : tasks) {
      addTask(task);
    }
    updateEventTask();
    ArrayList<String> notescp = new ArrayList<>(notes);
    for (String note : notescp) {
      addNotes(note);
    }
    ArrayList<String> quotescp = new ArrayList<>(quotes);
    for (String quote : quotescp) {
      addQuotes(quote);
    }
  }

  /**
   * Making a new Week file based on the given inputs
   *
   * @param file       the file where to store the week data
   * @param maxTask    the user can set everyday
   * @param maxEvent   the user can set everyday
   * @param nameOfWeek the name of the week spread
   */
  public void setupNew(File file, int maxTask, int maxEvent, String nameOfWeek) {
    this.bujoModel = new BujoModel(file, maxTask, maxEvent, nameOfWeek);
    this.bujoModel.setPassword(password);
    this.maxTask = maxTask;
    this.maxEvent = maxEvent;
    this.categories = bujoModel.getCategories();
    this.nameOfWeek.setText(nameOfWeek);
    this.notes = new ArrayList<>();
    this.quotes = new ArrayList<>();
    updateEventTask();
  }

  /**
   * Updates the event and task categories in the popup controllers.
   */
  public void updateEventTask() {
    eventPopupController.updateCategory(this.categories);
    taskPopupController.updateCategory(this.categories);
  }

  /**
   * Checks if the size of the given list is under the specified maximum.
   *
   * @param <T>  the type of elements in the list
   * @param max  the maximum size allowed
   * @param list the list to check
   * @return true if the size of the list is less than the maximum, false otherwise
   */
  private <T> boolean isUnderMax(int max, ObservableList<T> list) {
    return list.size() < max;
  }

  /**
   * Adds an event to the corresponding day's event list.
   *
   * @param event the event to add
   */
  public void addEvent(Event event) {
    DayOfWeek day = event.getDayOfWeek();
    switch (day) {
      case SUNDAY -> {
        ObservableList<String> listS = sundayEvent.getItems();
        addEventToDay(listS, event);
      }
      case MONDAY -> {
        ObservableList<String> listM = mondayEvent.getItems();
        addEventToDay(listM, event);
      }
      case TUESDAY -> {
        ObservableList<String> listT = tuesdayEvent.getItems();
        addEventToDay(listT, event);
      }
      case WEDNESDAY -> {
        ObservableList<String> listW = wednesdayEvent.getItems();
        addEventToDay(listW, event);
      }
      case THURSDAY -> {
        ObservableList<String> listTh = thursdayEvent.getItems();
        addEventToDay(listTh, event);
      }
      case FRIDAY -> {
        ObservableList<String> listF = fridayEvent.getItems();
        addEventToDay(listF, event);
      }
      case SATURDAY -> {
        ObservableList<String> listSa = saturdayEvent.getItems();
        addEventToDay(listSa, event);
      }
      default -> {
      }
      // Handle the case where the day doesn't match any of the above cases
    }
  }

  /**
   * Adds an event to the specified day's event list.
   *
   * @param list  the event list for the day
   * @param event the event to add
   */
  private void addEventToDay(ObservableList<String> list, Event event) {
    String eventTitle = event.makeEventTitle();
    if (isUnderMax(maxEvent, list)) {
      list.add(eventTitle);
      eventHashMap.put(event, eventTitle);
      if (!bujoModel.weekContainsEvent(event)) {

        bujoModel.addEvent(event);
      }
    } else {
      warningMaxPopup.showPopup();
    }
  }

  /**
   * Adds a task to the corresponding day's task list.
   *
   * @param task the task to add
   */
  public void addTask(Task task) {
    DayOfWeek day = task.getDayOfWeek();
    switch (day) {
      case SUNDAY -> {
        ObservableList<CheckBox> listS = sundayTask.getItems();
        addTaskToDay(listS, task);
      }
      case MONDAY -> {
        ObservableList<CheckBox> listM = mondayTask.getItems();
        addTaskToDay(listM, task);
      }
      case TUESDAY -> {
        ObservableList<CheckBox> listT = tuesdayTask.getItems();
        addTaskToDay(listT, task);
      }
      case WEDNESDAY -> {
        ObservableList<CheckBox> listW = wednesdayTask.getItems();
        addTaskToDay(listW, task);
      }
      case THURSDAY -> {
        ObservableList<CheckBox> listTh = thursdayTask.getItems();
        addTaskToDay(listTh, task);
      }
      case FRIDAY -> {
        ObservableList<CheckBox> listF = fridayTask.getItems();
        addTaskToDay(listF, task);
      }
      case SATURDAY -> {
        ObservableList<CheckBox> listSa = saturdayTask.getItems();
        addTaskToDay(listSa, task);
      }
      default -> {
      }
      // Handle the case where the day doesn't match any of the above cases
    }
  }

  /**
   * Adds a task to the specified day's task list.
   *
   * @param list the task list for the day
   * @param task the task to add
   */
  private void addTaskToDay(ObservableList<CheckBox> list, Task task) {
    String taskTitle = task.makeTaskTitle();
    CheckBox taskBox = new CheckBox(taskTitle);
    taskBox.setOnAction(e -> task.setComplete(taskBox.isSelected()));
    if (task.getComplete()) {
      taskBox.setSelected(true);
    }
    if (isUnderMax(maxTask, list)) {
      list.add(taskBox);
      taskHashMap.put(task, taskBox);
      if (!bujoModel.weekContainsTask(task)) {

        bujoModel.addTask(task);
      }
    } else {
      warningMaxPopup.showPopup();
    }
  }

  /**
   * Adds a category to the list of categories.
   *
   * @param category the category to add
   */
  public void addCategory(String category) {
    categories.add(category);
  }

  /**
   * Adds notes to the notes list.
   *
   * @param notes the notes to add
   */
  public void addNotes(String notes) {
    ObservableList<String> list = notesList.getItems();
    list.add(notes);
    if (!this.notes.contains(notes)) {
      bujoModel.addNotes(notes);
    }
  }

  /**
   * Adds quotes to the quotes list.
   *
   * @param quotes the quotes to add
   */
  public void addQuotes(String quotes) {
    ObservableList<String> list = quotesList.getItems();
    list.add(quotes);
    if (!this.quotes.contains(quotes)) {
      bujoModel.addQuotes(quotes);
    }
  }

  /**
   * Removes a task from the specified day's task list.
   *
   * @param taskBox   the task checkbox to remove
   * @param dayOfWeek the day of the week corresponding to the task list
   */
  private void removeTask(CheckBox taskBox, DayOfWeek dayOfWeek) {
    switch (dayOfWeek) {
      case SUNDAY -> {
        ObservableList<CheckBox> listS = sundayTask.getItems();
        listS.remove(taskBox);
      }
      case MONDAY -> {
        ObservableList<CheckBox> listM = mondayTask.getItems();
        listM.remove(taskBox);
      }
      case TUESDAY -> {
        ObservableList<CheckBox> listT = tuesdayTask.getItems();
        listT.remove(taskBox);
      }
      case WEDNESDAY -> {
        ObservableList<CheckBox> listW = wednesdayTask.getItems();
        listW.remove(taskBox);
      }
      case THURSDAY -> {
        ObservableList<CheckBox> listTh = thursdayTask.getItems();
        listTh.remove(taskBox);
      }
      case FRIDAY -> {
        ObservableList<CheckBox> listF = fridayTask.getItems();
        listF.remove(taskBox);
      }
      case SATURDAY -> {
        ObservableList<CheckBox> listSa = saturdayTask.getItems();
        listSa.remove(taskBox);
      }
      default -> {
      }
      // Handle the case where the day doesn't match any of the above cases
    }
  }

  /**
   * Removes an event from the specified day's event list.
   *
   * @param event     the event to remove
   * @param dayOfWeek the day of the week corresponding to the event list
   */
  private void removeEvent(String event, DayOfWeek dayOfWeek) {
    switch (dayOfWeek) {
      case SUNDAY -> {
        ObservableList<String> listS = sundayEvent.getItems();
        listS.remove(event);
      }
      case MONDAY -> {
        ObservableList<String> listM = mondayEvent.getItems();
        listM.remove(event);
      }
      case TUESDAY -> {
        ObservableList<String> listT = tuesdayEvent.getItems();
        listT.remove(event);
      }
      case WEDNESDAY -> {
        ObservableList<String> listW = wednesdayEvent.getItems();
        listW.remove(event);
      }
      case THURSDAY -> {
        ObservableList<String> listTh = thursdayEvent.getItems();
        listTh.remove(event);
      }
      case FRIDAY -> {
        ObservableList<String> listF = fridayEvent.getItems();
        listF.remove(event);
      }
      case SATURDAY -> {
        ObservableList<String> listSa = saturdayEvent.getItems();
        listSa.remove(event);
      }
      default -> {
      }
      // Handle the case where the day doesn't match any of the above cases
    }
  }

  /**
   * Deletes an entry (task or event) from the planner.
   *
   * @param name      the name of the entry
   * @param dayOfWeek the day of the week corresponding to the entry
   * @param type      the type of the entry (either "Task" or "Event")
   */
  public void deleteEntry(String name, DayOfWeek dayOfWeek, String type) {
    if (type.equals("Task")) {
      for (Task task : taskHashMap.keySet()) {
        if (task.getName().equals(name) && task.getDayOfWeek() == dayOfWeek) {
          CheckBox taskBox = taskHashMap.get(task);
          removeTask(taskBox, dayOfWeek);
          bujoModel.removeTask(task);
        }
      }
    } else if (type.equals("Event")) {
      for (Event event : eventHashMap.keySet()) {
        if (event.getName().equals(name) && event.getDayOfWeek() == dayOfWeek) {
          String eventString = eventHashMap.get(event);
          removeEvent(eventString, dayOfWeek);
          bujoModel.removeEvent(event);
        }
      }
    }
  }

  /**
   * Filters the planner entries by the specified category.
   *
   * @param category the category to filter by
   * @return a string representation of the filtered entries
   */
  public String filter(String category) {
    return bujoModel.filter(category);
  }

  /**
   * Searches for tasks that match the given input.
   *
   * @param input the search input
   * @return a list of tasks that match the search input
   */
  public List<Task> searchTask(String input) {
    return this.bujoModel.searchTask(input);
  }

  /**
   * Sets the edit choice for the specified planner entry.
   *
   * @param entry  the planner entry to edit
   * @param choice the choice indicating which aspect of the entry to edit (1: name, 2: description,
   *               3: category)
   * @param text   the new text to set for the chosen aspect
   */
  private void setEditChoice(PlannerEntry entry, int choice, String text) {
    if (choice == 1) {
      entry.setName(text);
    }
    if (choice == 2) {
      entry.setDescription(text);
    }
    if (choice == 3) {
      entry.setCategory(text);
    }
  }

  /**
   * Sets the edit helper for the specified planner entry. Updates the entry's information and the
   * corresponding UI elements.
   *
   * @param entry  the planner entry to edit
   * @param choice the choice indicating which aspect of the entry to edit (1: name, 2: description,
   *               3: category)
   * @param text   the new text to set for the chosen aspect
   * @param events the list of events in the UI
   * @param tasks  the list of tasks in the UI
   */
  private void setEditHelper(PlannerEntry entry, int choice, String text,
                             ObservableList<String> events, ObservableList<CheckBox> tasks) {
    if (entry instanceof Event) {
      int index = events.indexOf(((Event) entry).makeEventTitle());
      setEditChoice(entry, choice, text);
      events.set(index, ((Event) entry).makeEventTitle());
    } else {
      int index = tasks.indexOf(taskHashMap.get(entry));
      setEditChoice(entry, choice, text);
      tasks.get(index).setText(((Task) entry).makeTaskTitle());
    }
  }

  /**
   * Edits an entry (task or event) in the planner.
   *
   * @param name      the name of the entry
   * @param dayOfWeek the day of the week corresponding to the entry
   * @param type      the type of the entry (either "Task" or "Event")
   * @param newText   the new text to set for the chosen aspect of the entry
   * @param choice    the choice indicating which aspect of the entry to edit (1: name,
   *                  2: description, 3: category)
   */
  public void editEntry(String name, DayOfWeek dayOfWeek, String type, String newText,
                        int choice) {
    if (type.equals("Task")) {
      for (Task task : taskHashMap.keySet()) {
        if (task.getName().equals(name) && task.getDayOfWeek() == dayOfWeek) {
          editPlannerEntry(task, dayOfWeek, newText, choice);
          bujoModel.editTask(name, dayOfWeek, newText, choice);
        }
      }
    } else if (type.equals("Event")) {
      for (Event event : eventHashMap.keySet()) {
        if (event.getName().equals(name) && event.getDayOfWeek() == dayOfWeek) {
          editPlannerEntry(event, dayOfWeek, newText, choice);
          bujoModel.editEvent(name, dayOfWeek, newText, choice);
        }
      }
    }
  }

  /**
   * Edits a planner entry by updating its information and the corresponding UI elements.
   *
   * @param entry     the planner entry to edit
   * @param dayOfWeek the day of the week corresponding to the entry
   * @param text      the new text to set for the chosen aspect
   * @param choice    the choice indicating which aspect of the entry to edit (1: name,
   *                  2: description, 3: category)
   */
  private void editPlannerEntry(PlannerEntry entry, DayOfWeek dayOfWeek, String text, int choice) {
    switch (dayOfWeek) {
      case SUNDAY ->
          setEditHelper(entry, choice, text, sundayEvent.getItems(), sundayTask.getItems());
      case MONDAY ->
          setEditHelper(entry, choice, text, mondayEvent.getItems(), mondayTask.getItems());
      case TUESDAY ->
          setEditHelper(entry, choice, text, tuesdayEvent.getItems(), tuesdayTask.getItems());
      case WEDNESDAY ->
          setEditHelper(entry, choice, text, wednesdayEvent.getItems(), wednesdayTask.getItems());
      case THURSDAY ->
          setEditHelper(entry, choice, text, thursdayEvent.getItems(), thursdayTask.getItems());
      case FRIDAY ->
          setEditHelper(entry, choice, text, fridayEvent.getItems(), fridayTask.getItems());
      case SATURDAY ->
          setEditHelper(entry, choice, text, saturdayEvent.getItems(), saturdayTask.getItems());
      default -> {
      }
      // Handle the case where the day doesn't match any of the above cases
    }
  }

  /**
   * set the password
   *
   * @param password the password
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * retrieves the password
   *
   * @return the password in string form
   */
  public String getPassword() {
    return password;
  }


}