<%--
  Created by IntelliJ IDEA.
  User: actor
  Date: 2021/4/29
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>请求成功</title>
</head>
<body>
    <p>request域对象.msg0 = \${ requestScope.msg0 } = \${ msg0 } = ${ msg0 }</p>
    <p>request.msg1 = \${ msg1 } = ${ msg1 }</p><br/>
    <P>返回String: request.account = \${ account } = ${ account }</P><%-- 也可写成: account.toString() --%>

    <p>session域对象 = \${ sessionScope } = ${ sessionScope }</p><br/>

    <p>request = \${ requestScope } = ${ requestScope }</p>
</body>
</html>
