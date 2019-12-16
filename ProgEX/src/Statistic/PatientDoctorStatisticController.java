package Statistic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import model.Patients;

import java.net.URL;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class PatientDoctorStatisticController implements Initializable {

    @FXML private BarChart<String,Integer> barChart;
    @FXML private CategoryAxis categoryAxis;

    private ObservableList<String> doctors = FXCollections.observableArrayList();

    public void setDoctors(ObservableList<Patients> list){
        try{
            Set<String> hashSet = new LinkedHashSet<>();
            for (Patients patients:list){
                hashSet.add(patients.getDoctor());
            }

            doctors.addAll(hashSet);

            categoryAxis.setCategories(doctors);
        }catch (Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You need to add some doctors first");
        }

    }

    public void setData(ObservableList<Patients> list){
        int[] doctorCounter = new int[doctors.size()];
        for (Patients patients:list){
            for (int i = 0; i < doctors.size();i++){
                if (patients.getDoctor().equals(doctors.get(i))){
                    doctorCounter[i]++;
                }
            }
        }

        XYChart.Series<String,Integer> series = new XYChart.Series<>();
        for (int i = 0; i< doctorCounter.length; i++){
            series.getData().add(new XYChart.Data<>(doctors.get(i),doctorCounter[i]));
        }

        barChart.getData().add(series);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
