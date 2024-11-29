package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.BO.BOFactory;
import lk.ijse.BO.custom.UserBO;
import lk.ijse.BO.custom.impl.UserBOImpl;
import lk.ijse.dto.UserDTO;
import lk.ijse.exception.UserAlreadyExistsException;

import java.io.IOException;

public class RegesterFormController {

    UserBO userBO =  (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtUserRole1;

    @FXML
    void btnRegesterOnAction(ActionEvent event) {
        String id= txtUserId.getText();
        String password =txtPassword.getText();
        String userName = txtUserName.getText();
        String userRole = txtUserRole1.getText();

        try {
            boolean isRegistered = userBO.saveUser(new UserDTO(id,password,userName,userRole));
            if(isRegistered){
                new Alert(Alert.AlertType.INFORMATION,"REGISTERED SUCCESSFULLY").show();
                clearFeilds();
                loginPage();
            }
            else {
                new Alert(Alert.AlertType.ERROR,"PLEASE TRY AGAIN").show();
            }
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"duplicate Id");
        }
    }

    public void clearFeilds(){
        txtUserId.setText("");
        txtPassword.setText("");
        txtUserName.setText("");
        txtUserRole1.setText("");
    }

    @FXML
    void btnLOgingonaction(ActionEvent event) {


    }
    private void loginPage() throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/loginForm.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login Form");
    }

}

