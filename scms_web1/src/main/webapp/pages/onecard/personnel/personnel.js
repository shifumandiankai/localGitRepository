var wade = wade || {};
wade.role = wade.role || {};
var config1 = new Object();
var config2 = new Object();// 批量开卡
var NodeArray = new Array(5);// 节点
var personArray = new Array(3);// 存 人员编号，姓名，部门

/*window.onload = function () { 
    new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
}*/






var ztreeConf = $.extend({}, {
	id : "tree",
	url : "/personnel/getptree.do",
	// 获取所有节点
	btns : [ "add", "delete", "edit", "cancel" ],
	DatatableId : "personnelexample",
	adddiv : "adddept",
	search : "searchdiv",
	search_hide : "searchHide",
	searchurl : "/personnel/searchByDept.do",
	addurl : "/personnel/adddept.do",
	add_shown : add_shownTree,
	deleteurl : "/personnel/deleteTreeNode.do",
	delnode: deldept,
	// oncancel: cancel,
	// afterdelete:afterdel,
	setting : {
		data : {
			simpleData : {
				enable : true
			// 简单数据
			}
		},
		view : {
			selectedMulti : false
		},
		callback : {
			onClick : nodeClick
		}
	},

});

// $.fn.zTree.init($("#"+ztreeConf.id),ztreeConf.setting, ztreeConf.zNodes);
wade.libs.tree(ztreeConf);

window.onload = function() {
	new uploadPreview({
		UpBtn : "up_img",
		DivShow : "imgdiv",
		ImgShow : "imgShow"
	});
}

function afterdel() {
	$("[name=searchValue0_search_hidden]").val("").trigger('keyup.DT');
}

// 点击节点是触发事件,触发表格
function nodeClick(event, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj(treeId);
	var node = zTree.getSelectedNodes()[0];
	if (node.spId != -1) {
		// 点击的是默认部门节点 显示所有员工信息
		$("[name=searchValue0_search_hidden]").val(node.id).trigger('keyup.DT');
	} else {
		// 点击的不是默认部门节点
		$("[name=searchValue0_search_hidden]").val('').trigger('keyup.DT');
	}

}
// 点击cancel回调函数
function cancel() {

}

// 弹出部门树节点的信息

function add_shownTree(devClone, treeid, flag) {

	var selnode = wade.tree.selectNode(treeid);
	if (selnode == undefined) {
		devClone.modal('hide');
		wade.libs.alert("请选择需要操作的部门", 2);
		return false;

	}

	var zTree = $.fn.zTree.getZTreeObj(treeid);
	var node = zTree.getSelectedNodes()[0];
	// /console.log(selnode);
	var id = node.id;// 树的id
	devClone.find('input[name="bimDeptId"]').val(id);
	devClone.find('input[name="deptName"]').on('blur', function() {
		var deptName = devClone.find('input[name="deptName"]').val();
		$.ajax({
			url : '/personnel/selectpinyin.do',
			data : {
				val : deptName
			},
			dataType : 'json',
			success : function(data) {
				var result = eval(data);
				devClone.find('input[name="pinyin"]').val(result.pinyin);
			}

		});

	});
	if (flag == 0) {
		// 添加树节点时上级部门就是当前选中的节点
		devClone.find('input[name="type"]').val(flag);// 设置为添加
		devClone.find('input[name="inCode"]').val(node.name);

	} else {
		// 修改数节点
		if (selnode.pId == null) {
			if (selnode.pName == null) {
				devClone.find('input[name="inCode"]').val("无");
			} else {
				devClone.find('input[name="inCode"]').val(selnode.pName);
			}

		} else {
			var parentnode = zTree.getNodeByParam("id", selnode.pId, null);
			devClone.find('input[name="inCode"]').val(parentnode.name);
		}

		devClone.find('input[name="deptCode"]').val(node.deptCode);
		devClone.find('input[name="deptName"]').val(node.name);
		devClone.find('input[name="pinyin"]').val(node.pinyin);
		devClone.find('input[name="type"]').val(flag);// 设置为修改
		// 修改树节点
		devClone.find('input[name="bimDeptId"]').val(id);
		// $("#" + config.id).DataTable().ajax.reload();
	}

	devClone.find('#submit').click(register);

	function register(event) {
		var code = devClone.find('input[name="deptCode"]').val();
		var name = devClone.find('input[name="deptName"]').val();
		var pinyin = devClone.find('input[name="pinyin"]').val();
		var msg = null;
		if (code == '') {
			msg = '部门编号不能为空';
			devClone
					.find("#deptCodeNotice")
					.html(
							'<span class="point-box errorbgTop dropdown"><i class="sprite icon_arrow_up2 pa"></i>'
									+ msg + '</span>').show();
			event.stopImmediatePropagation(); // 阻止提交数据
			return false;
		} else if (!RegExpObj.isNumber(code)) {
			msg = '部门编号只能是数字';
			devClone
					.find("#deptCodeNotice")
					.html(
							'<span class="point-box errorbgTop dropdown"><i class="sprite icon_arrow_up2 pa"></i>'
									+ msg + '</span>').show();
			event.stopImmediatePropagation(); // 阻止提交数据
			return false;
		} else if (checkAllName(name, '部门名称') != 100) {
			msg = checkAllName(name, '部门名称');
			devClone
					.find("#deptNamerNotice")
					.html(
							'<span class="point-box errorbgTop dropdown"><i class="sprite icon_arrow_up2 pa"></i>'
									+ msg + '</span>').show();
			event.stopImmediatePropagation(); // 阻止提交数据
			return false;
		} else if (checkAllName(pinyin, '拼音') != 100) {
			msg = checkAllName(pinyin, '拼音');
			devClone
					.find("#pinyinNotice")
					.html(
							'<span class="point-box errorbgTop dropdown"><i class="sprite icon_arrow_up2 pa"></i>'
									+ msg + '</span>').show();
			event.stopImmediatePropagation(); // 阻止提交数据
			return false;
		}

	}
	devClone.find('input').on('focus', function() {
		devClone.find(".point-out").hide();

	});

}

//删除部门节点
function deldept(node){
	
	if(node.spId==-1){
		wade.libs.dialog("该节点是一级节点，不能删除");
		return false;
	}
	
	return true;
}





// 需要显示的按钮
config1.btns = new Array({
	'id' : '10001',
	'name' : '添加',
	'fun' : addPersonInfo
	//'fun' : null
},{
	'id' : "10002",
	'name' : 'edit',
	"fun" : null
},
{
	'id' : "10003",
	'name' : 'del',
	"fun" : null
},{
	'id' : '10004',
	'name' : '操作',
	'fun' : showPersonInfo
}, 
 {
	'id' : "10005",
	'name' : '移至',
	"fun" : move
}, {
	'id' : "10006",
	'name' : '批量开卡',
	"fun" : batchcard
}, {
	'id' : "10007",
	'name' : '身份管理',
	"fun" : identitymanagement
}, {
	'id' : "10008",
	'name' : '导入',
	"fun" : importInfo
}, {
	'id' : "10009",
	'name' : '导出',
	"fun" : exportInfo
});

config1.search = new Array(

{
	'type' : 'search',
	'placeholder' : '编号',
	'url' : null,
	'index' : 1
}, {
	'type' : 'search_hidden',
	'placeholder' : '',
	'url' : null,
	'index' : 0
});

/*
 * config2.btns =[]; config2.search = new Array( { 'type' : 'search',
 * 'placeholder' : '卡号', 'url' : null, 'index' : 2 }, { 'type' :
 * 'search_hidden', 'placeholder' : null, 'url' : null, 'index' : 1 } );
 */

//填充form表单数据
function fillFormData(name, value, div) {
	var form = div.find('#form1');
	var form2 = div.find('#form2');
	var form3 = div.find('#form3');
	var input = form.find('input[name=' + name + '][type=text]').val(value);
	var input = form.find('input[name=' + name + '][type=hidden]').val(value);
	form.find('textarea[name=' + name + ']').val(value);
	form.find('select[name=' + name + ']').val(value).trigger("change"); // select2
	if(name=='sex'){
		//alert(value);
		if(value=='男'){
			form.find('#radio1').attr('checked', true);
			form.find('#radio2').attr('checked', false);
		}else{
			form.find('#radio2').attr('checked', true);
			form.find('#radio1').attr('checked', false);
		}
	}
	form2.find('input[name=' + name + '][type=text]').val(value);
	if(name=='photo1'){
		form.find('#imgShow').attr('src',value);
	}
	
	
	

	if(name=='fingerprint1Num'){
		var finger=form3.find('input[name="fingerindex"]');
		form3.find('input[name="fingerprint1Num"]').val(value);
		//alert(0);
		$.each(finger,function(index,obj){
			var indexid=$(this).attr('id');
			var fingerindex=indexid.substring(12,13);
			
			if(value==fingerindex){
				//alert($(this).attr('id'));
				
				$(this).parent().addClass("do");
				//form3.find('.fingerprint-finger-undo.do').on('click',bindClick);
				
				$(this).val('-2')
		 				
		 		
			}
		});
	}
	
	if(name=='fingerprint2Num'){
		var finger=form3.find('input[name="fingerindex"]');
		form3.find('input[name="fingerprint2Num"]').val(value);
		//alert(0);
		$.each(finger,function(index,obj){
			var indexid=$(this).attr('id');
			var fingerindex=indexid.substring(12,13);
			
			if(value==fingerindex){
				//alert($(this).attr('id'));
				
				//$(this).parent().css('background-color','red');
				$(this).parent().addClass("do");
				//form3.find('.fingerprint-finger-undo.do').on('click',bindClick);
				
				$(this).val('-2')
		 				
		 		
			}
		});
	}
	
	if(name='fingerprint1'){
		form3.find('input[name="fingerprint1"]').val(value);
	}
	if(name='fingerprint2'){
		form3.find('input[name="fingerprint2"]').val(value);
	}
	
	
	
	
	
	
	/*function   bindClick(){
		alert(0);
		form3.find('input[name="fingerindex"]').val('');
			$(this).children().val('-2');
		
	}
	*/
	
};




