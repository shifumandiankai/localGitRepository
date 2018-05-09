<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link rel="stylesheet" type="text/css" href="/pages/pfm/carmanage/css/car_manage.css" >
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>出入监控界面</h1>
			<ol class="breadcrumb">
				<li><a href="/index.do"><i class="fa fa-dashboard"></i>主页</a></li>
				<li class="active">出入监控</li>
			</ol>
		</section>
		<section class="content">
			<div class="box">
				<div class="box-body">
					<div class="col-xs-6">
						 <div class="box box-info" >
						 	<div class="box-header with-border">
						 		<h3 class="box-title">入场</h3>
								<div class="box-tools pull-right">
					                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
					                <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
              					</div>
						 	</div>
							<div id="rukou" class="box-body">
								<div id="rukouimgdiv" class="col-xs-7">
									<img id="inPhoto" class="img-responsive" alt="进场车辆拍照" src="/photo/car_default.png" style="height:300px"> </img>
								
								</div>
								<div class="col-xs-5">
									<label>事件信息：</label><label id="inEventInfo">无</label><br>
									<label>车牌号码：</label><label id="inCarNum">无</label> <br> 
									<label>车辆类型：</label><label id="inCarType">无 </label><br>
									<label>入场时间：</label><label id="inTime">无 </label><br> 
									<label>入场岗亭：</label><label id="inBooth">无 </label><br>
									<label>出入口：</label><label id="inEntrance">无</label><br> 
									<label>操作员：</label><label id="inOperator">无</label><br>
								</div>
							</div>
						</div>	
					</div>
					<div class="col-xs-6">
					 <div class="box box-info"> 
						 <div class="box-header with-border">
						 		<h3 class="box-title">出场</h3>
								<div class="box-tools pull-right">
					                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
					                <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
              					</div>
						 	</div>
							<div id="chukou" class="box-body">
								<div id="chukouimgdiv" class="col-xs-7">
									<img id="outPhoto" alt="出场车辆拍照" class="img-responsive" src="/photo/car_default.png" style="height:300px"/> 				
								</div>
								
								<div class="col-xs-5">
									<label>事件信息：</label><label id="outEventInfo">无</label><br>
									<label>车牌号码：</label><label  id="outCarNum">无</label> <br> 
									<label>收费金额：</label><label id="chargeFee">无 </label><br> 
									<label>车辆类型：</label><label id="outCarType">无 </label><br> 
									<label>出场时间：</label><label id="outTime">无</label> <br> 
									<label>入场时间：</label><label id="outInTime">无 </label><br> 
									<label>停车时长：</label><label id="tingcheshichang">无 </label><br>
									<label>出场岗亭：</label><label id="outBooth">无 </label><br> 
									<label>出入口：</label><label id="outEntrance">无</label><br> 
									<label>操作员：</label><label id="outOperator">无</label><br>
								</div>
							</div>
						 </div> 
					</div>
				
					<div class="col-xs-12">
						<div class="box box-info">
				            <div class="box-header with-border">
				              <h3 class="box-title">最近10条通行记录</h3>
				              <div class="box-tools pull-right">
				                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
				                </button>
				                <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
				              </div>
				            </div>
				            <!-- /.box-header -->
				            <div class="box-body">
				              <div class="table-responsive">
				                <table class="table table-hover" id="tongxingitems">
				                  <thead>
				                  <tr>
               				        <th>事件信息</th>
				                    <th>车牌号码</th>
				                    <th>收费金额</th>
         				            <th>车辆类型</th>
				                    <th>通行时间</th>
				                    <th>入场时间</th>
				                    <th>停车时长</th>
				                    <th>岗亭</th>
               				        <th>出入口</th>
				                    <th>操作员</th>
				                  </tr>
				                  </thead>
				                  <tbody>
				                  </tbody>
				                </table>
				              </div>
				              <!-- /.table-responsive -->
				            </div>
		          		</div>
					</div>
	
				</div>
			</div>
		</section>
	</div>
<script src="/pages/pfm/jiankong/amq_jquery_adapter.js" type="text/javascript"></script>
<script src="/pages/pfm/jiankong/amq.js" type="text/javascript"></script>
<script src="/pages/pfm/jiankong/jiankong.js?<%=System.currentTimeMillis() %>" type="text/javascript"></script>
