<%@ page contentType="text/html; charset=UTF-8"%>
<style>
.form-control[readonly]{
background-color: rgba(255, 255, 255, 0)!important;
}
</style>
  <div class="modal open" id="timeModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">时段信息</h4>
      </div>
      <div class="modal-body">

        <form class="form-horizontal"> 
         <input type="hidden" name ="pfmTimeId">
          <!-- <div class="form-group"> -->
          <div class="form-group" >
            
           
           
            <div class="col-md-3 right"><label >时段编号*</label></div>
            <div  class="col-lg-3">
            <div id ="timeCodeNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
            <input type="text" class="form-control" id="recipient-name"  name ="timeCode">
            </div>
            
            <div class="col-md-3 right"><label >时段名称*</label></div>
            
            <div  class="col-lg-3">
           <div id ="timeNameNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
            <input type="text" class="form-control" id="recipient-name"  name ="timeName">
            </div>
            </div>
          <!-- </div> -->
          
         <!--  <div class="form-group"> -->
          <div class="form-group">
           <div class="col-md-3 right"><label >开始日期时间*</label></div>
           
            <div class="col-lg-3">
             <div id ="beginTimeNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
             <div class="input-group date">
			<!-- <input type="text"  name="beginTime" class="form-control pull-right"  > -->
			<input type="text"  name="strbeginTime" class="form-control pull-right"  onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd',maxDate:'2099-12-31'})">
			</div>
			</div>
            
            
            <div class="col-md-3 right"><label >结束日期时间*</label></div>
             
            <div class="col-lg-3">
            <div id ="endTimeNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
             <div class="input-group date">
			<!-- <input type="text"  name="endTime" class="form-control pull-right"> -->
			<input type="text"  name="strendTime" class="form-control pull-right"  onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd',maxDate:'2099-12-31'})">
			</div>
            </div>
           </div>
          
          
           
         <!--  </div> -->
          <!-- <div class="form-group"> -->
          <div class="form-group" >
         <div class="col-md-3 right"><label >时段一开始时间</label></div>
            
              <div  class="col-lg-3 bootstrap-timepicker timepicker">
           <div id ="dayBeginTime1Notice" class="point-out " style="width: 348px;top:-38px; left:0px; display: none;"></div>
           <!--  <input type="text" class="form-control" id="recipient-name"  name ="dayBeginTime1" placeholder="如：00:00:00"> -->
            <input type="text" class="form-control time " id="recipient-name"  name ="strBeginTime1" value="00:00" style="cursor: not-allowed;"readonly="readonly">
            </div>
            
           
              <div class="col-md-3 right"><label >时段一结束时间</label></div>
           
            <div  class="col-lg-3 bootstrap-timepicker timepicker">
            <div id ="dayEndTime1Notice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
            <!-- <input type="text" class="form-control" id="recipient-name"  name ="dayEndTime1" placeholder="如：23:59:59"> -->
            <input type="text" class="form-control time " id="recipient-name"  name ="strEndTime1"  value="00:00" style="cursor: not-allowed;"readonly="readonly">
            
			</div>
			</div>
			<div class="form-group" >
         <div class="col-md-3 right "><label >时段二开始时间</label></div>
            
              <div  class="col-lg-3 bootstrap-timepicker timepicker" >
           <div id ="dayBeginTime2Notice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
          <!--   <input type="text" class="form-control" id="recipient-name"  name ="dayBeginTime2" placeholder="如：00:00:00"> -->
            <input type="text" class="form-control time" id="recipient-name"  name ="strBeginTime2" value="00:00" style="cursor: not-allowed;"readonly="readonly">
            </div>
            
           
              <div class="col-md-3 right "><label >时段二结束时间</label></div>
           
            <div  class="col-lg-3 bootstrap-timepicker timepicker">
            <div id ="dayEndTime2Notice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
            <!-- <input type="text" class="form-control" id="recipient-name"  name ="dayEndTime2" placeholder="如：23:59:59"> -->
            <input type="text" class="form-control time" id="recipient-name"  name ="strEndTime2" value="00:00" style="cursor: not-allowed;"readonly="readonly">
			</div>
			</div>
          <!-- </div> -->
      <!--   <div class="container"> -->
          <!--  <div class="form-group"> -->
           <div class="form-group">
         <div class="col-md-3 right"><label >时段三开始时间</label></div>
            
              <div  class="col-lg-3 bootstrap-timepicker timepicker">
           <div id ="dayBeginTime3Notice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
            <!-- <input type="text" class="form-control" id="recipient-name"  name ="dayBeginTime3" placeholder="如：00:00:00"> -->
             <input type="text" class="form-control time" id="recipient-name"  name ="strBeginTime3" value="00:00" style="cursor: not-allowed;"readonly="readonly">
            </div>
            
           
              <div class="col-md-3 right"><label >时段三结束时间</label></div>
           
            <div  class="col-lg-3 bootstrap-timepicker timepicker">
            <div id ="dayEndTime3Notice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
            <!-- <input type="text" class="form-control" id="recipient-name"  name ="dayEndTime3" placeholder="如：23:59:59"> -->
            <input type="text" class="form-control time" id="recipient-name"  name ="strEndTime3" value="00:00" style="cursor: not-allowed;"readonly="readonly">
			</div>
			</div>
			<div class="form-group">
         <div class="col-md-3 right"><label >时段四开始时间</label></div>
            
              <div  class="col-lg-3   bootstrap-timepicker timepicker">
           <div id ="dayBeginTime4Notice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
            <!-- <input type="text" class="form-control" id="recipient-name"  name ="dayBeginTime4" placeholder="如：00:00:00"> -->
            <input type="text" class="form-control time" id="recipient-name"  name ="strBeginTime4" value="00:00" style="cursor: not-allowed;"readonly="readonly">
            </div>
            
           
              <div class="col-md-3 right"><label >时段四结束时间</label></div>
           
            <div  class="col-lg-3 bootstrap-timepicker timepicker">
            <div id ="dayEndTime4Notice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>      
            <input type="text" class="form-control time" id="recipient-name"  name ="strEndTime4" value="00:00" style="cursor: not-allowed;"readonly="readonly">
			</div>
			</div>
           	<div class="form-group" >
           
            <div class="col-md-3 right"><label>启用星期*</label></div>
           <!--  <label class=" control-label" style="margin-left:20px;"></label> -->
           <div class="col-md-9">
            <div id ="listNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
            
            <label style="padding-right:50px;">
            <input type="checkbox" name="list" value="周一" /> 周一</label>
        
            
            <label style="padding-right:50px;">
            <input type="checkbox" name="list" value="周二" /> 周二</label>
            
            
            <label style="padding-right:50px;">
            <input type="checkbox" name="list" value="周三" /> 周三</label>
           
            
            <label style="padding-right:50px;">
            <input type="checkbox" name="list" value="周四" />周四</label>
  
            <label style="padding-right:50px;">
            <input type="checkbox" name="list" value="周五" /> 周五</label>
            
            
            <label style="padding-right:50px;">
            <input type="checkbox" name="list" value="周六" /> 周六</label>
            
          
            <label style="padding-right:55px;">
            <input type="checkbox" name="list" value="周日" />周日</label>
          
           
            <label style="padding-right:50px;">
            <input type="checkbox" name="list" value="每天" id="list" />每天</label>
          
             
            </div>
            
            </div>
           	<div class="form-group" >
             <div><div class="col-md-3 right"><label >出入口启用</label></div></div>
           <!--  <label class="control-label" style="margin-left:20px;"></label> -->
           <div class="col-md-9">
           
            <div class="col-md-6"><label><input type="checkbox" name="enableIn" value="0"  checked="checked" >进启用</input></label></div>
            <div  class="col-md-6"><label><input type="checkbox" name="enableOut"  value="0"  checked="checked"  >出启用</input></label></div>
            </div>
            </div>
           <!--  <div class="row">
             <div><div class="col-md-3 right"><label >启用时间限时</label></div></div>
            <label class="control-label" style="margin-left:20px;"></label>
           <div class="col-md-9">
           
            <div class="col-md-6"><label><input type="radio" name="result" id="dayLimit1" value="0"  checked="checked" >启用</input></label></div>
            <div  class="col-md-6"><label><input type="radio" name="result" id="dayLimit2" value="1"  >不启用</input></label></div>
            </div>
            </div> -->
           <!--  </div> -->
           <!--  </div> -->
        </form>
      </div> 
     <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="submit">保存</button>
      </div>
    </div>
  </div>
</div>  

