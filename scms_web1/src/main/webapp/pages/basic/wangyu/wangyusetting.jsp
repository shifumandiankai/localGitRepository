<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				网域设置界面
			</h1>
			<ol class="breadcrumb">
				<li><a href="/index.do"><i class="fa fa-dashboard"></i> 主页</a></li>
				<li class="active">网域设置</li>
			</ol>
		</section>
		<section class="content">
			<div class="box">
				<div class="box-body">
					<table id="t_wangyu" class="table table-bordered table-striped">
						<thead>
							<tr>	
								<th>网域名称</th>
								<th>平台地址</th>
								<th>端口</th>
								<th>id</th>
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
	<%@ include file="wangyu_Dialog.jsp"%>
	<script type="text/javascript" src="/pages/basic/wangyu/wangyu.js"></script>
	<script type="text/javascript">
		function Buttonpermission(){
			<shiro:lacksPermission name="WangyuAdd">$("#t_wangyu_wrapper a[name='wade.libs.datatable_add']").remove();</shiro:lacksPermission>
			<shiro:lacksPermission name="WangyuDelete">$("#t_wangyu_wrapper a[name='wade.libs.datatable_del']").remove();</shiro:lacksPermission>
			<shiro:lacksPermission name="WangyuEdit">$("#t_wangyu_wrapper a[name='wade.libs.datatable_edit']").remove();</shiro:lacksPermission>
		}
		function Seepermission(){
			<shiro:lacksPermission name="WangyuSee">$("#t_wangyu tbody td").text("没有浏览权限");</shiro:lacksPermission>
		
		}
	</script>
