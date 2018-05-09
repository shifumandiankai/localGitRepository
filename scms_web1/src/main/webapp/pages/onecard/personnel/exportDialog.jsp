<%@ page contentType="text/html; charset=UTF-8"%>
<div class="modal open" id="downloadModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" >
  <div class="modal-dialog" role="document">
    <div class="modal-content" >
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">导出</h4>
      </div>
    
			<div class="modal-body" >
				  <form class="form-horizontal"   id="form1" action="/personnel/upload.do" method="post">
                     <div class="form-group">
                         <label  class="col-sm-4 control-label">导出类型</label>
                       	 <div  class="col-sm-8">
               		   <input type="radio" value="0" name="LX" checked="true">部门
               		   <input type="radio" value="1" name="LX">人员 
               		 </div>
                         
                   	 </div>
                    
                    
                    
                    
                    
                   	
               		  
   	             </form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button"  class="btn btn-primary" id="submit">导出文件</button>
			</div>
			
		</div>
	</div>
</div>