<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				用户管理界面
			</h1>
			<ol class="breadcrumb">
				<li><a href="/index.do"><i class="fa fa-dashboard"></i> 主页</a></li>
				<li class="active">用户管理</li>
			</ol>
		</section>
		<section class="content">
			<div class="box">
				<div class="box-body">
					<table id="userDatetable" class="table table-bordered table-striped">
						<thead>
							<tr>	
								<th></th>
								<th>用户名</th>
								<th>姓名</th>
								<th></th>
								<th></th>
								<th></th>
								<th></th>
								<th></th>
								<th>联系电话</th>
								<th></th>
								<th>创建时间</th>
								<th></th>
								<th></th>
								<th></th>
								<th></th>
								<th></th>
								<th></th>
								<th>创建操作员</th>
								<th>角色</th>
								<th></th>
								<th></th>
								<th></th>
								<th></th>
								<th>到期时间</th>
								<th>备注</th>
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
	<%@ include file="user_dialog.jsp"%>
	<script src="/plugins/My97DatePicker/WdatePicker.js"></script>
	<script src="/plugins/md5/md5.js"></script>
	<script src="/pages/basic/user/user.js"></script>
	<script type="text/javascript">
 		function Buttonpermission(){
			<shiro:lacksPermission name="UserAdd">$("#userDatetable_wrapper a[name='wade.libs.datatable_add']").remove();</shiro:lacksPermission>
			<shiro:lacksPermission name="UserDelete">$("#userDatetable_wrapper a[name='wade.libs.datatable_del']").remove();</shiro:lacksPermission>
			<shiro:lacksPermission name="UserEdit">$("#userDatetable_wrapper a[name='wade.libs.datatable_edit']").remove();</shiro:lacksPermission>
			<shiro:lacksPermission name="PassWordChange">$("#userDatetable_wrapper a[id='wade.libs.datatable_custom_修改密码']").remove();</shiro:lacksPermission>
		}
		function Seepermission(){
			<shiro:lacksPermission name="UserSee">$("#userDatetable tbody td").text("没有浏览权限");</shiro:lacksPermission>
		
		} 
	</script>
