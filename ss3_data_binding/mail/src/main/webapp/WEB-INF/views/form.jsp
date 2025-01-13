<%--
  Created by IntelliJ IDEA.
  User: Thao
  Date: 1/13/2025
  Time: 9:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Settings</h1>
<form:form action="/configuration" method="post" modelAttribute="mailConfiguration">
    <form:label path="language">Languages:</form:label>
    <form:select path="language">
        <form:option value="English" label="English"/>
        <form:option value="Vietnamese" label="Vietnamese"/>
        <form:option value="Japanese" label="Japanese"/>
        <form:option value="Chinese" label="Chinese"/>
    </form:select><br>

    <form:label path="pageSize">Page Size:</form:label>
    Show <form:select path="pageSize">
        <form:option value="5" label="5"/>
        <form:option value="10" label="10"/>
        <form:option value="15" label="15"/>
        <form:option value="25" label="25"/>
        <form:option value="50" label="50"/>
        <form:option value="100" label="100"/>
    </form:select> emails per page<br>

    <button type="submit">Save</button>
</form:form>


<p>${message}</p>


</body>
</html>
