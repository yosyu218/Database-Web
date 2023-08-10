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
        // extract the form data
       
        //what to do with customer ID?
        Integer customerId = Integer.valueOf(request.getParameter("customerId"));
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstname");
        String surname = request.getParameter("surname");
        String shippingAddress = request.getParameter("address");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

// create the student object
        Customer customer = new Customer(customerId, username, firstName, surname, shippingAddress, email);
        
        customer.setPassword(password);
// save the student
        dao.saveCustomer(customer);
        
    }

}
