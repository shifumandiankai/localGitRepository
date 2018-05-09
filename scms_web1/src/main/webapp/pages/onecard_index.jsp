<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>ASSAS综合管理平台</title>
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<%@ include file="/common/jsp/common_css.jsp"%>
</head>
 <body class="hold-transition skin-green-light  sidebar-mini "> 
<%@ include file="/common/jsp/top.jsp"%>
<%@ include file="onecard_menu.jsp"%>
	<div class="wrapper"></div>
</body>
 <%@ include file="/common/jsp/common_js.jsp"%>
<script type="text/javascript">
	   //根据url获取页面信息
	   function pageLoad(url,obj) {
	    	$.get(url,function(data){
	    		$('.wrapper').html(data);
    		});
	    	//移除所有选中样式
	    	$(obj).parent().siblings().removeClass("active");
	    	//当前菜单选中
	    	$(obj).parent().addClass("active").parents(".treeview").addClass("active"); 	
	   }
	   var hash =window.location.hash;
	   //页面刷新
	   if(hash && $("a[href='"+hash+"']")) {
		   $("a[href='"+hash+"']").click();
	   } else {
		   //主页跳转
		   var menu_id = wade.libs.getQueryString("menu_id");
			$("#"+menu_id+" ul li:first-child a").click();
	   }
</script>
</html>
