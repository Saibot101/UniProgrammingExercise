package ServerConnector;

import Interface.PatientServer;
import javafx.collections.ObservableList;
import model.Employee;
import model.Patients;

import java.rmi.Naming;
import java.util.ArrayList;

public class ServerConnector {

    public String checkLogIn(String name, String password){
        PatientServer look_up;
        String re = null;

        if (System.getSecurityManager() == null){
            System.setProperty("java.security.policy","D:\\Uni\\4. Semester\\Programming Exercise\\ProgEX\\Sicherheit.policy");
            System.setSecurityManager(new SecurityManager());
        }

        try{
            //Registry registry = LocateRegistry.getRegistry("localhost",4242);
            //look_up = (ServerConnector) registry.lookup("Interface.ServerConnector");

            System.out.println("Connected to Server");

            look_up = (PatientServer) Naming.lookup("rmi://localhost:4242/Interface.PatientServer");
            re = look_up.LogIn(name, password);

        } catch (Exception e){
            System.out.println("ClientPatient: "+e.getMessage());
            e.printStackTrace();
        }

        return re;
    }

    public ArrayList<Patients> getList(){
        ArrayList<Patients> list = null;
        PatientServer look_up;
        if (System.getSecurityManager() == null){
            System.setProperty("java.security.policy","D:\\Uni\\4. Semester\\Programming Exercise\\ProgEX\\Sicherheit.policy");
            System.setSecurityManager(new SecurityManager());
        }

        try{
            //Registry registry = LocateRegistry.getRegistry("localhost",4242);
            //look_up = (ServerConnector) registry.lookup("Interface.ServerConnector");

            System.out.println("Connected to Server");

            look_up = (PatientServer) Naming.lookup("rmi://localhost:4242/Interface.PatientServer");
            list = look_up.getPatientList();

        } catch (Exception e){
            System.out.println("ClientPatient: "+e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Got object");
        return list;
    }

    public void savePatient(ObservableList<Patients> list){
        PatientServer look_up;
        if (System.getSecurityManager() == null){
            System.setProperty("java.security.policy","D:\\Uni\\4. Semester\\Programming Exercise\\ProgEX\\Sicherheit.policy");
            System.setSecurityManager(new SecurityManager());
        }

        try{
            //Registry registry = LocateRegistry.getRegistry("localhost",4242);
            //look_up = (ServerConnector) registry.lookup("Interface.ServerConnector");

            System.out.println("Connected to Server");

            look_up = (PatientServer) Naming.lookup("rmi://localhost:4242/Interface.PatientServer");
            look_up.setPatientsList(new ArrayList<>(list));

        } catch (Exception e){
            System.out.println("ClientPatient: "+e.getMessage());
            e.printStackTrace();
        }
    }

    public ArrayList<Employee> getEmployeeList(){
        ArrayList<Employee> list = null;
        PatientServer look_up;
        if (System.getSecurityManager() == null){
            System.setProperty("java.security.policy","D:\\Uni\\4. Semester\\Programming Exercise\\ProgEX\\Sicherheit.policy");
            System.setSecurityManager(new SecurityManager());
        }

        try{
            //Registry registry = LocateRegistry.getRegistry("localhost",4242);
            //look_up = (ServerConnector) registry.lookup("Interface.ServerConnector");

            System.out.println("Connected to Server");

            look_up = (PatientServer) Naming.lookup("rmi://localhost:4242/Interface.PatientServer");
            list = look_up.getEmployeeList();

        } catch (Exception e){
            System.out.println("ClientEmployee: "+e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Got object");
        return list;
    }

    public void saveEmployee(ObservableList<Employee> list){
        PatientServer look_up;
        if (System.getSecurityManager() == null){
            System.setProperty("java.security.policy","D:\\Uni\\4. Semester\\Programming Exercise\\ProgEX\\Sicherheit.policy");
            System.setSecurityManager(new SecurityManager());
        }

        try{
            //Registry registry = LocateRegistry.getRegistry("localhost",4242);
            //look_up = (ServerConnector) registry.lookup("Interface.ServerConnector");

            System.out.println("Connected to Server");

            look_up = (PatientServer) Naming.lookup("rmi://localhost:4242/Interface.PatientServer");
            look_up.setEmployeeList(new ArrayList<>(list));

        } catch (Exception e){
            System.out.println("ClientPatient: "+e.getMessage());
            e.printStackTrace();
        }
    }

}
