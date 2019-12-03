package by.epam.ellipse.repository.impl;

import by.epam.ellipse.comparator.IdComparator;
import by.epam.ellipse.entity.Ellipse;
import by.epam.ellipse.entity.Point;
import by.epam.ellipse.repository.Specification;
import by.epam.ellipse.repository.exception.RepositoryException;
import by.epam.ellipse.service.EllipseServiceImpl;
import by.epam.ellipse.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class IdSpecification implements Specification {

    @Override
    public void remove(List<Ellipse> ellipses, Object identifier) throws RepositoryException {
        if (identifier instanceof Integer) {
            Integer id = (Integer) identifier;

            for (int i = 0; i < ellipses.size(); i++) {
                Ellipse currentEllipse = ellipses.get(i);
                if (currentEllipse.getId() == id) {
                    ellipses.remove(i);
                    return;
                }
            }
        } else
            throw new RepositoryException("IdSpecification: takeSome(): Null of incorrect identifier has been passed into method.");
    }

    @Override
    public void update(List<Ellipse> ellipses, Object identifier, Point pointA, Point pointB) throws RepositoryException {
        try {
            if (identifier instanceof Integer) {
                Integer id = (Integer) identifier;

                for (int i = 0; i < ellipses.size(); i++) {
                    Ellipse currentEllipse = ellipses.get(i);
                    if (currentEllipse.getId() == id) {
                        currentEllipse.setPointA(pointA);
                        currentEllipse.setPointB(pointB);
                        EllipseServiceImpl.getInstance().updateEllipse(currentEllipse);
                    }
                }
            }
        } catch (NullPointerException | ServiceException e) {
            throw new RepositoryException("IdSpecification: update(): " + e.getMessage());
        }
    }

    @Override
    public List<Ellipse> sort(List<Ellipse> ellipses) throws RepositoryException {
        try {
            List<Ellipse> newEllipses = new ArrayList<>();
            for (Ellipse ellipse : ellipses) {
                newEllipses.add(ellipse.clone());
            }

            newEllipses.sort(new IdComparator());

            return ellipses;
        } catch (NullPointerException | CloneNotSupportedException e) {
            throw new RepositoryException("IdSpecification: sort(): " + e.getMessage());
        }
    }


    @Override
    public List<Ellipse> takeSome(List<Ellipse> ellipses, Object from, Object till) throws RepositoryException {
        try {
            if (from instanceof Integer && till instanceof Integer) {
                Integer fromId = (Integer) from;
                Integer tillId = (Integer) till;

                List<Ellipse> result = new ArrayList<>();

                for (Ellipse currentEllipse : ellipses) {
                    if (currentEllipse.getId() <= tillId && currentEllipse.getId() >= fromId) {
                        result.add(currentEllipse.clone());
                    }
                }
                return result;
            } else
                throw new RepositoryException("IdSpecification: takeSome(): Incorrect identifiers have been passed into method.");
        } catch (NullPointerException | CloneNotSupportedException e) {
            throw new RepositoryException("IdSpecification: takeSome(): " + e.getMessage());
        }
    }

    @Override
    public Ellipse takeOne(List<Ellipse> ellipses, Object identifier) throws RepositoryException {
        try {
            if (identifier instanceof Integer) {
                Integer id = (Integer) identifier;

                Ellipse result = null;
                for (Ellipse ellipse : ellipses) {
                    if (ellipse.getId() == id) {
                        result = ellipse.clone();
                    }
                }
                return result;
            } else
                throw new RepositoryException("IdSpecification: takeOne(): Null of incorrect identifier has been passed into method.");
        } catch (NullPointerException | CloneNotSupportedException e) {
            throw new RepositoryException("IdSpecification: takeSome(): " + e.getMessage());
        }
    }
}
