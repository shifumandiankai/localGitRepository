<%@ page contentType="text/html; charset=UTF-8"%>
<div class="modal open" id="swapcardModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content" style=" width: 750px;"><!--  -->
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">人员换卡</h4>
      </div>
      <div class="modal-body">
      
        <form class="form-horizontal"> 
        
         
      
          <div class="form-group ">
          <div class="col-sm-8">
          <label  class="col-sm-6 control-label">旧卡卡号:</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" name="oldcard" disabled="disabled"/>
				</div>
		  </div>
		   
          </div>
         
         <div class="form-group">
          <div class="col-lg-8">
          <label  class="col-sm-6 control-label">*新卡卡号:</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" name="newcard"/>
				</div>
          </div>
          </div>
          

          
          
          
        </form> 
        
        
        
        
        
        
        
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
       <button type="button" class="btn btn-primary" id="submit">换卡</button>
      </div>
    </div>
  </div>
</div> 