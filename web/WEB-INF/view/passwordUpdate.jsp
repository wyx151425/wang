<%@ page import="com.rumofuture.wzq.model.domain.User" %><%--
  Created by IntelliJ IDEA.
  User: WangZhenqi
  Date: 2016/12/30
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>密码修改</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.png"
          type="image/x-icon"/>
    <link rel="shortcut icon" type="image/x-icon"
          href="${pageContext.request.contextPath}/images/favicon.png"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-slider.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/responsive-nav.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/datalist.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/pay.css"/>

    <script type="text/javascript">
        var xmlHttpRequest;
        function createXMLHttpRequest() {
            if (window.ActiveXObject) {
                xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
            } else {
                xmlHttpRequest = new XMLHttpRequest();
            }
        }

        function validateAll() {
            if (document.OnlinePasswordModifyForm.hidpsw.value != document.OnlinePasswordModifyForm.oldpsw.value) {
                document.getElementById('modify-pwd-note').innerHTML = "原密码输入错误";
                return false;
            }
            if (document.OnlinePasswordModifyForm.password.value != document.OnlinePasswordModifyForm.repsw.value) {
                document.getElementById('modify-pwd-note').innerHTML = "新密码输入不一致";
                return false;
            }
            if (document.OnlinePasswordModifyForm.password.value.length < 6) {
                document.getElementById('modify-pwd-note').innerHTML = "密码必须由六位以上字符组成";
                return false;
            }
            createXMLHttpRequest();
            xmlHttpRequest.onreadystatechange = onlinePasswordModifyResult;
            xmlHttpRequest.open("GET", "${pageContext.request.contextPath}/PasswordModifyServlet?mobile=" + document.getElementById("mobile").value + "&"
                    + "password=" + document.getElementById("password").value);
            xmlHttpRequest.send();
        }

        function onlinePasswordModifyResult() {
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
                <li><a href="team.jsp">团队管理</a></li>
                <li class="on"><a href="passwordUpdate.jsp">密码修改</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="main-right container">
    <div class="lists order-table user-pswd">
        <form id="user-form" action=""
              method="post" class="form-horizontal" role="form" name="OnlinePasswordModifyForm" onsubmit="return false">
            <div class="form-group">
                <label class="col-sm-2 control-label nopd  col-lg-1 mt8">现有密码：</label>
                <div class="col-sm-10 col-lg-5">
                    <input type="hidden" value="<%=((User) request.getSession().getAttribute("currentUser")).getMobilePhoneNumber()%>"
                           name="mobile" id="mobile"/>
                    <input type="hidden" value="<%=((User) request.getSession().getAttribute("currentUser")).getPassword()%>"
                           name="hidpsw" id="hidpsw"/>
                    <input type="password" class="form-control" placeholder="必填"
                           name="oldpsw" id="oldpsw"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label nopd  col-lg-1 mt8">新密码：</label>
                <div class="col-sm-10 col-lg-5">
                    <input type="password" class="form-control" placeholder="必填"
                           name="password" id="password"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label nopd mt13 col-lg-1">确认新密码：</label>
                <div class="col-sm-10 col-lg-5">
                    <input type="password" class="form-control" placeholder="必填"
                           name="repsw" id="repsw"/>
                    <p id="modify-pwd-note" class="tips"></p>
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-offset-1 col-sm-10 col-lg-5">
                    <button class="btn bg-blue save-btn"
                            id="updateBtn" onclick="validateAll()">确定
                    </button>
                </div>
            </div>
        </form>
    </div>

    <div class="footer">
        <div class="footer-left pull-left">
            <a href="index.jsp"><img src="${pageContext.request.contextPath}/images/logo.png" alt="王振琦"/></a>
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
