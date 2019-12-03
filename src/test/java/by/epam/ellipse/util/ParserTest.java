package by.epam.ellipse.util;

import by.epam.ellipse.dao.exception.DAOexception;
import org.junit.*;

public class ParserTest {
    private static Parser parser;

    private static String coordinates1;
    private static String coordinates2;
    private static String coordinates3;
    private static String coordinates4;

    @Before
    public void setUp() {
        parser = Parser.getInstance();

        coordinates1 = "-10.0 5.0 10.0 -10.0";
        coordinates2 = "-10.c0 0.0 -0.5 0.6";
        coordinates3 = "-10.6 5.06 ";
        coordinates4 = "";
    }

    @After
    public void shutDown() {
        parser = null;
    }

    @Test
    public void parseCoordinates() {
        double[] actual;
        double[] expected = new double[]{-10.0, 5.0, 10.0, -10.0};

        try {
            actual = parser.parseCoordinates(coordinates1);
            for (int i = 0; i < actual.length; i++) {
                Assert.assertEquals(actual[i], expected[i], 0.01);
            }
        } catch (DAOexception e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void parseCoordinates_INCORRECT_STRING_FORMAT() {
        try {
            parser.parseCoordinates(coordinates2);
            Assert.fail("Expected DAOException.");
        } catch (DAOexception e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }


    @Test
    public void parseCoordinates_LACK_INFORMATION() {
        try {
            parser.parseCoordinates(coordinates3);
            Assert.fail("Expected DAOException.");
        } catch (DAOexception e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void parseCoordinates_EMPTY_STRING() {
        try {
            parser.parseCoordinates(coordinates4);
            Assert.fail("Expected DAOException.");
        } catch (DAOexception e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void parseCoordinates_NULL() {
        try {
            parser.parseCoordinates(null);
            Assert.fail("Expected DAOException.");
        } catch (DAOexception e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }
}