package com.rumofuture.wang.version1.servlet;

import com.rumofuture.wang.model.domain.User;
import com.rumofuture.wang.version1.service.PasswordModifyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by WangZhenqi on 2016/12/30.
 */
@WebServlet("/PasswordModifyServlet")
public class PasswordModifyServlet extends HttpServlet {
    private PasswordModifyService passwordModifyService = new PasswordModifyService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mobilePhone = request.getParameter("mobile");
        String password = request.getParameter("password");

        User user = new User();
        user.setMobilePhoneNumber(mobilePhone);
        user.setPassword(password);

        System.out.println(password);

        String result;
        if (1 == passwordModifyService.passwordModify(user)) {
            User currentUser = ((User) request.getSession().getAttribute("currentUser"));
            currentUser.setPassword(password);
            request.getSession().setAttribute("currentUser", currentUser);
            result = "1";
        } else {
            result = "0";
        }
        PrintWriter out = response.getWriter();
        out.println(result);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
