<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<link href="/pages/basic/database/database.css" rel="stylesheet"/>
<style type="text/css">
.point-out{
	pointer-events:none;
}
</style>
	<div class="content-wrapper">
		<section class="content-header">
			<h1>
				数据库管理界面
			</h1>
			<ol class="breadcrumb">
				<li><a href="/index.do"><i class="fa fa-dashboard"></i> 主页</a></li>
				<li class="active">数据库管理</li>
			</ol>
		</section>
		<section class="content">
			<div class="box">
				<div class="box-body">
					<table id="t_database" class="table table-bordered table-striped">
						<thead>
							<tr>									
								<th>序号</th>
								<th>备份时间</th>
								<th>数据文件大小</th> 
								<th>备份数据名称</th>							
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
	<script src="/plugins/My97DatePicker/WdatePicker.js"></script>
	<script src="/pages/basic/database/database.js"></script>
	<script type="text/javascript">
		function Buttonpermission(){
			<shiro:lacksPermission name="DatabaseDelete">$("#t_database_wrapper a[name='wade.libs.datatable_del']").remove();</shiro:lacksPermission>
			<shiro:lacksPermission name="DatabaseBackup">$("#t_database_wrapper a[id='wade.libs.datatable_custom_备份']").remove();</shiro:lacksPermission>
			<shiro:lacksPermission name="DatabaseRevert">$("#t_database_wrapper a[id='wade.libs.datatable_custom_还原']").remove();</shiro:lacksPermission>
			<shiro:lacksPermission name="DatabaseConfig">$("#t_database_wrapper a[id='wade.libs.datatable_custom_配置']").remove();</shiro:lacksPermission>
		}
		function Seepermission(){
			<shiro:lacksPermission name="DatabaseSee">$("#t_database tbody td").text("没有浏览权限");</shiro:lacksPermission>
		}
	</script>
	<%@ include file="databaseconf.jsp"%>
