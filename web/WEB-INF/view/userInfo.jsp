<%@ page import="com.rumofuture.wzq.model.domain.User" %>
<%--
  Created by IntelliJ IDEA.
  User: WangZhenqi
  Date: 2016/12/29
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.png"
          type="image/x-icon"/>
    <title>个人信息</title>
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

        function infoModify() {
            if (document.accountInfoForm.name.value == "") {
                return false;
            }

            createXMLHttpRequest();
            xmlHttpRequest.onreadystatechange = infoModifyResult;
            xmlHttpRequest.open("GET", "${pageContext.request.contextPath}/AccountInfoModifyServlet?"
                    + "name=" + document.getElementById("name").value + "&"
                    + "work_exprience=" + document.getElementById("work_exprience").value + "&"
                    + "annual_salary=" + document.getElementById("annual_salary").value + "&"
                    + "graduated_from=" + document.getElementById("graduated_from").value + "&"
                    + "education=" + document.getElementById("education").value + "&"
                    + "team_position=" + document.getElementById("team_position").value);
            xmlHttpRequest.send();
        }

        function infoModifyResult() {
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
                <li class="on"><a href="userInfo.jsp">个人信息</a></li>
                <li><a href="teamManage.jsp">团队管理</a></li>
                <li><a href="passwordUpdate.jsp">密码修改</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="main-right container">
    <div class="lists box-sh order-table">
        <h5><b>请务必完善个人信息</b></h5>
        <ul class="file-dep">
        </ul>
    </div>

    <div class="mt order-table user-content">

        <form class="form-horizontal" id="user-form" role="form" action="" name="accountInfoForm"
              onsubmit="return false" method="post">

            <div class="form-group">
                <label class="col-sm-2 control-label nopd  col-lg-1">手机号码</label>
                <div class="col-sm-10 col-lg-5">
                    <span id="mobile_phone"><%=((User) session.getAttribute("currentUser")).getMobilePhoneNumber()%></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label nopd  col-lg-1 mt8">真实姓名</label>
                <div class="col-sm-10 col-lg-5">
                    <input type="text" class="form-control" name="name"
                           id="name" value="<%=((User) session.getAttribute("currentUser")).getName()%>" placeholder="必填">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label nopd  col-lg-1 mt8">经验(/年)</label>
                <div class="col-sm-10 col-lg-5">
                    <input type="text" class="form-control" name="work_exprience"
                           id="work_exprience" value="<%=((User) session.getAttribute("currentUser")).getWorkExperience()%>" placeholder="不大于2位">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label nopd  col-lg-1 mt8">年薪(/万)</label>
                <div class="col-sm-10 col-lg-5">
                    <input type="text" class="form-control" name="annual_salary"
                           id="annual_salary" value="<%=((User) session.getAttribute("currentUser")).getAnnualSalary()%>" placeholder="不大于6位">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label nopd  col-lg-1 mt8">毕业院校</label>
                <div class="col-sm-10 col-lg-5">
                    <input type="text" class="form-control" name="graduated_from"
                           id="graduated_from" value="<%=((User) session.getAttribute("currentUser")).getGraduatedFrom()%>" placeholder="选填">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label nopd  col-lg-1 mt8">最高学历</label>
                <div class="col-sm-10 col-lg-5">
                    <input type="text" class="form-control" name="education"
                           id="education" value="<%=((User) session.getAttribute("currentUser")).getEducation()%>" placeholder="选填">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label nopd  col-lg-1 mt8">团队职务</label>
                <div class="col-sm-10 col-lg-5">
                    <input type="text" class="form-control" name="team_position"
                           id="team_position" value="<%=((User) session.getAttribute("currentUser")).getTeamPosition()%>" placeholder="选填">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label nopd  col-lg-1 mt8">创建时间</label>
                <div class="col-sm-10 col-lg-5">
                    <input type="text" class="form-control" value="<%=((User) session.getAttribute("currentUser")).getCreateTime()%>" disabled>
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-offset-1 col-sm-10 col-lg-5">
                    <button type="submit" class="btn bg-blue save-btn" onclick="infoModify()">保存</button>
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
