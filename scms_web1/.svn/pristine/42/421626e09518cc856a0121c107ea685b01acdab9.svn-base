<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<ul class="sidebar-menu">
			<li class="leftJichu"><span> 安全管理应用</span></li>
			<!-- 车俩管理菜单 -->
			<shiro:hasPermission name="CarManage">
				<li class="treeview" id="car_manage">
					<a href="#"> 
						<i class="fa fa-car"></i>
						<span>车辆管理</span> 
						<span class="pull-right-container">
							<i class="fa fa-angle-left pull-right"></i>
						</span>
					</a>
					<ul class="treeview-menu">
						<shiro:hasPermission name="CarManages">
							<li><a href="#1" onclick="pageLoad('/pages/pfm/carmanage/car_manage.jsp',this);">
							<i class="fa fa-circle-o"></i>车辆信息</a></li>
						</shiro:hasPermission>
						<shiro:hasPermission name="AccountManage">
							<li><a href="#2" onclick="pageLoad('/pages/pfm/account/account.jsp',this);">
							<i class="fa fa-circle-o"></i>账户管理</a></li>
						</shiro:hasPermission>
						<shiro:hasPermission name="InOutMonitor">
							<li><a href="#3" onclick ="pageLoad('/pages/pfm/jiankong/jiankong.jsp',this);">
							<i class="fa fa-circle-o"></i>出入监控</a></li>
						</shiro:hasPermission>
						
						<shiro:hasPermission name="ParkingSetting">
							<li><a href="#4" onclick ="pageLoad('/pages/pfm/parkinglot/park_setting.jsp',this);">
							<i class="fa fa-circle-o"></i>车场设置</a></li>
						</shiro:hasPermission>
						
						<shiro:hasPermission name="MingxiRecord">
							<li><a href="#5" onclick ="pageLoad('/pages/pfm/report/ChargeRecord.jsp',this);">
							<i class="fa fa-circle-o"></i>明细报表</a></li>
						</shiro:hasPermission>
						
						<shiro:hasPermission name="TongjiRecord">
							<li><a href="#6" onclick ="pageLoad('/pages/pfm/report/Czreport/czreport.jsp',this);">
							<i class="fa fa-circle-o"></i>统计报表</a></li>
						</shiro:hasPermission>
					</ul>
				</li>
			</shiro:hasPermission>	
			
			<!-- 门禁管理菜单 -->
			<shiro:hasPermission name="DoorManage">
				<li class="treeview" id="access_control">
					<a href="#">
						<i class="fa fa-key"></i><span>门禁管理</span> <span
							class="pull-right-container"> <i
								class="fa fa-angle-left pull-right"></i>
						</span>
					</a>
					<ul class="treeview-menu">
						<!--包括门、门组管理-->
						<shiro:hasPermission name="AcmDoor">
							<li><a href="#7" onclick="pageLoad('/pages/acm/door.jsp',this);"><i
									class="fa fa-circle-o"></i>门信息管理</a></li>
						</shiro:hasPermission>

						<shiro:hasPermission name="AcmTime">
							<li><a href="#8" onclick="pageLoad('/pages/acm/time.jsp',this);"><i
									class="fa fa-circle-o"></i>刷卡时段</a></l刷卡时段i>
						</shiro:hasPermission>

 						<!--包括人员权限和门禁权限-->
						<shiro:hasPermission name="AcmAuth">
							<li><a href="#9" onclick="pageLoad('/pages/acm/auth.jsp',this)"><i
									class="fa fa-circle-o"></i>权限管理</a></li>
						</shiro:hasPermission>

						<shiro:hasPermission name="AcmMonitor">
							<li><a href="#10" onclick= "pageLoad('/pages/acm/monitor.jsp',this);"><i
									class="fa fa-circle-o"></i>事件监控</a></li>
						</shiro:hasPermission>

						<shiro:hasPermission name="AcmDownload">
							<li><a href= "#11" onclick ="pageLoad('/pages/acm/download.jsp',this);"><i
									class="fa fa-circle-o"></i>权限下载</a></li>
						</shiro:hasPermission>

						<shiro:hasPermission name="AcmReport">
							<li><a href="#12" onclick="pageLoad('/pages/acm/report.jsp',this);"><i
									class="fa fa-circle-o"></i>报表查询</a></li>
						</shiro:hasPermission>

					</ul>
				</li>
			</shiro:hasPermission> 
			
			<!-- 电子地图菜单 -->
			<shiro:hasPermission name="ElecMap">
				<li  class="treeview" id="map">
					<a href="#">
				 	<i class="fa fa-map"></i><span>地图管理</span> <span
					class="pull-right-container"> <i
					class="fa fa-angle-left pull-right"></i>
					</span>
					</a>
					<ul class="treeview-menu">
						<shiro:hasPermission name="ElecMap1">
							<li><a href="#13" onclick="pageLoad('/pages/map/elec_map.jsp',this);">
							<i class="fa fa-circle-o"></i>电子地图</a></li>
						</shiro:hasPermission>
					</ul></li>
			</shiro:hasPermission> 	
				
			<!-- 派车管理  -->
			<shiro:hasPermission name="PaicheManage">
				<li class="treeview" id="paiche">
						<a href="#"> <i
						class="fa fa-bus"></i><span>派车管理</span> <span
						class="pull-right-container"> <i
							class="fa fa-angle-left pull-right"></i>
					</span>
					</a>
					<ul class="treeview-menu">
						<shiro:hasPermission name="Paiche">
							<li><a href="#14" onclick="pageLoad('/pages/paiche/paiche.jsp',this);">
							<i class="fa fa-circle-o"></i>派车申请</a></li>
						</shiro:hasPermission>
						<shiro:hasPermission name="PaicheApproval ">
							<li><a href="#15" onclick="pageLoad('/pages/paiche/approval.jsp',this);">
							<i class="fa fa-circle-o"></i>派车审批</a></li>
						</shiro:hasPermission>
						<shiro:hasPermission name="PaicheReport">
							<li><a href="#16" onclick="pageLoad('/pages/paiche/report.jsp',this);">
							<i class="fa fa-circle-o"></i>报表查询</a></li>
						</shiro:hasPermission>
					</ul></li>
			</shiro:hasPermission>
			 
			<!-- 视频监控 -->
			<shiro:hasPermission name="VideoJiankongManage">
				<li class="treeview" id="video">
					<a href="#"> <i
						class="fa fa-video-camera"></i><span>视频监控</span> <span
						class="pull-right-container"> <i
							class="fa fa-angle-left pull-right"></i>
					</span>
					</a>
					<ul class="treeview-menu">
						<shiro:hasPermission name="VideoManage">
							<li><a href="#17" onclick="pageLoad('/pages/video/video.jsp',this);">
							<i class="fa fa-circle-o"></i>视频管理</a></li>
						</shiro:hasPermission>
							<shiro:hasPermission name="VideoPreview">
							<li><a href="#18" onclick="pageLoad('/pages/video/preview.jsp',this);">
							<i class="fa fa-circle-o"></i>视频预览</a></li>
						</shiro:hasPermission>
							<shiro:hasPermission name="Playback">
							<li><a href="#19" onclick="pageLoad('/pages/vidoe/playback.jsp',this);">
							<i class="fa fa-circle-o"></i>录像回放</a></li>
						</shiro:hasPermission>
					</ul></li>
			</shiro:hasPermission> 
	</section>
</aside>
