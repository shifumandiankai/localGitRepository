var wade = wade || {};
wade.time = wade.time || {};
var timeconfig = new Object();

/*window.onload=function(){
	$('#parkpotsetting').attr('class','treeview active');
	$('ul.treeview-menu li:eq(1)').attr('class','active');
};*/


//需要显示的按钮
timeconfig.btns = new Array({
	'id' : '10001',
	'name' : 'add',
	'fun' : null
}, {
	'id' : '10002',
	'name' : 'edit',
	"fun" : null
}, {
	'id' : "10003",
	'name' : 'del',
	"fun" : null
});
//查询控件 type:控件类型 placeholder:显示值 url: 获取数据的请求路径 index参数序号 最多不超过总列数
timeconfig.search = new Array(
// {'type':'multiple_select','placeholder':'多选','url':'/role/GetSelectDataTest.do','index':2}
// //select 多选
{
	'type' : 'search',
	'placeholder' : '时段名称',
	'url' : null,
	'index' : 0
});

timeconfig.add_shown =function(devClone,operId,sel_obj){
	 
	
	devClone.find('.time').timepicker({
		 showInputs: false,
         minuteStep: 1,
         showMeridian: false
        
		});
	 
	 //修改信息时让checkbox默认选择
	 if(operId==1){
		 var array=new Array();
		 array=sel_obj.enableWeek.split(",");
		for(var i=0;i<array.length;i++){
			if(array[i]=='每天'){
				devClone.find('*[name="list"]').prop("checked",true);
				devClone.find('input[name="list"]').each(function(){
					
						 $(this).prop("checked",true);
						 
					 
				 });
				
			}
			else{ devClone.find('input[name="list"]').each(function(){
				
				 var val=$(this).val();
				 if(val==array[i]){
					 $(this).prop("checked",true);
					 
					
				 }
				 
				
				
				
				
			 });
		}
		}
		if(sel_obj.dayLimit==0){
			devClone.find('#dayLimit1').attr('checked',true);
			devClone.find('input[name="strBeginTime1"]').attr('disabled',false);
			 devClone.find('input[name="strBeginTime2"]').attr('disabled',false); 
			 devClone.find('input[name="strBeginTime3"]').attr('disabled',false); 
			 devClone.find('input[name="strBeginTime4"]').attr('disabled',false); 
			 devClone.find('input[name="strEndTime1"]').attr('disabled',false); 
			 devClone.find('input[name="strEndTime2"]').attr('disabled',false); 
			 devClone.find('input[name="strEndTime3"]').attr('disabled',false); 
			 devClone.find('input[name="strEndTime4"]').attr('disabled',false); 
		}
		else{
			devClone.find('#dayLimit2').attr('checked',true);
			 devClone.find('input[name="strBeginTime1"]').attr('disabled',true);
			 devClone.find('input[name="strBeginTime2"]').attr('disabled',true); 
			 devClone.find('input[name="strBeginTime3"]').attr('disabled',true); 
			 devClone.find('input[name="strBeginTime4"]').attr('disabled',true); 
			 devClone.find('input[name="strEndTime1"]').attr('disabled',true); 
			 devClone.find('input[name="strEndTime2"]').attr('disabled',true); 
			 devClone.find('input[name="strEndTime3"]').attr('disabled',true); 
			 devClone.find('input[name="strEndTime4"]').attr('disabled',true);
		}
		
		
	   if(sel_obj.enableIn==0){
			devClone.find('input[name="enableIn"]').prop('checked',true);
		}
		else{
			devClone.find('input[name="enableIn"]').prop('checked',false);
		}
		if(sel_obj.enableOut==0){
			devClone.find('input[name="enableOut"]').prop('checked',true);
		}
		else{
			devClone.find('input[name="enableOut"]').prop('checked',false);
		}
		
		devClone.find('input[name="result"]').attr('disabled',true);
		 }
	
	 //点击全选按钮
	 devClone.find("#list").on("click",function(){
		 
		 //选中每天
		if($(this).is(":checked")){
			devClone.find('*[name="list"]').prop("checked",true);
		}
		//不选每天
		else{
			devClone.find('*[name="list"]').prop("checked",false);
		}
			
	 });
	 
	 devClone.find('#submit').on('click',registerUser);	//绑定用户添加或修改事件
	 //时段名称光标移走后验证
	 devClone.find("input[name=timeName]").on("blur",function(){
		 
		
		 
		 var timename=devClone.find("input[name=timeName]").val();
		
		 
		 var msg=checkAllName(timename,'时段编号');
		 if(msg!=100){
			 devClone.find('.point-out').hide();
			 devClone.find("#timeNameNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + msg + '</span>').show();	  
		 }
		 
	 });
	 devClone.find("input[name=timeName]").on("focus",function(){
		 devClone.find("#timeNameNotice").hide(); 
	 });
	 
	 //时段名称光标锁定
	 devClone.find("input[name=timeName]").on('change, keyup',function(){
		 devClone.find("#timeNameNotice").hide();
	 });
	 
	 //时段编号光标移走后验证
	 devClone.find("input[name=timeCode]").on("blur",function(){
		
		 
		 var timecode=devClone.find("input[name=timeCode]").val();
		 /*if(timecode==''){
			 msg='时段编号不能为空';
			 devClone.find('.point-out').hide();
			 devClone.find("#timeCodeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + msg + '</span>').show();
				 
		 }
		 if(timecode.length>7){
			 msg='时段编号长度不能超过七个字';
			 devClone.find('.point-out').hide();
			 devClone.find("#timeCodeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + msg + '</span>').show();
				
		 }*/
		 
			errMsg=checkAllName(username,'时段编号');
            if(errMsg!=100){
   			 devClone.find('.point-out').hide();
   			 devClone.find("#timeCodeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + msg + '</span>').show();
            }
		 
	 });
	 devClone.find("input[name=timeCode]").on("focus",function(){
		 devClone.find("#timeCodeNotice").hide(); 
	 });
	 
	 
	 
	 //时段编号光标锁定
	 devClone.find("input[name=timeCode]").on('change, keyup',function(){
		 devClone.find("#timeCodeNotice").hide();
	 });
	/* devClone.find("input[name=strbeginTime]").on("blur",function(){
		 
		 var begintime=devClone.find("input[name=strbeginTime]").val();
		 if(checkAllName(begintime,'开始时间')!=100){
       	  msg=checkAllName(begintime,'开始时间');
       	
       	   devClone.find('.point-out').hide();
			 devClone.find("#beginTimeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + msg + '</span>').show();
		     setTimeout(function(){
					 devClone.find("#beginTimeNotice").hide();
					},1500);
			
       }
        
		 
		 
		 
		 
		 
	 });*/
	 
	 //开始日期光标锁定
	 devClone.find("input[name=strbeginTime]").on('change, keyup',function(){
		 devClone.find("#beginTimeNotice").hide();
	 });
	 //结束日期光标移走后验证
	 /*devClone.find("input[name=strendTime]").on("blur",function(){	
		 
		 var endtime=devClone.find("input[name=strendTime]").val();	
		 if(checkAllName(endtime,'结束时间')!=100){
        	 msg=checkAllName(endtime,'结束时间');
        	
        	devClone.find('.point-out').hide();
			devClone.find("#endTimeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + msg + '</span>').show();
			setTimeout(function(){
				 devClone.find("#endTimeNotice").hide();
				},1500);
			
		 }
		
		 
	 });*/
	 //结束日期光标锁定
	 devClone.find("input[name=strendTime]").on('keyup',function(){
		
		 devClone.find("#endTimeNotice").hide();
	 });
 //启用日期光标锁定
	 
	 devClone.find("input[name=list]").on('change',function(){
		 //alert(0);
		 devClone.find("#listNotice").hide();
	 });	
	  
	 devClone.find('input[name=strBeginTime1]').on('mousemove',function(){
		 devClone.find('.point-out ').hide();
	 });
	 
	 //点击时间限时按钮
	devClone.find('*[name="result"]').on("click",function(){
			
		 var val=$(this).val();
		
		 if(val==0){
			/// devClone.find('*[name="beginTime"]').attr("disabled",false);
			 //devClone.find('*[name="endTime"]').attr("disabled",false); 
			 devClone.find('input[name="strBeginTime1"]').attr('disabled',false);
			 devClone.find('input[name="strBeginTime2"]').attr('disabled',false); 
			 devClone.find('input[name="strBeginTime3"]').attr('disabled',false); 
			 devClone.find('input[name="strBeginTime4"]').attr('disabled',false); 
			 devClone.find('input[name="strEndTime1"]').attr('disabled',false); 
			 devClone.find('input[name="strEndTime2"]').attr('disabled',false); 
			 devClone.find('input[name="strEndTime3"]').attr('disabled',false); 
			 devClone.find('input[name="strEndTime4"]').attr('disabled',false); 
		 }
		 else{
			 devClone.find('.point-out').hide();
			 //devClone.find('*[name="beginTime"]').attr("disabled","disabled");
			 //devClone.find('*[name="endTime"]').attr("disabled","disabled");
			 devClone.find('input[name="strBeginTime1"]').attr('disabled',true);
			 devClone.find('input[name="strBeginTime2"]').attr('disabled',true); 
			 devClone.find('input[name="strBeginTime3"]').attr('disabled',true); 
			 devClone.find('input[name="strBeginTime4"]').attr('disabled',true); 
			 devClone.find('input[name="strEndTime1"]').attr('disabled',true); 
			 devClone.find('input[name="strEndTime2"]').attr('disabled',true); 
			 devClone.find('input[name="strEndTime3"]').attr('disabled',true); 
			 devClone.find('input[name="strEndTime4"]').attr('disabled',true);
		 }
	 });
		 
	 //校验
	 function registerUser(event){
		 
		 var msg=null;
		 //var re=/^(0\d{1}|1\d{1}|2[0-3]):[0-5]\d{1}:([0-5]\d{1})$/;
		 var re=/^(0\d{1}|1\d{1}|2[0-3]):[0-5]\d{1}$/;
		 var re2=/^[1-2]{1}([0-9]{3})-([0-1]{1})([0-9]{1})-(([0-2]){1}([0-9]{1})|([3]{1}[0-1]{1}))$/;
		 var re3=/^(([1-9]{1})|([0-1][0-9])|([1-2][0-3])):([0-5][0-9])$/;
		 var timename=devClone.find("input[name=timeName]").val();
		 var timecode=devClone.find("input[name=timeCode]").val();
		 var begintime=devClone.find("input[name=strbeginTime]").val();
		 var endtime=devClone.find("input[name=strendTime]").val();
		 var dayBeginTime1=devClone.find("input[name=strBeginTime1]").val();
		 var dayEndTime1=devClone.find("input[name=strEndTime1]").val();
		 var dayBeginTime2=devClone.find("input[name=strBeginTime2]").val();
		 var dayEndTime2=devClone.find("input[name=strEndTime2]").val();
		 var dayBeginTime3=devClone.find("input[name=strBeginTime3]").val();
		 var dayEndTime3=devClone.find("input[name=strEndTime3]").val();
		 var dayBeginTime4=devClone.find("input[name=strBeginTime4]").val();
		 var dayEndTime4=devClone.find("input[name=strEndTime4]").val();
		 
		 //errMsg=checkAllName(timecode,'时段编号');
         if(checkAllName(timecode,'时段编号')!=100){
        	  msg=checkAllName(timecode,'时段编号');
			 devClone.find('.point-out').hide();
			 devClone.find("#timeCodeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + msg + '</span>').show();
			 event.stopImmediatePropagation(); //阻止提交数据
			 return false;
         }
		 if(checkAllName(timename,'时段名称')!=100){
			  msg=checkAllName(timename,'时段名称');
			 devClone.find('.point-out').hide();
			 devClone.find("#timeNameNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + msg + '</span>').show();
			 event.stopImmediatePropagation(); //阻止提交数据
			 return false;
		 }
		 
	         
	        if(checkAllName(begintime,'开始时间')!=100){
	        	  msg=checkAllName(begintime,'开始时间');
	        	
	        	 devClone.find('.point-out').hide();
				 devClone.find("#beginTimeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + msg + '</span>').show();
			     setTimeout(function(){
						 devClone.find("#beginTimeNotice").hide();
						},1500);
				 event.stopImmediatePropagation(); //阻止提交数据
				 return false;
	        }else if(checkAllName(endtime,'结束时间')!=100){
	        	 msg=checkAllName(endtime,'结束时间');
	        	
	        	devClone.find('.point-out').hide();
				devClone.find("#endTimeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + msg + '</span>').show();
				setTimeout(function(){
					 devClone.find("#endTimeNotice").hide();
					},1500);
				event.stopImmediatePropagation(); //阻止提交数据
				return false;
	        }else if(checkStartAndEndTime(begintime,endtime)!=100){
	           msg=checkStartAndEndTime(begintime,endtime);
	        	 //alert(msg);
	        	devClone.find('.point-out').hide();
				devClone.find("#beginTimeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + msg + '</span>').show();
			    setTimeout(function(){
						 devClone.find("#beginTimeNotice").hide();
						},1500);
				event.stopImmediatePropagation(); //阻止提交数据
				return false;
	        }else if(checkStartAndEndTime(dayBeginTime1,dayEndTime1)!=100){
	        	msg='开始时间不能大于结束时间';
				 devClone.find('.point-out').hide();
					devClone.find("#dayBeginTime1Notice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + msg + '</span>').show();
					setTimeout(function(){
						 devClone.find("#dayBeginTime1Notice").hide();
						},1500);
					event.stopImmediatePropagation(); //阻止提交数据
					  return false;
	        }else if(checkStartAndEndTime(dayBeginTime2,dayEndTime2)!=100){
	        	msg='开始时间不能大于结束时间';
				 devClone.find('.point-out').hide();
					devClone.find("#dayBeginTime2Notice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + msg + '</span>').show();
					setTimeout(function(){
						 devClone.find("#dayBeginTime2Notice").hide();
						},1500);
					event.stopImmediatePropagation(); //阻止提交数据
					  return false;
	        }else if(checkStartAndEndTime(dayBeginTime3,dayEndTime3)!=100){
	        	msg='开始时间不能大于结束时间';
				 devClone.find('.point-out').hide();
					devClone.find("#dayBeginTime3Notice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + msg + '</span>').show();
					setTimeout(function(){
						 devClone.find("#dayBeginTime3Notice").hide();
						},1500);
					event.stopImmediatePropagation(); //阻止提交数据
					  return false;
	        }else if(checkStartAndEndTime(dayBeginTime4,dayEndTime4)!=100){
	        	msg='开始时间不能大于结束时间';
				devClone.find('.point-out').hide();
				devClone.find("#dayBeginTime4Notice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + msg + '</span>').show();
				setTimeout(function(){
						 devClone.find("#dayBeginTime4Notice").hide();
						},1500);
				event.stopImmediatePropagation(); //阻止提交数据
				return false;
	        }
	         
	        
	        
	        
		 
		 if(!devClone.find("input[name=list]").is(':checked')){
			 
			 msg='请选择启用日期';
			 devClone.find('.point-out').hide();
				devClone.find("#listNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + msg + '</span>').show();
				 event.stopImmediatePropagation(); //阻止提交数据
				  return false;
		 }
		
		
	 }

	
};

timeconfig.customDialog=function(sel_obj_arr){
	//alert(0);
	var flag=true;
	$.each(sel_obj_arr,function(index,value){
		if(value.timeId!=null){
			wade.libs.dialog('当前时间段已绑定'+value.entranceName+'出入口，请先移除该出入口中的时段');
			flag=false;
			return flag;
		}
		
	});
	return flag;
	
}






timeconfig.id = "timetable"; // table id
timeconfig.addDiv = "timeModal"; // 新增弹出的层 ID
timeconfig.editDiv = "timeModal"; // 编辑 弹出层 ID
timeconfig.getUrl = "/time/getTimeInfo.do"; // ajax获取数据请求地址
timeconfig.delUrl = "/time/delTimeInfo.do"; // ajax删除角色请求地址
timeconfig.saveUrl = "/time/saveTimeInfo.do"; // 保存请求地址
//需要绑定数据的列
//config1.columns = new Array("pfmTimeId", "timeCode", "timeName","strweek","result");
timeconfig.columns = new Array("pfmTimeId", "timeCode", "timeName","enableWeek","timeId","entranceName");
//需要隐藏的列
timeconfig.defs = new Array("pfmTimeId","timeId","entranceName");

wade.libs.datatable(timeconfig);