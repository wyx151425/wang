<%--
  Created by IntelliJ IDEA.
  User: WangZhenqi
  Date: 2016/12/29
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>新增成员</title>
    <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.png"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/global.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-slider.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/responsive-nav.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/datalist.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/pay.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/zTreeStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-switch.css"/>

    <script>
        var xmlHttpRequest;
        function createXMLHttpRequest() {
            if (window.ActiveXObject) {
                xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
            } else {
                xmlHttpRequest = new XMLHttpRequest();
            }
        }
        function memberIntive() {
            createXMLHttpRequest();
            xmlHttpRequest.onreadystatechange = memberIntiveResult;
            xmlHttpRequest.open("GET", "${pageContext.request.contextPath}/MemberInviteServlet?"
                    + "name=" + document.getElementById("name").value + "&"
                    + "mobile_phone=" + document.getElementById("mobile_phone").value + "&"
                    + "work_exprience=" + document.getElementById("work_exprience").value + "&"
                    + "annual_salary=" + document.getElementById("annual_salary").value + "&"
                    + "graduated_from=" + document.getElementById("graduated_from").value + "&"
                    + "education=" + document.getElementById("education").value + "&"
                    + "team_position=" + document.getElementById("team_position").value);
            xmlHttpRequest.send();
        }

        function memberIntiveResult() {
            if (xmlHttpRequest.readyState == 4) {
                if (xmlHttpRequest.status == 200) {
                    var responseResult = xmlHttpRequest.responseText;
                    if (1 == responseResult) {
                        document.getElementById("msg_success_div").style = "display: block;";
                        document.getElementById("msg_success").innerHTML = "添加成功";
                    } else {
                        document.getElementById("msg_error_div").style = "display: block;";
                        document.getElementById("msg_error").innerHTML = "添加失败";
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
        <li class="on"><a href="team.jsp">团队</a></li>
        <li><a href="userInfo.jsp">个人</a></li>
    </ul>
</div>

<div class="main-left">
    <div>
        <div>
            <div>
                <p class="biaoti pd1"><b>团队管理</b></p>
            </div>
            <ul class="subnav">
                <li class="on"><a href="teamManage.jsp">团队管理</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="main-right container">
    <div class="lists box-sh return-finace">
        <a class="glyphicon glyphicon-arrow-left" href="team.jsp"></a>
        <span>新增成员</span>
    </div>

    <div class="mt order-table">
        <form class="form-horizontal info-edit" role="form" method="post"
              action="" onsubmit="return false">
            <div class="form-group">
                <label class="col-sm-2 nopd control-label mt8 col-lg-1">真实姓名</label>
                <div class="col-sm-6">
                    <input type="text" id="name" name="name" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 nopd control-label mt8 col-lg-1">手机号码</label>
                <div class="col-sm-6">
                    <input type="text" id="mobile_phone" name="mobile_phone" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 nopd control-label mt8 col-lg-1">经验(/年)</label>
                <div class="col-sm-6">
                    <input type="text" id="work_exprience" name="work_exprience" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 nopd control-label mt8 col-lg-1">年薪(/万)</label>
                <div class="col-sm-6">
                    <input type="text" id="annual_salary" name="annual_salary" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 nopd control-label mt8 col-lg-1">毕业院校</label>
                <div class="col-sm-6">
                    <input type="text" id="graduated_from" name="graduated_from" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 nopd control-label mt8 col-lg-1">最高学历</label>
                <div class="col-sm-6">
                    <input type="text" id="education" name="education" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 nopd control-label mt8 col-lg-1">团队职务</label>
                <div class="col-sm-6">
                    <input type="text" id="team_position" name="team_position" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-lg-offset-1 col-sm-10 ">
                    <button class="btn bg-blue save-btn" onclick="memberIntive()">添加</button>
                </div>
            </div>
        </form>
    </div>

    <div id="treeNodes" class=""></div>

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

