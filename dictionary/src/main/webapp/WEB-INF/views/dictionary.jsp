<%--
  Created by IntelliJ IDEA.
  User: Thao
  Date: 1/9/2025
  Time: 2:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Tra cứu từ điển Anh - Việt</h2>
<form action="/translate" method="get">
    <input type="text" name="word" placeholder="Nhập từ cần tra cứu" required>
    <button type="submit">Tra cứu</button>
</form>

<c:if test="${not empty word and not empty vie}">
    <div>
        <p><strong>Kết quả:</strong> ${word} có nghĩa là ${vie}</p>
    </div>
</c:if>

</body>
</html>
