<%@ page contentType="text/html; charset=UTF-8"%>
<style>
/* .fingerprint-bg {
    background: url(/pages/onecard/personnel/hand.png) 0 0 no-repeat;
    width: 400px;
    height: 210px;
}  */
#imgShow{
width:160px;
height:190px;
cursor: pointer;
}


#cover{
            position: fixed;
            top: 0;
            left: 0;
            height: 100%;
            width: 100%;
            background-color: rgba(0,0,0,0.3);
            display:none;
            z-index:1000;
        }
        


       
        
        
        
        
        
</style>

<div class="modal open" id="personModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">添加人员</h4>
      </div>
     
     <div class="modal-body" style="padding:0px;">
     <!--  <div class="nav-tabs-custom" style="padding:0px;"> -->
	    <ul class="nav nav-tabs">
	     <li name="basicInfo" class="active"><a href="#basicInfo" data-toggle="tab" id="jb">基本信息</a></li>
	      
	    <li name="allInfo"><a href="#allInfo" data-toggle="tab" id="xx">详细信息</a></li> 
	    <li name="fingerInfo"><a href="#fingerInfo" data-toggle="tab" id="zw">指纹信息</a></li> 
	     
	    </ul>	
	    				
       <div class="tab-content">  
         <div class="tab-pane   active"  style=" padding-top: 10px;" id="basicInfo" > 
         <form class="form-horizontal" id="form1" name="formname">
                  <input type="hidden" name ="personId">
                  <input type="hidden" name ="bimDeptId">
                   <input type="hidden" name ="photoSrc">
                 <div class="col-lg-7 left form-group"> 
					<div class="form-group" style="margin-bottom: 3px;">
                    <label for="recipient-name" class="control-label col-lg-4"><font color="red">*</font>人员编号</label>
                    <div id="personCode" class="col-lg-8">
                    <div id ="personCodeNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
                     <input type="text" class="form-control"   name ="personCode" >
                    </div>
                    </div>
                    
                     <div class="form-group" style="margin-bottom: 3px;">
                    <label for="recipient-name" class="control-label col-lg-4"><font color="red">*</font>姓名</label>
                    <div id="personName" class="col-lg-8">
                    <div id ="personNameNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
                    <input type="text" class="form-control"   name ="personName" >
                    </div>
                    </div>
                     <div class="form-group" style="margin-bottom: 3px;">
                    <label for="recipient-name" class="control-label col-lg-4"><font color="red">*</font>部门</label>
                    <div id="deptName" class="col-lg-8">
                    <div id ="deptNameNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
                    <input type="hidden" name ="deptName">
                    <input type="text" class="form-control"   name ="deptName"  disabled="disabled">
                    </div>
                    </div> 
                     <div class="form-group" style="margin-bottom: 3px;">
                    <label for="recipient-name" class="control-label col-lg-4 ">性别</label>
                    <div id="sex" class="col-lg-8" >
                     <label><input type="radio"  name ="sex" value="0" checked="true" id="radio1"> 男  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><label><input type="radio"  name ="sex" value="1" id="radio2">女</label>
                    </div>
                    </div> 
                     <div class="form-group" style="margin-bottom: 3px;">
                    <label for="recipient-name" class="control-label col-lg-4">身份类型</label>
                    <div  class="col-lg-8" >
                    <div id ="sexNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
                  
                    <select id="personType" title="无" class="form-control" name="custom3" >
                    
                    </select>
                    </div>
                    </div>
                     <div class="form-group" style="margin-bottom: 3px;">
                    <label for="recipient-name" class="control-label col-lg-4">证件类型</label>
                    <div  class="col-lg-8" >
                    <div id ="sexNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
                    <select id="certtype" title="身份证" class="form-control" name="certtype">
                    <option value="身份证" title="身份证">身份证</option>
                    <option value="军官证" title="军官证">军官证</option>
                    <option value="学生证" title="学生证">学生证</option>
                    <option value="驾驶证" title="驾驶证">驾驶证</option>
                    <option value="护照" title="护照">护照</option>
                    <option value="港澳通行证" title="港澳通行证">港澳通行证</option>
                    </select>
                    </div>
                    </div> 
                     <div class="form-group" style="margin-bottom: 3px;">
                    <label for="recipient-name" class="control-label col-lg-4"><!-- <font color="red">*</font> -->证件号码</label>
                    <div id="certId" class="col-lg-8">
                    <div id ="certIdNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
                    
                     <input type="text" class="form-control"   name ="certId" >
                    </div>
                    </div>
                     <div class="form-group" style="margin-bottom: 3px;">
                    <label for="recipient-name" class="control-label col-lg-4">出生日期</label>
                    <div id="birthday" class="col-lg-8">
                    <div id ="birthdayNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
                     <input type="text" class="form-control"   name ="birthday" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd',maxDate:'2099-12-31'})"><i class="fa fa-calendar"></i>
                    
                    </div>
                    </div> 
                     <div class="form-group" style="margin-bottom: 3px;">
                    <label for="recipient-name" class="control-label col-lg-4">拼音代码</label>
                    <div id="pinyin" class="col-lg-8">
                    <div id ="pinyinNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
                    
                     <input type="text" class="form-control"   name ="pinyin" >
                    </div>
                    </div> 
                     <div class="form-group" style="margin-bottom: 3px;">
                    <label for="recipient-name" class="control-label col-lg-4"><!-- <font color="red">*</font> -->联系电话</label>
                    <div id="phone" class="col-lg-8">
                    <div id ="phoneNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
                    
                     <input type="text" class="form-control"   name ="phone" >
                   <!--   <div>短信开门:<input type="checkbox" id="openMsg" name="custom1" value="1"></div> -->
                    </div>
                    </div> 
                     <div class="form-group" style="margin-bottom: 3px;">
                    <label for="recipient-name" class="control-label col-lg-4">联系地址</label>
                    <div id="address" class="col-lg-8">
                    <div id ="addressNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
                   
                     <input type="text" class="form-control"   name ="address" >
                    </div>
                    </div> 
					
                    <!-- </div> -->
                  </div >  
                <div  class="col-lg-5 left form-group" style="padding-left:80px;"> 
               <div style="border:solid 1px ;width:160px; height:190px;" id="imgdiv" >
                 <img id="imgShow"  class="img-polaroid"  alt="人员头像" >
				</div>
				
				<div class="upload-box">
				<input name="up_img" id="up_img" type="file" style="position:absolute;width: 160px;">
				 
				</div>
				
                 </div> 
              <!--  <div  class="col-lg-5 left form-group" style="padding-left:80px;"> 
               <div style="border:solid 1px ;width:160px; height:190px;" id="imgdiv" >
                 <img id="pic"  class="img-polaroid"  alt="人员头像"  src="">
				</div>
				<input name="up_img" id="up_img" type="file" >
				<input id="upload" name="file"  type="file" />
                 </div> -->
                 
         </form>
 

         </div>
         
         
          <div class="tab-pane "  style=" padding-top: 10px;" id="fingerInfo" >
          <form class="form-horizontal" id="form3" name="formname">
          <!-- <input type="hidden" name="personId"> -->
          <input type="hidden" name="fingerprint1">
           <input type="hidden" name="fingerprint1Num">
           <input type="hidden" name="fingerprint2">
           <input type="hidden" name="fingerprint2Num">
            <input type="hidden" name="flag">
          <input type="hidden" name="registcount" value="1">
          
           <div class="form-group " style="margin-left: 140px;">
           <a class="btn  btn-default fingerbtn" tabindex="-1" href="javascript:;" style="margin-right: 20px;" id="zc" >开始注册</a>
		   <a class="btn  btn-default fingerbtn" tabindex="-1" href="javascript:;" style="margin-right: 20px;" id="sc">删除指纹</a>
		   <a class="btn  btn-default fingerbtn" tabindex="-1" href="javascript:;" style="margin-right: 20px;" id="qb">全部清除</a>		
          </div>
          <div class="form-group fingerprint-bg" style="margin-left: 60px;">
          <div class="fingerprint-ck">
				<div class="fingerprint-ck fingerprint-ck-leftOne">
					<i class="data-icon-finger-ck-unchecked" style="display:none"></i>
					<input type="checkbox" value="1" name="checkbox[0]" style="display:none">
				</div>
				<div class="fingerprint-ck fingerprint-ck-leftTwo">
					<i class="data-icon-finger-ck-unchecked" style="display:none"></i>
					<input type="checkbox" value="2" name="checkbox[1]" style="display:none">
				</div>
				<div class="fingerprint-ck fingerprint-ck-leftThree">
					<i class="data-icon-finger-ck-unchecked" style="display:none"></i>
					<input type="checkbox" value="3" name="checkbox[2]" style="display:none">
				</div>
				<div class="fingerprint-ck fingerprint-ck-leftFour">
					<i class="data-icon-finger-ck-unchecked" style="display:none"></i>
					<input type="checkbox" value="4" name="checkbox[3]" style="display:none">
				</div>
				<div class="fingerprint-ck fingerprint-ck-leftFive">
					<i class="data-icon-finger-ck-unchecked" style="display:none"></i>
					<input type="checkbox" value="5" name="checkbox[4]" style="display:none">
				</div>
				<div class="fingerprint-ck fingerprint-ck-rightOne">
					<i class="data-icon-finger-ck-unchecked" style="display:none"></i>
					<input type="checkbox" value="6" name="checkbox[5]" style="display:none">
				</div>
				<div class="fingerprint-ck fingerprint-ck-rightTwo">
					<i class="data-icon-finger-ck-unchecked" style="display:none"></i>
					<input type="checkbox" value="7" name="checkbox[6]" style="display:none">
				</div>
				<div class="fingerprint-ck fingerprint-ck-rightThree">
					<i class="data-icon-finger-ck-unchecked" style="display:none"></i>
					<input type="checkbox" value="8" name="checkbox[7]" style="display:none">
				</div>
				<div class="fingerprint-ck fingerprint-ck-rightFour">
					<i class="data-icon-finger-ck-unchecked" style="display:none"></i>
					<input type="checkbox" value="9" name="checkbox[8]" style="display:none">
				</div>
				<div class="fingerprint-ck fingerprint-ck-rightFive">
					<i class="data-icon-finger-ck-unchecked" style="display:none"></i>
					<input type="checkbox" value="10" name="checkbox[9]" style="display:none">
				</div>
			</div>
			
			<div class="fingerprint-all">
				<div name="fingerprint0" class="fingerprint-finger-undo fingerprint-finger-leftOne" >
					<input type="hidden" id="fingerprint[0]" value=""  name="fingerindex">
					
				</div>
				<div name="fingerprint1" class="fingerprint-finger-undo fingerprint-finger-leftTwo">
					<input type="hidden" id="fingerprint[1]" value=""  name="fingerindex">
					
				</div>
				<div name="fingerprint2" class="fingerprint-finger-undo fingerprint-finger-leftThree">
					<input type="hidden" id="fingerprint[2]" value=""  name="fingerindex">
					
				</div>
				<div name="fingerprint3" class="fingerprint-finger-undo fingerprint-finger-leftFour">
					<input type="hidden" id="fingerprint[3]" value=""  name="fingerindex">
					
				</div>
				<div name="fingerprint4" class="fingerprint-finger-undo fingerprint-finger-leftFive">
					<input type="hidden" id="fingerprint[4]" value=""  name="fingerindex">
					
				</div>
				<div name="fingerprint5" class="fingerprint-finger-undo fingerprint-finger-rightOne">
					<input type="hidden" id="fingerprint[5]" value=""  name="fingerindex">
					
				</div>
				<div name="fingerprint6" class="fingerprint-finger-undo fingerprint-finger-rightTwo">
					<input type="hidden" id="fingerprint[6]" value=""  name="fingerindex">
					
				</div>
				<div name="fingerprint7" class="fingerprint-finger-undo fingerprint-finger-rightThree">
					<input type="hidden" id="fingerprint[7]" value=""  name="fingerindex">
					
				</div>
				<div name="fingerprint8" class="fingerprint-finger-undo fingerprint-finger-rightFour">
					<input type="hidden" id="fingerprint[8]" value=""  name="fingerindex">
					
				</div>
				<div name="fingerprint9" class="fingerprint-finger-undo fingerprint-finger-rightFive">
					<input type="hidden" id="fingerprint[9]" value="" name="fingerindex">
					
				</div>
				<div class="fingerprint-deviceError-tip">
				</div>
			</div>			
          </div>
           
        
           <div class="form-group fingerprint-tip" style="margin-left: 140px;">
            
			
			&nbsp;提示：请在指纹采集器上进行指纹数据收集。
	
          </div>
           
           
          </form>
          
          <div>
          
          </div id="cover">
          
           <object id="csocx" classid="clsid:73B60FD1-C8FC-414E-9246-9FD071DAA2C8"></object>
          
          </div>
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         <div class="tab-pane " style=" padding-top: 10px;" id="allInfo" >
          <form class="form-horizontal" id="form2" name="formname">
          <div class="form-group" style="margin-bottom: 3px;">
                    <label for="recipient-name" class="control-label col-lg-3">英文名</label>
                    <div id="englishName" class="col-lg-6">
                    <div id ="englishNameNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
                   
                     <input type="text" class="form-control"   name ="englishName" >
                    </div>
                    </div>
				
				
				<div class="form-group" style="margin-bottom: 3px;">
                    <label for="recipient-name" class="control-label col-lg-3">E-MAIL</label>
                    <div id="email" class="col-lg-6">
                    <div id ="emailNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
                   
                     <input type="text" class="form-control"   name ="mail" >
                    </div>
                    </div>
				<div class="form-group" style="margin-bottom: 3px;">
                    <label for="recipient-name" class="control-label col-lg-3">到职日期</label>
                    <div id="jobDate" class="col-lg-6">
                    <div id ="jobDateNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
                   
                     <input type="text" class="form-control"   name ="jobDate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd',maxDate:'2099-12-31'})"><i class="fa fa-calendar"></i>
                    </div>
                    </div>
				<div class="form-group" style="margin-bottom: 3px;">
                    <label for="recipient-name" class="control-label col-lg-3">离职日期</label>
                    <div id="resignationDate" class="col-lg-6">
                    <div id ="resignationDateNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
                   
                     <input type="text" class="form-control"   name ="resignationDate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd',maxDate:'2099-12-31'})"><i class="fa fa-calendar"></i>
                    </div>
                    </div>
				<div class="form-group" style="margin-bottom: 3px;">
                    <label for="recipient-name" class="control-label col-lg-3">学历</label>
                    <div id="educational" class="col-lg-6">
                    <div id ="educationalNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
                   
                     <input type="text" class="form-control"   name ="educational" >
                    </div>
                    </div>
				<div class="form-group" style="margin-bottom: 3px;">
                    <label for="recipient-name" class="control-label col-lg-3">民族</label>
                    <div id="ethnic" class="col-lg-6">
                    <div id ="ethnicNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
                   
                     <input type="text" class="form-control"   name ="ethnic" >
                    </div>
                    </div>
          
          
          </form>
         
         
         
         
         
         
         
         
         
         </div> 
        
          <div style="clear:both;"/> 
        
        
        
        
          </div> 
      </div>
      
      <div class="modal-footer"  >
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="submit">保存</button>
      
      
    </div>
  </div>
</div> 
</div>



