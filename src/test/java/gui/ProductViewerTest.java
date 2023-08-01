/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package gui;

import dao.ProductDAO;
import domain.Product;
import helpers.SimpleListModel;
import java.math.BigDecimal;
import java.util.Arrays;
import static org.assertj.core.api.Java6Assertions.assertThat;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.DialogFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author yukiyoshiyasu
 */
public class ProductViewerTest {

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
public void testView() {
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

    when(dao.getProducts()).thenReturn(Arrays.asList(product1, product2));

    // Create an instance of the ProductViewer class to test
    ProductViewer viewer = new ProductViewer(null, false, dao);

    // Show the dialog using AssertJ Swing's DialogFixture
    fixture = new DialogFixture(robot, viewer);
    fixture.show();

    // Verify that the JList contains 2 products
    fixture.list("lstProducts").requireItemCount(2);
    
    
    // Get the model
    SimpleListModel model = (SimpleListModel) fixture.list("lstProducts").target().getModel();

    // Check the contents
    assertThat(model.getElementAt(0)).isEqualTo(product1);
    assertThat(model.getElementAt(1)).isEqualTo(product2);

    // Close the dialog
    fixture.button("btnClose").click();
}

}
