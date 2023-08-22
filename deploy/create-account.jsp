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
            <%@include file="WEB-INF/jspf/navigation.jspf"%>
            <h1>Create a new account</h1>

            <fieldset>

                <legend>Account Details</legend>
                <%
                    String validation = (String) session.getAttribute("validation");
                    validation = validation != null ? validation : "";
                %>

                <p><%= validation%></p>
                <form action="create-account" method="POST">

<!--                <label for="customerId">Customer ID:</label> <input type="number" id="customerId" name="customerId" required><br>-->
                    <label for="firstname">First Name:</label> <input type="text" id="firstname" name="firstname" required><br>
                    <label for="surname">Surname:</label> <input type="text" id="surname" name="surname" required><br>
                    <label for="email">Email address:</label> <input type="email" id="email" name="email"><br>
                    <label for="address">Shipping Address:</label> <input type="text" id="address" name="address" required=""><br>
                    <label for="username">Username:</label> <input type="text" id="username" name="username" required><br>
                    <label for="pwd">Password:</label> <input type="password" id="password" name="password" required>


                    <button type="submit">Create account</button>

                </form>

            </fieldset>

            <a class="nav" href="index.jsp">Back to Home</a>

            <main/>
    </body>
</html>
