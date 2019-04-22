<%--
  Created by IntelliJ IDEA.
  User: wang
  Date: 2018/4/16
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script src="${pageContext.request.contextPath}/source/jquery-1.11.3.min.js"></script>
<script>
function get() {
    $.get("${pageContext.request.contextPath}/ajaxServlet",{"name":"汤姆","age":23},function (data) {
        console.log(data)
    },"json");

}

function post() {
    $.post("${pageContext.request.contextPath}/ajaxServlet",{"name":"汤姆","age":23},function (data) {
        console.log(data)
    },"json");
}


function ajax() {
    $.ajax({
        url:"${pageContext.request.contextPath}/ajaxServlet",
        type:"post",
        dataType:"json",
        data:{"name":"wang","age":23},
        success:function (result) {
            console.log(result);
        }
    });
}
</script>
<body>
    <button onclick="get()">get请求</button>
    <button onclick="post()">post请求</button>

    <button onclick="ajax()">ajax请求</button>
</body>
</html>
