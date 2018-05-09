<%@ page contentType="text/html; charset=UTF-8"%>
<div class="modal open" id="exampleReturnModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">退款信息</h4>
      </div>
      <div class="modal-body">
 <form >
                        <div class="row">
                        <div class="col-md-6 right"><label>人员姓名:</label></div>
                        <div class="col-md-6"><span id="people"></span></div>
                        </div>
                        
                        <div class="row">
                        <div class="col-md-6 right"><label>账户类型:</label></div>
                        <div class="col-md-6"><span>普通账户</span></div>
                        </div>
                        
                        <div class="row" id="redevvalue">
                        <div class="col-md-6 right"><label>账户余额:</label></div>
                        <div class="col-md-6"><span id="chargelf" style="color:#F87808;font-weight:bold;font-size:20px"></span></div>
                        </div> 
                        
                        <div class="row" id="redev1">
                        <div class="col-md-6 right"><label>退款金额:</label></div>
                        <div class="col-md-6"  id="returndiv"><div id ="ReturnMoneyNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;" name="renotice"></div><input type="text" id="tmoney"/></div>
                        </div>
                        <div class="row" id="redevdisabled">
                        <div class="col-md-6 right"><label>到期时间:</label></div>
                        <div class="col-md-6" ><span id="redistime"></span></div>
                        </div>
                        <div class="row" id="redev2">
                        <div class="col-md-6 right"><label>退款的月个数或年个数:</label></div>
                        <div class="col-md-6"  id="returndiv"><div id ="YearormonthNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;" name="renotice"></div><input type="text" id="tmonthoryear"/></div>
                        </div>
                        
                          
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                    	关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="returnsuccess">
                        确定
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>