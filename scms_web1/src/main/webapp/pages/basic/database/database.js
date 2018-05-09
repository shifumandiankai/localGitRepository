var config = $.extend({},{
	btns: new Array(
			{'id':"10003",'name':'del',"fun":null},
			{'id':"10003",'name':'备份',"fun":fun_export},
			{'id':"10003",'name':'还原',"fun":fun_import},
			{'id':"10003",'name':'配置',"fun":fun_peizhi}),
			search:new Array(), //查询条件
			id:"t_database",	//dataTable ID
			getUrl:"/auth/database/getdatabaseinfo.do",	//获取数据请求路径
			delUrl:"/auth/database/delete.do",	//删除数据请求路径
			columns: new Array("no","backUpTime","fileSize","fileName"),
			defs:new Array("fileName"),
});	
wade.libs.datatable(config);	//初始化datatable
//备份数据库
function fun_export(){
	$.ajax({
		url:"/auth/database/backup.do",
		type:'get',
		dataType:'json',
		success:function(data){
			if(data["success"]){
				wade.libs.alert("备份成功" );
				$("#" + config.id).DataTable().draw(false); 

			}else{
				wade.libs.alert(data.msg,0 );
		}
	}
});
}
function fun_import(sel_tr,all_tr,sel_obj_arr,sel_obj){
	
	if(sel_obj==null){
		wade.libs.alert("请选择要恢复的库文件",2);
		return;
	}
	
	var formdata = "condition=" + JSON.stringify(sel_obj);	
	$.ajax({
		url:"/auth/database/restore.do",
		type:'get',
		dataType:'json',
		data:formdata,
		success:function(data){
			if(data["success"]){
				wade.libs.alert("还原成功");
			
			}else{
				wade.libs.alert(data.msg,0);
			}
		}
	});
	
	
}
function fun_peizhi(){
	var devClone = $("#dataconf").clone(true);
	devClone.on('hidden.bs.modal',function(event){
		$(this).remove();
	});

    devClone.find("[name=bankUpStartTime]").attr('id','begintime');
    devClone.find("[name=bankUpEndTime]").attr('id','endtime');
	var checked="1";
	devClone.find(":radio").on("click",function(){
		if(checked==this.value){
			return;
		}
		switch(this.value){
		case "1":{
			devClone.find("#cycle").show();
			devClone.find("#interval").hide();
			devClone.find("#day").hide();
			devClone.find("#week").hide();
			checked="1";
		}break;
		case "2":{
			devClone.find("#cycle").hide();
			devClone.find("#interval").hide();
			devClone.find("#day").hide();
			devClone.find("#week").show();
			checked="2";
		}break;
		case "3":{
			devClone.find("#cycle").hide();
			devClone.find("#interval").hide();
			devClone.find("#day").show();
			devClone.find("#week").hide();
			checked="3";
		}break;
		case "4":{
			devClone.find("#cycle").hide();
			devClone.find("#interval").show();
			devClone.find("#day").hide();
			devClone.find("#week").hide();
			checked="4";
		}break;
		}
	});
	devClone.find("#all").click(function(){
		if(this.checked){
			devClone.find("[name=monthPlanExecuteTime]").prop("checked","checked");
			
		}
		else{
			devClone.find("[name=monthPlanExecuteTime]").prop("checked",false);
		}
	});
	devClone.find("[name='monthPlanExecuteTime']").click(function(){
		if(this.checked){
			if(devClone.find("[name='monthPlanExecuteTime']:checked").size()==32){
				devClone.find("#all").prop("checked",true);
			}
		}
		else{
			devClone.find("#all").prop("checked",false);
		}
	});
	
	devClone.find("#submit").on("click",function(){
		var val1 = devClone.find('input[name=maxFileQuantity]').val();
		if (!verifymaxFileQuantity(val1)) {
		    return false;
		}
		var val2 = devClone.find('input[name=maxFileTotalSize]').val();
		if (!verifymaxFileTotalSize(val2)) {
			return false;
		}
		var val3 = devClone.find('input[name=bankUpExecuteTime]').val();
		if(!verifybankUpExecuteTime(val3)){
			return false;
		}
		var val4 = devClone.find('input[name=bankUpStartTime]').val();
		if(!verifybankUpStartTime(val4)){
			return false;
		}
		var val5 = devClone.find('input[name=bankUpEndTime]').val();
		if(!verifybankUpEndTime(val5)){
			return false;
		}
		
		var plantype=devClone.find("[name=planType]:checked").val();
		
		switch(plantype){
		case "1":
			var val3 = devClone.find('input[name=dayPlanExecuteInterval]').val();
			if (!verifydayPlanExecuteInterval(val3)) {
				return false;
			}
			break;
		case "2":
			if(!devClone.find("[name=weekPlanExecuteTime]:checked").length){
				alert("请至少选择一天")
				return false;
			}
			break;
		case "3":
			if(!devClone.find("[name=monthPlanExecuteTime]:checked").length){
				alert("请至少选择一天")
				return false;
			}
			break;
		case "4":
			var val4 = devClone.find('input[name=intervalPlanExecuteTime]').val();
			if (!verifyintervalPlanExecuteTime(val4)) {
				return false;
			}
			break;
		}
		var forminfo = devClone.find("form:first").serializeObject(); 
		if(forminfo.weekPlanExecuteTime){
			if(!forminfo.weekPlanExecuteTime.push){
				forminfo.weekPlanExecuteTime=[forminfo.weekPlanExecuteTime];
			}
		}
		if(forminfo.monthPlanExecuteTime){
			if(!forminfo.monthPlanExecuteTime.push){
				forminfo.monthPlanExecuteTime=[forminfo.monthPlanExecuteTime];
			}
		}
		
        var formdata = "condition=" + JSON.stringify(forminfo);

		$.ajax({
			url:"/auth/database/update.do",
			type:"post",
			data:formdata,
			success:function(data){
				var result = JSON.parse(data);
				if(result.success){
					wade.libs.alert('修改成功');
					devClone.modal('hide');	
				}
				else{
					wade.libs.alert(result.msg,0 ,devClone.find('div:first'));	
				}
			}
		});
	
	});
	
	//////////////////////////////验证、、、、、、、、、、、、、、、、、、、、、、
	
	devClone.find("[name=maxFileQuantity]").on('change keyup click',
		    function() {
		        devClone.find("#maxFileQuantityNotice").hide();
		    });
	devClone.find("[name=maxFileQuantity]").on('blur',function(){
		var val = $(this).val();
		verifymaxFileQuantity(val);
		
	});
	function verifymaxFileQuantity(val){
		if (val.trim()=="") {
			devClone.find('.point-out').hide();
			devClone.find("#maxFileQuantityNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + "不能为空" + '</span>').show();
			
			return false;
		}
		else if(val.indexOf(" ")!=-1){
			devClone.find('.point-out').hide();
			devClone.find("#maxFileQuantityNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + "不能含空格" + '</span>').show();
			
			return false;
		}else if(!(/^[1-9]$|^[1-5][0-9]$|^[6][0-4]$/.test(val))){
			devClone.find('.point-out').hide();
			devClone.find("#maxFileQuantityNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + "请输入1-64的整数" + '</span>').show();
			
			return false;
		}
		return true;
	}
	
	devClone.find("[name=maxFileTotalSize]").on('change keyup click',
		    function() {
		        devClone.find("#maxFileTotalSizeNotice").hide();
		    });
	devClone.find("[name=maxFileTotalSize]").on('blur',function(){
		var val = $(this).val();
		verifymaxFileTotalSize(val);
		
	});
	function verifymaxFileTotalSize(val){
		if (val.trim()=="") {
			devClone.find('.point-out').hide();
			devClone.find("#maxFileTotalSizeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + "不能为空" + '</span>').show();
			
			return false;
		}
		else if(val.indexOf(" ")!=-1){
			devClone.find('.point-out').hide();
			devClone.find("#maxFileTotalSizeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + "不能含空格" + '</span>').show();
			
			return false;
		}else if(!(/^[1-9][\d]{0,4}$/.test(val))){
			
			
			devClone.find('.point-out').hide();
			devClone.find("#maxFileTotalSizeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + "请输入1-65535的整数" + '</span>').show();
			
			return false;
			
		}else if(Number(val)<1||Number(val)>65535){
			devClone.find('.point-out').hide();
			devClone.find("#maxFileTotalSizeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + "请输入1-65535的整数" + '</span>').show();
			
			return false;
		}
		return true;
	}
	devClone.find("[name=bankUpExecuteTime]").on('change keyup click',
		    function() {
		        devClone.find("#bankUpExecuteTimeNotice").hide();
		    });
	devClone.find("[name=bankUpExecuteTime]").on('blur',function(){
		var val = $(this).val();
		verifybankUpExecuteTime(val);
		
	});
	function verifybankUpExecuteTime(val){
		if (val=="") {
			devClone.find('.point-out').hide();
			devClone.find("#bankUpExecuteTimeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + "不能为空" + '</span>').show();
			
			return false;
		}
		
		return true;
	}
	//开始时间
	devClone.find("[name=bankUpStartTime]").on('change keyup click',
		    function() {
		        devClone.find("#bankUpStartTimeNotice").hide();
		    });
	devClone.find("[name=bankUpStartTime]").on('blur',function(){
		var val = $(this).val();
		verifybankUpStartTime(val);
		
	});
	function verifybankUpStartTime(val){
		if (val=="") {
			devClone.find('.point-out').hide();
			devClone.find("#bankUpStartTimeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + "不能为空" + '</span>').show();
			
			return false;
		}
		
		return true;
	}
	//结束时间
	devClone.find("[name=bankUpEndTime]").on('change keyup click',
		    function() {
		        devClone.find("#bankUpEndTimeNotice").hide();
		    });
	devClone.find("[name=bankUpEndTime]").on('blur',function(){
		var val = $(this).val();
		verifybankUpEndTime(val);
		
	});
	function verifybankUpEndTime(val){
		if (val=="") {
			devClone.find('.point-out').hide();
			devClone.find("#bankUpEndTimeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + "不能为空" + '</span>').show();
			
			return false;
		}
		
		return true;
	}
	devClone.find("[name=dayPlanExecuteInterval]").on('change keyup click',
		    function() {
		        devClone.find("#dayPlanExecuteIntervalNotice").hide();
		    });
	devClone.find("[name=dayPlanExecuteInterval]").on('blur',function(){
		var val = $(this).val();
		verifydayPlanExecuteInterval(val);
		
	});
	function verifydayPlanExecuteInterval(val){
		if (val.trim()=="") {
			devClone.find('.point-out').hide();
			devClone.find("#dayPlanExecuteIntervalNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + "不能为空" + '</span>').show();
			return false;
		}
		else if(val.indexOf(" ")!=-1){
			devClone.find('.point-out').hide();
			devClone.find("#dayPlanExecuteIntervalNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + "不能含空格" + '</span>').show();
			
			return false;
		}else if(!(/^[1-9][\d]{0,1}$/.test(val))){
			devClone.find('.point-out').hide();
			devClone.find("#dayPlanExecuteIntervalNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + "请输入1-31的整数" + '</span>').show();
			
			return false;
		}else if(Number(val)<1||Number(val>31)){
			devClone.find('.point-out').hide();
			devClone.find("#dayPlanExecuteIntervalNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + "请输入1-31的整数" + '</span>').show();
			
			return false;
		}
		return true;
	}
	devClone.find("[name=intervalPlanExecuteTime]").on('change keyup click',
		    function() {
		        devClone.find("#intervalPlanExecuteTimeNotice").hide();
		    });
	devClone.find("[name=intervalPlanExecuteTime]").on('blur',function(){
		var val = $(this).val();
		verifyintervalPlanExecuteTime(val);
		
	});
	function verifyintervalPlanExecuteTime(val){
		if (val.trim()=="") {
			devClone.find('.point-out').hide();
			devClone.find("#intervalPlanExecuteTimeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + "不能为空" + '</span>').show();
			
			return false;
		}
		else if(val.indexOf(" ")!=-1){
			devClone.find('.point-out').hide();
			devClone.find("#intervalPlanExecuteTimeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + "不能含空格" + '</span>').show();
			
			return false;
		}else if(!(/^[1-9][\d]{0,4}$/.test(val))){
			devClone.find('.point-out').hide();
			devClone.find("#intervalPlanExecuteTimeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + "请输入1-10000的整数" + '</span>').show();
			
			return false;
		}else if(Number(val)<1||Number(val)>10000){
			devClone.find('.point-out').hide();
			devClone.find("#intervalPlanExecuteTimeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + "请输入1-10000的整数" + '</span>').show();
			
			return false;
		}
		return true;
	}
	
	
	
	//init
	$.ajax({
		url:"/auth/database/get.do",
		dataType:'json',
		success:function(data){
		$.each(data,function(index,value){
			
			if(value.bimSysParamsId==1){
				devClone.find("[name=maxFileQuantity]").val(value.intValue);
			}else if(value.bimSysParamsId==2){
				devClone.find("[name=maxFileTotalSize]").val(value.intValue);
				
			}else if(value.bimSysParamsId==3){
				devClone.find("[name=planType][value="+value.intValue+"]").trigger("click");
			}else if(value.bimSysParamsId==4){
				devClone.find("[name=bankUpStartTime]").val(value.strValue);
	
			}else if(value.bimSysParamsId==5){
				devClone.find("[name=bankUpEndTime]").val(value.strValue);
			}else if(value.bimSysParamsId==6){
				devClone.find("[name=bankUpExecuteTime]").val(value.strValue);
			}else if(value.bimSysParamsId==7){
				devClone.find("[name=dayPlanExecuteInterval]").val(value.intValue);
			}else if(value.bimSysParamsId==8){
				var array1 = value.strValue.split(",");
				for(var i=0;i<array1.length-1;i++){
					
				devClone.find("[name=weekPlanExecuteTime][value="+array1[i]+"]").prop("checked",true);
				}
			}else if(value.bimSysParamsId==9){
				var array2 = value.strValue.split(",");
				for(var i=0;i<array2.length-1;i++){
				devClone.find("[name=monthPlanExecuteTime][value="+array2[i]+"]").prop("checked",true);
				}
			}else if(value.bimSysParamsId==10){
				devClone.find("[name=intervalPlanExecuteTime]").val(value.intValue);
			}
			
			
		});
		switch(devClone.find("[name=planType]:checked").val()){
		case "1":
			devClone.find("[name=weekPlanExecuteTime]").prop("checked",false);
			devClone.find("[name=monthPlanExecuteTime]").prop("checked",false);
			devClone.find("[name=intervalPlanExecuteTime]").val("");
			break;
		case "2":
			devClone.find("[name=dayPlanExecuteInterval]").val("");
			devClone.find("[name=monthPlanExecuteTime]").prop("checked",false);
			devClone.find("[name=intervalPlanExecuteTime]").val("");
			break;
		case "3":
			devClone.find("[name=dayPlanExecuteInterval]").val("");
			devClone.find("[name=weekPlanExecuteTime]").prop("checked",false);
			devClone.find("[name=intervalPlanExecuteTime]").val("");
			break;
		case "4":
			devClone.find("[name=dayPlanExecuteInterval]").val("");
			devClone.find("[name=dayPlanExecuteInterval]").val("");
			devClone.find("[name=weekPlanExecuteTime]").prop("checked",false);
			break;
		}
	
		}
		
	});

	devClone.modal({backdrop: 'static', keyboard: false});
	
}