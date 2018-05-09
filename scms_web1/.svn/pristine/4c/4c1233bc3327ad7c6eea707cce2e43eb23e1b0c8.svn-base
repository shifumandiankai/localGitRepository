<%@ page contentType="text/html; charset=UTF-8"%>
<div class="modal open" id="uploadModal1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" >
  <div class="modal-dialog" role="document">
    <div class="modal-content" >
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">导入</h4>
      </div>

			<div class="modal-body">
				<form class="form-horizontal" role="form" id="form1"
					enctype="multipart/form-data" method="post"
					action="/personnel/upload.do">
					<div class="form-group">
						<label class="col-sm-4 control-label">部门类型</label>
						<div class="col-sm-8">
							<input type="radio" value="0" name="lx" checked="checked"/>部门
							<input type="radio" value="1" name="lx"/>人员
						</div>

					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">浏览</label>
						<div class="col-sm-8">
							<input name="file" id="file" type="file"
								accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel" />

						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-4 control-label"><a href="#" id="mb">下载部门模版</a></label>

					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" id="sc">上传文件</button>
			</div>

		</div>
	</div>
</div>
<div class="modal open" id="progressModal" tabindex="-1" role="dialog" >
 
	<div id="progress" style="margin-top:300px;"></div>
	<div style="text-align:center;margin-top:30px;color:blue;">导入中，请稍后。。。。</div>
</div>