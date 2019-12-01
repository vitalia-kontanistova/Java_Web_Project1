package by.epam.ellipse.service.impl;

import by.epam.ellipse.entity.Ellipse;
import by.epam.ellipse.entity.Parameters;
import by.epam.ellipse.service.ParametersService;
import by.epam.ellipse.service.exception.ServiceException;

public class ParametersServiceImpl implements ParametersService {

    private static ParametersServiceImpl instance = new ParametersServiceImpl();

    private ParametersServiceImpl() {

    }

    public static ParametersServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Parameters createParameters(Ellipse ellipse, EllipseServiceImpl ellipseServiceImpl) throws ServiceException {
        try {
            if (ellipseServiceImpl.isEllipseExist(ellipse, ParametersServiceImpl.getInstance())) {

                Parameters parameters = new Parameters(ellipse);
                parameters.setArea(findArea(ellipse, ellipseServiceImpl));
                parameters.setPerimeter(findPerimeter(ellipse, ellipseServiceImpl));
                parameters.setCircle(isCircle(ellipse, ellipseServiceImpl));
                parameters.setCrossingX(isCrossX(ellipse, ellipseServiceImpl));
                parameters.setCrossingY(isCrossY(ellipse, ellipseServiceImpl));

                return parameters;

            } else throw new ServiceException("Trying to create invalid ellipse.");
        } catch (ServiceException e) {
            throw new ServiceException("ParametersServiceImpl: createParameters(): " + e.getMessage());
        }
    }

    @Override
    public void updateParameters(Parameters parameters, Ellipse ellipse, EllipseServiceImpl ellipseServiceImpl) throws ServiceException {
        try {
            if (ellipseServiceImpl.isEllipseExist(ellipse, ParametersServiceImpl.getInstance())) {

                parameters.setEllipse(ellipse);
                parameters.setArea(findArea(ellipse, ellipseServiceImpl));
                parameters.setPerimeter(findPerimeter(ellipse, ellipseServiceImpl));
                parameters.setCircle(isCircle(ellipse, ellipseServiceImpl));
                parameters.setCrossingX(isCrossX(ellipse, ellipseServiceImpl));
                parameters.setCrossingY(isCrossY(ellipse, ellipseServiceImpl));

            } else throw new ServiceException("Trying to create invalid ellipse.");
        } catch (ServiceException e) {
            throw new ServiceException("ParametersServiceImpl: createParameters(): " + e.getMessage());
        }
    }

    public double findPerimeter(Ellipse ellipse, EllipseServiceImpl ellipseServiceImpl) throws ServiceException {
        try {
            if (ellipseServiceImpl.isEllipseExist(ellipse, ParametersServiceImpl.getInstance())) {
                double halfAxisX = findDeltaX(ellipse) / 2;
                double halfAxisY = findDeltaY(ellipse) / 2;

                return Math.PI * (3 * (halfAxisX + halfAxisY) - Math.sqrt((3 * halfAxisX + halfAxisY) * (halfAxisX + 3 * halfAxisY)));

            } else throw new ServiceException("Trying to createFromFile invalid ellipse.");
        } catch (NullPointerException e) {
            throw new ServiceException("ParametersServiceImpl: findPerimeter(): " + e.getMessage());
        }
    }

    public double findArea(Ellipse ellipse, EllipseServiceImpl ellipseServiceImpl) throws ServiceException {
        try {
            if (ellipseServiceImpl.isEllipseExist(ellipse, ParametersServiceImpl.getInstance())) {
                double halfAxisX = findDeltaX(ellipse) / 2;
                double halfAxisY = findDeltaY(ellipse) / 2;

                return Math.PI * (halfAxisX) * (halfAxisY);

            } else throw new ServiceException("Trying to createFromFile invalid ellipse.");
        } catch (NullPointerException e) {
            throw new ServiceException("ParametersServiceImpl: findArea(): " + e.getMessage());
        }
    }

    public boolean isCircle(Ellipse ellipse, EllipseServiceImpl ellipseServiceImpl) throws ServiceException {
        try {
            if (ellipseServiceImpl.isEllipseExist(ellipse, ParametersServiceImpl.getInstance())) {

                double axisX = findDeltaX(ellipse);
                double axisY = findDeltaY(ellipse);

                return Math.abs(axisX - axisY) < 0.01;
            } else throw new ServiceException("Trying to createFromFile invalid ellipse.");

        } catch (NullPointerException e) {
            throw new ServiceException("EllipseServiceImpl: isCircle(): " + e.getMessage());
        }
    }

    public boolean isCrossX(Ellipse ellipse, EllipseServiceImpl ellipseServiceImpl) throws ServiceException {
        try {
            Ellipse.Point a = ellipse.getPointA();
            Ellipse.Point b = ellipse.getPointB();

            if (ellipseServiceImpl.isEllipseExist(ellipse, ParametersServiceImpl.getInstance())) {
                double xA = a.getX();
                double xB = b.getX();

                return (xA * xB) <= 0;
            } else throw new ServiceException("Trying to createFromFile invalid ellipse.");
        } catch (NullPointerException e) {
            throw new ServiceException("EllipseServiceImpl: isCrossX(): " + e.getMessage());
        }
    }

    public boolean isCrossY(Ellipse ellipse, EllipseServiceImpl ellipseServiceImpl) throws ServiceException {
        try {
            Ellipse.Point a = ellipse.getPointA();
            Ellipse.Point b = ellipse.getPointB();

            if (ellipseServiceImpl.isEllipseExist(ellipse, ParametersServiceImpl.getInstance())) {
                double yA = a.getY();
                double yB = b.getY();

                return (yA * yB) <= 0;
            } else throw new ServiceException("Trying to createFromFile invalid ellipse.");
        } catch (NullPointerException e) {
            throw new ServiceException("EllipseServiceImpl: isCrossY(): " + e.getMessage());
        }
    }

    //package private
    double findDeltaX(Ellipse ellipse) throws NullPointerException {
        Ellipse.Point a = ellipse.getPointA();
        Ellipse.Point b = ellipse.getPointB();

        return Math.abs(a.getX() - b.getX());
    }

    //package private
    double findDeltaY(Ellipse ellipse) throws NullPointerException {
        Ellipse.Point a = ellipse.getPointA();
        Ellipse.Point b = ellipse.getPointB();

        return Math.abs(a.getY() - b.getY());
    }
}