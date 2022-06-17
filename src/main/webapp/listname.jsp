<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="UTF-8">
    <title>ListName</title>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr/>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Name</th>
        </tr>
        </thead>
        <c:forEach var="name" items="${names}">
            <td>${name}</td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>