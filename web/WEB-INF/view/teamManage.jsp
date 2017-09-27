<%@ page import="org.json.JSONArray" %>
<%@ page import="org.json.JSONObject" %>
<%--
  Created by IntelliJ IDEA.
  User: WangZhenqi
  Date: 2016/12/29
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.png"
          type="image/x-icon"/>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
    <title>团队管理</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/global.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/bootstrap-slider.min.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/css/responsive-nav.css?"/>
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

        function memberRemove(memberId) {
            createXMLHttpRequest();
            xmlHttpRequest.onreadystatechange = memberRemoveResult;
            xmlHttpRequest.open("GET", "${pageContext.request.contextPath}/MemberRemoveServlet?id=" + memberId);
            xmlHttpRequest.send();
        }

        function memberRemoveResult() {
            if (xmlHttpRequest.readyState == 4) {
                if (xmlHttpRequest.status == 200) {
                    var responseResult = xmlHttpRequest.responseText;
                    if (1 == responseResult) {
                        document.getElementById("msg_success_div").style = "display: block;";
                        document.getElementById("msg_success").innerHTML = "删除成功";
                        window.self.location = "teamManage.jsp";
                    } else {
                        document.getElementById("msg_error_div").style = "display: block;";
                        document.getElementById("msg_error").innerHTML = "删除失败";
                    }
                }
            }
        }
        
        function positiveClick() {
            document.getElementById("msg_success_div").style = "display: none;";
        }

        function nagivateClick() {
            document.getElementById("msg_error_div").style = "display: none;";
        }
    </script>

</head>
<body>
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


    <div class="main-right container">
        <div class="lists box-sh order-table">
            <div class="main-right-bottom">
                <div class="table-responsive">
                    <table class="table table-hover table-bordered">
                        <tr class="active">
                            <td>手机号</td>
                            <td>姓名</td>
                            <td>加入时间</td>
                            <td>操作</td>
                        </tr>

                        <%
                            JSONArray jsonArray = (JSONArray) request.getSession().getAttribute("currentMembers");
                            for (int index = 0; index < jsonArray.length(); index++) {
                                JSONObject jsonObject = (JSONObject) jsonArray.get(index);
                                out.println("<tr>");
                                out.println("<td>" + jsonObject.getString("mobile_phone") + "</td>");
                                out.println("<td>" + jsonObject.getString("name") + "</td>");
                                out.println("<td>" + jsonObject.getString("create_time") + "</td>");
                                out.println("<td>");
                                out.println("<a href='memberInfo.jsp?id=" + jsonObject.getLong("id")
                                        + "' class='btn btn-link'>编辑</a>");
                                out.println("<button type='button' class='btn btn-link btn-del' onclick='deleteMember("
                                        + jsonObject.getLong("id") + ");'>删除</button>");
                                out.println("</td>");
                                out.println("</tr>");
                            }
                        %>
                    </table>
                </div>
                <div class="main-right-top">
                    <form action="team.jsp" method="get" class="main-right-cotent">
                        <a href="memberAdd.jsp"
                           class="save-btn pull-right  btn mt10 bg-blue">
                            新增成员
                        </a>
                    </form>
                </div>
            </div>
        </div>
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
