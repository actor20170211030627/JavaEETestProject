<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

        <script src="js/jquery-3.4.1.min.js"></script>
    </head>

    <body>
        <h3>2.各种响应类型</h3>
        <ul>
            <li>1.<a href="return/returnString">通过Model返回user对象</a></li>
            <li>2.<a href="return/returnVoid">返回void类型(1.请求转发, 2.重定向, 3.直接进行响应)</a></li>
            <li>3.<a href="return/testForwardOrredirect">测试关键字'forward(请求转发)'和'redirect(重定向)'</a></li>
            <li>4.<a href="return/returnModelAndView">返回SpringMVC自带的'ModelAndView'</a></li>
            <li>5.<button id="btn">@ResponseBody 异步Ajax发送json, 返回Json</button></li>
            <li>6.文件上传之传统方式上传代码回顾, post(multipart/form-data: 把表单分成几个部分上传)
                <form action="return/fileUpload1" enctype="multipart/form-data" method="post">
                    选择文件: <input type="file" name="file1"/>
                    <input type="submit" value="上传"/>
                </form>
            </li>
            <li>7.SpringMVC方式文件上传
                <form action="return/fileUpload2" enctype="multipart/form-data" method="post">
                    选择文件: <input type="file" name="file2"/>
                    <input type="submit" value="上传"/>
                </form>
            </li>
            <li>8.跨服务器文件上传
                <form action="return/fileUpload3" enctype="multipart/form-data" method="post">
                    选择文件: <input type="file" name="file3"/>
                    <input type="submit" value="上传"/>
                </form>
            </li>
        </ul>

        <script>
            $(function () {
                //页面加载, 绑定单击事件
                $("#btn").click(function () {
                    $.ajax({
                        type: "POST",       //默认get
                        url: "return/returnJson",
                        contentType:"application/json;charset=UTF-8",
                        data: '{"uname":"张三","age":12}',
                        dataType: "json",   //返回json, xml, html, script, jsonp, text
                        success: function (data) {
                            alert(JSON.stringify(data))
                        }
                    });
                });
            });
        </script>
    </body>
</html>
