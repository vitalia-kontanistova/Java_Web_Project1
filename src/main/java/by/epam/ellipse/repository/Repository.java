package by.epam.ellipse.repository;

import by.epam.ellipse.entity.Ellipse;
import by.epam.ellipse.repository.exception.RepositoryException;

import java.util.List;

public interface Repository {
    void add(Ellipse ellipse) throws RepositoryException;

    void update(Ellipse ellipse);

    void remove(Specification specification, Object identifier) throws RepositoryException;

    List<Ellipse> takeAll(Specification specification) throws RepositoryException;

    List<Ellipse> takeSome(Specification specification, Object from, Object till) throws RepositoryException;

    Ellipse takeOne(Specification specification, Object identifier) throws RepositoryException;
}