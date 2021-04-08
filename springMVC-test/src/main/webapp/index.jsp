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
    <title>SpringMVC: $Title$</title>
  </head>

  <body>
    <ol>
      <li>
        <h3>SpringMVC框架基于组件方式执行流程.png</h3>
        <%--    <img width="25%" src="WEB-INF/SpringMVC框架基于组件方式执行流程.png">--%>
        <img width="35%" src="https://img-blog.csdnimg.cn/20201001155806721.png">
      </li>
      <li><a href="hello/sayHello?name=hehe">调用接口, 跳转'success'页面!</a></li>
      <li><a href="hello/testParam?username=hehe呵呵&password=123">请求参数绑定</a></li>
      <li>请求参数绑定 基本类型&Data:<br/>
        <form action="hello/testAccount" method="post">
          姓名(username): <input type="text" name="username" />
          密码(password): <input type="password" name="password" />
          金额(money): <input type="text" name="money" />
          时分(type="time"): <input type="time" name="time" /><br/>
          年月日(type="date"): <input type="date" dataformatas="yyyy-MM-dd" name="birthday" />
          年月日时分,没秒(type="datetime-local"): <input type="datetime-local" dataformatas="yyyy-MM-dd'T'HH:mm:ss" name="localDateTime">
          <input type="submit" value="提交" />
        </form>
      </li>

      <li>请求参数绑定 实体类型:<br/>
        <form action="hello/testAccount" method="post">
          User对象:<br/>
          user.uname: <input type="text" name="user.uname" />
          user.age: <input type="number" maxlength="3" name="user.age" />
          <input type="submit" value="提交" />
        </form>
      </li>

      <li>请求参数绑定 List&Map:<br/>
        <form action="hello/testAccount" method="post">
          List对象:<br/>
          list[0].uname: <input type="text" name="list[0].uname" />
          list[0].age: <input type="number" maxlength="3" name="list[0].age" /><br/>
          Map对象:<br/>
          map['key1'].uname: <input type="text" name="map['key1'].uname" />
          map['key1'].age: <input type="number" maxlength="3" name="map['key1'].age" />
          <input type="submit" value="提交" />
        </form>
      </li>

    </ol>

  $END$
  </body>
</html>
