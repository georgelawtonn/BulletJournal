package cs3500.pa05.model.records;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.enums.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for WeekRecord
 */
class WeekRecordTest {

  private WeekRecord weekRecord;

  /**
   * Setup for testing
   */
  @BeforeEach
  public void setup() {
    ArrayList<EventRecord> events = new ArrayList<>();
    events.add(
        new EventRecord("Event 1", "Event 1 description", DayOfWeek.MONDAY, "Category 1", "10:00",
            60));
    events.add(new EventRecord("Event 2", "Event 2 description", DayOfWeek.WEDNESDAY, "Category 2",
        "14:00", 90));

    ArrayList<TaskRecord> tasks = new ArrayList<>();
    tasks.add(
        new TaskRecord("Task 1", "Task 1 description", DayOfWeek.TUESDAY, "Category 1", true));
    tasks.add(
        new TaskRecord("Task 2", "Task 2 description", DayOfWeek.THURSDAY, "Category 2", false));

    final ArrayList<String> categories = new ArrayList<>();
    categories.add("school");
    categories.add("personal");

    final ArrayList<String> notes = new ArrayList<>();
    notes.add("notes1");
    notes.add("notes2");

    final ArrayList<String> quotes = new ArrayList<>();
    quotes.add("quotes1");
    quotes.add("quotes2");

    weekRecord = new WeekRecord(events, tasks, 2, 2, "Week 1", categories, notes, quotes);
  }

  /**
   * Tests for weekRecord Constructor and getters
   */
  @Test
  public void testWeekRecordConstructorAndGetters() {
    // Test the getters to ensure the values are set correctly
    assertEquals(2, weekRecord.events().size());
    assertEquals(2, weekRecord.tasks().size());
    assertEquals(2, weekRecord.maxTask());
    assertEquals(2, weekRecord.maxEvent());
    assertEquals("Week 1", weekRecord.weekName());
    assertEquals(Arrays.asList("school", "personal"), weekRecord.categories());
    assertEquals(Arrays.asList("notes1", "notes2"), weekRecord.notes());
    assertEquals(Arrays.asList("quotes1", "quotes2"), weekRecord.quotes());
  }

  /**
   * Tests for weekRecord toString
   */
  @Test
  public void testWeekRecordToString() {
    // Test the toString() method
    String expectedString =
        "WeekRecord[events=[EventRecord[name=Event 1, description=Event 1 description, "
            + "dayOfWeek=MONDAY, category=Category 1, startTime=10:00, duration=60], "
            + "EventRecord[name=Event 2, description=Event 2 description, dayOfWeek=WEDNESDAY, "
            + "category=Category 2, startTime=14:00, duration=90]], tasks=[TaskRecord[name=Task 1, "
            + "description=Task 1 description, dayOfWeek=TUESDAY, category=Category 1, "
            + "complete=true], TaskRecord[name=Task 2, description=Task 2 description, "
            + "dayOfWeek=THURSDAY, category=Category 2, complete=false]], maxTask=2, maxEvent=2, "
            + "weekName=Week 1, categories=[school, personal], notes=[notes1, notes2], "
            + "quotes=[quotes1, quotes2]]";
    assertEquals(expectedString, weekRecord.toString());
  }
}