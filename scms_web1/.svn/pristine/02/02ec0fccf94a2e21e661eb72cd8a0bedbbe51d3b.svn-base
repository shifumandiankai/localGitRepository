var wade = wade||{};
wade.role = wade.apply||{};
var config1 = new Object();

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
    function registerRole(event){
       var username = devClone.find("input[name=userName]").val();
       if (!verifyUsername(username)) { //验证用户名
           event.stopImmediatePropagation(); //阻止提交数据
           return false;
       }
    	
    }

}

var config = $.extend({},{
	btns: new Array(
				{'id':"30003",'name':'申请用车',"fun":appley},
				{'id':"30004",'name':'取消申请',"fun":cancel_appley}),
	search:new Array({'type':'search','placeholder':'单号/车牌','url':null,'index':0}), //查询条件
	id:"example1",	//dataTable ID
	addDiv:"", //新增弹出的DIV ID
	editDiv:"",	//编辑弹出的DIV ID
	getUrl:"/apply/getApplyInfo.do",	//获取数据请求路径
	delUrl:"",	//删除数据请求路径
	saveUrl:"",	//保存数据请求路径
	columns: new Array(	//绑定数据的列
			"orderNum","personName","deptName","carNumber","useCarTime","useCarTimeLen","applayTime","statusDes","note","personId","personCode","deptId","deptCode","status","carId"),
	defs:new Array("personId","personCode","deptId","deptCode","carId","status"),
	add_shown:shown, //弹出新增或编辑对话框后执行的方法,
});	
wade.libs.datatable(config);	//初始化datatable


/**
 * 配置权限信息
 * @param sel_tr
 * @returns
 */
function appley(sel_tr,all_sel_tr){
	//TODO 申请派车
}

function cancel_appley(sel_tr,all_sel_tr){
	//TODO 取消派车
}

