package by.epam.ellipse.dao;

import by.epam.ellipse.dao.exception.DAOexception;
import by.epam.ellipse.entity.Ellipse;
import by.epam.ellipse.service.exception.ServiceException;
import by.epam.ellipse.service.EllipseServiceImpl;
import by.epam.ellipse.util.FileManipulator;

import java.util.List;

public class EllipseDAOImpl implements EllipseDAO {
    @Override
    public List<Ellipse> createFromFile(String requestToPropFile, EllipseServiceImpl ellipseService) throws DAOexception {
        try {
            return ellipseService.createFromFile(requestToPropFile, FileManipulator.getInstance());
        } catch (ServiceException e) {
            throw new DAOexception("EllipseDAOImpl: createFromFile(): " + e.getMessage());
        }
    }
}