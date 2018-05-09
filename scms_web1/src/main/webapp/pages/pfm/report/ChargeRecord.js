var wade = wade || {};
wade.role = wade.role || {};
var config1 = new Object();
var config2 = new Object();
var config3 = new Object();
var config4 = new Object();
var config5 = new Object();
$(".nav-tabs li:first").addClass("active");


$.ajax({
	 url: "/report/configentranceName.do",
     dataType: 'json',
     type:'post',
     quietMillis: 250,
     success: function (result) {
   	  $('*[name="entranceId"]').select2({
      		//dropdownParent:devClone,
      		multiple:true,
      		allowClear: true,
      		data:result
      	});
     	
  	   
     }
});
//绑定车辆类型
$.ajax({
	 url: "/report/parkreport/configCarType.do",
     dataType: 'json',
     type:'post',
     success: function (data) {
     	var result=eval(data);
     	if(result.length!=0){
     		for(var i in result){
     			if(result[i].id!=undefined){
     				$('select[name="carTypeId"]').append('<option value="'+result[i].id+'">'+result[i].text+'</option>');
     			}
         		
         	}
 		}
     }
});


//收费记录对象
	config1.columns = new Array("carNumber","personName","carTypeName","ruleName","parkingDuration","chargeFee","jianmianFee","jianmianRuleName","userName","inTime","outTime","chargeRecordId","inPhoto","outPhoto");
	config1.defs = new Array("chargeRecordId","inPhoto","outPhoto");
	config1.id="chargerecordexample";
	config1.getUrl="/report/selectAllListByPage.do";
	config1.btns=[
{'id':"10001",'name':'查询',"fun":config1sel},
{'id':'10002','name':'导出',"fun":exportcharge},
{'id':"10003",'name':'入场图片',"fun":chargephoto},
{'id':"10003",'name':'出场图片',"fun":chargeOutphoto},
	              ];
	config1.search = new Array(
			{
				'type' : 'search_hidden',
				'placeholder' : null,
				'url' : null,
				'index' : 0
			}
			);
//正常通行记录对象
	config2.columns = new Array("carNumber","direction","personName","carTypeName","parkName","entranceName","eventTime","carriagewayName","boothName","userName","photo","recordId");
	// 需要隐藏的列
	config2.defs = new Array("recordId","photo");
	config2.id="normalexample";
	config2.getUrl="/report/selectNormalListByPage.do";
	config2.btns=[
{'id':"10001",'name':'查询',"fun":config2sel},
{'id':'10002','name':'导出',"fun":exportnormal},
{'id':'10003','name':'车辆图片',"fun":photo}
	              ];
	config2.search = new Array(
			{
				'type' : 'search_hidden',
				'placeholder' : null,
				'url' : null,
				'index' : 0
			}
			
			
			);

//异常通行记录对象
	config3.columns = new Array("direction","eventTime","parkName","entranceName","carriagewayName","boothName","userName","recordId");
	config3.defs = new Array("recordId");
	config3.id="abnormalexample";
	config3.getUrl="/report/selectabNormalListByPage.do";
	config3.btns=[
{'id':"10001",'name':'查询',"fun":config3sel},
{'id':'10002','name':'导出',"fun":exportabnormal},
{'id':'10003','name':'车辆图片',"fun":photo}
	              ];
	config3.search = new Array(
			{
				'type' : 'search_hidden',
				'placeholder' : null,
				'url' : null,
				'index' : 0
			}
			);
//场内车辆记录对象
	config4.columns = new Array("carNumber","personName","carTypeName","parkName","entranceName","eventTime","carriagewayName","boothName","userName","recordId");
	config4.defs = new Array("recordId");
	config4.id="parkreportexample";
	config4.getUrl="/report/parkreport/selectListByPage.do";
	//config4.getUrl="/parkreport/selectListByPage.do";
	config4.btns=[
{'id':"10001",'name':'查询',"fun":config4sel},
{'id':'10002','name':'导出',"fun":exportpark},
{'id':'10003','name':'车辆图片',"fun":photo}
	              ];
	config4.search = new Array(
			{
				'type' : 'search_hidden',
				'placeholder' : null,
				'url' : null,
				'index' : 0
			}
			);
	
