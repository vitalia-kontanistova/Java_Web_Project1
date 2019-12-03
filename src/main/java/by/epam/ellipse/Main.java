package by.epam.ellipse;


import by.epam.ellipse.entity.Ellipse;
import by.epam.ellipse.entity.Point;
import by.epam.ellipse.registrar.EllipseObservable;
import by.epam.ellipse.registrar.ParametersObserver;
import by.epam.ellipse.service.exception.ServiceException;

public class Main {
    public static void main(String[] args) throws ServiceException {

        Point p1 = new Point(0, 0);
        Point p2 = new Point(10, 10);
        Point p3 = new Point(-5, -3);
        Point p4 = new Point(15, 7);

        Ellipse e1 = new Ellipse(p1, p2);
        Ellipse e2 = new Ellipse(p3, p4);

        EllipseObservable eOb = new EllipseObservable();
        ParametersObserver pOb = new ParametersObserver(eOb);

        eOb.setEllipse(e1);
        Ellipse ellipse = eOb.getEllipse();
        double area = pOb.getArea();
        double perimeter = pOb.getPerimeter();

        System.out.println(ellipse);
        System.out.println("area=" + area + ", perimeter=" + perimeter);

        eOb.setEllipse(e2);
        ellipse = eOb.getEllipse();
        area = pOb.getArea();
        perimeter = pOb.getPerimeter();

        System.out.println(ellipse);
        System.out.println("area=" + area + ", perimeter=" + perimeter);

        eOb.setPoints(p1, p2);
        ellipse = eOb.getEllipse();
        area = pOb.getArea();
        perimeter = pOb.getPerimeter();
        System.out.println(ellipse);
        System.out.println("area=" + area + ", perimeter=" + perimeter);

        eOb.setPointA(p3);
        ellipse = eOb.getEllipse();
        area = pOb.getArea();
        perimeter = pOb.getPerimeter();
        System.out.println(ellipse);
        System.out.println("area=" + area + ", perimeter=" + perimeter);

        eOb.setPointB(p4);
        ellipse = eOb.getEllipse();
        area = pOb.getArea();
        perimeter = pOb.getPerimeter();
        System.out.println(ellipse);
        System.out.println("area=" + area + ", perimeter=" + perimeter);
    }
}