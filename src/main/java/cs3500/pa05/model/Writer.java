package cs3500.pa05.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.records.MultiplePasswordRecord;
import cs3500.pa05.model.records.WeekRecord;
import java.io.File;
import java.io.IOException;

/**
 * The Writer class provides functionality to update a Bujo file with a Week object.
 */
public class Writer {

  public static String passwordFile = "password.Json";

  /**
   * Private constructor because static methods only
   */
  private Writer() {
  }

  /**
   * Updates the BUJO file at the specified path with the provided Week object.
   *
   * @param file       the path of the BUJO file to be updated
   * @param weekRecord the Week object representing the week's data
   * @throws IOException if an I/O error occurs during the file write operation
   */
  public static void updateBujoFile(File file, WeekRecord weekRecord) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(file, weekRecord);
  }

  /**
   * Saves password
   *
   * @param multiplePasswordRecord The passwords
   * @throws IOException If writeValue fails
   */
  public static void savePassword(MultiplePasswordRecord multiplePasswordRecord)
      throws IOException {
    File file = new File("password.Json");
    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(file, multiplePasswordRecord);
  }
}
