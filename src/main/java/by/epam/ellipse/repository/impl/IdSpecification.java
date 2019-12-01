package by.epam.ellipse.repository.impl;

import by.epam.ellipse.comparator.IdComparator;
import by.epam.ellipse.dao.exception.DAOexception;
import by.epam.ellipse.entity.Ellipse;
import by.epam.ellipse.registrar.EllipseObservable;
import by.epam.ellipse.repository.Specification;

import java.util.ArrayList;
import java.util.List;

public class IdSpecification implements Specification {

    @Override
    public void remove(List<EllipseObservable> base, Object identifier) throws DAOexception {
        if (identifier instanceof Integer) {
            Integer id = (Integer) identifier;
            for (int i = 0; i < base.size(); i++) {

                EllipseObservable ellipseObservable = base.get(i);
                Ellipse currentEllipse = ellipseObservable.getEllipse();

                if (currentEllipse.getId() == id) {
                    base.remove(i);
                    return;
                }
            }
        } else
            throw new DAOexception("IdSpecification: takeSome(): Null of incorrect identifier has been passed into method.");
    }

    @Override
    public List<Ellipse> takeAll(List<EllipseObservable> base) {
        List<Ellipse> ellipses = new ArrayList<>();

        for (EllipseObservable ellipseObservable : base) {
            Ellipse currentEllipse = ellipseObservable.getEllipse();
            ellipses.add(currentEllipse);
        }
        ellipses.sort(new IdComparator());

        return ellipses;
    }

    @Override
    public List<Ellipse> takeSome(List<EllipseObservable> base, Object from, Object till) throws DAOexception {
        if (from instanceof Integer && till instanceof Integer) {
            Integer fromId = (Integer) from;
            Integer tillId = (Integer) till;

            List<Ellipse> result = new ArrayList<>();

            for (EllipseObservable ellipseObservable : base) {
                Ellipse currentEllipse = ellipseObservable.getEllipse();
                if (currentEllipse.getId() <= tillId && currentEllipse.getId() >= fromId) {
                    result.add(currentEllipse);
                }
            }
            return result;
        } else
            throw new DAOexception("IdSpecification: takeSome(): Null of incorrect identifier has been passed into method.");
    }

    @Override
    public Ellipse takeOne(List<EllipseObservable> base, Object identifier) throws DAOexception {
        if (identifier instanceof Integer) {
            Integer id = (Integer) identifier;

            for (EllipseObservable ellipseObservable : base) {
                Ellipse ellipse = ellipseObservable.getEllipse();

                if (ellipse.getId() == id) {
                    return ellipse;
                }
            }
        } else
            throw new DAOexception("IdSpecification: takeOne(): Null of incorrect identifier has been passed into method.");
        return null;
    }
}
