package com.actor.springmvc_test.utils;

import com.actor.springmvc_test._1springmvc1.springmvc_test;

import java.io.File;
import java.net.URLDecoder;

/**
 * 文件路径测试 & 打成jar包后文件路径
 */
public class FilePathTest {

    public static void main(String[] args) {

        //idea中: /E:/JavaEEProjects/JavaEETestProject/spring-test/target/classes/
        //jar 包: /E:/JavaEEProjects/JavaEETestProject/out/artifacts/springMVC_test_jar/springMVC-test.jar
        String path = null;
        try {
            path = springmvc_test.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //idea中: /E:/JavaEEProjects/JavaEETestProject/spring-test/target/classes/
        //jar 包: /E:/JavaEEProjects/JavaEETestProject/out/artifacts/springMVC_test_jar/springMVC-test.jar
        String jarPath = null;
        try {
            jarPath = springmvc_test.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //idea中: /E:/JavaEEProjects/JavaEETestProject/spring-test/target/classes/
        //jar 包: /E:/JavaEEProjects/JavaEETestProject/out/artifacts/springMVC_test_jar/springMVC-test.jar
        String decodedPath = null;
        try {
            decodedPath = URLDecoder.decode(path, "UTF-8");//这应该解决空格和特殊字符的问题
        } catch (Exception e) {
            e.printStackTrace();
        }

        //idea中: /E:/JavaEEProjects/JavaEETestProject/spring-test/target/classes//newDemo.xlsx
        //jar 包: null
        String filePath = null;
        try {
            filePath = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "/newDemo.xlsx";
        } catch (Exception e) {
            e.printStackTrace();
        }
        //idea中: E:\JavaEEProjects\JavaEETestProject\spring-test
        //jar 包: E:\JavaEEProjects\JavaEETestProject\out\artifacts\springMVC_test_jar
        String filePath1 = null;
        try {
            filePath1 = new File("").getCanonicalPath();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.printf(" path=%s\n\n jarPath=%s\n decodedPath=%s\n filePath=%s\n filePath1=%s\n",
                path, jarPath, decodedPath, filePath, filePath1);
    }
}
