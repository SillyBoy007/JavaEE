<%--
  Created by IntelliJ IDEA.
  User: wang
  Date: 2018/4/5
  Time: 19:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP九大内置对象</title>
</head>
<body>
    aaaa
    <%
      out.write("bbbb"); //out缓冲区默认8k大小
      response.getWriter().write("cccc");
      //使用pageContext向其它域存数据
      request.setAttribute("name","zhangsan");
      pageContext.setAttribute("name","req",pageContext.REQUEST_SCOPE);
      pageContext.setAttribute("name","sess",pageContext.SESSION_SCOPE);
      pageContext.setAttribute("name","app",pageContext.APPLICATION_SCOPE);

    %>
    <%
        //pageContext获取其它域的值
        out.write("request:");
        out.write(pageContext.getAttribute("name",pageContext.REQUEST_SCOPE).toString());
        out.write(",session:");
        out.write(pageContext.getAttribute("name",pageContext.SESSION_SCOPE).toString());
        out.write(",application:");
        out.write(pageContext.getAttribute("name",pageContext.APPLICATION_SCOPE).toString());
    %>

    <!--findAttribute会根据域的范围从小到大搜索,找到后不再搜索-->
    <!--page域<request域<session域<application域-->
    <%= pageContext.findAttribute("name")%>
    <%= "ddddddd"%>
    <!--pageContext可以获得其它内置对象-->
    <%
        pageContext.getRequest();
        pageContext.getSession();
    %>
</body>
</html>
