package lk.ijse.dto;

import lk.ijse.entity.Course;
import lk.ijse.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnrollmentDTO {

    private String registrationId;
    private String registrationDate;
    private double downPayment;
    private double balance;
    private String studentId;
    private String courseId;

    public EnrollmentDTO(String registrationId, String registrationDate, double downPayment, double balance, Student student, Course course) {
    }

    public EnrollmentDTO(String registrationId, double downPayment, double balance) {
        this.registrationId =registrationId;
        this.downPayment = downPayment;
        this.balance = balance;
    }
}
