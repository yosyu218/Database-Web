/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import domain.Product;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import static org.assertj.core.api.Java6Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author yukiyoshiyasu
 */
public class ProductDAOTest {

    @BeforeAll
    public static void initialise() {
        JdbiDaoFactory.setJdbcUri("jdbc:h2:mem:tests;INIT=runscript from 'src/main/java/dao/schema.sql'");
    }

    //private ProductCollectionsDAO productCollectionsDAO;
    private ProductDAO dao;
    private Product p1;
    private Product p2;
    private Product p3;

    @BeforeEach
    public void setUp() {
        
        //dao = new ProductCollectionsDAO();
        dao = JdbiDaoFactory.getProductDAO();
        p1 = new Product();
        p1.setProductId("123");
        p1.setName("Nakamura");
        p1.setDescription("Thing");
        p1.setCategory("cd");
        p1.setListPrice(new BigDecimal("14.99"));
        p1.setQuantityInStock(new BigDecimal("10.00"));
  
        

        p2 = new Product();
        p2.setProductId("124");
        p2.setName("x2"); 
        p2.setDescription("Great");
        p2.setCategory("Poster");
        p2.setListPrice(new BigDecimal("9.99"));
        p2.setQuantityInStock(new BigDecimal("10.00"));
        
       

        p3 = new Product();
        p3.setProductId("125");
        p3.setName("Lucid");
        p3.setDescription("Colourful");
        p3.setCategory("Banner");
        p3.setListPrice(new BigDecimal("10.99"));
        p3.setQuantityInStock(new BigDecimal("10.00"));
        

        dao.saveProduct(p1);
        dao.saveProduct(p2);

    }

    @AfterEach
    public void tearDown() {
        dao.removeProduct(p1);
         dao.removeProduct(p2);
          dao.removeProduct(p3);
    }

    @Test
    public void testSaveProduct() {
        // make sure that item3 does not already exist
        assertThat(dao.getProducts()).doesNotContain(p3);
        //check size is 2
        assertEquals(2, dao.getProducts().size());
        //save p3
        dao.saveProduct(p3);
        //check size is 3
        assertEquals(3, dao.getProducts().size());
        //Check p1,p2,p3 are saved
        assertThat(dao.getProducts()).contains(p1, p2, p3);

    }

    @Test
    public void testRemoveProduct() {

        //Check p1,p2,p3 are saved
        assertThat(dao.getProducts()).contains(p1, p2);
        //size 2
        assertEquals(2, dao.getProducts().size());
        //delete it
        dao.removeProduct(p1);

        //size should be just 1 with p2 
        assertEquals(1, dao.getProducts().size());

        //check it contains 2
        assertThat(dao.getProducts()).contains(p2);
        // Create a mock of the ProductDAO

        ProductDAO dao = mock(ProductDAO.class);

        // Create some products to test with
        Product p1 = new Product();
        p1.setProductId("123");
        // Set other properties of p1...

        Product p2 = new Product();
        p2.setProductId("456");
        // Set other properties of p2...

        // Add the products to the collection
        dao.saveProduct(p1);
        dao.saveProduct(p2);

        // Stub the getProducts method to return the test products
        when(dao.getProducts()).thenReturn(new HashSet<>(Arrays.asList(p1, p2)));

        // Stub the delete method to remove the selected product from the collection
        doAnswer((invocation) -> {
            Product productToRemove = invocation.getArgument(0); // Get the product passed to the delete method
            dao.getProducts().remove(productToRemove); // Remove the product from the collection
            return null;
        }).when(dao).removeProduct(any());

    }

    @Test
    public void testGetProducts() {

        Collection<Product> products = dao.getProducts();

        // Check that the size of the collection matches the expected size (3)
        assertEquals(2, products.size());

        // Check that the collection contains the expected products (p1, p2, and p3)
        assertTrue(products.contains(p1));
        assertTrue(products.contains(p2));
        //assertTrue(products.contains(p3));

    }

    @Test
    public void testGetCategories() {
        Collection<String> allCategories = dao.getCategories();

        Collection<String> expectedCategories = new HashSet<>();

        expectedCategories.add(p1.getCategory());
        expectedCategories.add(p2.getCategory());

        assertThat(allCategories.size()).isEqualTo(expectedCategories.size());

    }

    @Test
    public void testSearchById() {

        //product1 Product
        Product product1 = dao.searchById("123");

        // Using AssertJ matchers
        assertThat(product1).isEqualTo(p1);
        assertThat(product1).usingRecursiveComparison().isEqualTo(p1);
        assertThat(product1).hasFieldOrPropertyWithValue("name", "Nakamura");
        assertThat(product1).hasFieldOrPropertyWithValue("Category", "cd");

        //product2 product
        Product product2 = dao.searchById("124");

        // Using AssertJ matchers
        assertThat(product2).isEqualTo(p2);
        assertThat(product2).usingRecursiveComparison().isEqualTo(p2);
        assertThat(product2).hasFieldOrPropertyWithValue("name", "x2");
        assertThat(product2).hasFieldOrPropertyWithValue("Category", "Poster");

        //negative tests
        Product product3 = dao.searchById("125");
        assertThat(product3).isNull();

    }

    @Test
    public void testFilterByCategory() {

        Collection<Product> filteredProducts = dao.filterByCategory("cd");

        // Ensure that the filtered products contain the correct products (p1) with the category "cd"
        assertThat(filteredProducts).contains(p1);

        // Ensure that the filtered products do not contain products with other categories (p2, p3)
        assertThat(filteredProducts).doesNotContain(p2, p3);

        // negative tests
        Collection<Product> filteredProductsB = dao.filterByCategory("NonExistentCategory");

        assertThat(filteredProductsB).isEmpty();

    }

}
