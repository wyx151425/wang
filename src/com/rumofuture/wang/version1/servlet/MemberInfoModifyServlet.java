package com.rumofuture.wang.version1.servlet;

import com.rumofuture.wang.model.schema.MemberSchema;
import com.rumofuture.wang.model.domain.Member;
import com.rumofuture.wang.model.domain.User;
import com.rumofuture.wang.version1.service.MemberInfoModifyService;
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
 * Created by WangZhenqi on 2016/12/30.
 */
@WebServlet("/MemberInfoModifyServlet")
public class MemberInfoModifyServlet extends HttpServlet {
    private MemberInfoModifyService memberInfoModifyService = new MemberInfoModifyService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer memberId = Integer.valueOf(request.getParameter("member_id"));
        String name = request.getParameter("name");
        String mobilePhone = request.getParameter("mobile_phone");
        int workExprience = Integer.parseInt(request.getParameter("work_exprience"));
        int annualSalary = Integer.parseInt(request.getParameter("annual_salary"));
        String graduatedFrom = request.getParameter("graduated_from");
        String education = request.getParameter("education");
        String teamPosition = request.getParameter("team_position");

        User currentUser = (User) request.getSession().getAttribute("currentUser");

        JSONArray jsonArray = (JSONArray) request.getSession().getAttribute("currentMembers");
        JSONObject memberModify = null;
        int memberModifyIndex = 0;
        for (int index = 0; index < jsonArray.length(); index++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(index);
            if (jsonObject.getLong("id") == memberId) {
                memberModify = jsonObject;
                memberModifyIndex = index;
                break;
            }
        }

        Member member = new Member();
        member.setId(memberId);
        member.setMobilePhoneNumber(mobilePhone);
        member.setLeader(currentUser);
        member.setName(name);
        member.setWorkExperience(workExprience);
        member.setAnnualSalary(annualSalary);
        member.setGraduatedFrom(graduatedFrom);
        member.setHighestEducation(education);
        member.setTeamPosition(teamPosition);
        if (null != memberModify) {
            member.setCreateTime(memberModify.getString("create_time"));
        }

        String result;
        if (1 == memberInfoModifyService.memberInfoModify(member)) {
            if (memberModify != null && memberModifyIndex != 0) {
                jsonArray.remove(memberModifyIndex);
                memberModify.put(MemberSchema.Table.Cols.NAME, name);
                memberModify.put(MemberSchema.Table.Cols.MOBILE_PHONE_NUMBER, mobilePhone);
                memberModify.put(MemberSchema.Table.Cols.WORK_EXPERIENCE, workExprience);
                memberModify.put(MemberSchema.Table.Cols.ANNUAL_SALARY, annualSalary);
                memberModify.put(MemberSchema.Table.Cols.GRADUATED_FROM, graduatedFrom);
                memberModify.put(MemberSchema.Table.Cols.HIGHEST_EDUCATION, education);
                memberModify.put(MemberSchema.Table.Cols.TEAM_POSITION, teamPosition);
            }
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
