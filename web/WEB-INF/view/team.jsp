<%@ page import="com.rumofuture.wzq.model.domain.User" %>
<%@ page import="org.json.JSONArray" %>
<%@ page import="org.json.JSONObject" %><%--
  Created by IntelliJ IDEA.
  User: WangZhenqi
  Date: 2016/12/29
  Time: 18:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.png"
          type="image/x-icon"/>
    <title>团队信息</title>
    <link rel="stylesheet" type="text/css"
          href="http://assets2016.bmob.cn/css/bootstrap.min.css?26"/>

    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/global.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/bootstrap-slider.min.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/responsive-nav.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/base.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/index.css"/>

    <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquerysession.js"></script>
    <script>
        function positiveClick() {
            document.getElementById("msg_success_div").style = "display: none;";
        }

        function nagivateClick() {
            document.getElementById("msg_error_div").style = "display: none;";
        }
        
        $(document).ready(function () {
            var userString = $.session.get("current-user");
            if (undefined === userString || null === userString || "" === userString) {
                window.location.href = "${pageContext.request.contextPath}/mvc/login";
            }
            var user = JSON.parse(userString);
            $("#user-name").text(user.name);
            $("#user-work-experience").text(user.workExperience);
            $("#user-annual-salary").text(user.annualSalary);
            $("#user-graduated-from").text(user.graduatedFrom);
            $("#user-education").text(user.education);
            $("#user-team-position").text(user.teamPosition);
            $.get("${pageContext.request.contextPath}/member/list?id=" + user.id, function (data) {
                alert(data);
            });
        });
    </script>

</head>
<body>

<div id="nav" class="sidenav">
    <ul>
        <li><a href="index.jsp">首页</a></li>
        <li class="on"><a href="team.jsp">团队</a></li>
        <li><a href="userInfo.jsp">个人</a></li>
    </ul>
