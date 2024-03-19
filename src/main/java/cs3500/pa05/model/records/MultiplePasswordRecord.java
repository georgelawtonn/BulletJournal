package cs3500.pa05.model.records;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Record for multiple passwords
 *
 * @param passwords the list of password records
 */
public record MultiplePasswordRecord(
    @JsonProperty("Passwords") List<PasswordRecord> passwords) {
}
