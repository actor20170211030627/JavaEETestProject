<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/4/27
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
    <head>
        <title>Title</title>
        <script src="js/jquery-3.4.1.min.js"></script>
    </head>
    <body>
        <ul>
            <li><button onclick="findAll('account/findAll')">1.findAll(按F12查看结果)</button></li>
            <li>2.新增Account
                <form action="account/saveAccount" method="post">
                    姓名(name): <input type="text" name="name" />
                    金额(money): <input type="text" name="money" />
                    <input type="submit" value="提交" />
                </form>
            </li>
        </ul>

        <script>
            /*查询所有
            * data: JSON.stringify(user),
                    contentType: "application/json;charset=UTF-8"
                    * */
            function findAll(url) {
                $.ajax({
                    type: "POST",
                    url: url,

                });
            }
        </script>
    </body>
</html>
