package viewStart;

import ServerConnector.ServerConnector;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {
    private String isOK = null;
    private Stage logInStage;
    private ServerConnector serverConnection = new ServerConnector();

    @FXML private PasswordField Password;
    @FXML private TextField Name;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setLogInStage(Stage stage){
        this.logInStage = stage;
    }

    @FXML //compare input with employee list
    public void handleSubmit(){
        String name = Name.getText();
        String password = Password.getText();
        String re="";

       try{
           re = serverConnection.checkLogIn(name,password);
           System.out.println(re);
       }catch (Exception e){
           System.out.println("Exception");
           e.printStackTrace();
       }

        if (re.equals("admin")){
            isOK="admin";
            logInStage.close();
        }else if(re.equals("patient")){
            isOK = "patient";
            logInStage.close();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(logInStage);
            alert.setTitle("Invalid password");
            alert.setHeaderText("Pls correct password");
            alert.showAndWait();
        }
    }

    public String isOk(){
        return isOK;
    }

}
