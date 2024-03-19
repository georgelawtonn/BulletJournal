package cs3500.pa05.model.plannerentry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa05.model.enums.DayOfWeek;
import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for Task
 */
class TaskTest {
  private Task task;

  /**
   * Setup for testing
   */
  @BeforeEach
  public void setUp() {
    // Create a task with initial details
    task = new Task("Task 1", "Description", DayOfWeek.MONDAY, "Category", true);
  }

  /**
   * Tests for getComplete
   */
  @Test
  public void testGetComplete() {
    assertTrue(task.getComplete());

    task.setComplete(false);
    assertFalse(task.getComplete());

    task.setComplete(true);
    assertTrue(task.getComplete());
  }

  /**
   * Tests for makeTaskTitle
   */
  @Test
  public void testMakeTaskTitle() {
    String expectedTitle = "Task Name: Task 1" + System.lineSeparator()
        + "Desc: Description" + System.lineSeparator()
        + "Category: Category" + System.lineSeparator();
    assertEquals(expectedTitle, task.makeTaskTitle());

    Task task2 = new Task("Task 2", "", DayOfWeek.MONDAY, "", false);
    String expectedTitle2 = "Task Name: Task 2" + System.lineSeparator();
    assertEquals(expectedTitle2, task2.makeTaskTitle());
  }

  /**
   * Tests for equals
   */
  @Test
  public void testEquals() {
    Task task1 = new Task("Task 1", "Description",
        DayOfWeek.MONDAY, "Category", true);
    Task task2 = new Task("Task 1", "Description",
        DayOfWeek.MONDAY, "Category", true);
    assertTrue(task1.equals(task2));
    Task task3 = new Task("Task 2", "Description",
        DayOfWeek.MONDAY, "Category", true);
    assertFalse(task1.equals(task3));
    Task task4 = new Task("Task 1", "Different Description",
        DayOfWeek.MONDAY, "Category", true);
    assertFalse(task1.equals(task4));
    Task task5 = new Task("Task 1", "Description",
        DayOfWeek.TUESDAY, "Category", true);
    assertFalse(task1.equals(task5));
    Task task6 = new Task("Task 1", "Description",
        DayOfWeek.MONDAY, "Different Category", true);
    assertFalse(task1.equals(task6));
    Task task7 = new Task("Task 1", "Description",
        DayOfWeek.MONDAY, "Category", false);
    assertFalse(task1.equals(task7));
    assertFalse(task1.equals(null));
    assertFalse(task1.equals(new Event("Meeting", "Discuss project updates",
        DayOfWeek.MONDAY, "Work", 1, LocalTime.of(9, 0))));
  }
}