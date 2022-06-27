<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Deal List</title>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h3><a href="clients">Client list</a></h3>
    <hr/>

    <jsp:useBean id="client" type="ru.fulkin.servletlinux.model.Client" scope="request"/>
    <h2>Deal List for ${client.id} client</h2>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Product Name</th>
            <th>Price</th>
            <th>Amount</th>
            <th>Sum</th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.dealsToList}" var="dealToList">
            <tr>
                <td>${dealToList.date.toLocalDate()} ${dealToList.date.toLocalTime()}</td>
                <td>${dealToList.product.name}</td>
                <td>${dealToList.product.price}</td>
                <td>${dealToList.amount}</td>
                <td>${dealToList.product.price * dealToList.amount}</td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>