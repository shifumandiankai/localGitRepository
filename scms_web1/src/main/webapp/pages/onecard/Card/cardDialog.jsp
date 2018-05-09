<%@ page contentType="text/html; charset=UTF-8"%>
<style>
  p{
color: #353535;
font-weight: bold;
}
.divider {
height: 1px;
margin: 9px 1px;
overflow: hidden;
background-color: #E5E5E5;
border-bottom: 1px solid white;
}

/*  .control-label {
    width: 72px;
    padding: 2px 1px;
}  */
.form-horizontal label {
    cursor: default;
    margin-bottom: 0px;
}
/* tr.active{
    background-color: #017ebc;!important
} */

   
 


</style>
<div class="modal open" id="cardModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content" style=" width: 750px;"><!--  -->
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">卡片操作</h4>
      </div>
      <div class="modal-body">
      
        <form class="form-horizontal"> 
        
         
       
          
          <div class="form-group">
          <div class="col-lg-12">
          <p class="pull-left" style="margin:0px;">人员信息&nbsp;</p>
          <hr class="divider">
          </div>
          </div>
          <div class="form-group ">
          <div class="col-sm-5">
          <label  class="col-sm-6 control-label" style=" width: 72px;padding: 2px 1px;">持卡人姓名</label>
					<div class="col-sm-6">
				<input type="text" class="form-control" name="personName" style=" width: 181px;" />
				</div>
		  </div>
		   <div class="col-sm-5">
          <label  class="col-sm-6 control-label" style=" width: 72px;padding: 2px 1px;">部门</label>
					<div class="col-sm-6">
				<input type="text" class="form-control" name="deptName" style=" width: 181px;"/>
				</div>
		  </div>
		  <div class="col-sm-2">
		  <button id="person-info"  type="button" class="btn btn-default">详细信息</button>
		  </div>
          </div>
           <div class="form-group " style="padding-top:10px;">
          <div class="col-sm-5">
          <label  class="col-sm-6 control-label" style=" width: 72px;padding: 2px 1px;">电话号码</label>
					<div class="col-sm-6">
				<input type="text" class="form-control" name="phone"  placeholder="" style=" width: 181px;"/>
				</div>
		  </div>
		   <div class="col-sm-5">
          <label  class="col-sm-6 control-label" style=" width: 72px;padding: 2px 1px;">证件号码</label>
					<div class="col-sm-6">
				<input type="text" class="form-control" name="certId"  placeholder="" style=" width: 181px;"/>
				</div>
		  </div>
		  
          </div>
         
        <!--  <div class="form-group">
          <div class="col-lg-12">
          <p class="pull-left" style="margin:0px;">卡片操作&nbsp;</p>
          <hr class="divider">
          </div>
          </div> -->
         
          <div class="nav-tabs-custom">
			<ul class="nav nav-tabs">
			<li id="li1" class="active"><a href="#card" data-toggle="tab" id="a1">卡片操作</a></li>
			<li id="li2"><a href="#finger" data-toggle="tab" id="a2">指纹操作</a></li>
			<li id="li3"><a href="#carNumber" data-toggle="tab" id="a3">车牌操作</a></li>
			</ul>
			 <div class="tab-content">
           <div class="tab-pane  active" id="card">
           
         <div class="form-group"> 
           <div  style="width:600px; height:300px; overflow-y:auto;padding-left: 20px;float:left;" >
                 <table id="cardtable" class="table table-bordered table-striped " width="300px" height="200px" >
							<tbody>
							<thead >
								<tr id="tr1" role="row" style="width: 579px; height: 40px;">	
									<th>序号</th>
									<th >卡号</th>
									<th >卡状态</th>
									<th >绑定系统</th>
									<th  style="display:none;"></th>
									 
								</tr>
								
							</thead>
							 
							</tbody>
						</table>
                 </div> 
           <div class="col-lg-2">
        <button id="card-bind-btn"  type="button" class="btn btn-default" >开卡</button>
        <button id="card-swap-btn"  type="button" class="btn btn-default" name="normalcard" disabled="disabled">换卡</button>
        <button id="card-loss-btn"  type="button"  class="btn btn-default"  name="normalcard" disabled="disabled">挂失</button>
        <button id="card-unloss-btn"  type="button" class="btn btn-default" disabled="disabled"  name="unnormalcard">解挂</button>
        <!-- <button id="card-supplement-btn"  type="button" class="btn btn-default" disabled="disabled"  name="unnormalcard">补卡</button> -->
        <button id="card-return-btn"  type="button"  class="btn btn-default"  name="normalcard" disabled="disabled">退卡</button>
      </div>
           </div> 
           </div>
           <div class="tab-pane" id="finger">
           <div class="form-group"> 
           <div  style="width:600px; height:300px; overflow-y:auto;padding-left: 20px;float:left;" >
                 <table id="fingertable" class="table table-bordered table-striped " width="300px" height="200px" >
							<tbody>
							<thead >
								<tr id="tr2" role="row" style="width: 579px; height: 40px;">	
									<th>第一个指纹</th>
									<th >第二个指纹</th>
									<th >授权系统</th>
									<th  style="display:none;"></th>
									 
								</tr>
								
							</thead>
							 
							</tbody>
						</table>
                 </div> 
            <div class="col-lg-2">
            <button id="fingersqbtn"  type="button" class="btn btn-default" >授权</button>
            </div>
           </div>
          
           
          
           
           </div>
           <div class="tab-pane " id="carNumber">
          <div class="form-group"> 
           <div  style="width:600px; height:300px; overflow-y:auto;padding-left: 20px;float:left;" >
                 <table id="carnumbertable" class="table table-bordered table-striped " width="300px" height="200px" >
							<tbody>
							<thead >
								<tr id="tr3" role="row" style="width: 579px; height: 40px;">	
									<th>车牌号</th>
									<th >授权系统</th>
									
									<th  style="display:none;"></th>
									 
								</tr>
								
							</thead>
							 
							</tbody>
						</table>
                 </div> 
            <div class="col-lg-2">
            <button id="carsqbtn"  type="button" class="btn btn-default" >授权</button>
            <button id="carnosqbtn"  type="button" class="btn btn-default"  disabled="disabled">解除</button>
            </div>
           </div>
         
           
           </div>
           
          
          </div>
									
			</div> 
         
       
          
          
          
          
          
        </form> 
        
        
        
        
        
        
        
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
       
      </div>
    </div>
  </div>
</div> 