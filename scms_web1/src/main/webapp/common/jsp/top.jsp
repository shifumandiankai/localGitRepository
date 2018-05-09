<%@page import="com.wadejerry.scms.frame.AppContext"%>
<%@page import="com.wadejerry.scms.frame.entity.LoginInfo"%>
<%@page import="com.wadejerry.scms.modules.auth.dao.BimUserMapper"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<header class="main-header">
	<!-- Logo -->
	<a href="/index.do" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
		<span class="logo-mini"><b>S</b>CMS</span> <!-- logo for regular state and mobile devices -->
		<!-- <span class="logo-lg">车辆管理平台</span> -->
		<span class="logo-lg">ASSAS综合管理平台</span>
	</a>
	<!-- Header Navbar: style can be found in header.less -->
	<nav class="navbar navbar-static-top">
		<!-- Sidebar toggle button-->
		<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
			role="button"> <span class="sr-only">Toggle navigation</span>
		</a>
		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
				<!-- Messages: style can be found in dropdown.less-->
				<!--           <li class="dropdown messages-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-envelope-o"></i>
              <span class="label label-success">4</span>
            </a>
            <ul class="dropdown-menu">
              <li class="header">You have 4 messages</li>
              <li>
                inner menu: contains the actual data
                <ul class="menu">
                  <li>start message
                    <a href="#">
                      <div class="pull-left">
                        <img src="/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                      </div>
                      <h4>
                        Support Team
                        <small><i class="fa fa-clock-o"></i> 5 mins</small>
                      </h4>
                      <p>Why not buy a new awesome theme?</p>
                    </a>
                  </li>
                  end message
                  <li>
                    <a href="#">
                      <div class="pull-left">
                        <img src="/dist/img/user3-128x128.jpg" class="img-circle" alt="User Image">
                      </div>
                      <h4>
                        AdminLTE Design Team
                        <small><i class="fa fa-clock-o"></i> 2 hours</small>
                      </h4>
                      <p>Why not buy a new awesome theme?</p>
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <div class="pull-left">
                        <img src="/dist/img/user4-128x128.jpg" class="img-circle" alt="User Image">
                      </div>
                      <h4>
                        Developers
                        <small><i class="fa fa-clock-o"></i> Today</small>
                      </h4>
                      <p>Why not buy a new awesome theme?</p>
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <div class="pull-left">
                        <img src="/dist/img/user3-128x128.jpg" class="img-circle" alt="User Image">
                      </div>
                      <h4>
                        Sales Department
                        <small><i class="fa fa-clock-o"></i> Yesterday</small>
                      </h4>
                      <p>Why not buy a new awesome theme?</p>
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <div class="pull-left">
                        <img src="/dist/img/user4-128x128.jpg" class="img-circle" alt="User Image">
                      </div>
                      <h4>
                        Reviewers
                        <small><i class="fa fa-clock-o"></i> 2 days</small>
                      </h4>
                      <p>Why not buy a new awesome theme?</p>
                    </a>
                  </li>
                </ul>
              </li>
              <li class="footer"><a href="#">See All Messages</a></li>
            </ul>
          </li> -->
				<!-- Notifications: style can be found in dropdown.less -->
				<!-- 		<li class="dropdown notifications-menu"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"> <i
						class="fa fa-bell-o"></i> <span class="label label-warning">10</span>
				</a>
					<ul class="dropdown-menu">
						<li class="header">您有 10 个通知</li>
						<li>
							inner menu: contains the actual data
							<ul class="menu">
								<li><a href="#"> <i class="fa fa-car text-aqua"></i>
								        12-09 09:09 后勤部-王显江提交派车申请
								</a></li>
								<li><a href="#"> <i class="fa fa-car text-aqua"></i>
									    12-09 10:08 后勤部-李明提交派车申请
								</a></li>
								<li><a href="#"> <i class="fa fa-car text-aqua"></i>
										12-09 10:30 后勤部-刘顺海提交派车申请
								</a></li>
								<li><a href="#"> <i class="fa fa-car text-aqua"></i>
										12-09 11:32 政治部-蔡玉芹提交派车申请
								</a></li>
								<li><a href="#"> <i class="fa fa-car text-red"></i>
										12-09 12:02 政治部-于新取消派车申请
								</a></li>
							</ul>
						</li>
						<li class="footer"><a href="#">查看所有</a></li>
					</ul></li> -->
				<!-- Tasks: style can be found in dropdown.less -->
				<!--  <li class="dropdown tasks-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-flag-o"></i>
              <span class="label label-danger">9</span>
            </a>
            <ul class="dropdown-menu">
              <li class="header">You have 9 tasks</li>
              <li>
                inner menu: contains the actual data
                <ul class="menu">
                  <li>Task item
                    <a href="#">
                      <h3>
                        Design some buttons
                        <small class="pull-right">20%</small>
                      </h3>
                      <div class="progress xs">
                        <div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                          <span class="sr-only">20% Complete</span>
                        </div>
                      </div>
                    </a>
                  </li>
                  end task item
                  <li>Task item
                    <a href="#">
                      <h3>
                        Create a nice theme
                        <small class="pull-right">40%</small>
                      </h3>
                      <div class="progress xs">
                        <div class="progress-bar progress-bar-green" style="width: 40%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                          <span class="sr-only">40% Complete</span>
                        </div>
                      </div>
                    </a>
                  </li>
                  end task item
                  <li>Task item
                    <a href="#">
                      <h3>
                        Some task I need to do
                        <small class="pull-right">60%</small>
                      </h3>
                      <div class="progress xs">
                        <div class="progress-bar progress-bar-red" style="width: 60%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                          <span class="sr-only">60% Complete</span>
                        </div>
                      </div>
                    </a>
                  </li>
                  end task item
                  <li>Task item
                    <a href="#">
                      <h3>
                        Make beautiful transitions
                        <small class="pull-right">80%</small>
                      </h3>
                      <div class="progress xs">
                        <div class="progress-bar progress-bar-yellow" style="width: 80%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                          <span class="sr-only">80% Complete</span>
                        </div>
                      </div>
                    </a>
                  </li>
                  end task item
                </ul>
              </li>
              <li class="footer">
                <a href="#">View all tasks</a>
              </li>
            </ul>
          </li> -->
          <%String photo= ((BimUserMapper)(AppContext.getBean("bimUserMapper"))).selectByPrimaryKey(LoginInfo.getLoginId()).getPhoto();%>
				<!-- User Account: style can be found in dropdown.less -->
				<li class="dropdown user user-menu" style="height:auto" ><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"> <img id="user-image"
						src="<%=photo==null?"/photo/touxiang.png":photo%>" class="user-image"
						alt="User Image"> <span class="hidden-xs"><%=LoginInfo.getLoginName() %></span>
				</a>
					<ul class="dropdown-menu" >
						<!-- User image -->
						<li class="user-header" style="height:230px;">
							
						<div id="imgdiv">
						<input id="imgstatus" type="hidden" value="0"/>
						<img id="imgShow" src="<%=photo==null?"/photo/touxiang.png":photo%>" class="img-circle" style="cursor:pointer" alt="用户头像"  width="160" height="160"/>  
						</div>
							<h3><%=LoginInfo.getLoginName() %><input type="hidden" name="userID" value="<%=LoginInfo.getLoginId() %>"/></h3>
						</li>
						<!-- Menu Body -->
						<li class="user-body" >
						<div id="photo" class="pull-left" style="display:none">
						 <div class="pull-left"><form id="uploadForm" action="/user/uploadtouxiang" method="post"><input type="file" id="up_img" name="up_img"/></form></div>
						 <div class="pull-left"> <input type="button" id="upload" value="上传头像" /></div>
						 </div>
						 <div id="mima" class="pull-left" style="display:none">
						 <div class="pull-left" style="position:relative"><label style="width:80px">原 &nbsp; 密  &nbsp;     码*</label><input id="lastPassword" name="lastpassword" type="password" style="width:150px;"/></div>
						<div class="pull-left" style="position:relative"><label style="width:80px">密  &nbsp; &nbsp;&nbsp;&nbsp;     码*</label><div id ="passwordNotice" class="point-out" style="pointer-events:none;width: 348px;top:-38px; left:80px; display: none;"></div><input id="password1" name="password1" type="password" style="width:150px;"/></div>
						 <div class="pull-left" style="position:relative"><label style="width:80px">确认密码*</label><div id ="confirmpasswordNotice" class="point-out" style="pointer-events:none;width: 348px;top:-38px; left:80px; display: none;"></div><input id="password2" name="password2" type="password" style="width:150px;"/></div>
						<div id="mimasub"><label  style="width:80px"> </label><a id="mimatijiao" class="btn btn-primary ">确认修改</a><a id="chongzhi" class="btn btn-primary">重置</a></div>
						 </div>
							<!-- 				<div class="row">
								<div class="col-xs-4 text-center">
									<a href="#">Followers</a>
								</div>
								<div class="col-xs-4 text-center">
									<a href="#">Sales</a>
								</div>
								<div class="col-xs-4 text-center">
									<a href="#">Friends</a>
								</div>
							</div> /.row -->
						</li>
						<!-- Menu Footer-->
						<li class="user-footer">
							 <div class="pull-left">
								<a id="xiugaimima" href="#" value="0" class="btn btn-default btn-flat">点击修改密码</a>
							</div> 
							<div class="pull-right">
								<a id="signout" href="#" class="btn btn-default btn-flat">退出系统</a>
							</div>
						</li>
					</ul></li>
				<!-- Control Sidebar Toggle Button -->
				<!-- start zy 2017-05- 19 隐藏用户设置样式功能 -->
				<!-- <li><a href="#" data-toggle="control-sidebar"><i
						class="fa fa-gears"></i></a></li> -->
				<!-- end -->
			</ul>
		</div>
	</nav>
</header>
