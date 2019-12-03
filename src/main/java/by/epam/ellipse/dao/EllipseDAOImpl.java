package by.epam.ellipse.dao;

import by.epam.ellipse.dao.exception.DAOexception;
import by.epam.ellipse.entity.Ellipse;
import by.epam.ellipse.service.exception.ServiceException;
import by.epam.ellipse.service.EllipseServiceImpl;

import java.util.List;

public class EllipseDAOImpl implements EllipseDAO {
    private EllipseServiceImpl ellipseService;

    private static EllipseDAOImpl instance = new EllipseDAOImpl();

    private EllipseDAOImpl() {
        ellipseService = EllipseServiceImpl.getInstance();
    }

    public static EllipseDAOImpl getInstance() {
        return instance;
    }

    @Override
    public List<Ellipse> createFromFile(String requestToPropFile) throws DAOexception {
        try {
            return ellipseService.createFromFile(requestToPropFile);
        } catch (ServiceException e) {
            throw new DAOexception("EllipseDAOImpl: createFromFile(): " + e.getMessage());
        }
    }
}