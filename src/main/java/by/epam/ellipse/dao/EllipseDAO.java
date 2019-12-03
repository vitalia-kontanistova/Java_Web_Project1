package by.epam.ellipse.dao;

import by.epam.ellipse.dao.exception.DAOexception;
import by.epam.ellipse.entity.Ellipse;
import by.epam.ellipse.service.EllipseServiceImpl;

import java.util.List;

public interface EllipseDAO {
    List<Ellipse> createFromFile(String requestToPropFile, EllipseServiceImpl ellipseService) throws DAOexception;
}
