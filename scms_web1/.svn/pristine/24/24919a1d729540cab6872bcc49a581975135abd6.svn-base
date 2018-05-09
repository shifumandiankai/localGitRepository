<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
	<div class="content-wrapper">
			<section class="content-header">
				<h1>
					消息管理界面
				</h1>
				<ol class="breadcrumb">
					<li><a href="/index.do"><i class="fa fa-dashboard"></i> 主页</a></li>
					<li class="active">消息管理</li>
				</ol>
			</section>
			<!-- Main content -->
			<section class="content">
				<div class="box">
					<div class="box-body">
						<table id="example1" class="table table-bordered table-striped">
							<thead>
								<tr>	

									<th>显示消息</th>
									<th>开始时间</th>
									<th>结束时间</th>
									<th>显示屏</th>
									<th>更新时间</th>
									<th>更新操作员</th>
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
	<%@ include file="/pages/basic/message/NewMessage.jsp"%>
	<script src="/plugins/My97DatePicker/WdatePicker.js"></script>
	<script src="/pages/basic/message/message.js"></script>
	<script type="text/javascript">
		function Buttonpermission(){
			<shiro:lacksPermission name="MessageAdd">$("#example1_wrapper a[name='wade.libs.datatable_add']").remove();</shiro:lacksPermission>
			<shiro:lacksPermission name="MessageDelete">$("#example1_wrapper a[name='wade.libs.datatable_del']").remove();</shiro:lacksPermission>
			<shiro:lacksPermission name="MessageEdit">$("#example1_wrapper a[name='wade.libs.datatable_edit']").remove();</shiro:lacksPermission>
		}
		function Seepermission(){
			<shiro:lacksPermission name="MessageSee">$("#example1 tbody td").text("没有浏览权限");</shiro:lacksPermission>
		
		} 
	</script>

