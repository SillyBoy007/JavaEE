<%--
  Created by IntelliJ IDEA.
  User: wang
  Date: 2018/4/16
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        application.setAttribute("name","wang"); //存数据
        application.setAttribute("name","xia");//改数据
        application.removeAttribute("name");
    %>
</body>
</html>
