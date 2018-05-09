<%@ page contentType="text/html; charset=UTF-8"%>
<div class="modal open" id="addchargeruleModal" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">规则</h4>
			</div>
			<div class="modal-body">


							<div class="nav-tabs-custom">
								<ul class="nav nav-tabs"  style="border-bottom: 0px;">
									<li class="active"><a href="#chargerule" data-toggle="tab">收费规则</a></li>
									<li><a href="#reducerule" data-toggle="tab">减免规则</a></li>
									<li><a href="#mobilereducerule" data-toggle="tab">移动支付减免规则</a></li>
									<li><a href="#exceptionrule" data-toggle="tab">异常放行规则</a></li>
								</ul>
							</div>
					<form id="chargeForm" class="form-horizontal" role="form">
			    	</form>
			    	<form id="reduceForm" class="form-horizontal" style="display:none" role="form">
			    	</form>
			    	<form id="mobilereduceForm" class="form-horizontal" style="display:none" role="form">
			    	</form>
			    	<form id="exceptionForm" class="form-horizontal" style="display:none" role="form">
			    	</form>

				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button  style="display:none;" type="button" id="dosubmit" class="btn">确认</button>
				<button type="button" id="submit" class="btn btn-primary">确认</button>
			</div>
		</div>
	</div>
</div>
<div class="modal open" id="timeModal" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title"></h4>
			</div>
			<div class="modal-body">


						
								
			</div>
			<div class="modal-footer">
				<button type="button"  class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" id="save" class="btn btn-primary">保存</button>
			</div>
		</div>
	</div>
</div>
