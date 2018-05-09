<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<style>
/* label{
font-size:12px;
}
.form-control {
font-size:12px !important;
} */

</style>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>ASSAS综合管理平台</title>
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<%@ include file="/common/jsp/common_css.jsp"%>
<link rel="stylesheet" href="/plugins/datatables/dataTables.bootstrap.css">
<!-- <link rel="stylesheet" href="/pages/pfm/carmanage/css/jquery.shCircleLoader.css"> -->
<link rel="stylesheet" type="text/css" href="/pages/pfm/carmanage/css/car_manage.css" >
</head>
<%@ include file="/common/jsp/commonBody.jsp"%>
<!-- <body class="hold-transition skin-blue sidebar-mini"> -->
	<div class="wrapper">
		<%@ include file="/common/jsp/top.jsp"%>
		<%@ include file="/pages/pfm/pfm_menu.jsp"%>
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					车场报表<small>控制面板</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i>主页</a></li>
					<li class="active">报表</li>
				</ol>
			</section>
			<!-- Main content -->
			<section class="content">
			<div class="box">
				<div class="box-body">
					<div>
						<div id="left">
							<!-- <div data-name="search-form-wrap" class="search-form-wrap"><div class="tt-new-filter-search"><div class="tu-input-default" data-name="search-body">
                            <label class="placeholder" for="1493863558333" data-name="placeholder">请输入关键字</label>
                            <input type="text" id="1493863558333" data-name="input">
                            <div class="right-action tu-clearfix">
                            <span class="tu-icon-close cancel-btn" data-name="cancel-btn" style="display: none;"></span>
                            <span class="tu-icon-search" data-name="search-btn"></span>
                            </div>
                            </div></div></div> -->
							<div><ul id="tree" class="ztree" style="margin-left:10px;"></ul></div>
						</div>

						<div id="right">
						
            <div>
            <form class="form-horizontal" id="form1">
             <input type="hidden" name ="parkId"> 
             <input type="hidden" name ="entranceId">
             <input type="hidden" name ="carriagewayId">
            <div class="form-group">
            <div class="col-sm-8" style="padding-left:0px;">
            <div class="col-sm-2"><label>开始时间</label></div>
            <div class="col-sm-4">
            <input type="text" class="form-control"  name="startTime">
            </div>
            <div class="col-sm-2"><label  class=" control-label">结束时间</label></div>
            <div class="col-sm-4">
            <input type="text" class="form-control"   name="endTime">
            </div>
            </div>
             <div class="col-sm-4"> 
            <div class="col-sm-6"><input type="button" class="form-control btn  btn-default" value="查询" name="select"></div>
            <div class="col-sm-6"><input type="button" class="form-control btn  btn-default" value="导出" id="export"></div>
            </div>
            <!-- <div  class="col-sm-5" style="padding-left:0px;">
           <label for="endTime" class="col-sm-3 control-label">车牌号码</label>
            <div class="col-sm-5">
            <input type="text" class="form-control" name="carNumber">
            </div> 
            <div class="col-sm-4">
            <input type="button" class="form-control btn  btn-default" value="查询" name="select">
            </div>
            <div class="col-sm-3">
            <input type="button" class="form-control btn  btn-default" value="导出" id="export">
            </div> 
            </div> -->
            </div>
            
            <div class="form-group">
            <div class="col-sm-8" style="padding-left:0px;">
            <div class="col-sm-2"><label  class=" control-label">方向</label></div>
           
           <div class="col-sm-4">
           <select style="width:154px;height:34px;" name="carType" class="form-control selectfont">
            <option value="0" >无</option>
           <option value="1" >进</option>
           <option value="2" >出</option>
           </select>
            </div>
            <div class="col-sm-2"><label  class=" control-label">车牌号码</label></div>
            <div class="col-sm-4">
            <input type="text" class="form-control"   name="carNumber">
            </div>
            </div>
           
            </div>
            </form>
            </div>
					
						<table id="normalexample" class="table table-bordered table-striped" >
							<thead>
								<tr>	
								<!-- (carNumber,direction,personName,carTypeName,parkName,entranceName,eventTime,carriagewayName,boothName,deviceName,cardId,userName) -->
									<th>车牌</th>
									<th>进出方向</th>
									<th>车主姓名</th>
									<th>车辆类型</th>
									<th>车场</th>
									<th>出入口</th>
									<th>通行时间</th>
									<th>车道</th>
									<th>岗亭</th>
									<th>设备</th>
									<th>卡号</th>
									<th>操作员</th>
									
								</tr>
							</thead>
							<tbody>						
							</tbody>
							<tfoot>						
							</tfoot>
						</table>
						
					
           
            
         
            
           
           

					</div>
					<!--/. container -->
                   
				</div>
				 
			</div>
			<!-- /.box -->
			
			</section>
			
		</div>
		<!-- /.content -->
		<%@ include file="/common/jsp/footer.jsp"%>
		<%@ include file="/common/jsp/sidebar.jsp"%>
	</div>

	
	<%-- <%@ include file="importDialog.jsp"%> --%>
	

	<%@ include file="/common/jsp/common_js.jsp"%>
	 <script src="report.js" type="text/javascript"></script>

</body>
</html>