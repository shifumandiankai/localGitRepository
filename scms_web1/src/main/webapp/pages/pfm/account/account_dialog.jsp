<%@ page contentType="text/html; charset=UTF-8"%>
<style>

.tooltip-inner {
    max-width: 200px;
    padding: 3px 8px;
    color: #fff;
    text-align: center;
    background-color: #F87808;
    border-radius: 4px;
}
.form-horizontal {
    padding-bottom: 5px;
}

</style>
<div class="modal open" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">充值信息</h4>
      </div>
      <div class="modal-body">
  <form >
                        <div class="row">
                        <div class="col-md-6 right"><label>人员姓名:</label></div>
                        <div class="col-md-6" ><span id=people></span></div>
                        </div>
                        
                        <div class="row">
                        <div class="col-md-6 right"><label>账户类型:</label></div>
                        <div class="col-md-6" ><span>普通账户</span></div>
                        </div>
                        
                        <div class="row" id="devvalue">
                        <div class="col-md-6 right"><label>账户余额:</label></div>
                        <div class="col-md-6" ><span id="chargelf" style="color:#F87808;font-weight:bold;font-size:20px"></span></div>
                        </div>
                        
                         <div class="row" id="devdisabled">
                        <div class="col-md-6 right"><label>到期时间:</label></div>
                        <div class="col-md-6" ><span id="distime"></span></div>
                        </div>
                        
                          <div class="row" >
                        <div class="col-md-6 right"><label>充值金额:</label></div>
                        <div class="col-md-6" id="chargediv"><div id ="chargeMoneyNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div><input type="text" id="cmoney" placeholder="请输入"/></div>
                        </div>
                      
                    </form> 
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                    	关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="success">
                        确定
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>