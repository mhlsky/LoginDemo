package com.demo.dao;

import com.demo.bean.User;
import com.demo.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public static User login(String username, String password){
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtil.getconn();
            String sql = "select * from user where name = ? and password =?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            System.out.println("username："+username);
            System.out.println("password："+password);

            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                user = new User();
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                System.out.println("登录成功");
            }else {
                System.out.println("用户名或密码错误");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            JdbcUtil.close(preparedStatement,connection,resultSet);

        }
        if(user==null){
            System.out.println("为空");

        }
        return user;
    }

    public static void addUser(User user){
        Connection connection =null;
        PreparedStatement preparedStatement =null;
        try {
            connection= JdbcUtil.getconn();
            String sql = "insert into user (id,name,password,role) values(?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,user.getId());
            preparedStatement.setString(2,user.getName());
            preparedStatement.setString(3,user.getPassword());
            preparedStatement.setInt(4,user.getRole());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(preparedStatement,connection);
        }
    }
}
