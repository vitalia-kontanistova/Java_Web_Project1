package by.epam.ellipse.util;

import by.epam.ellipse.entity.Ellipse;

import java.util.List;

public class IdGenerator {

    private static IdGenerator instance = new IdGenerator();

    private IdGenerator() {
    }

    public static IdGenerator getInstance() {
        return instance;
    }

    public int generate(List<Ellipse> ellipses) {
        if (ellipses != null && !ellipses.isEmpty()) {

            int index = ellipses.size() - 1;

            Ellipse currentEllipse = ellipses.get(index);
            return currentEllipse.getId() + 1;
        }
        return 1;
    }
}