<%@ page contentType="text/html; charset=UTF-8"%>
<%if(request.getHeader("X-Requested-With")!=null){
	response.setStatus(999);
} %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>登录/ASSAS综合管理平台</title>
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"name="viewport">
<%@ include file="common/jsp/common_css.jsp"%>  <!-- 引用样式文件 -->
</head>
<body class="hold-transition login-page">
	<div class="login-box">
		<!-- <div class="login-logo">
			<a href="index.html" style="color: #000066"><b>Wadejerry<br></b>安防综合管理云平台</a>
		</div> -->
		<div class="login-box-body">
			<!--yystart,链接一张Wadejerry.png图片  -->
			<!-- <p class="login-box-msg">欢迎登录</p> -->
			<img src="dist/img/wadejerry.png" class="login-box-msg" />
			<!--yyend  -->
			<form method="post" id="loginform" action="index.do">
				<div class="form-group has-feedback">
					<input type="text" class="form-control" id="userName"
						placeholder="用户名" required="required"
						oninvalid="setCustomValidity('请输入用户名');"
						oninput="setCustomValidity('');"> <span
						class="glyphicon glyphicon-envelope form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" class="form-control" placeholder="密码"
						id="password"> <span
						class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="row">
					<div class="col-xs-8">
						<div class="checkbox icheck">
							<label> <input type="checkbox" id ="rememberMe"> 记住我
							</label>
						</div>
					</div>
					<div class="col-xs-4">
						<button type="submit" class="btn btn-primary btn-block btn-flat"
							onclick="return submitFunc();">登录</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script src="plugins/jQuery/jquery-2.2.3.min.js"></script>
	<!-- Bootstrap 3.3.6 -->
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<!-- iCheck -->
	<script src="plugins/iCheck/icheck.min.js"></script>
	<script src="plugins/md5/md5.js"></script>
	<script src="plugins/jQueryCookie/jquery.cookie.js"></script>
    <script>
	    $(function() {
	    	$('input').iCheck({
	    		checkboxClass : 'icheckbox_square-blue',
	    		radioClass : 'iradio_square-blue',
	    		increaseArea : '20%' // optional
	    	});
	    	var rememberMe = $.cookie("scms_chk");
	    	if (rememberMe) {
	    		$("#rememberMe").iCheck('check'); 
	    	}
	    });
	
	    function submitFunc() {
	    	var username = $.trim($("#userName").val());
	    	if (username == "") {
	    		$("#userName").val("");
	    		return true;
	    	}
	    	var user = new Object();
	    	user.userName = $("#userName").val();
	    	user.password = hex_md5($("#password").val());
	    	user.time = new Date().getTime();
	    	user.rememberMe = $("#rememberMe").is(":checked");
	    	var result = $.ajax({
				type: "POST",
	    		url : "checkLogin.do",
	    		async : "false",
	    		data : "condition=" + JSON.stringify(user),
	    		success : function(result) {
	    			obj = JSON.parse(result);
	    			if (obj.success) {		
	    				if(user.rememberMe){	//保存选中状态
	    					$.cookie('scms_chk',user.rememberMe.toString() , {expires: 7});
	    				}else{
	    					$.cookie('scms_chk',null);
	    				}    			
	    				var rememberMe = $.cookie("scms_chk");
	    				window.location.href = "index.do";
	    			} else {
	    				alert(obj.msg+"!"); // TODO 使用dialog提示
	    			}
	    		}
	    	});
	    	return false;
	    }
    </script>   
</body>
</html>
