package lk.ijse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
public class Enrollment {
    @Id
    private String registrationId;
    private String registrationDate;
    private double downPayment;
    private double balance;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "studentId")
    private Student student;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "programId")
    private Course course;

    @OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL)
    private List<Payement> paymentList = new ArrayList<>();

    public Enrollment(String registrationId, String registrationDate, double downPayment, double balance, Student student, Course course) {
          this.registrationId =registrationId;
          this.registrationDate =registrationDate;
          this.downPayment =downPayment;
          this.balance=balance;
          this.student=student;
          this.course=course;
    }
}
