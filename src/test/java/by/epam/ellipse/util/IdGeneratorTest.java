package by.epam.ellipse.util;

import by.epam.ellipse.entity.Ellipse;
import by.epam.ellipse.entity.Point;
import by.epam.ellipse.service.exception.ServiceException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class IdGeneratorTest {
    private IdGenerator idGenerator;
    private List<Ellipse> ellipses1;
    private List<Ellipse> ellipses2;
    private List<Ellipse> ellipses3;

    private Ellipse ellipse1;
    private Ellipse ellipse2;

    private Point point1;

    @Before
    public void setUp() {
        idGenerator = IdGenerator.getInstance();

        ellipse1 = new Ellipse();
        ellipse1.setId(5);
        ellipses1 = new ArrayList<>();
        ellipses1.add(ellipse1);

        point1 = new Point(0.0, 0.0);
        ellipse2 = new Ellipse(point1, point1);
        ellipse2.setId(5);
        ellipses2 = new ArrayList<>();
        ellipses2.add(ellipse2);

        ellipses3 = new ArrayList<>();
        ellipses3.add(null);
    }

    @After
    public void shutDown() {
        idGenerator = null;

        point1 = null;

        ellipse1 = null;
        ellipse2 = null;

        ellipses1 = null;
        ellipses2 = null;
        ellipses3 = null;
    }


    @Test
    public void generate() {
        try {
            int expected = 6;
            int actual = idGenerator.generate(ellipses1);
            Assert.assertEquals(expected, actual);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void generate_INVALID_ELLIPSE() {
        try {
            int expected = 6;
            int actual = idGenerator.generate(ellipses2);

            Assert.assertEquals(expected, actual);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void generate_NULL_ELLIPSE() {
        try {
            idGenerator.generate(ellipses3);
            Assert.fail("Expected ServiceException.");
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void generate_NULL() {
        try {
            idGenerator.generate(null);
            Assert.fail("Expected ServiceException.");
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }
}