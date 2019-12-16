package main;

import ServerConnector.ServerConnector;
import Statistic.PatientDoctorStatisticController;
import ViewEmployee.AddEditEmployeeController;
import ViewEmployee.AdminLayoutController;
import ViewEmployee.EditPasswordController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import model.Employee;
import model.Patients;
import viewAddEdit.AddEditBuildingController;
import viewAddEdit.AddEditPersonalController;
import viewAddEdit.AddEditStreetController;
import viewPatient.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import viewStart.LogInController;

import java.io.IOException;

public class MainApp extends Application {

    //Variables
    private Stage primaryStage;
    private AnchorPane rootLayout;
    private ObservableList<Patients> patientsList = FXCollections.observableArrayList();
    private ServerConnector serverConnection = new ServerConnector();

    private ObservableList<Employee> employeeList = FXCollections.observableArrayList();

    //-------------------------------------------------------------------------------------------------
    //Getter and Setter Patient
    public ObservableList<Patients> getPatientsList(){ return patientsList;}

    //-------------------------------------------------------------------------------------------------
    //Getter and Setter Employee
    public ObservableList<Employee> getEmployeeList(){ return employeeList;}

    //---------------------------------------------------------------------------------------------------
    //Getter and Setter Primary Stage
    public Stage getPrimaryStage(){
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("PatientApp");

        //start server connection
        //serverConnection.setSendConnection();

        //load patients/employee from db into list
        try{
            patientsList.addAll(serverConnection.getList());
            employeeList.addAll(serverConnection.getEmployeeList());
        }catch (Exception e){
            System.out.println("Exception");
        }
        //returns value if admin or personal
        String re = LogInLayout();
        if (re != null){
            if (re.equals("patient")){
                System.out.println(3);
                showPatientOverview();
            }else if(re.equals("admin")){
                System.out.println(4);
                showAdminOverview();
            }
            else System.exit(0);
        }

    }

    //-------------------------------------------------------------------
    //start function

    private String LogInLayout(){
        //Load LogIn layout from fxml file
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/viewStart/LogInLayout.fxml"));
            rootLayout = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(primaryStage);

            //show the scene
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            //primaryStage.initModality(Modality.WINDOW_MODAL);

            //give the controller access to MainApp
            LogInController controller = loader.getController();
            controller.setLogInStage(stage);

            stage.showAndWait();
            String message = controller.isOk();

            System.out.println(message);

            return message;

            //System.out.println(2);


        }catch (IOException e){
            e.printStackTrace();
        }

