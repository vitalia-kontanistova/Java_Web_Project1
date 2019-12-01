package by.epam.ellipse.util;

import by.epam.ellipse.dao.exception.DAOexception;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidator {

    private static final String POINT_PATTERN = "-?\\d+\\.\\d+\\s-?\\d+\\.\\d+";
    private static final String ELLIPSE_PATTERN = "^" + POINT_PATTERN + "\\s" + POINT_PATTERN + "$";


    private static final DataValidator instance = new DataValidator();

    private DataValidator() {
    }

    public static DataValidator getInstance() {
        return instance;
    }


    public boolean isEllipseValid(String ellipse) throws DAOexception {
        try {
            Pattern pattern = Pattern.compile(ELLIPSE_PATTERN);
            Matcher matcher = pattern.matcher(ellipse);

            return matcher.find();
        } catch (NullPointerException e) {
            throw new DAOexception("DataValidator: isEllipseValid(): null object has been passed into method.");
        }
    }
}