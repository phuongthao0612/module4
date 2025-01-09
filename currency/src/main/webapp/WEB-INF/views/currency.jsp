<%--
  Created by IntelliJ IDEA.
  User: Thao
  Date: 1/9/2025
  Time: 11:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Currency Converter</title>
</head>
<body>
<h1>Convert USD to VND</h1>
<form action="/convert" method="post">
    <label for="usd">USD: </label>
    <input type="number" name="usd" id="usd" required value="${usd != null ? usd : ''}"/><br/><br/>

    <input type="submit" value="Convert"/>
</form>
<c:if test="${vnd != null}">
    <h2>Result</h2>
    <p>VND: ${vnd} VND</p>
</c:if>
<a href="/convert">Convert again</a>


</body>
</html>
