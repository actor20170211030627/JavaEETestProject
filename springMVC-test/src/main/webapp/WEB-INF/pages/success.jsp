<%--
  Created by IntelliJ IDEA.
  User: actor
  Date: 2021/3/28
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>

<%-- isELIgnored="false", 不忽略el表达式: $ --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>

<body>
    <h2>成功!</h2>

    <p>request域对象.msg = \${ requestScope.msg } = ${ requestScope.msg }</p>
    <p>request.msg, 简写 = \${ msg } = ${ msg }</p>
    <p>request.msg1 = \${ msg1 } = ${ msg1 }</p>
    <p>session域对象 = \${ sessionScope } = ${ sessionScope }</p>
    <p>session域对象 = \${ sessionScope.msg } = ${ sessionScope.msg }</p>
    <p>session域对象 = \${ sessionScope.msg1 } = ${ sessionScope.msg1 }</p>
    <p>request = \${ requestScope } = ${ requestScope }</p>
</body>
</html>