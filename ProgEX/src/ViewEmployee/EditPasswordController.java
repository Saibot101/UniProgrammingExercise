package ViewEmployee;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Employee;

import java.net.URL;
import java.util.ResourceBundle;

public class EditPasswordController implements Initializable {
    //Variables
    private Stage editStage;
    private Employee employee = new Employee();
    private boolean isSubmitted = false;

    //FXML
    @FXML
    private TextField oldPassword;

    @FXML
    private PasswordField newPassword;

    @FXML
    private PasswordField repeatPassword;

    @FXML
    public void handleSubmit(){
        String password = newPassword.getText();
        if (password.equals(repeatPassword.getText()) || !password.equals(oldPassword.getText())){
            employee.setPassword(password);

            isSubmitted = true;
            editStage.close();
        }

    }

    @FXML
    public void handleClose(){
        editStage.close();
    }

    public void setEmployee(Employee employee){
        this.employee = employee;
    }

   public void setEditStage(Stage editStage){
        this.editStage = editStage;
   }

   public boolean isSubmitted(){ return isSubmitted;}


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
