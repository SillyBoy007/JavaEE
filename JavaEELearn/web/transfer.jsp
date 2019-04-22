<%--
  Created by IntelliJ IDEA.
  User: wang
  Date: 2018/4/7
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${ pageContext.request.contextPath }/transferServlet" method="post">
    <p>转出帐户: <input type="text" name="out"></p>
    <p>转入帐户: <input type="text" name="in"></p>
    <p>转出金额: <input type="text" name="money"></p>
    <input type="submit" value="确认转账">
</form>
</body>
</html>
