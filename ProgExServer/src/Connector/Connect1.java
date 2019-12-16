package Connector;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import model.Employee;
import model.Patients;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.sql.*;

public class Connect1 {

    public ArrayList<Patients> getPatient() throws SQLException{
        int lport=5656;

        String rhost="kschiffh.goip.de";
        String host="kschiffh.goip.de";

        int rport=3306;

        String user="pi";
        String password="wimsbach";



        String url = "jdbc:mysql://kschiffh.goip.de/hospital";

        Connection conn = null;
        Session session= null;

        ArrayList<Patients> list = new ArrayList<>();

        try
        {
            //Set StrictHostKeyChecking property to no to avoid UnknownHostKey issue
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            session=jsch.getSession(user, host, 22);
            session.setPassword(password);
            session.setConfig(config);

            session.connect();
            System.out.println("Connected");
            int assinged_port=session.setPortForwardingL(lport, rhost, rport);
            System.out.println("localhost:"+assinged_port+" -> "+rhost+":"+rport);
            System.out.println("Port Forwarded");

            Class.forName ("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection (url,"pi","wimsbach");
            System.out.println ("Database connection established");

            //Patient information
            String query = "SELECT * FROM Patient";
            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                list.add(new Patients(rs.getInt("Patient_ID"),rs.getString("FirstName"),rs.getString("LastName"), LocalDate.parse(rs.getDate("Birthday").toString()),rs.getString("Notes")));
            }

            //missing: Doctor, OtherDoctor, Room, Bed

            //Street information/Patient address
            query = "SELECT * FROM Patient_Adress";
            ResultSet rs1 = st.executeQuery(query);

            while (rs1.next()){
                for(Patients patients:list){
                    if (rs1.getInt("Patient_ID")== patients.getPatientid()){
                        patients.setCountry(rs1.getString("Country"));
                        patients.setZip(rs1.getInt("ZIPCode"));
                        patients.setStreet(rs1.getString("Street"));
                        patients.setHousenumber(rs1.getInt("HouseNumber"));
                    }
                }
            }

            //Room information

            query = "SELECT * FROM Room";
            ResultSet rs2 = st.executeQuery(query);

            int i = 0;
            while (rs2.next()){
                if(i > list.size()-1){
                    break;
                }
                list.get(i).setRoom(rs2.getInt("RoomNumber"));
                list.get(i).setRoomid(rs2.getInt("Room_ID"));
                i++;
            }

            query = "SELECT * FROM Department";
            ResultSet rs3 = st.executeQuery(query);

            i=0;
            while (rs3.next()){
                if (i > list.size()-1){
                    break;
                }
                list.get(i).setDepartmentnumber(rs3.getInt("Department_ID"));
                i++;
            }

            query = "SELECT * FROM Floor";
            ResultSet rs4 = st.executeQuery(query);

            i=0;
            while (rs4.next()){
                if (i> list.size()-1){
                    break;
                }
                list.get(i).setFloornumber(rs4.getInt("Floor"));
                i++;
            }

            query = "SELECT * FROM Building";
            ResultSet rs5 = st.executeQuery(query);
            i=0;
            while (rs5.next()){
                if (i> list.size()-1){
                    break;
                }
                list.get(i).setBuildingname(rs5.getString("City"));
                list.get(i).setBuildingnumber(rs5.getInt("Building_ID"));
                i++;
            }



            //doctors
            //Select LastName FROM Employees E,Medical_Doctor M WHERE E.Medical_ID = M.Medical_ID
            query = "SELECT LastName From Employees E,Medical_Doctor M WHERE E.Employees_ID = M.Employees_ID";
            //if list > result -> result wiederholen
            //if list = result -> fine
            //if list < result -> list wiederholen
            ResultSet resultSet = st.executeQuery(query);
            i = 0;

            if (resultSet.getFetchSize() == list.size()){
                while (resultSet.next()){
                    list.get(i).setDoctor(resultSet.getString("LastName"));

                    i++;
                }
            }else if(resultSet.getFetchSize() > list.size()) {
                while (resultSet.next()){
                    if (i == (list.size()-1)){
                        i=0;
                    }

                    list.get(i).setDoctor(resultSet.getString("LastName"));

                    i++;
                }
            }
            else {
                System.out.println(3);
                ArrayList<String> list1 = new ArrayList<>();
                while (resultSet.next()){
                    list1.add(resultSet.getString("LastName"));
                }

                for(String string: list1){
                    System.out.println(string);
                }

                for (Patients patients:list){
                    if (list1.size() == i){
                        i = 0;
                    }
                    patients.setDoctor(list1.get(i));

                    i++;
                }

            }


            return list;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            if(conn != null && !conn.isClosed()){
                System.out.println("Closing Database Connection");
                conn.close();
            }
            if(session !=null && session.isConnected()){
                System.out.println("Closing SSH Connection");
                session.disconnect();
            }
        }
    }

    public ArrayList<Employee> getEmployees() throws SQLException{

        int lport=5656;

        String rhost="kschiffh.goip.de";
        String host="kschiffh.goip.de";

        int rport=3306;

        String user="pi";
        String password="wimsbach";



        String url = "jdbc:mysql://kschiffh.goip.de/hospital";

        Connection conn = null;
        Session session= null;

        ArrayList<Employee> list = new ArrayList<>();

        try
        {
            //Set StrictHostKeyChecking property to no to avoid UnknownHostKey issue
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            session=jsch.getSession(user, host, 22);
            session.setPassword(password);
            session.setConfig(config);

            session.connect();
            System.out.println("Connected");
            int assinged_port=session.setPortForwardingL(lport, rhost, rport);
            System.out.println("localhost:"+assinged_port+" -> "+rhost+":"+rport);
            System.out.println("Port Forwarded");

            Class.forName ("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection (url,"pi","wimsbach");
            System.out.println ("Database connection established");

            String query = "SELECT * FROM Employees";
            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);

            while (rs.next()){
                list.add(new Employee(rs.getString("Firstname"),rs.getString("LastName"),rs.getString("Password"),LocalDate.parse(rs.getDate("Birthday").toString())));
            }

            return list;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            if(conn != null && !conn.isClosed()){
                System.out.println("Closing Database Connection");
                conn.close();
            }
            if(session !=null && session.isConnected()){
                System.out.println("Closing SSH Connection");
                session.disconnect();
            }
        }


    }

    public void savePatient(String query){
        Connection conn = null;

        try
        {

            String url = "jdbc:mysql://kschiffh.goip.de/hospital";
            Class.forName ("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection (url,"pi","wimsbach");
            System.out.println ("Database connection established");

            Statement statement = conn.createStatement();
            statement.executeUpdate(query);

            conn.commit();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close ();
                    System.out.println ("Database connection terminated");
                }
                catch (Exception e) { /* ignore close errors */ }
            }
        }
    }

    public void saveEmployees(String query){}
}
