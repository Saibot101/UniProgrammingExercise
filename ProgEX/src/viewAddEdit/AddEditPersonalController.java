package viewAddEdit;

import javafx.fxml.FXML;


import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Patients;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEditPersonalController implements Initializable {

    private Stage editStage;
    private Patients patients = new Patients();
    private boolean usedBefore = false;


    @FXML
    private TextField name;//Last name

    @FXML
    private TextField firstname;

    @FXML
    private TextField middlename;

    @FXML
    private DatePicker birthday;

    @FXML
    private TextField room;

    @FXML
    private TextField bed;

    @FXML
    private TextField doctor;

    @FXML
    private TextField otherDoctors;



    public void setEditStage(Stage editStage){
        this.editStage = editStage;
    }

    public void setPatients(Patients patients){
        this.patients = patients;

        if (patients.getBed() != 0){
            name.setText(patients.getName());
            firstname.setText(patients.getFirstname());
            middlename.setText(patients.getMiddlename());
            birthday.setValue(patients.getBirthday());
            room.setText(Integer.toString(patients.getRoom()));

            doctor.setText(patients.getDoctor());
            otherDoctors.setText(patients.getOtherDoctor());

            if (patients.getBed() != -1){
                bed.setText(Integer.toString(patients.getRoom()));
            }else {
                bed.setText("");
            }
        }
        else {
            name.setText("");
            firstname.setText("");
            middlename.setText("");
            //birthday.setValue("");
            room.setText("");
            bed.setText("");
            doctor.setText("");
            doctor.setText("");
            otherDoctors.setText("");
        }
    }

    private boolean isInputValid(){
        String errormessage= "";

        if(name.getText() == null || name.getText().length() == 0){
            errormessage += "No input at name!\n";
        }

        if(firstname.getText() == null || firstname.getText().length() == 0){
            errormessage += "No input at First name!\n";
        }

        if(middlename.getText() == null || middlename.getText().length() == 0){
            errormessage += "No input at Middle name!\n";
        }

        if (birthday.getValue() == null) {
            errormessage += "No input at Birthday!\n";
        }


        if(room.getText() == null || room.getText().length() == 0)
        {
            errormessage += "no input at room\n";
        }
        else
        {
            try{
                Integer.parseInt(room.getText());
            }catch(NumberFormatException e)
            {
                errormessage += "no valid input at room (must be an integer)";
            }
        }


        if(bed.getText() == null || bed.getText().length() == 0)
        {
            errormessage += "no input at bed\n";
        }
        else{
            try {
                Integer.parseInt(bed.getText());
            }catch (NumberFormatException e)
            {
                errormessage += "no valid input at bed (must be an integer)";
            }
        }

        if(doctor.getText() == null || doctor.getText().length() == 0){
            errormessage += "No input at doctor!\n";
        }

        if(otherDoctors.getText() == null || otherDoctors.getText().length() == 0){
            errormessage += "No input at other doctors!\n";
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

    public void setUsedBeforeTrue(){
        usedBefore = true;
    }

    public boolean getUsedBefore(){
        return this.usedBefore;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /*
    @FXML
    public void cancel(){
        editStage.close();
    }*/

    public void submit(){
        if (isInputValid()){
            patients.setName(name.getText());
            patients.setFirstname(firstname.getText());
            patients.setMiddlename(middlename.getText());

            patients.setBirthday(birthday.getValue());
            patients.setBed(Integer.parseInt(bed.getText()));
            patients.setRoom(Integer.parseInt(room.getText()));
            patients.setDoctor(doctor.getText());
            patients.setOtherDoctor(otherDoctors.getText());
        }
    }

}
