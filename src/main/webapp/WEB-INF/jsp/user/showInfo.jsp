<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/head.jsp" %>
<title>userInfo</title>
</head>
<body>
	用户信息 昵称： ${userInfo.nickName} 用户id：${userInfo.id}
	用户电话:${userInfo.telephone } 注册时间： <fmt:formatDate value="${userInfo.registerTime }" pattern="yyyy-MM-dd HH:mm" />
	<br /> ajax显示全部用户信息：
	<div id="show_all_user"></div>
<script type="text/javascript" src="${basePath }/assets/js/jquery.min.js"></script>	
<script type="text/javascript">
    $.ajax({
        type : "get",
        url : "../showInfos",
        dataType : "json",
        success : function(data) {
            console.log(data)
            $.each(data, function(i, user) {
                var p = "<p style='color:red'>昵称:" + user.nickName + "    电话:"
                        + user.telephone + "    注册时间:"
                        + user.registerTime + "    id:" + user.id + "</p>";
                $("#show_all_user").append(p);
            });
        }
    });
</script>
</body>
</html>