package cs3500.pa05.model.records;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a password record that contains the filename and associated password.
 */
public record PasswordRecord(
    @JsonProperty("Filename") String fileName,
    @JsonProperty("Password") String password) {
}
