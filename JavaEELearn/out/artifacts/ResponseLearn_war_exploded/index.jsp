<%--
  Created by IntelliJ IDEA.
  User: wang
  Date: 2018/4/4
  Time: 9:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page  contentType="text/html;charset=UTF-8" language="java" %>
<!--引入jstl核心库-->
<%--<%@ taglib uri="http://" prefix="c" %>--%>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <!--使用标签-->
  <%--<c:if></c:if>--%>
  <%@ include file="demo.jsp" %>
  <%@ include file="index.html" %>


      <form action="${pageContext.request.contextPath}/el/elobj.jsp" method="post">
          <p>用户名:<input type="text" name="username"></p>
          <p>密码: <input type="text" name="password"></p>
          <p>爱好:<input type="checkbox" name="hobby" value="zq">足球 <input name="hobby" value="lq" type="checkbox">篮球 <input value="pq" name="hobby" type="checkbox">排球</p>
          <p>验证码: <input type="text" name="code"><img src="/imageCodeServlet" alt="验证码"></p>
          <input type="submit">
      </form>
  </body>
</html>
