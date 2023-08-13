/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import domain.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author yukiyoshiyasu
 */
public class CustomerCollectionsDAOTest {

    private CustomerDAO customerDAO;
    private Customer c1;
    private Customer c2;

    @BeforeEach
    public void setUp() {
        customerDAO = new CustomerCollectionsDAO();

        c1 = new Customer(1, "testuser1", "Carlos", "Alca", "123 Castle st", "carlos@gmail.com");
        c2 = new Customer(2, "testuser2", "Ben", "Smith", "456 Cumberland St", "ben@gmail.com");

        customerDAO.saveCustomer(c1);
        customerDAO.saveCustomer(c2);
    }

    @AfterEach
    public void tearDown() {
        customerDAO.removeCustomer(c1);
        customerDAO.removeCustomer(c2);
    }

    @Test
    public void testSaveCustomer() {

        Customer c3 = new Customer(3, "newuser", "Alice", "Jason", "789 Queen St", "alice@gmail.com");
        customerDAO.saveCustomer(c3);

        Customer savedCustomer = customerDAO.getCustomerByUsername("newuser");
        assertNotNull(savedCustomer);
        assertEquals("newuser", savedCustomer.getUsername());
    }

    @Test
    public void testRemoveCustomer() {
        customerDAO.removeCustomer(c1);

        Customer removedCustomer = customerDAO.getCustomerByUsername("testuser1");
        assertNull(removedCustomer);
    }

    @Test
    public void testVerifyCredentials() {
        c1.setPassword("password123");
        customerDAO.saveCustomer(c1);

        assertTrue(customerDAO.verifyCredentials("testuser1", "password123"));
        assertFalse(customerDAO.verifyCredentials("testuser1", "wrongpassword"));
    }

    @Test
    public void testGetCustomer() {
        Customer retrievedCustomer = customerDAO.getCustomerByUsername("testuser1");
        assertNotNull(retrievedCustomer);
        assertEquals("testuser1", retrievedCustomer.getUsername());
    }
}
