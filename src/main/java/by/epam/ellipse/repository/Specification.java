package by.epam.ellipse.repository;


import by.epam.ellipse.entity.Ellipse;
import by.epam.ellipse.repository.exception.RepositoryException;

import java.util.List;

public interface Specification {
    void remove(List<Ellipse> ellipses, Object identifier) throws RepositoryException;

    List<Ellipse> takeAll(List<Ellipse> ellipses) throws RepositoryException;

    List<Ellipse> takeSome(List<Ellipse> ellipses, Object from, Object till) throws RepositoryException;

    Ellipse takeOne(List<Ellipse> ellipses, Object identifier) throws RepositoryException;
}
