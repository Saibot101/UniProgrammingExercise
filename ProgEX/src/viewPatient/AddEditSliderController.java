package viewPatient;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;
import main.MainApp;
import model.Patients;
import viewAddEdit.AddEditBuildingController;
import viewAddEdit.AddEditPersonalController;
import viewAddEdit.AddEditStreetController;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEditSliderController implements Initializable {

    @FXML private Slider slider;
    @FXML private SplitPane pane;
    private MainApp mainApp;
    private Patients patients;
    private Stage stage;
    private boolean isSubmitted = false;

    //-----------------------------------------------
    private AddEditPersonalController addEditPersonalController = new AddEditPersonalController();
    private AddEditStreetController addEditStreetController = new AddEditStreetController();
    private AddEditBuildingController addEditBuildingController = new AddEditBuildingController();

    public void showDetails(Number number){
        if (number.intValue() == 1){
            addEditPersonalController = mainApp.showAddPatientPersonal(patients,pane,stage);
        } else if (number.intValue() == 2) {
            addEditStreetController = mainApp.showAddPatientStreet(patients,pane,stage);
        } else if (number.intValue() == 3) {
            addEditBuildingController = mainApp.showAddPatientBuilding(patients,pane,stage);
        }
    }


    public void initializePane(){
        addEditPersonalController = mainApp.showAddPatientPersonal(patients,pane,stage);
    }


    public void setPatients(Patients patients){
        this.patients = patients;
    }

    public void setMainapp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public boolean isSubmitted(){

        return isSubmitted;
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    @FXML public void cancel(){
        stage.close();
    }

    @FXML public void submit(){

        if (addEditPersonalController.getUsedBefore()){
            addEditPersonalController.submit();
        }

        if (addEditStreetController.getUsedBefore()){
            addEditStreetController.submit();
        }

        if (addEditBuildingController.getUsedBefore()) {
            addEditBuildingController.submit();
        }

        isSubmitted = true;
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        slider.valueProperty().addListener(((observable, oldValue, newValue) -> showDetails(newValue)));


    }
}
