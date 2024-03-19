package cs3500.pa05.model;


import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.enums.DayOfWeek;
import cs3500.pa05.model.records.EventRecord;
import cs3500.pa05.model.records.TaskRecord;
import cs3500.pa05.model.records.WeekRecord;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tester class for the reader test
 */
class ReaderTest {
  Path tempDir;
  Path tempFile;
  File file;
  WeekRecord weekRecordEx;

  /**
   * sets up the necessary objects for testing
   *
   * @throws IOException if a file can not be created
   */
  @BeforeEach
  void setUp() throws IOException {
    Path dir = Path.of("TestDir/");
    dir = dir.toAbsolutePath();
    tempDir = Files.createTempDirectory(dir, "Temp");
    tempFile = Files.createTempFile(tempDir, "plants", ".bujo");
    try (BufferedWriter writer = Files.newBufferedWriter(tempFile, StandardCharsets.UTF_8)) {
      writer.write("{\"Events\":[],\"Tasks\":[{\"Name\":\"2\",\"Description\":"
          + "\"\",\"Day Of Week\":\"SUNDAY\",\"Category\":\"\",\"Complete\":false}],\"Max"
          + "Task\":2,\"MaxEvent\":2,\"WeekName\":\"222\",\"Categories\":[],\"Notes\":[],\""
          + "Quotes\":[]}");
    }
    file = tempFile.toFile();

    weekRecordEx = new WeekRecord(new ArrayList<EventRecord>(),
        new ArrayList<TaskRecord>(Arrays.asList(new TaskRecord("2", "", DayOfWeek.SUNDAY,
            "", false))), 2, 2, "222", new ArrayList<String>(),
        new ArrayList<String>(), new ArrayList<String>());
  }

  /**
   * deletes the temp files and directories after testing
   *
   * @throws IOException if the files/directories went wrong
   */
  @AfterEach
  void delete() throws IOException {
    Files.delete(tempFile);
    Files.delete(tempDir);
  }

  /**
   * testing the ability of reading from a file
   *
   * @throws IOException if the method breaks
   */
  @Test
  void readBujoFile() throws IOException {
    WeekRecord weekRecord = Reader.readBujoFile(file);
    assertEquals(weekRecordEx, weekRecord);
  }
}
