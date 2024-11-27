package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBO;

import java.io.IOException;

public interface EnrollmentBO extends SuperBO {
    String getCurrentId() throws IOException;

    String generateNewRegisterId();

}
