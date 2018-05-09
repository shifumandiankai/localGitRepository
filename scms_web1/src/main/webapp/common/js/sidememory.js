$(function(){
	
	//menu记录
	var pageid=$(".breadcrumb li:last").text();
	//alert(pageid);
	$("li:contains('"+pageid+"')").addClass("active").parents(".treeview").addClass("active");
	//$.cookie('layout-boxed','true',{expires:365,path:'/'});
	//读取cookie，设置页面
	var value;
	if((value=$.cookie('fixed'))!=null)
	{
		//alert(0);
			$("input[data-layout='fixed']").click();
		
				
	}
	if((value=$.cookie('layout-boxed'))!=null)
	{
		//alert(1);
			//$("input[data-layout='layout-boxed']").click();
	
			
	}
	if((value=$.cookie('sidebar-collapse'))!=null)
	{	
		//alert(2);
			$("input[data-layout='sidebar-collapse']").click();
		
				
	}
	if((value=$.cookie('control-sidebar-open'))!=null)
	{
	
			$("input[data-controlsidebar='control-sidebar-open']").click();
	
			
	}
	if((value=$.cookie('toggle'))!=null)
	{
		//alert(3);
			$("input[data-sidebarskin='toggle']").click();
		
				
	}
/*if((value=$.cookie('layout-boxed'))!=null)
	{
		//alert(0);
			$("input[data-layout='layout-boxed']").click();
	
			
	}*/
/*if((value=$.cookie('sidebar-collapse'))!=null)
	{	
			$("input[data-layout='sidebar-collapse']").click();
		
				
	}*/
//	if((value=$.cookie('expandOnHover'))!=null)
//	{
//		
//			$("input[data-enable='expandOnHover']").click();
//		
//				
//	}
/*if((value=$.cookie('control-sidebar-open'))!=null)
	{
	
			$("input[data-controlsidebar='control-sidebar-open']").click();
	
			
	}
	if((value=$.cookie('toggle'))!=null)
	{
		
			$("input[data-sidebarskin='toggle']").click();
		
				
	}
	*/
	
	//保存到cookie
	$("input[data-layout='fixed']").on('click',function(){
		
		
		if($(this).prop('checked')){
			
			$.cookie('fixed','true',{expires:365,path:'/'});
			
		}
		else{	
			$.cookie('fixed','',{expires:-1,path:'/'});
		}
		
	});
	$("input[data-layout='layout-boxed']").on('click',function(){
		
		
		if($(this).prop('checked')){
			
			$.cookie('layout-boxed','true',{expires:365,path:'/'});
		}
		else{
			$.cookie('layout-boxed','',{expires:-1,path:'/'});
		}
		
	});
	
	$("input[data-layout='sidebar-collapse']").on('click',function(){
		
		
		if($(this).prop('checked')){
			
			$.cookie('sidebar-collapse','true',{expires:365,path:'/'});
			
		}
		else{
			
			$.cookie('sidebar-collapse','',{expires:-1,path:'/'});
		}
		
	});
//	$("input[data-enable='expandOnHover']").on('click',function(){
//		
//		
//		if($(this).prop('checked')){
//			
//			$.cookie('expandOnHover','true',{expires:365,path:'/'});
//			
//		}
//		else{
//			
//			$.cookie('expandOnHover','',{expires:-1});
//		}
//		
//	});
	$("input[data-controlsidebar='control-sidebar-open']").on('click',function(){
		
		
		if($(this).prop('checked')){
			
			$.cookie('control-sidebar-open','true',{expires:365,path:'/'});
			
		}
		else{
			
			$.cookie('control-sidebar-open','',{expires:-1,path:'/'});
		}
		
	});
	$("input[data-sidebarskin='toggle']").on('click',function(){
		
		
		if($(this).prop('checked')){
			
			$.cookie('toggle','true',{expires:365,path:'/'});
			
		}
		else{
			
			$.cookie('toggle','',{expires:-1,path:'/'});
		}
		
	});
	

	
});