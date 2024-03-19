package cs3500.pa05.model.plannerentry;


import cs3500.pa05.model.enums.DayOfWeek;

/**
 * The Task class represents a task entry in a planner and extends the PlannerEntry class.
 */
public class Task extends PlannerEntry {
  private boolean isComplete;

  /**
   * Constructs a Task object with the specified details.
   *
   * @param name        the name of the task
   * @param description the description of the task
   * @param dayOfWeek   the day of the week the task is scheduled for
   * @param category    the category of the task
   * @param isComplete  the completion status of the task
   */
  public Task(String name, String description, DayOfWeek dayOfWeek, String category,
              boolean isComplete) {
    super(name, description, dayOfWeek, category);
    this.isComplete = isComplete;
  }

  /**
   * Returns the completion status of the task.
   *
   * @return true if the task is complete, false otherwise
   */
  public boolean getComplete() {
    return isComplete;
  }

  public void setComplete(boolean complete) {
    this.isComplete = complete;
  }

  /**
   * Makes the title of the Task to show to the user
   *
   * @return the task title
   */
  public String makeTaskTitle() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Task Name: " + this.getName());
    stringBuilder.append(System.lineSeparator());
    if (!this.getDescription().isEmpty()) {
      stringBuilder.append("Desc: " + this.getDescription());
      stringBuilder.append(System.lineSeparator());
    }
    if (!this.getCategory().isEmpty()) {
      stringBuilder.append("Category: " + this.getCategory());
      stringBuilder.append(System.lineSeparator());
    }
    return stringBuilder.toString();
  }

  /**
   * overriding equals to compare two tasks
   *
   * @param obj the other task object to compare to
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
    Task otherTask = (Task) obj;
    return super.equals(obj)
        && isComplete == otherTask.isComplete;
  }

}
