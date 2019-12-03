package by.epam.ellipse.comparator;

import by.epam.ellipse.entity.Ellipse;

import java.util.Comparator;


public class PerimeterComparator implements Comparator<Ellipse> {
    @Override
    public int compare(Ellipse ellipse1, Ellipse ellipse2) {
        return Double.compare(ellipse1.getPerimeter(), ellipse2.getPerimeter());
    }
}