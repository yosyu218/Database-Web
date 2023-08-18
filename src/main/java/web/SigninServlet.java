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

/**
 *
 * @author yukiyoshiyasu
 */
@WebServlet(name = "SigninServlet", urlPatterns = {"/signin-account"})
public class SigninServlet extends HttpServlet {

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
            // Extracts the password and username info
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            // Check credentials info
            boolean credentialsVerified = dao.verifyCredentials(username, password);
            if (credentialsVerified) {
                // Get the customer by username
                Customer customer = dao.getCustomerByUsername(username);

                // Set the customer attribute in the session
                HttpSession session = request.getSession();
                session.setAttribute("customer", customer);

                response.sendRedirect("view-products.jsp");
            } else {
                // Display a message popup for incorrect credentials
                String errorMessage = "Wrong credential info";
                String alertScript = "<script>alert('" + errorMessage + "');</script>";

                // Append the alert script to the response output
                response.getWriter().write(alertScript);

                // Forward back to the signin-account page
                request.getRequestDispatcher("signin-account.jsp").forward(request, response);
            }

        } catch (Exception e) {
            // Handle any exceptions that may occur
            String errorMsg = "An error occurred: " + e.getMessage();
            request.setAttribute("error", errorMsg);
            request.getRequestDispatcher("error-page.jsp").forward(request, response);
        }
    }

}
