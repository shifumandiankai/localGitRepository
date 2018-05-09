<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.wadejerry.scms.frame.AppContext"%>
<%@page import="com.wadejerry.scms.frame.entity.LoginInfo"%>
<%@page import="com.wadejerry.scms.modules.auth.dao.BimUserMapper"%>
<%@page import="com.wadejerry.scms.frame.constant.user.ConstSystem"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					角色管理界面
				</h1>
				<ol class="breadcrumb">
					<li><a href="/index.do"><i class="fa fa-dashboard"></i> 主页</a></li>
					<li class="active">角色管理</li>
				</ol>
			</section>
			<!-- Main content -->

			<section class="content">
				<div class="box">
					<div class="box-body">
						<table id="example1" class="table table-bordered table-striped">
							<thead>
								<tr>	
									<th>序号</th>
									<th>角色名称</th>
									<th>创建用户序号</th>
									<th>创建用户</th>
									<th>创建时间</th>
									<th>更新时间</th>
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
	<%@ include file="role_dialog.jsp"%>
	<%@ include file="permission.jsp"%>
	<script type="text/javascript">
 		if(false==<%=LoginInfo.isCompanyManager()%>){
			var isManager=false;
		}
		else{
			var isManager=true;
		}
 		var pfm=0;var acm=0; var bim=0;
	<c:if test="<%=LoginInfo.getSubSystem().contains(ConstSystem.SUB_SYSTEM_CODE_PFM) %>">
		 pfm = 1;//标志是否停车场系统
	</c:if>	

	<c:if test="<%=LoginInfo.getSubSystem().contains(ConstSystem.SUB_SYSTEM_CODE_ACM) %>">
 		acm = 1;//标志是否有门禁系统
	</c:if>	
	<c:if test="<%=LoginInfo.getSubSystem().contains(ConstSystem.SUB_SYSTEM_CPDE_BIM) %>">
		 bim =1;//标志是否有一卡通系统
	</c:if>
	
	</script>
	<script src="/pages/basic/role/role.js?<%=System.currentTimeMillis() %>"></script>
	<script type="text/javascript">
 		function Buttonpermission(){
			<shiro:lacksPermission name="AuthConfig">$("#example1_wrapper a[id='wade.libs.datatable_custom_权限配置']").remove();</shiro:lacksPermission>
			if(false==<%=LoginInfo.isCompanyManager()%>){
				$("#example1_wrapper a[name='wade.libs.datatable_add']").remove();
				$("#example1_wrapper a[name='wade.libs.datatable_del']").remove();
				$("#example1_wrapper a[name='wade.libs.datatable_edit']").remove();
			}
		}
		function Seepermission(){
			<shiro:lacksPermission name="RoleSee">$("#example1 tbody td").text("没有浏览权限");</shiro:lacksPermission>
		
		} 
	</script>