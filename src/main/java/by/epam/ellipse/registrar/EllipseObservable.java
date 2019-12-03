package by.epam.ellipse.registrar;

import by.epam.ellipse.entity.Ellipse;
import by.epam.ellipse.entity.Point;
import by.epam.ellipse.service.exception.ServiceException;
import by.epam.ellipse.service.EllipseServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class EllipseObservable implements Observable<Observer<Ellipse>> {

    private List<Observer<Ellipse>> observers;
    private Ellipse ellipse;
    private EllipseServiceImpl ellipseService;

    public EllipseObservable() {
        ellipseService = EllipseServiceImpl.getInstance();
        observers = new ArrayList<>();
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
        for (Observer<Ellipse> observer : observers) {
            try {
                observer.update(this.ellipse);
            } catch (ServiceException e) {
                throw new ServiceException("EllipseObservable: notifyObservers(): " + e.getMessage());
            }
        }
    }

    public void setEllipse(Ellipse ellipse) throws ServiceException {
        try {
            if (ellipseService.isEllipseExist(ellipse)) {
                this.ellipse = ellipse;
                ellipseService.updateEllipse(ellipse);
                notifyObservers();
            } else throw new ServiceException("Trying to create invalid ellipse.");
        } catch (ServiceException e) {
            throw new ServiceException("EllipseObservable: setEllipse(): " + e.getMessage());
        }
    }

    public void setPoints(Point pointA, Point pointB) throws ServiceException {
        try {
            if (ellipseService.isEllipseExist(new Ellipse(pointA, pointB))) {
                this.ellipse.setPointA(pointA);
                this.ellipse.setPointB(pointB);
                ellipseService.updateEllipse(ellipse);
                notifyObservers();
            } else throw new ServiceException("Trying to create invalid ellipse.");
        } catch (NullPointerException | ServiceException e) {
            throw new ServiceException("EllipseObservable: setPoints(): " + e.getMessage());
        }
    }

    public void setPointA(Point pointA) throws ServiceException {
        setPoints(pointA, ellipse.getPointB());
    }

    public void setPointB(Point pointB) throws ServiceException {
        setPoints(ellipse.getPointA(), pointB);
    }
}