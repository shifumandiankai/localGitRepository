/*$(document).ready(function(){
	$.extend($.datepicker._defaults , { // Default regional settings
		closeText: '确定', // Display text for close link
		prevText: '上月', // Display text for previous month link
		nextText: '下月', // Display text for next month link
		currentText: '今天', // Display text for current month link
		monthNames: ['一月','二月','三月','四月','五月','六月',
			'七月','八月','九月','十月','十一月','十二月'], // Names of months for drop-down and formatting
		monthNamesShort: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'], // For formatting
		dayNames: ['周日','周一', '周二', '周三', '周四', '周五', '周六'], // For formatting
		dayNamesShort: ['日','一', '二', '三', '四', '五', '六',], // For formatting
		dayNamesMin: ['日','一', '二', '三', '四', '五', '六'], // Column headings for days starting at Sunday
		weekHeader: '周', // Column header for week of the year
		dateFormat: 'yy-mm-dd', // See format options on parseDate
		firstDay: 0, // The first day of the week, Sun = 0, Mon = 1, ...
		isRTL: false, // True if right-to-left language, false if left-to-right
		showMonthAfterYear: false, // True if the year select precedes month, false for month then year
		yearSuffix: '' // Additional text to append to the year in the month headers
	});
	
});*/

var wade = wade || {};
wade.role = wade.role || {};
$(".nav-tabs li:first").addClass("active");
$(".tab-content .tab-pane:first").addClass("active");
/*window.onload=function(){
	$('#datareport ul.treeview-menu li:eq(1)').attr('class','active');
};*/
var config1 = new Object();
var config2 = new Object();
var config3 = new Object();
var config4 = new Object();
var config5 = new Object();
var config6 = new Object();

var startTime=new Date().Format('yyyy-MM-dd')+' 00:00:00';
$('input[name="startTime"]').val(startTime);
var endTime=new Date().Format('yyyy-MM-dd')+' 23:59:59';
$('input[name="endTime"]').val(endTime);


//车辆类型绑定
$.ajax({
	 url: "/report/Czreport/configCarType.do",
      dataType: 'json',
      type:'post',
      quietMillis: 250,
      success: function (result) {
    	  $('*[name="carType"]').select2({
       		//dropdownParent:devClone,
       		multiple:true,
       		allowClear: true,
       		data:result
       	});
      	
   	   
      }
});





config1.columns = new Array("insertTime","typeName","czcount","czfee");

config1.id="dayexample";
config1.getUrl="/report/Czreport/selectDayAllListByPage.do";
config1.btns=[
{'id':"10001",'name':'查询',"fun":czdaysel},
{'id':'10002','name':'导出',"fun":exportdaycharge}
	              
              ];
config1.search = new Array(
		{
			'type' : 'search_hidden',
			'placeholder' : null,
			'url' : null,
			'index' : 0
		}
		);

config2.columns = new Array("insertTime","typeName","czcount","czfee");
/*config2.defs = new Array("pfmFeeRecId");*/
config2.id="monthexample";
config2.getUrl="/report/Czreport/selectMonthAllListByPage.do";
config2.btns=[
{'id':"10001",'name':'查询',"fun":czmonthsel},
{'id':'10002','name':'导出',"fun":exportmonthcharge} 
              ];
config2.search = new Array(
		{
			'type' : 'search_hidden',
			'placeholder' : null,
			'url' : null,
			'index' : 0
		}
		);

config3.columns = new Array("insertTime","typeName","czcount","czfee");
/*config3.defs = new Array("pfmFeeRecId");*/
config3.id="yearexample";
config3.getUrl="/report/Czreport/selectYearAllListByPage.do";
config3.btns=[
{'id':"10001",'name':'查询',"fun":czyearsel},
{'id':'10002','name':'导出',"fun":exportyearcharge} 
              ];
config3.search = new Array(
		{
			'type' : 'search_hidden',
			'placeholder' : null,
			'url' : null,
			'index' : 0
		}
		);

//收费日报表
config4.columns = new Array("insertTime","typeName","czcount","czfee");
/*config4.defs = new Array("pfmFeeRecId");*/
config4.id="chargedayexample";
config4.getUrl="/report/Czreport/selectChargeDayAllListByPage.do";
config4.btns=[
{'id':"10001",'name':'查询',"fun":chargedaysel},
{'id':'10002','name':'导出',"fun":exportchargeday} 
              ];
config4.search = new Array(
		{
			'type' : 'search_hidden',
			'placeholder' : null,
			'url' : null,
			'index' : 0
		}
		);

//收费月报表
config5.columns = new Array("insertTime","typeName","czcount","czfee");
/*config5.defs = new Array("pfmFeeRecId");*/
config5.id="chargemonthexample";
config5.getUrl="/report/Czreport/selectChargeMonthAllListByPage.do";
config5.btns=[
{'id':"10001",'name':'查询',"fun":chargemonthsel},
{'id':'10002','name':'导出',"fun":exportchargemonth} 
              ];
config5.search = new Array(
		{
			'type' : 'search_hidden',
			'placeholder' : null,
			'url' : null,
			'index' : 0
		}
		);
