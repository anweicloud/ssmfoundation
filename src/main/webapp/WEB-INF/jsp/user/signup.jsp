<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/head.jsp" %>
<title>Insert title here</title>
</head>
<body>
Signup , ${ xxl }, ${ basePath }
<hr/>
<table>
	<c:forEach items="${ list }" var="v" varStatus="vs">
	<tr>
		<td>${ vs.count }</td>
        <td>${ v.nick_name }</td>
        <td>${ v.register_time }</td>
        <td>${ v.telephone }</td>
	</tr>
	</c:forEach>
</table>

<div id="app">
    <div class="container">
        <h1>  Vue 分页组件 </h1>
        <div id="pagination"></div>
        <pre>{{ $data|json }}</pre>
    </div>
</div>

<!-- 开发环境版本，包含了有帮助的命令行警告 -->
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script type="text/javascript" src="js/vue.page.js"></script>
<script>
  window.onload=function () {
	// 分页插件
	setTimeout(function() {
		var eventHub = Pagenation('pagination');
		var app = new Vue({
		    el: '#app',
		    data: {
		        total: 30,     // 记录总条数
		        display: 10,   // 每页显示条数
		        current: 1     // 当前第n页 ， 也可以 watch current 的变化 
		    },
		    created: function() {
		    	eventHub.$on('pagechange', this.pagechange);
		    	
		    },
		    methods: {
		    	pagechange: function(p){
		            console.log('pagechange',p);
		        }
		    }
		});
		
	}, 100);
  }
</script>

</body>
</html>