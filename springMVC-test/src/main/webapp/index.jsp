<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/3/15
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%-- 如果没有这句, 会出现中文乱码 --%>
<%-- isELIgnored="false", 不忽略el表达式: $ --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
    <head>
        <title>SpringMVC各种属性. $Title$</title>

        <%-- type="text/javascript" --%>
<%--        <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script> --%>
      <%-- 要过滤静态资源, 否则js下载不了, 调js方法报错: Uncaught ReferenceError: $ is not defined --%>
        <script src="js/jquery-3.4.1.min.js"></script>
    </head>

  <body>
    <ul>
      <li>
        <h3>1.SpringMVC框架基于组件方式执行流程.png</h3>
            <img width="35%" src="image/SpringMVC框架基于组件方式执行流程.png" alt="SpringMVC框架基于组件方式执行流程"/>
<%--        <img width="35%" src="https://img-blog.csdnimg.cn/20201001155806721.png"/>--%>
      </li>
      <%-- "${pageContext.request.contextPath}/hello/sayHello": 一样的 --%>
      <li>2.<a href="hello/sayHello?name=hehe">调用接口, 跳转'success'页面!</a></li>
      <li>3.<a href="hello/testParam?username=hehe呵呵&password=123">请求参数绑定</a></li>
      <li>4.请求参数绑定 基本类型&Data:<br/>
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

      <li>5.请求参数绑定 实体类型:<br/>
        <form action="hello/testAccount" method="post">
          User对象:<br/>
          user.uname: <input type="text" name="user.uname" />
          user.age: <input type="number" maxlength="3" name="user.age" />
          <input type="submit" value="提交" />
        </form>
      </li>

      <li>6.请求参数绑定 List&Map:<br/>
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

      <li>7.获取Servlet原生的API(HttpServletRequest, HttpServletResponse):
        <a href="hello/testRequest">测试Request</a>
      </li>

      <li>8.<a href="hello/testRequestParam?name12=测试name12">@RequestParam 注解</a></li>

      <li>9.@RequestBody 注解:
        <form action="hello/testRequestBody" method="post">
          uname: <input type="text" name="uname" />
          age: <input type="number" maxlength="3" name="age" />
          <input type="submit" value="提交" />
        </form>
      </li>

      <li>10.@RequestBody 注解, json转对象:
        <form action="hello/testRequestBodyJson2User" method="post" id="form_json2user">
          uname: <input type="text" name="uname" />
          age: <input type="number" maxlength="3" name="age" />
          <input type="button" value="提交" onclick="postJson(form_json2user.action, uname.value, age.value)" />
        </form>
      </li>

      <li>11.<a href="hello/testPathVariable/123">@PathVariable 注解</a></li>
      <li>12.<a href="hello/testRequestHeader">@RequestHeader 注解</a></li>
      <li>13.<a href="hello/testCookieValue">@CookieValue 注解</a></li>
      <li>14.@ModelAttribute 注解:
        <form action="hello/testModelAttribute" method="post">
          uname: <input type="text" name="uname" />
          <input type="submit" value="提交" />
        </form>
      </li>
      <li>14.@ModelAttribute 注解1:
        <form action="hello/testModelAttribute1" method="post">
          uname: <input type="text" name="uname" />
          <input type="submit" value="提交" />
        </form>
      </li>
      <li>15.@SessionAttributes 注解:<br/>
          <a href="hello/testSessionAttributes">@SessionAttributes 注解, 添加'msg'到request&session域中</a>
          &emsp;<a href="hello/getSessionAttributes">从request域中获取值</a>
          &emsp;<a href="hello/deleteSessionAttributes">从request域中删除</a>
      </li>

        <li><h3><a href="hello/go2Returns">2.跳转各种响应类型</a></h3></li>
    </ul>

    <script>
      function postJson(url, uname, age) {
        var user = {
          "uname": uname,
          "age": age
        };

        //ajax需要jquery
        $.ajax({
          type: "POST",
          url: url,
          data: JSON.stringify(user),
          contentType: "application/json;charset=UTF-8"
        });
      }
    </script>

  $END$
  </body>
</html>
