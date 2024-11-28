package lk.ijse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
public class Payement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;

    @ManyToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "registrationId" , foreignKey = @ForeignKey(name = "FK_ENROLLMENT"))
    private Enrollment enrollment;
    private double downPayment;
    private double balance;
    private double finalInstallment;
    private String finalPaidDate;


    public Payement(String registrationId, double downPayment, double balance, double finalInstallment, String finalPaidDate) {

    }
    public Payement(Enrollment enrollment, double downPayment, double balance, double finalInstallment, String finalPaidDate) {
        this.enrollment = enrollment;
        this.downPayment = downPayment;
        this.balance = balance;
        this.finalInstallment = finalInstallment;
        this.finalPaidDate = finalPaidDate;
    }



}


