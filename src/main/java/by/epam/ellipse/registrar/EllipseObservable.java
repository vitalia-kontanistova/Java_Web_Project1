package by.epam.ellipse.registrar;

import by.epam.ellipse.entity.Ellipse;
import by.epam.ellipse.service.exception.ServiceException;
import by.epam.ellipse.service.impl.EllipseServiceImpl;
import by.epam.ellipse.service.impl.ParametersServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class EllipseObservable implements Observable<Observer<Ellipse>> {

    private List<Observer<Ellipse>> observers = new ArrayList<>();
    private Ellipse ellipse;

    public EllipseObservable() {
    }

    @Override
    public void add(Observer<Ellipse> observer) {
        observers.add(observer);
    }

    public Ellipse getEllipse() {
        return this.ellipse;
    }

    @Override
    public void notifyObservers() throws ServiceException {
        for (Observer<Ellipse> o : observers) {
            try {
                o.update(this.ellipse, ParametersServiceImpl.getInstance());
            } catch (ServiceException e) {
                throw new ServiceException("EllipseObservable: notifyObservers(): " + e.getMessage());
            }
        }
    }

    public void setEllipse(Ellipse ellipse, EllipseServiceImpl ellipseService) throws ServiceException {
        try {
            if (ellipseService.isEllipseExist(ellipse, ParametersServiceImpl.getInstance())) {
                this.ellipse = ellipse;
                notifyObservers();
            } else throw new ServiceException("Trying to create invalid ellipse.");
        } catch (ServiceException e) {
            throw new ServiceException("EllipseObservable: setEllipse(): " + e.getMessage());
        }
    }

    public void setPoints(Ellipse.Point pointA, Ellipse.Point pointB, EllipseServiceImpl ellipseService) throws ServiceException {
        try {
            if (ellipseService.isEllipseExist(new Ellipse(pointA, pointB), ParametersServiceImpl.getInstance())) {
                this.ellipse.setPointA(pointA);
                this.ellipse.setPointB(pointB);
                notifyObservers();
            } else throw new ServiceException("Trying to create invalid ellipse.");
        } catch (ServiceException e) {
            throw new ServiceException("EllipseObservable: setPoints(): " + e.getMessage());
        }
    }

    public void setPointA(Ellipse.Point pointA) throws ServiceException {
        setPoints(pointA, ellipse.getPointB(), EllipseServiceImpl.getInstance());
    }

    public void setPointB(Ellipse.Point pointB) throws ServiceException {
        setPoints(ellipse.getPointA(), pointB, EllipseServiceImpl.getInstance());
    }
}