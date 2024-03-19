package cs3500.pa05.model;

import cs3500.pa05.model.enums.DayOfWeek;
import cs3500.pa05.model.plannerentry.Event;
import cs3500.pa05.model.plannerentry.Task;
import cs3500.pa05.model.records.EventRecord;
import cs3500.pa05.model.records.MultiplePasswordRecord;
import cs3500.pa05.model.records.PasswordRecord;
import cs3500.pa05.model.records.TaskRecord;
import cs3500.pa05.model.records.WeekRecord;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The format class provides the functionality to change records to class objects, and vice versa
 */
public class Format {
  /**
   * Private constructor because static methods only
   */
  private Format() {
  }

  /**
   * Converts a weekRecord to a list of days
   *
   * @param weekRecord The weekRecord to be converted
   * @return The weekRecord's representation as a list of days
   */
  public static Week weekRecordToWeek(WeekRecord weekRecord) {
    ArrayList<Task> tasks = createTasks(weekRecord.tasks());
    ArrayList<Event> events = createEvents(weekRecord.events());
    return new Week(tasks, events, weekRecord.maxTask(), weekRecord.maxEvent(),
        weekRecord.weekName(), weekRecord.categories(), weekRecord.notes(), weekRecord.quotes());
  }

  /**
   * Converts a list of TaskRecord to a List of Task
   *
   * @param taskList The list of TaskRecords to be converted
   * @return The converted List of TaskRecord as a List of Task
   */
  private static ArrayList<Task> createTasks(ArrayList<TaskRecord> taskList) {
    ArrayList<Task> tasks = new ArrayList<>();
    for (TaskRecord taskRecord : taskList) {
      String name = taskRecord.name();
      String description = taskRecord.description();
      DayOfWeek dayOfWeek = taskRecord.dayOfWeek();
      String category = taskRecord.category();
      boolean complete = taskRecord.complete();
      Task task = new Task(name, description, dayOfWeek, category, complete);
      tasks.add(task);
    }
    return tasks;
  }

  /**
   * Converts a list of EventRecord to a List of Event
   *
   * @param eventList The list of EventRecords to be converted
   * @return The converted List of EventRecord as a List of Event
   */
  private static ArrayList<Event> createEvents(ArrayList<EventRecord> eventList) {
    ArrayList<Event> events = new ArrayList<>();
    for (EventRecord eventRecord : eventList) {
      String name = eventRecord.name();
      String description = eventRecord.description();
      DayOfWeek dayOfWeek = eventRecord.dayOfWeek();
      String category = eventRecord.category();
      LocalTime startTime = LocalTime.parse(eventRecord.startTime(),
          DateTimeFormatter.ofPattern("HH:mm"));
      int duration = eventRecord.duration();
      Event event = new Event(name, description, dayOfWeek, category, duration, startTime);
      events.add(event);
    }
    return events;
  }

  /**
   * Converts a List of Day into a WeekRecord
   *
   * @param currentWeek The List of Day to be converte
   * @return The converted data as a WeekRecord
   */
  public static WeekRecord dayToWeekRecord(Week currentWeek) {
    ArrayList<EventRecord> eventRecords = createEventRecord(currentWeek.getListOfEvents());
    ArrayList<TaskRecord> taskRecords = createTaskRecord(currentWeek.getListOfTasks());
    return new WeekRecord(eventRecords, taskRecords, currentWeek.getMaxTask(),
        currentWeek.getMaxEvent(), currentWeek.getWeekName(),
        currentWeek.getCategories(), currentWeek.getNotes(), currentWeek.getQuotes());
  }

  /**
   * Converts a List of Event into a List of EventRecord
   *
   * @param eventList The List of Event to be converted
   * @return The converted data as a List of EventRecord
   */
  private static ArrayList<EventRecord> createEventRecord(ArrayList<Event> eventList) {
    ArrayList<EventRecord> eventRecords = new ArrayList<>();
    for (Event event : eventList) {
      String name = event.getName();
      String description = event.getDescription();
      DayOfWeek dayOfWeek = event.getDayOfWeek();
      String category = event.getCategory();
      LocalTime startTime = event.getTime();
      int duration = event.getDuration();
      EventRecord eventRecord =
          new EventRecord(name, description, dayOfWeek, category, startTime.toString(), duration);
      eventRecords.add(eventRecord);
    }
    return eventRecords;
  }

  /**
   * Converts a List of Task into a List of TaskRecord
   *
   * @param taskList The List of Task to be converted
   * @return The converted data as a List of TaskRecord
   */
  private static ArrayList<TaskRecord> createTaskRecord(ArrayList<Task> taskList) {
    ArrayList<TaskRecord> taskRecords = new ArrayList<>();
    for (Task task : taskList) {
      String name = task.getName();
      String description = task.getDescription();
      DayOfWeek dayOfWeek = task.getDayOfWeek();
      String category = task.getCategory();
      boolean complete = task.getComplete();
      TaskRecord taskRecord = new TaskRecord(name, description, dayOfWeek, category, complete);
      taskRecords.add(taskRecord);
    }
    return taskRecords;
  }

  /**
   * Converts a MultiplePasswordRecord object to a map of passwords.
   *
   * @param multiplePasswordRecord The MultiplePasswordRecord object to be converted.
   * @return The converted data as a map of passwords.
   */
  public static Map<String, String> passwordJsonToMap(
      MultiplePasswordRecord multiplePasswordRecord) {
    Map<String, String> passwords = new HashMap<>();
    for (PasswordRecord passwordRecord : multiplePasswordRecord.passwords()) {
      passwords.put(passwordRecord.fileName(), passwordRecord.password());
    }
    return passwords;
  }

  /**
   * Converts a map of passwords to a MultiplePasswordRecord object.
   *
   * @param passwords The map of passwords to be converted.
   * @return The converted data as a MultiplePasswordRecord object.
   */
  public static MultiplePasswordRecord passwordMapToJson(Map<String, String> passwords) {
    List<PasswordRecord> records = new ArrayList<>();
    for (Map.Entry<String, String> entry : passwords.entrySet()) {
      records.add(new PasswordRecord(entry.getKey(), entry.getValue()));
    }
    return new MultiplePasswordRecord(records);
  }
}
