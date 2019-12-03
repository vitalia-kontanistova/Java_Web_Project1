package by.epam.ellipse.repository.impl;

import by.epam.ellipse.comparator.IdComparator;
import by.epam.ellipse.entity.Ellipse;
import by.epam.ellipse.repository.Specification;
import by.epam.ellipse.repository.exception.RepositoryException;

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
    public List<Ellipse> takeAll(List<Ellipse> ellipses) throws RepositoryException {
        try {
            List<Ellipse> newEllipses = new ArrayList<>();
            for (Ellipse ellipse : ellipses) {
                newEllipses.add(ellipse.clone());
            }

            newEllipses.sort(new IdComparator());

            return ellipses;
        } catch (NullPointerException | CloneNotSupportedException e) {
            throw new RepositoryException("IdSpecification: takeAll(): " + e.getMessage());
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
