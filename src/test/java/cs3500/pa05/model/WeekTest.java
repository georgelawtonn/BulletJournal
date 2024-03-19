package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa05.model.enums.DayOfWeek;
import cs3500.pa05.model.plannerentry.Event;
import cs3500.pa05.model.plannerentry.Task;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Week tester class
 */
class WeekTest {

  private Week week;
  Event event;
  Task task;
  Event eventT;
  Task taskT;
  Event eventTh;
  Task taskTh;
  Event eventF;
  Task taskF;

  /**
   * setups any weeks, events and task that will be used for testing
   */
  @BeforeEach
  public void setUp() {
    ArrayList<Task> tasks = new ArrayList<>();
    ArrayList<Event> events = new ArrayList<>();
    ArrayList<String> categories = new ArrayList<>();
    ArrayList<String> notes = new ArrayList<>();
    ArrayList<String> quotes = new ArrayList<>();
    week = new Week(tasks, events, 5, 10, "Week 1",
        categories, notes, quotes);
    event = new Event("Event 1", "Description 1", DayOfWeek.MONDAY,
        "Category 1", 60,
        LocalTime.of(1, 2));
    task = new Task("Task 1", "Description 1", DayOfWeek.TUESDAY,
        "Category 2", false);

    eventT = new Event("R", "R", DayOfWeek.MONDAY,
        "R", 60,
        LocalTime.of(1, 2));
    taskT = new Task("R", "R", DayOfWeek.TUESDAY,
        "R", false);

    eventTh = new Event("Event 1", "R", DayOfWeek.WEDNESDAY,
        "R", 60,
        LocalTime.of(1, 2));
    taskTh = new Task("Task 1", "R", DayOfWeek.WEDNESDAY,
        "R", false);

    eventF = new Event("R", "Description 1", DayOfWeek.WEDNESDAY,
        "R", 60,
        LocalTime.of(1, 2));
    taskF = new Task("R", "Description 1", DayOfWeek.WEDNESDAY,
        "R", false);
  }

  /**
   * testing if the week can add a new event
   */
  @Test
  public void testAddEventEntry() {
    assertFalse(week.containsEvent(event));
    week.addEventEntry(event);
    assertTrue(week.containsEvent(event));
    assertTrue(week.getListOfEvents().contains(event));
  }

  /**
   * testing if the week can add a new task
   */
  @Test
  public void testAddTaskEntry() {
    assertFalse(week.containsTask(task));
    week.addTaskEntry(task);
    assertTrue(week.containsTask(task));
    assertTrue(week.getListOfTasks().contains(task));
  }

  /**
   * testing if the week can remove an event
   */
  @Test
  void removeEventEntry() {
    week.addEventEntry(event);
    week.removeEventEntry(event);
    assertFalse(week.containsTask(task));
  }

  /**
   * testing if the week can remove a task
   */
  @Test
  void removeTaskEntry() {
    week.addTaskEntry(task);
    week.removeTaskEntry(task);
    assertFalse(week.containsTask(task));
  }

  /**
   * testing if the week will retrieve the list of events
   */
  @Test
  void getListOfEvents() {
    week.addEventEntry(event);
    assertEquals(week.getListOfEvents(), new ArrayList<Event>(Arrays.asList(event)));
  }

  /**
   * testing if the week will retrieve the list of tasks
   */
  @Test
  void getListOfTasks() {
    week.addTaskEntry(task);
    assertEquals(week.getListOfTasks(), new ArrayList<Task>(Arrays.asList(task)));
  }

  /**
   * testing if the week will retrieve the max number of tasks in the week
   */
  @Test
  void getMaxTask() {
    assertEquals(week.getMaxTask(), 5);
  }

  /**
   * testing if the week will retrieve the max number of events in the week
   */
  @Test
  void getMaxEvent() {
    assertEquals(week.getMaxEvent(), 10);
  }

  /**
   * testing if the week will retrieve the name of the week
   */
  @Test
  void getWeekName() {
    assertEquals(week.getWeekName(), "Week 1");
  }

  /**
   * testing if the week will retrieve the categories of the week
   */
  @Test
  void getCategories() {
    assertEquals(week.getCategories(), new ArrayList<String>());
  }

  /**
   * testing if the week will add a new note
   */
  @Test
  void addNotes() {
    week.addNotes("Note");
    assertEquals(week.getNotes().get(0), "Note");
  }

  /**
   * testing if the week will add a new quote
   */
  @Test
  void addQuotes() {
    week.addQuotes("Quote");
    assertEquals(week.getQuotes().get(0), "Quote");
  }

  /**
   * testing if the week will filter correctly for the events
   */
  @Test
  void filterEvent() {
    week.addEventEntry(event);
    week.addEventEntry(eventT);
    assertEquals(week.filterEvent("Category 1").get(0), event);
  }

  /**
   * testing if the week will filter correctly for the tasks
   */
  @Test
  void filterTask() {
    week.addTaskEntry(task);
    week.addTaskEntry(taskT);
    assertEquals(week.filterTask("Category 2").get(0), task);
  }

  /**
   * testing if the week can search for a task
   */
  @Test
  void searchTask() {
    week.addTaskEntry(task);
    week.addTaskEntry(taskT);
    assertEquals(week.searchTask("Category 2").get(0), task);
    assertEquals(week.searchTask("Task 1").get(0), task);
    assertEquals(week.searchTask("Description 1").get(0), task);
  }

