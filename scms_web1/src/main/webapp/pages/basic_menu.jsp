<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <section class="sidebar">
      <ul class="sidebar-menu">
      <li class="leftJichu"><span>基础应用</span></li>
       <shiro:hasPermission name="UserManage">
            <li class="treeview" id ="user_manage">
              <a href="#">
                <i class="fa fa-user"></i><span>用户管理</span>
                <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
              </a>
                <ul class="treeview-menu">
                  <shiro:hasPermission name="UserManage1">
               		<li><a href="#1"  onclick="pageLoad('/pages/basic/user/user.jsp',this);"><i class="fa fa-circle-o"></i>用户管理</a></li>
                  </shiro:hasPermission>     
                 <shiro:hasPermission name="RoleManage">
              		<li><a href="#2" onclick="pageLoad('/pages/basic/role/role.jsp',this);"><i class="fa fa-circle-o"></i>角色管理</a></li>
                 </shiro:hasPermission>
              </ul>
            </li>
        </shiro:hasPermission>

         <shiro:hasPermission name="DeviceManage">
            <li class="treeview" id="device_manage">
              <a href="#">
                <i class="fa fa-server"></i><span>设备管理</span>
                <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
              </a>
              <ul class="treeview-menu">
                <shiro:hasPermission name="ParkingDevice"> 
                  <li><a href="#3" onclick="pageLoad('/pages/pfm/devicelpr/pfmdevice.jsp',this);"><i class="fa fa-circle-o"></i>设备管理</a></li> 
                </shiro:hasPermission>
                <shiro:hasPermission name="Server">
                  <li><a href="#4" onclick="pageLoad('/pages/basic/server/server.jsp',this);"><i class="fa fa-circle-o"></i>服务器</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="MessageManage"> 
                  <li><a href="#5" onclick="pageLoad('/pages/basic/message/message.jsp',this);"><i class="fa fa-circle-o"></i>消息管理</a></li> 
                </shiro:hasPermission>
              </ul>
            </li>
         </shiro:hasPermission>

         <shiro:hasPermission name="SystemSetting">
            <li class="treeview" id="system_setting">
              <a href="#">
                <i class="fa fa-cogs"></i><span>系统配置</span>
                <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
              </a>
              <ul class="treeview-menu">
              	<shiro:hasPermission name="SystemParam">
              		<li><a href="#6" onclick="pageLoad('/pages/systemconfig/params/params.jsp',this);"><i class="fa fa-circle-o"></i>系统参数</a></li>
              	</shiro:hasPermission>
              	<shiro:hasPermission name="WangyuSetting"> 
              		<li><a href="#7" onclick="pageLoad('/pages/basic/wangyu/wangyusetting.jsp',this);"><i class="fa fa-circle-o"></i>网域设置</a></li>
              	</shiro:hasPermission>
             	<shiro:hasPermission name="DatabaseManage">
             		<li><a href="#8" onclick="pageLoad('/pages/basic/database/database.jsp',this);"><i class="fa fa-circle-o"></i>数据库管理</a></li>
             	</shiro:hasPermission>
              </ul>
            </li>    
        </shiro:hasPermission>

         <shiro:hasPermission name="LogSearch">
            <li class="treeview" id="log_search">
              <a href="#">
                <i class="fa fa-pie-chart"></i><span>日志查询</span>
                <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
              </a>
              <ul class="treeview-menu">
               	<shiro:hasPermission name="110Search">
               		<li><a href="#9" onclick="pageLoad('/pages/basic/log/alarm_log.jsp',this);"><i class="fa fa-circle-o"></i>报警查询</a></li>
               	</shiro:hasPermission>
                <shiro:hasPermission name="ConfigSearch">
                	<li><a href="#10" onclick="pageLoad('/pages/basic/log/configurationlog.jsp',this);"><i class="fa fa-circle-o"></i>配置日志</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="ConfigLog">
                	<li><a href="#11" onclick="pageLoad('/pages/basic/log/control_log.jsp',this);"><i class="fa fa-circle-o"></i>控制日志</a></li>
                </shiro:hasPermission>
                 <shiro:hasPermission name="DeviceLog">
                 	<li><a href="#12" onclick="pageLoad('/pages/basic/log/device_log.jsp',this);"><i class="fa fa-circle-o"></i>设备日志</a></li>
                 </shiro:hasPermission>
              </ul>
            </li>    
         </shiro:hasPermission>     
      </ul>
    </section>
  </aside>
