package com.rumofuture.wzq.version1.servlet;

import com.rumofuture.wzq.model.domain.User;
import com.rumofuture.wzq.version1.service.AccountInfoModifyService;

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
@WebServlet("/AccountInfoModifyServlet")
public class AccountInfoModifyServlet extends HttpServlet {
    private AccountInfoModifyService infoModifyService = new AccountInfoModifyService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String workExprience = request.getParameter("work_experience");
        String annualSalary = request.getParameter("annual_salary");
        String graduatedFrom = request.getParameter("graduated_from");
        String education = request.getParameter("education");
        String teamPosition = request.getParameter("team_position");

        User currentUser = (User) request.getSession().getAttribute("currentUser");

        User user = new User();
        user.setId(currentUser.getId());
        user.setMobilePhoneNumber(currentUser.getMobilePhoneNumber());
        user.setPassword(currentUser.getPassword());
        user.setName(name);
        user.setWorkExperience(Integer.parseInt(workExprience));
        user.setAnnualSalary(Integer.parseInt(annualSalary));
        user.setGraduatedFrom(graduatedFrom);
        user.setHighestEducation(education);
        user.setTeamPosition(teamPosition);
        user.setCreateTime(currentUser.getCreateTime());

        String result;
        if (1 == infoModifyService.infoModify(user)) {
            request.getSession().setAttribute("currentUser", user);
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
