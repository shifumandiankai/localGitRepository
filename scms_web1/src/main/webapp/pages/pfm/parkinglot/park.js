
var wade = wade || {};
wade.park = wade.park || {};
var parkconfig = new Object();
// 需要显示的按钮
parkconfig.btns = new Array({
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


// 查询控件 type:控件类型 placeholder:显示值 url: 获取数据的请求路径 index参数序号 最多不超过总列数
parkconfig.search = new Array(
// {'type':'multiple_select','placeholder':'多选','url':'/role/GetSelectDataTest.do','index':2}
// //select 多选
{
	'type' : 'search',
	'placeholder' : '车场名称',
	'url' : null,
	'index' : 0
}

);

parkconfig.add_shown = function(devClone, operId,sel_obj) {
	// ===========================填充数据及插件初始化==============================
		
	
		

		
		if(operId==1){
			//修改
			
			var inId=devClone.find('*[name="inId"]').val(sel_obj.inId);
		
			var parkinglotname=sel_obj.attribution;
			var selectvalue=devClone.find('*[name="attribution"]');
		       if(parkinglotname!=null){  
		    	   $.ajax({
		  			 url: "/parkinglot/selectParkinglotInfo.do",
		  		        dataType: 'json',
		  		        type:'post',
		  		        data:{parkinglotname:parkinglotname},
		  		        success: function (data) {
		  		        	var result=eval(data);
		  		        	var pfmParkingLotId=result.pfmParkingLotId;
		  		        	selectvalue.append('<option value="'+pfmParkingLotId+'">'+parkinglotname+'</option>');
		  		        	selectvalue.attr('disabled',true);
		  		        }
		  		});
		    	  
		       }
		       else{
		    	   selectvalue.append('<option value="-2">无</option>');
		    	   selectvalue.attr('disabled',true);
		    	  
		       }
		}
		else{
			//添加
			
			$.ajax({	//获取角色数据
		        url: "/parkinglot/GetParkingSelectData.do",
		        dataType: 'json',
		        type:'post',
		        quietMillis: 250,
		        //async : false,
		        success: function (result) { 
		        	var b=eval(result);
		        	var selectvalue=devClone.find('*[name="attribution"]');
		        	
		        	
		        	for(var i in b){
		        		if(b[i].id!=undefined){
		        			if(b[i].id==0&&b[i].text=='无归属车场'){
			        			devClone.find("*[name=attribution]").attr("disabled", "disabled");
			        			
			        		}
			        		selectvalue.append('<option value="'+b[i].id+'">'+b[i].text+'</option>');
		        		}
		        		
		        		
		        		
		        	}
		        }
		    }); 
		}
      
	
	
	//===================================验证相关=================================================
	devClone.find('#submit').on('click',registerUser);	//绑定用户添加或修改事件
	//车场数量光标移走后验证
	devClone.find('input[name=carNumber]').on("blur", function () {
		    
		var re = /^\+?[1-9]\d*$/;
		var carnumber=devClone.find("input[name=carNumber]").val();
		var errMsg=checkCarNumber(carnumber);
		if(errMsg!=100){
			devClone.find('.point-out').hide();
			devClone.find("div[name=carNumberNotice]").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
		}
		
		
		
	});
	
	devClone.find('input[name=carNumber]').on("focus", function () {
		
		devClone.find("div[name=carNumberNotice]").hide();
	});
	
	//车场名称光标移走后验证
	devClone.find('input[name=parkingLotName]').on("blur", function () {
		   
		var username = devClone.find("input[name=parkingLotName]").val();
		
		
		var errMsg=checkAllName(username,'车场名称');
		if(errMsg!=100){
			devClone.find('.point-out').hide();
			devClone.find("#parkNameNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
		}
		
		
		
	});
	devClone.find('input[name=parkingLotName]').on("focus", function () {
		
		devClone.find("#parkNameNotice").hide();
	});
	
	
	//车场名称光标锁定
	devClone.find('input[name=parkName]').on('change, keyup', function (){
		devClone.find("#parkNameNotice").hide();
	});
	
	
	//车场序号
	devClone.find('input[name=parkingLotCode]').on("blur", function () {
		var re = /^\+?[1-9]\d*$/;
		var username = devClone.find("input[name=parkingLotCode]").val();
		
		var errMsg=checkParkingLotCode(username);
		//alert(errMsg);
		if(errMsg!=100){
			devClone.find('.point-out').hide();
			devClone.find("#parkingLotCodeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
		}
	});
	devClone.find('input[name=parkingLotCode]').on("focus", function () {
		
		devClone.find("#parkingLotCodeNotice").hide();
	});
	
	
	//车场名称光标锁定
	devClone.find('input[name=parkingLotCode]').on('change, keyup', function (){
		devClone.find("#parkingLotCodeNotice").hide();
	});
	
	
	
	
	
	
	
	
	
	
	
	
	
	//车位数量光标锁定
	devClone.find('input[name=carNumber]').on('change, keyup', function (){
		devClone.find("div[name=carNumberNotice]").hide();
	});
	
	
	
	function registerUser(event){
		var errMsg;
		var re = /^\+?[1-9]\d*$/;
		var re1=/^\+?[0-9]\d*$/;
		var username = devClone.find("input[name=parkingLotName]").val();
		var carnumber=devClone.find("input[name=carNumber]").val();
		var parkcode=devClone.find("input[name=parkingLotCode]").val();
		var note=devClone.find("input[name=note]").val();
		if(checkCarNumber(carnumber)!=100){
			errMsg=checkCarNumber(carnumber);
			devClone.find('.point-out').hide();
			devClone.find("#carNumberNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
			event.stopImmediatePropagation(); //阻止提交数据 
			return false;
		}else if(checkAllName(username)!=100){
			errMsg=checkAllName(username,'车场名称');
			devClone.find('.point-out').hide();
			devClone.find("#parkNameNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
			event.stopImmediatePropagation(); //阻止提交数据 
			return false;
		}else if(checkParkingLotCode(parkcode)!=100){
			errMsg=checkParkingLotCode(parkcode);
			devClone.find('.point-out').hide();
			devClone.find("#parkingLotCodeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
			event.stopImmediatePropagation(); //阻止提交数据 
			return false;
		}
		
		
		
		
	}
}

//ajax删除车场信息
parkconfig.beforeAjaxfun=function(sel_obj_arr){
	var flag=true;//true
	
	$.each(sel_obj_arr,function(index,value){

	     $.ajax({
			url :"/parkinglot/selectCarInfoByType.do",
			type:"post",
			async:false,
			data : {parkId:value.pfmParkingLotId},
			success: function(data){
				obj= JSON.parse(data);

				if(!obj.result){
					wade.libs.dialog("车场名称为"+value.parkingLotName+"的车场下包含车辆信息,请先删除该车场下的车辆信息");
					flag=false;//false
						
				} 
			}
		});	

		if(flag==false){

			return false;
			
		}
	});
	return flag;
}






//删除车场
parkconfig.beforefun=function(sel_obj_arr){
	var flag=true;
		$.each(sel_obj_arr,function(index,value){
			
			   if(value.parkId!=null){
					wade.libs.dialog(value.parkingLotName+'有服务关联，请先移除关联他的'+value.serverName+'服务器服务');
					flag=false;
					return false;//==break;
				}
			   
		});
		return flag;
				
}


/*parkconfig.customDialog=function(sel_obj_arr){
	wade.libs.dialog('如果删除车场将删除此车场所有的出入口、车辆类型，是否删除',function(){	
		$.ajax({
			url :config1.delUrl,
			type:"post",
			data : "condition=" + JSON.stringify(sel_obj_arr),
			success: function(data){
				result = JSON.parse(data);
				if(result.success){
					wade.libs.alert('删除成功');
					$("#" + config1.id+'_wrapper').find(".paginate_button.active").click(); //触发请求分页数据
				} else {		
					wade.libs.alert(result.msg, 0);
				}
			}
		});	
		
		
	});
	
	flag=false;
	return flag;
}*/

parkconfig.id = "parktable"; // table id
parkconfig.addDiv = "parkModal"; // 新增弹出的层 ID
parkconfig.editDiv = "parkModal"; // 编辑 弹出层 ID
parkconfig.getUrl = "/parkinglot/getParkInfo.do"; // ajax获取数据请求地址
parkconfig.delUrl = "/parkinglot/delPark.do"; // ajax删除角色请求地址
parkconfig.saveUrl = "/parkinglot/savePark.do"; // 保存请求地址
// 需要绑定数据的列
parkconfig.columns = new Array("pfmParkingLotId", "parkingLotName" ,"parkingLotCode","carNumber","attribution", "note","pfmServerId","parkId","serverName");
// 需要隐藏的列
parkconfig.defs = new Array("pfmParkingLotId", /*"userType"*/"pfmServerId","parkId","serverName");
wade.libs.datatable(parkconfig);

