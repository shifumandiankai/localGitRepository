<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!-- <style>
th{
width: 110px;
padding-right:10px;
}

 #chargerecordexample_wrapper .col-sm-12{
overflow:auto;
} 
#chargerecordexample_wrapper div:nth-child(1){
padding-left:10px;
}

#chargerecordexample_wrapper div:nth-child(3){
padding-left:10px;
}

.form-control[readonly]{
background-color: rgba(255, 255, 255, 0)!important;
}

.select2-selection{
width:247px!important;
}


/* div.col-sm-2{
display:none!important;
} */
</style> -->
<style>
.select2-selection{
width:275px!important;
}
</style>




<link href="/pages/pfm/report/Czreport/jquery-ui-timepicker-addon.css" rel="stylesheet" type="text/css" />
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					统计报表界面
				</h1>
				<ol class="breadcrumb">
					<li><a href="/index.do"><i class="fa fa-dashboard"></i> 主页</a></li>
					<li class="active">统计报表</li>
				</ol>
			</section>
			<!-- Main content -->
			
			<section class="content">
			<div class="box">
			<div class="box-body"> 
			<div class="nav-tabs-custom">
			<ul class="nav nav-tabs">
			<li id="li1"><a href="#day" data-toggle="tab" id="a1">充值日报表</a></li>
			<li id="li2"><a href="#month" data-toggle="tab" id="a2">充值月报表</a></li>
			<li id="li3"><a href="#year" data-toggle="tab" id="a3">充值年报表</a></li>
			<li id="li4"><a href="#chargeday" data-toggle="tab" id="a1">收费日报表</a></li>
			<li id="li5"><a href="#chargemonth" data-toggle="tab" id="a2">收费月报表</a></li>
			<li id="li6"><a href="#chargeyear" data-toggle="tab" id="a3">收费年报表</a></li>
			</ul>						
			</div> 
            <div class="tab-content">
            
         
            <div class="tab-pane fade in active" id="day">
            
             <form class="form-horizontal" id="form1">
              <input type="hidden" name ="order1">
               <input type="hidden" name ="order2">
                <input type="hidden" name="selectclick">
            <div class="form-group">
            <div class="col-sm-10" style="padding-left:0px;">
            <div class="col-sm-2"><label>开始时间</label></div>
            <div class="col-sm-4">
            <div id ="startTimeNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
            <input type="text" class="form-control"  name="startTime" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'2099-12-31'})">
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
            <div class="col-sm-2"><label  class=" control-label">车牌号码</label></div>
            <div class="col-sm-4">
            <div id ="carNumberNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
            <input type="text" class="form-control"   name="carNumber">
            </div>
            
          
           <div class="col-sm-2" ><label  class="control-label">车辆类型</label></div>
             <div class="col-sm-4">
            <select class="form-control"   name="carType"></select>
            </div>  
            
            </div>
            </div>
            </form>
            
					
						<table id="dayexample" class="table table-bordered table-striped" >
							<thead>
								<tr>	
									
									
									<th>时间</th>
									<th>车辆类型</th>
									<th>记录数量</th>
									<th>总计金额</th>
								</tr>
							</thead>
							<tbody>						
							</tbody>
							<tfoot>						
							</tfoot>
						</table>
							
			
            </div>
            
            	<div class="tab-pane fade " id="month">
           <form class="form-horizontal" id="form2">
             <input type="hidden" name ="order1">
               <input type="hidden" name ="order2">
                <input type="hidden" name="selectclick">
            <div class="form-group">
            <div class="col-sm-10" style="padding-left:0px;">
            <div class="col-sm-2"><label>开始时间</label></div>
            <div class="col-sm-4">
            <div id ="startTimeNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
            <input type="text" class="form-control"  name="startTime" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'2099-12-31'})">
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
            <div class="col-sm-2"><label  class=" control-label">车牌号码</label></div>
            <div class="col-sm-4">
            <div id ="carNumberNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
            <input type="text" class="form-control"   name="carNumber">
            </div>
            
          
           <div class="col-sm-2" ><label  class="control-label">车辆类型</label></div>
            <div class="col-sm-4">
            <select class="form-control"   name="carType"></select>
            </div> 
            
            </div>
            </div>
            </form>
            
						<table id="monthexample" class="table table-bordered table-striped" >
							<thead>
								<tr>	
								<!-- (carNumber,direction,personName,carTypeName,parkName,entranceName,eventTime,carriagewayName,boothName,deviceName,cardId,userName) -->
									
									
									<th>时间</th>
									<th>车辆类型</th>
									<th>记录数量</th>
									<th>总计金额</th>
									
								</tr>
							</thead>
							<tbody>						
							</tbody>
							<tfoot>						
							</tfoot>
						</table>
					
					
            </div>
            
            <div class="tab-pane fade " id="year">
             <form class="form-horizontal" id="form3">
            <input type="hidden" name ="order1">
               <input type="hidden" name ="order2">
                <input type="hidden" name="selectclick">
            <div class="form-group">
            <div class="col-sm-10" style="padding-left:0px;">
            <div class="col-sm-2"><label>开始时间</label></div>
            <div class="col-sm-4">
            <div id ="startTimeNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
            <input type="text" class="form-control"  name="startTime" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'2099-12-31'})">
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
            <div class="col-sm-2"><label  class=" control-label">车牌号码</label></div>
            <div class="col-sm-4">
            <div id ="carNumberNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
            <input type="text" class="form-control"   name="carNumber">
            </div>
            
          
           <div class="col-sm-2" ><label  class="control-label">车辆类型</label></div>
            <div class="col-sm-4">
            <select class="form-control"   name="carType"></select>
            </div> 
            
            </div>
            </div>
            </form>
            
						<table id="yearexample" class="table table-bordered table-striped" >
							<thead>
								<tr>	
								
								    <th>时间</th>
									<th>车辆类型</th>
									<th>记录数量</th>
									<th>总计金额</th>
									
								</tr>
							</thead>
							<tbody>						
							</tbody>
							<tfoot>						
							</tfoot>
						</table>
					
            </div>
            
            <div class="tab-pane fade " id="chargeday">
            
             <form class="form-horizontal" id="form4">
              <input type="hidden" name ="order1">
               <input type="hidden" name ="order2">
                <input type="hidden" name="selectclick">
            <div class="form-group">
            <div class="col-sm-10" style="padding-left:0px;">
            <div class="col-sm-2"><label>开始时间</label></div>
            <div class="col-sm-4">
            <div id ="startTimeNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
            <input type="text" class="form-control"  name="startTime" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'2099-12-31'})">
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
            <div class="col-sm-2"><label  class=" control-label">车牌号码</label></div>
            <div class="col-sm-4">
            <div id ="carNumberNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
            <input type="text" class="form-control"   name="carNumber">
            </div>
            
          
           <div class="col-sm-2" ><label  class="control-label">车辆类型</label></div>
            <div class="col-sm-4">
            <select class="form-control"   name="carType"></select>
            </div> 
            
            </div>
            </div>
            </form>
            
            <table id="chargedayexample" class="table table-bordered table-striped" >
							<thead>
								<tr>	
								
								<th>时间</th>
									<th>车辆类型</th>
									<th>记录数量</th>
									<th>总计金额</th>
									
								</tr>
							</thead>
							<tbody>						
							</tbody>
							<tfoot>						
							</tfoot>
						</table>
            </div>
            <div class="tab-pane fade " id="chargemonth">
            
             <form class="form-horizontal" id="form5">
              <input type="hidden" name ="order1">
               <input type="hidden" name ="order2">
                <input type="hidden" name="selectclick">
            <div class="form-group">
            <div class="col-sm-10" style="padding-left:0px;">
            <div class="col-sm-2"><label>开始时间</label></div>
            <div class="col-sm-4">
            <div id ="startTimeNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
            <input type="text" class="form-control"  name="startTime" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'2099-12-31'})">
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
            <div class="col-sm-2"><label  class=" control-label">车牌号码</label></div>
            <div class="col-sm-4">
            <div id ="carNumberNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
            <input type="text" class="form-control"   name="carNumber">
            </div>
            
          
           <div class="col-sm-2" ><label  class="control-label">车辆类型</label></div>
            <div class="col-sm-4">
            <select class="form-control"   name="carType"></select>
            </div> 
            
            </div>
            </div>
            </form>
            <table id="chargemonthexample" class="table table-bordered table-striped" >
							<thead>
								<tr>	
								    
								    <th>时间</th>
									<th>车辆类型</th>
									<th>记录数量</th>
									<th>总计金额</th>
									
								</tr>
							</thead>
							<tbody>						
							</tbody>
							<tfoot>						
							</tfoot>
						</table>
            </div>
            <div class="tab-pane fade " id="chargeyear">
             <form class="form-horizontal" id="form6">
              <input type="hidden" name ="order1">
               <input type="hidden" name ="order2">
                <input type="hidden" name="selectclick">
            <div class="form-group">
            <div class="col-sm-10" style="padding-left:0px;">
            <div class="col-sm-2"><label>开始时间</label></div>
            <div class="col-sm-4">
            <div id ="startTimeNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
            <input type="text" class="form-control"  name="startTime" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'2099-12-31'})">
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
            <div class="col-sm-2"><label  class=" control-label">车牌号码</label></div>
            <div class="col-sm-4">
            <div id ="carNumberNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
            <input type="text" class="form-control"   name="carNumber">
            </div>
            
          
           <div class="col-sm-2" ><label  class="control-label">车辆类型</label></div>
            <div class="col-sm-4">
            <select class="form-control"   name="carType"></select>
            </div> 
            
            </div>
            </div>
            </form>
            <table id="chargeyearexample" class="table table-bordered table-striped" >
							<thead>
								<tr>	
								   
								    <th>时间</th>
									<th>车辆类型</th>
									<th>记录数量</th>
									<th>总计金额</th>
									
								</tr>
							</thead>
							<tbody>						
							</tbody>
							<tfoot>						
							</tfoot>
						</table>
            </div>
            </div>
            </div>
           <!--  </div> -->
			</div> 
						</section>
			</div>
	<%@ include file="/common/jsp/footer.jsp"%>
	<%@ include file="/common/jsp/sidebar.jsp"%>
	<%-- <%@ include file="/pages/pfm/report/basic_js.jsp"%> --%>
	
	<script src="/pages/pfm/report/Czreport/jquery-ui-timepicker-addon.js" type="text/javascript"></script> 
	  
    <script src="/pages/pfm/report/Czreport/jquery-ui-timepicker-zh-CN.js" type="text/javascript"></script>  
        
    <script src="/pages/pfm/report/Czreport/czreport.js"></script> 
    
	<script type="text/javascript">
	
		function Buttonpermission(){
			<shiro:lacksPermission name="ParkingAdd">$("#example1_wrapper a[name='wade.libs.datatable_add']").remove();</shiro:lacksPermission>
			<shiro:lacksPermission name="ParkingDelete">$("#example1_wrapper a[name='wade.libs.datatable_del']").remove();</shiro:lacksPermission>
			<shiro:lacksPermission name="ParkingEdit">$("#example1_wrapper a[name='wade.libs.datatable_edit']").remove();</shiro:lacksPermission>
			
		}
		function Seepermission(){
			<shiro:lacksPermission name="ParkingSee">$("#example1 tbody td").text("没有浏览权限");</shiro:lacksPermission>
		
		}
        function drawInit(){
        	$("#dayexample tbody td").text("请点击查询获取数据");
        	$("#monthexample tbody td").text("请点击查询获取数据");
			$("#yearexample tbody td").text("请点击查询获取数据");
			$("#chargedayexample tbody td").text("请点击查询获取数据");
			$("#chargemonthexample tbody td").text("请点击查询获取数据");
			$("#chargeyearexample tbody td").text("请点击查询获取数据");
		}
	
	</script> 
	
	<script src="/plugins/My97DatePicker/WdatePicker.js"></script>