package viewAddEdit;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Patients;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEditBuildingController implements Initializable {
    //Variables
    private Stage stage;
    private Patients patients = new Patients();
    private boolean usedBefore = false;

    //FXML
    @FXML private TextField floor;
    @FXML private TextField department;
    @FXML private TextField building;
    @FXML private TextArea information;


    //Methods
    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void setPatientes(Patients patients){
        this.patients = patients;

        if (patients != null){
            floor.setText(Integer.toString(patients.getFloornumber()));
            department.setText(Integer.toString(patients.getDepartmentnumber()));
            building.setText(Integer.toString(patients.getBuildingnumber()));
            information.setText(patients.getRoominformation());
        }else{
            floor.setText("");
            department.setText("");
            building.setText("");
            information.setText("");
        }
    }

    public void setUsedBeforeTrue(){
        this.usedBefore = true;
    }

    public boolean getUsedBefore(){
        return usedBefore;
    }

    public void submit(){
        if (isInputValid()){
            patients.setFloornumber(Integer.parseInt(floor.getText()));
            patients.setDepartmentnumber(Integer.parseInt(department.getText()));
            patients.setBuildingnumber(Integer.parseInt(building.getText()));
            patients.setRoominformation(information.getText());
        }

    }

    private boolean isInputValid(){
        String errormessage = "";

        if(floor.getText() == null || floor.getText().length() == 0)
        {
            errormessage += "no input at floor number\n";
        }
        else
        {
            try{
                Integer.parseInt(floor.getText());
            }catch(NumberFormatException e)
            {
                errormessage += "no valid input at floor number (must be an integer)";
            }
        }

        if(department.getText() == null || department.getText().length() == 0)
        {
            errormessage += "no input at department number\n";
        }
        else
        {
            try{
                Integer.parseInt(department.getText());
            }catch(NumberFormatException e)
            {
                errormessage += "no valid input at department number (must be an integer)";
            }
        }

        if(building.getText() == null || building.getText().length() == 0)
        {
            errormessage += "no input at building number\n";
        }
        else
        {
            try{
                Integer.parseInt(building.getText());
            }catch(NumberFormatException e)
            {
                errormessage += "no valid input at building number (must be an integer)";
            }
        }

        if(information.getText() == null || information.getText().length() == 0){
            errormessage += "No input at Roominformation!\n";
        }

        if(errormessage.length() == 0)
        {
            return true;
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(stage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Pls correct invalid fields");
            alert.setContentText(errormessage);

            alert.showAndWait();

            return false;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
