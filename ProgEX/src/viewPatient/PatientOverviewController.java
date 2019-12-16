package viewPatient;


import ServerConnector.ServerConnector;
import Undo.UndoHandlerPatient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import Undo.PairPatient;
import javafx.stage.Stage;
import main.MainApp;
import model.Patients;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class PatientOverviewController implements Initializable{
    //Variables
    private MainApp mainApp;
    private boolean savedBeforeClosing = false;
    private UndoHandlerPatient undoHandlerPatient = new UndoHandlerPatient();
    private ServerConnector server = new ServerConnector();
    private Stage primaryStage;


    @FXML
    private TableView<Patients> patientTable;

    @FXML
    private TableColumn<Patients,String> nameColumn;

    @FXML
    private TableColumn<Patients,Integer> roomColumn;

    @FXML
    private TableColumn<Patients,String> doctorColumn;

    @FXML
    private Label nameLabel;

    @FXML
    private Label firstnameLabel;

    @FXML
    private Label middlenameLabel;

    @FXML
    private Label birthdayLabel;

    @FXML
    private Label roomLabel;

    @FXML
    private Label bedLabel;

    @FXML
    private Label doctorLabel;

    @FXML
    private Label otherDoctorLabel;

    public PatientOverviewController(){}

    public TableView<Patients> getPatientTable(){
        return patientTable;
    }
    public void showPatientDetails(Patients temp){
        if(temp != null){
            nameLabel.setText(temp.getName());
            firstnameLabel.setText(temp.getFirstname());
            middlenameLabel.setText(temp.getMiddlename());
            birthdayLabel.setText(temp.getBirthday().toString());
            roomLabel.setText(Integer.toString(temp.getRoom()));

            doctorLabel.setText(temp.getDoctor());
            otherDoctorLabel.setText(temp.getOtherDoctor());

            if (temp.getBed() != -1){
                bedLabel.setText(Integer.toString(temp.getBed()));
            }
            else {
                bedLabel.setText("");
            }
        }else {
            nameLabel.setText("");
            firstnameLabel.setText("");
            middlenameLabel.setText("");
            birthdayLabel.setText("");
            roomLabel.setText("");
            bedLabel.setText("");
            doctorLabel.setText("");
            otherDoctorLabel.setText("");
        }
    }

    //Buttons
    @FXML //finished
    public void handleButtonAdd(){
        //patientlist must be saved in savelist
        undoHandlerPatient.setAdd(patientTable.getItems().size());
        savedBeforeClosing = false;

        //create new patient object
        Patients patients = new Patients();

        //open add GUI
        //boolean SubmitClicked = mainApp.showAddPatientStreet(patients);
        boolean SubmitClicked = mainApp.showAddPatientsAll(patients);

        if (SubmitClicked){
            patientTable.getItems().add(patients);
            patientTable.refresh();
        }
    }

    @FXML //finished
    public void handleButtonChange(){
        //patientlist must be saved in savelist
        savedBeforeClosing = false;

        //select Item from tableview
        Patients selectedPatient = patientTable.getSelectionModel().getSelectedItem();


        //open edit GUI
        if (selectedPatient != null){
            undoHandlerPatient.setChange(selectedPatient,patientTable.getSelectionModel().getSelectedIndex());

            boolean SubmitClicked = mainApp.showEditPatientAll(selectedPatient);
            if (SubmitClicked){
                showPatientDetails(selectedPatient);
                patientTable.getItems().set(patientTable.getSelectionModel().getSelectedIndex(),selectedPatient);
                patientTable.refresh();
            }
        }else{
            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No selection");
            alert.setHeaderText("No patient selected");
            alert.setContentText("pls select a patient in the table");

            alert.showAndWait();
        }
    }

    @FXML //finished
    public void handleButtonDelete(){
        //patientlist must be saved in savelist

        savedBeforeClosing = false;

        //must be one patient selected in tableview
        //else not possible
        int selectedIndex = patientTable.getSelectionModel().getSelectedIndex();
        if(selectedIndex >=0){
            undoHandlerPatient.setDelete(patientTable.getSelectionModel().getSelectedItem(),patientTable.getSelectionModel().getSelectedIndex());

            patientTable.getItems().remove(selectedIndex);
            patientTable.refresh();

        }
        else {
            //nothing selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person selected");
            alert.setContentText("Please select a patient in the table");

            alert.showAndWait();
        }

    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;

        //add patients to list
        patientTable.setItems(mainApp.getPatientsList());
    }

    public void getPrimaryStage(Stage stage){
        this.primaryStage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set table column values
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        roomColumn.setCellValueFactory(new PropertyValueFactory<>("room"));
        doctorColumn.setCellValueFactory(new PropertyValueFactory<>("doctor"));

        //listener for changeEditor
        patientTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->showPatientDetails(newValue));

        //clear patient details at start
        showPatientDetails(null);

        /*
        this.primaryStage.setOnCloseRequest(event -> {
            if (!savedBeforeClosing){
                Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
                confirmation.initOwner(mainApp.getPrimaryStage());
                confirmation.setTitle("Confirmation Leaving unsaved");
                confirmation.setHeaderText("Information are not saved");
                confirmation.setContentText("Information will be lost");

                Optional<ButtonType> result = confirmation.showAndWait();

                if (result.get() == ButtonType.OK){
                    System.exit(0);
                }else{
                    event.consume();
                }
            }else {
                System.exit(0);
            }
        });*/
    }

    //---------------------------------------------------------------------------------------------------
    //MenuBar

    @FXML //db connector missing
    public void handleMenuSave(){
        //save patientlist to db
        if(!savedBeforeClosing){
            server.savePatient(patientTable.getItems());
            System.out.println("saved");
            savedBeforeClosing = true;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Patients are saved ");
            alert.setTitle("Save patients list");
            alert.setContentText("Patients are saved before");

            alert.showAndWait();
        }



    }

    @FXML //db connector missing
    public void handleMenuClose(){
        //first save patient to db
        //or check if nothing changed last check
        //then close program
        if (savedBeforeClosing){
            System.out.println("Saved");
            System.exit(0);
        }else {
            //save patients to db
            handleMenuSave();
            System.out.println("Saved and Exit");
            //exit
            System.exit(0);

        }

    }

    @FXML
    public void PatientInformation(){
        if (patientTable.getSelectionModel().getSelectedItem() != null ){
            mainApp.showPatientInformation(patientTable.getSelectionModel().getSelectedItem());
        }else {
            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No selection");
            alert.setHeaderText("No person selected");
            alert.setContentText("pls select a person in the table");

            alert.showAndWait();
        }

    }

    @FXML //finished
    public void handleMenuAdd(){
        //patientlist must be saved in savelist
        undoHandlerPatient.setAdd(patientTable.getItems().size());
        savedBeforeClosing = false;

        Patients patients = new Patients();

        //open add GUI
        boolean SubmitClicked = mainApp.showAddPatientsAll(patients);
        if (SubmitClicked){
              patientTable.getItems().add(patients);
              patientTable.refresh();
        }
        /*
           patientTable.getItems().add(new Patients("abc","def","ghi",,123,2,"Klaus","no"));
           patientTable.getItems().add(new Patients("def","ghi","jkl",456,1,"Peter","no"));
           patientTable.getItems().add(new Patients("ghi","jkl","mno",789,2,"Klaus","Peter"));
           patientTable.getItems().add(new Patients("jkl","mno","abc",123,1,"Peter","no"));
           patientTable.getItems().add(new Patients("mno","abc","def",789,1,"Klaus","no"));

           patientTable.refresh();*/
    }

    @FXML //finishing
    public void handleMenuChange(){
        //patientlist must be saved in savelist

        savedBeforeClosing = false;

        Patients selected = patientTable.getSelectionModel().getSelectedItem();

        //open edit GUI
        if (selected != null){
            undoHandlerPatient.setChange(selected,patientTable.getSelectionModel().getSelectedIndex());
            boolean submitClicked = mainApp.showEditPatientAll(selected);
            if (submitClicked) {
                showPatientDetails(selected);
                patientTable.getItems().set(patientTable.getSelectionModel().getSelectedIndex(),selected);
                patientTable.refresh();
            }
        }else {
            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No selection");
            alert.setHeaderText("No person selected");
            alert.setContentText("pls select a person in the table");

            alert.showAndWait();
        }
    }

    @FXML //finished
    public void handleMenuDelete(){
        //patientlist must be saved in savelist
        savedBeforeClosing = false;

        //if one selected in tableview -> delete this
        //if no element selected -> delete last

        if (patientTable.getSelectionModel().getSelectedItem() != null){
            undoHandlerPatient.setDelete(patientTable.getSelectionModel().getSelectedItem(),patientTable.getSelectionModel().getSelectedIndex());
            patientTable.getItems().remove(patientTable.getSelectionModel().getSelectedItem());
            patientTable.refresh();
        }else{
            undoHandlerPatient.setDelete(patientTable.getItems().get(patientTable.getItems().size()-1),patientTable.getItems().size()-1);
            patientTable.getItems().remove(patientTable.getItems().size()-1);
            patientTable.refresh();
        }


    }

    @FXML //should be finished
    public void handleMenuUnDo(){
        //one possibility: save list in save before every add,change,delete action
        //only one undo is possible
        PairPatient pairPatient = undoHandlerPatient.undo();

        if (pairPatient != null){
            if (pairPatient.getAttribute().equals("Add")){
                patientTable.getItems().remove(pairPatient.getPosition());
            }else if(pairPatient.getAttribute().equals("Change")){
                patientTable.getItems().set(pairPatient.getPosition(), pairPatient.getPatients());
            }else if (pairPatient.getAttribute().equals("Delete")){
                patientTable.getItems().add(pairPatient.getPosition(), pairPatient.getPatients());
            }else {
                Alert alert= new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Error");
                alert.setHeaderText("Undo error");
                alert.setContentText("No person in list");

                alert.showAndWait();
            }
            patientTable.refresh();
        }
    }

    @FXML
    public void handleMenuTest(){
        mainApp.showPatientDoctorStatistic(patientTable.getItems());
    }

    @FXML
    public void handleMenuInfo(){

    }

    @FXML //finished
    public void handleMenuAbout(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("About: ");
        alert.setTitle("About");
        alert.setContentText("Programming Exercise by Tobias Maas and Tobias Schiffhauer");

        alert.showAndWait();
    }


}
