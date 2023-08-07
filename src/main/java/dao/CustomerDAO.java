/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import domain.Customer;
/**
 *
 * @author yukiyoshiyasu
 */

public interface CustomerDAO {

    void saveCustomer(Customer customer);
    
    void removeCustomer (Customer customer);
    
    Customer getCustomer(String username);
    
    boolean verifyCredentials(String username, String password);
    
    }
    
    
    
    
