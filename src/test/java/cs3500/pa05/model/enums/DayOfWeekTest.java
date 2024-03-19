package cs3500.pa05.model.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for DayOfWeek enum
 */
public class DayOfWeekTest {

  /**
   * Tests for getStringRep
   */
  @Test
  public void testGetStringRep() {
    DayOfWeek sunday = DayOfWeek.SUNDAY;
    assertEquals("Sunday", sunday.getStringRep());

    DayOfWeek monday = DayOfWeek.MONDAY;
    assertEquals("Monday", monday.getStringRep());

    DayOfWeek tuesday = DayOfWeek.TUESDAY;
    assertEquals("Tuesday", tuesday.getStringRep());

    DayOfWeek wednesday = DayOfWeek.WEDNESDAY;
    assertEquals("Wednesday", wednesday.getStringRep());

    DayOfWeek thursday = DayOfWeek.THURSDAY;
    assertEquals("Thursday", thursday.getStringRep());

    DayOfWeek friday = DayOfWeek.FRIDAY;
    assertEquals("Friday", friday.getStringRep());

    DayOfWeek saturday = DayOfWeek.SATURDAY;
    assertEquals("Saturday", saturday.getStringRep());
  }

  /**
   * Tests for Values
   */
  @Test
  public void testValues() {
    DayOfWeek[] days = DayOfWeek.values();

    // Ensure all days are present
    assertEquals(7, days.length);

    // Ensure the order is correct
    assertEquals(DayOfWeek.SUNDAY, days[0]);
    assertEquals(DayOfWeek.MONDAY, days[1]);
    assertEquals(DayOfWeek.TUESDAY, days[2]);
    assertEquals(DayOfWeek.WEDNESDAY, days[3]);
    assertEquals(DayOfWeek.THURSDAY, days[4]);
    assertEquals(DayOfWeek.FRIDAY, days[5]);
    assertEquals(DayOfWeek.SATURDAY, days[6]);
  }

  /**
   * Tests for valueOf
   */
  @Test
  void testValueOf() {
    DayOfWeek monday = DayOfWeek.valueOf("MONDAY");
    assertEquals(DayOfWeek.MONDAY, monday);

    DayOfWeek saturday = DayOfWeek.valueOf("SATURDAY");
    assertEquals(DayOfWeek.SATURDAY, saturday);
  }
}