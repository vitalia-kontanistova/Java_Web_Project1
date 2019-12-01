package by.epam.ellipse.registrar;

import by.epam.ellipse.service.exception.ServiceException;

public interface Observable<T> {
    void add(T item) throws ServiceException;

    void notifyObservers() throws ServiceException;
}