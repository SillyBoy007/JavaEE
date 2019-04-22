<%--
  Created by IntelliJ IDEA.
  User: wang
  Date: 2018/4/6
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.wang.eljstl.User" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<%--模拟增强for--%>
<%--items表示一个集合--%>
<%--var代表集合的某一元素--%>
<%
   List<String> strList = new ArrayList<String>();  /*这里后面不写String会报错*/
    strList.add("itcast");
    strList.add("fish");
    strList.add("hahah");
    pageContext.setAttribute("strlist",strList,pageContext.REQUEST_SCOPE);

    List<User> userList = new ArrayList<User>();
    User user1 = new User();
    user1.setUsername("wang");
    user1.setPassword("1234");

    User user2 = new User();
    user2.setUsername("xia");
    user2.setPassword("wangxxx");
    userList.add(user1);
    userList.add(user2);
    application.setAttribute("userList",userList);

    Map<String,String> strMap = new HashMap<String, String>();
    strMap.put("name","xiaowang");
    strMap.put("age","23");
    strMap.put("email","82123231@qq.com");
    session.setAttribute("strMap",strMap);

    Map<String,User> userMap = new HashMap<String, User>();
    userMap.put("user1",user1);
    userMap.put("user2",user2);
    request.setAttribute("userMap",userMap);

%>

<h1>取出strList的值</h1>
<c:forEach items="${strlist }" var="str">
    ${str }</br>
</c:forEach>
<h1>取出userList的值</h1>
<c:forEach items="${userList}" var="user">
    ${user.username }</br>
</c:forEach>
<h1>遍历Map< String ,String >的值</h1>
<c:forEach items="${strMap}" var="entry">
    ${entry.key } : ${entry.value }</br>
</c:forEach>
<h1>遍历Map< String ,User >的值 </h1>
<c:forEach items="${userMap }" var="entry">
    ${entry.key } -> ${entry.value.username} : ${entry.value.password} </br>
</c:forEach>
</body>
</html>
