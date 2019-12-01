package by.epam.ellipse.comparator;

import by.epam.ellipse.entity.Parameters;

import java.util.Comparator;

public class AreaComparator implements Comparator<Parameters> {
    @Override
    public int compare(Parameters parameters1, Parameters parameters2) {
        double result = parameters1.getArea() - parameters2.getArea();

        if (result < -0.01) {
            return -1;
        } else if (result > 0.01) {
            return 1;
        } else return 0;
    }
}
