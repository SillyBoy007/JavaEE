<%--
  Created by IntelliJ IDEA.
  User: wang
  Date: 2018/4/17
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
 <form action="${pageContext.request.contextPath }/encodingServlet" method="get">
     <input type="text" name="username">
     <input type="submit">
 </form>
</body>
</html>
