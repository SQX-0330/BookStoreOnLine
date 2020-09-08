package com.sqx.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author SQX
 * @date 2020/8/29 - 10:30
 */
public class JdbcUtils {

    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();

    static{

        try {
            Properties properties = new Properties();
            //读取jdbc.properties的属性配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //从流中加载数据
            properties.load(inputStream);
            //创建数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接池中的连接
     * @return 如果返回nul 说明获取连接失败，有值就是获取连接成功
     */
    public static Connection getConnection(){

        Connection connection = conns.get();

        if(connection == null){
            try {
                connection = dataSource.getConnection(); //c从数据库连接池中获取连接
                conns.set(connection); //保存到ThreadLocal对象中，供后面的jdbc操作使用
                connection.setAutoCommit(false); //手动管理事务
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
       return connection;

    }

    /**
     * 提交事务，并关闭连接
     */
    public static void commitAndClose(){
        Connection connection = conns.get();
        if(connection != null){  //如果不等于null，a说明之前使用过连接，操作过数据库
            try {
                connection.commit(); // 提交事务

            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();  //关闭连接
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //一定要执行remove操作，否则就会出错(因为Tomcat服务器底层使用了线程池技术)
        conns.remove();
    }

    /**
     * 回滚事务，并关闭连接
     */
    public static void rollbackAndClose(){
        Connection connection = conns.get();
        if(connection != null){  //如果不等于null，a说明之前使用过连接，操作过数据库
            try {
                connection.rollback(); // 回滚事务

            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();  //关闭连接
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //一定要执行remove操作，否则就会出错(因为Tomcat服务器底层使用了线程池技术)
        conns.remove();
    }

//    /**
//     * 关闭连接， 返回数据库连接池
//     * @param connection
//     */
//    public static void close(Connection connection){
//
//        if(connection != null){
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }
}
