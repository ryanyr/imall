<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <title>信贷系统-登录</title>
    
   
    
    <link rel="stylesheet" href="<%=path%>/style/login/reset.css"/>
    <link rel="stylesheet" href="<%=path%>/style/login/login.css"/>
    <link rel="stylesheet" href="<%=path%>/style/common/commons.css"/>
    <link rel="stylesheet" href="<%=path%>/font/icomoon/style.css"/>
       <script src="<%=path%>/JavaScript/jquery-plug/jquery.min-1.9.0.js"></script>
       <script src="<%=path%>/JavaScript/jquery-plug/Validform_v5.3.2_ncr_min.js"></script>
   <script>
   $(function(){
       $('.valiForm').Validform({
            tiptype:function(msg,o,cssctl){
                var objtip=$("<div class='tip'></div>");
                switch (o.type){
                    case 2:
                        o.obj.parent('div').removeClass('has-error').prevAll('.tip').remove();
                        break;
                    case 3:
//                        o.obj.parent('.loginInput').prevAll('.tip').remove().before(objtip);
                        o.obj.parent().addClass('has-error').prev('.tip').remove();
                        o.obj.parent().before(objtip);
                        break;
                }
                cssctl(objtip,o.type);
                objtip.html("<span class='icon-notification'></span>"+msg);
            },
            showAllError:true,
            postonce:true,
            datatype:{//传入自定义datatype类型【方式二】;
                "name":/^([\u4E00-\u9FA5]{2,5})$/,
                "cardNum":/^[0-9]{16,19}$/
            }
        });
        $('.forgetPwd').click(function(){
            $('.lock').show();
            $('.alertBox').fadeIn(100);
            $(window).on('scroll.alertBox',function(){
                document.documentElement.scrollTop=0;
            });
        });
        $('.btn').click(function(){
            $('.lock').hide();
            $('.alertBox').fadeOut(100);
            $(window).off('scroll.alertBox');
        })
    });
    </script>
</head>
<body>

	<div class="header container">
        <img src="<%=path%>/images/login/logo.jpg" alt="信贷管理系统" class="logo"/>
        <a href="#" class="contact">联系我们</a>
    </div>
    <div class="section">
        <div class="container">
        
            <form  action="<%=path%>/login" class="valiForm" method="post">
                <img src="<%=path%>/images/login/loginIcon.png" alt="惠分期商户登录"/>
                <h1>用户登录</h1>
                <div>${successMsg }</div>
    			<div style="font-size: 13px;margin: 10px;color: rgb(247, 76, 76);">${failMsg }</div>
                <div class="input-group">
                    <i class="icon-user"></i>
                    <input type="text" autofocus="autofocus" placeholder="用户名" name="username" nullmsg="用户名不能为空" datatype="*"/>
                </div>
                <div class="input-group">
                    <i class="icon-key"></i>
                    <input type="password" placeholder="密码" name="password" nullmsg="密码不能为空" datatype="*"/>
                </div>
                <input type="submit" value="登录"/>
<!--         		<a href="<%=path %>/forgetPwd" target="_blank" onclick="return true;" style="color:white; float:right;">忘记密码</a> -->
            </form>
        </div>
    </div>
    <div class="container">
        <ul class="about">
            <li>
                <h1><i class="icon-gift"></i></h1>
                <div class="article">
                    <h2>体验友好</h2>
                    <h2>工具不应成为您的障碍</h2>
                    <p>界面简洁，无需学习即可上手操作。一年优化580个细节，用心做好每处体验</p>
                </div>
            </li>
            <li>
                <h1><i class="icon-heart"></i></h1>
                <div class="article">
                    <h2>贴心服务</h2>
                    <h2>热情而周到全面的服务</h2>
                    <p>全天无休贴心服务，随时为您解决您的疑问</p>
                </div>
            </li>
            <li>
                <h1><i class="icon-cog"></i></h1>
                <div class="article">
                    <h2>管理方便</h2>
                    <h2>操作简单便捷易于管理</h2>
                    <p>直观的商品管理界面，丰富的数据控制功能，让线上管理变得简单</p>
                </div>
            </li>
            <li>
                <h1><a class="icon-briefcase"></a></h1>
                <div class="article">
                    <h2>功能全面</h2>
                    <h2>商品及订单信息随时掌控</h2>
                    <p>商品信息数据全全面覆盖，订单信息精准投放</p>
                </div>
            </li>
        </ul>
    </div>
    <img class="step" src="<%=path%>/images/login/step.jpg" alt="步骤流程"/>
    <div class="footer">经营许可证编号：京ICP备09064160号-50 公安备案号码：京公网安备 110108901166号 客服热线400-001-5287</div>
	
    
</body>
</html>
	<%
	request.getSession().removeAttribute("successMsg");
	request.getSession().removeAttribute("failMsg");
	%>
