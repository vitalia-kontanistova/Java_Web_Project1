package by.epam.ellipse.service;

import by.epam.ellipse.entity.Ellipse;
import by.epam.ellipse.entity.Parameters;
import by.epam.ellipse.service.exception.ServiceException;
import by.epam.ellipse.service.impl.EllipseServiceImpl;

public interface ParametersService {
    Parameters createParameters(Ellipse ellipse, EllipseServiceImpl ellipseServiceImpl) throws ServiceException;

    void updateParameters(Parameters parameters, Ellipse ellipse, EllipseServiceImpl ellipseServiceImpl) throws ServiceException;
}
