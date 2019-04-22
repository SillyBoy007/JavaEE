<%@ page import="com.wang.eljstl.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: wang
  Date: 2018/4/6
  Time: 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>el接收域中的数据</title>
</head>
<body>
<!--模拟域中的数据-->
<%
    request.setAttribute("company","blezza");

    User user = new User();
    user.setUsername("haha");
    user.setPassword("123456");
    session.setAttribute("user",user);
    User user1 = new User();
    user1.setUsername("sadsd");
    user1.setPassword("244");


    List<User> list = new ArrayList<User>();
    list.add(user);
    list.add(user1);
    application.setAttribute("list",list);
%>
<%--1.jsp脚本取出数据--%>
<%=request.getAttribute("company")%>
</br>
<%=session.getAttribute("user")%>
</br>
<%=application.getAttribute("list")%>
</br>
<%--2.el取出数据--%>
${requestScope.company}
</br>
${sessionScope.user.username}
</br>
${applicationScope.list[1].username}
</br>
<%--使用el表达式全域查找,默认从小到大的范围查找,找到为止--%>
${company}
</br>
${user.username}
</br>
${list[1].username}
</br>
</body>
</html>
