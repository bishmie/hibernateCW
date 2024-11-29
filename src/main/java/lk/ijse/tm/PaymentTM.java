package lk.ijse.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentTM {
    private String registrationId;
    private double downPayament;
    private double balance;
    private double firstInstallment;
    private String paidDate;


}
