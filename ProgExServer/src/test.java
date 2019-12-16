import model.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class test {
    public static void main(String[] args){
        Connection conn = null;

        ArrayList<Employee> list = new ArrayList<>();

        try
        {

            String url = "jdbc:mysql://kschiffh.goip.de/hospital";
            Class.forName ("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection (url,"pi","wimsbach");
            System.out.println ("Database connection established");

            String query = "SELECT * FROM Employees";
            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                System.out.println(rs.getString("FirstName")+" "+rs.getString("Password"));
            }



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
}