//添加人员信息
function addPersonInfo() {	

	


var zTree = $.fn.zTree.getZTreeObj("tree");
var node = zTree.getSelectedNodes()[0];
// console.log(node);
if (node == undefined) {
	wade.libs.alert("请选择需要添加员工的部门!", 2);
	return;
}
var devClone = $("#personModal").clone(true);
devClone.modal({
	backdrop : 'static',
	keyboard : false
}).on('hidden.bs.modal', function(event) {
	$(this).remove();
});

devClone.find('li[name="basicInfo"]').attr('class', 'active');



	devClone.find("#up_img").on("change",function(){
	var objUrl = getObjectURL(this.files[0]) ; //获取图片的路径，该路径不是图片在本地的路径
	if (objUrl) {
		devClone.find("#imgShow").attr("src", objUrl) ; //将图片路径存入src中，显示出图片
	}
	});
	
	
	 
	//建立一個可存取到該file的url
	function getObjectURL(file) {
	var url = null ;
	if (window.createObjectURL!=undefined) { // basic
	url = window.createObjectURL(file) ;
	} else if (window.URL!=undefined) { // mozilla(firefox)
	url = window.URL.createObjectURL(file) ;
	} else if (window.webkitURL!=undefined) { // webkit or chrome
	url = window.webkitURL.createObjectURL(file) ;
	}
	return url ;
	}



 //默认显示部门名称 
 var zTree = $.fn.zTree.getZTreeObj("tree");
 var node=zTree.getSelectedNodes()[0]; 

 devClone.find('input[name="deptName"]').val(node.name); 
 
 //初始化select控件
$.ajax({
		url : '/personnel/selectCateType.do',
		dataType : 'json',
		success : function(data) {
			var result = eval(data);
			for(var i in result){
				if(result[i].id!=undefined){
					
					if(result[0].id==-1&&result.length==1){
	   	                 //devClone.find("*[name=custom3]").attr("disabled", "disabled");
	   	                 devClone.find("*[name=custom3]").append('<option value="'+result[0].id+'" ">'+result[0].text+'</option>');
	   	                 	break;
	   	                 }
						else{
							devClone.find("*[name=custom3]").append('<option value="'+result[i].id+'">'+result[i].text+'</option>');
						}		
				}			
			}
		}

	});
 
 
 
 
 
 
// 动态显示拼音
devClone.find('input[name="personName"]').on('blur', function() {
	var personName = devClone.find('input[name="personName"]').val();
	$.ajax({
		url : '/personnel/selectpinyin.do',
		data : {
			val : personName
		},
		dataType : 'json',
		// async : false,//设置为同步操作就可以给全局变量赋值成功
		success : function(data) {
			var result = eval(data);
			devClone.find('input[name="pinyin"]').val(result.pinyin);
		}

	});
});

// 用来改变tab样式
function ChangeTab(devClone) {
	devClone.find('#allInfo').removeClass('active in');
	devClone.find('#fingerInfo').removeClass('active in');
	devClone.find('#basicInfo').addClass('active in');
	devClone.find('li[name="allInfo"]').removeClass('active in');
	devClone.find('li[name="fingerInfo"]').removeClass('active in');
	devClone.find('li[name="basicInfo"]').addClass('active in');
}
//改变详细信息Tab
function allInfoTab(devClone){
	devClone.find('#basicInfo').removeClass('active in');
	devClone.find('#fingerInfo').removeClass('active in');
	devClone.find('#allInfo').addClass('active in');
	devClone.find('li[name="basicInfo"]').removeClass('active in');
	devClone.find('li[name="fingerInfo"]').removeClass('active in');
	devClone.find('li[name="allInfo"]').addClass('active in');
}


ChangeTab(devClone);

devClone.find('#jb').click(function() {
	devClone.find('#allInfo').removeClass('active in');
	devClone.find('#fingerInfo').removeClass('active in');
	devClone.find('#basicInfo').addClass('active in');

});
devClone.find('#xx').click(function() {
	devClone.find('#basicInfo').removeClass('active in');
	devClone.find('#fingerInfo').removeClass('active in');
	devClone.find('#allInfo').addClass('active in');

});
devClone.find('#zw').click(function() {
	devClone.find('#allInfo').removeClass('active in');
	devClone.find('#basicInfo').removeClass('active in');
	devClone.find('#fingerInfo').addClass('active in');
	

});







/** ********************** 校验开始 */
devClone.find("#submit").click(register);// 绑定提交事件
function register(event) {
	var personCode = devClone.find('input[name="personCode"]').val();
	// alert(personCode);
	var personName = devClone.find('input[name="personName"]').val();
	var touxiang = devClone.find('#up_img').val();
	var certId = devClone.find('*[name="certId"]').val();
	// var birthday=devClone.find('*[name="birthday"]').val();
	var deptName = devClone.find('input[name="deptName"]').val();
	var mail = devClone.find('input[name="mail"]').val();
	var phone = devClone.find('input[name="phone"]').val();
	// var jobDate=$('input[name="jobDate"]').val();
	// var resignationDate=$('input[name="resignationDate"]').val();
	var msg = null;
	if (!RegExpObj.isNumber(personCode)) {
		// alert(personCode);
		msg = '人员编号为数字';
		devClone
				.find("#personCodeNotice")
				.html(
						'<span class="point-box errorbgTop dropdown"><i class="sprite icon_arrow_up2 pa"></i>'
								+ msg + '</span>').show();
		event.stopImmediatePropagation(); // 阻止提交数据
		ChangeTab(devClone);
		return false;
	}
	if (!RegExpObj.isName(personName)) {
		// alert(personName);
		msg = '姓名只能为中文';
		devClone
				.find("#personNameNotice")
				.html(
						'<span class="point-box errorbgTop dropdown"><i class="sprite icon_arrow_up2 pa"></i>'
								+ msg + '</span>').show();
		event.stopImmediatePropagation(); // 阻止提交数据
		ChangeTab(devClone);
		return false;
	}

	/*if(IDCard(certId)!= 100){
		var msg = IDCard(certId);
		devClone.find("#certIdNotice").html('<span class="point-box errorbgTop dropdown"><i class="sprite icon_arrow_up2 pa"></i>'+ msg + '</span>').show();
		event.stopImmediatePropagation(); // 阻止提交数据
		ChangeTab(devClone);
		return false;
	}
	*/
	
	/*if (checkPhone(phone) != 100) {
		var msg = checkMobile(phone);
		devClone.find("#phoneNotice").html('<span class="point-box errorbgTop dropdown"><i class="sprite icon_arrow_up2 pa"></i>'+ msg + '</span>').show();
		event.stopImmediatePropagation(); // 阻止提交数据
		ChangeTab(devClone);
		return false;
	}*/
	if (!RegExpObj.email(mail)&&mail!='') {
		// alert(personName);
		msg = '邮箱格式不正确';
		devClone
				.find("#emailNotice")
				.html(
						'<span class="point-box errorbgTop dropdown"><i class="sprite icon_arrow_up2 pa"></i>'
								+ msg + '</span>').show();
		event.stopImmediatePropagation(); // 阻止提交数据
		allInfoTab(devClone);
		return false;
	}
	
	

}
// 获取焦点
devClone.find('input[name="personCode"]').on('focus', function() {
	devClone.find("#personCodeNotice").hide();
	devClone.find("#personNameNotice").hide();
});

devClone.find('input[name="personName"]').on('focus', function() {
	devClone.find("#personCodeNotice").hide();
	devClone.find("#personNameNotice").hide();
});

devClone.find('input[name="certId"]').on('focus', function() {
	devClone.find("#certIdNotice").hide();
	//devClone.find("#personNameNotice").hide();
});

devClone.find('input[name="phone"]').on('focus', function() {
	devClone.find("#phoneNotice").hide();
	//devClone.find("#personNameNotice").hide();
});

devClone.find('input[name="mail"]').on('focus', function() {
	devClone.find("#emailNotice").hide();
});

/** ******************校验结束 */

devClone.find(".btn-primary").click(
		function() { // 保存按钮添加事件
			/*
			 * if(devClone.find("#file").val()==""){ alert("请选择上传的头像！");
			 * return; }
			 */
			// var formData= new FormData(devClone.find("#form1")[0]);
			var formData = new FormData(
					devClone.find('*[name="formname"]')[0]);
			var jsonuserinfo = devClone.find('*[name="formname"]')
					.serializeObject();
			var condition = JSON.stringify(jsonuserinfo);
			formData.append('condition', condition);
			// 提交表单
			$.ajax({
				url : '/personnel/savepersonnelInfo.do',
				type : 'post',
				data : formData, // 默认获取div下第一个from数据
				mimeType : "multipart/form-data",
				contentType : false,
				cache : false,
				processData : false,
				success : function(data) {
					result = JSON.parse(data);
					if (result.success) {
						devClone.modal("hide");
						wade.libs.alert('添加成功');
						if ($("#personnelexample_wrapper").find(
								".paginate_button.active").size() > 0) {
							$("#personnelexample_wrapper").find(
									".paginate_button.active").click(); // 点击分页按钮获取新数据
						} else {
							$("#personnelexample_wrapper").find(
									'#personnelexample th:first').click(); // 数据表中没有数据，通过排序列获取数据
						}
					} else {

						wade.libs.alert(result.msg,0,devClone);
					}
				}
			});
		});

 
     fingerdev=devClone.find('#form3');
     var count=0;

//注册指纹
devClone.find("#zc").click(function(){
	if(count>1){
		alert('最多可以注册两个指纹');
		return false;
	}
	
	
	
	
	var fingerhid=fingerdev.find('input[type="hidden"][value="-1"]');
	if(fingerhid.length==0){
		alert('选择要录的手指');
		return false;
	}
	var loadClone = $("#loadModal").clone(true);
	var id= 0;
	var ocx = document.getElementById("csocx");
	
	 startFingerRegion();
	 
	 
	 
	 
	//采集指纹
	 function startFingerRegion() {
	 	 //发送开始采集命令
	 	
	 	var strCmd = ocx.SyCreateBstrCommand("0120", "");
	 	 var strFrame =ocx.SyGetAndSendUSBData(strCmd);
	 	//alert(0);
	 	 //命令发送成功
	 	 if(ocx.ErrCode == 0) {
	 		
	 		

			loadClone.modal({
				backdrop : 'static',
				keyboard : false
			}).on('hidden.bs.modal', function(event) {
				$(this).remove();
			});
	 		 
	 		 
	 		 
	 		 //获取采集结果
	 	     id=self.setInterval(getResult,1000);
	 	 } else {
	 		//设备连接失败
	 		alert("设备连接失败");
	 	 }
	 }
	 	
	//获取指纹注册结果
	  function getResult() {
		strCmd = ocx.SyCreateBstrCommand("0220", "");
	 	strFrame = ocx.SyGetAndSendUSBData(strCmd);
	 	//console.log(strFrame);
	 	if (ocx.ErrCode != 0) {
	 		
	 		loadClone.modal("hide");
	 		
	 		alert("指纹采集失败");
	 		
	 		stopFingerRegion();
	 	} else if (strFrame.substring(34,36) !="FF") {
	 		loadClone.modal("hide");
	 		//alert("指纹采集成功," + strFrame.substring(34));
	 		alert("指纹采集成功");
	 		count+=1;
	 		stopFingerRegion();
	 		if(count==1){
	 			/*var flag=fingerdev.find('input[name="flag"]').val();
		 		fingerdev.find('input[name="fingerprint1"]').val(strFrame.substring(34));
		 		fingerdev.find('input[name="fingerprint1Num"]').val(flag);
		 		fingerdev.find('div[name="fingerprint'+flag+'"]').addClass("do");*/
	 			var flag=fingerdev.find('input[name="flag"]').val();
	 			var  val=fingerdev.find('input[name="fingerindex"][value="-1"]').attr('id').substring(12,13);
		 		fingerdev.find('input[name="fingerprint1"]').val(strFrame.substring(34));
		 		fingerdev.find('input[name="fingerprint1Num"]').val(flag);
		 		//fingerdev.find('#fingerprint['+val+']').val('-2');
		 		fingerdev.find('input[name="fingerindex"][value="-1"]').val('-2');
		 		fingerdev.find('div[name="fingerprint'+flag+'"]').addClass("do");
		 		
	 		}else if(count==2){
	 			/*var flag=fingerdev.find('input[name="flag"]').val();
		 		fingerdev.find('input[name="fingerprint2"]').val(strFrame.substring(34));
		 		fingerdev.find('input[name="fingerprint2Num"]').val(flag);
		 		fingerdev.find('div[name="fingerprint'+flag+'"]').addClass("do");*/
	 			
	 			var flag=fingerdev.find('input[name="flag"]').val();
	 			var  val=fingerdev.find('input[name="fingerindex"][value="-1"]').attr('id').substring(12,13);
		 		fingerdev.find('input[name="fingerprint2"]').val(strFrame.substring(34));
		 		fingerdev.find('input[name="fingerprint2Num"]').val(flag);
		 		//fingerdev.find('#fingerprint['+val+']').val('-2');
		 		fingerdev.find('input[name="fingerindex"][value="-1"]').val('-2');
		 		fingerdev.find('div[name="fingerprint'+flag+'"]').addClass("do");
	 		}
	 		/*
	 		devClone.find('.fingerprint-finger-undo.do').on('click',function(){
	 			//alert(0);
	 			var finger=$(this);
	 			fingerdev.find('input[name="fingerindex"]').val('');
	 			finger.children().val('-2');
	 			
	 			
	 			
	 			
	 			
	 			
	 		});*/
	 		
	 	}
		 
		  
	  }

	//停止指纹采集
	  function stopFingerRegion() {
	 	window.clearInterval(id);
	  }
	 
	 
	 
	 
	
});


//删除指纹
//devClone.find('#sc').click(function(){
devClone.find('#sc').click(function(){
	/*var length=fingerdev.find('input[name="fingerindex"][value="-2"]').length;
	if(length==0){
		alert('请选择已注册的指纹');
		return false;
	}
	    var indexname=fingerdev.find('input[name="fingerindex"][value="-2"]').attr('id');
		var fingerindex=indexname.substring(12,13);
		//alert(fingerindex);
		var fingerprint1Num=devClone.find('input[name="fingerprint1Num"]').val();
		var fingerprint2Num=devClone.find('input[name="fingerprint2Num"]').val();
		if(fingerprint1Num==fingerindex){
			devClone.find('input[name="fingerprifnt1Num"]').val('');
			devClone.find('input[name="fingerprint1"]').val('');
		}else if(fingerprint2Num==fingerindex){
			devClone.find('input[name="fingerprint2Num"]').val('');
			devClone.find('input[name="fingerprint2"]').val('');
		}
	
	
	fingerdev.find('input[name="fingerindex"][value="-2"]').parent().removeClass('do');
	count-=1;*/
	
	var length=fingerdev.find('input[name="fingerindex"][value="-3"]').length;
	if(length==0){
		alert('请选择已注册的指纹');
		return false;
	}
	    var indexname=fingerdev.find('input[name="fingerindex"][value="-3"]').attr('id');
		var fingerindex=indexname.substring(12,13);
		//alert(fingerindex);
		var fingerprint1Num=devClone.find('input[name="fingerprint1Num"]').val();
		var fingerprint2Num=devClone.find('input[name="fingerprint2Num"]').val();
		alert(fingerindex)
		
		//alert();
		if(fingerprint1Num==fingerindex){
			devClone.find('input[name="fingerprint1Num"]').val('');
			devClone.find('input[name="fingerprint1"]').val('');
		}else if(fingerprint2Num==fingerindex){
			devClone.find('input[name="fingerprint2Num"]').val('');
			devClone.find('input[name="fingerprint2"]').val('');
		}
	
	
	fingerdev.find('input[name="fingerindex"][value="-3"]').parent().removeClass('do');
	count-=1;
	fingerdev.find('input[name="fingerindex"][value="-3"]').val('');
	
	
});


//删除所有指纹
devClone.find('#qb').click(function(){

	fingerdev.find('.fingerprint-finger-undo.do').removeClass('do');
	fingerdev.find('input[name="fingerprint1"]').val('');
	fingerdev.find('input[name="fingerprint1Num"]').val('');
	fingerdev.find('input[name="fingerprint2"]').val('');
	fingerdev.find('input[name="fingerprint2Num"]').val('');
	fingerdev.find('input[name="flag"]').val('');
	fingerdev.find('input[name="fingerindex"]').val('');
	
	count=0;
	
});


devClone.find('.fingerprint-finger-undo').click(function(){
	/*alert(0);
	var finger=$(this);
	fingerdev.find('input[name="fingerindex"]').val('');
	$(this).children().val(-1);
	var indexname=finger.children().attr('id');
	var fingerindex=indexname.substring(12,13);
	fingerdev.find('input[name="flag"]').val(fingerindex);*/
	
	var finger=$(this);
	var fv = finger.children().eq(0).val();
	if(fv==''){
		fingerdev.find('input[name="fingerindex"][value=-1]').val('');
		finger.children().eq(0).val('-1')
	}else if(fv=='-2'){
		fingerdev.find('input[name="fingerindex"][value=-3]').val('-2');
		finger.children().val('-3');
	}
	
	var indexname=finger.children().attr('id');
	var fingerindex=indexname.substring(12,13);
	fingerdev.find('input[name="flag"]').val(fingerindex);
	
	
	
});




}




