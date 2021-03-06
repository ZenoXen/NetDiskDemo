<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>注册页面</title>
    <link rel="stylesheet" type="text/css" href="css/register.css">
    <script type="text/javascript" src="js/reg.js"></script>
</head>
<body>
<%request.setCharacterEncoding("utf8");%>
   <div class="wrapper">
        <article>
            <div class="main">
                <form action="RegisterServlet" method="post">
                        <input type="text" name="uname" placeholder="用户名">
                        <input type="password" name="upwd" placeholder="密码">
                        <span style="font-color:red;">
                        ${requestScope.EmptyError?"用户名或密码不能为空":""}
                        ${requestScope.NameError?"用户名格式：首位只能为字母，其余位必须为大小写字母或者数字，6-12位":""}
                        ${requestScope.PwdError?"密码格式：为7-14位大小写字母或者数字":""}
                        ${requestScope.DuplicateError?"该用户名已存在":""}
                        </span>
                    <input type="submit" value="提交"/>
                </form>
            </div>
        </article>
    </div>
</body>
</html>