  /**
   * testing if the week can edit an existing task's name
   */
  @Test
  void editTaskName() {
    week.editTaskName("Task 1", DayOfWeek.TUESDAY, "Task");
    assertEquals(week.getListOfTasks(), new ArrayList<>());

    week.addTaskEntry(taskT);
    week.addTaskEntry(taskTh);
    week.addTaskEntry(taskF);
    week.editTaskName("Task 1", DayOfWeek.TUESDAY, "Task");
    assertEquals(week.getListOfTasks().get(0), taskT);

    week.addTaskEntry(task);

    Task taskNew = new Task("Task", "Description 1", DayOfWeek.TUESDAY,
        "Category 2", false);
    week.editTaskName("Task 1", DayOfWeek.TUESDAY, "Task");
    assertEquals(week.getListOfTasks().get(3), taskNew);
  }

  /**
   * testing if the week can edit an existing event's name
   */
  @Test
  void editEventName() {
    week.editEventName("Event 1", DayOfWeek.MONDAY, "Event");
    assertEquals(week.getListOfEvents(), new ArrayList<>());

    week.addEventEntry(eventT);
    week.addEventEntry(eventTh);
    week.addEventEntry(eventF);
    week.editEventName("Event 1", DayOfWeek.MONDAY, "Event");
    assertEquals(week.getListOfEvents().get(0), eventT);

    week.addEventEntry(event);

    Event eventNew = new Event("Event", "Description 1", DayOfWeek.MONDAY,
        "Category 1", 60,
        LocalTime.of(1, 2));
    week.editEventName("Event 1", DayOfWeek.MONDAY, "Event");
    assertEquals(week.getListOfEvents().get(3), eventNew);
  }

  /**
   * testing if the week can edit an existing task's description
   */
  @Test
  void editTaskDescription() {
    week.editTaskDescription("Task 1", DayOfWeek.TUESDAY, "Task");
    assertEquals(week.getListOfTasks(), new ArrayList<>());

    week.addTaskEntry(taskT);
    week.addTaskEntry(taskTh);
    week.addTaskEntry(taskF);
    week.editTaskDescription("Task 1", DayOfWeek.TUESDAY, "Task");
    assertEquals(week.getListOfTasks().get(0), taskT);

    week.addTaskEntry(task);

    Task taskNew = new Task("Task 1", "Task", DayOfWeek.TUESDAY,
        "Category 2", false);
    week.editTaskDescription("Task 1", DayOfWeek.TUESDAY, "Task");
    assertEquals(week.getListOfTasks().get(3), taskNew);
  }

  /**
   * testing if the week can edit an existing event's description
   */
  @Test
  void editEventDescription() {
    week.editEventDescription("Event 1", DayOfWeek.MONDAY, "Event");
    assertEquals(week.getListOfEvents(), new ArrayList<>());

    week.addEventEntry(eventT);
    week.addEventEntry(eventTh);
    week.addEventEntry(eventF);
    week.editEventDescription("Event 1", DayOfWeek.MONDAY, "Event");
    assertEquals(week.getListOfEvents().get(0), eventT);

    week.addEventEntry(event);

    Event eventNew = new Event("Event 1", "Event", DayOfWeek.MONDAY,
        "Category 1", 60,
        LocalTime.of(1, 2));
    week.editEventDescription("Event 1", DayOfWeek.MONDAY, "Event");
    assertEquals(week.getListOfEvents().get(3), eventNew);
  }

  /**
   * testing if the week can edit an existing task's category
   */
  @Test
  void editTaskCategory() {
    week.editTaskCategory("Task 1", DayOfWeek.TUESDAY, "Task");
    assertEquals(week.getListOfTasks(), new ArrayList<>());

    week.addTaskEntry(taskT);
    week.addTaskEntry(taskTh);
    week.addTaskEntry(taskF);
    week.editTaskCategory("Task 1", DayOfWeek.TUESDAY, "Task");
    assertEquals(week.getListOfTasks().get(0), taskT);

    week.addTaskEntry(task);

    Task taskNew = new Task("Task 1", "Description 1", DayOfWeek.TUESDAY,
        "Task", false);
    week.editTaskCategory("Task 1", DayOfWeek.TUESDAY, "Task");
    assertEquals(week.getListOfTasks().get(3), taskNew);
  }

  /**
   * testing if the week can edit an existing event's category
   */
  @Test
  void editEventCategory() {
    week.editEventCategory("Event 1", DayOfWeek.MONDAY, "Event");
    assertEquals(week.getListOfTasks(), new ArrayList<>());

    week.addEventEntry(eventT);
    week.addEventEntry(eventTh);
    week.addEventEntry(eventF);
    week.editEventCategory("Event 1", DayOfWeek.MONDAY, "Event");
    assertEquals(week.getListOfEvents().get(0), eventT);

    week.addEventEntry(event);

    Event eventNew = new Event("Event 1", "Description 1", DayOfWeek.MONDAY,
        "Event", 60,
        LocalTime.of(1, 2));
    week.editEventCategory("Event 1", DayOfWeek.MONDAY, "Event");
    assertEquals(week.getListOfEvents().get(3), eventNew);
  }
}