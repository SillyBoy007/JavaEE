<%--
  Created by IntelliJ IDEA.
  User: wang
  Date: 2018/4/6
  Time: 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>el内置对象</title>
</head>
<body>
<%--使用el获得参数--%>
${param.username}
</br>
${paramValues.hobby}
</br>
<%--获取请求头--%>
${header["User-Agent"]}
</br>
<%--获取cookie--%>
<%
    Cookie cookie = new Cookie("age","23");
    response.addCookie(cookie);
%>
${cookie.age.value}
</br>
<%--通过el表达式的pageContext获取request对象--%>
${pageContext.request}
<%--通过request对象获得contextPath--%>
${pageContext.request.contextPath}
</body>
</html>
