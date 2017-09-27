package com.rumofuture.wzq.version1.servlet;

import com.rumofuture.wzq.model.dao.MemberSchema;
import com.rumofuture.wzq.model.domain.Member;
import com.rumofuture.wzq.model.domain.User;
import com.rumofuture.wzq.version1.service.MemberInviteService;
import org.json.JSONArray;
import org.json.JSONObject;

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
@WebServlet("/MemberInviteServlet")
public class MemberInviteServlet extends HttpServlet {
    private MemberInviteService memberInviteService = new MemberInviteService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String mobilePhone = request.getParameter("mobile_phone");
        String workExprience = request.getParameter("work_exprience");
        String annualSalary = request.getParameter("annual_salary");
        String graduatedFrom = request.getParameter("graduated_from");
        String education = request.getParameter("education");
        String teamPosition = request.getParameter("team_position");

        User currentUser = (User) request.getSession().getAttribute("currentUser");

        Member member = new Member();
        member.setLeader(currentUser);
        member.setName(name);
        member.setMobilePhoneNumber(mobilePhone);
        member.setWorkExperience(Integer.parseInt(workExprience));
        member.setAnnualSalary(Integer.parseInt(annualSalary));
        member.setGraduatedFrom(graduatedFrom);
        member.setEducation(education);
        member.setTeamPosition(teamPosition);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(MemberSchema.Table.Cols.ID, 0);
        jsonObject.put(MemberSchema.Table.Cols.NAME, name);
        jsonObject.put(MemberSchema.Table.Cols.MOBILE_PHONE_NUMBER, mobilePhone);
        jsonObject.put(MemberSchema.Table.Cols.LEADER_ID, currentUser.getId());
        jsonObject.put(MemberSchema.Table.Cols.WORK_EXPERIENCE, workExprience);
        jsonObject.put(MemberSchema.Table.Cols.ANNUAL_SALARY, annualSalary);
        jsonObject.put(MemberSchema.Table.Cols.GRADUATED_FROM, graduatedFrom);
        jsonObject.put(MemberSchema.Table.Cols.EDUCATION, education);
        jsonObject.put(MemberSchema.Table.Cols.TEAM_POSITION,teamPosition);
        jsonObject.put(MemberSchema.Table.Cols.CREATE_TIME, "now");

        ((JSONArray) request.getSession().getAttribute("currentMembers")).put(jsonObject);

        int inviteResult = memberInviteService.memberIntive(member);

        String result;
        if (1 == inviteResult) {
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
