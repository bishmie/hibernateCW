package lk.ijse.BO.custom.impl;

import lk.ijse.BO.custom.EnrollmentBO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.DAO.custom.EnrollmentDAO;

import java.io.IOException;

public class EnrollmentBOImpl implements EnrollmentBO {

    EnrollmentDAO enrollmentDAO = (EnrollmentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ENROLLMENT);

    @Override
    public String getCurrentId() throws IOException {
        return enrollmentDAO.getNextOrderId();
    }

    @Override
    public String generateNewRegisterId() {
        return enrollmentDAO.getCurrentId();

    }


}


