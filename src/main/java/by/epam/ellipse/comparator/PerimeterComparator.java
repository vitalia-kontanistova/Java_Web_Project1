package by.epam.ellipse.comparator;

import by.epam.ellipse.entity.Parameters;

import java.util.Comparator;

public class PerimeterComparator implements Comparator<Parameters> {
    @Override
    public int compare(Parameters parameters1, Parameters parameters2) {
        double result = parameters1.getPerimeter() - parameters2.getPerimeter();

        if (result < -0.01) {
            return -1;
        } else if (result > 0.01) {
            return 1;
        } else return 0;
    }
}