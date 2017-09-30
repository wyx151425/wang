package com.rumofuture.wang.version1.servlet;

import com.rumofuture.wang.model.domain.Member;
import com.rumofuture.wang.model.domain.User;
import com.rumofuture.wang.version1.service.MembersObtainService;
import com.rumofuture.wang.version1.service.SignInService;
import org.json.JSONArray;

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
@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {
    private SignInService signInService = new SignInService();
    private MembersObtainService membersObtainService = new MembersObtainService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String mobilePhone = request.getParameter("mobile");
        String password = request.getParameter("password");

        User user = new User();
        user.setMobilePhoneNumber(mobilePhone);
        user.setPassword(password);
        User resultUser = signInService.signIn(user);

        String result;
        if (resultUser != null) {
            request.getSession().setAttribute("currentUser", resultUser);
            Member member = new Member();
            member.setLeader(resultUser);
            JSONArray membersJsonArray = membersObtainService.membersObtain(member);
            request.getSession().setAttribute("currentMembers", membersJsonArray);

            result = "1";
        } else {
            result = "0";
        }

        PrintWriter out = response.getWriter();
        out.println(result);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
