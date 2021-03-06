<%@ page contentType="text/html; charset=UTF-8"%>
<div class="modal open" id="exampleModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="exampleModalLabel">消息</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal">
					<input type="hidden" name="bimUserId">
					<div class="form-group">
						<label class="col-lg-3 control-label">消息内容:</label>
						<div class="col-lg-8">
							<div id="messageNotice" class="point-out"
								style="width: 348px; top: -38px; left: 0px; display: none;"></div>
							<input type="text" class="form-control" name="message">
							<input type="hidden" name="pfmTaskMessageId" value="">
						</div>
					</div>
				<div class="form-group">
						<label class="col-lg-3 control-label">开始时间:</label>
						<div class="col-lg-8">
							<div id="beginTimeNotice" class="point-out"
								style="width: 348px; top: -38px; left: 0px; display: none;"></div>
							<input type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',maxDate:'#F{$dp.$D(\'endtime\',{m:-1})}'})"   style="background-color:#fff;"  class="form-control mydatepicker" name="beginTime">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">结束时间:</label>
						<div class="col-lg-8">
							<div id="endTimeNotice" class="point-out"
								style="width: 348px; top: -38px; left: 0px; display: none;"></div>
							<input type="text"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'begintime\',{m:1})}'})"   style="background-color:#fff;" class="form-control mydatepicker" name="endTime">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">显示屏:</label>
						<div class="col-lg-8">
							<div id="deviceScreenNotice" class="point-out"
								style="width: 348px; top: -38px; left: 0px; display: none;"></div>
							<select type="text" class="form-control" name="deviceScreen"></select>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary">保存</button>
			</div>
		</div>
	</div>
</div>