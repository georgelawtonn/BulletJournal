package cs3500.pa05.model.records;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.enums.DayOfWeek;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for EventRecord
 */
class EventRecordTest {
  private EventRecord event;

  /**
   * Setup for Testing
   */
  @BeforeEach
  public void settingUp() {
    event = new EventRecord("Event Name", "Event Description",
        DayOfWeek.MONDAY, "Category", "10:00", 60);
  }

  /**
   * Tests for getName
   */
  @Test
  public void testGetName() {
    assertEquals("Event Name", event.name());
  }

  /**
   * Tests for getDescription
   */
  @Test
  public void testGetDescription() {
    assertEquals("Event Description", event.description());
  }

  /**
   * Tests for getDayOfWeek
   */
  @Test
  public void testGetDayOfWeek() {
    assertEquals(DayOfWeek.MONDAY, event.dayOfWeek());
  }

  /**
   * Tests for getCategory
   */
  @Test
  public void testGetCategory() {
    assertEquals("Category", event.category());
  }

  /**
   * Tests for getStartTime
   */
  @Test
  public void testGetStartTime() {
    assertEquals("10:00", event.startTime());
  }

  /**
   * Tests for getDuration
   */
  @Test
  public void testGetDuration() {
    assertEquals(60, event.duration());
  }
}