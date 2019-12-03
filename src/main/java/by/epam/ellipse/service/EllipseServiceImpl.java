package by.epam.ellipse.service;

import by.epam.ellipse.dao.exception.DAOexception;
import by.epam.ellipse.entity.Ellipse;
import by.epam.ellipse.entity.Point;
import by.epam.ellipse.service.exception.ServiceException;
import by.epam.ellipse.util.Parser;
import by.epam.ellipse.util.FileManipulator;

import java.util.ArrayList;
import java.util.List;

public class EllipseServiceImpl implements EllipseService {
    private static EllipseServiceImpl instance = new EllipseServiceImpl();

    private EllipseServiceImpl() {
    }

    public static EllipseServiceImpl getInstance() {
        return instance;
    }

    public boolean isEllipseExist(Ellipse ellipse) throws ServiceException {
        try {
            double axisX = findDeltaX(ellipse);
            double axisY = findDeltaY(ellipse);

            return axisX > 0.1 && axisY > 0.1;

        } catch (NullPointerException e) {
            throw new ServiceException("EllipseServiceImpl: isEllipseExist(): null object has been passed into method: " + e.getMessage());
        }
    }

    @Override
    public List<Ellipse> createFromFile(String requestToPropFile, FileManipulator fileManipulator) throws ServiceException {

        try {
            List<Ellipse> ellipses = new ArrayList<>();
            List<String> ellipsesStr = fileManipulator.extractEntriesFromFile(requestToPropFile);

            for (String str : ellipsesStr) {
                Ellipse currentEllipse = createFromString(str, Parser.getInstance());
                if (isEllipseExist(currentEllipse)) {
                    ellipses.add(currentEllipse);
                }
            }

            return ellipses;

        } catch (ServiceException | DAOexception e) {
            throw new ServiceException("EllipseServiceImpl: createFromFile(): " + e.getMessage());
        }
    }

    @Override
    public Ellipse create(Point pointA, Point pointB) throws ServiceException {
        try {
            Ellipse ellipse = new Ellipse(pointA, pointB);
            updateEllipse(ellipse);

            return ellipse;

        } catch (ServiceException e) {
            throw new ServiceException("EllipseServiceImpl: createParameters(): " + e.getMessage());
        }
    }

    @Override
    public void updateEllipse(Ellipse ellipse) throws ServiceException {
        try {
            if (isEllipseExist(ellipse)) {

                double area = findArea(ellipse);
                double perimeter = findPerimeter(ellipse);
                boolean circle = isCircle(ellipse);
                boolean crossingX = isCrossX(ellipse);
                boolean crossingY = isCrossY(ellipse);

                ellipse.setArea(area);
                ellipse.setPerimeter(perimeter);
                ellipse.setCircle(circle);
                ellipse.setCrossingX(crossingX);
                ellipse.setCrossingY(crossingY);
            }
        } catch (ServiceException e) {
            throw new ServiceException("EllipseServiceImpl: createParameters(): " + e.getMessage());
        }
    }

    private Ellipse createFromString(String ellipseStr, Parser parser) throws ServiceException {
        try {
            double[] coordinates = parser.parseCoordinates(ellipseStr);
            Point pointA = new Point(coordinates[0], coordinates[1]);
            Point pointB = new Point(coordinates[2], coordinates[3]);

            return create(pointA, pointB);

        } catch (DAOexception e) {
            throw new ServiceException("EllipseServiceImpl: createFromString(): " + e.getMessage());
        }
    }

    public double findPerimeter(Ellipse ellipse) throws ServiceException {
        try {
            if (isEllipseExist(ellipse)) {
                double halfAxisX = findDeltaX(ellipse) / 2;
                double halfAxisY = findDeltaY(ellipse) / 2;

                return Math.PI * (3 * (halfAxisX + halfAxisY) - Math.sqrt((3 * halfAxisX + halfAxisY) * (halfAxisX + 3 * halfAxisY)));

            } else throw new ServiceException("Trying to createFromFile invalid ellipse.");
        } catch (NullPointerException e) {
            throw new ServiceException("EllipseServiceImpl: findPerimeter(): " + e.getMessage());
        }
    }

    public double findArea(Ellipse ellipse) throws ServiceException {
        try {
            if (isEllipseExist(ellipse)) {
                double halfAxisX = findDeltaX(ellipse) / 2;
                double halfAxisY = findDeltaY(ellipse) / 2;

                return Math.PI * (halfAxisX) * (halfAxisY);

            } else throw new ServiceException("Trying to createFromFile invalid ellipse.");
        } catch (NullPointerException e) {
            throw new ServiceException("EllipseServiceImpl: findArea(): " + e.getMessage());
        }
    }

    public boolean isCircle(Ellipse ellipse) throws ServiceException {
        try {
            if (isEllipseExist(ellipse)) {

                double axisX = findDeltaX(ellipse);
                double axisY = findDeltaY(ellipse);

                return Math.abs(axisX - axisY) < 0.01;
            } else throw new ServiceException("Trying to createFromFile invalid ellipse.");

        } catch (NullPointerException e) {
            throw new ServiceException("EllipseServiceImpl: isCircle(): " + e.getMessage());
        }
    }

    public boolean isCrossX(Ellipse ellipse) throws ServiceException {
        try {
            Point a = ellipse.getPointA();
            Point b = ellipse.getPointB();

            if (isEllipseExist(ellipse)) {
                double xA = a.getX();
                double xB = b.getX();

                return (xA * xB) <= 0;
            } else throw new ServiceException("Trying to createFromFile invalid ellipse.");
        } catch (NullPointerException e) {
            throw new ServiceException("EllipseServiceImpl: isCrossX(): " + e.getMessage());
        }
    }

    public boolean isCrossY(Ellipse ellipse) throws ServiceException {
        try {
            Point a = ellipse.getPointA();
            Point b = ellipse.getPointB();

            if (isEllipseExist(ellipse)) {
                double yA = a.getY();
                double yB = b.getY();

                return (yA * yB) <= 0;
            } else throw new ServiceException("Trying to createFromFile invalid ellipse.");
        } catch (NullPointerException e) {
            throw new ServiceException("EllipseServiceImpl: isCrossY(): " + e.getMessage());
        }
    }

    private double findDeltaX(Ellipse ellipse) throws NullPointerException {
        Point a = ellipse.getPointA();
        Point b = ellipse.getPointB();

        return Math.abs(a.getX() - b.getX());
    }

    private double findDeltaY(Ellipse ellipse) throws NullPointerException {
        Point a = ellipse.getPointA();
        Point b = ellipse.getPointB();

        return Math.abs(a.getY() - b.getY());
    }
}