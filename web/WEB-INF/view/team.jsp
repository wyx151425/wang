<%--
  Created by IntelliJ IDEA.
  User: WangZhenqi
  Date: 2016/12/29
  Time: 18:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1">
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
        $(document).ready(function () {

            var userString = $.session.get("current-user");
            if (undefined === userString || null === userString || "" === userString) {
                window.location.href = "${pageContext.request.contextPath}/mvc/login";
            }
            var user = JSON.parse(userString);

            $("#current-user-name").text(user.name);
            $("#user-id").text(user.id);
            $("#user-name").text(user.name);
            $("#user-work-experience").text(user.workExperience);
            $("#user-annual-salary").text(user.annualSalary);
            $("#user-graduated-from").text(user.graduatedFrom);
            $("#user-education").text(user.education);
            $("#user-team-position").text(user.teamPosition);

            $.ajax("${pageContext.request.contextPath}/member/list?id=" + user.id,
                {
                    dataType: "json",
                    type: "get",
                    contentType: "application/json",
                    async: true,
                    success: function (data) {
                        var teamForm = $("#team-form");
                        $.each(data.memberList, function () {
                            var divForm = $("<div class='table-responsive'/>");
                            var tableForm = $("<table class='table app'/>");
                            var tr = $("<tr/>");

                            var td1 = $("<td class='hidden'/>");
                            var h51 = $("<h5/>");
                            $("<strong/>").html(this.id).appendTo(h51);
                            $("<span/>").appendTo(h51);
                            h51.appendTo(td1);
                            $("<span/>").appendTo(td1);
                            td1.appendTo(tr);

                            var td2 = $("<td class='hidden-xs'/>");
                            var h52 = $("<h5/>");
                            $("<strong/>").html(this.name).appendTo(h52);
                            $("<span/>").appendTo(h52);
                            h52.appendTo(td2);
                            $("<span/>").appendTo(td2);
                            td2.appendTo(tr);

                            var td3 = $("<td class='hidden-xs'/>");
                            var h53 = $("<h5/>");
                            $("<strong/>").html(this.workExperience).appendTo(h53);
                            $("<span/>").html("年").appendTo(h53);
                            h53.appendTo(td3);
                            $("<span/>").html("工作经验").appendTo(td3);
                            td3.appendTo(tr);

                            var td4 = $("<td class='hidden-xs'/>");
                            var h54 = $("<h5/>");
                            $("<strong/>").html(this.annualSalary).appendTo(h54);
                            $("<span/>").html("万").appendTo(h54);
                            h54.appendTo(td4);
                            $("<span/>").html("年薪").appendTo(td4);
                            td4.appendTo(tr);

                            var td5 = $("<td class='hidden-xs'/>");
                            var h55 = $("<h5/>");
                            $("<strong/>").html(this.graduatedFrom).appendTo(h55);
                            $("<span/>").appendTo(h55);
                            h55.appendTo(td5);
                            $("<span/>").html("毕业院校").appendTo(td5);
                            td5.appendTo(tr);

                            var td6 = $("<td class='hidden-xs hidden-sm'/>");
                            var h56 = $("<h5/>");
                            $("<strong/>").html(this.education).appendTo(h56);
                            $("<span/>").appendTo(h56);
                            h56.appendTo(td6);
                            $("<span/>").html("最高学历").appendTo(td6);
                            td6.appendTo(tr);

                            var td7 = $("<td class='hidden-xs hidden-sm hidden-md'/>");
                            var h57 = $("<h5/>");
                            $("<strong/>").html(this.teamPosition).appendTo(h57);
                            $("<span/>").appendTo(h57);
                            h57.appendTo(td7);
                            $("<span/>").html("团队职务").appendTo(td7);
                            td7.appendTo(tr);

                            tr.appendTo(tableForm);
                            tableForm.appendTo(divForm);
                            divForm.appendTo(teamForm);
                        });
                    }
                }
            );
        });
    </script>

</head>
<body>

<div id="nav" class="sidenav">
    <ul>
        <li><a href="${pageContext.request.contextPath}/mvc/index">首页</a></li>
        <li class="on"><a href="${pageContext.request.contextPath}/mvc/team">团队</a></li>
        <li><a href="${pageContext.request.contextPath}/mvc/userInfo">个人</a></li>
    </ul>
</div>
<div class="main">
    <div class="main-top" style="position:relative;z-index:1;">
        <a href="${pageContext.request.contextPath}/mvc/memberAdd">
            <button class="btn btn-lg btn-addapp" data-toggle="modal" data-target="#appCreateModal">
                + 添加成员
            </button>
        </a>
        <div class="user-msg">
            <i id="current-user-name" class="hidden-xs"></i>
            <span class="glyphicon glyphicon-off hidden-xs"></span>
        </div>
    </div>
    <div id="team-form">
        <div class="table-responsive">
            <table class="table app">
                <tr>
                    <td class="hidden">
                        <h5>
                            <strong id="user-id"></strong>
                            <span></span>
                        </h5>
                        <span></span>
                    </td>
                    <td class="hidden-xs">
                        <h5>
                            <strong id="user-name"></strong>
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
    </div>

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

<div id="msg-success-div" class="alert alert-success alert-dismissable" style="display: none;">
    <button id="positive-button" type="button" class="close" aria-hidden="true">&times;
    </button>
    <div id="msg-success"></div>
</div>

<div id="msg-error-div" class="alert alert-danger alert-dismissable" style="display: none;">
    <button id="navigate-button" type="button" class="close" aria-hidden="true">&times;
    </button>
    <div id="msg-error"></div>
</div>
</body>
</html>
