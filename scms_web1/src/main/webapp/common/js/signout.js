//验证两次密码是否输入一致  register
function chkTwicePassword(password1, password2,div) {
    if (password1 == password2) {
        $("#confirmpasswordNotice").hide();
        return true;
    }
    var msg = checkPasswordtwo(password1, password2);
    if (msg != 100) {
    	div.find('.point-out').hide();
    	div.find("#confirmpasswordNotice").html('<span class="point-box errorbgTop dropdown"><i class="sprite icon_arrow_up2 pa"></i>' + msg + '</span>').show();
        return false;
    }
}
  //验证密码规则  register
function checkPassword(password,div) {
    var result = chkPassword(password);
    if (result != 100) {
    	div.find('.point-out').hide();
    	div.find("#passwordNotice").html('<span class="point-box errorbgTop dropdown"><i class="sprite icon_arrow_up2 pa"></i>' + result + '</span>').show();
        return false;
    }
    div.find("#passwordNotice").hide();
    return true;
}

//密码光标锁定，有输入新的密码
	$('#password1').on('change keyup', function () {
		$("#passwordNotice").hide();
		$("#confirmpasswordNotice").hide();
 });
	//密码光标移出后验密码
	$('#password1').on("blur", function () {
		var val = $(this).val();
		if(val != ''){
			checkPassword(val,$("#mima"));
		}
 });

//密码光标锁定，有输入新的密码
	$('#password2').on('change keyup', function () {
		$("#confirmpasswordNotice").hide();
 });
	//密码光标移出后验密码
	$('#password2').on("blur", function () {
		var val = $(this).val();
		if(val != ''){
			 var password = $('#password1').val();
			chkTwicePassword(password,val,$("#mima")); 
		}
 });

$("#signout").on('click',function(){
	var bool = confirm("确认退出系统吗？");
	if(bool){
		$.ajax({
			url:"/logout",
			success:function(){
				window.location.href="/login";
				$.cookie("SESSIONID",'',{expires:-1,path:'/'});
			}
		});
	}
});

window.onload = function () { 
    new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
}

$("#imgdiv").click(function(event){
	if($("#imgstatus").attr('value')=='0'){//切换为显示
		$("#photo").show();
		$("#imgstatus").attr('value',1);
		if($("#xiugaimima").attr("value")==1){
			$("#xiugaimima").attr("value","0").text("点击修改密码");
			$("#mima").hide();
			
		}
		//$("#photo").text("关闭更改头像");
		
	}else{//切换为隐藏
		$("#imgstatus").attr('value',0);
		$("#photo").hide();
		$("#imgShow").attr("src",$("#user-image").attr("src"));
		$("#up_img").val("");
		//$("#photo").text("点击更改头像");
	}
	
	
	event.stopPropagation();
});


$("#upload").click(function(){
	if($("#up_img").val()==""){
	alert("请选择一张图片");
	return;
}
	$('#uploadForm').submit();
});

$('#uploadForm').submit(function(event) {
    event.preventDefault();
    var form = $(this);
    var formData = new FormData(this);
    //console.log(formData);
    $.ajax({
        type: form.attr('method'),
        url: form.attr('action'),
        data: formData,
        contentType: false,
        cache: false,
        processData:false,
        success:function(data) {
        var result = JSON.parse(data);
        if (result.success) {
            alert("恭喜！头像修改成功！");
            //$("#photo").click();
           $("#user-image").attr("src",result.msg);
           $("#imgShow").attr("src",result.msg);
           $("#up_img").val("");
           $("#photo").hide();
        } else {
        	alert("恭喜！头像修改失败！");
        }}
    }) ;
    
});


$("#xiugaimima").on("click",function(event){
	if($(this).attr('value')=='0'){//切换为显示
		$("#mima").show();
		$(this).attr('value',1);
		$("#chongzhi").click();
		if($("#imgstatus").val()==1){
			$("#imgstatus").val("0");
			$("#photo").hide();
		}
		$(this).text("关闭修改密码");
	}else{//切换为隐藏
		$(this).attr('value',0);
		$("#mima").hide();
		$(this).text("点击修改密码");
		
	}
	event.stopPropagation();
});

$("#chongzhi").click(function(event){
	$("#password1").val("");
	$("#password2").val("");
	$("#lastPassword").val("");
	$(".point-out").hide();
	event.stopPropagation();
});

$(".dropdown-menu").on("click",function(event){
	event.stopImmediatePropagation();
});

$("#mimatijiao").click(function(event){
	var lastPassword = hex_md5($("#lastPassword").val());
     var password = $("#password1").val();
     var confirmpassword = $("#password2").val();
     
     if (!checkPassword(password,$("#mima"))){	//检查密码
         return false;
     }
	 if (!chkTwicePassword(password, confirmpassword,$("#mima"))){	//检查两次输入的密码是否一致
    	 	return false;
	 }
	 if(!confirm("您确定要修改密码吗？")){
		 return ;
	 }
	 var userId = $("[name='userID']").val();
	 $.ajax({	//获取角色数据
	        url: "/user/changePassword2.do",
	        dataType:"json",
	        data:"lastPassword="+lastPassword+"&userid="+userId+"&newPassword="+hex_md5(password),
	        success: function (result) {
	        	if(result.success){
	        		wade.libs.alert('密码修改成功'); 
	        		$("#chongzhi").click();
	        		$("#mima").hide();
	        		$("#xiugaimima").attr("value",'0').text("点击修改密码");
	        	}else {
	        		alert(result.msg);	
	        	}
	        }
	    }); 
	 
});