package cs3500.pa05.model.records;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.enums.DayOfWeek;

/**
 * Represents a task as a record
 *
 * @param name The name of the task
 * @param description The description of the task
 * @param dayOfWeek The day of week of the task
 * @param category The category the task falls under
 * @param complete The completeness of the task
 */
public record TaskRecord(
    @JsonProperty("Name") String name,
    @JsonProperty("Description") String description,
    @JsonProperty("Day Of Week") DayOfWeek dayOfWeek,
    @JsonProperty("Category") String category,
    @JsonProperty("Complete") boolean complete) {
}


