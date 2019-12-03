package by.epam.ellipse.repository.impl;

import by.epam.ellipse.comparator.AreaComparator;
import by.epam.ellipse.entity.Ellipse;
import by.epam.ellipse.entity.Point;
import by.epam.ellipse.repository.Specification;
import by.epam.ellipse.repository.exception.RepositoryException;
import by.epam.ellipse.service.EllipseServiceImpl;
import by.epam.ellipse.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class AreaSpecification implements Specification {

    @Override
    public void remove(List<Ellipse> ellipses, Object identifier) throws RepositoryException {
        if (identifier instanceof Double) {
            Double area = (Double) identifier;

            for (int i = 0; i < ellipses.size(); i++) {
                Ellipse currentEllipse = ellipses.get(i);
                if (currentEllipse.getArea() == area) {
                    ellipses.remove(i);
                    return;
                }
            }
        } else
            throw new RepositoryException("AreaSpecification: takeSome(): Null of incorrect identifier has been passed into method.");
    }

    @Override
    public List<Ellipse> sort(List<Ellipse> ellipses) throws RepositoryException {
        try {
            List<Ellipse> newEllipses = new ArrayList<>();
            for (Ellipse ellipse : ellipses) {
                newEllipses.add(ellipse.clone());
            }

            newEllipses.sort(new AreaComparator());

            return ellipses;
        } catch (NullPointerException | CloneNotSupportedException e) {
            throw new RepositoryException("AreaSpecification: sort(): " + e.getMessage());
        }
    }

    @Override
    public void update(List<Ellipse> ellipses, Object identifier, Point pointA, Point pointB) throws RepositoryException {
        try {
            if (identifier instanceof Double) {
                Double area = (Double) identifier;

                for (int i = 0; i < ellipses.size(); i++) {
                    Ellipse currentEllipse = ellipses.get(i);
                    if (currentEllipse.getArea() - area < 0.01) {
                        currentEllipse.setPointA(pointA);
                        currentEllipse.setPointB(pointB);
                        EllipseServiceImpl.getInstance().updateEllipse(currentEllipse);
                        return;
                    }
                }
            }
        } catch (NullPointerException | ServiceException e) {
            throw new RepositoryException("AreaSpecification: update(): " + e.getMessage());
        }
    }

    @Override
    public List<Ellipse> takeSome(List<Ellipse> ellipses, Object from, Object till) throws RepositoryException {
        try {
            if (from instanceof Double && till instanceof Double) {
                Double fromArea = (Double) from;
                Double tillArea = (Double) till;

                List<Ellipse> result = new ArrayList<>();

                for (Ellipse currentEllipse : ellipses) {
                    if (currentEllipse.getArea() <= tillArea && currentEllipse.getArea() >= fromArea) {
                        result.add(currentEllipse.clone());
                    }
                }
                return result;
            } else
                throw new RepositoryException("AreaSpecification: takeSome(): Incorrect identifiers have been passed into method.");
        } catch (NullPointerException | CloneNotSupportedException e) {
            throw new RepositoryException("AreaSpecification: takeSome(): " + e.getMessage());
        }
    }

    @Override
    public Ellipse takeOne(List<Ellipse> ellipses, Object identifier) throws RepositoryException {
        try {
            if (identifier instanceof Double) {
                Double area = (Double) identifier;

                Ellipse result = null;
                for (Ellipse ellipse : ellipses) {
                    if (ellipse.getArea() == area) {
                        result = ellipse.clone();
                    }
                }
                return result;
            } else
                throw new RepositoryException("AreaSpecification: takeOne(): Null of incorrect identifier has been passed into method.");
        } catch (NullPointerException | CloneNotSupportedException e) {
            throw new RepositoryException("AreaSpecification: takeSome(): " + e.getMessage());
        }
    }
}