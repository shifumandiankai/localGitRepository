<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
 <style>
/* .form-control[readonly]{
	background-color: rgba(255, 255, 255, 0)!important;
} */
</style> 
<link rel="stylesheet" type="text/css" href="/pages/pfm/carmanage/css/car_manage.css" >

		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					配置日志界面
				</h1>
				<ol class="breadcrumb">
					<li><a href="/index.do"><i class="fa fa-dashboard"></i> 主页</a></li>
					<li class="active">配置日志</li>
				</ol>
			</section>
			
			<section class="content">
			<div class="box">
			<div class="box-body"> 
            
            <div>
            
            <form class="form-horizontal" id="form1">
             <div class="form-group"> 
             <input type="hidden" name ="systemCode">
            <div class="col-sm-10" style="padding-left:0px;">
            <div class="col-sm-2"><label>开始时间</label></div>
            <div class="col-sm-4">
            <div id ="startTimeNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
            <input type="text" class="form-control"  name="startTime" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'2099-12-31'})">
            <!--  <span class="glyphicon glyphicon-calendar "></span> -->
            </div>
            
            <div class="col-sm-2"><label  class=" control-label">结束时间</label></div>
            <div class="col-sm-4">
             <div id ="endTimeNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
            <input type="text" class="form-control"   name="endTime" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'2099-12-31'})">
            </div>
            </div>
            </div>
            <div class="form-group">
            <div class="col-sm-10" style="padding-left:0px;">
            <div class="col-sm-2"><label  class=" control-label">配置类型</label></div>
            <div class="col-sm-4">
            <div id ="carNumberNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
             <select  class="form-control"  name="systemCode"></select>
            </div>
             <div class="col-sm-2"><label  class=" control-label">用户</label></div>
            <div class="col-sm-4">
           <select  class="form-control"  name="user"></select>
            </div>
            </div> 
            
            
            </div>
            
            
            </form>
            

            </div>
            
					
						<table id="configurationlogexample" class="table table-bordered table-striped" >
							<thead>
								<tr>
								    
									<th>编号</th>
									<th>配置类型</th>
									<th>系统模块</th>
									<th>操作类型</th>
									<th>用户</th>
									<th>IP地址</th>
									<th>时间</th>
									<th>内容</th>
									
									
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
	
    <script src="/pages/basic/log/configurationLog.js"></script>  
	<script type="text/javascript">
		function Buttonpermission(){
			<shiro:lacksPermission name="ParkingAdd">$("#example1_wrapper a[name='wade.libs.datatable_add']").remove();</shiro:lacksPermission>
			<shiro:lacksPermission name="ParkingDelete">$("#example1_wrapper a[name='wade.libs.datatable_del']").remove();</shiro:lacksPermission>
			<shiro:lacksPermission name="ParkingEdit">$("#example1_wrapper a[name='wade.libs.datatable_edit']").remove();</shiro:lacksPermission>
			
		}
		function Seepermission(){
			<shiro:lacksPermission name="ParkingSee">$("#example1 tbody td").text("没有浏览权限");</shiro:lacksPermission>
		
		}
	</script> 
	<script src="/plugins/My97DatePicker/WdatePicker.js"></script>
	 