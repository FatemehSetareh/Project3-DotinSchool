import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

/*This servlet gets the information from the servlet context object and prints it*/
public class ActualCustomerInformation extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        ServletContext context = getServletContext();
        List list = (List) context.getAttribute("data");

        Iterator itr = list.iterator();
        while (itr.hasNext()) {
            ActualCustomer actualCustomer = (ActualCustomer) itr.next();
            out.print("<br>" + actualCustomer.getFirstName()
                    + " " + actualCustomer.getLastName()
                    + " " + actualCustomer.getFatherName()
                    + " " + actualCustomer.getNationalCode()
                    + " " + actualCustomer.getBirthdayDate()
                    + " " + actualCustomer.getCustomerNumber());
        }

        out.close();
    }

}
