/*


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import main.MainApp;
import model.Patients;
import viewPatient.PatientOverviewController;
import java.net.URL;
import java.util.ResourceBundle;

public class RootOverviewController implements Initializable {

    //Variables
    private MainApp mainApp;
    private PatientOverviewController patientOverviewController;

    public void setPatientOverviewController(PatientOverviewController patientOverviewController){
        this.patientOverviewController = patientOverviewController;
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void handleClose(){
        System.exit(0);
    }

    @FXML
     private void handleAdd(){
        Patients patients = new Patients();
        boolean submit = mainApp.showAddPatientsAll(patients);
        if (submit) {
            //mainApp.addPatient(patients);
        }
    }

    @FXML
    private void handleChange(){
        //select Item from tableview
        TableView<Patients> tableView = patientOverviewController.getPatientTable();
        Patients selectedPatient = tableView.getSelectionModel().getSelectedItem();

        //open edit GUI
        if (selectedPatient != null){
            boolean SubmitClicked = mainApp.showEditPatientAll(selectedPatient);
            if (SubmitClicked){
                patientOverviewController.showPatientDetails(selectedPatient);
            }
        }else{
            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No selection");
            alert.setHeaderText("No person selected");
            alert.setContentText("pls select a person in the table");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleDelete(){
        int length = mainApp.getPatientsList().size()-1;
        mainApp.getPatientsList().remove(length);
    }

    @FXML
    private void handleTest(){

    }

    @FXML
    private void handleInfo(){

    }

    @FXML
    private void handleAbout(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("About: ");
        alert.setTitle("About");
        alert.setContentText("Programming Exercise by Tobias Maas and Tobias Schiffhauer");

        alert.showAndWait();
    }

}
    public void initRootLayout(){
        try {
            //Load root layout from fxml file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/viewPatient/RootLayout.fxml"));
            rootLayout = loader.load();

            //show the scene containing the root layout
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            //Give the controller access to MainApp
            RootOverviewController controller = loader.getController();
            controller.setMainApp(this);


            primaryStage.show();

        } catch (IOException e){
            e.printStackTrace();
        }

        //load data from db
    }

//private ObservableList<Employee> employeeSaveList = FXCollections.observableArrayList();

//public void setPatientsList(ObservableList<Patients> list){patientsList = list;}

//-------------------------------------------------------------------------------------------------
//Getter and Setter Save

//public void setEmployeeSaveList(ObservableList<Employee> employeeList){ this.employeeSaveList = employeeList;}
//public ObservableList<Employee> getEmployeeSaveList(){ return employeeSaveList;}

//only in setMainApp
//public void setEmployeeList(ObservableList<Employee> list){this.employeeList = list;}
*/