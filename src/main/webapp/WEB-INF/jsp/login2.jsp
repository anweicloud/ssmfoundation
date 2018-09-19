<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/head.jsp" %>
<title>Login</title>
</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <form role="form" class="" action="shiro" method="post">
                <div class="form-group">
                     <label for="exampleInputEmail1">UserName</label>
                     <input type="text" class="form-control" id="exampleInputEmail1" name="username" />
                </div>
                <div class="form-group">
                     <label for="exampleInputPassword1">Password</label>
                     <input type="password" class="form-control" id="exampleInputPassword1" name="password"/>
                </div>
                <div class="checkbox">
                     <label><input type="checkbox" />Remember me !</label>
                </div> 
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
            <c:if test="${ not empty msg }">
            <p class="alert alert-danger">${msg }</p>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>