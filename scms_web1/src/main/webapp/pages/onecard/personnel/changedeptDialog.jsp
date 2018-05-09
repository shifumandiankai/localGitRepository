<%@ page contentType="text/html; charset=UTF-8"%>
<div class="modal open" id="changedept" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" >
  <div class="modal-dialog" role="document">
    <div class="modal-content" >
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">将选定人员移动至部门</h4>
      </div>
			<div class="modal-body" >
				<form class="form-horizontal" role="form" >
                    
                        
                
                       <div class="form-group ">
                         
                          
                         <label class="col-sm-4 control-label" >请选择部门</label>
                          <div class="col-sm-8">
                            <!--   <select name="carTypeId" id="cartypeselect" class="form-control"></select>-->
            
                           
                             <ul id="changetree" class="ztree"></ul>
                          </div>
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