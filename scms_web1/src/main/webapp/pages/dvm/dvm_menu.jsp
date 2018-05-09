<%@ page contentType="text/html; charset=UTF-8"%>
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>Admin</p>
          <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
        </div>
      </div>
      <!-- search form -->
      <form action="#" method="get" class="sidebar-form">
        <div class="input-group">
          <input type="text" name="q" class="form-control" placeholder="搜索...">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form>
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      
      <ul class="sidebar-menu">
        <li class="header">主  导  航</li>
        <li id="dashboard" class="treeview">
          <a href="/pfm_index.do">
            <i class="fa fa-home"></i> <span>主页</span>    
          </a>
        </li>
        
        <li id="carmanagemenu" class="treeview">
          <a href="/pages/dvm/apply/apply.jsp">
            <i class="fa fa-pencil-square-o"></i><span>派车申请</span>
          </a>
        </li>     
        <li id="feemanage" class="treeview">
          <a href="/pages/dvm/approval/approval.jsp">
            <i class="fa fa-check-square-o"></i><span>派车审批</span>
          </a>
        </li>     
         <li id="chargemonitor" class="treeview">
          <a href="#">
            <i class="fa fa-pie-chart"></i><span>派车单查询</span>
          </a>
        </li>
<!--         <li  id="parkpotsetting" class="treeview">
          <a href="#">
            <i class="fa fa-files-o"></i><span>车场设置</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
            <ul class="treeview-menu">
            <li><a href="/pages/pfm/parkinglot/park.jsp"><i class="fa fa-circle-o"></i>车场管理</a></li>
             <li><a href="/pages/pfm/time/time.jsp"><i class="fa fa-circle-o"></i>通行时段</a></li>
           <li  ><a href="/pages/pfm/booth/booth.jsp"><i class="fa fa-circle-o"></i>岗亭管理</a></li> 
            <li id="binding"><a href="index.html"><i class="fa fa-circle-o"></i>车位绑定</a></li>
            <li id="allowsection"><a href="index.html"><i class="fa fa-circle-o"></i>通行时段</a></li>
            <li id="special"><a href="index.html"><i class="fa fa-circle-o"></i>特殊车牌</a></li>
          </ul>
        </li>   --> 
<!--         <li id="datareport" class="treeview">
          <a href="#">
            <i class="fa fa-files-o"></i><span>报表查询</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
            <ul class="treeview-menu">     
            <li id="carrecord"><a href="index.html"><i class="fa fa-circle-o"></i>收费记录</a></li>
            <li id="feerecord"><a href="index.html"><i class="fa fa-circle-o"></i>充值记录</a></li>
            <li id="carrecord2"><a href="index.html"><i class="fa fa-circle-o"></i>退款记录</a></li>
            <li id="carrecord2"><a href="index.html"><i class="fa fa-circle-o"></i>综合统计</a></li>
            <li id="carrecord2"><a href="index.html"><i class="fa fa-circle-o"></i>异常开闸</a></li>
          </ul>
        </li> -->
             
       
      </ul>
    </section>
  </aside>
