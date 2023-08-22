/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import domain.Product;
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
public interface ProductJdbiDAO extends ProductDAO {


    @Override
    @SqlQuery("select * from Product where ProductId = :productId")
    @RegisterBeanMapper(Product.class)
    public Product searchById(@Bind ("productId")String productId);

    @Override
    @SqlUpdate("insert into Product(ProductId, Name, Description, Category, ListPrice, QuantityInStock) values (:productId, :name, :description, :category, :listPrice, :quantityInStock)")
    public void saveProduct(@BindBean Product product);

    @Override
    @SqlUpdate("delete from Product where ProductId = :productId")
    public void removeProduct(@BindBean Product product);

    
    @Override
    @SqlQuery("select * from Product order by productId")
    @RegisterBeanMapper(Product.class)
    public Collection<Product> getProducts();
    
    
    @Override
    @SqlQuery("select distinct category from Product order by category ASC")
    public Collection<String> getCategories();
    

    @Override
    @SqlQuery("select * from Product where Category  = :category")
    @RegisterBeanMapper(Product.class)
    public Collection<Product> filterByCategory(@Bind("category")String category);
    
  
    
}
