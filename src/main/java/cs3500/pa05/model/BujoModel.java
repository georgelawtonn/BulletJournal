package cs3500.pa05.model;

import cs3500.pa05.model.enums.DayOfWeek;
import cs3500.pa05.model.plannerentry.Event;
import cs3500.pa05.model.plannerentry.Task;
import cs3500.pa05.model.records.MultiplePasswordRecord;
import cs3500.pa05.model.records.WeekRecord;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the model for a singular Bujo file.
 */
public class BujoModel {
  private Week currentWeek;
  private final File file;
  private String password;
  private Map<String, String> passwords = new HashMap<>();

  /**
   * Constructs a BujoModel with the specified file, maximum task count, maximum event count, and
   * name of the week.
   *
   * @param file       the file that this BujoModel manages
   * @param maxTask    the maximum number of tasks allowed
   * @param maxEvent   the maximum number of events allowed
   * @param nameOfWeek the name of the week
   */
  public BujoModel(File file, int maxTask, int maxEvent, String nameOfWeek) {
    this.file = file;
    currentWeek =
        new Week(new ArrayList<>(), new ArrayList<>(), maxTask, maxEvent, nameOfWeek,
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    try {
      MultiplePasswordRecord multiplePasswordRecord = Reader.readPassword();
      this.passwords = Format.passwordJsonToMap(multiplePasswordRecord);
    } catch (IOException e) {
      this.passwords = new HashMap<>();
    }
  }

  /**
   * Constructs a BujoModel with the specified file.
   *
   * @param file the file that this BujoModel manages
   */
  public BujoModel(File file) {
    this.file = file;
  }

  /**
   * Reads the Bujo file and returns its data as a List of Day
   *
   * @return The data within the file as a List of Day
   * @throws IOException If parsing the file fails
   */
  public Week load() throws IOException {
    WeekRecord fileContents = Reader.readBujoFile(file);
    currentWeek = Format.weekRecordToWeek(fileContents);
    return currentWeek;
  }

  /**
   * Saves the BujoFile with any modified data
   *
   * @throws IOException If the write of the file fails
   */
  public void save() throws IOException {
    WeekRecord weekRecord = Format.dayToWeekRecord(currentWeek);
    Writer.updateBujoFile(file, weekRecord);
    String fileName = file.getName();
    if (this.password != null) {
      this.passwords.put(fileName, password);
    }
    MultiplePasswordRecord multiplePasswordRecord = Format.passwordMapToJson(this.passwords);
    Writer.savePassword(multiplePasswordRecord);
  }

  /**
   * Returns the list of categories in the current week.
   *
   * @return the list of categories
   */
  public ArrayList<String> getCategories() {
    return currentWeek.getCategories();
  }

  /**
   * Adds a task to the current week.
   *
   * @param task the task to add
   */
  public void addTask(Task task) {
    currentWeek.addTaskEntry(task);
  }

  /**
   * Adds an event to the current week.
   *
   * @param event the event to add
   */
  public void addEvent(Event event) {
    currentWeek.addEventEntry(event);
  }

  /**
   * Checks if the current week contains the specified event.
   *
   * @param event the event to check
   * @return true if the current week contains the event, false otherwise
   */
  public boolean weekContainsEvent(Event event) {
    return currentWeek.containsEvent(event);
  }

  /**
   * Checks if the current week contains the specified task.
   *
   * @param task the task to check
   * @return true if the current week contains the task, false otherwise
   */
  public boolean weekContainsTask(Task task) {
    return currentWeek.containsTask(task);
  }

  /**
   * Adds notes to the current week.
   *
   * @param notes the notes to add
   */
  public void addNotes(String notes) {
    currentWeek.addNotes(notes);
  }

  /**
   * Adds quotes to the current week.
   *
   * @param quotes the quotes to add
   */
  public void addQuotes(String quotes) {
    currentWeek.addQuotes(quotes);
  }

  /**
   * Removes the specified task from the current week.
   *
   * @param task the task to remove
   */
  public void removeTask(Task task) {
    currentWeek.removeTaskEntry(task);
  }

  /**
   * Removes the specified event from the current week.
   *
   * @param event the event to remove
   */
  public void removeEvent(Event event) {
    currentWeek.removeEventEntry(event);
  }

  /**
   * Filters tasks in the current week based on the specified category.
   *
   * @param category the category to filter tasks by
   * @return a list of tasks that match the specified category
   */
  private List<Task> filterTask(String category) {
    return currentWeek.filterTask(category);
  }

  /**
   * Filters events in the current week based on the specified category.
   *
   * @param category the category to filter events by
   * @return a list of events that match the specified category
   */
  private List<Event> filterEvent(String category) {
    return currentWeek.filterEvent(category);
  }

  /**
   * Filters and retrieves a list of tasks and events that match the specified category,
   * and returns them as a formatted string.
   *
   * @param category the category to filter by
   * @return a formatted string containing the titles of the filtered tasks and events
   */
  public String filter(String category) {
    StringBuilder stringBuilder = new StringBuilder();
    List<Task> tasks = filterTask(category);
    for (Task task : tasks) {
      stringBuilder.append(task.makeTaskTitle()).append("\n");
    }
    List<Event> events = filterEvent(category);
    for (Event event : events) {
      stringBuilder.append(event.makeEventTitle()).append("\n");
    }
    return stringBuilder.toString();
  }

  /**
   * Searches for tasks in the current week that contain the specified input.
   *
   * @param input the input to search for in task names and descriptions
   * @return a list of tasks that match the search input
   */
  public List<Task> searchTask(String input) {
    return currentWeek.searchTask(input);
  }

  /**
   * Edits the name of a task with the specified details.
   *
   * @param name      the current name of the task
   * @param dayOfWeek the day of the week for the task
   * @param newName   the new name to set for the task
   */
  private void editTaskName(String name, DayOfWeek dayOfWeek, String newName) {
    currentWeek.editTaskName(name, dayOfWeek, newName);
  }

  /**
   * Edits the name of an event with the specified details.
   *
   * @param name      the current name of the event
   * @param dayOfWeek the day of the week for the event
   * @param newName   the new name to set for the event
   */
  private void editEventName(String name, DayOfWeek dayOfWeek, String newName) {
    currentWeek.editEventName(name, dayOfWeek, newName);
  }

  /**
   * Edits the description of a task with the specified details.
   *
   * @param name           the name of the task
   * @param dayOfWeek      the day of the week for the task
   * @param newDescription the new description to set for the task
   */
  private void editTaskDescription(String name, DayOfWeek dayOfWeek, String newDescription) {
    currentWeek.editTaskDescription(name, dayOfWeek, newDescription);
  }

  /**
   * Edits the description of an event with the specified details.
   *
   * @param name           the name of the event
   * @param dayOfWeek      the day of the week for the event
   * @param newDescription the new description to set for the event
   */
  private void editEventDescription(String name, DayOfWeek dayOfWeek, String newDescription) {
    currentWeek.editEventDescription(name, dayOfWeek, newDescription);
  }

  /**
   * Edits the category of a task with the specified details.
   *
   * @param name        the name of the task
   * @param dayOfWeek   the day of the week for the task
   * @param newCategory the new category to set for the task
   */
  private void editTaskCategory(String name, DayOfWeek dayOfWeek, String newCategory) {
    currentWeek.editTaskCategory(name, dayOfWeek, newCategory);
  }

  /**
   * Edits the category of an event with the specified details.
   *
   * @param name        the name of the event
   * @param dayOfWeek   the day of the week for the event
   * @param newCategory the new category to set for the event
   */
  private void editEventCategory(String name, DayOfWeek dayOfWeek, String newCategory) {
    currentWeek.editEventCategory(name, dayOfWeek, newCategory);
  }

  /**
   * Edits an event with the specified details based on the given choice.
   *
   * @param name      the name of the event
   * @param dayOfWeek the day of the week for the event
   * @param text      the new value to set (name, description, or category)
   * @param choice    the choice indicating which property to edit (1 for name, 2 for description,
   *                  3 for category)
   */
  public void editEvent(String name, DayOfWeek dayOfWeek, String text, int choice) {
    if (choice == 1) {
      this.editEventName(name, dayOfWeek, text);
    } else if (choice == 2) {
      this.editEventDescription(name, dayOfWeek, text);
    } else {
      this.editEventCategory(name, dayOfWeek, text);
    }
  }

  /**
   * Edits a task with the specified details based on the given choice.
   *
   * @param name      the name of the task
   * @param dayOfWeek the day of the week for the task
   * @param text      the new value to set (name, description, or category)
   * @param choice    the choice indicating which property to edit (1 for name, 2 for description,
   *                  3 for category)
   */
  public void editTask(String name, DayOfWeek dayOfWeek, String text, int choice) {
    if (choice == 1) {
      this.editTaskName(name, dayOfWeek, text);
    } else if (choice == 2) {
      this.editTaskDescription(name, dayOfWeek, text);
    } else {
      this.editTaskCategory(name, dayOfWeek, text);
    }
  }

  /**
   * Reads the password data from the password file and updates the passwords map.
   * Prints the multiplePasswordRecord to the standard output.
   */
  public void readPassword() {
    try {
      MultiplePasswordRecord multiplePasswordRecord = Reader.readPassword();
      System.out.print(multiplePasswordRecord);
      this.passwords = Format.passwordJsonToMap(multiplePasswordRecord);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Returns the map of passwords.
   *
   * @return The map of passwords.
   */
  public Map<String, String> getPasswords() {
    return passwords;
  }

  /**
   * Returns the password.
   *
   * @return The password.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the password.
   *
   * @param password The password to set.
   */
  public void setPassword(String password) {
    this.password = password;
  }
}
