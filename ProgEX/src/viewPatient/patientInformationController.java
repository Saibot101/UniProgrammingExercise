package viewPatient;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Patients;

import java.net.URL;
import java.util.ResourceBundle;

public class patientInformationController implements Initializable {

    private Stage informationStage;
    private Patients patients = new Patients();


    @FXML
    private GridPane gridPane;

    @FXML
    private Label patientid;

    @FXML
    private Label firstname;

    @FXML
    private Label lastname;

    @FXML
    private Label middlename;

    @FXML
    private Label birthday;

    @FXML
    private Label country;

    @FXML
    private Label zip;

    @FXML
    private Label street;

    @FXML
    private Label housenumber;

    @FXML
    private Label roominformation;

    @FXML
    private Label roomnumber;

    @FXML
    private Label floornumber;

    @FXML
    private Label departmentnumber;

    @FXML
    private Label buildingnumber;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setInformationStage(Stage stage){
        this.informationStage= stage;
    }

    public void setInformation(Patients patients){
        this.patients = patients;

        gridPane.setGridLinesVisible(true);

        //set labels on
        if (!patients.getBirthday().toString().equals("") || patients.getBirthday() != null) {
            birthday.setText(patients.getBirthday().toString());

        }else {
            birthday.setText("");
        }

        if (patients.getPatientid() != 0){
            patientid.setText(Integer.toString(patients.getPatientid()));
        }else {
            patientid.setText("");
        }

        if (!patients.getFirstname().equals("") || patients.getFirstname() != null){
            firstname.setText(patients.getFirstname());
        }else {
            firstname.setText("");
        }

        if (!patients.getName().equals("") || patients.getName() != null){
            lastname.setText(patients.getName());
        }else {
            lastname.setText("");
        }

        /*
        try{
            if (patients.getMiddlename().equals("") || patients.getMiddlename() != null){
                middlename.setText(patients.getMiddlename());
            }else {
                middlename.setText("");
            }
        }catch(Exception e){
            middlename.setText("");
            e.printStackTrace();
        }*/

        //---------------------------------------------------------------------------------------------------
        if ( patients.getCountry() != null){
            country.setText(patients.getCountry());
        }else {
            country.setText("");
        }

        if (patients.getZip() != -1){
            zip.setText(Integer.toString(patients.getZip()));
        }else {
            zip.setText("");
        }

        if ( patients.getStreet() != null) {
            street.setText(patients.getStreet());
        }else {
            street.setText("");
        }

        if (patients.getHousenumber() != -1) {
            housenumber.setText(Integer.toString(patients.getHousenumber()));
        }else {
            housenumber.setText("");
        }
        //---------------------------------------------------------------------------------------------------
        if(patients.getRoominformation() != null){
            roominformation.setText(patients.getRoominformation());
        }else {
            roominformation.setText("");
        }

        if (patients.getRoom() != -1) {
            roomnumber.setText(Integer.toString(patients.getRoom()));
        }else {
            roomnumber.setText("");
        }

        if (patients.getFloornumber() !=-1) {
            floornumber.setText(Integer.toString(patients.getFloornumber()));
        }else {
            floornumber.setText("");
        }

        if (patients.getDepartmentnumber() != -1) {
            departmentnumber.setText(Integer.toString(patients.getDepartmentnumber()));
        }else {
            departmentnumber.setText("");
        }

        if (patients.getBuildingnumber() != -1) {
            buildingnumber.setText(Integer.toString(patients.getBuildingnumber()));
        }else {
            buildingnumber.setText("");
        }
    }

    public void close(){
        informationStage.close();
    }
}
