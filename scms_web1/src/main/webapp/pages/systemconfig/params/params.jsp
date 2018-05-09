<%@ page contentType="text/html; charset=UTF-8"%>
<style>
.save {
	margin-bottom: 20px;
}
.controls {
	margin-left: 5px;
	display: inline-block !important;
	ertical-align: top;
}
.control-label {
	width: 130px;
	padding: 2px 1px;
	cursor: default;
	margin-bottom: 0px;
}

.form-horizontal .btn {
	padding: 1px 10px;
	font-size: 12px;
}

.form-horizontal select {
	width: 185px;
	height: 26px;
	line-height: 20px;
}

.form-horizontal select {
	padding: 0 0 1px 2px;
	box-shadow: none;
	border-radius: 0px;
}

.paramTime {
	border-top: 2px solid #289b93;
	margin-right: -1px;
	margin-left: -2px;
	padding: 10px;
}

.aa {
	padding: 10px;
}

.aa:last-child {
	padding-bottom: 20px;
}

.paramButton {
	margin-left: 440px;
	height: 26px;
	width: 72px;
}
</style>
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>系统参数界面</h1>
		<ol class="breadcrumb">
			<li><a href="/index.do"><i class="fa fa-dashboard"></i> 主页</a></li>
			<li class="active">系统参数</li>
		</ol>
	</section>
	<section class="content">
		<div class="box">
			<div class="box-body">
				<form class="form-horizontal" id ="paramsform">
					<div class="row">
						<div class="col-md-12 ">
							<p style="font-weight: bold; font-size: 15px;">设备/服务器校时</p>
							<div class="aa row">
								<label class="control-label">手动校时:</label>
								<div class="controls">
									<button type="button" id="handleTiming" data-loading-text="正在校时...">手动校时</button>
								</div>
							</div>
							<div class=" aa row">
								<label class="control-label">自动校时:</label>
								<div class="controls">
									<select name ="autoSetTimeStyleWeek">
										<option value="0" title="每日">每日</option>
										<option value="2" title="星期一">星期一</option>
										<option value="3" title="星期二">星期二</option>
										<option value="4" title="星期三">星期三</option>
										<option value="5" title="星期四">星期四</option>
										<option value="6" title="星期五">星期五</option>
										<option value="7" title="星期六">星期六</option>
										<option value="1" title="星期日">星期日</option>
									</select> <input type="text" name="autoSetTimeStyle" class="popover-show timepicker"/>
								</div>
							</div>
						</div>
					</div>
					<div class="paramTime"></div>

					<!--yystart,-日志保存月数  -->
					<div class="row">

						<div class="col-md-12">
							<p style="font-weight: bold; font-size: 15px;">日志保存时间</p>
							<div class="aa row">
								<label class="control-label">报警日志:</label>
								<div class="controls">
									<select name="alarmLogPreserveMonths">
										<option value="1" title="1">1</option>
										<option value="3" title="3">3</option>
										<option value="6" title="6">6</option>
										<option value="9" title="9">9</option>
										<option value="12" title="12">12</option>
										<option value="15" title="15">15</option>
										<option value="18" title="18">18</option>
										<option value="21" title="21">21</option>
										<option value="24" title="24">24</option>
									</select>
								</div>
								<span class="add-on">月</span>
							</div>
							<div class="aa row">
								<label class="control-label">配置日志:</label>
								<div class="controls">
									<select name="configLogPreserveMonths">
										<option value="1" title="1">1</option>
										<option value="3" title="3">3</option>
										<option value="6" title="6">6</option>
										<option value="9" title="9">9</option>
										<option value="12" title="12">12</option>
										<option value="15" title="15">15</option>
										<option value="18" title="18">18</option>
										<option value="21" title="21">21</option>
										<option value="24" title="24">24</option>
									</select>
								</div>
								<span class="add-on">月</span>
							</div>
							<div class="aa row">
								<label class="control-label">控制日志:</label>
								<div class="controls">
									<select name="commonLogPreserveMonths">
										<option value="1" title="1">1</option>
										<option value="3" title="3">3</option>
										<option value="6" title="6">6</option>
										<option value="9" title="9">9</option>
										<option value="12" title="12">12</option>
										<option value="15" title="15">15</option>
										<option value="18" title="18">18</option>
										<option value="21" title="21">21</option>
										<option value="24" title="24">24</option>
									</select>
								</div>
								<span class="add-on">月</span>
							</div>
							<div class="aa row">
								<label class="control-label">工作日志:</label>
								<div class="controls">
									<select name="workLogPreserveMonths">
										<option value="1" title="1">1</option>
										<option value="3" title="3">3</option>
										<option value="6" title="6">6</option>
										<option value="9" title="9">9</option>
										<option value="12" title="12">12</option>
										<option value="15" title="15">15</option>
										<option value="18" title="18">18</option>
										<option value="21" title="21">21</option>
										<option value="24" title="24">24</option>
									</select>
								</div>
								<span class="add-on">月</span>
							</div>
							<div class="aa row">
								<label class="control-label">网管日志:</label>
								<div class="controls">
									<select name="monitorLogPreserveMonths">
										<option value="1" title="1">1</option>
										<option value="3" title="3">3</option>
										<option value="6" title="6">6</option>
										<option value="9" title="9">9</option>
										<option value="12" title="12">12</option>
										<option value="15" title="15">15</option>
										<option value="18" title="18">18</option>
										<option value="21" title="21">21</option>
										<option value="24" title="24">24</option>
									</select>
								</div>
								<span class="add-on">月</span>
							</div>
						</div>


					</div>
					<div class="paramTime"></div>
					<div class="row">
						<div class="col-md-12">
							<p style="font-weight: bold; font-size: 15px;">停车场参数</p>
							<div class="aa row">
								<label class="control-label">图片保存时间:</label>
								<div class="controls">
									<select name="pfmPhotoSaveTime">
										<option value="1" title="1">1</option>
										<option value="3" title="3">3</option>
										<option value="6" title="6">6</option>
										<option value="9" title="9">9</option>
										<option value="12" title="12">12</option>
										<option value="15" title="15">15</option>
										<option value="18" title="18">18</option>
										<option value="21" title="21">21</option>
										<option value="24" title="24">24</option>
									</select>
								</div>
								<span class="add-on">月</span>
							</div>
						</div>
					</div>
					<div class="paramTime"></div>
                           <div class="row">
						<div class="col-md-12">
							<p style="font-weight: bold; font-size: 15px;">支付平台参数 </p>
							<div class="aa row">
								<label class="control-label">平台地址 :</label>
								<div id ="scmsPayUrlNotice" class="point-out" style="width: 348px;top:10px; left:120px; display: none;" name="scmsPayUrlNotice"></div>
								<div class="controls">
								
									<input type="text" style="width:181px;" name="scmsPayUrl" placeholder="如 :  http://www.baidu.com">
								</div>
							</div>
							
							<div class="aa row">
								<label class="control-label">支付成功出场时间 :</label>
								<!-- <div id ="SuccessfulPayOffTimeNotice" class="point-out" style="width: 348px;top:10px; left:120px; display: none;position:relative;" name="SuccessfulPayOffTimeNotice"></div> -->
								<div class="controls" style="position:relative">
								<div id ="SuccessfulPayOffTimeNotice" class="point-out" style="width: 348px;top:-35px; left:-15px; display: none;" name="SuccessfulPayOffTime"></div>
									<input type="text" style="width:181px;" name="successfulPayOffTime" ><span class="add-on">分钟</span>
								</div>
								
							</div>
							
						</div>
					</div>
					<div class="paramTime"></div>
					<div class=" save row">
						<input type="button" class="btn btn-primary paramButton"
							id="submit" value="保存">
					</div>
				</form>
			</div>
		</div>
	</section>
</div>
<script src="/pages/systemconfig/params/params.js"></script>