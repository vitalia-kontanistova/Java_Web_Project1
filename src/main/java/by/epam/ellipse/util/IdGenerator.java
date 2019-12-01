package by.epam.ellipse.util;

import by.epam.ellipse.entity.Ellipse;
import by.epam.ellipse.registrar.EllipseObservable;

import java.util.List;

public class IdGenerator {

    private static IdGenerator instance = new IdGenerator();

    private IdGenerator() {
    }

    public static IdGenerator getInstance() {
        return instance;
    }

    public int generate(List<EllipseObservable> ellipseObservables) {
        if (ellipseObservables != null && !ellipseObservables.isEmpty()) {

            int index = ellipseObservables.size() - 1;

            EllipseObservable ellipseObservable = ellipseObservables.get(index);
            Ellipse ellipse = ellipseObservable.getEllipse();
            return ellipse.getId() + 1;
        }
        return 1;
    }
}