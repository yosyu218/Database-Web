/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package gui;

import dao.ProductDAO;
import domain.Product;
import java.math.BigDecimal;
import java.util.Arrays;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.DialogFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductViewerSearchIDTest {

    private Robot robot;
    private ProductDAO dao;
    private DialogFixture fixture;

    @BeforeEach
    public void setUp() {
        robot = BasicRobot.robotWithNewAwtHierarchy();
        robot.settings().delayBetweenEvents(75);

        // create a mock for the DAO
        dao = mock(ProductDAO.class);
    }

    @AfterEach
    public void tearDown() {
        fixture.cleanUp();
    }

    @Test
    public void testSearchID() {
        // Create an instance of the ProductDAO mock
        ProductDAO dao = mock(ProductDAO.class);

        // Add some test products to the DAO
        Product product1 = new Product();
        product1.setName("Product 1");
        product1.setCategory("Category 1");
        product1.setListPrice(new BigDecimal(10));
        product1.setQuantityInStock(new BigDecimal(10));
        product1.setProductId("123");

        Product product2 = new Product();
        product2.setName("Product 2");
        product2.setCategory("Category 2");
        product2.setListPrice(new BigDecimal(10));
        product2.setQuantityInStock(new BigDecimal(10));
        product2.setProductId("456");

        when(dao.searchById("123")).thenReturn(product1);
        when(dao.searchById("456")).thenReturn(product2);

        // Create an instance of the ProductViewer class to test
        ProductViewer viewer = new ProductViewer(null, false, dao);

        // Show the dialog using AssertJ Swing's DialogFixture
        fixture = new DialogFixture(robot, viewer);
        fixture.show();

        // Verify that the JList contains 0 products initially
        fixture.list("lstProducts").requireItemCount(0);

        // Click the "Search" button to search for a specific product by ID
        fixture.textBox("txtSearchId").setText("123"); // Assuming "123" is the ID of the first product
        fixture.button("btnSearch").click();

        // Verify that the searched product is displayed in the JList
        fixture.list("lstProducts").requireItemCount(1); // Only one product should be displayed now

        // Click the "Search" button to search for a different product by ID
        fixture.textBox("txtSearchId").setText("456"); // Assuming "456" is the ID of the second product
        fixture.button("btnSearch").click();

        // Verify that the searched product is displayed in the JList
        fixture.list("lstProducts").requireItemCount(1); // Only one product should be displayed now

        // Close the dialog
        fixture.button("btnClose").click();
    }
}
