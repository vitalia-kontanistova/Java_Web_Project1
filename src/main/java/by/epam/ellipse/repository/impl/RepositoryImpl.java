package by.epam.ellipse.repository.impl;

import by.epam.ellipse.entity.Ellipse;
import by.epam.ellipse.registrar.EllipseObservable;
import by.epam.ellipse.registrar.ParametersObserver;
import by.epam.ellipse.dao.exception.DAOexception;
import by.epam.ellipse.repository.Repository;
import by.epam.ellipse.repository.Specification;
import by.epam.ellipse.repository.exception.RepositoryException;
import by.epam.ellipse.service.exception.ServiceException;
import by.epam.ellipse.service.impl.EllipseServiceImpl;
import by.epam.ellipse.util.IdGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepositoryImpl implements Repository {

    private List<EllipseObservable> ellipses;
    private Map<Integer, ParametersObserver> ellipseParameters;


    public RepositoryImpl() {
        ellipses = new ArrayList<>();
        ellipseParameters = new HashMap<>();
    }

    @Override
    public void add(Ellipse ellipse, IdGenerator idGenerator) throws RepositoryException {
        try {
            int id = idGenerator.generate(ellipses);
            ellipse.setId(id);

            EllipseObservable ellipseObservable = new EllipseObservable();
            ParametersObserver paramObserver = new ParametersObserver(ellipseObservable);
            ellipseObservable.setEllipse(ellipse, EllipseServiceImpl.getInstance());


            ellipses.add(ellipseObservable);
            ellipseParameters.put(id, paramObserver);

        } catch (NullPointerException | ServiceException e) {
            throw new RepositoryException("RepositoryImpl: add(): " + e.getMessage());
            //запись в лог
        }
    }

    @Override
    public void update(Ellipse item) {
    }

    @Override
    public void remove(Specification specification, Object identifier) throws RepositoryException {
        try {
            specification.remove(ellipses, identifier);
        } catch (DAOexception e) {
            throw new RepositoryException("RepositoryImpl: remove(): " + e.getMessage());
            //запись в лог
        }
        coordinateRemoving();
    }

    @Override
    public List<Ellipse> takeAll(Specification specification) {
        return specification.takeAll(ellipses);
    }


    @Override
    public List<Ellipse> takeSome(Specification specification, Object from, Object till) throws RepositoryException {
        try {
            return specification.takeSome(ellipses, from, till);
        } catch (DAOexception e) {
            throw new RepositoryException("RepositoryImpl: takeSome(): " + e.getMessage());
            //запись в лог
        }
    }

    @Override
    public Ellipse takeOne(Specification specification, Object identifier) throws RepositoryException {
        try {
            return specification.takeOne(ellipses, identifier);
        } catch (DAOexception e) {
            throw new RepositoryException("RepositoryImpl: takeSome(): " + e.getMessage());
            //запись в лог
        }
    }

    private void coordinateRemoving() {
        List<Integer> idBase = new ArrayList<>(ellipseParameters.keySet());

        for (int currentId : idBase) {
            boolean missingId = true;
            for (EllipseObservable ellipseObservable : ellipses) {
                Ellipse currentEllipse = ellipseObservable.getEllipse();
                if (currentEllipse.getId() == currentId) {
                    missingId = false;
                }
            }
            if (missingId) {
                ellipseParameters.remove(currentId);
            }
        }
    }
}