/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;


import domain.Product;
import java.math.BigDecimal;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author yukiyoshiyasu
 */
public class ProductCollectionsDAOTest {
    private ProductCollectionsDAO productCollectionsDAO;
    private Product p1;
    private Product p2;
    private Product p3;
    
    @BeforeEach
    public void setUp() {
       
       productCollectionsDAO = new ProductCollectionsDAO();
      
       p1 = new Product();
       p1.setName("Nakamura");
       p1.setCategory("cd");
       p1.setProductId("123");
       p1.setQuantityInStock(new BigDecimal(10));
       p1.setListPrice(new BigDecimal(14.99));
       p1.setDescription("Thing");
       
       p2 = new Product();
       p2.setName("x2");
       p2.setCategory("Poster");
       p2.setProductId("124");
       p2.setQuantityInStock(new BigDecimal(10));
       p2.setListPrice(new BigDecimal(9.99));
       p2.setDescription("Great");
       
       p3 = new Product();
       p3.setName("Lucid");
       p3.setCategory("Banner");
       p3.setProductId("125");
       p3.setListPrice(new BigDecimal(10.99));
       p3.setQuantityInStock(new BigDecimal(10));
       p3.setDescription("Colourful");
       
       
       
       productCollectionsDAO.saveProduct(p1);
       productCollectionsDAO.saveProduct(p2);
       
    }

   
    
    @AfterEach
    public void tearDown() {
       
    }

    @Test
    public void testSaveProduct() {
        // make sure that item3 does not already exist
        assertThat(productCollectionsDAO.getProducts()).doesNotContain(p3);
        //check size is 2
        assertEquals(2, productCollectionsDAO.getProducts().size());
        //save p3
        productCollectionsDAO.saveProduct(p3);
        //check size is 3
        assertEquals(3, productCollectionsDAO.getProducts().size());
        //Check p1,p2,p3 are saved
        assertThat(productCollectionsDAO.getProducts()).contains(p1, p2,p3);
        
        
    }

    @Test
    public void testRemoveProduct() {
        //Check p1,p2,p3 are saved
        assertThat(productCollectionsDAO.getProducts()).contains(p1, p2);
        //size 2
        assertEquals(2, productCollectionsDAO.getProducts().size());
        //delete it
        productCollectionsDAO.removeProduct(p1);
        
        //size should be just 1 with p2 
        assertEquals(1, productCollectionsDAO.getProducts().size());
        
        //check it contains 2
        assertThat(productCollectionsDAO.getProducts()).contains(p2);
        
        
    }

    @Test
    public void testGetProducts() {
    }

    @Test
    public void testGetCategories() {
    }

    @Test
    public void testSearchById() {
    }

    @Test
    public void testFilterByCategory() {
    }
    
}
