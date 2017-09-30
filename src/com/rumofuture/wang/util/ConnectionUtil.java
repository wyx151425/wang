package com.rumofuture.wang.util;

import com.alibaba.druid.pool.DruidDataSource;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by WangZhenqi on 2017/09/29.
 */

public class ConnectionUtil {

    private static final ConnectionUtil factory = new ConnectionUtil();

    private static DruidDataSource dataSource;

    private ConnectionUtil() {

    }

    static {
        // 定义Properties类 定义键值对 其方法用于处理属性文件中的键值对
        Properties properties = new Properties();
        try {
			/*
			 * 获取属性文件的内容 获取当前类的类加载器 通过方法读取数据
			 */
            InputStream in = ConnectionUtil.class.getClassLoader()
                    .getResourceAsStream("jdbc.properties"); // 读取属性文件中的内容
            properties.load(in); // 从输入流中读取键值对列表

            // 将值赋值给定义的变量
            dataSource = new DruidDataSource();
            dataSource.setDriverClassName(properties.getProperty("driver"));
            dataSource.setUrl(properties.getProperty("url"));
            dataSource.setUsername(properties.getProperty("username"));
            dataSource.setPassword(properties.getProperty("password"));

            dataSource.setInitialSize(Integer.parseInt(properties.getProperty("initialSize")));
            dataSource.setMinIdle(Integer.parseInt(properties.getProperty("minIdle")));
            dataSource.setMaxActive(Integer.parseInt(properties.getProperty("maxActive")));
            dataSource.setMaxWait(Long.parseLong(properties.getProperty("maxWait")));
            dataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(properties.getProperty("timeBetweenEvictionRunsMillis")));
            dataSource.setMinEvictableIdleTimeMillis(Long.parseLong(properties.getProperty("minEvictableIdleTimeMillis")));
            dataSource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(properties.getProperty("maxPoolPreparedStatementPerConnectionSize")));

            dataSource.setTestWhileIdle(Boolean.parseBoolean(properties.getProperty("testWhileIdle")));
            dataSource.setTestOnBorrow(Boolean.parseBoolean(properties.getProperty("testOnBorrow")));
            dataSource.setTestOnReturn(Boolean.parseBoolean(properties.getProperty("testOnReturn")));
            dataSource.setPoolPreparedStatements(Boolean.parseBoolean(properties.getProperty("poolPreparedStatements")));
            dataSource.setDefaultAutoCommit(Boolean.parseBoolean(properties.getProperty("defaultAutoCommit")));

            dataSource.setValidationQuery(properties.getProperty("validationQuery"));
            dataSource.setFilters(properties.getProperty("filters"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获取一个ConnectionFactory实例
    public static ConnectionUtil getInstance() {
        return factory;
    }

    // 数据库连接的获取方法
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
