/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package gui;

import dao.ProductDAO;
import domain.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.DialogFixture;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author yukiyoshiyasu
 */
public class ProductEditorTest {

    private Robot robot;
    private ProductDAO dao;
    private DialogFixture fixture;

    @BeforeEach
    public void setUp() {
        robot = BasicRobot.robotWithNewAwtHierarchy();

        // Slow down the robot a little bit - default is 30 (milliseconds).
        // Do NOT make it less than 10 or you will have thread-race problems.
        robot.settings().delayBetweenEvents(75);

        // add some majors for testing with
        Collection<String> majors = new ArrayList<>();
        majors.add("Album");
        majors.add("Poster");

        // create a mock for the DAO
        dao = mock(ProductDAO.class);

        // stub the getMajors method to return the test majors
        when(dao.getCategories()).thenReturn(majors);

    }

    @AfterEach
    public void tearDown() {
        fixture.cleanUp();

    }

    @Test
    public void testSave() {
        // create the dialog passing in the mocked DAO
        ProductEditor dialog = new ProductEditor(null, true, dao);
        //StudentEditor dialog = new StudentEditor(null, true);

        // use AssertJ to control the dialog
        fixture = new DialogFixture(robot, dialog);

        // show the dialog on the screen, and ensure it is visible
        fixture.show().requireVisible();

        // click the dialog to ensure the robot is focused in the correct window
        // since it can get confused by multi-monitor and virtual desktop setups
        fixture.click();

        // enter some details into the UI components
        fixture.textBox("txtId").enterText("11111");
        fixture.textBox("txtName").enterText("Jack");
        fixture.textBox("txtDescription").enterText("Very cool");
        fixture.comboBox("cmbCategory").enterText("Poster");
        fixture.textBox("txtPrice").enterText("10");
        fixture.textBox("txtQuantity").enterText("10");

        fixture.button("btnSave").click();

        // create a Mockito argument captor to use to retrieve the passed product from the mocked DAO
        ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);

        // verify that the DAO.save method was called, and capture the passed product
        verify(dao).saveProduct(argument.capture());
        // retrieve the passed product from the captor
        Product savedProduct = argument.getValue();

        // test that the product's details were properly saved
        assertThat("Ensure the ID was saved", savedProduct.getProductId(), equalTo("11111"));

        assertThat("Ensure the Name was saved", savedProduct.getName(), equalTo("Jack"));
        assertThat("Ensure the price was saved", savedProduct.getListPrice(), equalTo(new BigDecimal(10)));
        assertThat("Ensure the quantity was saved", savedProduct.getQuantityInStock(), equalTo(new BigDecimal(10)));
        assertThat("Ensure the description was saved", savedProduct.getDescription(), equalTo("Very cool"));
        assertThat("Ensure the category was saved", savedProduct.getCategory(), equalTo("Poster"));

    }

}
