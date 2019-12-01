package by.epam.ellipse.service;

import by.epam.ellipse.entity.Ellipse;
import by.epam.ellipse.service.exception.ServiceException;
import by.epam.ellipse.util.FileManipulator;

import java.util.List;

public interface EllipseService {
    List<Ellipse> createFromFile(String requestToPropFile, FileManipulator fileManipulator) throws ServiceException;
}
