<%  
    String path = request.getContextPath();  
    String basePath = request.getScheme() + "://"  
            + request.getServerName() + ":" + request.getServerPort()  
            + path + "/";  
    pageContext.setAttribute("basePath",basePath);    
%>
<%@include file="tag.jsp" %>
<%@include file="bootstrap.jsp" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>