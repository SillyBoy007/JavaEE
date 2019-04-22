<%@ page import="com.wang.eljstl.User" %><%--
  Created by IntelliJ IDEA.
  User: wang
  Date: 2018/4/6
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>jstl标签库</title>
</head>
<body>
    <%--if语句,test表示条件--%>
    <c:if test="${1==1}">
        hello
    </c:if>
    <c:if test="${1!=1}">
        hehe
    </c:if>
    <%--模拟foreach--%>
    <c:forEach begin="0" end="5" var="i" step="1" >
        ${i}<hr/>
    </c:forEach>

</body>
</html>
