package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.enums.DayOfWeek;
import cs3500.pa05.model.plannerentry.Event;
import cs3500.pa05.model.plannerentry.Task;
import cs3500.pa05.model.records.EventRecord;
import cs3500.pa05.model.records.TaskRecord;
import cs3500.pa05.model.records.WeekRecord;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tester class for formats
 */
class FormatTest {

  private WeekRecord weekRecord;
  private Week week;

  /**
   * sets up the necessary objects for testing
   */
  @BeforeEach
  public void setUp() {
    // Create sample data for WeekRecord
    ArrayList<TaskRecord> taskRecords = new ArrayList<>();
    TaskRecord taskRecord =
        new TaskRecord("Task 1", "Description 1", DayOfWeek.MONDAY, "Category 1", false);
    taskRecords.add(taskRecord);
    ArrayList<EventRecord> eventRecords = new ArrayList<>();
    EventRecord eventRecord =
        new EventRecord("Event 1", "Description 1", DayOfWeek.TUESDAY, "Category 2",
            "09:00", 60);
    eventRecords.add(eventRecord);
    weekRecord = new WeekRecord(eventRecords, taskRecords, 5, 10,
        "Week 1", new ArrayList<>(), new ArrayList<String>(), new ArrayList<String>());

    // Create sample data for Day
    ArrayList<Task> tasks = new ArrayList<>();
    Task task = new Task("Task 1", "Description 1", DayOfWeek.MONDAY, "Category 1", false);
    tasks.add(task);
    ArrayList<Event> events = new ArrayList<>();
    Event event = new Event("Event 1", "Description 1", DayOfWeek.TUESDAY, "Category 2", 60,
        LocalTime.parse("09:00", DateTimeFormatter.ofPattern("HH:mm")));
    events.add(event);
    week = new Week(tasks, events, 5, 10, "Week 1", new ArrayList<>(), new ArrayList<String>(),
        new ArrayList<String>());
  }

  /**
   * tests if the method can convert a week record to a week object
   */
  @Test
  public void testWeekRecordToWeekConversion() {
    // Perform the conversion
    Week convertedWeek = Format.weekRecordToWeek(weekRecord);

    // Assert the converted data matches the expected values
    assertEquals(week.getListOfTasks(), convertedWeek.getListOfTasks());
    assertEquals(week.getListOfEvents(), convertedWeek.getListOfEvents());
    assertEquals(week.getMaxTask(), convertedWeek.getMaxTask());
    assertEquals(week.getMaxEvent(), convertedWeek.getMaxEvent());
    assertEquals(week.getWeekName(), convertedWeek.getWeekName());
    assertEquals(week.getCategories(), convertedWeek.getCategories());
    assertEquals(week.getNotes(), convertedWeek.getNotes());
    assertEquals(week.getQuotes(), convertedWeek.getQuotes());
  }

  /**
   * tests if the method can convert a day object to a week record
   */
  @Test
  public void testDayToWeekRecordConversion() {
    // Perform the conversion
    WeekRecord convertedWeekRecord = Format.dayToWeekRecord(week);

    // Assert the converted data matches the expected values
    assertEquals(weekRecord.tasks(), convertedWeekRecord.tasks());
    assertEquals(weekRecord.events(), convertedWeekRecord.events());
    assertEquals(weekRecord.maxTask(), convertedWeekRecord.maxTask());
    assertEquals(weekRecord.maxEvent(), convertedWeekRecord.maxEvent());
    assertEquals(weekRecord.weekName(), convertedWeekRecord.weekName());
    assertEquals(weekRecord.categories(), convertedWeekRecord.categories());
    assertEquals(weekRecord.notes(), convertedWeekRecord.notes());
    assertEquals(weekRecord.quotes(), convertedWeekRecord.quotes());
  }

  /**
   * tests if the method can convert a week record to a week object
   */
  @Test
  void testWeekRecordToWeek() {
    ArrayList<TaskRecord> taskRecords = new ArrayList<>();
    TaskRecord taskRecord1 =
        new TaskRecord("Task 1", "Task 1 description", DayOfWeek.MONDAY, "Category 1", true);
    TaskRecord taskRecord2 =
        new TaskRecord("Task 2", "Task 2 description", DayOfWeek.TUESDAY, "Category 2", false);
    taskRecords.add(taskRecord1);
    taskRecords.add(taskRecord2);

    ArrayList<EventRecord> eventRecords = new ArrayList<>();
    EventRecord eventRecord1 =
        new EventRecord("Event 1", "Event 1 description", DayOfWeek.MONDAY, "Category 1",
            "10:00", 60);
    EventRecord eventRecord2 =
        new EventRecord("Event 2", "Event 2 description", DayOfWeek.TUESDAY, "Category 2",
            "02:30", 90);
    eventRecords.add(eventRecord1);
    eventRecords.add(eventRecord2);

    WeekRecord weekRecord = new WeekRecord(eventRecords, taskRecords, 5, 10,
        "Week 1", new ArrayList<>(), new ArrayList<String>(), new ArrayList<String>());

    Week week = Format.weekRecordToWeek(weekRecord);

    assertEquals(2, week.getListOfTasks().size());
    assertEquals(2, week.getListOfEvents().size());
    assertEquals(5, week.getMaxTask());
    assertEquals(10, week.getMaxEvent());
    assertEquals("Week 1", week.getWeekName());
    assertEquals(0, week.getCategories().size());
    assertEquals(new ArrayList<>(), week.getNotes());
    assertEquals(new ArrayList<>(), week.getQuotes());
  }

  /**
   * tests if the method can convert a day object to a week record
   */
  @Test
  void testDayToWeekRecord() {
    ArrayList<Task> tasks = new ArrayList<>();
    Task task1 = new Task("Task 1", "Task 1 description", DayOfWeek.MONDAY,
        "Category 1", true);
    Task task2 = new Task("Task 2", "Task 2 description", DayOfWeek.TUESDAY,
        "Category 2", false);
    tasks.add(task1);
    tasks.add(task2);

    ArrayList<Event> events = new ArrayList<>();
    Event event1 = new Event("Event 1", "Event 1 description", DayOfWeek.MONDAY, "Category 1",
        60, LocalTime.of(10, 0));
    Event event2 = new Event("Event 2", "Event 2 description", DayOfWeek.TUESDAY, "Category 2",
        90, LocalTime.of(14, 30));
    events.add(event1);
    events.add(event2);

    Week currentWeek = new Week(tasks, events, 5, 10,
        "Week 1", new ArrayList<>(), new ArrayList<String>(), new ArrayList<String>());

    WeekRecord weekRecord = Format.dayToWeekRecord(currentWeek);

    assertEquals(2, weekRecord.tasks().size());
    assertEquals(2, weekRecord.events().size());
    assertEquals(5, weekRecord.maxTask());
    assertEquals(10, weekRecord.maxEvent());
    assertEquals("Week 1", weekRecord.weekName());
    assertEquals(0, weekRecord.categories().size());
    assertEquals(new ArrayList<>(), weekRecord.notes());
    assertEquals(new ArrayList<>(), weekRecord.quotes());
  }

}