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
            <li>1.<a href="account/findAll">findAll(查询所有)</a></li>
            <li>
                <form action="account/saveAccount" method="post" id="form_saveAccount">
                    2.姓名(name): <input type="text" name="name"/>
                    金额(money): <input type="text" name="money" />
                    <input type="submit" value="新增Account"/>
                </form>
            </li>
        </ul>

        <script>
            function saveAccount(url, name, money) {
                var account = {
                    "name": name,
                    "money": money
                };
                $.ajax({
                    type: "POST",
                    url: url,
                    data: JSON.stringify(account),
                    contentType: "application/json;charset=UTF-8"
                });
            }
        </script>
    </body>
</html>
