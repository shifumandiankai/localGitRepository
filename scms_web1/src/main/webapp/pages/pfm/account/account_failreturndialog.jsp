<%@ page contentType="text/html; charset=UTF-8"%>
<!-- <style>

</style> -->
<div class="modal open" id="failreturnModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" >
  <div class="modal-dialog" role="document" style="width:1000px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">因错退款</h4>
      </div>
      <div class="modal-body">
      
 <form class="form-horizontal">
   <div class="row">
   <div class="col-md-3 right"><label>开始时间</label></div>
   <div class="col-md-3 right"><input type="text"  name="beginTime" class="form-control pull-right"  ></div>
   <div class="col-md-3 right"><label >结束时间</label></div>
   <div class="col-md-3 right"><input type="text"  name="beginTime" class="form-control pull-right"  ></div>
   </div>
   <div class="row">
   <div class="col-md-3 right"><input type="button" value="查询" class="btn btn-default"></div>
   <div class="col-md-3 right"><input type="button" value="重置" class="btn btn-default"></div>
   </div>
  </form>
        
          <!-- <ul class="nav nav-tabs" id="myTab">
	      <li class=""><a href="#consume" name="consume" data-toggle="tab">消费扣款记录</a></li>
	      <li class=""><a href="#return" name="return" data-toggle="tab">退款记录</a></li>
	      <li class="active"><a href="#recharge" name="recharge" data-toggle="tab" >充值记录</a></li>
	      <li class=""><a href="javascript:void(0);" name="other">其它记录</a></li>
          </ul>
          <div class="tab-context"> 
          <div class="tab-pane in active" id="consume">
          的回复回复回复哈哈
				<div class="box">

					<div class="box-body">
						<table id="example1" class="table table-bordered table-striped">
							<thead>
								<tr>	
								("pfmBoothId","boothCode","boothName","phone","parkName","timeName","consumeConfirmresult","centerChargeresult");			
									<th>岗亭ID</th>
									<th>岗亭编号</th>
									<th>岗亭名称</th>
									<th>联系电话</th>
									<th>所属停车场</th>
									<th>时段名称</th>
									<th>启用功能</th>
									<th>中央收费岗亭</th>
									<th>启用功能结果</th>
									<th>中央收费岗亭结果</th>
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
          <div class="tab-pane " id="return">
          更符合规范和 
						<table id="returnexample" class="table table-bordered table-striped">
							<thead>
								<tr>	
									
									<th>序号</th>
									<th>人员姓名</th>
									<th>账户卡号</th>
									<th>退款前金额</th>
									<th>退款金额</th>
									<th>退款后金额</th>
									<th>退款时间</th>
									<th>操作员</th>
								</tr>
							</thead>
							<tbody>						
							</tbody>
							<tfoot>						
							</tfoot>
						</table>
					
			
          </div>
          <div class="tab-pane " id="recharge">
         法规和共和国哈哈
						<table id="rechargeexample" class="table table-bordered table-striped">
							<thead>
								<tr>	
								("pfmFeeRecId","personName","boothName","phone","parkName","timeName","consumeConfirmresult","centerChargeresult");			
									<th>序号</th>
									<th>人员姓名</th>
									<th>账户卡号</th>
									<th>充值前金额</th>
									<th>充值金额</th>
									<th>充值后金额</th>
									<th>充值时间</th>
									<th>操作员</th>
									
									<th>启用功能结果</th>
									<th>中央收费岗亭结果</th>
								</tr>
							</thead>
							<tbody>						
							</tbody>
							<tfoot>						
							</tfoot>
						</table>
					
				
          </div>
          </div>-->
         <ul id="myTab" class="nav nav-tabs">
	<li class="active">
		<a href="#recharge" data-toggle="tab">
			充值记录
		</a>
	</li>
	<li><a href="#leave" data-toggle="tab">退款记录</a></li>
	
</ul>
<div id="myTabContent" class="tab-content">
	<div class="tab-pane fade in active" id="recharge">
		<table id="rechargeexample" class="table table-bordered table-striped">
							<thead>
								<tr>	
								
									<th>序号</th>
									<th>人员姓名</th>
									<th>账户卡号</th>
									<th>充值前金额</th>
									<th>充值金额</th>
									<th>充值后金额</th>
									<th>充值时间</th>
									<th>操作员</th>
									
									
								</tr>
							</thead>
							<tbody>						
							</tbody>
							<tfoot>						
							</tfoot>
						</table>
	</div>
	<div class="tab-pane fade" id="leave">
		<table id="leaveexample" class="table table-bordered table-striped">
							<thead>
								<tr>	
								
									<th>序号</th>
									<th>人员姓名</th>
									<th>账户卡号</th>
									<th>退款前金额</th>
									<th>退款金额</th>
									<th>退款后金额</th>
									<th>退款时间</th>
									<th>操作员</th>
									
									
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
    
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal" id="off">
                    	关闭
                    </button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal" id="yes">
                        确定
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>