//编辑人员信息
config1.shown_by_photo=function(devClone,sel_obj){

//alert(0);
	//加载select控件
	$.ajax({
		url : '/personnel/selectCateType.do',
		dataType : 'json',
		async : false,
		success : function(data) {
			//var result = JSON.parse(data);
			var result = eval(data);
			//console.log(result);
			for(var i in result){
				if(result[i].id!=undefined){
					
					if(result[0].id==-1&&result.length==1){
						//alert(0);
						//devClone.find("*[name=custom3]").attr("disabled", "disabled");
						devClone.find("*[name=custom3]").append('<option value="'+result[0].id+'" ">'+result[0].text+'</option>');
	   	                 	break;
	   	                 }
						else{
							devClone.find("*[name=custom3]").append('<option value="'+result[i].id+'">'+result[i].text+'</option>');
						}
					
				}
				
				
				
			}
		}

	});
 
	//点击指纹
devClone.find('.fingerprint-finger-undo').click(function(){
		
		var finger=$(this);
		var fv = finger.children().eq(0).val();
		if(fv==''){
			fingerdev.find('input[name="fingerindex"][value=-1]').val('');
			finger.children().eq(0).val('-1')
		}else if(fv=='-2'){
			//alert(0);
			fingerdev.find('input[name="fingerindex"][value=-3]').val('-2');
			finger.children().val('-3');
		}
		
		var indexname=finger.children().attr('id');
		var fingerindex=indexname.substring(12,13);
		fingerdev.find('input[name="flag"]').val(fingerindex);
		
	});
	
	
	
	$.each(sel_obj,function(name,value){						
		
		if(name=='photo1'&&value!=''){
			devClone.find('input[name="photoSrc"]').val(value);
		}/*else if(name=='custom3'){
		  $(this).find('option[value="'+value+'"]')
		}*/
		
		fillFormData(name,value,devClone);
	});
	
	devClone.find("#up_img").on("change",function(){
		var objUrl = getObjectURL(this.files[0]) ; //获取图片的路径，该路径不是图片在本地的路径
		if (objUrl) {
			devClone.find("#imgShow").attr("src", objUrl) ; //将图片路径存入src中，显示出图片
		}
		});
		
	//devClone.find().val();
		 
	
	
		//建立一個可存取到該file的url
		function getObjectURL(file) {
		var url = null ;
		if (window.createObjectURL!=undefined) { // basic
		url = window.createObjectURL(file) ;
		} else if (window.URL!=undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file) ;
		} else if (window.webkitURL!=undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file) ;
		}
		return url ;
		}



	 //默认显示部门名称 
	// var zTree = $.fn.zTree.getZTreeObj("tree");
	 //var node=zTree.getSelectedNodes()[0]; 

	 //devClone.find('input[name="deptName"]').val(node.name); 
	 
	 //初始化select控件
	
	 
	 
	 
	 
	 
	// 动态显示拼音
	devClone.find('input[name="personName"]').on('blur', function() {
		var personName = devClone.find('input[name="personName"]').val();
		$.ajax({
			url : '/personnel/selectpinyin.do',
			data : {
				val : personName
			},
			dataType : 'json',
			// async : false,//设置为同步操作就可以给全局变量赋值成功
			success : function(data) {
				var result = eval(data);
				devClone.find('input[name="pinyin"]').val(result.pinyin);
			}

		});
	});

	// 用来改变tab样式
	function ChangeTab(devClone) {
		devClone.find('#allInfo').removeClass('active in');
		devClone.find('#fingerInfo').removeClass('active in');
		devClone.find('#basicInfo').addClass('active in');
		devClone.find('li[name="allInfo"]').removeClass('active in');
		devClone.find('li[name="fingerInfo"]').removeClass('active in');
		devClone.find('li[name="basicInfo"]').addClass('active in');
	}
	//改变详细信息Tab
	function allInfoTab(devClone){
		devClone.find('#basicInfo').removeClass('active in');
		devClone.find('#fingerInfo').removeClass('active in');
		devClone.find('#allInfo').addClass('active in');
		devClone.find('li[name="basicInfo"]').removeClass('active in');
		devClone.find('li[name="fingerInfo"]').removeClass('active in');
		devClone.find('li[name="allInfo"]').addClass('active in');
	}


	ChangeTab(devClone);

	devClone.find('#jb').click(function() {
		devClone.find('#allInfo').removeClass('active in');
		devClone.find('#fingerInfo').removeClass('active in');
		devClone.find('#basicInfo').addClass('active in');

	});
	devClone.find('#xx').click(function() {
		devClone.find('#basicInfo').removeClass('active in');
		devClone.find('#fingerInfo').removeClass('active in');
		devClone.find('#allInfo').addClass('active in');

	});
	devClone.find('#zw').click(function() {
		devClone.find('#allInfo').removeClass('active in');
		devClone.find('#basicInfo').removeClass('active in');
		devClone.find('#fingerInfo').addClass('active in');
	});

	






	/** ********************** 校验开始 */
	devClone.find("#submit").click(register);// 绑定提交事件
	function register(event) {
		var personCode = devClone.find('input[name="personCode"]').val();
		// alert(personCode);
		var personName = devClone.find('input[name="personName"]').val();
		var touxiang = devClone.find('#up_img').val();
		var certId = devClone.find('*[name="certId"]').val();
		// var birthday=devClone.find('*[name="birthday"]').val();
		var deptName = devClone.find('input[name="deptName"]').val();
		var mail = devClone.find('input[name="mail"]').val().trim();
		var phone = devClone.find('input[name="phone"]').val();
		// var jobDate=$('input[name="jobDate"]').val();
		// var resignationDate=$('input[name="resignationDate"]').val();
		var msg = null;
		if (!RegExpObj.isNumber(personCode)) {
			// alert(personCode);
			msg = '人员编号为数字';
			devClone
					.find("#personCodeNotice")
					.html(
							'<span class="point-box errorbgTop dropdown"><i class="sprite icon_arrow_up2 pa"></i>'
									+ msg + '</span>').show();
			event.stopImmediatePropagation(); // 阻止提交数据
			ChangeTab(devClone);
			return false;
		}
		if (!RegExpObj.isName(personName)) {
			// alert(personName);
			msg = '姓名只能为中文';
			devClone
					.find("#personNameNotice")
					.html(
							'<span class="point-box errorbgTop dropdown"><i class="sprite icon_arrow_up2 pa"></i>'
									+ msg + '</span>').show();
			event.stopImmediatePropagation(); // 阻止提交数据
			ChangeTab(devClone);
			return false;
		}

		/*if(IDCard(certId)!= 100){
			var msg = IDCard(certId);
			devClone.find("#certIdNotice").html('<span class="point-box errorbgTop dropdown"><i class="sprite icon_arrow_up2 pa"></i>'+ msg + '</span>').show();
			event.stopImmediatePropagation(); // 阻止提交数据
			ChangeTab(devClone);
			return false;
		}
		*/
		
		/*if (checkPhone(phone) != 100) {
			var msg = checkMobile(phone);
			devClone.find("#phoneNotice").html('<span class="point-box errorbgTop dropdown"><i class="sprite icon_arrow_up2 pa"></i>'+ msg + '</span>').show();
			event.stopImmediatePropagation(); // 阻止提交数据
			ChangeTab(devClone);
			return false;
		}*/
		//console.log(mail);
		if (!RegExpObj.email(mail)&&mail!='') {
			// alert(personName);
			msg = '邮箱格式不正确';
			devClone
					.find("#emailNotice")
					.html(
							'<span class="point-box errorbgTop dropdown"><i class="sprite icon_arrow_up2 pa"></i>'
									+ msg + '</span>').show();
			event.stopImmediatePropagation(); // 阻止提交数据
			allInfoTab(devClone);
			return false;
		}
		
		

	}
	// 获取焦点
	devClone.find('input[name="personCode"]').on('focus', function() {
		devClone.find("#personCodeNotice").hide();
		devClone.find("#personNameNotice").hide();
	});

	devClone.find('input[name="personName"]').on('focus', function() {
		devClone.find("#personCodeNotice").hide();
		devClone.find("#personNameNotice").hide();
	});

	devClone.find('input[name="certId"]').on('focus', function() {
		devClone.find("#certIdNotice").hide();
		//devClone.find("#personNameNotice").hide();
	});

	devClone.find('input[name="phone"]').on('focus', function() {
		devClone.find("#phoneNotice").hide();
		//devClone.find("#personNameNotice").hide();
	});

	devClone.find('input[name="mail"]').on('focus', function() {
		devClone.find("#emailNotice").hide();
	});

	/** ******************校验结束 */

	devClone.find(".btn-primary").click(
			function() { // 保存按钮添加事件
				/*
				 * if(devClone.find("#file").val()==""){ alert("请选择上传的头像！");
				 * return; }
				 */
				// var formData= new FormData(devClone.find("#form1")[0]);
				var formData = new FormData(
						devClone.find('*[name="formname"]')[0]);
				var jsonuserinfo = devClone.find('*[name="formname"]')
						.serializeObject();
				var condition = JSON.stringify(jsonuserinfo);
				formData.append('condition', condition);
				// 提交表单
				$.ajax({
					url : '/personnel/savepersonnelInfo.do',
					type : 'post',
					data : formData, // 默认获取div下第一个from数据
					mimeType : "multipart/form-data",
					contentType : false,
					cache : false,
					processData : false,
					success : function(data) {
						result = JSON.parse(data);
						if (result.success) {
							devClone.modal("hide");
							wade.libs.alert('添加成功');
							if ($("#personnelexample_wrapper").find(
									".paginate_button.active").size() > 0) {
								$("#personnelexample_wrapper").find(
										".paginate_button.active").click(); // 点击分页按钮获取新数据
							} else {
								$("#personnelexample_wrapper").find(
										'#personnelexample th:first').click(); // 数据表中没有数据，通过排序列获取数据
							}
						} else {

							wade.libs.alert(result.msg,0,devClone);
						}
					}
				});
			});

	
	  fingerdev=devClone.find('#form3');
	     //var count=0;
	     var count=devClone.find('.fingerprint-finger-undo.do').length;
	    // alert(count);
	
	devClone.find("#zc").click(function(){
		if(count==2){
			alert('最多可以注册两个指纹');
			return false;
		}
		
		
		
		
		var fingerhid=fingerdev.find('input[type="hidden"][value="-1"]');
		if(fingerhid.length==0){
			alert('选择要录的手指');
			return false;
		}
		var loadClone = $("#loadModal").clone(true);
		var id= 0;
		var ocx = document.getElementById("csocx");
		
		 startFingerRegion();
		 
		 
		 
		 
		//采集指纹
		 function startFingerRegion() {
		 	 //发送开始采集命令
		 	
		 	var strCmd = ocx.SyCreateBstrCommand("0120", "");
		 	 var strFrame =ocx.SyGetAndSendUSBData(strCmd);
		 	//alert(0);
		 	 //命令发送成功
		 	 if(ocx.ErrCode == 0) {
		 		
		 		

				loadClone.modal({
					backdrop : 'static',
					keyboard : false
				}).on('hidden.bs.modal', function(event) {
					$(this).remove();
				});
		 		 
		 		 
		 		 
		 		 //获取采集结果
		 	     id=self.setInterval(getResult,1000);
		 	 } else {
		 		//设备连接失败
		 		alert("设备连接失败");
		 	 }
		 }
		 	
		//获取指纹注册结果
		  function getResult() {
			strCmd = ocx.SyCreateBstrCommand("0220", "");
		 	strFrame = ocx.SyGetAndSendUSBData(strCmd);
		 	//console.log(strFrame);
		 	if (ocx.ErrCode != 0) {
		 		
		 		loadClone.modal("hide");
		 		
		 		alert("指纹采集失败");
		 		
		 		stopFingerRegion();
		 	} else if (strFrame.substring(34,36) !="FF") {
		 		loadClone.modal("hide");
		 		//alert("指纹采集成功," + strFrame.substring(34));
		 		alert("指纹采集成功");
		 		count+=1;
		 		stopFingerRegion();
		 		if(count==1){
		 			var flag=fingerdev.find('input[name="flag"]').val();
		 			var  val=fingerdev.find('input[name="fingerindex"][value="-1"]').attr('id').substring(12,13);
			 		fingerdev.find('input[name="fingerprint1"]').val(strFrame.substring(34));
			 		fingerdev.find('input[name="fingerprint1Num"]').val(flag);
			 		//fingerdev.find('#fingerprint['+val+']').val('-2');
			 		fingerdev.find('input[name="fingerindex"][value="-1"]').val('-2');
			 		fingerdev.find('div[name="fingerprint'+flag+'"]').addClass("do");
			 		
			 		
		 		}else if(count==2){
		 			var flag=fingerdev.find('input[name="flag"]').val();
		 			var  val=fingerdev.find('input[name="fingerindex"][value="-1"]').attr('id').substring(12,13);
			 		fingerdev.find('input[name="fingerprint2"]').val(strFrame.substring(34));
			 		fingerdev.find('input[name="fingerprint2Num"]').val(flag);
			 		//fingerdev.find('#fingerprint['+val+']').val('-2');
			 		fingerdev.find('input[name="fingerindex"][value="-1"]').val('-2');
			 		fingerdev.find('div[name="fingerprint'+flag+'"]').addClass("do");
		 		}
		 		
		 	}
			 
			  
		  }

		//停止指纹采集
		  function stopFingerRegion() {
		 	window.clearInterval(id);
		  }
		 
		 
		 
		 
		
	}); 
	
	
	
	
	//删除指纹
	//devClone.find('#sc').click(function(){
	devClone.find('#sc').click(function(){
		var length=fingerdev.find('input[name="fingerindex"][value="-3"]').length;
		if(length==0){
			alert('请选择已注册的指纹');
			return false;
		}
		    var indexname=fingerdev.find('input[name="fingerindex"][value="-3"]').attr('id');
			var fingerindex=indexname.substring(12,13);
			//alert(fingerindex);
			var fingerprint1Num=devClone.find('input[name="fingerprint1Num"]').val();
			var fingerprint2Num=devClone.find('input[name="fingerprint2Num"]').val();
			alert(fingerindex)
			
			//alert();
			if(fingerprint1Num==fingerindex){
				devClone.find('input[name="fingerprint1Num"]').val('');
				devClone.find('input[name="fingerprint1"]').val('');
			}else if(fingerprint2Num==fingerindex){
				devClone.find('input[name="fingerprint2Num"]').val('');
				devClone.find('input[name="fingerprint2"]').val('');
			}
		
		
		fingerdev.find('input[name="fingerindex"][value="-3"]').parent().removeClass('do');
		count-=1;
		fingerdev.find('input[name="fingerindex"][value="-3"]').val('');
	});


	//删除所有指纹
	devClone.find('#qb').click(function(){

		fingerdev.find('.fingerprint-finger-undo.do').removeClass('do');
		fingerdev.find('input[name="fingerprint1"]').val('');
		fingerdev.find('input[name="fingerprint1Num"]').val('');
		fingerdev.find('input[name="fingerprint2"]').val('');
		fingerdev.find('input[name="fingerprint2Num"]').val('');
		fingerdev.find('input[name="flag"]').val('');
		fingerdev.find('input[name="fingerindex"]').val('');
		
		count=0;
		
	});


	/*devClone.find('.fingerprint-finger-undo').click(function(){
		
		var finger=$(this);
		fingerdev.find('input[name="fingerindex"]').val('');
		$(this).children().val(-1);
		var indexname=finger.children().attr('id');
		var fingerindex=indexname.substring(12,13);
		fingerdev.find('input[name="flag"]').val(fingerindex);
		
	});*/

	


