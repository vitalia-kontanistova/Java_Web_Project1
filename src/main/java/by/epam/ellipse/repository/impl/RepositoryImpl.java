package by.epam.ellipse.repository.impl;

import by.epam.ellipse.entity.Ellipse;
import by.epam.ellipse.entity.Point;
import by.epam.ellipse.repository.Repository;
import by.epam.ellipse.repository.Specification;
import by.epam.ellipse.repository.exception.RepositoryException;
import by.epam.ellipse.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class RepositoryImpl implements Repository {

    private List<Ellipse> ellipses;
    private IdGenerator idGenerator;


    public RepositoryImpl() {
        ellipses = new ArrayList<>();
        idGenerator = IdGenerator.getInstance();
    }

    @Override
    public void add(Ellipse ellipse) throws RepositoryException {
        try {
            int id = idGenerator.generate(ellipses);
            ellipse.setId(id);

            ellipses.add(ellipse);

        } catch (NullPointerException e) {
            throw new RepositoryException("RepositoryImpl: add(): " + e.getMessage());
            //запись в лог
        }
    }


    @Override
    public void update(Specification specification, Object identifier, Point pointA, Point pointB) throws RepositoryException {
        try {
            specification.update(ellipses, identifier, pointA, pointB);
        } catch (RepositoryException e) {
            throw new RepositoryException("RepositoryImpl: remove(): " + e.getMessage());
            //запись в лог
        }

    }

    @Override
    public void remove(Specification specification, Object identifier) throws RepositoryException {
        try {
            specification.remove(ellipses, identifier);
        } catch (RepositoryException e) {
            throw new RepositoryException("RepositoryImpl: remove(): " + e.getMessage());
            //запись в лог
        }
    }

    @Override
    public List<Ellipse> takeAll(Specification specification) throws RepositoryException {
        try {
            return specification.sort(ellipses);
        } catch (RepositoryException e) {
            throw new RepositoryException("RepositoryImpl: sort(): " + e.getMessage());
            //запись в лог
        }
    }


    @Override
    public List<Ellipse> takeSome(Specification specification, Object from, Object till) throws RepositoryException {
        try {
            return specification.takeSome(ellipses, from, till);
        } catch (RepositoryException e) {
            throw new RepositoryException("RepositoryImpl: takeSome(): " + e.getMessage());
            //запись в лог
        }
    }

    @Override
    public Ellipse takeOne(Specification specification, Object identifier) throws RepositoryException {
        try {
            return specification.takeOne(ellipses, identifier);
        } catch (RepositoryException e) {
            throw new RepositoryException("RepositoryImpl: takeOne(): " + e.getMessage());
            //запись в лог
        }
    }
}