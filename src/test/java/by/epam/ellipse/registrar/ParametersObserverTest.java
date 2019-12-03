package by.epam.ellipse.registrar;

import by.epam.ellipse.entity.Ellipse;
import by.epam.ellipse.entity.Point;
import by.epam.ellipse.service.exception.ServiceException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParametersObserverTest {

    private EllipseObservable ellipses;
    private ParametersObserver parameters;
    private Point pointA;
    private Point pointB;
    private Ellipse ellipse1;

    @Before
    public void setUp() {
        pointA = new Point(-1.0, -1.0);
        pointB = new Point(1.0, 1.0);
        ellipse1 = new Ellipse(pointA, pointB);

        ellipses = new EllipseObservable();
        parameters = new ParametersObserver(ellipses);
    }

    @After
    public void shutDown() {
        ellipses = null;
        parameters = null;

        pointA = null;
        pointB = null;
        ellipse1 = null;
    }

    @Test
    public void getArea() {
        try {
            ellipses.setEllipse(ellipse1);
            double actual = parameters.getArea();
            double expected = 3.15;

            Assert.assertEquals(expected, actual, 0.01);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getPerimeter() {
        try {
            ellipses.setEllipse(ellipse1);
            double actual = parameters.getPerimeter();
            double expected = 6.28;

            Assert.assertEquals(expected, actual, 0.01);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}