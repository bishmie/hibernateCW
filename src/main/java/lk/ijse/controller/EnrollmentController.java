


package lk.ijse.controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.AnchorPane;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.CourseBO;
import lk.ijse.BO.custom.EnrollmentBO;
import lk.ijse.BO.custom.StudentBO;
import lk.ijse.dto.CourseDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class EnrollmentController {

    
    EnrollmentBO enrollmentBO = (EnrollmentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ENROLLMENT);
    CourseBO courseBO= (CourseBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.COURSE);
    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

    public void initialize() throws SQLException, IOException, ClassNotFoundException {

            getCurrentRegisterId();
        getCourseIds();
//        configureMultiSelectComboBox();


    }

    private void getCurrentRegisterId() throws SQLException, ClassNotFoundException, IOException {
        String currentId = enrollmentBO.getCurrentId();

        String nextRegisterId = generateNextRegisterId(currentId);
        lblRegesterId.setText(nextRegisterId);
    }


    private String generateNextRegisterId(String currentId) {

        enrollmentBO.generateNewRegisterId();
        return currentId;
    }
    @FXML
    private ComboBox<String> cmbCourses;

    @FXML
    private TableColumn<?, ?> colBalance;

    @FXML
    private TableColumn<?, ?> colCourseIds;

    @FXML
    private TableColumn<?, ?> colDownPayment;

    @FXML
    private TableColumn<?, ?> colRegId;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colStudentName;

    @FXML
    private TableColumn<?, ?> colTotPayment;
    @FXML
    private TableColumn<?, ?> colCourseName;

    @FXML
    private DatePicker dateRegister;

    @FXML
    private Label lblBalance;

    @FXML
    private Label lblRegesterId;

    @FXML
    private Label lblStudentName;

    @FXML
    private Label lblTotalPayment;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<?> tblRegistration;

    @FXML
    private TextField txtDownpayment;

    @FXML
    private TextField txtStudentId;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private Label lblCourseFee;

    @FXML
    private Label lblCourseName;

    @FXML
    private TableColumn<?, ?> colFee;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void txtPaymentCalOnAction(ActionEvent event) {

    }

    @FXML
    void txtStudentDetailOnAction(ActionEvent event) {
       String studentId= txtStudentId.getText();
        String studentName = null;
        try {
            studentName = studentBO.getStudentName(studentId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(studentName!=null){
            lblStudentName.setText(studentName);
        }
        else {
            new Alert(Alert.AlertType.ERROR,"Student Id not found").show();
        }

    }

    @FXML
    void cmbCourseIdsOnAction(ActionEvent event) {
     String courseId = cmbCourses.getValue();

        try {
            CourseDTO courseDTO = courseBO.searchCourse(courseId);
            lblCourseName.setText(courseDTO.getProgramName());
            lblCourseFee.setText(String.valueOf(courseDTO.getFee()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    private void getCourseIds() {
        ObservableList<String> courseIds = FXCollections.observableArrayList();

        ArrayList<String> codeList = null;
        try {
            codeList = courseBO.getCourseIds();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String code : codeList) {
            courseIds.add(code);
        }
        cmbCourses.setItems(courseIds);

    }

    public void btnAddToTableOnAction(ActionEvent actionEvent) {
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) {
    }




}

