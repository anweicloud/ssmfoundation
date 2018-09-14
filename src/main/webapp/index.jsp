<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/head.jsp" %>
<title>Insert title here</title>
</head>
<body>
Welcome!
<button onclick="login()">点击login</button>
<script>
	function login() {
		$.ajax({
			url: "user/reg",
			type: "GET",
			dataType: "JSON",
			async:false,
			data:{},
			error: function(data) { console.log(data);console.log('error') },
			success: function(data) {
				console.log(data)
				alert(data.msg);
				window.location.href='pub/login';
			}
		});
	}
</script>
</body>
</html>