<%@page import="org.apache.shiro.SecurityUtils"%>
<%@page import="org.apache.shiro.mgt.SecurityManager"%>
<%@page import="org.apache.shiro.subject.Subject"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>ASSAS综合管理平台</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!--引入系统样式-->
  <%@ include file="common/jsp/common_css.jsp"%>
  <!--引入主界面样式-->
  <link rel="stylesheet" type="text/css" href="yindex.css">
</head>

<body class="hold-transition skin-green-light  sidebar-mini ">

<div class="wrapper breadcrumb">
<!--引用top页面-->
<%@ include file="common/jsp/top.jsp"%>

<div class="data-container">
	
	<div class="homePageDing">	
	   <div class="homePageDingWord1"></div>	
	   <div class="homePage">      
        <div class="homePageWord"><a href="javascript:;">&nbsp;&nbsp;系统首页&nbsp;&nbsp;</a></div>
        <div class="versionInfo"><a id="versionLiscense" href="javascript:;">版权信息</a></div>    
        <div class="versionInfo homePageWade"><a href="http://www.wadejerry.com">Wadejerry</a></div>   
     </div>
	</div>
	
	 <div class="homePageGap"></div>
			<%
				Subject subject = SecurityUtils.getSubject();
        		//安全管理权限
				boolean churu = subject.isPermitted("CarManage") || subject.isPermitted("DoorManage")
						|| subject.isPermitted("ElecMap") || subject.isPermitted("PaicheManage")
           				|| subject.isPermitted("VideoJiankongManage");
       			 //智能一卡通模块
				boolean yikatong = subject.isPermitted("YiKaTongManage") || subject.isPermitted("KaoqinManage")
						|| subject.isPermitted("FangKeManage") || subject.isPermitted("TiKongManage")
						|| subject.isPermitted("XiaoFeiManage");
        		//信息显示模块
				boolean xinxi = subject.isPermitted("XinXiFabuManage") || subject.isPermitted("MeeTingManage");
				pageContext.setAttribute("churu", churu);
				pageContext.setAttribute("yikatong", yikatong);
				pageContext.setAttribute("xinxi", xinxi);
			%>
	<c:if test="${churu}">
    <div class="data-container-piece data-container-one">
      <div class="data-container-title clearfix">
        <h2>安全管理</h2>
        <i></i>
      </div>
      
      <ul class="homePageNav homePageNav-pills border-bottom-index">   
    	  <shiro:hasPermission name="CarManage"> 
        <li>
          <a href="safety.do?menu_id=car_manage">
            <i class="data-icon55-car1 pull-left"></i>
             <br>车辆管理<br>
            <p>车辆信息、账户管理、出入监控、报表查询</p>
          </a>
        </li>  
        </shiro:hasPermission>
      
        <shiro:hasPermission name="DoorManage">
        <li>
          <a href="safety.do?menu_id=access_control">
            <i class="data-icon55-kao3  pull-left"></i>
            <br>门禁管理<br>
            <p>门信息管理、刷卡时段、权限管理、权限下载</p>
          </a>
        </li>
       
      </shiro:hasPermission>
       
        <shiro:hasPermission name="ElecMap">
        <li>
           <a href="safety.do?menu_id=map">
            <i class="data-icon55-map pull-left"></i>
            <br>地图管理<br>
            <p>电子地图</p>
          </a>
        </li> 
         </shiro:hasPermission>
       <shiro:hasPermission name="PaicheManage">
        <li>
           <a href="safety.do?menu_id=paiche">
            <i class="data-icon55-paiche pull-left"></i>
            <br>派车管理<br>
            <p>派车申请、派车审批、报表查询</p>
          </a>
        </li> 
		 </shiro:hasPermission>
     <shiro:hasPermission name="VideoJiankongManage">
		    <li>
           <a href="safety.do?menu_id=video">
            <i class="data-icon55-video pull-left"></i>
            <br>视频监控<br>
            <p>视频管理、视频预览、录像回放</p>
          </a>
        </li> 
      </shiro:hasPermission>    
      </ul>
      </div>
   </c:if>
   <c:if test="${yikatong}">
       <div class="data-container-piece data-container-one">
            <div class="data-container-title clearfix">
        <h2>智能一卡通</h2>
        <i></i>
      </div>
      <ul class="homePageNav homePageNav-pills border-bottom-index">    

        <shiro:hasPermission name="YiKaTongManage"> 
        <li>
          <a href="onecard.do?menu_id=personnel">
            <i class="data-icon55-onecard pull-left"></i>
         	<br>一卡通<br>
            <p>人员管理、部门管理</p>
          </a>
        </li>    
         </shiro:hasPermission>

       <shiro:hasPermission name="KaoqinManage"> 
        <li>
           <a href="onecard.do?menu_id=wam">
            <i class="data-icon55-wam pull-left"></i>
            <br>考勤管理<br>
            <p>班次管理、班组管理、考勤排班、报表查询</p>
          </a>
        </li>
         </shiro:hasPermission>
        <shiro:hasPermission name="FangKeManage"> 
        <li>
           <a href="onecard.do?menu_id=vrm">
            <i class="data-icon55-vrm pull-left"></i>
            <br>访客管理<br>
            <p>访客预约、访客登记、报表查询</p>
          </a>
        </li>
         </shiro:hasPermission>
        <shiro:hasPermission name="TiKongManage"> 
        <li>
           <a href="onecard.do?menu_id=ecm">
            <i class="data-icon55-ecm pull-left"></i>
            <br>梯控管理<br>
            <p>电梯参数、梯控授权、计划模板、免刷卡时</p>
          </a>
        </li>   
         </shiro:hasPermission>
        <shiro:hasPermission name="XiaoFeiManage"> 
        <li>
           <a href="onecard.do?menu_id=ccm">
            <i class="data-icon55-ccm pull-left"></i>
            <br>消费管理<br>
            <p>商户管理、营业时段、消费规则、报表查询</p>
          </a>
        </li>   

		  </shiro:hasPermission>
       
      </ul>
      </div>
     </c:if> 
      <div class="homePageRight">
	<div class="homePageRight1 homePageRight11">
	<div class=" data-container-title homePageRightTitle clearfix">
	<h2>信息中心</h2>
	</div>
      <ul class="homePageRightUl">      	
        <li><a href="#"><br>维德杰瑞常州智能科技有限公司<br><small>20170517 11:00</small><br></a></li>      
	 </ul>
	</div>
	
	<div class="homePageRight1 homePageRight2">
	<div class=" data-container-title homePageRightTitle clearfix">
	<h2>下载中心</h2>
	</div>
      <ul class="homePageRightUl">      	
        <li><a href="#"><br>控件下载<br></a></li>      
		<li><a href="#"><br>数据下载<br></a></li>
		<li><a href="#"><br>报表下载<br></a></li>
	 </ul>
	</div>
