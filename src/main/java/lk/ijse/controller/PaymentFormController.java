package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.EnrollmentBO;
import lk.ijse.BO.custom.PaymentBO;
import lk.ijse.dto.EnrollmentDTO;
import lk.ijse.dto.PaymentDTO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.entity.Enrollment;
import lk.ijse.entity.Payement;

import java.io.IOException;

public class PaymentFormController {
PaymentBO paymentBO =(PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);
EnrollmentBO enrollmentBO =(EnrollmentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ENROLLMENT);
    @FXML
    private TableColumn<?, ?> colBalance;

    @FXML
    private TableColumn<?, ?> colCourseID;

    @FXML
    private TableColumn<?, ?> colDownpayment;

    @FXML
    private TableColumn<?, ?> colFinalinstallment;

    @FXML
    private TableColumn<?, ?> colPaidDate;

    @FXML
    private TableColumn<?, ?> colRegisId;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colStudentID;

    @FXML
    private Label lblBalance;

    @FXML
    private Label lblCourseId;

    @FXML
    private Label lblDownpayment;

    @FXML
    private Label lblStudentId;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<?> tblPayment;

    @FXML
    private TextField txtFinalPayment;

    @FXML
    private TextField txtRegistrationId;
    @FXML
    private DatePicker dateFinalDate;
//    @FXML
//    void btnSaveOnAction(ActionEvent event) {
//
//        String registrationId = txtRegistrationId.getText();
//        double downPayment = Double.parseDouble(lblDownpayment.getText());
//        double balance = Double.parseDouble(lblBalance.getText());
//        double finalInstallment = Double.parseDouble(txtFinalPayment.getText());
//        String finalPayDate = String.valueOf(dateFinalDate.getValue());
//
//        try {
//            boolean isSaved = paymentBO.save(new PaymentDTO(registrationId,downPayment,balance,finalInstallment,finalPayDate));
//       if(isSaved){
//           new Alert(Alert.AlertType.CONFIRMATION,"payment saved successfully").show();
//       }
//       else {
//           new Alert(Alert.AlertType.ERROR,"payment declined").show();
//       }
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String registrationId = txtRegistrationId.getText();
        double downPayment = Double.parseDouble(lblDownpayment.getText());
        double balance = Double.parseDouble(lblBalance.getText());
        double finalInstallment = Double.parseDouble(txtFinalPayment.getText());
        String finalPayDate = String.valueOf(dateFinalDate.getValue());


    }



    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void txtDurationOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtIdKeyAction(KeyEvent event) {

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

        String registerId = txtRegistrationId.getText();

        try {
            EnrollmentDTO paymentDTO=  enrollmentBO.searchById(registerId);

            if(paymentDTO != null){
//               System.out.println(paymentDTO.getDownPayment()+ "heloooooooooooooo");

                lblDownpayment.setText(String.valueOf(paymentDTO.getDownPayment()));
                lblBalance.setText(String.valueOf(paymentDTO.getBalance()));

            }
            if(paymentDTO==null) {
                new Alert(Alert.AlertType.ERROR,"can not find the Registration Number").show();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
