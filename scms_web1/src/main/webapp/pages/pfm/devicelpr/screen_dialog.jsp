<%@ page contentType="text/html; charset=UTF-8"%>
<div class="modal open" id="screenModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">显示屏设备信息</h4>
      </div>
      <div class="modal-body">
      
        <form class="form-horizontal"> 
         <input type="hidden" name ="pfmDeviceScreenId">
         
          <div class="form-group">
            <label for="recipient-name" class="control-label col-lg-3">设备编号*</label>
            <div id="deviceCode" class="col-lg-9">
           <div id ="deviceCodeNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
            <input type="text" class="form-control" id="recipient-name"  name ="deviceCode">
            </div>
          </div>
          
          <div class="form-group">
            <label for="recipient-name" class="control-label col-lg-3">设备名称*</label>
            <div  class="col-lg-9">
             <div id ="deviceNameNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;" ></div>
            <input type="text" class="form-control" id="recipient-name"  name ="deviceName">
            </div>
          </div>
          <!-- <div class="form-group">
         
            <label for="recipient-name" class="control-label col-lg-3">ip地址</label>
            <div class="col-lg-9">
             <div id ="ipNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;" ></div>
            <input type="text" class="form-control" id="recipient-name"  name ="ip">
            </div>
          </div>
           <div class="form-group">
           <label for="message-text" class="control-label col-lg-3" >端口</label> 
           	<div class="col-lg-9">
             <div id ="portNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;" ></div>
            <input type="text" class="form-control" id="recipient-name"  name ="port">
            </div> 
         </div> -->  
          <!-- <div class="form-group">
          <label for="message-text" class="control-label col-lg-3">登录用户名</label>
           	<div class="col-lg-9">
             <div id ="loginIdNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;" ></div>
            <input type="text" class="form-control" id="recipient-name"  name ="loginId">
            </div>
          </div> -->
          <div class="form-group">
          <label for="message-text" class="control-label col-lg-3">选择设备</label>
           	<div class="col-lg-9">
           	<!-- <select  class="form-control"  name="listarr" > -->
           	<select  class="form-control"  name="listarr" >
			</select>
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="submit">保存</button>
      </div>
    </div>
  </div>
</div> 