</div>
<div class="main">
    <div class="main-top" style="position:relative;z-index:1;">
        <a href="memberAdd.jsp">
            <button class="btn btn-lg btn-addapp" data-toggle="modal" data-target="#appCreateModal">
                + 添加成员
            </button>
        </a>
        <div class="user-msg">
            <i id="user-name" class="hidden-xs"></i>
            <span class="glyphicon glyphicon-off hidden-xs"></span>
        </div>
    </div>

    <div class="table-responsive">
        <table class="table app">
            <tr>
                <td class="hidden">
                    <h5>
                        <strong></strong>
                        <span></span>
                    </h5>
                    <span></span>
                </td>
                <td class="hidden-xs">
                    <h5>
                        <strong></strong>
                        <span></span>
                    </h5>
                    <span></span>
                </td>
                <td class="hidden-xs">
                    <h5>
                        <strong id="user-work-experience"></strong>
                        <span>年</span>
                    </h5>
                    <span>工作经验</span>
                </td>

                <td class="hidden-xs">
                    <h5>
                        <strong id="user-annual-salary"></strong>
                        <span>万</span>

                    </h5>
                    <span>年薪</span>
                </td>

                <td class="hidden-xs">
                    <h5>
                        <strong id="user-graduated-from"></strong>
                        <span></span>
                    </h5>
                    <span>毕业院校</span>
                </td>

                <td class="hidden-xs hidden-sm">
                    <h5>
                        <strong id="user-education"></strong>
                        <span></span>
                    </h5>
                    <span>最高学历</span>
                </td>
                <td class="hidden-xs hidden-sm hidden-md">
                    <h5>
                        <strong id="user-team-position"></strong>
                        <span></span>
                    </h5>
                    <span>团队职务</span>
                </td>

            </tr>
        </table>
    </div>

    <%--<%--%>
        <%--JSONArray jsonArray = (JSONArray) request.getSession().getAttribute("currentMembers");--%>
        <%--for (int index = 0; index < jsonArray.length(); index++) {--%>
            <%--JSONObject jsonObject = (JSONObject) jsonArray.get(index);--%>
            <%--out.println("<div class='table-responsive'>");--%>
            <%--out.println("<table class='table app'>");--%>
            <%--out.println("<tr>");--%>
            <%--out.println("<td class='hidden'>");--%>
            <%--out.println("<h5>");--%>
            <%--out.println("<strong>" + jsonObject.getLong("id") + "</strong>");--%>
            <%--out.println("<span></span>");--%>
            <%--out.println("</h5>");--%>
            <%--out.println("<span></span>");--%>
            <%--out.println("</td>");--%>
            <%--out.println("<td class='hidden-xs'>");--%>
            <%--out.println("<h5>");--%>
            <%--out.println("<strong>" + jsonObject.getString("name") + "</strong>");--%>
            <%--out.println("<span></span>");--%>
            <%--out.println("</h5>");--%>
            <%--out.println("<span></span>");--%>
            <%--out.println("</td>");--%>
            <%--out.println("<td class='hidden-xs'>");--%>
            <%--out.println("<h5>");--%>
            <%--out.println("<strong>" + jsonObject.getInt("work_exprience") + "</strong>");--%>
            <%--out.println("<span>年</span>");--%>
            <%--out.println("</h5>");--%>
            <%--out.println("<span>工作经验</span>");--%>
            <%--out.println("</td>");--%>
            <%--out.println("<td class='hidden-xs'>");--%>
            <%--out.println("<h5>");--%>
            <%--out.println("<strong>" + jsonObject.getInt("annual_salary") + "</strong>");--%>
            <%--out.println("<span>万</span>");--%>
            <%--out.println("</h5>");--%>
            <%--out.println("<span>年薪</span>");--%>
            <%--out.println("</td>");--%>
            <%--out.println("<td class='hidden-xs'>");--%>
            <%--out.println("<h5>");--%>
            <%--out.println("<strong>" + jsonObject.getString("graduated_from") + "</strong>");--%>
            <%--out.println("<span></span>");--%>
            <%--out.println("</h5>");--%>
            <%--out.println("<span>毕业院校</span>");--%>
            <%--out.println("</td>");--%>
            <%--out.println("<td class='hidden-xs'>");--%>
            <%--out.println("<h5>");--%>
            <%--out.println("<strong>" + jsonObject.getString("education") + "</strong>");--%>
            <%--out.println("<span></span>");--%>
            <%--out.println("</h5>");--%>
            <%--out.println("<span>最高学历</span>");--%>
            <%--out.println("</td>");--%>
            <%--out.println("<td class='hidden-xs'>");--%>
            <%--out.println("<h5>");--%>
            <%--out.println("<strong>" + jsonObject.getString("team_position") + "</strong>");--%>
            <%--out.println("<span></span>");--%>
            <%--out.println("</h5>");--%>
            <%--out.println("<span>团队职务</span>");--%>
            <%--out.println("</td>");--%>
            <%--out.println("</tr>");--%>
            <%--out.println("</table>");--%>
            <%--out.println("</div>");--%>
        <%--}--%>
    <%--%>--%>

    <div class="footer">
        <div class="footer-left pull-left">
            <a href="index.jsp">
                <img src="${pageContext.request.contextPath}/images/logo.png" alt="王振琦"/></a>
        </div>
        <div class="footer-right pull-right">
            <a href="">2014级软件工程三班王振琦</a>
        </div>
    </div>
</div>

<div id="msg_success_div" class="alert alert-success alert-dismissable" style="display: none;">
    <button id="positive_button" type="button" class="close" aria-hidden="true" onclick="positiveClick()">&times;</button>
    <div id="msg_success"></div>
</div>

<div id="msg_error_div" class="alert alert-danger alert-dismissable" style="display: none;">
    <button id="nagivate_button" type="button" class="close" aria-hidden="true" onclick="nagivateClick()">&times;</button>
    <div id="msg_error"></div>
</div>
</body>
</html>
