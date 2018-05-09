<%@ page contentType="text/html; charset=UTF-8"%>
<div class="modal open" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">设备信息</h4>
      </div>
      <div class="modal-body">
      
        <form class="form-horizontal"> 
         <input type="hidden" name ="pfmDeviceLprId">
        <input type="hidden" name ="pfmServerId">
         <div class="form-group">
            <label for="recipient-name" class="control-label col-lg-3">编号*</label>
            <div id="deviceCode" class="col-lg-9">
           <div id ="deviceCodeNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;" name="deviceCodeNotice"></div>
            <input type="text" class="form-control" id="recipient-name"  name ="deviceCode">
            </div>
          </div>
          
          <div class="form-group">
            <label for="recipient-name" class="control-label col-lg-3">设备名称*</label>
            <div id="deviceName" class="col-lg-9">
           <div id ="deviceNameNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;" name="deviceNameNotice"></div>
            <input type="text" class="form-control" id="recipient-name"  name ="deviceName">
            </div>
          </div>
          
          <div class="form-group">
            <label for="recipient-name" class="control-label col-lg-3">IP地址*</label>
            <div id="ip" class="col-lg-9">
             <div id ="ipNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;" name="ipNotice" ></div>
            <input type="text" class="form-control" id="recipient-name"  name ="ip">
            </div>
          </div>
          <div class="form-group">
         
            <label for="recipient-name" class="control-label col-lg-3">端口*</label>
            <div id="port" class="col-lg-9">
             <div id ="portNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;" name="portNotice"></div>
            <input type="text" class="form-control" id="recipient-name"  name ="port" >
            </div>
          </div>
           <!-- <div class="form-group">
         
            <label for="recipient-name" class="control-label col-lg-3">mac</label>
            <div id="mac" class="col-lg-9">
             <div id ="macNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;" name="macNotice"></div>
            <input type="text" class="form-control" id="recipient-name"  name ="mac">
            </div>
          </div> -->
           <div class="form-group">
           <label for="message-text" class="control-label col-lg-3" >登录名称*</label> 
           <div class="col-lg-9">
           	<!-- <select name="loginId" class="form-control" id="loginId">  -->
           	 <div id ="loginIdNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;" name="loginIdNotice"></div>
           	<input type="text" class="form-control" id="loginId"  name ="loginId">
           	</select> 
           	</div>
         </div>  
          <div class="form-group">
          <label for="message-text" class="control-label col-lg-3">登录密码*</label>
          <div class="col-lg-9">
           <!--  <textarea class="form-control" id="message-text" name ="loginPassword"></textarea> -->
           <div id ="loginPasswordNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;" name="loginPasswordNotice"></div>
           	<input type="text" class="form-control" id="loginPassword"  name ="loginPassword">
            </div>
          </div>
          <div class="form-group">
          <label for="message-text" class="control-label col-lg-3">配置服务器</label>
          <div class="col-lg-9">
           <select class="form-control"  id="congfigsel" name="congfigsel"></select>
          
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
