package com.actor.springmvc_test._1springmvc1;

import com.actor.springmvc_test.domain.BaseResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * description: 描述
 *
 * date       : 2021/4/18 on 22
 * @version 1.0
 */
@Controller
@RequestMapping("/file")
public class _3FileController {

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=194 (先看这个)
     * 文件上传之上传原理分析和和搭建环境
     * 1.文件上传必要前提:
     *  1.1.form表单的 enctype(表单请求正文类型)="multipart/form-data"
     *      默认值是: "application/x-www-form-urlencoded"
     *  1.2.method 取值必须是Post
     *  1.3.提供一个文件选择域<input type="file" />
     *
     * 2.文件上传原理分析
     *  当form表单的 enctype取值不是默认值后, request.getParameter()将失效.
     *  enctype="application/x-www-form-urlencoded"时, form表单的内容是: key1=v1&k2=v2
     *  enctype="multipart/form-data"时, 请求正文内容就变成: 把表单分成几个部分上传, 每一部分都是MIME类型描述的正文.
     *
     * <!-- 传统方式文件上传 -->
     * <dependency>
     *     <groupId>commons-fileupload</groupId>
     *     <artifactId>commons-fileupload</artifactId>
     *     <version>1.4</version>
     * </dependency>
     * <dependency>
     *     <groupId>commons-io</groupId>
     *     <artifactId>commons-io</artifactId>
     *     <version>2.7</version>
     * </dependency>
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=192
     * 文件上传之传统方式上传代码回顾  (不推荐, 太原始)
     * 注意:
     *  文件解析器对象是后续视频用到的, 会导致这种传统方式解析request为空!!!
     *      @see #fileUpload2(HttpServletRequest, MultipartFile)
     */
    @RequestMapping("/fileUpload1")
    public String fileUpload1(HttpServletRequest request) throws Exception {
        String uploadPath = request.getSession().getServletContext().getRealPath("/uploads/");
        File parent = new File(uploadPath);
        if (!parent.exists()) {
            boolean mkdirs = parent.mkdirs();
        }
        //解析Response对象, 获取上传文件项
        DiskFileItemFactory factory = new DiskFileItemFactory(/*int sizeThreshold, File repository*/);
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        //所有文件
        List<FileItem> fileItems = fileUpload.parseRequest(request);    //FileUploadException
        for (FileItem item : fileItems) {
            if (item.isFormField()) {
                //普通表单项
            } else {
                //上传文件项
                String fieldName = item.getFieldName(); //表单上传的key
                //String encode = URLEncoder.encode(item.getName(), "UTF-8");//中文
                String fileName = UUID.randomUUID().toString().replaceAll("-", "")+ "_" + item.getName();//文件名称
                item.write(new File(parent, fileName));   //Exception
                //删除临时文件
                item.delete();
                /**
                 * 使用war 部署的项目, 会上传到: tomcat/webapps/项目名/uploads/
                 * 使用war exploded 方式, 会在: 项目路径/target/项目名/uploads/
                 */
                String format = String.format("fieldName(表单上传的key)=%s, fileName=%s. uploadPath=%s",
                        fieldName, fileName, uploadPath);
                request.setAttribute("msg0", BaseResponse.ok("文件上传成功!", format));
            }
        }
        return "success";
    }

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=191
     * 文件上传之Springmvc方式上传原理分析
     * @see MultipartFile 该对象表示上传的文件, 要求变量名必须和表单file标签的name属性名称相同.
     * 请求request -> 前端控制器 -> "配置文件解析器",解析request -> 返回前端控制器 -> 找到方法并映射到参数
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=195
     * 在springmvc.xml中配置 文件解析器:
     * <!-- 文件解析器(用于SpringMVC文件上传), 要求id必须是multipartResolver -->
     * <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
     *     <!-- 配置文件大小, 单位字节bytes -->
     *     <property name="maxUploadSize" value="10485760"/>
     * </bean>
     */
    @RequestMapping("/fileUpload2")
    public String fileUpload2(HttpServletRequest request, MultipartFile file2) throws IOException {
        String uploadPath = request.getSession().getServletContext().getRealPath("/uploads/");
        File parent = new File(uploadPath);
        if (!parent.exists()) {
            boolean mkdirs = parent.mkdirs();
        }
        //String encode = URLEncoder.encode(item.getName(), "UTF-8");//中文
        String fileName = UUID.randomUUID().toString().replaceAll("-", "")+ "_" + file2.getOriginalFilename();//文件名称
        //完成上传
        file2.transferTo(new File(parent, fileName));
        //返回上传信息
        String format = String.format("fieldName(表单上传的key)=%s, fileName=%s, contentType=%s, size=%d. uploadPath=%s",
                file2.getName(), fileName, file2.getContentType(), file2.getSize(), uploadPath);
        request.setAttribute("msg0", BaseResponse.ok("文件上传成功!", format));
        return "success";
    }

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=193
     * 文件上传之"跨服务器"分析和搭建环境
     * 1.分服务器的目的
     *  在实际开发中, 会有很多处理不同功能的服务器, 例如:
     *      应用服务器:      负责部署应用.
     *      数据库服务器:    运行数据库.
     *      缓存&消息服务器: 处理大并发访问的缓存和消息.
     *      文件服务器:      存储用户上传的文件.
     * 2.开启第2个Tomcat, 模拟另外一台服务器
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=197
     * 跨服务器上传文件, 需要导入依赖:
     * <!-- 跨服务器上传文件 -->
     * <dependency>
     *     <groupId>com.sun.jersey</groupId>
     *     <artifactId>jersey-core</artifactId>
     *     <version>1.19.4</version>
     * </dependency>
     * <dependency>
     *     <groupId>com.sun.jersey</groupId>
     *     <artifactId>jersey-client</artifactId>
     *     <version>1.19.4</version>
     * </dependency>
     */@RequestMapping("/fileUpload3")
    public String fileUpload3(HttpServletRequest request, MultipartFile file3) throws IOException {
        //1.定义上传文件服务器路径(文件夹必须存在, 否则报错500: returned a response status of 409 Conflict)
        String uploadPath = "http://localhost:9090/uploads/";
        //String encode = URLEncoder.encode(item.getName(), "UTF-8");//中文
        String fileName = UUID.randomUUID().toString().replaceAll("-", "")+ "_" + file3.getOriginalFilename();//文件名称
        //2.创建客户端对象
        Client client = Client.create();
        //3.和图片服务器进行连接
        WebResource webResource = client.resource(uploadPath + fileName);
        //4.完成 跨服务器上传
        webResource.put(file3.getBytes());
        //返回上传信息
        String format = String.format("fieldName(表单上传的key)=%s, fileName=%s, contentType=%s, size=%d. uploadPath=%s",
                file3.getName(), fileName, file3.getContentType(), file3.getSize(), uploadPath);
        request.setAttribute("msg0", BaseResponse.ok("文件上传成功!", format));
        return "success";
    }
}
