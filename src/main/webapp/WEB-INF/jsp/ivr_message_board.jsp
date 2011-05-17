<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: imransm
  Date: 5 May, 2011
  Time: 1:45:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

  <head><title>SMS Board</title></head>

<body>
<table border="1">
    <caption>Ivr DashBoard</caption>
    <thead>
    <tr>
        <th>XmlContent</th>
    </tr>
    </thead>
    <c:forEach items="${presenter}" var="ivrData">
    <tr>
        <td><c:out value="${ivrData.xmlData}"/></td>
    </tr>
    </c:forEach>
    <table>
</body>

  </body>
</html>