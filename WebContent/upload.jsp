<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>上传文件</title>
<style>
label{
width:120px;
height:70px;
display:block;
border:1px dashed black;
}
#progressBar{width: 300px;height: 20px;border: 1px #EEE solid;}
#progress{width: 0%;height: 20px;background-color: lime;}
iframe{
display:none;
}
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
function upload(){
    $("#f1").submit();
    var pro=null;
    pro=setInterval(function(){
        $.get("UploadServlet","",function(data){
            if(data=='100%'){
                clearInterval(pro);
                $("#proInfo").text("上传进度：100%");
                 //更新进度条
                $("#progress").width("100%");
            }else{//正在上传
                //更新进度信息
                $("#proInfo").text("上传进度："+data);
                //更新进度条
                $("#progress").width(data);
            }
        });
    },200);
}
</script>
</head>
<body>
<iframe name="invis"></iframe>
<form target="invis" action="UploadServlet" method="post" enctype="multipart/form-data" id="f1">
文件：<input name="file" type="file">
<input type="button" value="上传" onclick="upload();">
<div id="progressBar">
	<div id="progress"></div>
</div>
<span id="proInfo">上传进度：0%</span>
</form>
<a href="GetServlet">返回主页</a>
</body>
</html>