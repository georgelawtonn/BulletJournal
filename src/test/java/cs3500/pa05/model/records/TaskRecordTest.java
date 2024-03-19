package cs3500.pa05.model.records;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa05.model.enums.DayOfWeek;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for TaskRecord
 */
class TaskRecordTest {

  private TaskRecord taskRecord;

  /**
   * Setup for testing
   */
  @BeforeEach
  public void setup() {
    taskRecord =
        new TaskRecord("Task 1", "Task 1 description", DayOfWeek.MONDAY,
            "Category 1", true);
  }

  /**
   * Tests for record constructor and getters
   */
  @Test
  public void testTaskRecordConstructorAndGetters() {
    // Test the getters to ensure the values are set correctly
    assertEquals("Task 1", taskRecord.name());
    assertEquals("Task 1 description", taskRecord.description());
    assertEquals(DayOfWeek.MONDAY, taskRecord.dayOfWeek());
    assertEquals("Category 1", taskRecord.category());
    assertTrue(taskRecord.complete());
  }

  /**
   * Tests for record equality
   */
  @Test
  public void testTaskRecordEquality() {
    // Create another TaskRecord object with the same values
    TaskRecord taskRecord2 =
        new TaskRecord("Task 1", "Task 1 description", DayOfWeek.MONDAY,
            "Category 1", true);

    // Test the equality of the two objects
    assertEquals(taskRecord, taskRecord2);
  }

  /**
   * Tests for record to string
   */
  @Test
  public void testTaskRecordToString() {
    // Test the toString() method
    String expectedString =
        "TaskRecord[name=Task 1, description=Task 1 description, dayOfWeek=MONDAY, "
            + "category=Category 1, complete=true]";
    assertEquals(expectedString, taskRecord.toString());
  }
}