/*	//删除指纹
	devClone.find('#sc').click(function(){
		var fingerhid=devClone.find('input[type="hidden"][value="-1"]');
		if(fingerhid.length==0){
			alert('选择要删除的手指');
			return false;
		}
	
	});


	//删除所有指纹
	devClone.find('#qb').click(function(){

	});


	devClone.find('.fingerprint-finger-undo ').click(function(){
		 
		
		
	});*/
	
	
};






// 点击操作
function showPersonInfo(sel_tr, all_tr, sel_obj_arr, sel_obj) {
	if (sel_obj_arr.length == 0) {
		wade.libs.alert("请选择操作的人员!", 2);
		return;
	}
	var devClone = $("#cardModal").clone(true);
	devClone.modal('show').on('hidden.bs.modal', function(event) {
		$(this).remove();
	});
	devClone.find('#li1').attr('class', 'active');
	
	$.each(sel_obj, function(name, value) {
		wade.libs.datatable_fill_form_data(name, value, devClone);
	});
	devClone.find('input[type="text"]').attr('disabled',true);
	
	var tablehtml = '';
	$.ajax({
		url : "/Card/cardInfo.do",
		data : {
			personId : sel_obj.personId
		},
		async : false,
		success : function(data) {
			result = JSON.parse(data);
			//console.log(result);
			//alert(result);
			$.each(result, function(index, value) {

				
				
				
				if (value.cardStatus == 0) {
					tablehtml +='<tr ><td>'+(index + 1) +'</td><td>'+ value.cardId +'</td><td>正常卡</td><td>'+value.useSystem+'</td><td  style="display:none;">'+value.bimCardId+'</td></tr>'
				} else {
					tablehtml +='<tr ><td>'+(index + 1) +'</td><td>'+value.cardId +'</td><td>挂失卡</td><td>'+value.useSystem+'</td><td  style="display:none;">'+value.bimCardId+'</td></tr>'
				}

			});
			devClone.find('#tr1').after(tablehtml);
			
		}
	});

	function ChangeCardTab(devClone) {
		devClone.find('#carNumber').removeClass('active in');
		devClone.find('#finger').removeClass('active in');
		devClone.find('#card').addClass('active in');
		devClone.find('#li3').removeClass('active in');
		devClone.find('#li2').removeClass('active in');
		devClone.find('#li1').addClass('active in');
	}
	
	ChangeCardTab(devClone);
	
	
	
	//点击卡片操作tab
	devClone.find('#a1').click(function() {
		//alert(0);
		devClone.find('#carNumber').removeClass('active in');
		devClone.find('#finger').removeClass('active in');
		devClone.find('#card').addClass('active in');

	});
	devClone.find('#a2').click(function() {
		devClone.find('#carNumber').removeClass('active in');
		devClone.find('#card').removeClass('active in');
		devClone.find('#finger').addClass('active in');

	});
	devClone.find('#a3').click(function() {
		devClone.find('#card').removeClass('active in');
		devClone.find('#finger').removeClass('active in');
		devClone.find('#carNumber').addClass('active in');
	});
	
	
	
	//点击卡片tr
	devClone.find('#cardtable').find("tr").bind('click',clickTr);
	
	devClone.find('#carnumbertable').find("tr").bind('click',clickcarTr);
	//点击车牌tr
	
	function clickcarTr(){
	//alert($(this).children().eq(1).html());
		if($(this).children().eq(1).html()!='授权系统'){
			devClone.find("tr").removeAttr('class');
			$(this).attr('class', 'active');
			//devClone.find('#carsqbtn').attr('disabled',false);
			devClone.find('#carnosqbtn').attr('disabled',false);
		}else{
			devClone.find("tr").removeAttr('class');
			//devClone.find('#carsqbtn').attr('disabled',true);
		    devClone.find('#carnosqbtn').attr('disabled',true);
		}
		
		
	}
	
	
	
	
	
	
	
   function clickTr(){
	   //alert(0);
	   $("tr").removeAttr('class');
		$(this).attr('class', 'active');
		var val = $(this).children('td').eq(2).html();
		if (val == '正常卡') {
			devClone.find('button[name="unnormalcard"]').attr(
					'disabled', true);
			devClone.find('button[name="normalcard"]').attr(
					'disabled', false);
		} else if(val == '挂失卡'){
			devClone.find('button[name="normalcard"]').attr(
					'disabled', true);
			devClone.find('button[name="unnormalcard"]').attr(
					'disabled', false);
		}else{
			devClone.find('button[name="normalcard"]').attr(
					'disabled', true);
			devClone.find('button[name="unnormalcard"]').attr(
					'disabled', true);
			devClone.find('#card-bind-btn').attr(
					'disabled', false);
		}
   }
	
	
	
	
	//开卡
	    devClone.find('#card-bind-btn').click(function(){
		var index=devClone.find('#cardtable').find('tr').length;
		var bindcardClone = $("#bindcardModal").clone(true);
		bindcardClone.modal('show').on('hidden.bs.modal', function(event) {
			$(this).remove();
		});
		 
		 bindcardClone.find('input[name="personId"]').val(sel_obj.personId);
		 var tablehtml='';
		 bindcardClone.find('#submit').click(function(){
			 var cardId=bindcardClone.find('input[name="cardId"]').val();
			 if(!RegExpObj.isNumber(cardId)){
				 var msg='卡号为数字';
				 bindcardClone.find('#cardIdNotice').html('<span class="point-box errorbgTop dropdown"><i class="sprite icon_arrow_up2 pa"></i>'+ msg + '</span>').show();
	             return ;
			 }
			 
			 
			 
			 var jsoninfo = bindcardClone.find("form").serializeObject();
			 var formdata = JSON.stringify(jsoninfo);
			 
			 $.ajax({
					url : "/Card/bindCardInfo.do",
					data : {
						formdata : formdata
					},
					async : false,
					success : function(data) {
						result = JSON.parse(data);
						console.log(result);
						if(result.success){
							bindcardClone.modal('hide');
							wade.libs.alert(result.msg,3,devClone);
							tablehtml +='<tr ><td>'+(index) +'</td><td>'+ cardId +'</td><td>正常卡</td><td>'+result.object.subSystemStr+'</td><td  style="display:none;">'+result.object.bimCardId+'</td></tr>'
							devClone.find('#cardtable').find('tr').eq(index-1).after(tablehtml);
							devClone.find('#cardtable').find("tr").bind('click',clickTr);
						}else{
						
							bindcardClone.modal('hide');
							wade.libs.alert(result.msg, 0,devClone);
								
						}
						
						
					}
				});
		 });
			
		});	
	

	

	
	//点击换卡
	devClone.find('#card-swap-btn').click(function(){
		var card=devClone.find('#cardtable').find(' tr.active').children('td').eq(1).html();
		var bimCardId=devClone.find('#cardtable').find(' tr.active').children('td').eq(4).html();
		var index=devClone.find('#cardtable').find('tr.active').children('td').eq(0).html();
		
		var swapcardClone = $("#swapcardModal").clone(true);
		swapcardClone.modal('show').on('hidden.bs.modal', function(event) {
			$(this).remove();
		});
		
		swapcardClone.find('input[name="oldcard"]').val(card);
		swapcardClone.find('#submit').click(function(){
			var cardId=swapcardClone.find('input[name="newcard"]').val();
			$.ajax({
				url : "/Card/updateswapCardInfo.do",
				data : {
					cardId : cardId,
					bimCardId:bimCardId
				},
				async : false,
				success : function(data) {
					result = JSON.parse(data);
					
					if(result.success){
						wade.libs.alert(result.msg,3,devClone);
						swapcardClone.modal('hide');
						devClone.find('#cardtable').find('tr').eq(index).children('td').eq(1).html(cardId);
						//devClone.find("tr").removeAttr('active');
					}else{
						wade.libs.alert(result.msg, 0,devClone);
						swapcardClone.modal('hide');	
					}
				}
			});
		});	
	});
	
	
	//点击挂失
	devClone.find('#card-loss-btn').click(function(){
		
		//var cardtr=devClone.find('#tr1');
		
		var index=devClone.find('#cardtable').find('tr.active').children('td').eq(0).html();
		var bimCardId=devClone.find('#cardtable').find('tr.active').children('td').eq(4).html();
			//alert(bimCardId);
		$.ajax({
				url : "/Card/updatelossCardInfo.do",
				data : {
					bimCardId:bimCardId
					
				},
				async : false,
				success : function(data) {
					result = JSON.parse(data);
					if(result.success){
						wade.libs.alert(result.msg,3,devClone);
						devClone.find('#cardtable').find('tr').eq(index).children('td').eq(2).html('挂失卡');
						//clickTr();
						//devClone.find("tr").removeAttr('active');
					}else{
						wade.libs.alert(result.msg, 0,devClone);
							
					}
				}
			});
		});	
	//解挂
	devClone.find('#card-unloss-btn').click(function(){
		var index=devClone.find('#cardtable').find(' tr.active').children('td').eq(0).html();
		var bimCardId=devClone.find('#cardtable').find(' tr.active').children('td').eq(4).html();
			$.ajax({
				url : "/Card/updateunlossCardInfo.do",
				data : {
					bimCardId:bimCardId
				},
				async : false,
				success : function(data) {
					result = JSON.parse(data);
					if(result.success){
						wade.libs.alert(result.msg,3,devClone);
						devClone.find('#cardtable').find('tr').eq(index).children('td').eq(2).html('正常卡');
						//devClone.find('#tr1').eq(index).children('td').eq(2).html('正常卡');
						//clickTr();
						//devClone.find("tr").removeAttr('active');
					}else{
						wade.libs.alert(result.msg, 0,devClone);
							
					}
				}
			});
		});	
	
	//退卡
	
	devClone.find('#card-return-btn').bind('click',function(){
		wade.libs.dialog('确定退卡？',function(){
			//var index=devClone.find('tr.active').children('td').eq(0).html();
			var bimCardId=devClone.find('#cardtable').find('tr.active').children('td').eq(4).html();
			var tablehtml='';
				$.ajax({
					url : "/Card/returnCardInfo.do",
					data : {
						bimCardId:bimCardId,
						personId:sel_obj.personId
					},
					async : false,
					success : function(data) {
						result = JSON.parse(data);
						if(result.success){
							wade.libs.alert(result.msg,3,devClone);
							devClone.find('#cardtable').find('tr.active').remove();
							//console.log(result);
							//devClone.find('#cardtable').find('tr:gt(0)').remove();
							/*$.each(result.object, function(index, value) {

								if (value.cardStatus == 0) {
									tablehtml +='<tr ><td>'+(index + 1) +'</td><td>'+ value.cardId +'</td><td>正常卡</td><td>'+value.useSystem+'</td><td  style="display:none;">'+value.bimCardId+'</td></tr>'
								} else {
									tablehtml +='<tr ><td>'+(index + 1) +'</td><td>'+value.cardId +'</td><td>挂失卡</td><td>'+value.useSystem+'</td><td  style="display:none;">'+value.bimCardId+'</td></tr>'
								}

							});
							devClone.find('#cardtable').find('tr').after(tablehtml);*/
							devClone.find('#cardtable').find("tr").bind('click',clickTr);
							
						}else{
							wade.libs.alert(result.msg, 0,devClone);
								
						}
					}
				});
		})
	});
	
	
	
	
	

	
	// 点击详细信息
	devClone.find('#person-info').click(function() {
		var personClone = $("#personModal").clone(true);
		personClone.modal('show').on('hidden.bs.modal', function(event) {
			$(this).remove();
		});
		$.ajax({
			url : '/personnel/selectCateType.do',
			dataType : 'json',
			async : false,
			success : function(data) {
				var result = eval(data);
				for(var i in result){
					if(result[i].id!=undefined){
						
						if(result[0].id==-1&&result.length==1){
							personClone.find("*[name=custom3]").attr("disabled", "disabled");
							personClone.find("*[name=custom3]").append('<option value="'+result[0].id+'" ">'+result[0].text+'</option>');
		   	                 	break;
		   	                 }
							else{
								personClone.find("*[name=custom3]").append('<option value="'+result[i].id+'">'+result[i].text+'</option>');
							}
						
					}
					
					
					
				}
			}

		});
		$.each(sel_obj, function(name, value) {
			fillFormData(name, value, personClone);
		});
       // console.log(sel_obj	);
		personClone.find('input').attr('disabled', true);
		personClone.find('select').attr('disabled', true);
		personClone.find('.fingerbtn').remove()
		personClone.find('#submit').remove();
		function ChangeTab(personClone) {
			personClone.find('#allInfo').removeClass('active in');
			personClone.find('#fingerInfo').removeClass('active in');
			personClone.find('#basicInfo').addClass('active in');
			personClone.find('li[name="allInfo"]').removeClass('active in');
			personClone.find('li[name="fingerInfo"]').removeClass('active in');
			personClone.find('li[name="basicInfo"]').addClass('active in');
		}

		ChangeTab(personClone);

		personClone.find('#jb').click(function() {
			personClone.find('#allInfo').removeClass('active in');
			personClone.find('#fingerInfo').removeClass('active in');
			personClone.find('#basicInfo').addClass('active in');

		});
		personClone.find('#xx').click(function() {
			personClone.find('#basicInfo').removeClass('active in');
			personClone.find('#fingerInfo').removeClass('active in');
			personClone.find('#allInfo').addClass('active in');

		});
		personClone.find('#zw').click(function() {
			personClone.find('#allInfo').removeClass('active in');
			personClone.find('#basicInfo').removeClass('active in');
			personClone.find('#fingerInfo').addClass('active in');
			

		});
	});

	
	var fingerindex=devClone.find('#fingertable').find('tr').length;
	var fingertablehtml='';
	//指纹操作
	 $.ajax({
			url : "/personnel/fingersq.do",
			data : {
				personId:sel_obj.personId
			},
			async : false,
			success : function(data) {
				result = JSON.parse(data);
				console.log(result);
				if(result!=null){
					var fingerprint1='';
					var fingerprint2='';
					var useSystem='';
					if(result.fingerprint1==null){
						fingerprint1='无';
					}else{
						fingerprint1=result.fingerprint1;
					}
					if(result.fingerprint2==null){
						fingerprint2='无';
					}else{
						fingerprint2=result.fingerprint2;
					}
					if(result.useSystem==null){
						useSystem='无';
					}else{
						useSystem=result.useSystem;
					}
					fingertablehtml +='<tr ><td>'+fingerprint1+'</td><td>'+fingerprint2+'</td><td>'+useSystem+'</td><td  style="display:none;">'+result.bimCardId+'</td></tr>'
					    devClone.find('#tr2').eq(fingerindex-1).after(fingertablehtml);
					devClone.find('#fingertable').find("tr").bind('click',clickTr);
				}
			}
		});
	

	 
	
	//点击指纹授权
	 devClone.find('#fingersqbtn').click(function(){
		 var trlength= devClone.find("#fingertable").find('tr').length;
		 if(trlength<=1){
			 alert('无指纹信息，请先添加指纹再授权');
			 return false;
		 }
		 
		    var fingersqClone =$("#fingersqModal").clone(true);
		    fingersqClone.modal('show').on('hidden.bs.modal', function(event) {
				$(this).remove();
			});
		 
		    
		    fingersqClone.find('input[name="personId"]').val(sel_obj.personId);
		    fingersqClone.find('input[name="cardId"]').val(sel_obj.personPhotoId);
		   
			 
			 
		    fingersqClone.find('#submit').bind('click',function(){
		    	 
			    var jsoninfo = fingersqClone.find("form").serializeObject();
				var formdata = JSON.stringify(jsoninfo);
			    //alert(formdata);
		    	
		    	
		    	$.ajax({
						url : "/Card/fingersqInfo.do",
						data : {
							formdata : formdata
						},
						async : false,
						success : function(data) {
							result = JSON.parse(data);
							//console.log(result);
							if(result.success){
								fingersqClone.modal('hide');
								wade.libs.alert(result.msg,3,devClone);
								devClone.find('#fingertable').find('tr').eq(1).children('td').eq(2).html(result.object.subSystemStr);
								devClone.find('#fingertable').find("tr").bind('click',clickTr);
							}else{
							
								fingersqClone.modal('hide');
								wade.libs.alert(result.msg, 0,devClone);
									
							}
							
							
						}
					});
		    	
		    	
		    });
		 
		 
	 });
	 
	 var carnumbertablehtml='';
	// alert(sel_obj.personId);
	//显示车牌授权信息
	 $.ajax({
			url : "/personnel/cardsq.do",
			data : {
				personId:sel_obj.personId
			},
			async : false,
			success : function(data) {
				result = JSON.parse(data);
				$.each(result, function(index, value) {
				 carnumbertablehtml +='<tr ><td>'+ value.cardId +'</td><td>'+value.subSystemStr+'</td><td  style="display:none;">'+value.bimCardId+'</td></tr>';
				});
				//alert(carnumbertablehtml);
				devClone.find('#tr3').after(carnumbertablehtml);
				devClone.find('#carnumbertable').find("tr").bind('click',clickcarTr);
				
			}
		});
	 
	 
	 
	 //点击车牌授权
	 devClone.find('#carsqbtn').click(function(){
		 var carnumberClone = $("#carnumberModal").clone(true);
		 carnumberClone.modal('show').on('hidden.bs.modal', function(event) {
				$(this).remove();
			});
		 
		 
		 carnumberClone.find('#submit').click(function(){
			 
			 var cardId=carnumberClone.find('input[name="cardId"]').val();
			 if(checkCarNum(cardId)!=100){
				 var result=checkCarNum(cardId);
				 carnumberClone.find("#cardIdNotice").html('<span class="point-box errorbgTop dropdown"><i class="sprite icon_arrow_up2 pa"></i>'+ result + '</span>').show();
			return false;
			 }
			 
			 carnumberClone.find('input[name="personId"]').val(sel_obj.personId);
			 var carindex=devClone.find('#carnumbertable').find('tr').length;
			 
			  var jsoninfo = carnumberClone.find("form").serializeObject();
				var formdata = JSON.stringify(jsoninfo);
			 $.ajax({
					url : "/personnel/addCarsq.do",
					data : {
						formdata : formdata
					},
					async : false,
					success : function(data) {
						result = JSON.parse(data);
						//console.log(result);
						if(result.success){
							carnumberClone.modal('hide');
							wade.libs.alert(result.msg,3,devClone);
							//devClone.find('#carnumbertable').find('tr').eq(1).children('td').eq(2).html(result.object.subSystemStr);
							var html='<tr ><td>'+ result.object.cardId +'</td><td>'+result.object.subSystemStr+'</td><td  style="display:none;">'+result.object.bimCardId+'</td></tr>';
							devClone.find('#carnumbertable').find('tr').eq(carindex-1).after(html);
							devClone.find('#carnumbertable').find("tr").bind('click',clickcarTr);
						}else{
						
							carnumberClone.modal('hide');
							wade.libs.alert(result.msg, 0,devClone);
								
						}
						
						
					}
				});
			 
			 
		 });
		  
		 
		 
		 carnumberClone.find('input[name="cardId"]').on('focus', function() {
				carnumberClone.find("#cardIdNotice").hide();
		});
		 
		 
	 });
	 
	 

	
	 
	 
	 
	 
	 
	 
	 
	 
	 //点击解除授权
	 devClone.find('#carnosqbtn').bind('click',function(){
			wade.libs.dialog('确定解除权限？',function(){
				//var index=devClone.find('tr.active').children('td').eq(0).html();
				 var bimCardId=devClone.find('#carnumbertable').find('tr.active').children('td').eq(2).html();
				var tablehtml='';
					$.ajax({
						url : "/personnel/cancelsq.do",
						data : {
							bimCardId:bimCardId,
						},
						async : false,
						success : function(data) {
							result = JSON.parse(data);
							
							if(result){
								wade.libs.alert("解除权限成功",3,devClone);
								devClone.find('#carnumbertable').find('tr.active').remove();
								devClone.find('#carnumbertable').find("tr").bind('click',clickcarTr);
								
							}else{
								wade.libs.alert("解除权限失败", 0,devClone);
									
							}
						}
					});
			})
		});
	 
	 
	 
};

