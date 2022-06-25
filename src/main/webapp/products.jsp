<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Product List</title>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr/>
    <h2>Products</h2>
    <h3><a href="clients">Client list</a></h3>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Price</th>
            <th>Remnant</th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.products}" var="product">
            <jsp:useBean id="product" type="ru.fulkin.servletlinux.model.Product"/>
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.remnant}</td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
