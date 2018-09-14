<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/head.jsp" %>
<title>Template</title>
</head>
<body>
请先登录系统！
<button onclick="login()">登录</button>
<button onclick="logout()">注销</button>
<script type="text/javascript">
	function login() {
		$.ajax({
	        type : "get",
	        url : "pub/auth",
	        dataType : "json",
	        success : function(data) {
	        	console.log(data, typeof data)
	            alert(data.msg)
	        },
	    });
	}
	function logout() {
		$.ajax({
	        type : "get",
	        url : "pub/logout",
	        dataType : "json",
	        success : function(data) {
	        	console.log(data)
	            alert(data.msg)
	        }
	    });
	}
</script>
</body>
</html>