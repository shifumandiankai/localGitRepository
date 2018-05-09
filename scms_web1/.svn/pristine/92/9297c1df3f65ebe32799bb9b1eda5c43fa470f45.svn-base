
/**
 * [shown 弹出对话框执行的方法]
 * @param  {[type]} devClone [description]
 * @param  {[type]} operId   [description]
 * @param  {[type]} sel_obj  [description]
 * @return {[type]}          [description]
 */
var shown= function(devClone,operId,sel_obj){
	devClone.find('.btn-primary').on('click',registerRole);	//绑定角色添加或修改事件
    //用户名光标锁定，有输入新的用户名
 	devClone.find('input[name=userName]').on('change, keyup', function () {
 		devClone.find("#loginnameNotice").hide();
    });
   	//用户名光标移出后验用户名
   	devClone.find('input[name=userName]').on("blur", function () {
   		var val = $(this).val();
   		if(val != ''){
   			verifyUsername(val); 
   		}
    });
    //备注信息光标锁定
 	devClone.find('textarea[name=note]').on('change, keyup', function () {
 		devClone.find("#noteNotice").hide();
    });
   	//备注信息光标
   	devClone.find('textarea[name=note]').on("blur", function () {
   		var val = $(this).val();
   		if(val != ''){
   			verifyNote(val); 
   		}
    });
   	
    //验证备注信息
   	function verifyNote(value) {
   		var errMsg =checkStrMaxLen(value)
   		if(errMsg != 100){
        	devClone.find('.point-out').hide();
            devClone.find("#noteNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
   			return false;
   		}
   		return true;
   	}
   	
    //验证角色名称
    function verifyUsername(value) {
        var errMsg = checkRoleName(value);
        if (errMsg != 100) {
        	devClone.find('.point-out').hide();
            devClone.find("#loginnameNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
            return false;
        }
        return true;
    }
    //验证备注信息
    
    function registerRole(event){
       var username = devClone.find("input[name=userName]").val();
       if (!verifyUsername(username)) { //验证用户名
           event.stopImmediatePropagation(); //阻止提交数据
           return false;
       }
       var note =devClone.find("textarea[name=note]").val();
       if(!verifyNote(note)){	//验证备注信息
    	   event.stopImmediatePropagation(); //阻止提交数据
    	   return false;
       }
    	
    }

}

var config = $.extend({},{
	btns: new Array({'id':'10001','name':'add','fun':null},	//需要显示的按钮
				{'id':'10002','name':'edit',"fun":null},
				{'id':"10003",'name':'del',"fun":null},
				{'id':"10004",'name':'权限配置',"fun":set_auth}),
	search:new Array({'type':'search','placeholder':'角色名称','url':null,'index':0}), //查询条件
	id:"example1",	//dataTable ID
	addDiv:"exampleModal", //新增弹出的DIV ID
	editDiv:"exampleModal",	//编辑弹出的DIV ID
	getUrl:"/role/getRoleInfo.do",	//获取数据请求路径
	delUrl:"/role/delRole.do",	//删除数据请求路径
	saveUrl:"/role/saveRole.do",	//保存数据请求路径
	columns: new Array(	//绑定数据的列
			"bimUserId","userName","inId","inName","createTime","updateTime","note"),
	defs:new Array("bimUserId","inId"),
	add_shown:shown, //弹出新增或编辑对话框后执行的方法,
});	
wade.libs.datatable(config);	//初始化datatable




/**
 * 配置权限信息
 * @param sel_tr
 * @returns
 */
function set_auth(sel_tr,all_tr,sel_obj_arr,sel_obj){
	//TODO 
	if(sel_obj==null){
		alert("请先选择角色");
		return;
	}
	$("[href='#funauth']").click();
	 var devClone = $("#permissionModel");
	 devClone.modal({backdrop: 'static', keyboard: false});
		if(!isManager){
			 devClone.find(".btn-primary").remove();
		}
	    var tree1 = devClone.find(".authTree");
	    treeid = new Date().getTime().toString();
	    tree1.attr("id", treeid+"authTree");
	    if(pfm){
	    	var tree2 = devClone.find(".typeTree");
		    var tree3 = devClone.find(".churukouTree");
		    tree2.attr("id", treeid+"typeTree");
		    tree3.attr("id", treeid+"churukouTree");
	    }
	    if(acm){
	    	  
		    var tree4 = devClone.find(".areaTree");
		    tree4.attr("id", treeid+"areaTree");
	    }
	    if(bim){
	    	var tree5 = devClone.find(".deptTree");
	    	 tree5.attr("id", treeid+"deptTree");
	    }
	    
	    
	   
	 
	   
	    devClone.on('hidden.bs.modal',
	    function(event) {
	       // $(this).remove();
	    });
	    var ztreeConf1 = $.extend({},
	    {
	        id: tree1.attr('id'),
	        url: "/role/permissionconfig.do?roleid="+sel_obj.bimUserId,
	        // 获取所有节点
	        btns: [],
	        expand:false,
	        setting: {
	            data: {
	                simpleData: {
	                    enable: true
	                    // 简单数据
	                }
	            },
	            view: {
	                selectedMulti: false
	            },
	            check:{
	            	enable:true,
	            	chkboxType :{ "Y" : "ps", "N" : "ps" },
	            	chkStyle: "checkbox"
	            }
	        }
	    });
	    wade.libs.tree(ztreeConf1);
	    if(pfm){
	    //车辆类型权限
	    var ztreeConf2 = $.extend({},
	    	    {
	    	        id: tree2.attr('id'),
	    	        url: "/role/selectcartype.do?roleid="+sel_obj.bimUserId,
	    	        // 获取所有节点
	    	        btns: [],
	    	        setting: {
	    	            data: {
	    	                simpleData: {
	    	                    enable: true
	    	                    // 简单数据
	    	                }
	    	            },
	    	            view: {
	    	                selectedMulti: false
	    	            },
	    	            check:{
	    	            	enable:true,
	    	            	chkboxType :{ "Y" : "ps", "N" : "ps" },
	    	            	chkStyle: "checkbox"
	    	            }
	    	        }
	    	    });
	    //出入口权限
	    var ztreeConf3 = $.extend({},
	    		{
	    		    id: tree3.attr('id'),
	    		    url: "/role/getchurukou.do?roleid="+sel_obj.bimUserId,
	    		    // 获取所有节点
	    		    btns: [],
	    		    setting: {
	    		        data: {
	    		            simpleData: {
	    		                enable: true
	    		                // 简单数据
	    		            }
	    		        },
	    		        view: {
	    		            selectedMulti: false
	    		        },
	    		        check:{
	    	            	enable:true,
	    	            	chkboxType :{ "Y" : "s", "N" : "p" },
	    	            	chkStyle: "checkbox"
	    	            }
	    		    },
	    		});
	    
	    wade.libs.tree(ztreeConf2);
	    wade.libs.tree(ztreeConf3);
	    }
	    
	    if(acm){
	    //门禁区域权限
	    var ztreeConf4 = $.extend({},
	    	    {
	    	        id: tree4.attr('id'),
	    	        url: "/role/area/permissionconfig.do?roleid="+sel_obj.bimUserId,
	    	        // 获取所有节点
	    	        btns: [],
	    	        setting: {
	    	            data: {
	    	                simpleData: {
	    	                    enable: true
	    	                    // 简单数据
	    	                }
	    	            },
	    	            view: {
	    	                selectedMulti: false
	    	            },
	    	            check:{
	    	            	enable:true,
	    	            	chkboxType :{ "Y" : "ps", "N" : "ps" },
	    	            	chkStyle: "checkbox"
	    	            }
	    	        }
	    	    });
	    wade.libs.tree(ztreeConf4);
	    }
	    if(bim){
	    //部门权限
	    var ztreeConf5 = $.extend({},
	    		{
	    		    id: tree5.attr('id'),
	    		    url: "/role/dept/permissionconfig.do?roleid="+sel_obj.bimUserId,
	    		    // 获取所有节点
	    		    btns: [],
	    		    setting: {
	    		        data: {
	    		            simpleData: {
	    		                enable: true
	    		                // 简单数据
	    		            }
	    		        },
	    		        view: {
	    		            selectedMulti: false
	    		        },
	    		        check:{
	    	            	enable:true,
	    	            	chkboxType :{ "Y" : "s", "N" : "p" },
	    	            	chkStyle: "checkbox"
	    	            }
	    		    },
	    		});
	    wade.libs.tree(ztreeConf5);
	    }
	   
	   
	    devClone.find(".btn-primary").unbind("click").click(function(){
	    	if($("a[href='#funauth']").parent().hasClass("active")){
	    	if(confirm("您确认修改功能权限吗？")){
	    		var treeObj = $.fn.zTree.getZTreeObj(tree1.attr('id'));
	    		var checkedArr = treeObj.getCheckedNodes(true);
	    		var s = "";
	    		
	    		$.each(checkedArr,function(index,value){
	    			if(s==""){
	    				
	    				s+=value.id
	    			}
	    			else{
	    				s+=","+value.id
	    			}
	    		});
	    		
	    		$.ajax({
	    			url:"/role/permissionsave.do",
	    			type:'post',
	    			data:"authids="+s+"&roleid="+sel_obj.bimUserId,
	    			dataType:'json',
	    			success:function(data){
	    				if(data.success){
	    					wade.libs.alert('修改成功',4,devClone.find('div:first'));
	    					 //devClone.modal('hide');
	    				}
	    				else{
	    					wade.libs.alert(data.msg,1,devClone.find('div:first'));
	    				}
	    			}
	    		});
	    	}
	    	}
	    	else if($("a[href='#typeauth']").parent().hasClass("active")){
	    		if(confirm("您确认修改车辆类型权限吗？")){
		    		var treeObj = $.fn.zTree.getZTreeObj(tree2.attr('id'));
		    		var checkedArr = treeObj.getCheckedNodes(true);
		    		var s = "";
		    		
		    		$.each(checkedArr,function(index,value){
		    			if(s==""){
		    				
		    				s+=value.id
		    			}
		    			else{
		    				s+=","+value.id
		    			}
		    		});
		    		
		    		$.ajax({
		    			url:"/role/cartypepermissionsave.do",
		    			type:'post',
		    			data:"authids="+s+"&roleid="+sel_obj.bimUserId,
		    			dataType:'json',
		    			success:function(data){
		    				if(data.success){
		    					wade.libs.alert('修改成功',4,devClone.find('div:first'));
		    					 //devClone.modal('hide');
		    				}
		    				else{
		    					wade.libs.alert(data.msg,1,devClone.find('div:first'));
		    				}
		    			}
		    		});
		    	}
	    	}else if($("a[href='#churukouauth']").parent().hasClass("active")){
	     		if(confirm("您确认修改出入口权限吗？")){
		    		var treeObj = $.fn.zTree.getZTreeObj(tree3.attr('id'));
		    		var checkedArr = treeObj.getCheckedNodes(true);
		    		var s = "";
		    		
		    		$.each(checkedArr,function(index,value){
		    			if(s==""){
		    				
		    				s+=value.id
		    			}
		    			else{
		    				s+=","+value.id
		    			}
		    		});
		    		
		    		$.ajax({
		    			url:"/role/churukoupermissionsave.do",
		    			type:'post',
		    			data:"authids="+s+"&roleid="+sel_obj.bimUserId,
		    			dataType:'json',
		    			success:function(data){
		    				if(data.success){
		    					wade.libs.alert('修改成功',4,devClone.find('div:first'));
		    					 //devClone.modal('hide');
		    				}
		    				else{
		    					wade.libs.alert(data.msg,1,devClone.find('div:first'));
		    				}
		    			}
		    		});
		    	}
	    		
	    		
	    	}
	    	else if($("a[href='#areaauth']").parent().hasClass("active")){
	     		if(confirm("您确认修改门禁权限吗？")){
		    		var treeObj = $.fn.zTree.getZTreeObj(tree4.attr('id'));
		    		var checkedArr = treeObj.getCheckedNodes(true);
		    		var s = "";
		    		
		    		$.each(checkedArr,function(index,value){
		    			if(s==""){
		    				
		    				s+=value.id
		    			}
		    			else{
		    				s+=","+value.id
		    			}
		    		});
		    		
		    		$.ajax({
		    			url:"/role/area/permissionsave.do",
		    			type:'post',
		    			data:"authids="+s+"&roleid="+sel_obj.bimUserId,
		    			dataType:'json',
		    			success:function(data){
		    				if(data.success){
		    					wade.libs.alert('修改成功',4,devClone.find('div:first'));
		    					 //devClone.modal('hide');
		    				}
		    				else{
		    					wade.libs.alert(data.msg,1,devClone.find('div:first'));
		    				}
		    			}
		    		});
		    	}
	    		
	    		
	    	}
	    	else if($("a[href='#deptauth']").parent().hasClass("active")){
	     		if(confirm("您确认修改部门权限吗？")){
		    		var treeObj = $.fn.zTree.getZTreeObj(tree5.attr('id'));
		    		var checkedArr = treeObj.getCheckedNodes(true);
		    		var s = "";
		    		
		    		$.each(checkedArr,function(index,value){
		    			if(s==""){
		    				
		    				s+=value.id
		    			}
		    			else{
		    				s+=","+value.id
		    			}
		    		});
		    		
		    		$.ajax({
		    			url:"/role/dept/permissionsave.do",
		    			type:'post',
		    			data:"authids="+s+"&roleid="+sel_obj.bimUserId,
		    			dataType:'json',
		    			success:function(data){
		    				if(data.success){
		    					wade.libs.alert('修改成功',4,devClone.find('div:first'));
		    					 //devClone.modal('hide');
		    				}
		    				else{
		    					wade.libs.alert(data.msg,1,devClone.find('div:first'));
		    				}
		    			}
		    		});
		    	}
	    		
	    		
	    	}

	    });
}

