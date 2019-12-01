package by.epam.ellipse.service.impl;

import by.epam.ellipse.dao.exception.DAOexception;
import by.epam.ellipse.entity.Ellipse;
import by.epam.ellipse.service.EllipseService;
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

    @Override
    public List<Ellipse> createFromFile(String requestToPropFile, FileManipulator fileManipulator) throws ServiceException {

        try {
            List<Ellipse> ellipses = new ArrayList<>();
            List<String> ellipsesStr = fileManipulator.extractEntriesFromFile(requestToPropFile);

            for (String s : ellipsesStr) {
                Ellipse temp = createFromString(s, Parser.getInstance());
                if (isEllipseExist(temp, ParametersServiceImpl.getInstance())) {
                    ellipses.add(temp);
                }
            }

            return ellipses;

        } catch (ServiceException | DAOexception e) {
            throw new ServiceException("EllipseServiceImpl: createFromFile(): " + e.getMessage());
        }
    }

    public boolean isEllipseExist(Ellipse ellipse, ParametersServiceImpl parametersService) throws ServiceException {
        try {
            double axisX = parametersService.findDeltaX(ellipse);
            double axisY = parametersService.findDeltaY(ellipse);

            return axisX > 0.1 && axisY > 0.1;

        } catch (NullPointerException e) {
            throw new ServiceException("EllipseServiceImpl: isEllipseExist(): null object has been passed into method: " + e.getMessage());
        }
    }

    private Ellipse createFromString(String ellipseStr, Parser parser) throws ServiceException {
        try {
            double[] coordinates = parser.parseCoordinates(ellipseStr);
            Ellipse.Point pointA = new Ellipse.Point(coordinates[0], coordinates[1]);
            Ellipse.Point pointB = new Ellipse.Point(coordinates[2], coordinates[3]);

            return new Ellipse(pointA, pointB);

        } catch (DAOexception e) {
            throw new ServiceException("EllipseServiceImpl: createFromString(): " + e.getMessage());
        }
    }
}