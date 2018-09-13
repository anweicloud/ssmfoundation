<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/head.jsp" %>
<title>异常测试</title>
</head>
<body>
<a href="env/system">系统异常</a>
<a href="env/custom">手动异常</a>
<a href="javascript:void(0)" onclick="ajaxException()">Ajax异常</a>
<script type="text/javascript">
function ajaxException() {
	$.ajax({
        type : "get",
        url : "env/ajax",
        dataType : "json",
        success : function(data) {
        	console.log(data)
            alert(data.msg)
        },
        async : true
    });
}
</script>
</body>
</html>