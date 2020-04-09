package com.demo.service;

import com.demo.bean.User;
import com.demo.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPut(request,response);
    }

    //    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
    protected  void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        //PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        int id = Integer.valueOf(request.getParameter("id"));
        String username = request.getParameter("name");
        String password = request.getParameter("password");
        int role = Integer.valueOf(request.getParameter("role"));

        User user = new User();
        user.setId(id);
        user.setName(username);
        user.setPassword(password);
        user.setRole(role);
        UserDao userDAo = new UserDao();
        UserDao.addUser(user);
        System.out.println("注册成功");
        request.getRequestDispatcher("login.jsp").forward(request,response);
    }
}
