package by.epam.ellipse.service;

import by.epam.ellipse.entity.Ellipse;
import by.epam.ellipse.entity.Point;
import by.epam.ellipse.service.exception.ServiceException;

import java.util.List;

public interface EllipseService {
    List<Ellipse> createFromFile(String requestToPropFile) throws ServiceException;

    Ellipse create(Point pointA, Point pointB) throws ServiceException;

    void updateEllipse(Ellipse ellipse) throws ServiceException;

}
