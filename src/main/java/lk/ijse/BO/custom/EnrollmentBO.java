package lk.ijse.BO.custom;

import lk.ijse.BO.SuperBO;
import lk.ijse.dto.EnrollmentDTO;
import lk.ijse.dto.PaymentDTO;
import lk.ijse.entity.Enrollment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface EnrollmentBO extends SuperBO {
    String getCurrentId() throws IOException;

    String generateNewRegisterId();

    boolean registerStudent(EnrollmentDTO enrollmentDTO) throws IOException;

    EnrollmentDTO searchById(String registerId) throws IOException;

    boolean updateEnrollment(String registrationId, double finalInstallment, String finalPayDate) throws IOException;

    List<PaymentDTO> loadAllPayment() throws IOException;

    List<Enrollment> getAll() throws IOException;

//    EnrollmentDTO searchById(String registerId) throws IOException;
}
