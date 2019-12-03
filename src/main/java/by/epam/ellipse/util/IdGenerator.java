package by.epam.ellipse.util;

import by.epam.ellipse.entity.Ellipse;
import by.epam.ellipse.service.exception.ServiceException;

import java.util.List;

public class IdGenerator {

    private static IdGenerator instance = new IdGenerator();

    private IdGenerator() {
    }

    public static IdGenerator getInstance() {
        return instance;
    }

    public int generate(List<Ellipse> ellipses) throws ServiceException {
        try {
            if (ellipses.isEmpty()) {
                return 1;
            } else {
                int index = ellipses.size() - 1;
                Ellipse currentEllipse = ellipses.get(index);
                return currentEllipse.getId() + 1;
            }
        } catch (NullPointerException e) {
            throw new ServiceException("IdGenerator: generate(): " + e.getMessage());
        }
    }
}