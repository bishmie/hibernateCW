package lk.ijse.DAO.custom;

import lk.ijse.DAO.SuperDAO;

import java.io.IOException;

public interface EnrollmentDAO extends SuperDAO {
    String getNextOrderId() throws IOException;

    String getCurrentId();

}
