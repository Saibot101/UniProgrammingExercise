package ViewEmployee;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.MainApp;
import model.Employee;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEditEmployeeController implements Initializable {
    //Variables
    private Stage editStage;
    private Employee employee = new Employee();
    private boolean isOkClicked;

    //FXML
    @FXML
    private ComboBox positionBox;

    @FXML
    private DatePicker entryDatePicker;

    @FXML
    private TextField name;

    @FXML
    private TextField familiyname;


    //Getter & Setter
    public void setEditStage(Stage editStage){
        this.editStage = editStage;
    }

    public void setEmployee(Employee employee){
        this.employee = employee;
        if (employee == null){
            name.setText("");
            familiyname.setText("");
            positionBox.setPromptText("");
            //entryDatePicker.setValue("");
        } else {
            name.setText(employee.getName());
            familiyname.setText(employee.getFamilyname());
            positionBox.setPromptText(employee.getPosition());
            entryDatePicker.setValue(employee.getDate());
        }
    }

    public boolean getisOkClicked(){
        return isOkClicked;
    }

    @FXML
    public void handleSubmit(){
        if (isInputValid()){
            employee.setName(name.getText());
            employee.setFamilyname(familiyname.getText());
            try {
                if(!positionBox.getSelectionModel().toString().equals("") || !positionBox.getSelectionModel().isEmpty()){
                    employee.setPosition(positionBox.getSelectionModel().getSelectedItem().toString());
                    if (!entryDatePicker.getValue().toString().equals("") || entryDatePicker.getValue() != null){
                        employee.setDate(entryDatePicker.getValue());
                    }
                }

                isOkClicked = true;
                editStage.close();
            }catch (NullPointerException e){
                //if positionbox didn´t selected -> old or nothing happens
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(editStage);
                alert.setTitle("Invalid Fields");
                alert.setHeaderText("Pls correct invalid fields");
                alert.setContentText("Position or EntryDate are not selected");

                alert.showAndWait();
            }
        }
    }

    @FXML
    public void handleCancel(){
        editStage.close();
    }

    private boolean isInputValid(){
        String errormessage="";
        if(name.getText() == null || name.getText().length() == 0){
            errormessage += "No input at name!\n";
        }
        if(familiyname.getText() == null || familiyname.getText().length() == 0){
            errormessage += "No input at family name!\n";
        }

        if(errormessage.length() == 0)
        {
            return true;
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(editStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Pls correct invalid fields");
            alert.setContentText(errormessage);

            alert.showAndWait();

            return false;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        positionBox.setPromptText("Select Position");
        positionBox.getItems().setAll("CEO","Department Manager","Nurse");
    }
}
