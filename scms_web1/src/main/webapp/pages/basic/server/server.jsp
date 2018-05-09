<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="/pages/basic/server/css/server.css?<%=System.currentTimeMillis() %>" rel="stylesheet"/>
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					服务器界面
				</h1>
				<ol class="breadcrumb">
					<li><a href="/index.do"><i class="fa fa-dashboard"></i> 主页</a></li>
					<li class="active">服务器</li>
				</ol>
			</section>
			<!-- Main content -->
			<section class="content">
				<div class="box">
					<div class="box-body">
						<table id="t_server" class="table table-bordered table-striped">
							<thead>
								<tr>	
									<th>服务器名称</th>
									<th>IP地址</th>
									<th>端口号</th>
									<th>更新时间</th>
									<th>操作员</th>
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
	<%@ include file="server_Dialog.jsp"%>
	<script src="/pages/basic/server/server.js?<%=System.currentTimeMillis() %>"></script>
	<script type="text/javascript">
	 	function Buttonpermission(){
			<shiro:lacksPermission name="ServerAdd">$("#t_server_wrapper a[name='wade.libs.datatable_add']").remove();</shiro:lacksPermission>
			<shiro:lacksPermission name="ServerDelete">$("#t_server_wrapper a[name='wade.libs.datatable_del']").remove();</shiro:lacksPermission>
			<shiro:lacksPermission name="ServerEdit">$("#t_server_wrapper a[name='wade.libs.datatable_edit']").remove();</shiro:lacksPermission>
			<shiro:lacksPermission name="RemoteConfig">$("#t_server_wrapper a[id='wade.libs.datatable_custom_远程配置']").remove();</shiro:lacksPermission>
		}
		function Seepermission(){
			<shiro:lacksPermission name="ServerSee">$("#t_server tbody td").text("没有浏览权限");</shiro:lacksPermission>
		
		} 
	</script>
</body>
</html>
