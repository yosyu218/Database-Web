<%-- 
    Document   : view-products
    Created on : 9/08/2023, 8:55:40 pm
    Author     : yukiyoshiyasu
--%>
<%@page import="java.util.Collection"%>
<%@page import="domain.Product"%>
<%@page import="dao.ProductCollectionsDAO"%>
<%@page import="dao.ProductDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/style.css"/>

    </head>
    <body>
        <main>
            <%@include file="WEB-INF/jspf/navigation.jspf"%>
            <h1>View all products</h1>

            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Category</th>
                        <th>listPrice</th>
                        <th>quantityInStock</th>

                    </tr>
                </thead>
                <tbody>
                    <%
                        ProductDAO dao = new ProductCollectionsDAO();

                        // get the category from the query parameter
                        String selectedCategory = request.getParameter("category");

                        // declare the students collection
                        Collection<Product> products;

                        // if there is no major parameter, or "All" is requested, return all students
                        if (selectedCategory == null || selectedCategory.equals("All")) {
                            products = dao.getProducts();
                        } else {
                            // otherwise, get the students for the requested major
                            products = dao.filterByCategory(selectedCategory);
                        }

                        for (Product product : products) {
                    %>
                    <tr>
                        <td><%= product.getProductId()%></td>
                        <td><%= product.getName()%></td>
                        <td><%= product.getDescription()%></td>
                        <td><%= product.getCategory()%></td>
                        <td><%= product.getListPrice()%></td>
                        <td><%= product.getQuantityInStock()%></td>
                        <td><input type="hidden" name="id" value="<%= product.getProductId()%>"><button>Buy</button></form></td>
                        
                    </tr>
                    <%
                        }
                    %>

                </tbody>
                <a href="view-products.jsp?category=All"><button>All</button></a>

                <%
                    Collection<String> categories = dao.getCategories();

                    for (String category : categories) {
                %>

                <a href="view-products.jsp?category=<%= category%>"><button><%= category%></button></a>

                <%
                    }
                %>
            </table>

            <a class="nav" href="index.jsp">Back to Home</a>

        </main>
    </body>
</html>
