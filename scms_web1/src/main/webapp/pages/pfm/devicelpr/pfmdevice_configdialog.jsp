<%@ page contentType="text/html; charset=UTF-8"%>
<div class="modal open" id="configModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">车场信息</h4>
      </div>
      <div class="modal-body">
      
        <form class="form-horizontal"> 
         
         
         <div class="row">
            <div class="col-md-6 right"><label class="control-label">请选择要配置的服务器</label></div>
            <div class="col-md-6">
           
            <select class="form-control"  id="congfigsel"></select>
            </div>
          </div>
          
         
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="submit">保存配置</button>
      </div>
    </div>
  </div>
</div> 