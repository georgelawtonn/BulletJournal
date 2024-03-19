package cs3500.pa05.model.plannerentry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import cs3500.pa05.model.enums.DayOfWeek;
import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for Event
 */
class EventTest {
  Event event;
  Event eventT;

  /**
   * Setup for testing
   */
  @BeforeEach
  void setUp() {
    event = new Event("Meeting", "Discuss project updates",
        DayOfWeek.MONDAY, "Work", 1, LocalTime.of(9, 0));
    eventT = new Event("Meeting", "",
        DayOfWeek.MONDAY, "", 1, LocalTime.of(9, 0));
  }

  /**
   * Tests for getName
   */
  @Test
  public void testGetName() {
    String expectedName = "Meeting";
    assertEquals(expectedName, event.getName());
  }

  /**
   * Tests for getDescription
   */
  @Test
  public void testGetDescription() {
    String expectedDescription = "Discuss project updates";
    assertEquals(expectedDescription, event.getDescription());
  }

  /**
   * Tests for getDayOfWeek
   */
  @Test
  public void testGetDayOfWeek() {
    DayOfWeek expectedDayOfWeek = DayOfWeek.MONDAY;
    assertEquals(expectedDayOfWeek, event.getDayOfWeek());
  }

  /**
   * Tests for getCategory
   */
  @Test
  public void testGetCategory() {
    String expectedCategory = "Work";
    assertEquals(expectedCategory, event.getCategory());
  }

  /**
   * Tests for setName
   */
  @Test
  public void testSetName() {
    event.setName("Conference");
    String expectedName = "Conference";
    assertEquals(expectedName, event.getName());
  }

  /**
   * Tests for setDescription
   */
  @Test
  public void testSetDescription() {
    event.setDescription("Discuss project goals");
    String expectedDescription = "Discuss project goals";
    assertEquals(expectedDescription, event.getDescription());
  }

  /**
   * Tests for setCategory
   */
  @Test
  public void testSetCategory() {
    event.setCategory("Personal");
    String expectedCategory = "Personal";
    assertEquals(expectedCategory, event.getCategory());
  }

  /**
   * Tests for getTime
   */
  @Test
  void getTime() {
    LocalTime expectedTime = LocalTime.of(9, 0);
    assertEquals(expectedTime, event.getTime());
  }

  /**
   * Tests for getDuration
   */
  @Test
  void getDuration() {
    int expectedDuration = 1;
    assertEquals(expectedDuration, event.getDuration());
  }

  /**
   * Tests for makeEventTitle
   */
  @Test
  void makeEventTitle() {
    String expectedTitle = "Event Name: Meeting" + System.lineSeparator()
        + "Desc: Discuss project updates" + System.lineSeparator()
        + "Category: Work" + System.lineSeparator()
        + "Time of event: 09:00 - 10:00" + System.lineSeparator();

    String expectedTitleT = "Event Name: Meeting" + System.lineSeparator()
        + "Time of event: 09:00 - 10:00" + System.lineSeparator();
    assertEquals(expectedTitle, event.makeEventTitle());
    assertEquals(expectedTitleT, eventT.makeEventTitle());
  }

  /**
   * Tests for equals
   */
  @Test
  void testEquals() {
    Event otherEvent = new Event("Meeting", "Discuss project updates",
        DayOfWeek.MONDAY, "Work", 1, LocalTime.of(9, 0));
    assertEquals(event, otherEvent);
    Event otherNotEvent = new Event("Meeting", "Discuss project updates",
        DayOfWeek.TUESDAY, "Work", 1, LocalTime.of(9, 0));
    assertNotEquals(event, otherNotEvent);
    assertFalse(event.equals(null));
    assertFalse(event.equals(new Task("Task 1", "Description",
        DayOfWeek.MONDAY, "Category", true)));
    Event otherNotEventTwo = new Event("Meeting", "Discuss project updates",
        DayOfWeek.MONDAY, "Work", 1, LocalTime.of(10, 0));
    assertFalse(event.equals(otherNotEventTwo));
    Event otherNotEventThree = new Event("Meet", "Discuss project updates",
        DayOfWeek.TUESDAY, "Work", 1, LocalTime.of(9, 0));
    assertFalse(event.equals(otherNotEventThree));
    Event otherNotEventFour = new Event("Meeting", "Discuss",
        DayOfWeek.TUESDAY, "Work", 1, LocalTime.of(9, 0));
    assertFalse(event.equals(otherNotEventFour));
    Event otherNotEventFive = new Event("Meeting", "Discuss project updates",
        DayOfWeek.TUESDAY, "", 1, LocalTime.of(9, 0));
    assertFalse(event.equals(otherNotEventFive));
  }
}