//账户操作记录
	config5.columns = new Array("carNumber","personName","carTypeName","typeId","fee","disableTime","insertTime");
	config5.id="feerecexample";
	config5.getUrl="/report/selectFeeRec.do";
	//config4.getUrl="/parkreport/selectListByPage.do";
	config5.btns=[
{'id':"10001",'name':'查询',"fun":config5sel},
{'id':'10002','name':'导出',"fun":exportfeerec}
	              ];
	config5.search = new Array(
			{
				'type' : 'search_hidden',
				'placeholder' : null,
				'url' : null,
				'index' : 0
			}
			);
	
	
	
	
	
	
	
	
	
wade.libs.datatable(config1);
wade.libs.datatable(config2);
wade.libs.datatable(config3);
wade.libs.datatable(config4);
wade.libs.datatable(config5);


var wapdev=$("#" + config1.id+'_wrapper');
var jsonuserinfo = $("#form1").serializeObject();
var formdata =JSON.stringify(jsonuserinfo);
wapdev.find('*[name="searchValue0_search_hidden"]').val(formdata);
wapdev.find('*[name="searchValue0_search_hidden"]').trigger("keyup.DT");

//收费记录导出
 function exportcharge(sel_tr,all_tr,sel_obj_arr,sel_obj){
	 var table = $('#chargerecordexample').DataTable();
		var order = table.order();
		var count=order[0][0];
		var colname=config1.columns[count];
	    var dev=$('#form1');
		var wapdev=$('#chargerecordexample_wrapper');
		var startTime=dev.find('*[name="startTime"]').val();
		var endTime=dev.find('*[name="endTime"]').val();
		dev.find('*[name="order1"]').val(colname);//列的名字 
		dev.find('*[name="order2"]').val(order[0][1]);//asc或desc
		if(startTime>endTime&&startTime!=''&&endTime!=''){
			var errMsg='开始时间不能大于结束时间';	
			dev.find('.point-out').hide();
			dev.find("#startTimeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
			setTimeout(function(){dev.find('.point-out').hide()},2000);
			return false;
		}
		var jsonuserinfo = dev.serializeObject();
		var formdata =JSON.stringify(jsonuserinfo);
		wapdev.find('*[name="searchValue0_search_hidden"]').val(formdata);
		wapdev.find('*[name="searchValue0_search_hidden"]').trigger("keyup.DT");
	    window.open("/report/exportcharge/"+formdata+".do");
 }
//收费记录查询
 function config1sel(sel_tr,all_tr,sel_obj_arr,sel_obj){
	 var  dev=$('#form1');
	 var wapdev=$("#" + config1.id+'_wrapper');
	 var startTime=dev.find('*[name="startTime"]').val();
	 var endTime=dev.find('*[name="endTime"]').val();
	// dev.find('*[name="selectclick"]').val('0');
	 if(startTime>endTime&&startTime!=''&&endTime!=''){
			var errMsg='开始时间不能大于结束时间';	
			dev.find('.point-out').hide();
			dev.find("#startTimeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
			setTimeout(function(){dev.find('.point-out').hide()},2000);
			return false;
		}
		var carval=dev.find('input[name="carNumber"]').val();
		var jsonuserinfo = $("#form1").serializeObject();
		var formdata =JSON.stringify(jsonuserinfo);
		wapdev.find('*[name="searchValue0_search_hidden"]').val(formdata);
		wapdev.find('*[name="searchValue0_search_hidden"]').trigger("keyup.DT");
		//$("#" + config1.id).DataTable().ajax.reload();
 }
//正常通行记录查询
function config2sel(sel_tr,all_tr,sel_obj_arr,sel_obj){
	var wapdev=$("#" + config2.id+'_wrapper');
	var  dev=$('#form2');
	var startTime=dev.find('*[name="startTime"]').val();
	var endTime=dev.find('*[name="endTime"]').val();
	//dev.find('*[name="selectclick"]').val('0');
	if(startTime>endTime&&startTime!=''&&endTime!=''){
		var errMsg='开始时间不能大于结束时间';	
		dev.find('.point-out').hide();
		dev.find("#startTimeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
		setTimeout(function(){dev.find('.point-out').hide()},2000);
		return false;
	}
	var jsonuserinfo = $("#form2").serializeObject();
	var formdata =JSON.stringify(jsonuserinfo);
	wapdev.find('*[name="searchValue0_search_hidden"]').val(formdata);
	wapdev.find('*[name="searchValue0_search_hidden"]').trigger("keyup.DT");
	//$("#" + config2.id).DataTable().ajax.reload();
}
//异常通行记录查询
function config3sel(sel_tr,all_tr,sel_obj_arr,sel_obj){
	var wapdev=$("#" + config3.id+'_wrapper');
	var  dev=$('#form3');
	var startTime=dev.find('*[name="startTime"]').val();
	var endTime=dev.find('*[name="endTime"]').val();
	//dev.find('*[name="selectclick"]').val(0);
	if(startTime>endTime&&startTime!=''&&endTime!=''){
		var errMsg='开始时间不能大于结束时间';	
		dev.find('.point-out').hide();
		dev.find("#startTimeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
		setTimeout(function(){dev.find('.point-out').hide()},2000);
		return false;
	}
	var jsonuserinfo = $("#form3").serializeObject();
	var formdata =JSON.stringify(jsonuserinfo);
	wapdev.find('*[name="searchValue0_search_hidden"]').val(formdata);
	wapdev.find('*[name="searchValue0_search_hidden"]').trigger("keyup.DT");
	//$("#" + config3.id).DataTable().ajax.reload();
}
//场内车辆记录查询
function config4sel(sel_tr,all_tr,sel_obj_arr,sel_obj){
	var wapdev=$("#" + config4.id+'_wrapper');
	var  dev=$('#form4');
	var startTime=dev.find('*[name="startTime"]').val();
	var endTime=dev.find('*[name="endTime"]').val();
	if(startTime>endTime&&startTime!=''&&endTime!=''){
		var errMsg='开始时间不能大于结束时间';	
		dev.find('.point-out').hide();
		dev.find("#startTimeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
		setTimeout(function(){dev.find('.point-out').hide()},2000);
		return false;
	}
	var carval=dev.find('input[name="carNumber"]').val();
	
	var jsonuserinfo = $("#form4").serializeObject();
	var formdata =JSON.stringify(jsonuserinfo);
	//alert(formdata);
	wapdev.find('*[name="searchValue0_search_hidden"]').val(formdata);
	wapdev.find('*[name="searchValue0_search_hidden"]').trigger("search.DT");
	//$("#" + config4.id).DataTable().ajax.reload();
}

//账户操作记录查询
function config5sel(sel_tr,all_tr,sel_obj_arr,sel_obj){
	var wapdev=$("#" + config5.id+'_wrapper');
	var  dev=$('#form5');
	var startTime=dev.find('*[name="startTime"]').val();
	var endTime=dev.find('*[name="endTime"]').val();
	if(startTime>endTime&&startTime!=''&&endTime!=''){
		var errMsg='开始时间不能大于结束时间';	
		dev.find('.point-out').hide();
		dev.find("#startTimeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
		setTimeout(function(){dev.find('.point-out').hide()},2000);
		return false;
	}
	var carval=dev.find('input[name="carNumber"]').val();
	
	var jsonuserinfo = $("#form5").serializeObject();
	var formdata =JSON.stringify(jsonuserinfo);
	//alert(formdata);
	wapdev.find('*[name="searchValue0_search_hidden"]').val(formdata);
	wapdev.find('*[name="searchValue0_search_hidden"]').trigger("search.DT");
}
//账户操作记录导出
function exportfeerec(){
	var table = $('#feerecexample').DataTable();
	var order = table.order();
	var count=order[0][0];
	var colname=config5.columns[count];
	var dev=$('#form5');
	var wapdev=$('#feerecexample_wrapper');
	var startTime=dev.find('*[name="startTime"]').val();
	var endTime=dev.find('*[name="endTime"]').val();
	dev.find('*[name="order1"]').val(colname);//列的名字 
	dev.find('*[name="order2"]').val(order[0][1]);//asc或desc
	if(startTime>endTime&&startTime!=''&&endTime!=''){
		var errMsg='开始时间不能大于结束时间';	
		dev.find('.point-out').hide();
		dev.find("#startTimeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
		setTimeout(function(){dev.find('.point-out').hide()},2000);
		return false;
	}
	var jsonuserinfo = dev.serializeObject();
	var formdata =JSON.stringify(jsonuserinfo);
	wapdev.find('*[name="searchValue0_search_hidden"]').val(formdata);
	wapdev.find('*[name="searchValue0_search_hidden"]').trigger("keyup.DT");
	window.open("/report/exportaccount/"+formdata+".do");
	
	
}
//正常通行记录导出
function exportnormal(sel_tr,all_tr,sel_obj_arr,sel_obj){
	var table = $('#normalexample').DataTable();
	var order = table.order();
	var count=order[0][0];
	var colname=config2.columns[count];
	var dev=$('#form2');
	var wapdev=$('#normalexample_wrapper');
	var startTime=dev.find('*[name="startTime"]').val();
	var endTime=dev.find('*[name="endTime"]').val();
	dev.find('*[name="order1"]').val(colname);//列的名字 
	dev.find('*[name="order2"]').val(order[0][1]);//asc或desc
	if(startTime>endTime&&startTime!=''&&endTime!=''){
		var errMsg='开始时间不能大于结束时间';	
		dev.find('.point-out').hide();
		dev.find("#startTimeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
		setTimeout(function(){dev.find('.point-out').hide()},2000);
		return false;
	}
	var jsonuserinfo = dev.serializeObject();
	var formdata =JSON.stringify(jsonuserinfo);
	wapdev.find('*[name="searchValue0_search_hidden"]').val(formdata);
	wapdev.find('*[name="searchValue0_search_hidden"]').trigger("keyup.DT");
	window.open("/report/exportnormal/"+formdata+".do");
}
//异常通行记录导出
function exportabnormal(){
	var table = $('#abnormalexample').DataTable();
	var order = table.order();
	var count=order[0][0];
	var colname=config3.columns[count];
	var dev=$('#form3');
	var wapdev=$('#abnormalexample_wrapper');
	var startTime=dev.find('*[name="startTime"]').val();
	var endTime=dev.find('*[name="endTime"]').val();
	dev.find('*[name="order1"]').val(colname);//列的名字 
	dev.find('*[name="order2"]').val(order[0][1]);//asc或desc
	if(startTime>endTime&&startTime!=''&&endTime!=''){
		var errMsg='开始时间不能大于结束时间';	
		dev.find('.point-out').hide();
		dev.find("#startTimeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
		setTimeout(function(){dev.find('.point-out').hide()},2000);
		return false;
	}
	
	var jsonuserinfo = dev.serializeObject();
	var formdata =JSON.stringify(jsonuserinfo);
	wapdev.find('*[name="searchValue0_search_hidden"]').val(formdata);
	wapdev.find('*[name="searchValue0_search_hidden"]').trigger("keyup.DT");
	window.open("/report/exportabnormal/"+formdata+".do");
}
//场内车辆记录导出
function exportpark(){
	var table = $('#parkreportexample').DataTable();
	var order = table.order();
	var count=order[0][0];
	var colname=config4.columns[count];
	var dev=$('#form4');
	var wapdev=$('#parkreportexample_wrapper');
	var startTime=dev.find('*[name="startTime"]').val();
	var endTime=dev.find('*[name="endTime"]').val();
	dev.find('*[name="order1"]').val(colname);//列的名字 
	dev.find('*[name="order2"]').val(order[0][1]);//asc或desc
	if(startTime>endTime&&startTime!=''&&endTime!=''){
		var errMsg='开始时间不能大于结束时间';	
		dev.find('.point-out').hide();
		dev.find("#startTimeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
		setTimeout(function(){dev.find('.point-out').hide()},2000);
		return false;
	}
	var carval=$('input[name="carNumber"]').val();
	var jsonuserinfo = dev.serializeObject();
	var formdata =JSON.stringify(jsonuserinfo);
	wapdev.find('*[name="searchValue0_search_hidden"]').val(formdata);
	wapdev.find('*[name="searchValue0_search_hidden"]').trigger("keyup.DT");
	window.open("/report/parkreport/export/"+formdata+".do");
}
//正常通行记录,异常通行记录,场内车场记录图片
function photo(sel_tr,all_tr,sel_obj_arr,sel_obj){
	if(sel_obj==null){
		wade.libs.alert('请选择车辆',2);
		return;
	}
	var devClone = $("#photoModel").clone(true);
	devClone.modal({backdrop: 'static', keyboard: false}).on('hidden.bs.modal',function(event){
		$(this).remove();
	});
	
	$.ajax({
		url :'/report/carPhoto.do',
		type:"post",
		data : {Photo:sel_obj.photo},
		success: function(data){
			//result = eval(data);
			result =JSON.parse(data);
			if(result.iRet){
				var imgsrc=result.photo;
				devClone.find('#imgShow').attr("src","data:image/jpeg;base64,"+imgsrc);
			}
			else{
				alert(result.strError);
			}	
		}
	});	
	
	
	
}

//消费记录查询入场图片
function chargephoto(sel_tr,all_tr,sel_obj_arr,sel_obj){
	if(sel_obj==null){
		wade.libs.alert('请选择车辆',2);
		return;
	}
	var devClone = $("#ChargephotoModel").clone(true);
	devClone.modal({backdrop: 'static', keyboard: false}).on('hidden.bs.modal',function(event){
		$(this).remove();
	});
	
	$.ajax({
		url :'/report/chargePhoto.do',
		type:"post",
		data : {inPhoto:sel_obj.inPhoto},
		success: function(data){
			//result = eval(data);
			result =JSON.parse(data);
			if(result.iRet){
				var imgsrc=result.photo;
				devClone.find('#imgShowin').attr("src","data:image/jpeg;base64,"+imgsrc);
			}
			else{
				alert(result.strError);
			}
			
		}
	});	
	
}

//消费记录查询出场图片
function chargeOutphoto(sel_tr,all_tr,sel_obj_arr,sel_obj){
	if(sel_obj==null){
		wade.libs.alert('请选择车辆',2);
		return;
	}
	var devClone = $("#ChargeOutphotoModel").clone(true);
	devClone.modal({backdrop: 'static', keyboard: false}).on('hidden.bs.modal',function(event){
		$(this).remove();
	});
	
	$.ajax({
		url :'/report/chargeOutPhoto.do',
		type:"post",
		data : {outPhoto:sel_obj.outPhoto},
		success: function(data){
			//result = eval(data);
			result =JSON.parse(data);
			if(result.iRet){
				var imgsrc=result.photo;
				devClone.find('#imgShowout').attr("src","data:image/jpeg;base64,"+imgsrc);
			}
			else{
				alert(result.strError);
			}
			
		}
	});	
	
}


/*$('input[name=startTime]').on('blur',function(){
	$('.point-out').hide();
});
$('input[name=startTime]').on('focus',function(){
	$('.point-out').hide();
});
*/
$('input[name=carNumber]').on('blur',function(){
	$('.point-out').hide();
});
$('input[name=carNumber]').on('focus',function(){
	$('.point-out').hide();
});

