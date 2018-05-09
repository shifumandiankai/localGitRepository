<%@ page contentType="text/html; charset=UTF-8"%>
<div class="modal open" id="uploadModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" >
  <div class="modal-dialog" role="document">
    <div class="modal-content" >
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">批量导入</h4>
      </div>
			<div class="modal-body" >
				<form class="form-horizontal" role="form"    method="post" action="/pfm/carmanage/upload.do">
                    <div class="form-group">
                    <label  class="col-sm-4 control-label">浏览</label>
                    <div  class="col-sm-8">
               		  <input  name="file" id="file" type="file" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel"/>
               		
               		 </div>
               		 </div>
               		 <div class="form-group">
                         <label  class="col-sm-4 control-label">提示</label>
                       	 <p class="col-sm-8" style="margin:0px;padding-top:7px;"> 请选择上传xls或xlsx格式Excel文件。</p>
                         
                   	 </div>
                   	 <div id="progress"></div>
               		  
   	             </form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button"  class="btn btn-primary">上传文件</button>
			</div>
		</div>
	</div>
</div>
<div class="modal open" id="progressModal" tabindex="-1" role="dialog" >
 
	<div id="progress" style="margin-top:300px;"></div>
	<div style="text-align:center;margin-top:30px;color:blue;">导入中，请稍后。。。。</div>
</div>