<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: imransm
  Date: 28 Apr, 2011
  Time: 12:24:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>SMS Board</title></head>

<body>
<table border="1">
    <caption>SMS DashBoard</caption>
    <thead>
    <tr>
        <th width="25">Username</th>
        <th width="10">Password</th>
        <th width="140">Text</th>
        <th width="25">Concat</th>
        <th width="25">From</th>
        <th width="25">To</th>
    </tr>
    </thead>
    <c:forEach items="${presenter}" var="smsData">
    <tr>
        <td width="25"><c:out value="${smsData.username}"/></td>
        <td width="25"><c:out value="${smsData.password}"/></td>
        <td width="140"><c:out value="${smsData.text}"/></td>
        <td width="25"><c:out value="${smsData.concat}"/></td>
        <td width="25"><c:out value="${smsData.from}"/></td>
        <td width="25"><c:out value="${smsData.to}"/></td>
    </tr>
    </c:forEach>
    <table>
</body>
</html>