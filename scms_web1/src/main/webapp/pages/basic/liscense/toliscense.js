/**
 * liscence信息显示和弹窗
 */
$("#versionLiscense").click(function(){
	var devClone = $("#exampleModal").clone(true);

	devClone.on('shown.bs.modal',function(){
	})
	devClone.modal({backdrop: 'static', keyboard: false}).on('hidden.bs.modal',function(event){
		$(this).remove();
	});
	$.ajax({
		
		url:"/license/licenseinfo",
		type:'GET',
		dataType:'json',
		success:function(data){
			if(data.success){
				devClone.find("#s1").text(data.object.s1);
				devClone.find("#s2").text(data.object.s2);
				devClone.find("#s3").text(data.object.s3);
				devClone.find("#s4").text(data.object.s4);
				devClone.find("#s5").text(data.object.s5);
				devClone.find("#s6").text(data.object.s6);
				devClone.find("#s7").text(data.object.s7);
				devClone.find("#s8").text(data.object.s8);
			}
			else{
				alert("服务器异常，请联系管理员");
			}
		}
	});
	
	
});