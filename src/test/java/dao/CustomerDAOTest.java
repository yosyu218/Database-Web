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
import org.junit.jupiter.api.BeforeAll;

/**
 *
 * @author yukiyoshiyasu
 */
public class CustomerDAOTest {

    @BeforeAll
    public static void initialise() {
        JdbiDaoFactory.setJdbcUri("jdbc:h2:mem:tests;INIT=runscript from 'src/main/java/dao/schema.sql'");
    }
    
   private CustomerDAO dao;
    private Customer c1;
    private Customer c2;

    @BeforeEach
    public void setUp() {
        //dao = new CustomerCollectionsDAO();
        dao = JdbiDaoFactory.getCustomerDAO();

        c1 = new Customer( "testuser1", "Carlos", "Alca", "123 Castle st", "carlos@gmail.com","word");
        c2 = new Customer( "testuser2", "Ben", "Smith", "456 Cumberland St", "ben@gmail.com","ahha");
        //c1 = new Customer( "testuser1", "Carlos", "Alca", "123 Castle st", "carlos@gmail.com","pass");
        //c2 = new Customer( "testuser2", "Ben", "Smith", "456 Cumberland St", "ben@gmail.com","word");

        dao.saveCustomer(c1);
        dao.saveCustomer(c2);
    }

    @AfterEach
    public void tearDown() {
        dao.removeCustomer(c1);
        dao.removeCustomer(c2);
    }

    @Test
    public void testSaveCustomer() {

        Customer c3 = new Customer( "newuser", "Alice", "Jason", "789 Queen St", "alice@gmail.com","pass");
        //Customer c3 = new Customer("newuser", "Alice", "Jason", "789 Queen St", "alice@gmail.com","passpass");

        dao.saveCustomer(c3);

        Customer savedCustomer = dao.getCustomerByUsername("newuser");
        assertNotNull(savedCustomer);
        assertEquals("newuser", savedCustomer.getUsername());
    }

    @Test
    public void testRemoveCustomer() {
        dao.removeCustomer(c1);

        Customer removedCustomer = dao.getCustomerByUsername("testuser1");
        assertNull(removedCustomer);
    }

    @Test
    public void testVerifyCredentials() {
        c1.setPassword("password123");
        dao.saveCustomer(c1);

        assertTrue(dao.verifyCredentials("testuser1", "password123"));
        assertFalse(dao.verifyCredentials("testuser1", "wrongpassword"));
    }

    @Test
    public void testGetCustomer() {
        Customer retrievedCustomer = dao.getCustomerByUsername("testuser1");
        assertNotNull(retrievedCustomer);
        assertEquals("testuser1", retrievedCustomer.getUsername());
    }
}
