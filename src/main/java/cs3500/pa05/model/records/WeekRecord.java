package cs3500.pa05.model.records;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

/**
 * Represents a Day as a record
 *
 * @param events The events within a day
 * @param tasks  The tasks within a day
 */
public record WeekRecord(
    @JsonProperty("Events") ArrayList<EventRecord> events,
    @JsonProperty("Tasks") ArrayList<TaskRecord> tasks,
    @JsonProperty("MaxTask") int maxTask,
    @JsonProperty("MaxEvent") int maxEvent,
    @JsonProperty("WeekName") String weekName,
    @JsonProperty("Categories") ArrayList<String> categories,
    @JsonProperty("Notes") ArrayList<String> notes,
    @JsonProperty("Quotes") ArrayList<String> quotes) {
}

