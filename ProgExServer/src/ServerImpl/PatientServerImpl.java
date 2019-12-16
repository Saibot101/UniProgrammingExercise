package ServerImpl;

import Connector.DBConnector;
import Interface.PatientServer;
import model.Employee;
import model.Patients;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class PatientServerImpl extends UnicastRemoteObject implements PatientServer{
    //Variables
    private static DBConnector dbConnector = new DBConnector();


    public PatientServerImpl() throws RemoteException{}

    public static void main(String[] args){

        if (System.getSecurityManager() == null){
            System.setProperty("java.security.policy","D:\\Uni\\4. Semester\\Programming Exercise\\ProgEX\\Sicherheit.policy");
            System.setSecurityManager(new SecurityManager());

        }

        dbConnector.AddPatients();
        dbConnector.AddEmployees();

        try{
            Registry registry = LocateRegistry.createRegistry(4242);

            PatientServerImpl patientServer = new PatientServerImpl();
            registry.rebind("Interface.PatientServer",patientServer);

            System.out.println("Server is up");

        }catch (Exception e){
            System.out.println("ServerImpl.PatientServerImpl: "+ e.getMessage());
            e.printStackTrace();
        }
    }

    public String LogIn(String name, String password) throws RemoteException {
        System.out.println("LogIn");
        System.out.println(dbConnector.checkLogIn(name,password));
        return dbConnector.checkLogIn(name ,password);
    }

    public ArrayList<Patients> getPatientList() throws RemoteException {
        System.out.println("getPatients");
        return dbConnector.getPatients();
    }

    public void setPatientsList(ArrayList<Patients> list) throws RemoteException {
        System.out.println("setPatients");
        //dbConnector.setPatients(list);
    }

    public ArrayList<Employee> getEmployeeList() throws RemoteException {
        System.out.println("getEmployeeList");
        return dbConnector.getEmployeeList();
    }

    public void setEmployeeList(ArrayList<Employee> list) throws RemoteException {
        System.out.println("setEmployeeList");
        dbConnector.setEmployeeList(list);
    }

}
