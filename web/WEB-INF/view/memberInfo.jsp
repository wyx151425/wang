<%@ page import="com.rumofuture.wzq.model.domain.User" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="org.json.JSONArray" %><%--
  Created by IntelliJ IDEA.
  User: WangZhenqi
  Date: 2016/12/30
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.png"
          type="image/x-icon"/>
    <title>成员信息</title>
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
          href="${pageContext.request.contextPath}/css/datalist.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/pay.css"/>

    <script>
        var xmlHttpRequest;
        function createXMLHttpRequest() {
            if (window.ActiveXObject) {
                xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
            } else {
                xmlHttpRequest = new XMLHttpRequest();
            }
        }

        function memberInfoModify() {
            if (document.memberInfoForm.name.value == "") {
                return false;
            }

            if (document.getElementById("create_time").value == "now") {
                alert("新添加数据，暂不支持修改");
                return false;
            }

            createXMLHttpRequest();
            xmlHttpRequest.onreadystatechange = memberInfoModifyResult;
            xmlHttpRequest.open("GET", "${pageContext.request.contextPath}/MemberInfoModifyServlet?"
                    + "member_id=" + document.getElementById("member_id").value + "&"
                    + "name=" + document.getElementById("name").value + "&"
                    + "mobile_phone=" + document.getElementById("mobile_phone").value + "&"
                    + "work_exprience=" + document.getElementById("work_exprience").value + "&"
                    + "annual_salary=" + document.getElementById("annual_salary").value + "&"
                    + "graduated_from=" + document.getElementById("graduated_from").value + "&"
                    + "education=" + document.getElementById("education").value + "&"
                    + "team_position=" + document.getElementById("team_position").value);
            xmlHttpRequest.send();
        }

        function memberInfoModifyResult() {
            if (xmlHttpRequest.readyState == 4) {
                if (xmlHttpRequest.status == 200) {
                    var responseResult = xmlHttpRequest.responseText;
                    if (1 == responseResult) {
                        document.getElementById("msg_success_div").style = "display: block;";
                        document.getElementById("msg_success").innerHTML = "修改成功";
                    } else {
                        document.getElementById("msg_error_div").style = "display: block;";
                        document.getElementById("msg_error").innerHTML = "修改失败";
                    }
                }
            }
        }
    </script>
    <script>
        function positiveClick() {
            document.getElementById("msg_success_div").style = "display: none;";
        }

        function nagivateClick() {
            document.getElementById("msg_error_div").style = "display: none;";
        }
    </script>
</head>
<body>
<%!
    private JSONObject currentMember = null;
%>
<%
    JSONArray jsonArray = (JSONArray) request.getSession().getAttribute("currentMembers");
    for (int index = 0; index < jsonArray.length(); index++) {
        JSONObject jsonObject = (JSONObject) jsonArray.get(index);
        if (jsonObject.getLong("id") == Long.parseLong(request.getParameter("id"))) {
            currentMember = jsonObject;
            break;
        }
    }
%>

<div id="nav" class="sidenav">
    <ul>
        <li><a href="index.jsp">首页</a></li>
        <li><a href="team.jsp">团队</a></li>
        <li class="on"><a href="userInfo.jsp">个人</a></li>
    </ul>
</div>

<div class="main-left">
    <div>
        <div>
            <div>
                <p class="biaoti pd1"><b>个人管理</b></p>
            </div>
            <ul class="subnav">
                <li><a href="userInfo.jsp">个人信息</a></li>
                <li class="on"><a href="teamManage.jsp">团队管理</a></li>
                <li><a href="passwordUpdate.jsp">密码修改</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="main-right container">

    <div class="lists box-sh order-table">
        <h5><b>请务必完善成员信息</b></h5>
        <ul class="file-dep">
            <li></li>
        </ul>
    </div>

    <div class="mt order-table user-content">

        <form class="form-horizontal" id="user-form" role="form" action="" name="memberInfoForm"
              onsubmit="return false" method="post">

            <div class="form-group hidden">
                <label class="col-sm-2 control-label nopd  col-lg-1 mt8">成员ID</label>
                <div class="col-sm-10 col-lg-5">
                    <input type="text" id="member_id" name="member_id"
                           class="form-control" value="<%=currentMember.get("id")%>" disabled>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label nopd  col-lg-1 mt8">真实姓名</label>
                <div class="col-sm-10 col-lg-5">
                    <input type="text" class="form-control" name="name"
                           id="name" value="<%=currentMember.get("name")%>" placeholder="必填">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label nopd  col-lg-1 mt8">手机号码</label>
                <div class="col-sm-10 col-lg-5">
                    <input type="text" class="form-control" name="mobile_phone"
                           id="mobile_phone" value="<%=currentMember.get("mobile_phone")%>" placeholder="必填">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label nopd  col-lg-1 mt8">经验(/年)</label>
                <div class="col-sm-10 col-lg-5">
                    <input type="text" class="form-control" name="work_exprience"
                           id="work_exprience" value="<%=currentMember.get("work_exprience")%>" placeholder="不大于2位">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label nopd  col-lg-1 mt8">年薪(/万)</label>
                <div class="col-sm-10 col-lg-5">
                    <input type="text" class="form-control" name="annual_salary"
                           id="annual_salary" value="<%=currentMember.get("annual_salary")%>" placeholder="不大于6位">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label nopd  col-lg-1 mt8">毕业院校</label>
                <div class="col-sm-10 col-lg-5">
                    <input type="text" class="form-control" name="graduated_from"
                           id="graduated_from" value="<%=currentMember.get("graduated_from")%>" placeholder="选填">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label nopd  col-lg-1 mt8">最高学历</label>
                <div class="col-sm-10 col-lg-5">
                    <input type="text" class="form-control" name="education"
                           id="education" value="<%=currentMember.get("education")%>" placeholder="选填">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label nopd  col-lg-1 mt8">团队职务</label>
                <div class="col-sm-10 col-lg-5">
                    <input type="text" class="form-control" name="team_position"
                           id="team_position" value="<%=currentMember.get("team_position")%>" placeholder="选填">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label nopd  col-lg-1 mt8">创建时间</label>
                <div class="col-sm-10 col-lg-5">
                    <input id="create_time" type="text" class="form-control" value="<%=currentMember.get("create_time")%>" disabled>
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-offset-1 col-sm-10 col-lg-5">
                    <button type="submit" class="btn bg-blue save-btn" onclick="memberInfoModify()">保存</button>
                </div>
            </div>
        </form>
    </div>

    <div class="footer">
        <div class="footer-left pull-left">
            <a href="index.jsp">
                <img src="${pageContext.request.contextPath}/images/logo.png" alt="王振琦"/>
            </a>
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

