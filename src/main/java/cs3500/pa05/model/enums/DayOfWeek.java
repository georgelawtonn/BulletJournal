package cs3500.pa05.model.enums;

/**
 * The DayOfWeek enumeration represents the days of the week.
 */
public enum DayOfWeek {
  SUNDAY("Sunday"), MONDAY("Monday"), TUESDAY("Tuesday"), WEDNESDAY("Wednesday"),
  THURSDAY("Thursday"), FRIDAY("Friday"), SATURDAY("Saturday");

  private final String dayOfWeek;

  /**
   * Constructs a DayOfWeek with the specified day name.
   *
   * @param day the name of the day
   */
  DayOfWeek(String day) {
    this.dayOfWeek = day;
  }

  /**
   * Returns the string representation of the day.
   *
   * @return the name of the day
   */
  public String getStringRep() {
    return dayOfWeek;
  }
}
