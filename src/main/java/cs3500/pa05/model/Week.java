package cs3500.pa05.model;

import cs3500.pa05.model.enums.DayOfWeek;
import cs3500.pa05.model.plannerentry.Event;
import cs3500.pa05.model.plannerentry.Task;
import java.util.ArrayList;
import java.util.List;

/**
 * The Day class represents a day of the week and contains a list of tasks and events for that day.
 */
public class Week {
  private final ArrayList<Task> listOfTasks;
  private final ArrayList<Event> listOfEvents;
  private final int maxTask;
  private final int maxEvent;
  private final String weekName;
  private final ArrayList<String> categories;
  private final ArrayList<String> notes;
  private final ArrayList<String> quotes;

  /**
   * Constructs a Week object with the given parameters.
   *
   * @param listOfTasks  the list of tasks for the week
   * @param listOfEvents the list of events for the week
   * @param maxTask      the maximum number of tasks allowed
   * @param maxEvent     the maximum number of events allowed
   * @param weekName     the name of the week
   * @param categories   the list of categories for the week
   * @param notes        the list of notes for the week
   * @param quotes       the list of quotes for the week
   */
  public Week(ArrayList<Task> listOfTasks, ArrayList<Event> listOfEvents, int maxTask, int maxEvent,
              String weekName, ArrayList<String> categories, ArrayList<String> notes,
              ArrayList<String> quotes) {
    this.listOfTasks = listOfTasks;
    this.listOfEvents = listOfEvents;
    this.maxTask = maxTask;
    this.maxEvent = maxEvent;
    this.weekName = weekName;
    this.categories = categories;
    this.notes = notes;
    this.quotes = quotes;
  }

  /**
   * Adds an event entry to the day's list of events.
   *
   * @param event the event to be added
   */
  public void addEventEntry(Event event) {
    this.listOfEvents.add(event);
  }

  /**
   * Adds a task entry to the day's list of tasks.
   *
   * @param task the task to be added
   */
  public void addTaskEntry(Task task) {
    this.listOfTasks.add(task);
  }

  /**
   * Removes an event entry from the day's list of events
   *
   * @param event the event to be removed
   */
  public void removeEventEntry(Event event) {
    this.listOfEvents.remove(event);
  }

  /**
   * Removes a task entry from the day's list of tasks
   *
   * @param task the task to be removed
   */
  public void removeTaskEntry(Task task) {
    this.listOfTasks.remove(task);
  }

  /**
   * Gets this objects list of events
   *
   * @return the list of events field for this object
   */
  public ArrayList<Event> getListOfEvents() {
    return listOfEvents;
  }

  /**
   * Gets this objects list of tasks
   *
   * @return the list of tasks field for this object
   */
  public ArrayList<Task> getListOfTasks() {
    return listOfTasks;
  }

  /**
   * Returns the maximum number of tasks allowed for the week.
   *
   * @return the maximum number of tasks
   */
  public int getMaxTask() {
    return maxTask;
  }

  /**
   * Returns the maximum number of events allowed for the week.
   *
   * @return the maximum number of events
   */
  public int getMaxEvent() {
    return maxEvent;
  }

  /**
   * Returns the name of the week.
   *
   * @return the week name
   */
  public String getWeekName() {
    return weekName;
  }

  /**
   * Returns the list of categories for the week.
   *
   * @return the list of categories
   */
  public ArrayList<String> getCategories() {
    return categories;
  }

  /**
   * Checks if the week contains the specified event.
   *
   * @param event the event to check
   * @return true if the event is found, false otherwise
   */
  public boolean containsEvent(Event event) {
    return this.listOfEvents.contains(event);
  }

  /**
   * Checks if the week contains the specified task.
   *
   * @param task the task to check
   * @return true if the task is found, false otherwise
   */
  public boolean containsTask(Task task) {
    return this.listOfTasks.contains(task);
  }

  /**
   * Adds a note to the list of notes for the week.
   *
   * @param notes the note to add
   */
  public void addNotes(String notes) {
    this.notes.add(notes);
  }

  /**
   * Returns the list of notes for the week.
   *
   * @return the list of notes
   */
  public ArrayList<String> getNotes() {
    return this.notes;
  }

  /**
   * Adds a quote to the list of quotes for the week.
   *
   * @param quotes the quote to add
   */
  public void addQuotes(String quotes) {
    this.quotes.add(quotes);
  }

  /**
   * Returns the list of quotes for the week.
   *
   * @return the list of quotes
   */
  public ArrayList<String> getQuotes() {
    return this.quotes;
  }

