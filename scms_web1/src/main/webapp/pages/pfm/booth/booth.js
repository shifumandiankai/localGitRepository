var wade = wade || {};
wade.booth = wade.booth || {};
var boothconfig = new Object();
var arr=[];

//var array=[];
//需要显示的按钮
boothconfig.btns = new Array({
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
boothconfig.search = new Array(

{
	'type' : 'search',
	'placeholder' : '岗亭名称',
	'url' : null,
	'index' : 0
});


boothconfig.add_shown = function(devClone, operId,sel_obj) {
	//获取pfmEntranceId数据
	if(operId==0){
		$.ajax({
			 url: "/booth/GetEntranceSelectData.do",
			  dataType: 'json',
			  type:'post',
			  success: function (data) {
				  
				  var result=eval(data);
				  console.log(result);
					for(var i in result){
						if(result[i].id!=undefined){
							
							if(result[0].id==-1&&result.length==1){
			   	                 devClone.find("*[name=pfmEntranceId]").attr("disabled", "disabled");
			   	                 devClone.find("*[name=pfmEntranceId]").append('<option value="'+result[0].id+'" ">'+result[0].text+'</option>');
			   	                 	break;
			   	                 }
								else{
									devClone.find("*[name=pfmEntranceId]").append('<option value="'+result[i].id+'">'+result[i].text+'</option>');
								}
							
						}
						
						
						
					}
			  }
		});	
	}

	//默认radio的值
	if(operId==1){
		
		$.ajax({
			 url: "/booth/GetEntranceSelectData.do",
			  dataType: 'json',
			  type:'post',
			  success: function (data) {
				  
				  var result=eval(data);
				  //console.log(result);
				  
					for(var i in result){
						if(result[i].id!=undefined){
							if(result[0].id==-1&&result.length==1){
			   	                 devClone.find("*[name=pfmEntranceId]").attr("disabled", "disabled");
			   	                 devClone.find("*[name=pfmEntranceId]").append('<option value="'+result[0].id+'" ">'+result[0].text+'</option>');
			   	                 	break;
			   	                 }
								else{
									devClone.find("*[name=pfmEntranceId]").append('<option value="'+result[i].id+'">'+result[i].text+'</option>');
									
								}
						}
						
						
					}
					devClone.find('select option[value="'+sel_obj.pfmEntranceId+'"]').attr('selected',true);
			  }
		});	
		
		var yes=devClone.find("#yes").val();
		var no=devClone.find("#no").val();
     if(yes==sel_obj.centerCharge){
			
			devClone.find("#yes").attr("checked","checked");
		}
		else{
			devClone.find("#no").attr("checked","checked");
		}
		/**/
	}
	
	
	//校验
	//岗亭名称
	devClone.find("input[name=boothName]").on('blur',function(){
		var boothName=devClone.find("input[name=boothName]").val().trim();
		if(checkAllName(boothName,'岗亭名称')!=100){
			var msg=checkAllName(boothName,"岗亭名称");
			devClone.find('.point-out').hide();
			devClone.find("#boothNameNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + msg + '</span>').show();
		}
	});
	
	devClone.find("input[name=boothName]").on('focus',function(){
		devClone.find('#boothNameNotice').hide();
	});
	
	//岗亭编号
	devClone.find("input[name=boothCode]").on('blur',function(){
		var boothCode=devClone.find("input[name=boothCode]").val().trim();
		if(checkAllName(boothCode,'岗亭编号')!=100){
			var msg=checkAllName(boothCode,'岗亭编号');
			devClone.find('.point-out').hide();
			devClone.find("#boothCodeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + msg + '</span>').show();
		}
		
	});
    devClone.find("input[name=boothCode]").on('focus',function(){
    	devClone.find('#boothCodeNotice').hide();
	});
	
    //电话
    devClone.find("input[name=phone]").on('blur',function(){
    	var phoneval=devClone.find("input[name=phone]").val();
    	if(checkMobile(phoneval)!=100){

    		var msg=checkMobile(phoneval);
			devClone.find('.point-out').hide();
			devClone.find("#phoneNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + msg + '</span>').show();
    		
    	}
    	
	});
    devClone.find("input[name=phone]").on('focus',function(){
    	devClone.find('#phoneNotice').hide();
	});
    
    //客户端ip
    devClone.find("input[name=clientIp]").on('blur',function(){
    	var ip=devClone.find("input[name=clientIp]").val();
    	if(checkIp(ip)!=100){
    		var msg=checkIp(ip);
			devClone.find('.point-out').hide();
			devClone.find("#clientIpNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + msg + '</span>').show();
    	}
    	
    });
    devClone.find("input[name=clientIp]").on('focus',function(){
    	devClone.find('#clientIpNotice').hide();
	});
    //备注信息
    devClone.find("input[name=note]").on('blur',function(){
    	var note=devClone.find("input[name=note]").val().trim();
    	if(checkStrMaxLen(note)!=100){
    		
    	var msg=checkStrMaxLen(note);
   	    devClone.find('.point-out').hide();
   		devClone.find("#noteNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + msg + '</span>').show();
    	}
    	
    });
    devClone.find("input[name=note]").on('focus',function(){
    	devClone.find('#noteNotice').hide();
	});
    
    
	devClone.find("#submit").on('click',registerUser);////绑定用户添加或修改事件
	//校验
	function registerUser(event){
		
		var msg=null;
		var re=/^1[3|4|5|8][0-9]\d{4,8}$/;
		var ipre=/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
		var boothName=devClone.find("input[name=boothName]").val().trim();
		var phoneval=devClone.find("input[name=phone]").val();
		var boothCode=devClone.find("input[name=boothCode]").val().trim();
		var ip=devClone.find("input[name=clientIp]").val();
		var note=devClone.find("input[name=note]").val().trim();

		if(checkAllName(boothName,'岗亭名称')!=100){
			var msg=checkAllName(boothName,'岗亭名称');
			devClone.find('.point-out').hide();
			devClone.find("#boothNameNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + msg + '</span>').show();
			event.stopImmediatePropagation(); //阻止提交数据
			return false;
		}else if(checkAllName(boothCode,'岗亭编号')!=100){
			var msg=checkAllName(boothCode,'');
			devClone.find('.point-out').hide();
			devClone.find("#boothCodeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + msg + '</span>').show();
			event.stopImmediatePropagation(); //阻止提交数据
			return false;
		}else if(checkMobile(phoneval)!=100){

    		var msg=checkMobile(phoneval);
			devClone.find('.point-out').hide();
			devClone.find("#phoneNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + msg + '</span>').show();
			event.stopImmediatePropagation(); //阻止提交数据
			return false;
    	}else if(checkIp(ip)!=100){
    		var msg=checkIp(ip);
			devClone.find('.point-out').hide();
			devClone.find("#clientIpNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + msg + '</span>').show();
			event.stopImmediatePropagation(); //阻止提交数据
			return false;
    	}else if(checkStrMaxLen(note)!=100){
    		
        	var msg=checkStrMaxLen(note);
       	    devClone.find('.point-out').hide();
       		devClone.find("#noteNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + msg + '</span>').show();
       		event.stopImmediatePropagation(); //阻止提交数据
			return false;
    	}











		
};
};
boothconfig.beforefun=function(sel_obj_arr){
	var flag=true;
	$.each(sel_obj_arr,function(index,value){
		if(value.pfmEntranceId!=null){
			wade.libs.dialog('当前岗亭已绑定出入口，是否删除',function(){
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
							$("#" + config1.id+'_wrapper').find(".paginate_button.active").click(); //触发请求分页数据
						}
					}
				});	
				
				
			});
			flag=false;
			return flag;
		}
		
	});
	return flag;
	
}

//自定义功能按钮
function configuration(sel_tr,all_tr,sel_obj_arr,sel_obj){
	var count=0;//点击添加按钮的次数
	var Init=0;
	var hide=0;//隐藏的个数
	if(!sel_obj){
        wade.libs.alert('请选择配置的岗亭！',2);
        return;
    }
	var dev=$("#lprexampleModal");
	dev.modal('show');
	dev.find('*[name="sel"]').html('');
	 var boothid = sel_obj.pfmBoothId;
	 dev.find('#reset').attr("disabled",false);
	 selectData();
	 InitSelect();
	
	//显示select数据函数
	 function selectData(){
			var select=dev.find('*[name="sel"]');
			//alert(0);
			$.ajax({
				 url: "/Booth/GetLprSelectData.do",
			        dataType: 'json',
			        async:false,
			        success: function (data) {
			        	var result=eval(data);
			        	
			        	for(var i in result){
			        		
			        		if(result[0].text=='无设备选择'||result.length==1){
			        			
			        			$(select).append('<option value="'+result[i].id+'">'+result[i].text+'</option>');
			        			break;
			        		}
			        		else{
			        			
			        			$(select).append('<option value="'+result[i].id+'">'+result[i].text+'</option>');
			        			//array.push(result[i].id);
				        		
			        		}
			        		
			        	}
			        }
			});
		};
		//初始化选中的设备
	function InitSelect(){
		
		$.ajax({
			 url: "/Booth/GetLprInfo.do",
		        dataType: 'json',
		        data:{boothid:boothid},
		        async:false,
		        type:'post',
		        success: function (data) {
		        	var result=eval(data);
		        	if(result.length==1||result==null){		        	
		        		//alert(0);
		        		$("#lprexampleModal").find('*[name="sel"] option[value="0"]').attr("selected",true);		        		
        			}
		        		for(var i in result){
		        		if(result[i].id==0&&result[i].text=='0'){
		        			$("#lprexampleModal").find('#sel'+i+' option[value="0"]').attr("selected",true);
		        		}
		        		else{
		        			
		        			$("#lprexampleModal").find('#sel'+result[i].text+' option[value="'+result[i].id+'"]').attr("selected",true);
		        			
		        		}
		        		
		        		}
		        		//alert(hide);
		        }
		});
		
	};
	
	//点击重置按钮
	dev.find('#reset').click(function(){
	
		dev.find('*[name="sel"]').html('');
		 selectData();
		 InitSelect();
		 
	});
	//关闭层
	dev.on('hide.bs.modal',function(){
	});
	
	dev.find("#submit").on('click',registerLpr);////绑定用户添加或修改事件
	
	function registerLpr(event){
		var regisrterArr=[];
		var tempArr=[];
		var sel0val=dev.find('#sel0 option:selected').val();
		var sel1val=dev.find('#sel1 option:selected').val();
		var sel2val=dev.find('#sel2 option:selected').val();
		var sel3val=dev.find('#sel3 option:selected').val();
		var sel4val=dev.find('#sel4 option:selected').val();
		var sel5val=dev.find('#sel5 option:selected').val();
		var sel6val=dev.find('#sel6 option:selected').val();
		var sel7val=dev.find('#sel7 option:selected').val();
		regisrterArr.push(sel0val);
		regisrterArr.push(sel1val);
		regisrterArr.push(sel2val);
		regisrterArr.push(sel3val);
		regisrterArr.push(sel4val);
		regisrterArr.push(sel5val);
		regisrterArr.push(sel6val);
		regisrterArr.push(sel7val);
		for(var i=0;i<regisrterArr.length;i++){
			if(regisrterArr[i]!=0){
				tempArr.push(regisrterArr[i]);
			}
			
		}
		var resultArr=tempArr.sort();
		
		for(var i=0;i<regisrterArr.length;i++){
			if(i<4){
				if(regisrterArr[i]==0&&regisrterArr[i+4]!=0){
					//alert(0);
					wade.libs.alert('设备添加失败，设备要配套添加',0,dev.find('div:first'));
					event.stopImmediatePropagation(); //阻止提交数据
					return false;
				}
				
			}
			else{
				if(regisrterArr[i]==0&&regisrterArr[i-4]!=0){
					//alert(0);
					wade.libs.alert('设备添加失败，设备要配套添加',0,dev.find('div:first'));
					event.stopImmediatePropagation(); //阻止提交数据
					return false;
				}
			}
		}
		for(var i=0;i<resultArr.length;i++){
			if(resultArr[i]==resultArr[i+1]){
				
				//alert(tempArr[i]);
				wade.libs.alert('设备添加失败，设备不能重复',0,dev.find('div:first'));
				event.stopImmediatePropagation(); //阻止提交数据
				return false;
			}
		}
		
	};
	
	
	//提交
	dev.find('#submit').click(function(){
		
		var in1=dev.find('#sel0 option:selected').val();
		var out1=dev.find('#sel4 option:selected').val();
		var in2=dev.find('#sel1 option:selected').val();
		var out2=dev.find('#sel5 option:selected').val();
		var in3=dev.find('#sel2 option:selected').val();
		var out3=dev.find('#sel6 option:selected').val();
		var in4=dev.find('#sel3 option:selected').val();
		var out4=dev.find('#sel7 option:selected').val();
		arr.push(in1);
		arr.push(out1);
		arr.push(in2);
		arr.push(out2);
		arr.push(in3);
		arr.push(out3);
		arr.push(in4);
		arr.push(out4);
		var selectlist=JSON.stringify(arr);
		$.ajax({
			 url: "/Booth/SaveLpr.do",
		        dataType: 'json',
		        type:'post',
		        async:false,
		        data:{boothid:boothid,selectlist:selectlist},
		        success: function (data) {
		        	//alert(0);
		        	wade.libs.alert('设备修改成功',null,dev.find('div:first'));	
		        }
		});
	if(arr.length!=0){
		arr.length=0;
		
	}
	dev.find('#reset').attr("disabled",true);
	});
};

boothconfig.id = "booth_table"; // table id
boothconfig.addDiv = "boothModal"; // 新增弹出的层 ID
boothconfig.editDiv = "boothModal"; // 编辑 弹出层 ID
boothconfig.lprDiv="lprexampleModal";
boothconfig.getUrl ="/booth/getBoothInfo.do"; // ajax获取数据请求地址
boothconfig.saveUrl = "/booth/saveBoothInfo.do"; // 保存请求地址
boothconfig.delUrl = "/Booth/delBoothInfo.do"; // 保存请求地址
//需要绑定数据的列
boothconfig.columns = new Array("pfmBoothId", "boothCode", "boothName",
		"phone", "consumeConfirmresult","centerCharge","clientIp","note","pfmEntranceId");
//需要隐藏的列
boothconfig.defs = new Array("pfmBoothId","consumeConfirmresult","pfmEntranceId");

wade.libs.datatable(boothconfig);