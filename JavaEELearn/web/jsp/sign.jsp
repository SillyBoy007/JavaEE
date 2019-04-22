<%--
  Created by IntelliJ IDEA.
  User: wang
  Date: 2018/4/5
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP标签(动作)</title>
</head>
<body>
<!--动态包含-->
<jsp:include page="../index.html"/>
<!--请求转发-->
<jsp:forward page="../index.jsp"/>
</body>
</html>
