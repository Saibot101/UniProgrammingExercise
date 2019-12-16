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

public class AddEditStreetController implements Initializable {
    private Patients patients = new Patients();
    private Stage stage;
    private boolean getUSed = false;

    //--------------------------------------------------------------
    @FXML private TextField country;
    @FXML private TextField zip;
    @FXML private TextField street;
    @FXML private TextField number;
    @FXML private TextArea IllInfor;


    public void setPatients(Patients patients){
        this.patients = patients;

        if(patients.getStreet() != null){
            country.setText(patients.getStreet());
            zip.setText(Integer.toString(patients.getZip()));
            street.setText(patients.getStreet());
            number.setText(Integer.toString(patients.getHousenumber()));
            IllInfor.setText(patients.getIllnessInformation());
        }else {
            country.setText("");
            zip.setText("");
            street.setText("");
            number.setText("");
        }
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public boolean getUsedBefore(){
        return getUSed;
    }

    public void setUsedBeforeTrue(){
        getUSed = true;
    }

    public void submit(){
        if (isInputValid()) {
            patients.setCountry(country.getText());
            patients.setZip(Integer.parseInt(zip.getText()));
            patients.setStreet(street.getText());
            patients.setHousenumber(Integer.parseInt(number.getText()));
            patients.setIllnessInformation(IllInfor.getText());
        }

    }

    private boolean isInputValid(){
        String errormessage= "";

        if(country.getText() == null || country.getText().length() == 0){
            errormessage += "No input at country!\n";
        }

        if(zip.getText() == null || zip.getText().length() == 0) {
            errormessage += "no input at zip\n";
        }
        else {
            try{
                Integer.parseInt(zip.getText());
            }catch(NumberFormatException e)
            {
                errormessage += "no valid input at zip (must be an integer)";
            }
        }

        if(street.getText() == null || street.getText().length() == 0){
            errormessage += "No input at country!\n";
        }

        if(number.getText() == null || number.getText().length() == 0) {
            errormessage += "no input at zip\n";
        }
        else
        {
            try{
                Integer.parseInt(number.getText());
            }catch(NumberFormatException e)
            {
                errormessage += "no valid input at zip (must be an integer)";
            }
        }

        if(IllInfor.getText() == null || IllInfor.getText().length() == 0){
            errormessage += "No input at Illness Information!\n";
        }

        if (errormessage.length() == 0){
            return true;
        }else {
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
