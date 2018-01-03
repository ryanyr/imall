<!DOCTYPE html>
<html lang="zh-cn">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>AdminLTE 2 | Data Tables</title>
	<!-- Tell the browser to be responsive to screen width -->
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<!-- Bootstrap 3.3.7 -->
	<link rel="stylesheet" href="<%=basePath %>bower_components/bootstrap/dist/css/bootstrap.min.css">
	<!-- Font Awesome -->
	<link rel="stylesheet" href="<%=basePath %>bower_components/font-awesome/css/font-awesome.min.css">
	<!-- Ionicons -->
	<link rel="stylesheet" href="<%=basePath %>bower_components/Ionicons/css/ionicons.min.css">
	<!-- DataTables -->
	<link rel="stylesheet" href="<%=basePath %>bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
	<!-- Theme style -->
	<link rel="stylesheet" href="<%=basePath %>dist/css/AdminLTE.min.css">
	<!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
	<link rel="stylesheet" href="<%=basePath %>dist/css/skins/_all-skins.min.css">

	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->

	<!-- Google Font -->
	<link rel="stylesheet"
		  href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

	<%@include file="../base/header.jsp"%>

	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				Data Tables
				<small>advanced tables</small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
				<li><a href="#">Tables</a></li>
				<li class="active">Data tables</li>
			</ol>
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<form class="form-inline" role="form">
								<div class="box-body">
									<div class="form-group">
										<label for="inputPhone">手机号</label>
										<input type="text" class="form-control" id="inputPhone" placeholder="输入手机号">
									</div>
									<div class="form-group">
										<label for="inputName">姓名</label>
										<input type="text" class="form-control" id="inputName" placeholder="输入姓名">
									</div>
									<div class="form-group">
										<button type="submit" class="btn btn-primary">查询</button>
									</div>
								</div>
								<!-- /.box-body -->
							</form>
						</div>
						<!-- /.box-header -->
						<div class="box-body">
							<table id="example2" class="table table-bordered table-hover">
								<thead>
								<tr>
									<th>Rendering engine</th>
									<th>Browser</th>
									<th>Platform(s)</th>
									<th>Engine version</th>
									<th>CSS grade</th>
								</tr>
								</thead>
								<tbody>

								</tbody>
							</table>
						</div>
						<!-- /.box-body -->
					</div>
					<!-- /.box -->

				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
	<footer class="main-footer">
		<div class="pull-right hidden-xs">
			<b>Version</b> 2.4.0
		</div>
		<strong>Copyright &copy; 2014-2016 <a href="https://adminlte.io">Almsaeed Studio</a>.</strong> All rights
		reserved.
	</footer>

	<!-- Control Sidebar -->

	<!-- /.control-sidebar -->
	<!-- Add the sidebar's background. This div must be placed
         immediately after the control sidebar -->
	<div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- jQuery 3 -->
<script src="<%=basePath %>bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="<%=basePath %>bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- DataTables -->
<script src="<%=basePath %>bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="<%=basePath %>bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="<%=basePath %>bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="<%=basePath %>bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="<%=basePath %>dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="<%=basePath %>dist/js/demo.js"></script>
<!-- page script -->
<script>
    //
    // Pipelining function for DataTables. To be used to the `ajax` option of DataTables
    //
    $.fn.dataTable.pipeline = function ( opts ) {
        // Configuration options
        var conf = $.extend( {
            pages: 5,     // number of pages to cache
            url: '',      // script url
            data: null,   // function or object with parameters to send to the server
                          // matching how `ajax.data` works in DataTables
            method: 'GET' // Ajax HTTP method
        }, opts );

        // Private variables for storing the cache
        var cacheLower = -1;
        var cacheUpper = null;
        var cacheLastRequest = null;
        var cacheLastJson = null;

        return function ( request, drawCallback, settings ) {
            var ajax          = false;
            var requestStart  = request.start;
            var drawStart     = request.start;
            var requestLength = request.length;
            var requestEnd    = requestStart + requestLength;

            if ( settings.clearCache ) {
                // API requested that the cache be cleared
                ajax = true;
                settings.clearCache = false;
            }
            else if ( cacheLower < 0 || requestStart < cacheLower || requestEnd > cacheUpper ) {
                // outside cached data - need to make a request
                ajax = true;
            }
            else if ( JSON.stringify( request.order )   !== JSON.stringify( cacheLastRequest.order ) ||
                JSON.stringify( request.columns ) !== JSON.stringify( cacheLastRequest.columns ) ||
                JSON.stringify( request.search )  !== JSON.stringify( cacheLastRequest.search )
            ) {
                // properties changed (ordering, columns, searching)
                ajax = true;
            }

            // Store the request for checking next time around
            cacheLastRequest = $.extend( true, {}, request );

            if ( ajax ) {
                // Need data from the server
                if ( requestStart < cacheLower ) {
                    requestStart = requestStart - (requestLength*(conf.pages-1));

                    if ( requestStart < 0 ) {
                        requestStart = 0;
                    }
                }

                cacheLower = requestStart;
                cacheUpper = requestStart + (requestLength * conf.pages);

                request.start = requestStart;
                request.length = requestLength*conf.pages;

                // Provide the same `data` options as DataTables.
                if ( $.isFunction ( conf.data ) ) {
                    // As a function it is executed with the data object as an arg
                    // for manipulation. If an object is returned, it is used as the
                    // data object to submit
                    var d = conf.data( request );
                    if ( d ) {
                        $.extend( request, d );
                    }
                }
                else if ( $.isPlainObject( conf.data ) ) {
                    // As an object, the data given extends the default
                    $.extend( request, conf.data );
                }

                settings.jqXHR = $.ajax( {
                    "type":     conf.method,
                    "url":      conf.url,
                    "data":     request,
                    "dataType": "json",
                    "cache":    false,
                    "success":  function ( json ) {
                        cacheLastJson = $.extend(true, {}, json);

                        if ( cacheLower != drawStart ) {
                            json.data.splice( 0, drawStart-cacheLower );
                        }
                        json.data.splice( requestLength, json.data.length );

                        drawCallback( json );
                    }
                } );
            }
            else {
                json = $.extend( true, {}, cacheLastJson );
                json.draw = request.draw; // Update the echo for each response
                json.data.splice( 0, requestStart-cacheLower );
                json.data.splice( requestLength, json.data.length );

                drawCallback(json);
            }
        }
    };

    // Register an API method that will empty the pipelined data, forcing an Ajax
    // fetch on the next draw (i.e. `table.clearPipeline().draw()`)
    $.fn.dataTable.Api.register( 'clearPipeline()', function () {
        return this.iterator( 'table', function ( settings ) {
            settings.clearCache = true;
        } );
    } );
    $(document).ready(function() {
        $('#example2').dataTable( {
            "processing": true,
            "serverSide": true,
            "ajax": $.fn.dataTable.pipeline( {
                url: '<%=basePath %>user/userInfo/listData',//后台处理URL，如：“scripts/server_processing.php”
                pages: 5 // 预读5页
            } )
        } );
    } );

    /*$(function () {
        /!*$('#example1').DataTable()*!/
        $('#example2').DataTable({
            'paging'      : true,
            'lengthChange': false,
            'searching'   : false,
            'ordering'    : true,
            'info'        : true,
            'autoWidth'   : false
        })
    })*/
</script>
</body>
</html>
