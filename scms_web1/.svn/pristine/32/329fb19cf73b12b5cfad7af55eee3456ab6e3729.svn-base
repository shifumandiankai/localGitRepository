<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">

		<ul class="sidebar-menu">


			<li class="leftJichu"><span>智能一卡通应用</span></li>

			<shiro:hasPermission name="YiKaTongManage">
				<li class="treeview" id="personnel"><a href="#"> 
					<i class="fa fa-pie-chart"></i><span>人员管理</span> <span
							class="pull-right-container"> <i
								class="fa fa-angle-left pull-right"></i>
						</span>
					</a>
					<ul class="treeview-menu">
					<shiro:hasPermission name="Personnel">
						<li><a href="#1" onclick="pageLoad('/pages/onecard/personnel/personnel.jsp',this);">
						<i class="fa fa-pie-chart"></i >人员信息</a></li>
					</shiro:hasPermission>
					</ul>
				</li>
			</shiro:hasPermission>

			<shiro:hasPermission name="XiaoFeiManage">
				<li  class="treeview" id="ccm">
				<a href="#"> <i class="fa fa-pie-chart"></i><span>消费管理</span> <span
						class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i>
					</span>
				</a>
				<ul class="treeview-menu">
					<shiro:hasPermission name="Merchant">
						<li><a href="#2" onclick="pageLoad('/pages/ccm/merchant/merchant.jsp',this);">
						<i class="fa fa-circle-o"></i>商户管理</a></li>
					</shiro:hasPermission>

					<shiro:hasPermission name="TimeSec">
						<li><a href="#3" onclick="pageLoad('/pages/ccm/timesec/timesec.jsp',this);">
						<i class="fa fa-circle-o"></i>营业时段</a></li>
					</shiro:hasPermission>
					<!-- 包含高级参数-->
					<shiro:hasPermission name="ConsumeRule">
						<li><a href="#4" onclick="pageLoad('/pages/ccm/consumerule/consume_rule.jsp',this);">
						<i class="fa fa-circle-o"></i>消费规则</a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="FeeManage">
						<li><a href="#5" onclick="pageLoad('/pages/ccm/fee/fee.jsp',this);">
						<i class="fa fa-circle-o"></i>费用管理</a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="CCMReport">
						<li><a href="#6" onclick="pageLoad('/pages/ccm/report/report.jsp',this);">
						<i class="fa fa-circle-o"></i>报表查询</a></li>
					</shiro:hasPermission>
				</ul>
				</li>
			</shiro:hasPermission>

			<shiro:hasPermission name="FangKeManage">
				<li class="treeview" id="vrm"><a href="#"> <i
						class="fa fa-pie-chart"></i><span>访客管理</span> <span
						class="pull-right-container"> <i
							class="fa fa-angle-left pull-right"></i>
					</span>
				</a>
					<ul class="treeview-menu">
						<shiro:hasPermission name="VisitoReservation">
							<li id="carrecord"><a href="#7" onclick="pageLoad('/pages/vrm/visitoReservation/visito_reservation.jsp',this);">
							<i class="fa fa-circle-o"></i>访客预约</a></li>
						</shiro:hasPermission>
						<shiro:hasPermission name="VisitorRegistration">
							<li><a href="#8" onclick="pageLoad('/pages/vrm/visitorRegistration/visitor_registration.jsp',this);">
							<i class="fa fa-circle-o"></i>访客登记</a></li>
						</shiro:hasPermission>
						<shiro:hasPermission name="VrmAuth">
							<li><a href="#9" onclick="pageLoad('/pages/vrm/auth/auth.jsp',this);">
							<i class="fa fa-circle-o"></i>权限配置</a></li>
						</shiro:hasPermission>
						<shiro:hasPermission name="VrmReport">
							<li><a href="#10" onclick="pageLoad('/pages/vrm/auth/auth.jsp',this);">
							<i class="fa fa-circle-o"></i>报表查询</a></li>
						</shiro:hasPermission>
					</ul></li>
			</shiro:hasPermission>

			<shiro:hasPermission name="KaoqinManage">
				<li id="wam" class="treeview"><a href="#"> <i
						class="fa fa-pie-chart"></i><span>考勤管理</span> <span
						class="pull-right-container"> <i
							class="fa fa-angle-left pull-right"></i>
					</span>
				</a>
					<ul class="treeview-menu">
						<shiro:hasPermission name="ShiftManage">
							<li><a href="#11" onclick="pageLoad('/pages/wam/shiftManage/shift_manage.jsp',this);">
							<i class="fa fa-circle-o"></i>班次管理</a></li>
						</shiro:hasPermission>
						<shiro:hasPermission name="TeamManage">
							<li><a href="#12" onclick="pageLoad('/pages/wam/teamManage/team_manage.jsp',this);">
							<i class="fa fa-circle-o"></i>班组管理</a></li>
						</shiro:hasPermission>
						<shiro:hasPermission name="AttendanceScheduling">
							<li><a href="#13" onclick="pageLoad('/pages/wam/attendanceScheduling/attendance_scheduling.jsp',this);">
							<i class="fa fa-circle-o"></i>考勤排班</a></li>
						</shiro:hasPermission>
						<shiro:hasPermission name="AdjustmentSheet">
							<li><a href="#14" onclick="pageLoad('/pages/wam/adjustmentSheet/adjustment_sheet.jsp',this);">
							<i class="fa fa-circle-o"></i>调整单管理</a></li>
						</shiro:hasPermission>
						<shiro:hasPermission name="WAMRun">
							<li><a href="#15" onclick="pageLoad('/pages/wam/wamrun/wam_run.jsp',this);">
							<i class="fa fa-circle-o"></i>考勤运算</a></li>
						</shiro:hasPermission>
						<shiro:hasPermission name="WAMReport">
							<li><a href="#16" onclick="pageLoad('/pages/wam/report/report.jsp',this);">
							<i class="fa fa-circle-o"></i>报表查询</a></li>
						</shiro:hasPermission>
					</ul></li>
			</shiro:hasPermission>

			<shiro:hasPermission name="TiKongManage">
				<li id="ecm" class="treeview"><a href="#"> <i
						class="fa fa-pie-chart"></i><span>梯控管理</span> <span
						class="pull-right-container"> <i
							class="fa fa-angle-left pull-right"></i>
					</span>
				</a>
					<ul class="treeview-menu">
						<shiro:hasPermission name="ElevatorParams">
							<li><a href="#17" onclick="pageLoad('/pages/ecm/elevatorParams/elevator_params.jsp',this);">
							<i class="fa fa-circle-o"></i>电梯参数</a></li>
						</shiro:hasPermission>
						<shiro:hasPermission name="PlanTemplate">
							<li><a href="#18" onclick="pageLoad('/pages/ecm/planTemplate/plan_template.jsp',this);">
							<i class="fa fa-circle-o"></i>计划模板</a></li>
						</shiro:hasPermission>
						<shiro:hasPermission name="FreeTimeSec">
							<li><a href="#19" onclick="pageLoad('/pages/ecm/feeTimeSec/fee_timesec.jsp',this);">
							<i class="fa fa-circle-o"></i>免刷卡时段</a></li>
						</shiro:hasPermission>
						<shiro:hasPermission name="ECMAuth">
							<li><a href="#20" onclick="pageLoad('/pages/ecm/auth/auth.jsp',this);">
							<i class="fa fa-circle-o"></i>梯控授权</a></li>
						</shiro:hasPermission>
						<shiro:hasPermission name="ECMeport">
							<li><a href="#21" onclick="pageLoad('/pages/ecm/report/report.jsp',this);">
							<i class="fa fa-circle-o"></i>梯控信息查询</a></li>
						</shiro:hasPermission>
						<shiro:hasPermission name="ECMParams">
							<li><a href="#22" onclick="pageLoad('/pages/ecm/ecmParams/params.jsp',this);">
							<i class="fa fa-circle-o"></i>梯控参数设置</a></li>
						</shiro:hasPermission>
					</ul></li>
			</shiro:hasPermission>
		</ul>
	</section>
</aside>
