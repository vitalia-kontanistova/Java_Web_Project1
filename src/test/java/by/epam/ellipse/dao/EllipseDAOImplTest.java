package by.epam.ellipse.dao;

import by.epam.ellipse.dao.exception.DAOexception;
import by.epam.ellipse.entity.Ellipse;
import by.epam.ellipse.entity.Point;
import by.epam.ellipse.service.EllipseServiceImpl;
import by.epam.ellipse.service.exception.ServiceException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class EllipseDAOImplTest {

    private static EllipseDAOImpl ellipseDAO;
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
        ellipseDAO = EllipseDAOImpl.getInstance();
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
        ellipseDAO = null;

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
            actual = ellipseDAO.createFromFile("test.ellipse.base");
            Assert.assertEquals(actual, expected);
        } catch (DAOexception e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void createFromFile_WRONG_REQUEST() {
        try {
            ellipseDAO.createFromFile("xdgf");
            Assert.fail("Expected ServiceException.");
        } catch (DAOexception e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void createFromFile_EMPTY_REQUEST() {
        try {
            ellipseDAO.createFromFile("");
            Assert.fail("Expected ServiceException.");
        } catch (DAOexception e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void createFromFile_NULL() {
        try {
            ellipseDAO.createFromFile(null);
            Assert.fail(null);
        } catch (DAOexception e) {
            Assert.assertNotEquals("", e.getMessage());
        }
    }
}