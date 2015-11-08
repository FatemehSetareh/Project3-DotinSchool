import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.*;
import java.util.ArrayList;

public class CorporateCustomerListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent e) {

        ArrayList corporateCustomerList = new ArrayList();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "oracle");

            PreparedStatement preparedStatement = connection.prepareStatement("select * from corporateCustomer");
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                CorporateCustomer corporateCustomer = new CorporateCustomer();
                corporateCustomer.setCorporationName(result.getString(1));
                corporateCustomer.setFinancialCode(result.getString(2));
                corporateCustomer.setRegisterDate(result.getDate(3));
                corporateCustomer.setCustomerNumber(result.getInt(4));
                corporateCustomerList.add(corporateCustomer);
            }
            connection.close();

        } catch (Exception ex) {
            System.out.print(ex);
        }

        //storing the ArrayList object in ServletContext
        ServletContext context = e.getServletContext();
        context.setAttribute("data", corporateCustomerList);

    }

    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("project unDeployed...");
    }

}