<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<link rel="icon" href="images/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
<title>信贷系统-登录</title>

</head>
<body>
	<div class="modal-login">
		<div class="modal-heading">XX系统</div>
		<div class="modal-body">
			<form class="validFrom" action="<%=path%>/login" method="post">
				<div class="errormsg">
				
					<i class="iconfont icon-formerror">${message_login}</i>
				</div>
				<div class="flex form-item mt26">
					<input class="flex-item" type="tel" placeholder="请输入您的账号"
						maxlength="20" name="username" datatype="*" errormsg="账号格式不正确！"
						nullmsg="请输入账号！" autocomplete="off" /> <i class="icon-user"></i>
				</div>
				<div class="flex form-item">
					<input class="flex-item" type="password" name="password"
						placeholder="请输入您的密码" maxlength="20"
						datatype="/[_a-zA-Z0-9]{6,20}/" errormsg="密码格式不正确！"
						nullmsg="请输入密码！" autocomplete="off" /> <i class="icon-lock"></i>
				</div>
				<div class="text-center">
					<button type="submit" class="loginBtn border-radius">登录</button>
				</div>
			</form>
		</div>
	</div>
</body>

</html>