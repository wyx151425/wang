package com.rumofuture.wzq.version1.servlet;

import com.rumofuture.wzq.model.domain.Member;
import com.rumofuture.wzq.version1.service.MemberRemoveService;
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
@WebServlet("/MemberRemoveServlet")
public class MemberRemoveServlet extends HttpServlet {
    private MemberRemoveService memberRemoveService = new MemberRemoveService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Integer memberId = Integer.valueOf(id);
        System.out.println(memberId);

        Member member = new Member();
        member.setId(memberId);

        String result;
        if (1 == memberRemoveService.memberRemove(member)) {
            JSONArray jsonArray = (JSONArray) request.getSession().getAttribute("currentMembers");
            for (int index = 0; index < jsonArray.length(); index++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(index);
                if (jsonObject.getLong("id") == memberId) {
                    jsonArray.remove(index);
                    break;
                }
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