  /**
   * Filters and returns a list of events that match the specified category.
   *
   * @param category the category to filter events by
   * @return a list of events matching the specified category
   */
  public List<Event> filterEvent(String category) {
    List<Event> events = new ArrayList<>();
    for (Event event : this.getListOfEvents()) {
      if (event.getCategory().equals(category)) {
        events.add(event);
      }
    }
    return events;
  }

  /**
   * Filters and returns a list of tasks that match the specified category.
   *
   * @param category the category to filter tasks by
   * @return a list of tasks matching the specified category
   */
  public List<Task> filterTask(String category) {
    List<Task> tasks = new ArrayList<>();
    for (Task task : this.getListOfTasks()) {
      if (task.getCategory().equals(category)) {
        tasks.add(task);
      }
    }
    return tasks;
  }

  /**
   * Searches for tasks that match the specified input in the task name, description, or category.
   *
   * @param input the input to search for in tasks
   * @return a list of tasks matching the specified input
   */
  public List<Task> searchTask(String input) {
    List<Task> tasks = new ArrayList<>();
    for (Task task : this.getListOfTasks()) {
      if (task.getName().toLowerCase().contains(input.toLowerCase())) {
        tasks.add(task);
      } else if (task.getDescription().toLowerCase().contains(input.toLowerCase())) {
        tasks.add(task);
      } else if (task.getCategory().toLowerCase()
          .contains(input.toLowerCase())) {
        tasks.add(task);
      }
    }
    return tasks;
  }

  /**
   * Edits the name of a task that matches the specified name and day of the week.
   *
   * @param name      the current name of the task
   * @param dayOfWeek the day of the week the task belongs to
   * @param newName   the new name to set for the task
   */
  public void editTaskName(String name, DayOfWeek dayOfWeek, String newName) {
    for (Task task : this.getListOfTasks()) {
      if (task.getName().equals(name) && task.getDayOfWeek() == dayOfWeek) {
        task.setName(newName);
        return;
      }
    }
  }

  /**
   * Edits the name of an event that matches the specified name and day of the week.
   *
   * @param name      the current name of the event
   * @param dayOfWeek the day of the week the event belongs to
   * @param newName   the new name to set for the event
   */
  public void editEventName(String name, DayOfWeek dayOfWeek, String newName) {
    for (Event event : this.getListOfEvents()) {
      if (event.getName().equals(name) && event.getDayOfWeek() == dayOfWeek) {
        event.setName(newName);
        return;
      }
    }
  }

  /**
   * Edits the description of a task that matches the specified name and day of the week.
   *
   * @param name           the current name of the task
   * @param dayOfWeek      the day of the week the task belongs to
   * @param newDescription the new description to set for the task
   */
  public void editTaskDescription(String name, DayOfWeek dayOfWeek, String newDescription) {
    for (Task task : this.getListOfTasks()) {
      if (task.getName().equals(name) && task.getDayOfWeek() == dayOfWeek) {
        task.setDescription(newDescription);
        return;
      }
    }
  }

  /**
   * Edits the description of an event that matches the specified name and day of the week.
   *
   * @param name           the current name of the event
   * @param dayOfWeek      the day of the week the event belongs to
   * @param newDescription the new description to set for the event
   */
  public void editEventDescription(String name, DayOfWeek dayOfWeek, String newDescription) {
    for (Event event : this.getListOfEvents()) {
      if (event.getName().equals(name) && event.getDayOfWeek() == dayOfWeek) {
        event.setDescription(newDescription);
        return;
      }
    }
  }

  /**
   * Edits the category of a task that matches the specified name and day of the week.
   *
   * @param name      the current name of the task
   * @param dayOfWeek the day of the week the task belongs to
   * @param category  the new category to set for the task
   */
  public void editTaskCategory(String name, DayOfWeek dayOfWeek, String category) {
    for (Task task : this.getListOfTasks()) {
      if (task.getName().equals(name) && task.getDayOfWeek() == dayOfWeek) {
        task.setCategory(category);
        return;
      }
    }
  }

  /**
   * Edits the category of an event that matches the specified name and day of the week.
   *
   * @param name      the current name of the event
   * @param dayOfWeek the day of the week the event belongs to
   * @param category  the new category to set for the event
   */
  public void editEventCategory(String name, DayOfWeek dayOfWeek, String category) {
    for (Event event : this.getListOfEvents()) {
      if (event.getName().equals(name) && event.getDayOfWeek() == dayOfWeek) {
        event.setCategory(category);
        return;
      }
    }
  }
}
