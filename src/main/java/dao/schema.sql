/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  yukiyoshiyasu
 * Created: 18/08/2023
 */

CREATE TABLE IF NOT EXISTS Product (
    productId INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(500),
    category VARCHAR(255),
    listPrice NUMERIC(10, 2),
    quantityInStock NUMERIC(10, 2),
   
    CONSTRAINT chk_id_positive CHECK (productId > 0),
    CONSTRAINT chk_name_length CHECK (LENGTH(name) >= 2)
);
