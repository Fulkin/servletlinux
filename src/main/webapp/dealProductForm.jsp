<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Person</title>
    <style>
        dl {
            background: none repeat scroll 0 0 #FAFAFA;
            margin: 8px 0;
            padding: 0;
        }

        dt {
            display: inline-block;
            width: 170px;
        }

        dd {
            display: inline-block;
            margin-left: 8px;
            vertical-align: top;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <a href="clients">Client list</a>
    <hr>
    <h2>Create product and dealToList</h2>
    <jsp:useBean id="client" type="ru.fulkin.servletlinux.model.Client" scope="request"/>
    <form method="post" action="clients">
        <input type="hidden" name="action" value="post_deal">
        <input type="hidden" name="id" value="${client.id}">

        <%--<jsp:useBean id="product" type="ru.fulkin.servletlinux.model.Product"/>--%>
        <dl>
            <dt>Product:</dt>
            <dd>
                <select name="product_item">
                    <c:forEach items="${requestScope.products}" var="product">

                        <option value="${product.id}">
                                ${product.name} Price:${product.price}
                        </option>
                    </c:forEach>
                </select>
            </dd>
        </dl>

        <dl>
            <dt>Date:</dt>
            <dd><input type="datetime-local" name="date" required></dd>
        </dl>
        <dl>
            <dt>Amount:</dt>
            <dd><input type="number" name="amount" required></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>