// 移至
function move(sel_tr, all_tr, sel_obj_arr, sel_obj) {
	if (sel_obj_arr.length == 0) {
		wade.libs.alert("请选择需要移至的员工!", 2);
		return;
	}
	var devClone = $("#changedept").clone(true);
	devClone.modal('show').on('hidden.bs.modal', function(event) {
		$(this).remove();
	});
	// 修改clone后的modal的id
	var tree = devClone.find("#changetree");
	// var treeid='clonechangetree';
	// treeid = new Date().getTime().toString();
	tree.attr('id', 'clonechangetree');
	var treeid = tree.attr('id');
	var ztreeConf = $.extend({}, {
		id : tree.attr('id'),
		url : "/personnel/getptree.do",
		// 获取所有节点
		btns : [],
		setting : {
			data : {
				simpleData : {
					enable : true
				// 简单数据
				}
			},
			view : {
				selectedMulti : false
			}

		}
	});

	wade.libs.tree(ztreeConf);

	var condition = "[";
	$.each(sel_obj_arr, function(index, obj) {
		var personId = obj.personId;
		// var personName = obj.personName;
		// alert(personCode);
		if (index == 0)

			condition += personId;
		else
			condition += ',' + personId

	});
	condition += "]";
	devClone.find(".btn-primary").unbind("click").click(
			function() {
				if (!wade.tree.selectNode(treeid)) {

					wade.libs.alert("请选择部门！", 0, devClone.find('div:first'));
				} else {
					var deptid = wade.tree.selectNode(treeid).id
					// alert(deptid);
					$.ajax({
						url : "/personnel/changedept.do",
						data : "condition=" + condition + "&deptid=" + deptid,
						// 默认获取div下第一个from数据
						success : function(data) {
							result = JSON.parse(data);
							if (result.success) {
								wade.libs.alert('修改成功');
								$(".paginate_button.active").click(); // 点击分页获取最新数据
								devClone.modal('hide');
							} else {
								wade.libs.alert(result.msg, 0, devClone
										.find('div:first'));
							}
						}
					});
				}

			});

	// alert(cartypeid);

	// console.log(condition);

};
// 批量开卡
function batchcard(sel_tr, all_tr, sel_obj_arr, sel_obj) {
	if (sel_obj_arr.length == 0) {
		wade.libs.alert("请选择需要移至的员工!", 2);
		return;
	}
	var devClone = $("#batchcard").clone(true);

	devClone.modal({
		backdrop : 'static',
		keyboard : false
	}).on('hidden.bs.modal', function(event) {
		$(this).remove();
	});
	var tablehtml = '';
	// var cardNumberArray=devClone.find("input[name='cardNumberArray']");
	$.each(sel_obj_arr,function(index, value) {
				tablehtml += '<tr ><td>'+ value.personCode+ '</td><td>'+ value.personName+ '</td><td>'+ value.deptName+ '</td><td>'+ value.phone+ '</td><td><input type="text" name="cardNumberArray" ><label style="display: none;color:red;">卡号为数字</label><input type="hidden" name ="personIdArray" value="'+ value.personId + '"></td></tr>'
					});
	
	devClone.find('#tr1').after(tablehtml);
	/*
	 * 回车
	 */
	var el = devClone.find('input[name="cardNumberArray"]');
	el.keydown(function() {
		if (event.keyCode == 13) {// 输入回车键
       $(this).parent().parent().next().children('td').eq('4').find('input[name="cardNumberArray"]').focus();
		
		}
	});

	devClone.find('#add').click(function() {
		var cardId=devClone.find('input[name="cardNumberArray"]');
		var flag=true;
		$.each(cardId,function(index,item){
			var  value=$(this).val();
			if(!RegExpObj.isNumber(value)){
				//alert();
				$(this).parent().children('label').show();
				$(this).attr('style','border:1px solid;border-color:red');
				flag=false;
			}
		});
		if(!flag){
			return ;
		}
	/*	if(!RegExpObj.isNumber(cardId)){
			//devClone.find('div[name="sss"]').show();
			devClone.find('td label').show();
			devClone.find('input[name="cardNumberArray"]').attr('style','border:1px solid;border-color:red');
			return ;
		}*/
		
		
		
		var jsoninfo = devClone.find("form:first").serializeObject();
		var formdata = JSON.stringify(jsoninfo);
		$.ajax({
			url : "/personnel/batchcard.do",
			data : {
				formdata : formdata
			},
			// 默认获取div下第一个from数据
			success : function(data) {
				result = JSON.parse(data);
				if (result.success) {
					devClone.modal("hide");
					wade.libs.alert('批量开卡成功');

				} else {
					devClone.modal("hide");
					wade.libs.alert('批量开卡失败', 0);
				}
			}
		});

	});

	
	devClone.find('input[name="cardNumberArray"]').focus(function(){
		$(this).parent().children('label').hide();
	});
	
	
};



