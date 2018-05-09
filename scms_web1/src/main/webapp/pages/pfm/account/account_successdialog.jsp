<%@ page contentType="text/html; charset=UTF-8"%>
<div class="modal open" id="successModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
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
                        <div class="col-md-6"><span id="peoplecsuccess"></span></div>
                        </div>
                        
                        <div class="row">
                        <div class="col-md-6 right"><label>账户类型:</label></div>
                        <div class="col-md-6"><span>普通账户</span></div>
                        </div>
                        
                        <div class="row">
                        <div class="col-md-6 right"><label>充值时间:</label></div>
                        <div class="col-md-6"><span id="chargetime"></span></div>
                        </div>
                        
                         <div class="row"  id="sucbeforem">
                        <div class="col-md-6 right"><label>充值前金额:</label></div>
                        <div class="col-md-6"><span id="chargebeforemoney" style="color:#F87808;font-weight:bold;font-size:20px"></span></div>
                        </div>
                         <div class="row"  id="sucbeforet">
                        <div class="col-md-6 right"><label>充值前到期时间:</label></div>
                        <div class="col-md-6"><span id="beforedistime" ></span></div>
                        </div>
                         <div class="row">
                        <div class="col-md-6 right"><label>本次充值金额:</label></div>
                        <div class="col-md-6"><span id="chargemoney" style="color:#F87808;font-weight:bold;font-size:20px"></span></div>
                        </div>
                        
                        <div class="row" id="sucafterm">
                        <div class="col-md-6 right"><label>充值后金额:</label></div>
                        <div class="col-md-6"><span id="chargeaftermoney" style="color:#F87808;font-weight:bold;font-size:20px"></span></div>
                        </div>
                        <div class="row"  id="sucaftert">
                        <div class="col-md-6 right"><label>充值后到期时间:</label></div>
                        <div class="col-md-6"><span id="afterdistime" ></span></div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                    	关闭
                    </button>
                   
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>