        System.out.println(2);
        return "";
    }

    //-------------------------------------------------------------------
    //Patient functions

    private void showPatientOverview(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/viewPatient/MainScene.fxml"));
            rootLayout = loader.load();

            //set Patient overview into the center of root layout
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            //Controller gets access to MainApp
            PatientOverviewController controller = loader.getController();
            controller.setMainApp(this);
            controller.getPrimaryStage(primaryStage);

            primaryStage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showPatientInformation(Patients patients){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/viewPatient/patientInformation.fxml"));
            AnchorPane pane= loader.load();

            //Create edit stage
            Stage stage = new Stage();
            stage.setTitle("Patient Information");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(primaryStage);

            Scene scene = new Scene(pane);
            stage.setScene(scene);

            //set patients into Controller
            patientInformationController controller = loader.getController();
            controller.setInformationStage(stage);
            controller.setInformation(patients);

            //show the dialog and wait until the user closes it
            stage.showAndWait();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //-----------------------------------------------------------
    //AddEdit functions
    public AddEditBuildingController showAddPatientBuilding(Patients patients, SplitPane pane, Stage stage){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/viewAddEdit/AddEditBuilding.fxml"));
            AnchorPane anchorPane= loader.load();

            pane.getItems().remove(0);
            pane.getItems().add(0,anchorPane);

            AddEditBuildingController controller = loader.getController();
            controller.setPatientes(patients);
            controller.setStage(stage);

            if (!controller.getUsedBefore()){
                controller.setUsedBeforeTrue();
            }


            return controller;

        }catch (IOException e){
            e.printStackTrace();

            return null;
        }
    }

    public AddEditPersonalController showAddPatientPersonal(Patients patients, SplitPane pane, Stage stage){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/viewAddEdit/AddEditPersonal.fxml"));
            AnchorPane anchorPane= loader.load();

            pane.getItems().remove(0);
            pane.getItems().add(0,anchorPane);

            /*
            //Create edit stage
            Stage stage = new Stage();
            stage.setTitle("Add Patient");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(primaryStage);

            Scene scene = new Scene(pane);
            stage.setScene(scene);
            */

            //set patients into Controller
            AddEditPersonalController controller = loader.getController();
            controller.setEditStage(stage);
            controller.setPatients(patients);
            if (!controller.getUsedBefore()){
                controller.setUsedBeforeTrue();
            }


            //show the dialog and wait until the user closes it
            //stage.showAndWait();

            return controller;
        }catch (IOException e){
            e.printStackTrace();

            return null;

        }
    }

    public AddEditStreetController showAddPatientStreet(Patients patients, SplitPane pane, Stage stage){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/viewAddEdit/AddEditStreet.fxml"));
            AnchorPane anchorPane= loader.load();

            pane.getItems().remove(0);
            pane.getItems().add(0,anchorPane);

            AddEditStreetController controller = loader.getController();
            controller.setPatients(patients);
            controller.setStage(stage);
            if (!controller.getUsedBefore()){
                controller.setUsedBeforeTrue();
            }

            return controller;

        }catch (IOException e){
            e.printStackTrace();

            return null;
        }
    }

    //-----------------------------------------------------------
    //Add

    public boolean showAddPatientsAll(Patients patients){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/viewPatient/AddEditSlider.fxml"));
            AnchorPane pane= loader.load();

            //Create edit stage
            Stage stage = new Stage();
            stage.setTitle("Add Patient");


            Scene scene = new Scene(pane);
            stage.setScene(scene);

            //set patients into Controller
            AddEditSliderController controller = loader.getController();
            controller.setMainapp(this);
            controller.setPatients(patients);
            controller.initializePane();
            controller.setStage(stage);

            //show the dialog and wait until the user closes it
            stage.showAndWait();


            return controller.isSubmitted();
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    //-----------------------------------------------------------
    //Edit

    public boolean showEditPatientAll(Patients patients){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/viewPatient/AddEditSlider.fxml"));
            AnchorPane pane= loader.load();

            //Create edit stage
            Stage stage = new Stage();
            stage.setTitle("Change Patient");


            Scene scene = new Scene(pane);
            stage.setScene(scene);

            //set patients into Controller
            AddEditSliderController controller = loader.getController();
            controller.setMainapp(this);
            controller.setPatients(patients);
            controller.initializePane();
            controller.setStage(stage);

            //show the dialog and wait until the user closes it
            stage.showAndWait();


            return controller.isSubmitted();
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    //-----------------------------------------------------------
    //Statistic

    public void showPatientDoctorStatistic(ObservableList<Patients> list){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Statistic/PatientDoctorStatistic.fxml"));
            AnchorPane pane = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Doctor Statistic");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(primaryStage);

            Scene scene = new Scene(pane);
            stage.setScene(scene);

            PatientDoctorStatisticController controller = loader.getController();
            controller.setDoctors(list);
            controller.setData(list);

            stage.showAndWait();


        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //-------------------------------------------------------------------
    // Employee/Admin functions

    private void showAdminOverview(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/viewEmployee/AdminLayout.fxml"));
            rootLayout = loader.load();

            //set Patient overview into the center of root layout
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            //Controller gets access to MainApp
            AdminLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean showAddEmployee(Employee employee){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/viewEmployee/AddEditEmployee.fxml"));
            AnchorPane pane= loader.load();

            //Create edit stage
            Stage stage = new Stage();
            stage.setTitle("Add Employee");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(primaryStage);

            Scene scene = new Scene(pane);
            stage.setScene(scene);

            //set employee into Controller
            AddEditEmployeeController controller = loader.getController();
            controller.setEditStage(stage);
            controller.setEmployee(employee);

            //show the dialog and wait until the user closes it
            stage.showAndWait();

            return controller.getisOkClicked();
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean showEditEmployee(Employee employee){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/viewEmployee/AddEditEmployee.fxml"));
            AnchorPane pane= loader.load();

            //Create edit stage
            Stage stage = new Stage();
            stage.setTitle("Edit Employee");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(primaryStage);

            Scene scene = new Scene(pane);
            stage.setScene(scene);

            //set patients into Controller
            AddEditEmployeeController controller = loader.getController();
            controller.setEditStage(stage);
            controller.setEmployee(employee);

            //show the dialog and wait until the user closes it
            stage.showAndWait();

            return controller.getisOkClicked();
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }

    }

    public boolean showPasswordChangeEmployee(Employee employee){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/viewEmployee/EditPassword.fxml"));
            AnchorPane pane= loader.load();

            //Create edit stage
            Stage stage = new Stage();
            stage.setTitle("Change Password");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(primaryStage);

            Scene scene = new Scene(pane);
            stage.setScene(scene);

            //set patients into Controller
            EditPasswordController controller = loader.getController();
            controller.setEditStage(stage);
            controller.setEmployee(employee);

            //show the dialog and wait until the user closes it
            stage.showAndWait();

            return controller.isSubmitted();
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }

    }

}
