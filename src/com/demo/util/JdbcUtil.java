package com.demo.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtil {
    private static String url = null;
    private static String user = null;
    private static String password = null;
    private static String driver = null;

    static {
        Properties prop = new Properties();
        InputStream in = JdbcUtil.class.getClassLoader().getResourceAsStream("./jdbcConfig.properties");
        try {
            prop.load(in);
            url = prop.getProperty("url");
            user = prop.getProperty("user");
            password = prop.getProperty("password");
            driver = prop.getProperty("driver");
            Class.forName(driver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getconn() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    public static void close(PreparedStatement statement, Connection connection) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(PreparedStatement preparedStatement, Connection connection, ResultSet result) {
        if (result != null) {
            try {
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
