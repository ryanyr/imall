<!DOCTYPE html>
<html lang="zh-cn">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="paginator" uri="/WEB-INF/paginator.tld"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@include file="../../base/commons.jsp" %>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>保赚后台管理系统</title>
	<!-- Tell the browser to be responsive to screen width -->
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

	<link rel="stylesheet" href="<%=basePath %>JavaScript/plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

	<%@include file="../../base/header.jsp"%>

	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				权限信息
			</h1>
		</section>

		<form action="<%=basePath %>sys/sysRole/rolePerm/update" method="post" id="from1" name="from1">
			<input type="hidden" name="roleId" value="${sysRole.roleId}" />
		<!-- Main content -->
		<section class="content">


			<!-- Default box -->
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">${sysRole.roleName}</h3>

					<div class="box-tools pull-right">
						<button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
								title="Collapse">
							<i class="fa fa-minus"></i></button>
					</div>
				</div>
				<div class="box-body">
					<div class="detail-section">
						<div>
							<ul id="treeDemo" class="ztree"></ul>
							<input type="hidden" id="content" name="content" />
						</div>
					</div>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->

		</section>

			<div class="text-center">
				<button type="button" class="btn bg-maroon margin" onclick="javascript:location.href='<%=basePath%>sys/sysRole/list';">取消</button>
				<button id="submit" type="submit" class="btn bg-maroon margin">保存</button>
			</div>
		</form>
		<!-- /.content -->

	</div>
	<!-- /.content-wrapper -->
	<%@include file="../../base/footer.jsp"%>

	<!-- Control Sidebar -->

	<!-- /.control-sidebar -->
	<!-- Add the sidebar's background. This div must be placed
         immediately after the control sidebar -->
	<div class="control-sidebar-bg"></div>

</div>
<!-- ./wrapper -->

<!-- ztree -->
<script src="<%=basePath %>JavaScript/plugins/ztree/js/jquery.ztree.core.js"></script>
<script src="<%=basePath %>JavaScript/plugins/ztree/js/jquery.ztree.excheck.js"></script>

<!-- page script -->
<SCRIPT type="text/javascript">
    var zTreeObj;
    var zNodes;
    var setting = {
        check: {
            enable: true,
            chkStyle:"checkbox"
        },
        data: {
            key: {
                name: "menuName"
            },
            simpleData: {
                enable: true,
                idKey: "menuId",
                pIdKey: "superiorId"
            }
        },
        view: {
            fontCss : {
                color:"#656565",
                fontSize:"12px"
            }
        }
    };
    $(document).ready(function(){
        $.ajax({
            type: "Get",
            url: '<%=basePath %>sys/sysRole/rolePerm/getMenuTree',
            data: 'roleId=${sysRole.roleId}',
            dataType: "text",
            global: false,
            async: false,
            success: function (strReult) {
                zNodes=eval(strReult);	//把获取的数据转换成json对象在赋值给zNodes
            },
            error: function () {
                alert("Ajax请求数据失败!");
            }
        });
        zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);	//初始化树，传进三个参数，分别是 html对象，setting树属性，zNodes json数据
        $('#submit').click(function (){
            var nodes = zTreeObj.getCheckedNodes();
            var array=new Array();
            for(var i=0; i<nodes.length; i++){   //遍历选中的节点，为array赋值
                array[i] = nodes[i].menuId;
            }
            console.log(array);
            $("#content").val(array);
        });
    });
</SCRIPT>
</body>
</html>
