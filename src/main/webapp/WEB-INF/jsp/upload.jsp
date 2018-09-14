<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/jsp/common/head.jsp" %>
<title>Insert title here</title>
<style type="text/css">
#dropzone {
    background: #ccccc;
    width: 150px;
    height: 50px;
    line-height: 50px;
    text-align: center;
    font-weight: bold;
    border: 1px solid #ddd;
    border-radius: 5px;
}
#dropzone.in {
    width: 600px;
    height: 200px;
    line-height: 200px;
    font-size: larger;
}
#dropzone.hover {
    background: lawngreen;
}
#dropzone.fade {
    -webkit-transition: all 0.3s ease-out;
    -moz-transition: all 0.3s ease-out;
    -ms-transition: all 0.3s ease-out;
    -o-transition: all 0.3s ease-out;
    transition: all 0.3s ease-out;
    opacity: 1;
}
</style>
</head>
<body>
<h1>Spring MVC - jQuery File Upload</h1>
<div style="width:500px;padding:20px">
 
    <input id="fileupload" type="file" name="files[]" data-url="file/upload" multiple="multiple"/>
 
    <div id="dropzone">Drop files here</div>
    
    <div class="progress">
	  <div class="progress-bar" role="progressbar" aria-valuemin="0" aria-valuemax="100" style="width: 0%;"> 0% </div>
	</div>
 
    <table id="uploaded-files" class="table">
        <tr>
            <th>File Name</th>
            <th>File Size</th>
            <th>File Type</th>
            <th>Download</th>
        </tr>
    </table>
 
</div>

<script src="js/jquery.ui.widget.js"></script>
<script src="js/jquery.fileupload.js"></script>
<script type="text/javascript">
$(function () {
    $('#fileupload').fileupload({
        dataType: 'json',
 
        done: function (e, data) {
        	
        	console.log(data, ' done')
        	
            $("tr:has(td)").remove();
            $.each(data.result, function (index, file) {
 
                $("#uploaded-files").append(
                        $('<tr/>')
                        .append($('<td/>').text(file.fileName))
                        .append($('<td/>').text(file.fileSize))
                        .append($('<td/>').text(file.fileType))
                        .append($('<td/>').html("<a href='file/get/"+index+"'>Click</a>"))
                        )//end $("#uploaded-files").append()
            }); 
        },
        
        fail: function(e, data) {
        	console.log(data.errorThrown, 'fial')
        	console.log(data.textStatus, 'fial')
        	console.log(data.jqXHR, 'fial')
        },
 
        progressall: function (e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $('.progress .progress-bar').css(
                'width',
                progress + '%'
            ).text(progress + '%');
        },
 
        dropZone: $('#dropzone')
    });
});
</script>
</body>
</html>