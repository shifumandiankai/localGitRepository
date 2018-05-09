<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<% 
Cookie[] cookie=request.getCookies();
 boolean flag=false;
if(cookie!=null&&cookie.length>0){
	
	 for(Cookie temp:cookie){
		 if(temp.getName().equals("layout-boxed")&&temp.getValue().equals("true")){
		
			flag=true;
		    break;
		}
		
	} 
	
}	
	if(flag){
	%>
	 <body class="hold-transition skin-green-light  sidebar-mini layout-boxed"> 
	 <%
	}
	else{
	 %>
		 <body class="hold-transition skin-green-light  sidebar-mini "> 
		<%
	}
	%> 