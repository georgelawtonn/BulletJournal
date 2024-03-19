package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa05.model.records.EventRecord;
import cs3500.pa05.model.records.TaskRecord;
import cs3500.pa05.model.records.WeekRecord;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;


/**
 * The tester class of the writer
 */
public class WriterTest {

  /**
   * tests the functionality of updating the bujoFile
   *
   * @throws IOException if anything goes wrong with the making of the file
   */
  @Test
  public void testUpdateBujoFile() throws IOException {
    ArrayList<EventRecord> events = new ArrayList<>();
    ArrayList<TaskRecord> tasks = new ArrayList<>();
    int maxTask = 0;
    int maxEvent = 0;
    String weekName = "Asian Pride Week";
    ArrayList<String> categories = new ArrayList<>();
    ArrayList<String> notes = new ArrayList<>();
    ArrayList<String> quotes = new ArrayList<>();

    // Specify the path to the test file
    String filePath = "test_bujo.json";

    // Create a WeekRecord object to write to the file
    WeekRecord weekRecord =
        new WeekRecord(events, tasks, maxTask, maxEvent, weekName, categories, notes, quotes);

    // Delete the test file if it already exists
    Path path = Paths.get(filePath);
    Files.deleteIfExists(path);

    // Call the updateBujoFile method
    File file = new File(filePath);
    Writer.updateBujoFile(file, weekRecord);

    // Check if the file was created
    assertTrue(Files.exists(path));

    // Read the contents of the file
    String fileContent = Files.readString(path);

    // Specify the expected JSON content
    String expectedJson =
        "{\"Events\":[],\"Tasks\":[],\"MaxTask\":0,\"MaxEvent\":0,"
            + "\"WeekName\":\"Asian Pride Week\",\"Categories\":[],\"Notes\":[],\"Quotes\":[]}";

    // Compare the file content with the expected JSON
    assertEquals(expectedJson, fileContent);

    // Delete the test file after the test completes
    Files.deleteIfExists(path);
  }
}