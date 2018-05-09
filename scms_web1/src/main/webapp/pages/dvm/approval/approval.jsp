<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>ASSAS综合管理平台</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<%@ include file="/common/jsp/common_css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<%@ include file="/common/jsp/top.jsp"%>
		<%@ include file="/pages/dvm/dvm_menu.jsp"%>
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					派车审批界面
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i>主页</a></li>
					<li class="active">派车审批</li>
				</ol>
			</section>
			<!-- Main content -->

			<section class="content">
				<div class="box">
<!-- 					<div class="box-header"> -->
<!-- 						<h3 class="box-title">Data Table With Full Features</h3> -->
<!-- 					</div> -->
					<!-- /.box-header -->
					<div class="box-body">
						<table id="example1" class="table table-bordered table-striped">
							<thead>
								<tr>	
									<th>单号</th>
									<th>申请人</th>
									<th>部门</th>
									<th>车牌</th>
									<th>用车时间</th>
									<th>用车时长(小时)</th>
									<th>申请时间</th>
									<th>备注</th>	
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>				
									<th></th>	
									<th></th>
								</tr>
							</thead>
							<tbody>						
							</tbody>
							<tfoot>						
							</tfoot>
						</table>
					</div>
				</div>			
			</section>
		</div>
		<%@ include file="approval_dialog.jsp"%>
		<%@ include file="/common/jsp/footer.jsp"%>
		<%@ include file="/common/jsp/sidebar.jsp"%>
	</div>
	<%@ include file="/common/jsp/common_js.jsp"%>
	<script src="approval.js"></script>

</body>
</html>
