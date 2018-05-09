<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<div class="content-wrapper">
		<section class="content-header">
			<h1>
				车场设置界面
			</h1>
			<ol class="breadcrumb">
				<li><a href="/index.do"><i class="fa fa-dashboard"></i> 主页</a></li>
				<li class="active">车场设置</li>
			</ol>
		</section>
		<section class="content">
			<div class="box">
				<div class="nav-tabs-custom">
					<ul id="park_setting" class="nav nav-tabs">
						<shiro:hasPermission name="ParkingManage" >
							<li><a href="#park" data-toggle="tab">车场管理</a></li>
						</shiro:hasPermission>
						<shiro:hasPermission name="CrossPeriod">
							<li><a href="#time" data-toggle="tab">通行时段</a></li>
						</shiro:hasPermission>
						<shiro:hasPermission name="InOutManage">
							<li><a href="#entrance" data-toggle="tab">出入口管理</a></li>
						</shiro:hasPermission>
						<shiro:hasPermission name="StaffManage">
							<li><a href="#booth" data-toggle="tab">岗亭管理</a></li>
						</shiro:hasPermission>
						<shiro:hasPermission name="SpecialNumber">
							<li><a href="#specialNumber" data-toggle="tab">特殊车牌</a></li>
						</shiro:hasPermission>
					</ul>
					<div class="tab-content">
						<shiro:hasPermission name="ParkingManage" >
							<%@ include file="/pages/pfm/parkinglot/park.jsp"%>
						</shiro:hasPermission>
				 		<shiro:hasPermission name="CrossPeriod">
							<%@ include file="/pages/pfm/time/time.jsp"%>
						</shiro:hasPermission> 
						<shiro:hasPermission name="InOutManage">
							<%@ include file="/pages/pfm/entrance/entrance.jsp"%>
						</shiro:hasPermission>
						<shiro:hasPermission name="StaffManage">
							<%@ include file="/pages/pfm/booth/booth.jsp"%>
						</shiro:hasPermission>
						<shiro:hasPermission name="SpecialNumber">
							<%@ include file="/pages/pfm/specialcarnum/specialcarnum.jsp"%>
						</shiro:hasPermission>
					</div>
				</div>
			</div>
		</section>
	</div>
<script type="text/javascript">
 	$('#park_setting a:first').tab('show');  //显示第一个标签
</script>