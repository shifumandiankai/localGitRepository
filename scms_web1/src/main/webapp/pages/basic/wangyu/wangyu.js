var config = $.extend({},{
	btns: new Array({'id':'10001','name':'add','fun':null},	//需要显示的按钮
			{'id':'10002','name':'edit',"fun":null},
			{'id':"10003",'name':'del',"fun":null}),
			search:new Array(), //查询条件
			id:"t_wangyu",	//dataTable ID
			addDiv:"exampleModal", //新增弹出的DIV ID
			editDiv:"exampleModal",	//编辑弹出的DIV ID
			getUrl:"/auth/sysconfig/getwangyuinfos.do",	//获取数据请求路径
			delUrl:"/auth/sysconfig/delwangyu.do",	//删除数据请求路径
			saveUrl:"/auth/sysconfig/savewangyu.do",	//保存数据请求路径
			columns: new Array("name","url","port","netZoneId"),
			defs:new Array("netZoneId"),
			add_shown:shown, //弹出新增或编辑对话框后执行的方法,
			beforedel:beforedel
});	
wade.libs.datatable(config);	//初始化datatable

function beforedel(){

		if(confirm("删除该网域将清除服务器在该网域配置的地址。您确认要删除吗？")){
			return true;
		}
		else{
			return false;
		}
	
}
function shown(devClone,flag,sel_obj){
	
	devClone.find('input[name=name]').on("blur",
			function() {
		var val = $(this).val();
		if(val!="")
		verifyServerName(val);

	}).on('change keydown keyup',function(){$(this).parent().find('.point-out').hide();});
	function verifyServerName(val){

		if (val.trim()=="") {
			devClone.find('.point-out').hide();
			devClone.find("#nameNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + "不能为空" + '</span>').show();
			
			return false;
		}
		else if(val.indexOf(" ")!=-1){
			devClone.find('.point-out').hide();
			devClone.find("#nameNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + "不能含空格" + '</span>').show();
			return false;

		}
		else if(val.length>20){
			devClone.find('.point-out').hide();
			devClone.find("#nameNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + "请输入1-20个字符" + '</span>').show();
			return false;
		}
		return true;

	}
	//验证ip
	devClone.find('input[name=url]').on("blur",
			function() {
		var val = $(this).val();
		if(val!="")
		verifyIp(val);

	}).on('change keydown keyup',function(){$(this).parent().find('.point-out').hide();});
	function verifyIp(val){

		if (checkIp(val)!=100) {
			devClone.find('.point-out').hide();
			devClone.find("#urlNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + checkIp(val) + '</span>').show();

			return false;
		}
		return true;

	}
	//验证端口号
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
		return true;

	}
	devClone.find(".btn-primary").on("click",function(event) {
		
		var val1 = devClone.find('input[name=name]').val();
		if (!verifyServerName(val1)) {
			event.stopImmediatePropagation(); // 阻止提交数据
			return false;
		}
		var val2 = devClone.find('input[name=url]').val();
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