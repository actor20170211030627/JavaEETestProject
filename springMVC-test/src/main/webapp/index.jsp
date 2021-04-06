<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/3/15
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%-- 如果没有这句, 会出现中文乱码 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>

  <body>
    <h2>Hello World!测试中文乱码</h2>
    <ol>
      <li><a href="hello/sayHello?name=hehe">调用接口, 跳转'success'页面!</a></li>
      <li><a href="hello/testParam?username=hehe呵呵&password=123">请求参数绑定</a></li>
      <li>请求参数绑定实体类型:<br/>
        <form action="hello/testAccount" method="post">
          姓名: <input type="text" name="username" />
          密码: <input type="password" name="password" />
          金额: <input type="text" name="money" /><br/>
          User.uname: <input type="text" name="user.uname" />
          User.age: <input type="number" maxlength="3" name="user.age" />
          User.time: <input type="time" name="user.time" />
          User.birthday: <input type="date" dataformatas="yyyy-MM-dd" name="user.birthday" />
          User.localDateTime: <input type="datetime-local" dataformatas="yyyy-MM-dd HH:mm:ss" name="user.localDateTime">
          <input type="submit" value="提交" />
        </form>
      </li>
    </ol>

    <h3>SpringMVC框架基于组件方式执行流程.png</h3>
    <img width="25%" src="WEB-INF/SpringMVC框架基于组件方式执行流程.png">


  $END$
  </body>
</html>
