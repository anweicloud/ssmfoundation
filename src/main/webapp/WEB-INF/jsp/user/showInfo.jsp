<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/head.jsp" %>
<title>userInfo</title>
</head>
<body>
	用户信息 昵称： ${userInfo.nickName} 用户id：${userInfo.id}
	用户电话:${userInfo.telephone } 注册时间：
	<fmt:formatDate value="${userInfo.registerTime }" pattern="yyyy-MM-dd HH:mm:ss" />
	<br /> ajax显示全部用户信息：
	<div id="show_all_user"></div>
<script type="text/javascript" src="js/jquery.min.js"></script>	
<script type="text/javascript">
    $.ajax({
        type : "get",
        url : "user/showInfos",
        dataType : "json",
        success : function(data) {
            console.log(data)
            $(data).each(
                    function(i, user) {
                        var p = "<p>昵称:" + user.nickName + "    电话:"
                                + user.telephone + "    注册时间:"
                                + user.registerTime + "    id:" + user.id +
                        "</p>";
                        $("#show_all_user").append(p);
                    });
        },
        async : true
    });
</script>
</body>
</html>