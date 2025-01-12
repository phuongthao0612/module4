<%--
  Created by IntelliJ IDEA.
  User: Thao
  Date: 1/10/2025
  Time: 11:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Chọn gia vị</title>
</head>
<body>
<h2>Chọn gia vị cho Sandwich</h2>
<c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
</c:if>

<form action="/save" method="POST">
    <c:forEach var="condiment" items="${condiments}">
        <input type="checkbox" name="condiment" value="${condiment}"> ${condiment}<br>
    </c:forEach>
    <input type="submit" value="Lưu">
</form>

<c:if test="${not empty selectedCondiments}">
    <h3>Các gia vị đã chọn:</h3>
    <ul>
        <c:forEach var="condiment" items="${selectedCondiments}">
            <li>${condiment}</li>
        </c:forEach>
    </ul>
</c:if>
</body>
</html>