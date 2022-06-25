<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Client List</title>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr/>
    <h2>Persons</h2>
    <a href="clients?action=create">Add client</a>
    <br>
    <a href="clients?action=product">See products</a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Id</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>City</th>
            <th>Phone</th>
            <th>Deals List</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.clients}" var="client">
            <jsp:useBean id="client" type="ru.fulkin.servletlinux.model.Client"/>
            <tr>
                <td>${client.id}</td>
                <td>${client.firstname}</td>
                <td>${client.lastname}</td>
                <td>${client.city}</td>
                <td>${client.phone}</td>
                <td><a href="clients?action=deallist&id=${client.id}">Deals</a></td>
                <td><a href="clients?action=update&id=${client.id}">Update</a></td>
                <td><a href="clients?action=delete&id=${client.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
