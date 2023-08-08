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
        
        
        

        // Mock DAO methods
        when(dao.getProducts()).thenReturn(products);
        when(dao.getCategories()).thenReturn(new HashSet<>(Arrays.asList("Category 1")));

        // Create the fixture and show the dialog
        fixture = new DialogFixture(robot, new ProductViewer(null, true, dao));
        fixture.show();
       




        
    }

    @AfterEach
    public void tearDown() {
        fixture.cleanUp();
    }

    @Test
    public void testFilterByCategory() {


// Verify that the combo box is populated with the expected categories
    
        fixture.comboBox("cmbCategories").selectItem("Category 1");
        fixture.comboBox("cmbCategories").requireItemCount(1);
        
 

       
        fixture.button("btnClose").click();
    }
}
