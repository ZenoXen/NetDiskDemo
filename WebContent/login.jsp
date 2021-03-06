<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>网盘管理系统</title>
<link rel="stylesheet" type="text/css" href="css/login.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/vector.js"></script>
</head>
<body>
<%
request.setCharacterEncoding("utf8");
%>
<div id="container">
	<div id="output">
		<div class="containerT">
			<span>网盘系统登录</span>
			<form class="form" id="entry_form" action="LoginServlet" method="post">
				<input type="text" placeholder="用户名" name="uname" value="${cookie.uname.value}">
				<input type="password" placeholder="密码" name="upwd" value="">
				<button type="submit" id="entry_btn">登录</button>
				<a href="register.jsp"><button type="button" id="register_btn">注册</button></a>
				<div id="prompt" class="prompt"></div>
				${(empty requestScope.LoginError)?"":"账户名或密码错误"}
			</form>
		</div>
	</div>
</div>

<script type="text/javascript">
    $(function(){
        Victor("container", "output");   //登录背景函数
        $("#entry_name").focus();
        $(document).keydown(function(event){
            if(event.keyCode==13){
                $("#entry_btn").click();
            }
        });
    });
</script>
</body>
</html>