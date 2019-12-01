package by.epam.ellipse;


import by.epam.ellipse.entity.Ellipse;
import by.epam.ellipse.entity.Parameters;
import by.epam.ellipse.registrar.EllipseObservable;
import by.epam.ellipse.registrar.ParametersObserver;
import by.epam.ellipse.service.exception.ServiceException;
import by.epam.ellipse.service.impl.EllipseServiceImpl;

public class Main {
    public static void main(String[] args) throws ServiceException {

        Ellipse.Point p1 = new Ellipse.Point(0, 0);
        Ellipse.Point p2 = new Ellipse.Point(10, 10);
        Ellipse.Point p3 = new Ellipse.Point(-5, -3);
        Ellipse.Point p4 = new Ellipse.Point(15, 7);

        Ellipse e1 = new Ellipse(p1, p2);
        Ellipse e2 = new Ellipse(p3, p4);

        EllipseObservable eOb = new EllipseObservable();
        ParametersObserver pOb = new ParametersObserver(eOb);

        eOb.setEllipse(e1, EllipseServiceImpl.getInstance());
        Parameters p = pOb.getParameters();

        System.out.println(p);

        eOb.setEllipse(e2, EllipseServiceImpl.getInstance());
        System.out.println(p);

        eOb.setPoints(p1, p2, EllipseServiceImpl.getInstance());
        System.out.println(p);

        eOb.setPointA(p3);
        System.out.println(p);

        eOb.setPointB(p4);
        System.out.println(p);
    }
}