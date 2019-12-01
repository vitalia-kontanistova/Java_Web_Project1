package by.epam.ellipse.comparator;

import by.epam.ellipse.entity.Ellipse;

import java.util.Comparator;

public class IdComparator implements Comparator<Ellipse> {
    @Override
    public int compare(Ellipse ellipse1, Ellipse ellipse2) {
        return Integer.compare(ellipse1.getId(), ellipse2.getId());
    }
}