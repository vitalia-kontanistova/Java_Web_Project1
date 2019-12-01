package by.epam.ellipse.registrar;

import by.epam.ellipse.service.exception.ServiceException;
import by.epam.ellipse.service.impl.ParametersServiceImpl;

public interface Observer<T> {
    void update(T item, ParametersServiceImpl parametersService) throws ServiceException;
}