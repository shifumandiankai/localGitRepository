var wade = wade || {};
wade.role = wade.role || {};
$(".nav-tabs li:first").addClass("active");
$(".tab-content .tab-pane:first").addClass("active");
var config1 = new Object();//LPR管理
var config2 = new Object();//显示屏设备
//需要显示的按钮
config1.btns = new Array({
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
}
);
//查询控件 type:控件类型 placeholder:显示值 url: 获取数据的请求路径 index参数序号 最多不超过总列数
config1.search = new Array(

{
	'type' : 'search',
	'placeholder' : '编号/IP',
	'url' : null,
	'index' : 0
});
config1.id = "LPRexample"; // table id
config1.addDiv = "exampleModal"; // 新增弹出的层 ID
config1.editDiv = "exampleModal"; // 编辑 弹出层 ID
config1.getUrl ="/devicelpr/getdevicelprInfo.do"; // ajax获取数据请求地址
config1.saveUrl = "/devicelpr/savedevicelprInfo.do"; // 保存请求地址
config1.delUrl = "/devicelpr/deldevicelprInfo.do"; // 保存请求地址
//需要绑定数据的列
config1.columns = new Array("pfmDeviceLprId", "deviceCode", "deviceName",
		"ip", "port","serverName","loginId","loginPassword","insertTime","pfmServerId","pfmScreenId","deviceId","carriagewayName","screenName");
//需要隐藏的列
config1.defs = new Array("pfmDeviceLprId","pfmServerId","pfmScreenId","deviceId","carriagewayName","screenName");
wade.libs.datatable(config1);
//需要显示的按钮
config2.btns = new Array({
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
}
);
//查询控件 type:控件类型 placeholder:显示值 url: 获取数据的请求路径 index参数序号 最多不超过总列数
config2.search = new Array(

{
	'type' : 'search',
	'placeholder' : '编号',
	'url' : null,
	'index' : 0
});

config2.id = "screenexample"; // table id
config2.addDiv = "screenModal"; // 新增弹出的层 ID
config2.editDiv = "screenModal"; // 编辑 弹出层 ID
config2.getUrl ="/devicelpr/getscreenInfo.do"; // ajax获取数据请求地址
config2.saveUrl = "/devicelpr/savescreenInfo.do"; // 保存请求地址
config2.delUrl = "/devicelpr/delscreenInfo.do"; // 保存请求地址
//需要绑定数据的列
config2.columns = new Array("pfmDeviceScreenId", "deviceCode", "deviceName","updateTime","deviceIdArr");
//需要隐藏的列
config2.defs = new Array("pfmDeviceScreenId");
wade.libs.datatable(config2);
$(".nav-tabs-custom .nav-tabs li:first").addClass("active");
$(".tab-content .tab-pane:first").addClass("active");

//点击tab菜单，刷新datatable表格(重新加载表格)
$('li[name="deviceli"]').click(function(){
	
	/*if($('#li1').is('active')){
		alert(1);
		$("#LPRexample_wrapper").find(".paginate_button.active").click();  //点击分页获取最新数据
	}
	if($('#li2').is('active')){
		alert(2);
		$("#screenexample_wrapper").find(".paginate_button.active").click();  //点击分页获取最新数据
	}*/
	var id=$(this).attr('id');
	if(id=='li1'){
		//alert(id);
		$("#LPRexample_wrapper").find(".paginate_button.active").click();  //点击分页获取最新数据
	}
	if(id=='li2'){
		//alert(id);
		$("#screenexample_wrapper").find(".paginate_button.active").click();  //点击分页获取最新数据
	}
	
	
});





