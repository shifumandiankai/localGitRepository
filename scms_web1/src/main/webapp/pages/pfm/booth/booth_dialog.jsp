<%@ page contentType="text/html; charset=UTF-8"%>
<div class="modal open" id="boothModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">岗亭信息</h4>
      </div>
      <div class="modal-body">
 <form class="form-horizontal">
                         <input type="hidden" name ="pfmBoothId">
                        <div class="form-group">
                            <div class="col-md-2 right"><label >岗亭名称*</label></div>
                            <div class="col-md-4"> <div id ="boothNameNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div><input type="text" class="form-control" name ="boothName"/></div>
                             <div class="col-md-2 right"><label >岗亭编号*</label></div>
                          <div class="col-md-4">
                          <div id ="boothCodeNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div><input type="text" class="form-control pull-right"  name="boothCode"/>
                          </div>
                           
                        </div><!-- context first -->
                        <!-- <br /> -->
                        <div class="form-group">
                         <div class="col-md-2 right"><label >联系电话*</label></div>
                            <div class="col-md-4"> <div id ="phoneNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div><input type="text" class="form-control"  name ="phone"/></div>
                            <div class="col-md-2 right"><label >客户端IP*</label></div>
                            <div class="col-md-4"> <div id ="clientIpNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div><input type="text" class="form-control pull-right"  name="clientIp"/></div>
                            
                        </div><!-- context second -->
                        <!-- <br /> -->
                        <!-- <div class="row">
                            <div class="col-md-3 right"><label >所属停车场</label></div>
                            <div class="col-md-3"><div id ="parkNameNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div><select name="parkingLotName" class="form-control" id="parkName"></select></div>
                            <div class="col-md-3 right"><label >时段选择</label></div>
                            <div class="col-md-3"><div id ="timeNameNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div><select name="timeName" class="form-control" id="timeName"></select></div>
                        </div>context third -->
                        <div class="form-group">
                            <div class="col-md-2 right"><label >出入口选择</label></div>
                            <div class="col-md-10"><div id ="entranceNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div><select name="pfmEntranceId" class="form-control" id="entrance"></select></div>
                        </div>
                        
                        <!-- <br /> -->
                        <!-- <div class="row">
                            <div class="col-md-3 right">启用功能</div>
                            <div class="col-md-9">
                            <div id ="consumeConfirmNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
                                <div class="row">
                                <div  class="col-md-6">
                                <label>消费确认</label>
                                <input type="radio"  name="consumeConfirmresult" value="0" checked="checked" />
                                </div>
                                 <div  class="col-md-6">
                                <label >小车场临时放行</label>
                                <input type="radio"  name="consumeConfirmresult" value="1" />
                                </div>
                                </div>
                            </div>
                        </div>context fourth -->
                       <!--  <br /> -->
                        <div class="form-group">
                         <!-- <div class="col-md-3 right">岗亭编号</div>
                          <div class="col-md-3">
                          <div id ="boothCodeNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div><input type="text" class="form-control pull-right"  name="boothCode"/>
                          </div> -->
                          <div class="col-md-2 right"><label >备注信息</label></div>
                            <div class="col-md-10"><div id ="noteNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div><input type="text" class="form-control pull-right"  name="note"/></div>
                        </div>
                        <!-- <br /> -->
                        <div class="form-group">
                            <div class="col-md-3 right"><label >是否中央收费岗亭</label></div>
                            <div class="col-md-9">
                               <!--  <div class="row"> -->
                                    <div class="col-md-6">
                                        <label >是</label>
                                        <input type="radio" name="result"  value="是" id="yes" checked="checked"/>
                                    </div>
                                    <div class="col-md-6">
                                        <label >否</label>
                                        <input type="radio" name="result"  value="否" id="no"/>
                                    </div>
                                <!-- </div> -->
                            </div>
                        </div><!-- context fifth -->
                        <!-- <br /> -->
                        <div class="row">
                            <div class="col-md-6"></div>
                            <div class="col-md-6"></div>
                        </div><!-- button -->
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                    	关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="submit">
                        保存
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>