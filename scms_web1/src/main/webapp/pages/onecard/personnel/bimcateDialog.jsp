<%@ page contentType="text/html; charset=UTF-8"%>
<style>
.update,.delete{
cursor:pointer;
}
</style>
<div class="modal open" id="bimcateModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content" style=" width: 750px;"><!--  -->
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">身份管理</h4>
      </div>
      <div class="modal-body">
      
        <form class="form-horizontal"> 
        <input type="hidden" name="personId">
         
      
          <div class="form-group ">
          
          <label  class="col-sm-3 control-label" style="width: 72px;padding: 2px 1px;margin-right: 560px;margin-left: 10px;">身份信息</label>
          <a class="btn  btn-default fingerbtn" tabindex="-1" href="javascript:;" style="margin-right: 20px;" id="add">添加</a>
          <!-- <div class="col-sm-8"></div>
          <div class="col-sm-1">
           <a class="btn  btn-default fingerbtn" tabindex="-1" href="javascript:;" style="margin-right: 20px;" id="add">添加</a>
		   
		  </div> -->
         
		   
          </div>
         
         <div class="form-group">
          
          <div   style="width:720px; height:300px; overflow-y:auto;padding-left: 20px;float:left;" >
                 <table id="cardtable" class="table table-bordered table-striped " width="300px" height="200px" >
							<tbody>
							<thead >
								<tr id="tr1" role="row" >	
									<th>序号</th>
									<th >身份类型</th>
									<th >备注</th>
									<th >操作类型</th>
									<th  style="display:none;"></th>
									 
								</tr>
								
							</thead>
							 
							</tbody>
						</table>
                 </div> 
          
          
          
          </div>
          
          
          
        </form> 
        
        
      </div>
      <div class="modal-footer">
     <!--  <button type="button" class="btn btn-primary" id="submit">提交</button> -->
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
       
      </div>
    </div>
  </div>
</div> 