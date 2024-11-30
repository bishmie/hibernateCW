package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.StudentBO;

import lk.ijse.dto.StudentDTO;
import lk.ijse.tm.StudentTM;

import java.io.IOException;
import java.util.List;

public class ViewRegistration {



    @FXML
    private TableColumn<?, ?> colCoordinatorId;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colStudentName;

    @FXML
    private Text studentHeading;

    @FXML
    private Button viewBtn;

    @FXML
    private AnchorPane viewRegistrationForm;

    @FXML
    private TableView<StudentTM> viewTbl;

    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

    public void initialize(){
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
//        colCoordinatorId.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("firstname"));
    }

    @FXML
    void viewBtnOnAction(ActionEvent event) throws IOException {
        viewAllStudents();
    }

    private void viewAllStudents() throws IOException {
        ObservableList<StudentTM> obList = FXCollections.observableArrayList();


        List<StudentDTO> studentList = studentBO.getRegisteredStudents();

        for(StudentDTO studentDTO : studentList){

            StudentTM studentTm = new StudentTM(
                    studentDTO.getStudentId(),

                    studentDTO.getFirstname()
            );

            obList.add(studentTm);
        }
        System.out.println("done1");
        viewTbl.setItems(obList);

//        } catch (Exception e) {
//            new Alert(Alert.AlertType.ERROR, "Error loading students: " + e.getMessage()).show();
//        }

    }
}