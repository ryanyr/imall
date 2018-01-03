<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@include file="/commons/commons.jsp"%>
<html lang="zh-cn">
<head>
<meta charset="UTF-8">
	<title>信贷系统-用户管理-修改密码</title>
    <meta name="keywords" content="用户,修改,管理,修改用户,用户管理" />
	<meta name="description" content="修改密码页面">
	<script src="<%=path%>/JavaScript/index.js"></script>
	<script type="text/javascript">
		var submitForOldPassword = false;
		var submitForNewPassword = false;
		function checkOldPassword(){
			var userId = $("#userId").val();
			var password  = $("#oldPassword").val();
			if(password!=""){
				$.ajax({
					type: "POST",
			   		url: "<%=basePath %>userinfo/checkPwd",
			   		data: "userId="+userId+"&password="+password,
			   		success: function(msg){
			     		if(msg!=1){
			     			$("#isOldPwd").html("原始密码不正确");
		    				//layer.tips('原始密码不正确','#oldPassword', {guide: 1, time: 0,style: ['background-color:red; color:white;', 'red']});	
			     			submitForOldPassword = false;
							return false;
			     		}else if(msg==1){
		    				//layer.tips('原始密码正确','#oldPassword', {guide: 1, time: 0,style: ['background-color:green; color:white;', 'green']});	
			     			$("#isOldPwd").html("");
			     			submitForOldPassword = true;
			     			return true;	
			     		}
			   		}
				});
			}else{
				$("#isOldPwd").html("原始密码不得为空");
				submitForOldPassword = false;
				return false;
			}
		}
		function checkNewPassword(){
			var oldPassword = $("#oldPassword").val();
			var newPassword = $("#newPassword").val();
			var newPasswordAgain = $("#newPasswordAgain").val();
			if(newPassword == ''){
				$("#isNewPassword").html("密码不能为空!");
				return false;
			}else{
				$("#isNewPassword").html("");
			}
			if(newPassword!=newPasswordAgain){
				//$("#isNewPassword").html("密码不一致!");
				$("#isNewPasswordAgain").html("密码不一致!");
	    		//layer.tips('密码不一致!','#newPasswordAgain', {guide: 1, time: 0,style: ['background-color:red; color:white;', 'red']});	
				submitForNewPassword = false;
				return false;
			}else{
				$("#isNewPassword").html("");
				$("#isNewPasswordAgain").html("");
	    		//layer.tips('密码一致!','#newPasswordAgain', {guide: 1, time: 0,style: ['background-color:green; color:white;', 'green']});	
				if(oldPassword==newPasswordAgain){
					$("#isNewPassword").html("新密码和旧密码一样,无需修改!");
	    			$("#isOldPwd").html("新密码和旧密码一样,无需修改!");
	    			//layer.tips('新密码和旧密码一样还修改干什么!','#newPasswordAgain', {guide: 1, time: 0,style: ['background-color:red; color:white;', 'red']});	
				}else{
					submitForNewPassword = true;
					return true;
				}
			}
		}
		function changPwd(){
			$("#oldPassword").select();
			var oldPassword = $("#oldPassword").val();
			var newPassword = $("#newPassword").val();
			var newPasswordAgain = $("#newPasswordAgain").val();
			if($.trim(oldPassword)==''){
				$("#isOldPwd").html("旧密码为空,请输入...");
				return;
			}
			if(newPassword == ''){
				$("#isNewPassword").html("新密码为空,请输入...");
				return;
			}
			if(submitForOldPassword&&checkNewPassword()){
				var userId = $("#userId").val();
				var password = $("#newPasswordAgain").val();
				$.ajax({
					type: "POST",
			   		url: "<%=basePath %>userinfo/changePwd",
			   		data: "userId="+userId+"&password="+password,
			   		success: function(msg){
			     		if(msg==1){
		        			alert("密码修改成功");
		        			location.reload();
		        		
			     		}else if(msg==1){
			     			alert("密码修改成功");
			     		}
			   		},
			   		error : function(){
			   			alert("密码修改失败");
			   		}
				});
			}else if(!submitForOldPassword){
				$("#oldPassword").select();
		    	//layer.tips('该处错误，请重新填写','#oldPassword', {guide: 1, time: 0,style: ['background-color:red; color:white;', 'red']});	
			}else if(!submitForNewPassword){
				$("#newPassword").select();
		    	//layer.tips('该处错误，请重新填写','#newPasswordAgain', {guide: 1, time: 0,style: ['background-color:red; color:white;', 'red']});	
			}
		}
	</script>
</head>
<%@include file="/commons/commons.jsp"%>
<body >
<%@include file="/commons/header.jsp"%>
	<div class="admin">
				<div class="blank10"></div>
				<div class="blank10"></div>
					<input id="userId" type="hidden" value="${userId }" />
					<table class="table table-hover">
						<tr> 
							<td width="500px">
								<label style="float: right;" for="oldPassword">原始密码：</label>
							</td>
							<td>
								<input class="input" style="width: 30%;float: left;" type="password" id="oldPassword" onchange="checkOldPassword();" />
								<font id="isOldPwd" color="red" style="margin-left:5px;float: left;"></font>
							</td>
						</tr>
						<tr>
							<td>
								<label style="float: right;" for="newPassword">新密码：</label>
							</td>
							<td>
								<input class="input" style="width: 30%;float: left;" type="password" id="newPassword" 
								data-validate="required:必填,password:密码不符合规则,length#<20:密码过长" placeholder="请填写密码（必填）"/>
								<font id="isNewPassword" color="red" style="margin-left:5px;float: left;"></font>
							</td>
						</tr>
						<tr>
							<td>
								<label style="float: right;" for="newPasswordAgain">重复新密码：</label>
							</td>
							<td>
								<input class="input" style="width: 30%;float: left;" type="password" id="newPasswordAgain" onblur="checkNewPassword();" />
								<font id="isNewPasswordAgain" color="red" style="margin-left:5px;float: left;"></font>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center" >
								<button class="button border-sub tips" onclick="changPwd();" style="margin:0px; position:fixed;">
									修改密码
								</button>
							</td>
						</tr>
					</table>
</body>
</html>