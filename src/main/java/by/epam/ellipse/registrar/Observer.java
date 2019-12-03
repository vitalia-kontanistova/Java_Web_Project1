package by.epam.ellipse.registrar;

import by.epam.ellipse.service.exception.ServiceException;

public interface Observer<T> {
    void update(T item) throws ServiceException;
}