// 身份管理
function identitymanagement(sel_tr, all_tr, sel_obj_arr, sel_obj) {

	var devClone = $("#bimcateModal").clone(true);
	devClone.modal({
		backdrop : 'static',keyboard : false})
		.on('hidden.bs.modal', function(event) {$(this).remove();});
	//显示身份信息
	$.ajax({
		url : "/personnel/bimcate.do",
		async : false,
		success : function(data) {
			result = JSON.parse(data);
			//console.log(result);
			//var table='';
			if (result.length!=0) {
				$.each(result, function(index, value) {
					var table='<tr ><td>'+(index+1)+'</td><td><input type="text" name="cateName"></td><td><input  type="text" name="note"></td><td style="text-align:center;"><a class="update">修改</a><a class="delete" style="margin-left: 10px;">删除</a></td><td  style="display:none;" name="bimCateId">'+value.bimCateId+'</td></tr>';
					devClone.find('tr:last').after(table);
					var a=devClone.find('tr:last');
					//console.log(a.children('td').eq(0).html());
					//alert(value.cateName);
					a.find('input[name="cateName"]').val(value.cateName);
					a.find('input[name="note"]').val(value.note);
					//a.find('input[name="bimCateId"]').html(value.bimCateId);
				});
				
			} 
			
		}
	});
	

	//点击tr
	devClone.find("tr").bind('click',clickTr);
	
   function clickTr(){
	   devClone.find("tr").removeAttr('class');
		$(this).attr('class', 'active');
   }
	
	
	
	
	
	
	/*
	 * 点击修改操作
	 */
	devClone.find('.update').bind('click',updateCate);
	
	function updateCate(){
		//alert(0);
		var bimCateId=$(this).parent().next().html();
		var noteelem=$(this).parent().prev();
		var note=noteelem.find('input[name="note"]').val();
		var cateName=noteelem.prev().find('input[name="cateName"]').val();
		$.ajax({
			url : "/personnel/updatecate.do",
			async : false,
			data : {
				bimCateId : bimCateId,
				cateName:cateName,
				note:note
			},
			success : function(data) {
				result = JSON.parse(data);
				//console.log(result);
				if(result.success){
					wade.libs.alert(result.msg,3,devClone);
				}else{
					wade.libs.alert(result.msg, 0,devClone);
					//$(this).parent().prev().prev().find('input[name="cateName"]').css('border-color','red')
				}
			}
		});
		
	}
	
	
	
	/*
	 * 点击添加
	 */
	devClone.find('#add').click(function(){
		
		$.ajax({
			url : "/personnel/addcate.do",
			async : false,
			success : function(data) {
				result = JSON.parse(data);
				console.log(result);
				if(result.success){
					var index=devClone.find('tr').length;
					var lasttr=devClone.find("tr:last");
					var table='<tr ><td>'+index+'</td><td><input type="text" name="cateName"></td><td><input  type="text"  name="note"></td><td style="text-align:center;"><a class="update">修改</a><a class="delete" style="margin-left: 10px;">删除</a></td><td  style="display:none;" name="bimCateId">'+result.object.bimCateId+'</td></tr>';
					lasttr.after(table);
					var addlasttr=devClone.find("tr:last");
					addlasttr.find('input[name="cateName"]').val(result.object.cateName);
					addlasttr.find('input[name="note"]').val(result.object.note);
					devClone.find("tr").bind('click',clickTr);
					devClone.find('.update').bind('click',updateCate);
					devClone.find('.delete').bind('click',deleteCate);
				}else{
					alert(result.msg);
				}
			}
		});	
	});
	
	
	/*
	 * 点击删除
	 */
	
	devClone.find('.delete').bind('click',deleteCate);
	
	function deleteCate(){
		var bimCateId=$(this).parent().next().html();
		//alert(bimCateId);
		wade.libs.dialog('确定删除身份类型？',function(){
			
			
			$.ajax({
				url : "/personnel/delcate.do",
				async : false,
				data : {
					bimCateId : bimCateId
					
				},
				success : function(data) {
					result = JSON.parse(data);
					//console.log(result);
					if(result.success){
						wade.libs.alert(result.msg,3,devClone);
						//console.log(result);
						//if (result.object.length!=0) {
							devClone.find('tr:gt(0)').remove();
							$.each(result.object, function(index, value) {
								var table='<tr ><td>'+(index+1)+'</td><td><input type="text" name="cateName"></td><td><input  type="text" name="note"></td><td style="text-align:center;"><a class="update">修改</a><a class="delete" style="margin-left: 10px;">删除</a></td><td  style="display:none;" name="bimCateId">'+value.bimCateId+'</td></tr>';
								devClone.find('tr:last').after(table);
								var a=devClone.find('tr:last');
								a.find('input[name="cateName"]').val(value.cateName);
								a.find('input[name="note"]').val(value.note);
							});
							
						//}
						devClone.find("tr").bind('click',clickTr);
						devClone.find('.update').bind('click',updateCate);
						devClone.find('.delete').bind('click',deleteCate);
						$("#personnelexample").DataTable().draw(false);
						
					}else{
						wade.libs.alert(result.msg, 0,devClone);
						//$(this).parent().prev().prev().find('input[name="cateName"]').css('border-color','red')
					}
				}
			});
			
		});
		
		
		
	}	
};