</div>
<c:if test="${xinxi}">

       <div class="data-container-piece data-container-one">
            <div class="data-container-title clearfix">
        <h2>信息显示</h2>
        <i></i>
      </div>
      <ul class="homePageNav homePageNav-pills border-bottom-index">    
		<shiro:hasPermission name="XinXiFabuManage"> 
 		<li>
           <a href="info.do?menu_id=idm">
            <i class="data-icon55-info pull-left"></i>
            <br>信息发布<br>
            <p>节目制作、审核管理、发布管理、查询统计</p>
          </a>
        </li> 
         </shiro:hasPermission>
        <shiro:hasPermission name="MeeTingManage"> 
        <li>
           <a href="info.do?menu_id=meeting">
            <i class="data-icon55-mrs pull-left"></i>
            <br>会议预约<br>
            <p>会议预约、会议室管理、报表查询</p>
          </a>
        </li> 
         </shiro:hasPermission>
              
      </ul>
      </div>
      </c:if>
      
      <div class="data-container-piece data-container-one">
	     <div class="data-container-title clearfix">
	     	<h2>基础应用</h2>
	       <i></i>
	     </div>
	     <ul class="homePageNav homePageNav-pills border-bottom-index">  
	         <shiro:hasPermission name="UserManage">   
	             <li>
	               	<a href="basic.do?menu_id=user_manage">
	                   <i class="data-icon55-user pull-left"></i>
	                   <br>用户管理<br>
	                   <p>用户管理、角色管理</p>
	                 </a>
	             </li>  
	         </shiro:hasPermission>   
	     
	        <shiro:hasPermission name="DeviceManage">
	           <li>
	               <a href="basic.do?menu_id=device_manage">
	                 <i class="data-icon55-device pull-left"></i>
	                 <br>设备管理<br>
	                 <p>服务器、设备管理、消息管理</p>
	              </a>
	           </li>    
	       </shiro:hasPermission>
	       
	       <shiro:hasPermission name="SystemSetting">    
	        <li>
	        	<a href="basic.do?menu_id=system_setting">        
		            <i class="data-icon55-baisc pull-left"></i>
		            <br>系统配置<br>
		            <p>系统参数、网域设置、数据库管理</p>
	            </a>
	        </li>  
	       </shiro:hasPermission>  
	           
	        <shiro:hasPermission name="LogSearch">
	       	<li>
	      		   <a href="basic.do?menu_id=log_search">  
		            <i class="data-icon55-logs pull-left"></i>
		            <br>日志查询<br>
		            <p>报警查询、配置日志、控制日志、设备日志</p>
	          	</a>
	       	</li>
	       </shiro:hasPermission>
	 </ul>
	</div>
	
<div class="homePageBottom">Copyright © 2014-2016 维德杰瑞常州智能科技有限公司 . All rights reserved.</div>

</div>
</div>
<%@ include file="common/jsp/common_js.jsp"%> 
<%@ include file="pages/basic/liscense/liscenseDialog.jsp" %>
</body>
</html>
<script src="pages/basic/liscense/toliscense.js"></script>

