package src.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 操作数据库的工具类
 *
 * @version: 1.0
 * @author: cpprto
 * @date: 2020/9/23 16:57
 */
public class JDBCUtils {
    public static Connection getConnection() throws Exception {
        /**
         * 获取数据库的连接
         *
         * @param:
         * @return: java.sql.Connection
         * @author: cpprto
         * @date: 2020/9/23 16:58
         */
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        Properties pros = new Properties();
        pros.load(is);

        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driverClass = pros.getProperty("driverClass");

        Class.forName(driverClass);

        return DriverManager.getConnection(url, user, password);
    }

    public static void closeResource(Connection connection, Statement statement) {
        /**
         * 关闭 connection 和 statement 的操作
         *
         * @param: connection
         * @param: statement
         * @return: void
         * @author: cpprto
         * @date: 2020/9/23 16:58
         */
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        try {
            if (statement != null)
                statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void closeResource(Connection connection, Statement statement, ResultSet resultSet) {
        /**
         * 关闭资源的操作
         *
         * @param: connection
         * @param: statement
         * @param: resultSet
         * @return: void
         * @author: cpprto
         * @date: 2020/9/23 16:59
         */
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        try {
            if (statement != null)
                statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        try {
            if (resultSet != null)
                resultSet.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
