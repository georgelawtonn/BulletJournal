package cs3500.pa05.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.records.MultiplePasswordRecord;
import cs3500.pa05.model.records.WeekRecord;
import java.io.File;
import java.io.IOException;

/**
 * The Reader class provides the functionality to read a file
 */
public class Reader {
  /**
   * Private constructor because static methods only
   */
  private Reader() {
  }

  /**
   * Reads the given file and returns the Week record class of the data within
   *
   * @param file The read file
   * @return A Week record representation of the data within the file
   * @throws IOException If the objectMapper fails to read
   */
  public static WeekRecord readBujoFile(File file) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode data = objectMapper.readTree(file);
    return objectMapper.treeToValue(data, WeekRecord.class);
  }

  /**
   * Reads the password data from the password file and returns it as a MultiplePasswordRecord
   * object.
   *
   * @return The password data as a MultiplePasswordRecord object.
   * @throws IOException If an I/O error occurs during the file read operation.
   */
  public static MultiplePasswordRecord readPassword() throws IOException {
    File file = new File(Writer.passwordFile);
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode data = objectMapper.readTree(file);
    return objectMapper.treeToValue(data, MultiplePasswordRecord.class);
  }
}
