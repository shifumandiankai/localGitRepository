<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<ul class="sidebar-menu">
			<li class="leftJichu"><span> 信息显示应用</span></li>
			<!-- 信息显示菜单 -->
			<shiro:hasPermission name="XinXiFabuManage">
				<li class="treeview" id="idm">
					<a href="#"> 
						<i class="fa fa-car"></i>
						<span>信息发布</span> 
						<span class="pull-right-container">
							<i class="fa fa-angle-left pull-right"></i>
						</span>
					</a>
					<ul class="treeview-menu">
						<shiro:hasPermission name="ProgramMaking">
							<li><a href="#1" onclick="pageLoad('/pages/idm/program/program_making.jsp',this);">
							<i class="fa fa-circle-o"></i>节目制作</a></li>
						</shiro:hasPermission>
						<shiro:hasPermission name="ReviewManage">
							<li><a href="#2" onclick="pageLoad('/pages/idm/review/review.jsp',this);">
							<i class="fa fa-circle-o"></i>审核管理</a></li>
						</shiro:hasPermission>
						<shiro:hasPermission name="ReleaseManage">
							<li><a href="#3" onclick ="pageLoad('/pages/idm/release/release.jsp',this);">
							<i class="fa fa-circle-o"></i>发布管理</a></li>
						</shiro:hasPermission>		
						<shiro:hasPermission name="IdmReport">
							<li><a href="#4" onclick ="pageLoad('/pages/idm/report/report.jsp',this);">
							<i class="fa fa-circle-o"></i>查询统计</a></li>
						</shiro:hasPermission>
					</ul>
				</li>
			</shiro:hasPermission>	
		
			<!-- 会议预约  -->
			<shiro:hasPermission name="MeeTingManage">
				<li class="treeview" id="meeting">
						<a href="#"> <i
						class="fa fa-bus"></i><span>会议预约</span> <span
						class="pull-right-container"> <i
							class="fa fa-angle-left pull-right"></i>
					</span>
					</a>
					<ul class="treeview-menu">
						<shiro:hasPermission name="MeetingReserve">
							<li><a href="#14" onclick="pageLoad('/pages/meeting/reserve.jsp',this);">
							<i class="fa fa-circle-o"></i>会议预约</a></li>
						</shiro:hasPermission>
						<shiro:hasPermission name="MeetingRoom">
							<li><a href="#15" onclick="pageLoad('/pages/meeting/room.jsp',this);">
							<i class="fa fa-circle-o"></i>会议室管理</a></li>
						</shiro:hasPermission>
						<shiro:hasPermission name="MettingReport">
							<li><a href="#16" onclick="pageLoad('/pages/meeting/reports.jsp',this);">
							<i class="fa fa-circle-o"></i>报表查询</a></li>
						</shiro:hasPermission>
					</ul></li>
			</shiro:hasPermission>
			 
	</section>
</aside>
