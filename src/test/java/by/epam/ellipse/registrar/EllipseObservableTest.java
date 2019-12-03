package by.epam.ellipse.registrar;

import by.epam.ellipse.entity.Ellipse;
import by.epam.ellipse.entity.Point;
import by.epam.ellipse.service.EllipseServiceImpl;
import by.epam.ellipse.service.exception.ServiceException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class EllipseObservableTest {
    private EllipseObservable ellipseObservable;
    private EllipseServiceImpl ellipseService;

    private Point point1;
    private Point point2;
    private Point point3;
    private Point point4;

    private Ellipse ellipse1;
    private Ellipse ellipse2;
    private Ellipse ellipse3;
    private Ellipse ellipse4;
    private Ellipse ellipse5;
    private Ellipse ellipse6;

    @Before
    public void setUp() throws ServiceException {
        ellipseService = EllipseServiceImpl.getInstance();

        point1 = new Point(-1.0, -1.0);
        point2 = new Point(1.0, 1.0);
        point3 = new Point(10.0, 11.0);
        point4 = new Point(-10.0, -11.0);

        ellipse1 = new Ellipse(point1, point2);
        ellipseService.updateEllipse(ellipse1);
        ellipse2 = new Ellipse(point3, point4);
        ellipseService.updateEllipse(ellipse2);
        ellipse3 = new Ellipse(null, point4);
        ellipse4 = new Ellipse(point3, null);
        ellipse5 = new Ellipse(null, null);
        ellipse6 = new Ellipse(point1, point1);

        ellipseObservable = new EllipseObservable();
        ellipseObservable.setEllipse(ellipse1);
    }

    @After
    public void shutDown() {
        ellipseObservable = null;
        ellipseService = null;

        point1 = null;
        point2 = null;
        point3 = null;
        point4 = null;

        ellipse1 = null;
        ellipse2 = null;
        ellipse3 = null;
        ellipse4 = null;
        ellipse5 = null;
        ellipse6 = null;
    }


    @Test
    public void setEllipse() {
        Ellipse actual;
        Ellipse expected;

        try {
            ellipseObservable.setEllipse(ellipse2);
            actual = ellipseObservable.getEllipse();
            expected = ellipse2;
            Assert.assertEquals(expected, actual);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void setEllipse_NULL_POINT_A() {
        try {
            ellipseObservable.setEllipse(ellipse3);
            Assert.fail("Expected ServiceException");
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void setEllipse_NULL_POINT_B() {
        try {
            ellipseObservable.setEllipse(ellipse4);
            Assert.fail("Expected ServiceException");
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void setEllipse_NULL_POINTS() {
        try {
            ellipseObservable.setEllipse(ellipse5);
            Assert.fail("Expected ServiceException");
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void setEllipse_NULL() {
        try {
            ellipseObservable.setEllipse(null);
            Assert.fail("Expected ServiceException");
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void setEllipse_INVALID() {
        try {
            ellipseObservable.setEllipse(ellipse6);
            Assert.fail("Expected ServiceException");
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void setPoints() {
        try {
            ellipseObservable.setPoints(point3, point4);
            Ellipse actual = ellipseObservable.getEllipse();
            Ellipse expected = ellipse2;
            Assert.assertEquals(expected, actual);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void setPoints_NULL_POINT_A() {
        try {
            ellipseObservable.setPoints(null, point1);
            Assert.fail("Expected ServiceException");
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void setPoints_NULL_POINT_B() {
        try {
            ellipseObservable.setPoints(point1, null);
            Assert.fail("Expected ServiceException");
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void setPoints_NULL_POINTS() {
        try {
            ellipseObservable.setPoints(null, null);
            Assert.fail("Expected ServiceException");
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void setPoints_INVALID() {
        try {
            ellipseObservable.setPoints(point1, point1);
            Assert.fail("Expected ServiceException");
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void setPointA() {
        try {
            ellipseObservable.setPointA(point3);
            Ellipse actual = ellipseObservable.getEllipse();
            Ellipse expected = new Ellipse(point3, point2);
            ellipseService.updateEllipse(expected);

            Assert.assertEquals(expected, actual);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void setPointA_NULL_POINT() {
        try {
            ellipseObservable.setPointA(null);
            Assert.fail("Expected ServiceException");
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void setPointA_INVALID() {
        try {
            ellipseObservable.setPointA(point2);
            Assert.fail("Expected ServiceException");
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void setPointB() {
        try {
            ellipseObservable.setPointB(point3);
            Ellipse actual = ellipseObservable.getEllipse();
            Ellipse expected = new Ellipse(point1, point3);
            ellipseService.updateEllipse(expected);

            Assert.assertEquals(expected, actual);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void setPointB_NULL_POINT() {
        try {
            ellipseObservable.setPointB(null);
            Assert.fail("Expected ServiceException");
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void setPointB_INVALID() {
        try {
            ellipseObservable.setPointB(point1);
            Assert.fail("Expected ServiceException");
        } catch (ServiceException e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }
}