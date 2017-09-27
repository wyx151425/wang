package com.rumofuture.wzq.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by WangZhenqi on 2016/11/18.
 */
public class ConnectionFactory {

    private static String driver;
    private static String dburl;
    private static String user;
    private static String password;

    private static final ConnectionFactory factory = new ConnectionFactory();

    private Connection connection; // 用于保存数据库连接

    // 用静态代码块读取配置信息赋值给静态成员变量
    static {
        // 定义Properties类 定义键值对 其方法用于处理属性文件中的键值对
        Properties prop = new Properties();
        try {
			/*
			 * 获取属性文件的内容 获取当前类的类加载器 通过方法读取数据
			 */
            InputStream in = ConnectionFactory.class.getClassLoader()
                    .getResourceAsStream("dbconfig.properties"); // 读取属性文件中的内容
            prop.load(in); // 从输入流中读取键值对列表
        } catch (Exception e) {
            System.out.println("*****配置文件读取错误*****");
        }

        // 将值赋值给定义的变量
        driver = prop.getProperty("driver");
        dburl = prop.getProperty("dburl");
        user = prop.getProperty("user");
        password = prop.getProperty("password");
    }

    // 无参构造函数
    private ConnectionFactory() {
    }

    // 获取一个ConnectionFactory实例
    public static ConnectionFactory getInstance() {
        return factory;
    }

    // 数据库连接的获取方法
    public Connection getConnection() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(dburl, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
