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
    <h2>Create One deal for many clients</h2>
    <form method="post" action="clients">
        <input type="hidden" name="action" value="post_deal">
        <%--<jsp:useBean id="product" type="ru.fulkin.servletlinux.model.Product"/>--%>
        <dl>
            <dt>Product:</dt>
            <dd>
                <select name="product_item">
                    <c:forEach items="${requestScope.products}" var="product">

                        <option value="${product.id}">
                                ${product.name} Remnant:${product.remnant}
                        </option>
                    </c:forEach>
                </select>
            </dd>
        </dl>
        <dl>
            <dt>Clients:</dt>
            <dd>
                <select multiple name="clients_item">
                    <c:forEach items="${requestScope.clients}" var="clients_item">

                        <option value="${clients_item.id}">
                                ${clients_item.firstname} ${clients_item.lastname}
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
