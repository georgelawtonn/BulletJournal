package cs3500.pa05.model.records;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.enums.DayOfWeek;

/**
 * Represents an event as a record
 *
 * @param name The name of the event
 * @param description The description of the event
 * @param dayOfWeek The day of week of the event
 * @param category The category that the event falls under
 * @param startTime The start time of the event
 * @param duration The duration of the event
 */
public record EventRecord(
    @JsonProperty("Name") String name,
    @JsonProperty("Description") String description,
    @JsonProperty("Day Of Week") DayOfWeek dayOfWeek,
    @JsonProperty("Category") String category,
    @JsonProperty("Start Time") String startTime,
    @JsonProperty("Duration") int duration) {
}
