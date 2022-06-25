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
    <hr>
    <h2>${param.action == 'create' ? 'Create client' : 'Edit client'}</h2>
    <jsp:useBean id="client" type="ru.fulkin.servletlinux.model.Client" scope="request"/>
    <form method="post" action="clients">
        <input type="hidden" name="id" value="${client.id}">
        <dl>
            <dt>First name:</dt>
            <dd><input type="text" value="${client.firstname}" size=50 name="firstname" required></dd>
        </dl>
        <dl>
            <dt>Last name:</dt>
            <dd><input type="text" value="${client.lastname}" size=50 name="lastname" required></dd>
        </dl>
        <dl>
            <dt>City:</dt>
            <dd><input type="text" value="${client.city}" size=50 name="city" required></dd>
        </dl>
        <dl>
            <dt>Phone:</dt>
            <dd><input type="text" value="${client.phone}" size=20 name="phone" required></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>
