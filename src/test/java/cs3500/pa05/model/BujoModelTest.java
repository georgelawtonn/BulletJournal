package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa05.model.enums.DayOfWeek;
import cs3500.pa05.model.plannerentry.Event;
import cs3500.pa05.model.plannerentry.Task;
import cs3500.pa05.model.records.EventRecord;
import cs3500.pa05.model.records.MultiplePasswordRecord;
import cs3500.pa05.model.records.TaskRecord;
import cs3500.pa05.model.records.WeekRecord;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents the tester class for the BujoModel class and its methods
 */
class BujoModelTest {
  Path tempDir;
  Path tempFile;
  Path tempFileTwo;
  WeekRecord weekRecordEx;
  Week week;
  BujoModel bujoModel;
  Event event;
  Event eventTwo;
  Task task;
  Task taskDef;
  BujoModel bujoModelTwo;

  /**
   * sets up the necessary obejcts for testing
   *
   * @throws IOException if temp files/directories can not be correctly created
   */
  @BeforeEach
  void setUp() throws IOException {
    Path dir = Path.of("TestDir/");
    dir = dir.toAbsolutePath();
    tempDir = Files.createTempDirectory(dir, "TempTwo");
    tempFile = Files.createTempFile(tempDir, "plants", ".bujo");
    tempFileTwo = Files.createTempFile(tempDir, "plantsTwo", ".bujo");
    try (BufferedWriter writer = Files.newBufferedWriter(tempFile, StandardCharsets.UTF_8)) {
      writer.write("{\"Events\":[],\"Tasks\":[{\"Name\":\"2\",\"Description\":"
          + "\"\",\"Day Of Week\":\"SUNDAY\",\"Category\":\"\",\"Complete\":false}],\"Max"
          + "Task\":2,\"MaxEvent\":2,\"WeekName\":\"222\",\"Categories\":[],\""
          + "Notes\":[],\"Quotes\":[]}");
    }
    File file = tempFile.toFile();

    weekRecordEx = new WeekRecord(new ArrayList<EventRecord>(),
        new ArrayList<TaskRecord>(Arrays.asList(new TaskRecord("2", "", DayOfWeek.SUNDAY,
            "", false))), 2, 2, "222", new ArrayList<String>(),
        new ArrayList<String>(), new ArrayList<String>());
    bujoModel = new BujoModel(file);

    taskDef = new Task("2", "", DayOfWeek.SUNDAY,
        "", false);

    week = new Week(new ArrayList<Task>(Arrays.asList(taskDef)),
        new ArrayList<Event>(), 2, 2, "222",
        new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

    event = new Event("Meet", "Discuss project updates",
        DayOfWeek.MONDAY, "Work", 1, LocalTime.of(10, 0));
    eventTwo = new Event("Meet", "Discuss project updates",
        DayOfWeek.MONDAY, "Work", 1, LocalTime.of(10, 0));
    task = new Task("Task 1", "Description",
        DayOfWeek.MONDAY, "Category", true);

    bujoModelTwo = new BujoModel(tempFileTwo.toFile(), 1, 1, "BUJO");
  }

  /**
   * deletes the temp files/directories after each test
   *
   * @throws IOException if delete fails
   */
  @AfterEach
  void delete() throws IOException {
    Files.delete(tempFile);
    Files.delete(tempFileTwo);
    Files.delete(tempDir);
  }

  /**
   * tests the functionality of being able to load a week
   *
   * @throws IOException if the week can not load
   */
  @Test
  void load() throws IOException {
    Week fileContents = bujoModel.load();
    assertEquals(fileContents.getListOfTasks(), week.getListOfTasks());
    assertEquals(fileContents.getListOfEvents(), week.getListOfEvents());
    assertEquals(fileContents.getWeekName(), week.getWeekName());
    assertEquals(fileContents.getQuotes(), week.getQuotes());
    assertEquals(fileContents.getNotes(), week.getNotes());
    assertEquals(fileContents.getMaxEvent(), week.getMaxEvent());
    assertEquals(fileContents.getMaxTask(), week.getMaxTask());
    assertEquals(fileContents.getCategories(), week.getCategories());
  }

  /**
   * tests the functionality of being able to save a week
   *
   * @throws IOException if the files can not be created
   */
  @Test
  void save() throws IOException {
    bujoModel.load();
    bujoModel.addTask(task);
    bujoModel.addEvent(event);
    bujoModel.addEvent(eventTwo);
    bujoModel.addQuotes("String");
    bujoModel.addNotes("Notes");
    bujoModel.removeTask(taskDef);
    bujoModel.removeEvent(new Event("Meeting", "Discuss project updates",
        DayOfWeek.MONDAY, "Work", 1, LocalTime.of(10, 0)));
    bujoModel.editEvent("Meet", DayOfWeek.MONDAY, "NEW NAME", 1);
    bujoModel.editEvent("Meet", DayOfWeek.MONDAY, "NEW DESC", 2);
    bujoModel.editEvent("Meet", DayOfWeek.MONDAY, "NEW CAT", 3);
    bujoModel.editTask("Task 1", DayOfWeek.MONDAY, "NEW NAME", 1);
    bujoModel.editTask("Task 1", DayOfWeek.MONDAY, "NEW DESC", 2);
    bujoModel.editTask("Task 1", DayOfWeek.MONDAY, "NEW CAT", 3);
    bujoModel.save();
    assertEquals(Files.readAllLines(tempFile).get(0), "{\"Events\":[{\"Name\":\"NEW NAME\""
        + ",\"Description\":\"Discuss project updates\",\"Day Of Week\":\"MONDAY\",\"Category\":"
        + "\"Work\",\"Start Time\":\"10:00\",\"Duration\":1},{\"Name\":\"Meet\",\"Description\":\""
        + "NEW DESC\",\"Day Of Week\":\"MONDAY\",\"Category\":\"NEW CAT\",\"Start Time\":\"10:00\""
        + ",\"Duration\":1}],\"Tasks\":[{\"Name\":\"NEW NAME\",\"Description\":\"Description\",\""
        + "Day Of Week\":\"MONDAY\",\"Category\":\"Category\",\"Complete\":true}],\"MaxTask\":2,\""
        + "MaxEvent\":2,\"WeekName\":\"222\",\"Categories\":[],\"Notes\":[\"Notes\"],\"Quotes\":[\""
        + "String\"]}");

    bujoModelTwo.addTask(task);
    bujoModelTwo.save();
    assertEquals(Files.readAllLines(tempFileTwo).get(0), "{\"Events\":[],\"Tasks\":["
        + "{\"Name\":\"NEW NAME\",\"Description\":\"Description\",\"Day Of Week\":\"MONDAY\",\""
        + "Category\":\"Category\",\"Complete\":true}],\"MaxTask\":1,\"MaxEvent\":1,\"WeekName\""
        + ":\"BUJO\",\"Categories\":[],\"Notes\":[],\"Quotes\":[]}");
  }


  /**
   * Tests for getCategories
   *
   * @throws IOException if load fails
   */
  @Test
  void getCategories() throws IOException {
    bujoModel.load();
    ArrayList<String> cats = bujoModel.getCategories();
    assertEquals(cats, new ArrayList<String>());
  }

  /**
   * Tests for weekContainsEvent
   *
   * @throws IOException if load fails
   */
  @Test
  void weekContainsEvent() throws IOException {
    bujoModel.load();
    assertFalse(bujoModel.weekContainsEvent(event));
    bujoModel.addEvent(event);
    assertTrue(bujoModel.weekContainsEvent(event));
  }


  /**
   * Tests for weekContainsTask
   *
   * @throws IOException if load fails
   */
  @Test
  void weekContainsTask() throws IOException {
    bujoModel.load();
    assertFalse(bujoModel.weekContainsTask(task));
    bujoModel.addTask(task);
    assertTrue(bujoModel.weekContainsTask(task));
  }


  /**
   * Tests for filter
   *
   * @throws IOException if load fails
   */
  @Test
  void filter() throws IOException {
    bujoModel.load();
    assertEquals(bujoModel.filter("2"), "");
    bujoModel.addTask(task);
    bujoModel.addEvent(event);
    assertEquals(bujoModel.filter("Category").trim(), "Task Name: Task 1"
        + System.lineSeparator()
        + "Desc: Description" + System.lineSeparator()
        + "Category: Category");
    assertEquals(bujoModel.filter("Work").trim(), "Event Name: Meet" + System.lineSeparator()
        + "Desc: Discuss project updates" + System.lineSeparator()
        + "Category: Work" + System.lineSeparator()
        + "Time of event: 10:00 - 11:00");
  }


  /**
   * Tests for searchTask
   *
   * @throws IOException if load fails
   */
  @Test
  void searchTask() throws IOException {
    bujoModel.load();
    assertEquals(bujoModel.searchTask("2").get(0), taskDef);
  }

  @Test
  public void testReadPassword() throws IOException {
    File file = new File("YourJournal.Bujo");
    Map<String, String> passwords = new HashMap<>();
    passwords.put("YourJournal.bujo", "000000");
    bujoModel = new BujoModel(file, 5, 10, "Week 1");
    // Call the method under test
    bujoModel.readPassword();
    // Assertions
    assertEquals(passwords, bujoModel.getPasswords());
  }

  @Test
  public void testSetPassword() {
    bujoModel.setPassword("newpassword");
    assertEquals("newpassword", bujoModel.getPassword());
  }
}