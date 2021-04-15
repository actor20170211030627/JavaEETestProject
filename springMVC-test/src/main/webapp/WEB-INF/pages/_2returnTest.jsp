<%--
  Created by IntelliJ IDEA.
  User: actor
  Date: 2021/4/15
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
    <head>
        <title>各种返回值</title>

        <script src="../../js/jquery-3.4.1.min.js"></script>
    </head>

    <body>
        <h3>2.各种响应类型</h3>
        <ul>
            <li>1.<a href="return/returnString">通过Model返回user对象</a></li>
            <li>2.<a href="return/returnVoid">返回void类型(1.请求转发, 2.重定向, 3.直接进行响应)</a></li>
            <li>3.<a href="return/testForwardOrredirect">测试关键字'forward(请求转发)'和'redirect(重定向)'</a></li>
            <li>4.<a href="return/returnModelAndView">返回SpringMVC自带的'ModelAndView'</a></li>
        </ul>

<%--        <script>--%>
<%--            $(function () {--%>

<%--            });--%>
<%--        </script>--%>
    </body>
</html>
