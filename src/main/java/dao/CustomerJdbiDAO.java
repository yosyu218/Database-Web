/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import domain.Customer;
import java.util.Collection;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 *
 * @author yukiyoshiyasu
 */
public interface CustomerJdbiDAO extends CustomerDAO {

    @Override
    @SqlQuery("SELECT EXISTS (SELECT * FROM Customer WHERE Username = :username AND Password = :password)")
    boolean verifyCredentials(@Bind("username") String username, @Bind("password") String password);

    

    @Override
    @SqlQuery("select * from Customer where username = :username")
    @RegisterBeanMapper(Customer.class)
    public Customer getCustomerByUsername(@Bind ("username") String customerId);

     
    
    @Override
    @SqlUpdate("DELETE FROM Customer WHERE username = :username")
    public void removeCustomer(@BindBean Customer customer);

    @Override
    @SqlUpdate("INSERT INTO Customer (Username, FirstName,Surname,ShippingAddress,EmailAddress, Password) VALUES ( :username, :firstName,:surname,:shippingAddress,:emailAddress, :password)")
    public void saveCustomer(@BindBean Customer customer);

    
    
}
