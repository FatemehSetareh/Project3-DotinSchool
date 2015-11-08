import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class Search extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String rollno = request.getParameter("roll");
        int roll = Integer.valueOf(rollno);

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "oracle");

            PreparedStatement ps = con.prepareStatement("select * from result where rollno=?");
            ps.setInt(1, roll);

            out.print("<table width=50% border=1>");
            out.print("<caption>Result:</caption>");

            ResultSet rs = ps.executeQuery();
              
/* Printing column names */
            ResultSetMetaData rsmd = rs.getMetaData();
            int total = rsmd.getColumnCount();
            out.print("<tr>");
            for (int i = 1; i <= total; i++) {
                out.print("<th>" + rsmd.getColumnName(i) + "</th>");
            }

            out.print("</tr>");
              
/* Printing result */

            while (rs.next()) {
                out.print("<tr><td>" + rs.getInt(1) + "</td><td>" + rs.getString(2) + "</td><td>" + rs.getString(3) + "</td><td>" + rs.getString(4) + "</td></tr>");

            }

            out.print("</table>");

        } catch (Exception e2) {
            e2.printStackTrace();
        } finally {
            out.close();
        }

    }
}  