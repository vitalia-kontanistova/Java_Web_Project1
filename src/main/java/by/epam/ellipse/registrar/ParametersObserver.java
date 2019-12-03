package by.epam.ellipse.registrar;

import by.epam.ellipse.entity.Ellipse;
import by.epam.ellipse.service.exception.ServiceException;
import by.epam.ellipse.service.EllipseServiceImpl;


public class ParametersObserver implements Observer<Ellipse> {

    private double area;
    private double perimeter;
    private EllipseServiceImpl ellipseService;

    public ParametersObserver(EllipseObservable ellipseObservable) {
        ellipseService = EllipseServiceImpl.getInstance();
        ellipseObservable.add(this);
    }

    public double getArea() {
        return this.area;
    }

    public double getPerimeter() {
        return this.perimeter;
    }

    @Override
    public void update(Ellipse ellipse) throws ServiceException {
        try {
            area = ellipseService.findArea(ellipse);
            perimeter = ellipseService.findPerimeter(ellipse);

        } catch (NullPointerException | ServiceException e) {
            throw new ServiceException("ParametersObserver: updateParameters(): " + e.getMessage());
        }
    }
}