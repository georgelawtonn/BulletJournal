package cs3500.pa05.model.plannerentry;

import cs3500.pa05.model.enums.DayOfWeek;
import java.time.LocalTime;

/**
 * The Event class represents an event entry in a planner and extends the PlannerEntry class.
 */
public class Event extends PlannerEntry {
  private int duration;
  private LocalTime startTime;

  /**
   * Constructs an Event object with the specified details.
   *
   * @param name        the name of the event
   * @param description the description of the event
   * @param dayOfWeek   the day of the week the event occurs on
   * @param category    the category of the event
   * @param duration    the duration of the event in minutes
   * @param startTime   the start time of the event
   */
  public Event(String name, String description, DayOfWeek dayOfWeek, String category, int duration,
               LocalTime startTime) {
    super(name, description, dayOfWeek, category);
    this.duration = duration;
    this.startTime = startTime;
  }

  /**
   * Returns the start time of the event.
   *
   * @return the start time of the event
   */
  public LocalTime getTime() {
    return startTime;
  }

  /**
   * Returns the duration of the event in minutes.
   *
   * @return the duration of the event
   */
  public int getDuration() {
    return duration;
  }

  /**
   * Makes the title of the Event to show to the user
   *
   * @return the event title
   */
  public String makeEventTitle() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Event Name: " + this.getName());
    stringBuilder.append(System.lineSeparator());
    if (!this.getDescription().isEmpty()) {
      stringBuilder.append("Desc: " + this.getDescription());
      stringBuilder.append(System.lineSeparator());
    }
    if (!this.getCategory().isEmpty()) {
      stringBuilder.append("Category: " + this.getCategory());
      stringBuilder.append(System.lineSeparator());
    }
    stringBuilder.append(
        "Time of event: " + this.getTime() + " - " + startTime.plusHours(this.getDuration()));
    stringBuilder.append(System.lineSeparator());
    return stringBuilder.toString();
  }

  /**
   * overriding equals to compare two events
   *
   * @param obj the other event object to compare to
   * @return Whether obj is equal to this
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Event otherEvent = (Event) obj;
    return super.equals(obj)
        && duration == otherEvent.duration
        && startTime.equals(otherEvent.startTime);
  }

}