//收费年报表
config6.columns = new Array("insertTime","typeName","czcount","czfee");
/*config6.defs = new Array("pfmFeeRecId");*/
config6.id="chargeyearexample";
config6.getUrl="/report/Czreport/selectChargeYearAllListByPage.do";
config6.btns=[
{'id':"10001",'name':'查询',"fun":chargeyearsel},
{'id':'10002','name':'导出',"fun":exportchargeyear} 
              ];
config6.search = new Array(
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
wade.libs.datatable(config6);








//充值日报表查询
function czdaysel(){
	var dev=$('#form1');
	var wapdev=$('#dayexample_wrapper');
	//dev.find('*[name="selectclick"]').val(0);
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
	var jsonuserinfo = dev.serializeObject();
	var formdata =JSON.stringify(jsonuserinfo);
	//alert(formdata);
	wapdev.find('*[name="searchValue0_search_hidden"]').val(formdata);
	wapdev.find('*[name="searchValue0_search_hidden"]').trigger("keyup.DT");
	//$("#" + config1.id).DataTable().draw(false);
	//$("#" + config1.id).DataTable().ajax.reload();
}
//充值日报表导出
function exportdaycharge(sel_tr,all_tr,sel_obj_arr,sel_obj){
	var table = $('#dayexample').DataTable();
	var order = table.order();
	var count=order[0][0];
	var colname=config1.columns[count];
	var dev=$('#form1');
	var wapdev=$('#dayexample_wrapper');
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
	var carval=dev.find('input[name="carNumber"]').val();
	var jsonuserinfo = dev.serializeObject();
	var formdata =JSON.stringify(jsonuserinfo);
	
	wapdev.find('*[name="searchValue0_search_hidden"]').val(formdata);
	wapdev.find('*[name="searchValue0_search_hidden"]').trigger("keyup.DT");
	window.open("/report/Czreport/exportDay/"+formdata+".do");
}
//充值月报表查询
function czmonthsel(){
	var dev=$('#form2');
	var wapdev=$('#monthexample_wrapper');
	//dev.find('*[name="selectclick"]').val(0);
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
	var jsonuserinfo = dev.serializeObject();
	var formdata =JSON.stringify(jsonuserinfo);
	
	wapdev.find('*[name="searchValue0_search_hidden"]').val(formdata);
	wapdev.find('*[name="searchValue0_search_hidden"]').trigger("keyup.DT");
	//$("#" + config2.id).DataTable().ajax.reload();
}
//充值月报表导出
function exportmonthcharge(sel_tr,all_tr,sel_obj_arr,sel_obj){
	var table = $('#monthexample').DataTable();
	var order = table.order();
	var count=order[0][0];
	var colname=config2.columns[count];
	var dev=$('#form2');
	var wapdev=$('#monthexample_wrapper');
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
	var carval=dev.find('input[name="carNumber"]').val();
	var jsonuserinfo = dev.serializeObject();
	var formdata =JSON.stringify(jsonuserinfo);
	wapdev.find('*[name="searchValue0_search_hidden"]').val(formdata);
	wapdev.find('*[name="searchValue0_search_hidden"]').trigger("keyup.DT");
	window.open("/report/Czreport/exportMonth/"+formdata+".do");
}
//充值年报表查询
function czyearsel(){
	var dev=$('#form3');
	var wapdev=$('#yearexample_wrapper');
	//dev.find('*[name="selectclick"]').val(0);
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
	var jsonuserinfo = dev.serializeObject();
	var formdata =JSON.stringify(jsonuserinfo);
	//alert(formdata);
	wapdev.find('*[name="searchValue0_search_hidden"]').val(formdata);
	wapdev.find('*[name="searchValue0_search_hidden"]').trigger("keyup.DT");
	//$("#" + config3.id).DataTable().ajax.reload();
}
//充值年报表导出
function exportyearcharge(){
	var table = $('#yearexample').DataTable();
	var order = table.order();
	var count=order[0][0];
	var colname=config3.columns[count];
	var dev=$('#form3');
	var wapdev=$('#yearexample_wrapper');
	var startTime=dev.find('*[name="startTime"]').val();
	var endTime=dev.find('*[name="endTime"]').val();
	dev.find('*[name="order1"]').val(colname);//列的名字 
	dev.find('*[name="order2"]').val(order[0][1]);//asc或desc
	if(startTime!=''&&endTime!=''&&startTime>endTime){
		var errMsg='开始时间不能大于结束时间';	
		dev.find('.point-out').hide();
		dev.find("#startTimeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
		setTimeout(function(){dev.find('.point-out').hide()},2000);
		return false;
	}
	var carval=dev.find('input[name="carNumber"]').val();
	var jsonuserinfo = dev.serializeObject();
	var formdata =JSON.stringify(jsonuserinfo);
	wapdev.find('*[name="searchValue0_search_hidden"]').val(formdata);
	wapdev.find('*[name="searchValue0_search_hidden"]').trigger("keyup.DT");
	window.open("/report/Czreport/exportYear/"+formdata+".do");
}
//消费日报表查询
function chargedaysel(){
	var dev=$('#form4');
	var wapdev=$('#chargedayexample_wrapper');
	//dev.find('*[name="selectclick"]').val(0);
	var startTime=dev.find('*[name="startTime"]').val();
	var endTime=dev.find('*[name="endTime"]').val();
	if(startTime!=''&&endTime!=''&&startTime>endTime){
		var errMsg='开始时间不能大于结束时间';	
		dev.find('.point-out').hide();
		dev.find("#startTimeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
		setTimeout(function(){dev.find('.point-out').hide()},2000);
		return false;
	}
	var carval=dev.find('input[name="carNumber"]').val();
	var jsonuserinfo = dev.serializeObject();
	var formdata =JSON.stringify(jsonuserinfo);
	
	wapdev.find('*[name="searchValue0_search_hidden"]').val(formdata);
	wapdev.find('*[name="searchValue0_search_hidden"]').trigger("keyup.DT");
	//$("#" + config4.id).DataTable().ajax.reload();
}
//消费日报表导出
function exportchargeday(){
	var table = $('#chargedayexample').DataTable();
	var order = table.order();
	var count=order[0][0];
	var colname=config4.columns[count];
	var dev=$('#form4');
	var wapdev=$('#chargedayexample_wrapper');
	var startTime=dev.find('*[name="startTime"]').val();
	var endTime=dev.find('*[name="endTime"]').val();
	dev.find('*[name="order1"]').val(colname);//列的名字 
	dev.find('*[name="order2"]').val(order[0][1]);//asc或desc
	if(startTime!=''&&endTime!=''&&startTime>endTime){
		var errMsg='开始时间不能大于结束时间';	
		dev.find('.point-out').hide();
		dev.find("#startTimeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
		setTimeout(function(){dev.find('.point-out').hide()},2000);
		return false;
	}
	var carval=dev.find('input[name="carNumber"]').val();
	var jsonuserinfo = dev.serializeObject();
	var formdata =JSON.stringify(jsonuserinfo);
	wapdev.find('*[name="searchValue0_search_hidden"]').val(formdata);
	wapdev.find('*[name="searchValue0_search_hidden"]').trigger("keyup.DT");
	window.open("/report/Czreport/exportChargeDay/"+formdata+".do");
}
//消费月报表查询
function chargemonthsel(){
	var dev=$('#form5');
	var wapdev=$('#chargemonthexample_wrapper');
	//dev.find('*[name="selectclick"]').val(0);
	var startTime=dev.find('*[name="startTime"]').val();
	var endTime=dev.find('*[name="endTime"]').val();
	if(startTime!=''&&endTime!=''&&startTime>endTime){
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
	//$("#" + config5.id).DataTable().ajax.reload();
}
//消费月报表导出
function exportchargemonth(){
	var table = $('#chargemonthexample').DataTable();
	var order = table.order();
	var count=order[0][0];
	var colname=config5.columns[count];
	var dev=$('#form5');
	var wapdev=$('#chargemonthexample_wrapper');
	var startTime=dev.find('*[name="startTime"]').val();
	var endTime=dev.find('*[name="endTime"]').val();
	dev.find('*[name="order1"]').val(colname);//列的名字 
	dev.find('*[name="order2"]').val(order[0][1]);//asc或desc
	if(startTime!=''&&endTime!=''&&startTime>endTime){
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
	window.open("/report/Czreport/exportChargeMonth/"+formdata+".do");
}
//消费年报表查询
function chargeyearsel(){
	var dev=$('#form6');
	var wapdev=$('#chargeyearexample_wrapper');
	//dev.find('*[name="selectclick"]').val(0);
	var startTime=dev.find('*[name="startTime"]').val();
	var endTime=dev.find('*[name="endTime"]').val();
	if(startTime!=''&&endTime!=''&&startTime>endTime){
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
	//$("#" + config6.id).DataTable().ajax.reload();
}
//消费年报表导出
function exportchargeyear(){
	var table = $('#chargeyearexample').DataTable();
	var order = table.order();
	var count=order[0][0];
	var colname=config6.columns[count];
	var dev=$('#form6');
	var wapdev=$('#chargeyearexample_wrapper');
	var startTime=dev.find('*[name="startTime"]').val();
	var endTime=dev.find('*[name="endTime"]').val();
	dev.find('*[name="order1"]').val(colname);//列的名字 
	dev.find('*[name="order2"]').val(order[0][1]);//asc或desc
	if(startTime!=''&&endTime!=''&&startTime>endTime){
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
	window.open("/report/Czreport/exportChargeYear/"+formdata+".do");
}


//光标锁定隐藏提示
/*$('*[name="startTime"]').on('blur',function(){
	$('.point-out').hide();
});
$('*[name="startTime"]').on('focus',function(){
	$('.point-out').hide();
});*/
//车牌光标锁定隐藏提示
$('*[name="carNumber"]').on('blur',function(){
	$('.point-out').hide();
});

/*$('*[name="endTime"]').on('blur',function(){
	$('.point-out').hide();
});
$('*[name="endTime"]').on('focus',function(){
	$('.point-out').hide();
});*/

