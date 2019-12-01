package by.epam.ellipse.repository;


import by.epam.ellipse.dao.exception.DAOexception;
import by.epam.ellipse.entity.Ellipse;
import by.epam.ellipse.registrar.EllipseObservable;

import java.util.List;

public interface Specification {
    void remove(List<EllipseObservable> base, Object identifier) throws DAOexception;

    List<Ellipse> takeAll(List<EllipseObservable> base);

    List<Ellipse> takeSome(List<EllipseObservable> base, Object from, Object till) throws DAOexception;

    Ellipse takeOne(List<EllipseObservable> base, Object identifier) throws DAOexception;
}
