package by.epam.ellipse.service;

import by.epam.ellipse.entity.Ellipse;
import by.epam.ellipse.entity.Point;
import by.epam.ellipse.service.exception.ServiceException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class EllipseServiceImplTest {
    private static EllipseServiceImpl ellipseService;

    private Point point1;
    private Point point2;
    private Point point3;
    private Point point4;
    private Point point5;
    private Point point6;

    private Ellipse ellipse1;
    private Ellipse ellipse2;
    private Ellipse ellipse3;
    private Ellipse ellipse4;

    private List<Ellipse> expected;


    @Before
    public void setUp() throws ServiceException {
        ellipseService = EllipseServiceImpl.getInstance();

        expected = new ArrayList<>();

        point1 = new Point(-1.0, -2.0);
        point2 = new Point(5.0, 6.0);
        point3 = new Point(-10.0, -10.0);
        point4 = new Point(10.0, 10.0);
        point5 = new Point(10.0, 5.0);
        point6 = new Point(15.0, 10.0);

        ellipse1 = new Ellipse(point1, point2);
        ellipse2 = new Ellipse(point3, point4);
        ellipse3 = new Ellipse(point5, point6);
        ellipse4 = new Ellipse(point5, point5);

        ellipseService.updateEllipse(ellipse1);
        ellipseService.updateEllipse(ellipse2);
        ellipseService.updateEllipse(ellipse3);

        expected.add(ellipse1);
        expected.add(ellipse2);
        expected.add(ellipse3);
    }

    @After
    public void shutDown() {
        ellipseService = null;

        expected = null;

        point1 = null;
        point2 = null;
        point3 = null;
        point4 = null;
        point5 = null;
        point6 = null;

        ellipse1 = null;
        ellipse2 = null;
        ellipse3 = null;
        ellipse4 = null;
    }

    @Test
    public void createFromFile() {
        List<Ellipse> actual;
        try {
            actual = ellipseService.createFromFile("test.ellipse.base");
            Assert.assertEquals(actual, expected);
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void createFromFile_WRONG_REQUEST() {
        try {
            ellipseService.createFromFile("xdgf");
            Assert.fail("Expected ServiceException.");
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void createFromFile_EMPTY_REQUEST() {
        try {
            ellipseService.createFromFile("");
            Assert.fail("Expected ServiceException.");
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void createFromFile_NULL() {
        try {
            ellipseService.createFromFile(null);
            Assert.fail(null);
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test()
    public void findPerimeter() {
        double expected = 62.83;
        double actual;
        try {
            actual = ellipseService.findPerimeter(ellipse2);
            Assert.assertEquals(expected, actual, 0.01);
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test()
    public void findPerimeter_INVALID_ELLIPSE() {
        try {
            ellipseService.findPerimeter(ellipse4);
            Assert.fail("Expected ServiceException.");
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test()
    public void findPerimeter_NULL() {
        try {
            ellipseService.findPerimeter(null);
            Assert.fail("Expected ServiceException.");
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test()
    public void findArea() {
        double expected = 314.16;
        double actual;
        try {
            actual = ellipseService.findArea(ellipse2);
            Assert.assertEquals(expected, actual, 0.01);
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test()
    public void findArea_INVALID_ELLIPSE() {
        try {
            ellipseService.findArea(ellipse4);
            Assert.fail("Expected ServiceException.");
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test()
    public void findArea_NULL() {
        try {
            ellipseService.findArea(null);
            Assert.fail("Expected ServiceException.");
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void isCircle_FALSE() {
        try {
            boolean actual = ellipseService.isCircle(ellipse1);
            Assert.assertFalse(actual);
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void isCircle_TRUE() {
        try {
            boolean actual = ellipseService.isCircle(ellipse2);
            Assert.assertTrue(actual);
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void isCircle_INVALID_ELLIPSE() {
        try {
            ellipseService.isCircle(ellipse4);
            Assert.fail("Expected ServiceException.");
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void isCircle_NUL() {
        try {
            ellipseService.isCircle(null);
            Assert.fail("Expected ServiceException.");
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void isCrossX_TRUE() {
        try {
            boolean actual = ellipseService.isCrossX(ellipse1);
            Assert.assertTrue(actual);
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void isCrossX_FALSE() {
        try {
            boolean actual = ellipseService.isCrossX(ellipse3);
            Assert.assertFalse(actual);
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void isCrossX_INVALID_ELLIPSE() {
        try {
            ellipseService.isCrossX(ellipse4);
            Assert.fail("Expected ServiceException.");
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void isCrossX_NULL() {
        try {
            ellipseService.isCrossX(null);
            Assert.fail("Expected ServiceException.");
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void isCrossY_TRUE() {
        try {
            boolean actual = ellipseService.isCrossY(ellipse1);
            Assert.assertTrue(actual);
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void isCrossY_FALSE() {
        try {
            boolean actual = ellipseService.isCrossY(ellipse3);
            Assert.assertFalse(actual);
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void isCrossY_INVALID_ELLIPSE() {
        try {
            ellipseService.isCrossY(ellipse4);
            Assert.fail("Expected ServiceException.");
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void isCrossY_NULL() {
        try {
            ellipseService.isCrossY(null);
            Assert.fail("Expected ServiceException.");
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }
}