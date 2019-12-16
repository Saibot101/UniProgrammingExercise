package ViewEmployee;

import ServerConnector.ServerConnector;
import Undo.PairEmployee;
import Undo.UndoHandlerEmployee;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.MainApp;
import model.Employee;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminLayoutController implements Initializable {
    //Variables
    private MainApp mainApp;
    private boolean savedBeforeClosing = false;
    private UndoHandlerEmployee undoHandlerEmployee = new UndoHandlerEmployee();
    private ServerConnector serverConnector = new ServerConnector();

    //FXML elements
    @FXML
    TableView<Employee> employeeTable;

    @FXML
    TableColumn<Employee,String> nameColumn;

    @FXML
    TableColumn<Employee,String> positionColumn;

    //Labels
    @FXML
    private Label nameLabel;

    @FXML
    private Label familynameLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label positionLabel;

    @FXML
    private Label entrydateLabel;
    //MenuBar //later

    //Buttons
    @FXML //finished
    public void handleAdd(){
        savedBeforeClosing = false;
        undoHandlerEmployee.setAdd(employeeTable.getItems().size());

        Employee employee = null;

        //open add GUI
        boolean okClicked = mainApp.showAddEmployee(employee);
        if (okClicked){
            employeeTable.getItems().add(employee);
            employeeTable.refresh();
        }
    }

    @FXML //finished
    public void handleChange(){
        savedBeforeClosing = false;

        Employee employee = employeeTable.getSelectionModel().getSelectedItem();

        if (employee != null) {
            undoHandlerEmployee.setChange(employee,employeeTable.getSelectionModel().getSelectedIndex());

            boolean okClicked = mainApp.showEditEmployee(employee);

            if (okClicked) {
                showEmployeeDetails(employee);
                employeeTable.getItems().set(employeeTable.getSelectionModel().getSelectedIndex(),employee);
                employeeTable.refresh();
            }
        }else{
            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No selection");
            alert.setHeaderText("No employee selected");
            alert.setContentText("pls employee a patient in the table");

            alert.showAndWait();
        }
    }

    @FXML //finished
    public void handleDelete(){
        savedBeforeClosing = false;

        int selectedIndex = employeeTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            undoHandlerEmployee.setDelete(employeeTable.getSelectionModel().getSelectedItem(),employeeTable.getSelectionModel().getSelectedIndex());

            employeeTable.getItems().remove(selectedIndex);
            employeeTable.refresh();
        }else{
            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No selection");
            alert.setHeaderText("No employee selected");
            alert.setContentText("pls select a employee in the table");

            alert.showAndWait();
        }
    }

    //---------------------------------------------------------------------------------------------------
    //MenuBar items
    @FXML //db connector missing
    public void Save(){
        if (!savedBeforeClosing){
            //save in db
            serverConnector.saveEmployee(employeeTable.getItems());

            System.out.println("saved");
            savedBeforeClosing = true;

        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Patients are saved ");
            alert.setTitle("Save patients list");
            alert.setContentText("Patients are saved before");

            alert.showAndWait();
        }
    }

    @FXML //should be finished must be tested
    public void LogOut(){
        Stage stage = mainApp.getPrimaryStage();
        stage.close();

        mainApp.start(stage);
    }

    @FXML //finished
    public void Close(){
        //first save employee to db
        //or check if nothing changed last check
        //then close program
        if (savedBeforeClosing) {
            System.exit(0);
        }else {
            //save employee to db
            Save();
            //
            System.exit(0);
        }
    }

    @FXML
    public void Add(){
        undoHandlerEmployee.setAdd(employeeTable.getItems().size());
        savedBeforeClosing = false;

        Employee employee = new Employee();

        boolean submitClicked = mainApp.showAddEmployee(employee);
        if (submitClicked) {
            employeeTable.getItems().add(employee);
            employeeTable.refresh();
        }
    }

    @FXML //should be finished
    public void Edit() {
        savedBeforeClosing = false;

        Employee employee = employeeTable.getSelectionModel().getSelectedItem();
        if (employee != null) {
            undoHandlerEmployee.setChange(employee,employeeTable.getSelectionModel().getSelectedIndex());

            boolean okClicked = mainApp.showEditEmployee(employee);
            if (okClicked) {
                employeeTable.getItems().set(employeeTable.getSelectionModel().getSelectedIndex(),employee);
                showEmployeeDetails(employee);
                employeeTable.refresh();
            }else {
                Alert alert= new Alert(Alert.AlertType.WARNING);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("No selection");
                alert.setHeaderText("No person selected");
                alert.setContentText("pls select a person in the table");

                alert.showAndWait();
            }
        }
    }

    @FXML //should be finished must be tested
    public void handlePasswordChange(){
        savedBeforeClosing = false;

        Employee employee = employeeTable.getSelectionModel().getSelectedItem();
        if (employee !=null){
            undoHandlerEmployee.setChange(employee,employeeTable.getSelectionModel().getSelectedIndex());

            boolean okClicked = mainApp.showPasswordChangeEmployee(employee);
            if (okClicked) {
                employeeTable.getItems().set(employeeTable.getSelectionModel().getSelectedIndex(),employee);
                showEmployeeDetails(employee);
                employeeTable.refresh();
            }
        }
    }

    @FXML //must be worked
    public void Undo(){
        PairEmployee pairEmployee = undoHandlerEmployee.undo();

        if (pairEmployee!=null){
            if (pairEmployee.getAttribute().equals("Add")){
                employeeTable.getItems().remove(pairEmployee.getPosition());
            }else if (pairEmployee.getAttribute().equals("Change")){
                employeeTable.getItems().set(pairEmployee.getPosition(),pairEmployee.getEmployee());
            }else if (pairEmployee.getAttribute().equals("Delete")){
                employeeTable.getItems().add(pairEmployee.getPosition(),pairEmployee.getEmployee());
            }else {
                Alert alert= new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Error");
                alert.setHeaderText("Undo error");
                alert.setContentText("No person in list");

                alert.showAndWait();
            }
            employeeTable.refresh();
        }
    }

    @FXML //should be finished
    public void Delete(){
        savedBeforeClosing = false;

        Employee selected = employeeTable.getSelectionModel().getSelectedItem();

        if (selected != null) {
            undoHandlerEmployee.setDelete(selected,employeeTable.getSelectionModel().getSelectedIndex());

            employeeTable.getItems().remove(selected);
            employeeTable.refresh();
        }else {
            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No selection");
            alert.setHeaderText("No person selected");
            alert.setContentText("pls select a person in the table");

            alert.showAndWait();
        }
    }

    public AdminLayoutController(){}

    private void showEmployeeDetails(Employee employee){
        if (employee != null) {
            nameLabel.setText(employee.getName());
            familynameLabel.setText(employee.getFamilyname());
            String obfuscate = "";
            for (int i = 0; i <employee.getPassword().length();i++){
                obfuscate = obfuscate + "*";
            }
            passwordLabel.setText(obfuscate);
            positionLabel.setText(employee.getPosition());
            entrydateLabel.setText(employee.getDate().toString());
        }else {
            nameLabel.setText("");
            familynameLabel.setText("");
            passwordLabel.setText("");
            positionLabel.setText("");
            entrydateLabel.setText("");
        }
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;

        //Add observable list data to the table
        //load from server
        employeeTable.setItems(mainApp.getEmployeeList());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set table column values
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));

        //Listener for EditEmployee and ChangePassword
        employeeTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showEmployeeDetails(newValue));

        //clear Person details at start
        showEmployeeDetails(null);

        /*
        mainApp.getPrimaryStage().setOnCloseRequest(event -> {
            if (savedBeforeClosing){
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
}
