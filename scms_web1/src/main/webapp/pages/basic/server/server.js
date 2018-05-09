var config = $.extend({},{
	btns: new Array({'id':'10001','name':'add','fun':null},	//需要显示的按钮
			{'id':'10002','name':'edit',"fun":null},
			{'id':"10003",'name':'del',"fun":null},
			{'id':"10004",'name':'远程配置',"fun":remote_set }),
			search:new Array(), //查询条件 
			id:"t_server",	//dataTable ID
			addDiv:"exampleModal", //新增弹出的DIV ID
			editDiv:"exampleModal",	//编辑弹出的DIV ID
			getUrl:"/auth/server/getServerInfo.do",	//获取数据请求路径
			delUrl:"/auth/server/delServer.do",	//删除数据请求路径
			saveUrl:"/auth/server/saveServer.do",	//保存数据请求路径
			columns: new Array("serverName","ip","port","updateTime","userName","serverId","netZoneId","parkId","belongto"),
			defs:new Array("serverId","netZoneId","parkId","belongto"),
			add_shown:shown, //弹出新增或编辑对话框后执行的方法,
});	
wade.libs.datatable(config);	//初始化datatable

function shown(devClone,flag,sel_obj){

	var type="";
	var step=1;
	devClone.find(".servers .server-type").click(function(){//根据所选服务器类型
		if(type==$(this).find("i").attr("class")){
			return;
		}
		else{
			type=$(this).find("i").attr("class");
			step=1;
			switch(type){
			case "server-1-1":
					devClone.find("#mutablewizard").empty().append($("#wiz-park").html());
					devClone.find("#mutablesteps").empty().append($("#stp-park").html());
					parkVerifyBinding();//验证绑定
			break;
			case "server-1-2":
					devClone.find("#mutablewizard").empty().append($("#wiz-menjin").html());
					devClone.find("#mutablesteps").empty().append($("#stp-menjin").html());
					menjinVerifyBinding();
			break;
			
			}
		}
		devClone.find(".servers .server-type").removeClass("selected");
		$(this).addClass("selected");
		devClone.find(".step1 [name=serverType]").val($(this).find("i").attr("class"));
		
	
		devClone.find("[isdone]").on("click",function(){
			if($(this).attr("isdone")==0){
				return;
			}
			else{
				devClone.find("[isdone=1].selected").removeClass().addClass("done");
				$(this).removeClass("done").addClass("selected");
				var href = $(this).attr("href");
				step=parseInt(href.charAt(href.length-1));
				/* 判断服务器类型，切换页脚按钮形式*/
				if(type=="server-1-1"){
					switch(step){
					case 1:
						devClone.find(".prev").addClass("disabled");
						devClone.find(".finish").addClass("disabled");
						devClone.find(".next").removeClass("disabled");
						devClone.find(".step-container [class^=step]").hide();
						devClone.find(".step"+step).show();
						break;
					case 2:
						devClone.find(".prev").removeClass("disabled");
						devClone.find(".finish").removeClass("disabled");
						devClone.find(".next").addClass("disabled");
						devClone.find(".step-container [class^=step]").hide();
						devClone.find(".step"+step).show();

						break;
					}

				}//当服务器类型为server-1-1时，如有多个服务器类型，在下面添加更多的if
				else if(type=="server-1-2"){
					switch(step){
					case 1:
						devClone.find(".prev").addClass("disabled");
						devClone.find(".finish").addClass("disabled");
						devClone.find(".next").removeClass("disabled");
						devClone.find(".step-container [class^=step]").hide();
						devClone.find(".step"+step).show();
						break;
					case 2:
						devClone.find(".prev").removeClass("disabled");
						devClone.find(".finish").removeClass("disabled");
						devClone.find(".next").addClass("disabled");
						devClone.find(".step-container [class^=step]").hide();
						devClone.find(".step"+step).show();

						break;
					}
				}
			}
		});
	});

	devClone.find(".next").on("click",function(event){
		if($(this).hasClass("disabled")){
			event.stopImmediatePropagation();
			return;
		}
		switch(type){//根据服务器类型
		case "":alert("请先选择服务器类型！");break;
		case "server-1-1":
			/*当前为第一步，即将进入第二步时候的操作*/
			if(step==1){
				devClone.find("[href='#href1']").removeClass().addClass("done");
				devClone.find("[href='#href2']").removeClass().addClass("selected").attr("isdone",1);
				devClone.find(".prev").removeClass("disabled");
				devClone.find(".finish").removeClass("disabled");
				devClone.find(".next").addClass("disabled");
				devClone.find(".step1").hide();
				devClone.find(".step2").show();
				$.ajax({
					url:'/auth/sysconfig/getwangyulist.do',
					type:'get',
					success:function(data){
						var result = JSON.parse(data);
						devClone.find("#netZone").empty();
						
							var options="";
							$.each(result,function(index,value){
								options+="<option value='"+value.netZoneId+"'>"+value.name+"</option>";
							});
							devClone.find("#netZone").append(options);
						
							devClone.find("#netZone").append("<option value='null'>无</option>");
					}
				});
				$.ajax({
					url:'/auth/server/getparklotlist.do',
					type:'get',
					success:function(data){
						var result = JSON.parse(data);
						devClone.find("#parkId").empty();
						
							var options="";
							$.each(result,function(index,value){
								options+="<option value='"+value.pfm_parking_lot_id+"'>"+value.parking_lot_name+"</option>";
							});
							devClone.find("#parkId").append(options);
					}
				});
				 var textWidth = devClone.find("[name=port]").css("width");
				    var textHeight = devClone.find("[name=port]").css("height");
				    devClone.find("[name=netZone]").css("width", textWidth);
				    devClone.find("[name=netZone]").css("height", textHeight);
				   devClone.find("[name=parkId]").css("width", textWidth);
				    devClone.find("[name=parkId]").css("height", textHeight);
			
			}
			step++;
			
			break;
			
			
		case "server-1-2":
			/*当前为第一步，即将进入第二步时候的操作*/
			if(step==1){
				devClone.find("[href='#href1']").removeClass().addClass("done");
				devClone.find("[href='#href2']").removeClass().addClass("selected").attr("isdone",1);
				devClone.find(".prev").removeClass("disabled");
				devClone.find(".finish").removeClass("disabled");
				devClone.find(".next").addClass("disabled");
				devClone.find(".step1").hide();
				devClone.find(".step2").show();
				$.ajax({
					url:'/auth/sysconfig/getwangyulist.do',
					type:'get',
					success:function(data){
						var result = JSON.parse(data);
						devClone.find("#netZone").empty();
						
							var options="";
							$.each(result,function(index,value){
								options+="<option value='"+value.netZoneId+"'>"+value.name+"</option>";
							});
							devClone.find("#netZone").append(options);
						
							devClone.find("#netZone").append("<option value='null'>无</option>");
					}
				});
	
				 var textWidth = devClone.find("[name=port]").css("width");
				    var textHeight = devClone.find("[name=port]").css("height");
				    devClone.find("[name=netZone]").css("width", textWidth);
				    devClone.find("[name=netZone]").css("height", textHeight);
				
			}
			step++;
			
			break;
		}
		event.stopImmediatePropagation();
	});
	/*上一步按钮添加click事件，和触发点击href时相同效果*/
	devClone.find(".prev").on("click",function(event){
		if($(this).hasClass("disabled")){
			event.stopImmediatePropagation();
			return;
		}
		devClone.find("[href='#href"+(step-1)+"']").trigger("click");
	});
	/*停车场验证*/
	function parkVerifyBinding(){
	devClone.find('input[name=serverName]').on("blur",
			function() {
		var val = $(this).val();
		if(val!="")
		verifyServerName(val);

	}).on('change keydown keyup',function(){$(this).parent().find('.point-out').hide();});
	function verifyServerName(val){

		if (val.trim()=="") {
			devClone.find('.point-out').hide();
			devClone.find("#serverNameNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + "不能为空" + '</span>').show();

			return false;
		}
		else if(val.indexOf(" ")!=-1){
			devClone.find('.point-out').hide();
			devClone.find("#serverNameNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + "不能含空格" + '</span>').show();

			return false;

		}else if(val.length>20){
			devClone.find('.point-out').hide();
			devClone.find("#serverNameNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + "请输入1-20个字符" + '</span>').show();

			return false;
		}
		return true;

	}
	devClone.find('input[name=ip]').on("blur",
			function() {
		var val = $(this).val();
		if(val!="")
		verifyIp(val);

	}).on('change keydown keyup',function(){$(this).parent().find('.point-out').hide();});
	function verifyIp(val){

		if (checkIp(val)!=100) {
			devClone.find('.point-out').hide();
			devClone.find("#ipNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + checkIp(val) + '</span>').show();

			return false;
		}
		return true;

	}
	devClone.find('input[name=port]').on("blur",
			function() {
		var val = $(this).val();
		if(val!="")
		verifyPort(val);

	}).on('change keydown keyup',function(){$(this).parent().find('.point-out').hide();});
	function verifyPort(val){

		if (checkPort(val)!=100) {
			devClone.find('.point-out').hide();
			devClone.find("#portNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + checkPort(val) + '</span>').show();
	
			return false;
		}
		var ip=devClone.find("[name=ip]").val();
		var id="";
		if(flag==1){
			id=sel_obj.serverId;
		}
		else{
			id="";
		}
		var res=true;
		$.ajax({
			url:'/auth/server/checkport.do',
			type:'get',
			async:false,
			data:"ip="+ip+"&port="+val+"&flag="+flag+"&id="+id+"&belongto="+sel_obj.belongto,
			success:function(data){
				var result = JSON.parse(data);
				if(result.success){
					res=true;
				}
				else{
					res=false;
				}
			}
		});
		if(!res){
			devClone.find('.point-out').hide();
			devClone.find("#portNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i> 此端口已被占用</span>').show();
	
			return false;
		}
		return true;

	}
	devClone.find(".finish").on("click",function(event) {
		if($(this).hasClass("disabled")){
			event.stopImmediatePropagation();
			return;
		}
		var val1 = devClone.find('input[name=serverName]').val();
		if (!verifyServerName(val1)) {
			event.stopImmediatePropagation(); // 阻止提交数据
			return false;
		}
		var val2 = devClone.find('input[name=ip]').val();
		if (!verifyIp(val2)) {
			event.stopImmediatePropagation(); // 阻止提交数据
			return false;
		}
		var val3 = devClone.find('input[name=port]').val();
		if (!verifyPort(val3)) {
			event.stopImmediatePropagation(); // 阻止提交数据
			return false;
		}
		return true;
	});
	}
	//验证end
	
	/*门禁验证*/
	function menjinVerifyBinding(){
	devClone.find('input[name=serverName]').on("blur",
			function() {
		var val = $(this).val();
		if(val!="")
		verifyServerName(val);

	}).on('change keydown keyup',function(){$(this).parent().find('.point-out').hide();});
	function verifyServerName(val){

		if (val.trim()=="") {
			devClone.find('.point-out').hide();
			devClone.find("#serverNameNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + "不能为空" + '</span>').show();

			return false;
		}
		else if(val.indexOf(" ")!=-1){
			devClone.find('.point-out').hide();
			devClone.find("#serverNameNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + "不能含空格" + '</span>').show();

			return false;

		}else if(val.length>20){
			devClone.find('.point-out').hide();
			devClone.find("#serverNameNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + "请输入1-20个字符" + '</span>').show();

			return false;
		}
		return true;

	}
	devClone.find('input[name=ip]').on("blur",
			function() {
		var val = $(this).val();
		if(val!="")
		verifyIp(val);

	}).on('change keydown keyup',function(){$(this).parent().find('.point-out').hide();});
	function verifyIp(val){

		if (checkIp(val)!=100) {
			devClone.find('.point-out').hide();
			devClone.find("#ipNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + checkIp(val) + '</span>').show();

			return false;
		}
		return true;

	}
	devClone.find('input[name=port]').on("blur",
			function() {
		var val = $(this).val();
		if(val!="")
		verifyPort(val);

	}).on('change keydown keyup',function(){$(this).parent().find('.point-out').hide();});
	function verifyPort(val){

		if (checkPort(val)!=100) {
			devClone.find('.point-out').hide();
			devClone.find("#portNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + checkPort(val) + '</span>').show();
	
			return false;
		}
		var ip=devClone.find("[name=ip]").val();
		var id="";
		if(flag==1){
			id=sel_obj.serverId;
		}
		else{
			id="";
		}
		var res=true;
		$.ajax({
			url:'/auth/server/checkport.do',
			type:'get',
			async:false,
			data:"ip="+ip+"&port="+val+"&flag="+flag+"&id="+id+"&belongto="+sel_obj.belongto,
			success:function(data){
				var result = JSON.parse(data);
				if(result.success){
					res=true;
				}
				else{
					res=false;
				}
			}
		});
		if(!res){
			devClone.find('.point-out').hide();
			devClone.find("#portNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i> 此端口已被占用</span>').show();
	
			return false;
		}
		return true;

	}
	devClone.find(".finish").on("click",function(event) {
		if($(this).hasClass("disabled")){
			event.stopImmediatePropagation();
			return;
		}
		var val1 = devClone.find('input[name=serverName]').val();
		if (!verifyServerName(val1)) {
			event.stopImmediatePropagation(); // 阻止提交数据
			return false;
		}
		var val2 = devClone.find('input[name=ip]').val();
		if (!verifyIp(val2)) {
			event.stopImmediatePropagation(); // 阻止提交数据
			return false;
		}
		var val3 = devClone.find('input[name=port]').val();
		if (!verifyPort(val3)) {
			event.stopImmediatePropagation(); // 阻止提交数据
			return false;
		}
		return true;
	});
	}
	//验证end
	if(flag==1&&sel_obj.belongto=="pfm"){
		
		devClone.find(".servers .server-type:eq(0)").trigger('click');
		devClone.find(".servers .server-type").unbind('click');//修改时移除点击函数，禁止更换服务器类型
		devClone.find("[href='#href1']").removeClass().addClass("done").attr("isdone",1);
		step = 2;
		devClone.find("[href='#href2']").removeClass().addClass("selected").attr("isdone",1);
		devClone.find(".prev").removeClass("disabled");
		devClone.find(".finish").removeClass("disabled");
		devClone.find(".next").addClass("disabled");
		devClone.find(".step1").hide();
		devClone.find(".step2").show();
		$.ajax({
			url:'/auth/sysconfig/getwangyulist.do',
			type:'get',
			success:function(data){
				var result = JSON.parse(data);
				devClone.find("#netZone").empty();
				var options="";
				$.each(result,function(index,value){
					options+="<option value='"+value.netZoneId+"'>"+value.name+"</option>";
				});
				devClone.find("#netZone").append(options);
			
				devClone.find("#netZone").append("<option value='null'>无</option>");
				devClone.find("#netZone").val(sel_obj.netZoneId+"");
				
			}
		});
		$.ajax({
			url:'/auth/server/getparklotlist.do',
			type:'get',
			success:function(data){
				var result = JSON.parse(data);
				devClone.find("#parkId").empty();
				
					var options="";
					$.each(result,function(index,value){
						options+="<option value='"+value.pfm_parking_lot_id+"'>"+value.parking_lot_name+"</option>";
					});
					devClone.find("#parkId").append(options);
					devClone.find("#parkId").val(sel_obj.parkId+"");
			}
		});
		 var textWidth = devClone.find("[name=port]").css("width");
		    var textHeight = devClone.find("[name=port]").css("height");
		    devClone.find("[name=netZone]").css("width", textWidth);
		    devClone.find("[name=netZone]").css("height", textHeight);
		   devClone.find("[name=parkId]").css("width", textWidth);
		    devClone.find("[name=parkId]").css("height", textHeight);
		  
		
	}
	if(flag==1&&sel_obj.belongto=="acm"){
		devClone.find(".servers .server-type:eq(1)").trigger('click');
		devClone.find(".servers .server-type").unbind('click');//修改时移除点击函数，禁止更换服务器类型
		devClone.find("[href='#href1']").removeClass().addClass("done").attr("isdone",1);
		devClone.find("[href='#href2']").removeClass().addClass("selected").attr("isdone",1);
		step = 2;
		devClone.find(".prev").removeClass("disabled");
		devClone.find(".finish").removeClass("disabled");
		devClone.find(".next").addClass("disabled");
		devClone.find(".step1").hide();
		devClone.find(".step2").show();
		$.ajax({
			url:'/auth/sysconfig/getwangyulist.do',
			type:'get',
			success:function(data){
				var result = JSON.parse(data);
				devClone.find("#netZone").empty();
				var options="";
				$.each(result,function(index,value){
					options+="<option value='"+value.netZoneId+"'>"+value.name+"</option>";
				});
				devClone.find("#netZone").append(options);
			
				devClone.find("#netZone").append("<option value='null'>无</option>");
				devClone.find("#netZone").val(sel_obj.netZoneId+"");
				
			}
		});

		 var textWidth = devClone.find("[name=port]").css("width");
		    var textHeight = devClone.find("[name=port]").css("height");
		    devClone.find("[name=netZone]").css("width", textWidth);
		    devClone.find("[name=netZone]").css("height", textHeight);
		  
		
	}


}

function remote_set(sel_tr,all_tr,sel_obj_arr,sel_obj){
	if(sel_obj==undefined){
		alert("请选择一个服务器");
		return;
	}

	var data = JSON.stringify(sel_obj);
	var formdata = "condition="+data;
	$.ajax({
		url:'/auth/server/remoteconfig.do',
		type:'post',
		data:formdata,
		success:function(data){
			var result = JSON.parse(data);
			if(result.success){
				alert("配置成功");
			}
			else{
				alert(result.msg);
			}
		}
		
	});
}



