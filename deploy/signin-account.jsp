<%-- 
    Document   : signin-account
    Created on : 9/08/2023, 8:54:39 pm
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
            <h1>Sign In</h1>

            <fieldset>

                <legend>Account Details</legend>
                <%
                    String validation = (String) session.getAttribute("validation");
                    validation = validation != null ? validation : "";
                %>

                <p><%= validation%></p>
                <form action="signin-account" method="POST">

                    <label for="username">Username:</label><br>
                    <input type="text" id="username" name="username" required><br>
                    <label for="pwd">Password:</label><br>
                    <input type="password" id="password" name="password" required>


                    <button type="submit">Sign In account</button>

                </form>

            </fieldset>

            <a class="nav" href="index.jsp">Back to Home</a>

            <main/>
    </body>
</html>