config2.add_shown=function(devClone, operId,sel_obj){
	$.ajax({
		 url: "/devicelpr/configDeviceLpr.do",
	     dataType: 'json',
	     type:'post',
	     quietMillis: 250,
	     success: function (result) {
	    	 devClone.find('*[name="listarr"]').select2({
	      		//dropdownParent:devClone,
	      		multiple:true,
	      		allowClear: true,
	      		//placeholder:'请选择',
	      		data:result
	      	});
	    	 if(operId==1){
	    			devClone.find('*[name="listarr"]').select2().val(sel_obj.listarr).trigger("change");
	    		}
	     }
	});
	
	
      //显示屏校验	
	
devClone.find('*[name="deviceCode"]').blur(function(){
	var deviceCode=devClone.find('*[name="deviceCode"]').val();
	if(checkAllName(deviceCode,'设备编号')!=100){
		var errMsg=checkAllName(deviceCode,'设备编号');
		devClone.find('.point-out').hide();
		devClone.find("#deviceCodeNotice").html('<span class="point-box errorbgTop dropdown" ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
	}
	
    
    
	});

     devClone.find('*[name="deviceCode"]').focus(function(){
		devClone.find('#deviceCodeNotice').hide();
	});
     
    devClone.find('input[name=deviceName]').blur(function(){
    	var deviceName=devClone.find('*[name="deviceName"]').val();
    	
    	if(checkAllName(deviceName,'设备名称')!=100){
			var errMsg=checkAllName(deviceName,'设备名称');
			devClone.find('.point-out').hide();
			devClone.find("#deviceNameNotice").html('<span class="point-box errorbgTop dropdown" ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
			
		}
    	
    	
		
});
    
    devClone.find('input[name=deviceName]').focus(function(){
		devClone.find('#deviceNameNotice').hide();
	});
	
	
	
	
	
	//显示屏设备
	devClone.find('#submit').on('click',registerScreen);
	function registerScreen(event){
		var deviceCode=devClone.find('*[name="deviceCode"]').val();
		var deviceName=devClone.find('*[name="deviceName"]').val();
		
		if(checkAllName(deviceCode,'设备编号')!=100){
			var errMsg=checkAllName(deviceCode,'设备编号');
			devClone.find('.point-out').hide();
			devClone.find("#deviceCodeNotice").html('<span class="point-box errorbgTop dropdown" ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
			event.stopImmediatePropagation(); //阻止提交数据
			return false;
		}else if(checkAllName(deviceName,'设备名称')!=100){
			var errMsg=checkAllName(deviceName,'设备名称');
			devClone.find('.point-out').hide();
			devClone.find("#deviceNameNotice").html('<span class="point-box errorbgTop dropdown" ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
			event.stopImmediatePropagation(); //阻止提交数据
			return false;
		}
		
		
		
		
		
		
		
		
	}
	
  
	
	
};


//删除LPR管理
config1.customDialog=function(sel_obj_arr){
	var flag=true;
	//var msg='';
	$.each(sel_obj_arr,function(index,value){
		if(value.deviceId!=null){
			wade.libs.dialog('LPR名称为'+value.deviceName+'已绑是定车道名称为'+value.carriagewayName+'的车道，请先移除车道绑定');
			flag=false;
			return false;//==break;
		}
		
		if(value.pfmScreenId!=null){
		
			
			wade.libs.dialog('当前LPR已绑定设备名称为'+value.screenName+'的显示屏设备，请先移除显示屏设备');
			flag=false;
			return false;//==break;
			
		}
		
	});
	return flag;
}



config1.add_shown=function(devClone, operId,sel_obj){
	$.ajax({
		 url: "/devicelpr/configservice.do",
	        dataType: 'json',
	        //data:{serverName:serverName},
	        type:'post',
	        success: function (data) {
	        	var result=eval(data);
	        	
	        	for(var i in result){
	        		if(result[i].id!=undefined){
	        			if(result[0].id==0&&result.length==1){
		        			//alert(0);
		        			devClone.find('#congfigsel').append('<option value="'+result[0].id+'">'+result[0].text+'</option>');
		        			break;
		        		}
		        		else{
		        			
		        			devClone.find('#congfigsel').append('<option value="'+result[i].id+'">'+result[i].text+'</option>');
		        			
		        		}
	        		}
	        		
	        		
	        	}
	        	if(operId==1){
	        		devClone.find('option[value="'+sel_obj.pfmServerId+'"]').attr('selected',true);
	        	}
	        }
	});
	
/********************************************************LPR校验************************************/
	//编号失去焦点
	devClone.find('input[name=deviceCode]').blur(function(){
		var codeval=devClone.find("input[name=deviceCode]").val();
		if(checkAllName(codeval,'编号')!=100){
			var errMsg=checkAllName(codeval,'编号');
			devClone.find('.point-out').hide();
			devClone.find("div[name=deviceCodeNotice]").html('<span class="point-box errorbgTop dropdown" ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
		}	
	});
	
	//编号获取焦点
	 devClone.find('input[name=deviceCode]').focus(function(){
			
			devClone.find('div[name=deviceCodeNotice]').hide();
		});
	
	//设备名称
	 devClone.find('input[name=deviceName]').blur(function(){
		 var nameval=devClone.find("input[name=deviceName]").val();
		 if(checkAllName(nameval,'设备名称')!=100){
				var errMsg=checkAllName(nameval,'设备名称');
				devClone.find('.point-out').hide();
				devClone.find("div[name=deviceNameNotice]").html('<span class="point-box errorbgTop dropdown" ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
			}
		
			
			
		});
	 
	    devClone.find('input[name=deviceName]').focus(function(){
			
			devClone.find('div[name=deviceNameNotice]').hide();
		});
	//ip地址
	    devClone.find('input[name=ip]').blur(function(){
	    	var ipval=devClone.find("input[name=ip]").val();
	    	if(checkIp(ipval)!=100){
	    		var errMsg=checkIp(ipval);
	 			devClone.find('.point-out').hide();
	 			devClone.find("div[name=ipNotice]").html('<span class="point-box errorbgTop dropdown"><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
	    	}
	    	
		});
	    devClone.find('input[name=ip]').focus(function(){
			
			devClone.find('div[name=ipNotice]').hide();
		});
	 //端口
	    devClone.find('input[name=port]').blur(function(){
	    	var portval=devClone.find("input[name=port]").val();
	    	if(checkPort(portval)!=100){
	    		var errMsg=checkPort(portval);
				devClone.find('.point-out').hide();
				devClone.find("div[name=portNotice]").html('<span class="point-box errorbgTop dropdown"><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
	    	}
		});
	    devClone.find('input[name=port]').focus(function(){
			
			devClone.find('div[name=portNotice]').hide();
		});
	  //登录名称
	    devClone.find('input[name=loginId]').blur(function(){
	    	var loginIdval=devClone.find("input[name=loginId]").val();
	    	if(checkUsername(loginIdval)!=100){
	    		var errMsg=checkUsername(loginIdval);
				devClone.find('.point-out').hide();
				devClone.find("div[name=loginIdNotice]").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
	    	}
	    	
			
		});
	devClone.find('input[name=loginId]').focus(function(){
			
			devClone.find('div[name=loginIdNotice]').hide();
		});
	    //登录密码
	devClone.find('input[name=loginPassword]').blur(function(){
		var loginPasswordval=devClone.find('input[name=loginPassword]').val();
		if(checkAllName(loginPasswordval,'登录密码')!=100){
			var errMsg=checkAllName(loginPasswordval,'登录密码');
			devClone.find('.point-out').hide();
			devClone.find("div[name=loginPasswordNotice]").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();	
		}
		
		
	});
	devClone.find('input[name=loginPassword]').focus(function(){
		
		devClone.find('div[name=loginPasswordNotice]').hide();
	});
	
	    
	//提交验证
	devClone.find('#submit').on('click',registerMac);
	function registerMac(event){
		devClone.find('.point-out').hide();
		var re=/^[0-9]*[1-9][0-9]*$/;
		var re2=/^[\u4E00-\u9FA5\uF900-\uFA2D\w]{4,10}$/;
		var ipre =  /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/   
		var portre=/^([0-9]|[1-9]\d|[1-9]\d{2}|[1-9]\d{3}|[1-5]\d{4}|6[0-4]\d{3}|65[0-4]\d{2}|655[0-2]\d|6553[0-5])$/
		var portval=devClone.find("input[name=port]").val();
		var codeval=devClone.find("input[name=deviceCode]").val();
		var nameval=devClone.find("input[name=deviceName]").val();
		var ipval=devClone.find("input[name=ip]").val();
		var loginIdval=devClone.find("input[name=loginId]").val();
		var loginPasswordval=devClone.find("input[name=loginPassword]").val();//登陆密码
		var length=$.trim(portval).length;

		if(checkAllName(codeval,'编号')!=100){
			var errMsg=checkAllName(codeval,'编号');
			devClone.find('.point-out').hide();
			devClone.find("div[name=deviceCodeNotice]").html('<span class="point-box errorbgTop dropdown" ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
			event.stopImmediatePropagation(); //阻止提交数据
			return false;
		}else if(checkAllName(nameval,'设备名称')!=100){
			var errMsg=checkAllName(nameval,'设备名称');
			devClone.find('.point-out').hide();
			devClone.find("div[name=deviceNameNotice]").html('<span class="point-box errorbgTop dropdown" ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
			event.stopImmediatePropagation(); //阻止提交数据
			return false;
		}else if(checkIp(ipval)!=100){
    		var errMsg=checkIp(ipval);
 			devClone.find('.point-out').hide();
 			devClone.find("div[name=ipNotice]").html('<span class="point-box errorbgTop dropdown"><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
 			event.stopImmediatePropagation(); //阻止提交数据
			return false;
		}else if(checkPort(portval)!=100){
    		var errMsg=checkPort(portval);
			devClone.find('.point-out').hide();
			devClone.find("div[name=portNotice]").html('<span class="point-box errorbgTop dropdown"><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
			event.stopImmediatePropagation(); //阻止提交数据
			return false;
		}else if(checkUsername(loginIdval)!=100){
    		var errMsg=checkUsername(loginIdval);
			devClone.find('.point-out').hide();
			devClone.find("div[name=loginIdNotice]").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
			event.stopImmediatePropagation(); //阻止提交数据
			return false;
		}else if(checkAllName(loginPasswordval,'登录密码')!=100){
			var errMsg=checkAllName(loginPasswordval,'登录密码');
			devClone.find('.point-out').hide();
			devClone.find("div[name=loginPasswordNotice]").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();	
			event.stopImmediatePropagation(); //阻止提交数据
			return false;
		}
		
		
		
		
		
		
		
		
		
	};
	
	
	

};



