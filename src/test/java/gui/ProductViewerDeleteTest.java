package gui;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
import dao.ProductDAO;
import domain.Product;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.DialogFixture;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;

public class ProductViewerDeleteTest {

    private Robot robot;
    private ProductDAO dao;
    private DialogFixture fixture;
    private List<Product> products;

  @BeforeEach
public void setUp() {
    robot = BasicRobot.robotWithNewAwtHierarchy();
    robot.settings().delayBetweenEvents(75);

    // create a mock for the DAO
    dao = mock(ProductDAO.class);

    // Add some test products to the DAO
    Product product1 = new Product();
    product1.setName("Product 1");

  //  Product product2 = new Product();
  //  product2.setName("Product 2");

Collection<Product> products = new HashSet<>();
products.add(product1);



// stub the getAll method that is used to display the students
when(dao.getProducts()).thenReturn(products);

// stub the delete method and add behaviour that actually deletes a student
Mockito.doAnswer((call) -> {
	// remove s1 collection that getAll() uses
	products.remove(product1);
	return null;
}).when(dao).removeProduct(product1);
}


    @AfterEach
    public void tearDown() {
        fixture.cleanUp();
    }



    @Test
public void testDelete() {
    // Create an instance of the ProductViewer class to test
    ProductViewer viewer = new ProductViewer(null, false, dao);

    // Show the dialog using AssertJ Swing's DialogFixture
    fixture = new DialogFixture(robot, viewer);
    fixture.show();
    verify(dao).getProducts();

    // Get the product to delete
    Product productToDelete = dao.getProducts().stream()
            .filter(product -> product.getName().equals("Product 1"))
            .findFirst()
            .orElse(null);

    // Verify that the product to delete is found
    assertNotNull(productToDelete, "Product 1 not found in the list");

    // Select the product to be deleted
    fixture.list("lstProducts").selectItem(productToDelete.toString());

    // Click the Delete button to initiate the delete operation
    fixture.button("btnDelete").click();

    // Verify that the option pane confirming the deletion is visible and click the Yes button
    fixture.optionPane().requireVisible().yesButton().click();

    // Verify that the delete() method of the DAO is called with the selected product
    Mockito.verify(dao).removeProduct(productToDelete);

    // Update the stub for getProducts() to return an updated list without the deleted product
    List<Product> updatedProductList = dao.getProducts().stream()
            .filter(product -> !product.equals(productToDelete))
            .collect(Collectors.toList());
    when(dao.getProducts()).thenReturn(updatedProductList);

    // product1 should be deleted
    fixture.list("lstProducts").requireItemCount(0);

    // Close the dialog
    fixture.button("btnClose").click();
}


    
}
