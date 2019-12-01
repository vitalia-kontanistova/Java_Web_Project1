package by.epam.ellipse.util;

import by.epam.ellipse.dao.exception.DAOexception;

public class Parser {
    private static final Parser instance = new Parser();

    private Parser() {
    }

    public static Parser getInstance() {
        return instance;
    }

    public double[] parseCoordinates(String coordinatesEntry) throws DAOexception {
        DataValidator dataValidator = DataValidator.getInstance();

        try {
            double[] coordinates = new double[4];
            String[] coordinatesStr;

            if (dataValidator.isEllipseValid(coordinatesEntry)) {
                coordinatesStr = coordinatesEntry.split("\\s");
            } else {
                throw new DAOexception("Parser: parseCoordinates(): Incorrect string coordinatesEntry format.");
            }

            int i = 0;
            for (String s : coordinatesStr) {
                coordinates[i] = Double.parseDouble(s);
                i++;
            }
            return coordinates;

        } catch (NullPointerException e) {
            throw new DAOexception("Parser: parseCoordinates(): " + e.getMessage());
        }
    }
}