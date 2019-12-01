package by.epam.ellipse.repository;

import by.epam.ellipse.entity.Ellipse;
import by.epam.ellipse.repository.exception.RepositoryException;
import by.epam.ellipse.util.IdGenerator;

import java.util.List;

public interface Repository {
    void add(Ellipse ellipse, IdGenerator idGenerator) throws RepositoryException;

    void update(Ellipse ellipse);

    void remove(Specification specification, Object identifier) throws RepositoryException;

    List<Ellipse> takeAll(Specification specification);

    List<Ellipse> takeSome(Specification specification, Object from, Object till) throws RepositoryException;

    Ellipse takeOne(Specification specification, Object identifier) throws RepositoryException;
}