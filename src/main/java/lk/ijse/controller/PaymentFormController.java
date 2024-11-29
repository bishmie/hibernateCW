package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.EnrollmentBO;
import lk.ijse.BO.custom.PaymentBO;
import lk.ijse.dto.EnrollmentDTO;
import lk.ijse.dto.PaymentDTO;
import lk.ijse.entity.Enrollment;
import lk.ijse.tm.PaymentTM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PaymentFormController {
    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);
    EnrollmentBO enrollmentBO = (EnrollmentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ENROLLMENT);

    public void initialize() {
        setCellValueFactory();
//       loadAllPayment();
    }

    private void loadAllPayment() {
        tblPayment.getItems().clear();
        ObservableList<PaymentTM> obList = FXCollections.observableArrayList();
        List<Enrollment> registrations = null;

        System.out.println("man awaaaaa");
        try {
            registrations = enrollmentBO.getAll();
            System.out.println("data awaaaaa");
//            if (registrations != null) {
//                for (Enrollment c : registrations) {
//                    obList.add(new PaymentTM(
//                            c.getRegistrationId(),
//                            c.getDownPayment(),
//                            c.getBalance(),
//                            c.getFinalInstallment(),
//                            c.getFinalPaidDate()));
//                }

          //  }

            for (int i = 0; i < registrations.size(); i++) {
            PaymentTM registrationTM = new PaymentTM(
                    registrations.get(i).getRegistrationId(),
                    registrations.get(i).getDownPayment(),
                    registrations.get(i).getBalance(),
                    registrations.get(i).getFinalInstallment(),
                    registrations.get(i).getFinalPaidDate()


            );
                System.out.println("hiiiiiii");
            obList.add(registrationTM);
        }
        tblPayment.setItems(obList);
        } catch (IOException e) {
            e.printStackTrace(); // Log the error for debugging
            new Alert(Alert.AlertType.ERROR, "Failed to load payments.").show();
        }

    }

    private void setCellValueFactory() {
        colRegisId.setCellValueFactory(new PropertyValueFactory<>("registrationId"));
        colDownpayment.setCellValueFactory(new PropertyValueFactory<>("downPayment"));
        colBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        colFinalinstallment.setCellValueFactory(new PropertyValueFactory<>("finalInstallment"));
        colPaidDate.setCellValueFactory(new PropertyValueFactory<>("finalPaidDate"));
    }

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
    private TableView<PaymentTM> tblPayment;

    @FXML
    private TextField txtFinalPayment;

    @FXML
    private Label lblCourseID;

    @FXML
    private Label lblStatus;

    @FXML
    private TextField txtRegistrationId;

    @FXML
    private DatePicker dateFinalDate;

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String registrationId = txtRegistrationId.getText();
        double downPayment = Double.parseDouble(lblDownpayment.getText());
        double balance = Double.parseDouble(lblBalance.getText());
        double finalInstallment = Double.parseDouble(txtFinalPayment.getText());
        String finalPayDate = String.valueOf(dateFinalDate.getValue());

        if (finalInstallment == balance) {
            try {
                boolean isUpdate = enrollmentBO.updateEnrollment(registrationId, finalInstallment, finalPayDate);
                if (isUpdate) {
                    reduceBalance();
                    loadAllPayment();
                    new Alert(Alert.AlertType.CONFIRMATION, "Payment updated successfully").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Payment declined").show();
                }
            } catch (IOException e) {
                e.printStackTrace(); // Log the error for debugging
                new Alert(Alert.AlertType.ERROR, "Error updating payment.").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Balance amount is incorrect.").show();
        }
    }

    public void reduceBalance() {
        String registrationId = txtRegistrationId.getText();
        double balance = Double.parseDouble(lblBalance.getText());
        double finalInstallment = Double.parseDouble(txtFinalPayment.getText());

        if (balance == finalInstallment) {
            lblBalance.setText("COMPLETED");
            try {
                boolean isReduceBalance = paymentBO.removeBalance(registrationId);
                if (isReduceBalance) {
                    System.out.println("Balance reduced successfully.");
                } else {
                    System.out.println("Failed to reduce balance.");
                }
            } catch (IOException e) {
                e.printStackTrace(); // Log the error for debugging
                new Alert(Alert.AlertType.ERROR, "Error reducing balance.").show();
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        // Handle update action (not implemented yet)
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String registerId = txtRegistrationId.getText();

        try {
            EnrollmentDTO paymentDTO = enrollmentBO.searchById(registerId);

            if (paymentDTO != null) {
                lblDownpayment.setText(String.valueOf(paymentDTO.getDownPayment()));
                lblBalance.setText(String.valueOf(paymentDTO.getBalance()));
            } else {
                new Alert(Alert.AlertType.ERROR, "Cannot find the Registration Number").show();
            }
        } catch (IOException e) {
            e.printStackTrace(); // Log the error for debugging
            new Alert(Alert.AlertType.ERROR, "Error searching for registration number.").show();
        }
    }


    @FXML
    void btnclearOnAction(ActionEvent event) {
        loadAllPayment();
    }
}
