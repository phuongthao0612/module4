<%--
  Created by IntelliJ IDEA.
  User: Thao
  Date: 1/10/2025
  Time: 11:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Máy tính đơn giản</title>
</head>
<body>
<h1>Máy tính đơn giản</h1>

<form action="/calculate" method="post">
    <input type="number" name="num1" placeholder="Số 1" required/>
    <input type="number" name="num2" placeholder="Số 2" required/>
    <select name="operation">
        <option value="add">Cộng</option>
        <option value="subtract">Trừ</option>
        <option value="multiply">Nhân</option>
        <option value="divide">Chia</option>
    </select>
    <button type="submit">Tính</button>
</form>

<c:if test="${not empty result}">
    <h2>Kết quả: ${result}</h2>
</c:if>

<c:if test="${not empty error}">
    <h2 style="color:red;">${error}</h2>
</c:if>
</body>
</html>
