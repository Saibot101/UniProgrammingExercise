package Connector;

import model.Employee;
import model.Patients;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DBConnector implements Serializable {

    private ArrayList<Patients> patients = new ArrayList<>();
    private ArrayList<Employee> employees = new ArrayList<>();

    private Connect1 connect = new Connect1();

    public void AddPatients(){
        try{
            patients = connect.getPatient();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void AddEmployees(){
        try {
            employees = connect.getEmployees();
        }catch (SQLException e){
            e.printStackTrace();
        }
        employees.add(new Employee("Admin","Admin","Admin", LocalDate.now()));
    }

    public ArrayList<Patients> getPatients() {
        return patients;
    }

    public void setPatients(ArrayList<Patients> patientsList) {
        boolean flag = false;
        //if size is equal
        for (Patients patient:patientsList){
            if (!patients.contains(patient)){
                for (Patients patients1:patients){
                    if (patient.getRoomid() == patients1.getRoomid()){
                        changePatient(patient,patients1);
                        flag = true;
                        break;
                    }
                }
                if (!flag){
                    addPatients(patient);
                }
            }
        }


    }
    public void addPatients(Patients patients){
        String query;
        //patient
        query = "INSERT INTO Patient VALUES ('"+ patients.getFirstname()+"','"+ patients.getName()+"','"+patients.getBirthday()+"','"+patients.getIllnessInformation()+"' )";
        connect.saveEmployees(query);
        //patient adress
        query = "INSERT INTO Patient_Adress (Country,ZIPCode,Street,HouseNumber) VALUES ('"+ patients.getCountry()+"','"+ patients.getZip()+"','"+patients.getStreet()+"','"+patients.getHousenumber()+"' )";
        connect.saveEmployees(query);
        //building
        query = "INSERT INTO Building VALUES ('"+patients.getBuildingnumber()+"')";
        //department
        query = "INSERT INTO Department(Name) VALUES ('"+patients.getDepartmentnumber()+"')";

        //floor
        query = "INSERT INTO Floor(Floor) VALUES ('"+patients.getFloornumber()+"')";


    }

    public void changePatient(Patients patientnew,Patients patientold){

        if (!patientnew.getName().equals(patientold.getName())){
            String query = "Update Patient Set LastName= '"+patientnew.getName()+ "' where Patient_ID = '"+patientnew .getPatientid()+"'";
            connect.savePatient(query);
        }else if(!patientnew.getFirstname().equals(patientold.getFirstname())){
            String query = "Update Patient Set FirstName= '"+patientnew.getFirstname()+ "' where Patient_ID = '"+patientnew .getPatientid()+"'";
            connect.savePatient(query);
        }else if(!patientnew.getBirthday().equals(patientold.getBirthday())){
            String query = "Update Patient Set Birthday= '"+patientnew.getBirthday()+ "' where Patient_ID = '"+patientnew .getPatientid()+"'";
            connect.savePatient(query);
        }else if (!patientnew.getIllnessInformation().equals(patientold.getIllnessInformation())){
            String query = "Update Patient Set Notes= '"+patientnew.getIllnessInformation()+ "' where Patient_ID = '"+patientnew .getPatientid()+"'";
            connect.savePatient(query);
        }else if (patientnew.getRoom() != patientold.getRoom()){
            String query = "Update Room Set RoomNumber= '"+patientnew.getRoom()+ "' where Room_ID = '"+patientnew .getPatientid()+"'";
            connect.savePatient(query);
        }else if (patientnew.getDepartmentnumber() != patientold.getDepartmentnumber()){
            String query = "Update Department Set Department_ID= '"+patientnew.getDepartmentnumber()+ "' where Floor_ID = '"+patientnew .getPatientid()+"'";
            connect.savePatient(query);
        }else if (patientnew.getFloornumber() != patientold.getFloornumber()){
            String query = "Update Floor Set Floor= '"+patientnew.getFloornumber()+ "' where Floor_ID = '"+patientnew .getPatientid()+"'";
            connect.savePatient(query);
        }else if (patientnew.getBuildingnumber() != patientold.getBuildingnumber()){
            String query = "Update Room Set RoomNumber= '"+patientnew.getBuildingnumber()+ "' where City = '"+patientnew.getBuildingname()+"'";
            connect.savePatient(query);
        }
    }

    public String checkLogIn(String name, String password){
        for (Employee employee:employees){
            if (name.equals(employee.getName()) && password.equals(employee.getPassword())){
                if (employee.getName().equals("Admin")){
                    return "admin";
                }else {
                    return "patient";
                }
            }
        }
        return null;
    }

    public ArrayList<Employee> getEmployeeList(){
        return employees;
    }

    public void setEmployeeList(ArrayList<Employee>list){
        this.employees= list;
    }

}
