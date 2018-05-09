<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div class="content-wrapper">
	<section class="content-header">
		<h1>
			设备管理界面
		</h1>
		<ol class="breadcrumb">
			<li><a href="/index.do"><i class="fa fa-dashboard"></i> 主页</a></li>
			<li class="active">设备管理</li>
		</ol>
	</section>
	<section class="content">
	<div class="box">
		<div class="nav-tabs-custom">
			<ul id="myTab" class="nav nav-tabs">
				<shiro:hasPermission name="LprDevice">
					<li name="deviceli" id="li1"><a href="#first" data-toggle="tab">LPR管理</a></li>
				</shiro:hasPermission>
				<shiro:hasPermission name="ScreenDevice"> 
					<li name="deviceli" id="li2"><a href="#devicescreen" data-toggle="tab"> 显示屏设备 </a></li>
				</shiro:hasPermission>
				</ul>
				<div id="myTabContent" class="tab-content">
					<shiro:hasPermission name="LprDevice">
						<div class="tab-pane fade in" id="first">
							<table id="LPRexample"
								class="table table-bordered table-striped">
								<thead>
									<tr>
										<th>序号</th>
										<th>编号</th>
										<th>设备名称</th>
										<th>IP地址</th>
										<th>端口</th>
										<th>关联服务</th>
										<th>登录名称</th>
										<th>登录密码</th>
										<th>创建时间</th>
										<th>服务Id</th>
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
					</shiro:hasPermission>
					<shiro:hasPermission name="ScreenDevice"> 
						<div class="tab-pane fade in " id="devicescreen">
							<table id="screenexample"
								class="table table-bordered table-striped">
								<thead>
									<tr>
										<th>序号</th>
										<th>编号</th>
										<th>设备名称</th>
										<th>更新时间</th>
										<th>关联的设备</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
								<tfoot>
								</tfoot>
							</table>
						</div>
					</shiro:hasPermission>
				</div>
			</div>
		</div>

	</section>
</div>
<%@ include file="pfmdevice_dialog.jsp"%>
<%@ include file="pfmdevice_configdialog.jsp"%>
<%@ include file="screen_dialog.jsp"%>
<script src="/pages/pfm/devicelpr/pfmdevice.js"></script>
<script type="text/javascript">
 		function Buttonpermission(){
			<shiro:lacksPermission name="DeviceAdd">$("#LPRexample_wrapper a[name='wade.libs.datatable_add']").remove();</shiro:lacksPermission>//LPR设备添加
			<shiro:lacksPermission name="DeviceDelete">$("#LPRexample_wrapper a[name='wade.libs.datatable_del']").remove();</shiro:lacksPermission>//LPR设备删除
			<shiro:lacksPermission name="DeviceEdit">$("#LPRexample_wrapper a[name='wade.libs.datatable_edit']").remove();</shiro:lacksPermission>//LPR设备修改
			<shiro:lacksPermission name="ScreenAdd">$("#screenexample_wrapper a[name='wade.libs.datatable_add']").remove();</shiro:lacksPermission>//显示屏幕添加
			<shiro:lacksPermission name="ScreenDelete">$("#screenexample_wrapper a[name='wade.libs.datatable_del']").remove();</shiro:lacksPermission>//显示屏幕删除
			<shiro:lacksPermission name="ScreenEdit">$("#screenexample_wrapper a[name='wade.libs.datatable_edit']").remove();</shiro:lacksPermission>//显示屏幕修改
		}
		function Seepermission(){
			<shiro:lacksPermission name="DeviceSee">$("#LPRexample tbody td").text("没有浏览权限");</shiro:lacksPermission>//显示屏幕浏览
			<shiro:lacksPermission name="ScreenSee">$("#screenexample tbody td").text("没有浏览权限");</shiro:lacksPermission>//显示屏幕浏览
		}  
</script>