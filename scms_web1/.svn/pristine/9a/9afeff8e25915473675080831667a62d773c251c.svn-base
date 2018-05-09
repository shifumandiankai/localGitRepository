<%@ page contentType="text/html; charset=UTF-8"%>
<style>
tr{
border-bottom:1px solid #BBB;
}
</style>
<div class="modal open" id="batchcard" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"> <!-- style="display: none;" -->
  <div class="modal-dialog" role="document">
    <div class="modal-content" style="width: 700px;">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">批量开卡</h4>
      </div>
			<div class="modal-body" >
				 
                
				 <form class="form-horizontal" role="form" >
				 <input type="hidden">
                 <div > 卡片应用范围 </div>
                 <div>
                 <label><input type="checkbox" name="subSystem" checked="" value="0">门禁系统</label>
                 <label><input type="checkbox" name="subSystem" checked="" value="1">梯控系统</label>
                 <label><input type="checkbox" name="subSystem" checked="" value="2">考勤系统</label>
                <!--  <label><input type="checkbox" name="subSystem" checked="" value="3">巡查系统</label> -->
                 <label><input type="checkbox" name="subSystem" checked="" value="3">停车场系统</label>
                 <label><input type="checkbox" name="subSystem" checked="" value="4">消费系统</label>
                 </div>
                 <div>将以下列表中的持卡人开卡:</div>
                 <div  style="width:660px; height:300px; overflow-y:auto;">
                 <table id="batchcardexample" class="table table-bordered table-striped" width="380px" height="200px" >
							<thead >
								<tr id="tr1">	
									<th>序号</th>
									<th>持卡人姓名</th>
									<th>部门</th>
									<th>电话</th>
									<th>卡号</th> 
								</tr>
								
							</thead>
							<tfoot>						
							</tfoot> 
						</table>
                 </div>
                </form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" id="add" class="btn btn-primary">提交更改</button>
			</div>
		</div>
	</div>
</div>
