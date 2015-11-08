import javax.servlet.ServletException;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;
        import java.io.PrintWriter;
        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.PreparedStatement;

public class RegisterCorporateCustomer extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String corporationName = request.getParameter("corporationName");
        String financialCode = request.getParameter("financialCode");
        String registerDate = request.getParameter("registerDate");

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "oracle");

            PreparedStatement preparedStatement = connection.prepareStatement("insert into corporateCustomer values(?,?,?)");

            preparedStatement.setString(1, corporationName);
            preparedStatement.setString(2, financialCode);
            preparedStatement.setString(3, registerDate);

            int i = preparedStatement.executeUpdate();
            if (i > 0)
                out.print("You are successfully registered...");


        } catch (Exception e2) {
            System.out.println(e2);
        }

        out.close();
    }

}