import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.*;
import java.util.ArrayList;

public class ActualCustomerListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent e) {

        ArrayList actualCustomerList = new ArrayList();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "oracle");

            PreparedStatement preparedStatement = connection.prepareStatement("select * from actualCustomer");
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                ActualCustomer actualCustomer = new ActualCustomer();
                actualCustomer.setFirstName(result.getString(1));
                actualCustomer.setLastName(result.getString(2));
                actualCustomer.setFatherName(result.getString(3));
                actualCustomer.setNationalCode(result.getString(4));
                actualCustomer.setBirthdayDate(result.getDate(5));
                actualCustomer.setCustomerNumber(result.getInt(6));
                actualCustomerList.add(actualCustomer);
            }
            connection.close();

        } catch (Exception ex) {
            System.out.print(ex);
        }

        //storing the ArrayList object in ServletContext
        ServletContext context = e.getServletContext();
        context.setAttribute("data", actualCustomerList);

    }

    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("project unDeployed...");
    }

}  