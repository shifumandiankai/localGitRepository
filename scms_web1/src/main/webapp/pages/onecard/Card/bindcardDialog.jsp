<%@ page contentType="text/html; charset=UTF-8"%>
<style>
.fingerprint-bg {
    background: url(/pages/onecard/personnel/hand.png) 0 0 no-repeat;
    width: 400px;
    height: 210px;
}
</style>
<div class="modal open" id="bindcardModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content" style=" width: 750px;"><!--  -->
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">人员开卡</h4>
      </div>
      <div class="modal-body">
      
        <form class="form-horizontal"> 
        
         
      
          <div class="form-group ">
          <input type="hidden" name="personId">
          <div class="col-sm-6">
          <label  class="col-sm-4 control-label">卡号:</label>
			<div class="col-sm-8">
			  <div id ="cardIdNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
				<input type="text" class="form-control" name="cardId" />
				</div>
		  </div>
		   
          </div>
         
         <div class="form-group">
           <div class="col-sm-6">
             <label  class="col-sm-5 control-label">卡片应用范围:</label>
           </div>
                 <div class="col-sm-12">
                 <label class="col-sm-2 control-label"><input type="checkbox" name="subSystem" checked="" value="0">门禁系统</label>
                 <label class="col-sm-2 control-label"><input type="checkbox" name="subSystem" checked="" value="1">梯控系统</label>
                 <label class="col-sm-2 control-label"><input type="checkbox" name="subSystem" checked="" value="2">考勤系统</label>
                 <label class="col-sm-2 control-label"><input type="checkbox" name="subSystem" checked="" value="3">停车场系统</label>
                 <label class="col-sm-2 control-label"><input type="checkbox" name="subSystem" checked="" value="4">消费系统</label>
                 </div>
          
          
          
          
          
          </div>
          
         <!--  <div class="form-group fingerprint-bg" style="margin-left: 60px;">			
          </div>
          
          <div class="form-group fingerprint-tip" style="margin-left: 140px;">
         
			&nbsp;提示：请在指纹采集器上进行指纹数据收集。
	
          </div> -->
          
        </form> 
        
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
       <button type="button" class="btn btn-primary" id="submit">开卡</button>
      </div>
    </div>
  </div>
</div> 