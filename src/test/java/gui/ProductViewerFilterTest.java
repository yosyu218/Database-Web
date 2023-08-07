/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package gui;

import dao.ProductDAO;
import domain.Product;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.DialogFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductViewerFilterTest {

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
    public void testFilterByCategory() {
        // Create an instance of the ProductDAO mock
        ProductDAO dao = mock(ProductDAO.class);

         // Create some test products with different categories
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

        
        Collection<Product> products = new HashSet<>();
        products.add(product1);
        products.add(product2);

        // Stub the filterByCategory method to return the collection of products for each category
        when(dao.filterByCategory("Category 1")).thenReturn(products);
        when(dao.filterByCategory("Category 2")).thenReturn(products);      
       
        // Create an instance of the ProductViewer class to test
        ProductViewer viewer = new ProductViewer(null, false, dao);

        // Show the dialog using AssertJ Swing's DialogFixture
        fixture = new DialogFixture(robot, viewer);
        fixture.show();
    
        fixture.comboBox("cmbCategories").enterText("Category 1");

        // Verify that the JList contains 0 products initially
        fixture.list("lstProducts").requireItemCount(0);

        // Select the category "Category 1" from the combo box to filter the products
        fixture.comboBox("cmbCategories").selectItem(0);

        // Verify that the filtered products are displayed in the JList (only product1 with "Category 1" should be displayed)
        fixture.list("lstProducts").requireItemCount(1);
        fixture.list("lstProducts").requireSelection(0);

        // Select the category "Category 2" from the combo box to filter the products
        fixture.comboBox("cmbCategories").selectItem(1);

        // Verify that the filtered products are displayed in the JList (only product2 with "Category 2" should be displayed)
        fixture.list("lstProducts").requireItemCount(1);
        fixture.list("lstProducts").requireSelection(0);

        // Close the dialog
        fixture.button("btnClose").click();
    }
}
