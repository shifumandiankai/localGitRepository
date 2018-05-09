$(".btn-primary").click(function(){
	
	var formData = new FormData($("form")[0]);

	var fileName = $("[name=liscense]").val();
	if(fileName.substring(fileName.length-7)!='license')
		{
		alert("license文件格式错误");
		return;
		}
	$.ajax({
		url:"/LiscenseImport",
		type:'POST',
		data:formData,
		dataType:'json',
		success:function(data){
			if(data.success){
				alert(data.msg+"！重新登录生效！");
				window.location.href="/login";
			}
			else{
				alert(data.msg);
			}
		},
		processData:false,
		contentType:false
			
	});
	
	
});