// 导入
function importInfo() {
	// alert(0);
	var devClone = $("#uploadModal1").clone(true);
	// var devClone2 = $("#progressModal").clone(true);
	devClone.modal({
		backdrop : 'static',
		keyboard : false
	}).on('hidden.bs.modal', function(event) {
		$(this).remove();
	});

	devClone.find('input[name="lx"]').change(function() {
		var type = devClone.find('input:radio[name="lx"]:checked').val();

		if (type == 0) {// 部门
			devClone.find('#mb').text('下载部门模板');
		} else {// 员工
			devClone.find('#mb').text('下载人员模板');
		}
	});
	// 点击下载模板类型
	devClone.find('#mb').click(function() {
		var type = devClone.find('input:radio[name="lx"]:checked').val();
		if (type == 0) {// 部门

			window.open("/personnel/DeptDemodownload.do");
		} else {// 员工

			window.open("/personnel/PersonDemodownload.do");
		}
	});

	devClone.find('#sc').click(function() {
		// alert(0);
		if (devClone.find("#file").val() == "") {
			alert("请选择文件！");
			return;
		}

		var type = devClone.find('input:radio[name="lx"]:checked').val();
		var formData = new FormData(devClone.find('#form1')[0]);
		formData.append("type", type);
		$.ajax({
			type : 'post',
			url : '/personnel/upload.do',
			data : formData,
			mimeType : "multipart/form-data",
			contentType : false,
			cache : false,
			processData : false,
			success : function(data) {
				var result = JSON.parse(data);
				// alert(result.success);
				if (result.success) {
					alert("恭喜！上传成功！");
					devClone.modal('hide');
					// devClone2.modal('hide');
				} else {
					// devClone2.modal('hide');
					alert(result.msg);
				}

			},
			fail : function(jqXHR, textStatus, errorThrown) {

			}

		});

	});

};
// 导出
function exportInfo() {
	var devClone = $("#downloadModal").clone(true);

	devClone.modal({
		backdrop : 'static',
		keyboard : false
	}).on('hidden.bs.modal', function(event) {
		$(this).remove();
	});

	devClone.find('#submit').click(function() {
		var type = devClone.find('input:radio[name="LX"]:checked').val();
		if (type == 0) {// 部门
			window.open("/personnel/Deptdownload.do");
		} else {
			// 员工
			window.open("/personnel/Persondownload.do");
		}

	});
};



config1.id = "personnelexample"; // table id
//config1.addDiv = "personModal"; // 新增弹出的层 ID
config1.editDiv = "personModal"; // 编辑 弹出层 ID
config1.getUrl = "/personnel/getpersonnelInfo.do"; // ajax获取数据请求地址
config1.saveUrl = "/personnel/savepersonnelInfo.do"; // 保存请求地址
config1.delUrl = "/personnel/delpersonnelInfo.do";// 删除信息
// 需要绑定数据的列
config1.columns = new Array("personId", "bimDeptId", "deptName", "address",
		"mail", "jobDate", "resignationDate", "birthday", "national", "ethnic",
		"englishName", "note","educational","custom3","photo1","fingerprint1Num","fingerprint2Num","personPhotoId","fingerprint1","fingerprint2","personCode", "personName", "sex", "certId",
		"certtype", "pinyin", "phone");
// 需要隐藏的列
config1.defs = new Array("personId", "bimDeptId", "deptName", "address",
		"mail", "jobDate", "resignationDate", "birthday", "national", "ethnic",
		"englishName", "note","educational","custom3","photo1","fingerprint1Num","fingerprint2Num","personPhotoId","fingerprint1","fingerprint2");
wade.libs.datatable(config1);


