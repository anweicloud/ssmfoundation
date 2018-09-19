<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/head.jsp" %>
<title>Template</title>
</head>
<body>
欢迎登录系统！${currentUser.nickName }，系统时间：${ currentTime }
</body>
</html>