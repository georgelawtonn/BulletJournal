package cs3500.pa05.model.plannerentry;

import cs3500.pa05.model.enums.DayOfWeek;
import java.util.Objects;

/**
 * The PlannerEntry class represents a basic entry in a planner.
 */
public abstract class PlannerEntry {
  private String name;
  private String description;
  private DayOfWeek dayOfWeek;
  private String category;

  /**
   * Constructs a PlannerEntry object with the specified details.
   *
   * @param name        the name of the planner entry
   * @param description the description of the planner entry
   * @param dayOfWeek   the day of the week for the planner entry
   * @param category    the category of the planner entry
   */
  public PlannerEntry(String name, String description, DayOfWeek dayOfWeek, String category) {
    this.name = name;
    this.description = description;
    this.dayOfWeek = dayOfWeek;
    this.category = category;
  }

  /**
   * Returns the name of the planner entry.
   *
   * @return the name of the planner entry
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the description of the planner entry.
   *
   * @return the description of the planner entry
   */
  public String getDescription() {
    return description;
  }

  /**
   * Returns the day of the week for the planner entry.
   *
   * @return the day of the week for the planner entry
   */
  public DayOfWeek getDayOfWeek() {
    return dayOfWeek;
  }

  /**
   * Returns the category of the planner entry.
   *
   * @return the category of the planner entry
   */
  public String getCategory() {
    return category;
  }

  /**
   * overriding equals to compare two planner entries
   *
   * @param obj the other planner entry object to compare to
   * @return Whether the obj is equal to this
   */
  @Override
  public boolean equals(Object obj) {
    /*
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
     */
    PlannerEntry otherEntry = (PlannerEntry) obj;
    return Objects.equals(name, otherEntry.name)
        && Objects.equals(description, otherEntry.description)
        && dayOfWeek == otherEntry.dayOfWeek
        && Objects.equals(category, otherEntry.category);
  }

  /**
   * Sets the name of the planner entry.
   *
   * @param name the new name for the planner entry
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the description of the planner entry.
   *
   * @param description the new description for the planner entry
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Sets the category of the planner entry.
   *
   * @param category the new category for the planner entry
   */
  public void setCategory(String category) {
    this.category = category;
  }

}
