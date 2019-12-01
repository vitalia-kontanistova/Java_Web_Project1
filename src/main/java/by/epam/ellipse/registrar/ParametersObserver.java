package by.epam.ellipse.registrar;

import by.epam.ellipse.entity.Ellipse;
import by.epam.ellipse.entity.Parameters;
import by.epam.ellipse.service.exception.ServiceException;
import by.epam.ellipse.service.impl.EllipseServiceImpl;
import by.epam.ellipse.service.impl.ParametersServiceImpl;


public class ParametersObserver implements Observer<Ellipse> {

    private Parameters parameters;

    public ParametersObserver(EllipseObservable ellipseObservable) {
        parameters = new Parameters();
        ellipseObservable.add(this);
    }

    public Parameters getParameters() {
        return this.parameters;
    }

    @Override
    public void update(Ellipse ellipse, ParametersServiceImpl parametersService) throws ServiceException {
        try {
            parametersService.updateParameters(this.parameters, ellipse, EllipseServiceImpl.getInstance());
        } catch (NullPointerException | ServiceException e) {
            throw new ServiceException("ParametersObserver: updateParameters(): " + e.getMessage());
        }
    }
}