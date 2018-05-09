var wade = wade || {};
wade.role = wade.role || {};







//提交校验
$("#submit").on('click',register);

function register(event){
	var scmsPayUrl=$('input[name="scmsPayUrl"]').val();
	var successfulPayOffTime=$('input[name="successfulPayOffTime"]').val();
	re=/^[1-9]\d*$/;
	if(scmsPayUrl!=''&&!RegExpObj.url(scmsPayUrl)){
		var errMsg='平台url格式不对';
		$('#scmsPayUrlNotice').html('<span class="point-box errorbgTop dropdown" ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
		 event.stopImmediatePropagation(); //阻止提交数据
		 return false;
	}
	
	if(successfulPayOffTime==''||!re.test(successfulPayOffTime)){
		var errMsg='请输入大于0的整数';
		$('#SuccessfulPayOffTimeNotice').html('<span class="point-box errorbgTop dropdown" ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
		 event.stopImmediatePropagation(); //阻止提交数据
		 return false;
	}
	
	$.ajax({
		url : '/saveSysParams.do',
		type : 'post',
		data :{sysparams : JSON.stringify($("#paramsform").serializeObject())},
		success : function(data) {
			var result = JSON.parse(data);
			if (result.success) {
				wade.libs.alert('保存成功');
			} else {
				wade.libs.alert('保存失败', 0);
			}
		}
	});
	
};


/*// 保存按钮
$("#submit").click(function() {
	$.ajax({
		url : '/saveSysParams.do',
		type : 'post',
		data :{sysparams : JSON.stringify($("#paramsform").serializeObject())},
		success : function(data) {
			var result = JSON.parse(data);
			if (result.success) {
				wade.libs.alert('保存成功');
			} else {
				wade.libs.alert('保存失败', 0);
			}
		}
	});
});*/

//手动校时
$("#handleTiming").click(function(){
	var btn = $(this).button('loading');
	$.ajax({
		url:"/timeCalibrating.do",
		success:function(data) {
			var result = JSON.parse(data);	
			if(result.success) {
			 	wade.libs.alert('校时成功');
			} else {
				wade.libs.alert(result.msg,0);
			}
			btn.button('reset'); 
		}
	});
});

//获取系统参数
function getSysParams(){
	
	$.ajax({
		url : '/getSysParams.do',
		type: 'post',
		success : function(data) {
			var result = JSON.parse(data);
			if (result.success) {
				
				$.each(result.object,function(name,value) { //遍历对象属性和值
					wade.libs.datatable_fill_form_data(name,value,$("#paramsform").parent()); //填充数据到表单
				});
			} else {
				wade.libs.alert(result.msg, 0);
			} 
		}
	});
}
$(getSysParams());


//url失去焦点
$('input[name="scmsPayUrl"]').blur(function(){
	$('.point-out').hide();
});
//获取焦点
$('input[name="scmsPayUrl"]').focus(function(){
	$('.point-out').hide();
});
