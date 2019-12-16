package Interface;

import javafx.collections.ObservableList;
import model.Employee;
import model.Patients;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface PatientServer extends Remote{
    ArrayList<Patients> getPatientList() throws RemoteException;
    void setPatientsList(ArrayList<Patients> list) throws RemoteException;
    String LogIn(String name, String password) throws RemoteException;
    ArrayList<Employee> getEmployeeList() throws RemoteException;
    void setEmployeeList(ArrayList<Employee> list) throws RemoteException;
}
