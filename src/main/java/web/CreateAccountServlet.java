/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package web;

import dao.CustomerCollectionsDAO;
import dao.CustomerDAO;
import domain.Customer;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.exception.ConstraintsViolatedException;

/**
 *
 * @author yukiyoshiyasu
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/create-account"})
public class CreateAccountServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CustomerDAO dao = new CustomerCollectionsDAO();
        try {
            // extracts the form data
            Integer customerId = Integer.valueOf(request.getParameter("customerId"));
            String username = request.getParameter("username");
            String firstName = request.getParameter("firstname");
            String surname = request.getParameter("surname");
            String shippingAddress = request.getParameter("address");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            // create the customer object
            Customer customer = new Customer(customerId, username, firstName, surname, shippingAddress, email);

            customer.setPassword(password);
            new Validator().assertValid(customer);
            // save the customer
            dao.saveCustomer(customer);
            response.sendRedirect("index.jsp");
        } catch (NumberFormatException e) {
            // Handle invalid ID input
            HttpSession session = request.getSession();
            session.setAttribute("validation", "You have entered an invalid ID");
            response.sendRedirect("create-account.jsp");
        }catch (ConstraintsViolatedException ex) {

	// get the violated constraints from the exception
	ConstraintViolation[] violations = ex.getConstraintViolations();

	// create a nice error message for the user
	String msg = "Please fix the following input problems:";

	msg += "<ul>";
	for (ConstraintViolation cv : violations) {
		msg += "<li>" + cv.getMessage() + "</li>";
	}
	msg += "</ul>";

	request.getSession().setAttribute("validation", msg);
	response.sendRedirect("create-account.jsp");
}
    }
}
