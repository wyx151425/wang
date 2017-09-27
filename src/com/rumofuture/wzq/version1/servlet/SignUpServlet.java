package com.rumofuture.wzq.version1.servlet;

import com.rumofuture.wzq.model.domain.User;
import com.rumofuture.wzq.version1.service.SignUpService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by WangZhenqi on 2016/12/29.
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
    private SignUpService signUpService = new SignUpService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String mobilePhone = request.getParameter("mobile");
        String password = request.getParameter("password");

        User user = new User();
        user.setName(name);
        user.setMobilePhoneNumber(mobilePhone);
        user.setPassword(password);
        int signUpResult = signUpService.signUp(user);

        String result;
        if (1 == signUpResult) {
            User resultUser = new User();
            resultUser.setName(name);
            resultUser.setMobilePhoneNumber(mobilePhone);
            resultUser.setPassword(password);
            request.getSession().setAttribute("currentUser", resultUser);

            result = "1";
        } else {
            result = "0";
        }

        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
