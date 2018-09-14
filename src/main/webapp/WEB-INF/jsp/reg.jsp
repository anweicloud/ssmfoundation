<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/head.jsp" %>
<title>Template</title>
</head>
<body>
注册系统！
<script>
$.getJSON('pub/reg2',{}, function(d) {
	console.log(d)
})
</script>
</body>
</html>