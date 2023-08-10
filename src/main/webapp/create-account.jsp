<%-- 
    Document   : create-account
    Created on : 9/08/2023, 8:54:06 pm
    Author     : yukiyoshiyasu
--%>

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
            <h1>Create a new account</h1>

            <fieldset>

                <legend>Account Details</legend>
                <%
                    String validation = (String) session.getAttribute("validation");
                    validation = validation != null ? validation : "";
                %>

                <p><%= validation%></p>
                <form action="create-account" method="POST">

                     <label for="customerId">Customer ID:</label><br>
                    <input type="text" id="customerId" name="customerId"><br>
                    <label for="firstname">First Name:</label><br>
                    <input type="text" id="firstname" name="firstname"><br>
                    <label for="surname">Surname:</label><br>
                    <input type="text" id="surname" name="surname"><br>
                    <label for="email">Email address:</label><br>
                    <input type="email" id="email" name="email"><br>
                    
                    <label for="address">Shipping Address:</label><br>
                    <input type="text" id="address" name="address"><br>
                    
                    <label for="username">Username:</label><br>
                    <input type="text" id="username" name="username"><br>
                    <label for="pwd">Password:</label><br>
                    <input type="password" id="password" name="password">


                    <button type="submit">Create account</button>

                </form>

            </fieldset>

            <a class="nav" href="index.jsp">Back to Home</a>

            <main/>
    </body>
</html>
