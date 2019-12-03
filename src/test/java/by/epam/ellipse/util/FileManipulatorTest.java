package by.epam.ellipse.util;

import by.epam.ellipse.dao.exception.DAOexception;
import org.junit.*;
import java.util.ArrayList;
import java.util.List;

public class FileManipulatorTest {
    private static FileManipulator fileManipulator;
    private static String request;
    private static List<String> expected;

    @Before
    public void setUp() {
        fileManipulator = FileManipulator.getInstance();

        expected = new ArrayList<>();
        expected.add("-1.0 -2.0 5.0 6.0");
        expected.add("10.0 0.0 10.0 0.0");
        expected.add("-10.0 -10.0 10.0 10.0");
        expected.add("10.0 5.0 15.0 10.0");
    }

    @After
    public void shutDown() {
        fileManipulator = null;
        expected = null;
    }

    @Test
    public void extractEntriesFromFile() {
        request = "test.ellipse.base";
        List<String> actual;
        try {
            actual = fileManipulator.extractEntriesFromFile(request);
            Assert.assertEquals(expected, actual);
        } catch (DAOexception e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void extractEntriesFromFile_INWALID_REQUEST() {
        request = "test1.ellipse.base";
        try {
            fileManipulator.extractEntriesFromFile(request);
            Assert.fail("Expected DAOexception.");
        } catch (DAOexception e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void extractEntriesFromFile_EMPTY_REQUEST() {
        request = "";
        try {
            fileManipulator.extractEntriesFromFile(request);
            Assert.fail("Expected DAOexception.");
        } catch (DAOexception e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void extractEntriesFromFile_NULL() {
        try {
            fileManipulator.extractEntriesFromFile(null);
            Assert.fail("Expected DAOexception.");
        } catch (DAOexception e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }
}