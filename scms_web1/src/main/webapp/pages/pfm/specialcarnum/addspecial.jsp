<%@ page contentType="text/html; charset=UTF-8"%>
<div class="modal open" id="configspecialNumberModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel">
	<div class="modal-dialog" role="document" style="width: 700px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="exampleModalLabel">特殊车牌</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal">
					<input type="hidden" name="pfmSpecialCarId">
					<div class="form-group">
						<label class="col-lg-3 control-label">特殊车牌*</label>
						<div class="col-lg-8">
							<div id="carNumberNotice" class="point-out"
								style="width: 348px; top: -38px; left: 0px; display: none;"></div>
								<input type="text" style="display:none;" >
							<input type="text" class="form-control" name="carNumber">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">备注</label>
						<div class="col-lg-8">
							<div id="noteNotice" class="point-out"
								style="width: 348px; top: -38px; left: 0px; display: none;"></div>
							<textarea class="form-control" name="note"></textarea>
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