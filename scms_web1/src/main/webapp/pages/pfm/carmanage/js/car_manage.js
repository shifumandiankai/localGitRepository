/** *************页面tree控件初始化和函数定义相关***************** */
$(".sidebar-menu a").each(function(index,value){if($(this).attr("href")=="/pages/pfm/carmanage/car_manage.jsp"){return false;}if($(this).attr("href")!="#"){window.location.href=$(this).attr("href"); return false;}});
var ztreeConf = $.extend({},
{
    id: "tree",
    url: "/pfm/cartype/getcttree.do",
    // 获取所有节点
    btns: ["add", "delete", "edit","cancel"],
    adddiv: "addcarType",
    addurl: "/pfm/cartype/add.do",
    add_shown: add_shownTree,
    deleteurl: "/pfm/cartype/delete.do",
    delnode: delnode1,
    oncancel: cancel,
    expand:true,
    afterdelete:afterdel,
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
        callback: {
            onClick: nodeClick,
            beforeClick:beforeClick
        }
    }

});

wade.libs.tree(ztreeConf);
function afterdel(){
	 $("[name=searchValue0_search_hidden]").val("").trigger('keyup.DT');
}
function delnode1(node) {
    if (node.type == "cartype") {
        return true
    } else {
        wade.libs.alert("当前车场无法删除！",2);
        return false;
    }

}
// 点击节点是触发事件,触发表格
function nodeClick(event, treeId, treeNode) {
    $("[name=searchValue1_search_hidden]").val(treeNode.id).trigger('keyup.DT');
    $("[name=searchValue0_search_hidden]").val(treeNode.id).trigger('keyup.DT');
    $("#example2_info").hide();
	$("#example2_length").hide();
	$("#example2_paginate").hide();

}
function beforeClick(treeId, treeNode, clickFlag){
	if(treeNode.hasPermission==1){
		alert("您没有权限，请联系管理员");
		return false;
		
	}
}
// 点击cancel回调函数
function cancel() {
	$("#example2_info").show();
	$("#example2_length").show();
	$("#example2_paginate").show();
    $("[name=searchValue1_search_hidden]").val("").trigger('keyup.DT');
    $("[name=searchValue0_search_hidden]").val("").trigger('keyup.DT');
}
// 绑定验证，模态框初始化 0 新增 1 修改
function add_shownTree(devClone, treeid, flag) {

    var selnode = wade.tree.selectNode(treeid);
    if (selnode == undefined) {
        devClone.modal('hide');
      //  wade.libs.dialog("请选择需要操作的项");
        wade.libs.alert("请选择需要操作的项",2);
        return false;

    }
    //初始化车场权限select2
	 $.ajax({	//车场权限
	        url: "/subparks/getselectdata",
	        dataType: 'json',
	        data:{ppark:selnode.custom1},
	        quietMillis: 250,
	        success: function (result) {
	        		devClone.find('*[name=custom3]').select2({
	        		dropdownParent:devClone,
	        		multiple:true,
	        		data:result,
	        		 placeholder: '请选择小车场，可多选',
	        		 language: 'zh-CN' 
	        	});
	        	if(flag ==1){
	        		if(selnode){
	        		
	        			var s = selnode.custom3.split(',');
					wade.libs.datatable_fill_form_data(		
					"custom3",s,devClone);
	        		}
	        	}
	        }
	    }); 
	///////////////////

    devClone.find("#custom1").on('change',
    function() {
        var lotid = devClone.find("#custom1").val();
        $.ajax({
            url: "/pfm/cartype/getcartypeby1lot.do",
            type: "get",
            async: false,
            data: "lotid=" + lotid,
            success: function(data) {
                var result = JSON.parse(data);
                var opts ="";
                $.each(result,
                function(index, cartype) {
                    opts += "<option value='" + cartype.carTypeId + "'>" + cartype.typeName + "</option>"

                });
                devClone.find("#inId").empty();
                devClone.find("#inId").append(opts);
            },
            error: function() {
                alert("error！请重试！")
            }
        });

    });

    /** *************车场信息初始化******** */
    $.ajax({
        url: "/pfm/cartype/getlots.do",
        type: "get",
        async:false,
        success: function(data) {
            var result = JSON.parse(data);
            var opts = "";
            $.each(result,
            function(index, parklot) {
                opts += "<option value='" + parklot.pfmParkingLotId + "'>" + parklot.parkingLotName + "</option>"
            });

            devClone.find("#custom1").append(opts);
            devClone.find("#custom1").trigger("change");
        },
        error: function() {
            alert("error");
        }
    });
    /** ************end************** */
    if (flag == 0) {
        var node = wade.tree.selectNode(treeid);
        devClone.find("[name=custom1]").val(node.custom1).attr("disabled",'disabled'); 
        devClone.find('[name=custom1]').trigger('change');
        devClone.find("[name=custom1]").after("<input name='custom1' type='hidden'/>");
        devClone.find(":hidden[name=custom1]").val(node.custom1);
        devClone.find('[name=inId]').val(node.id).attr("disabled",'disabled');
        devClone.find("[name=inId]").after("<input name='inId' type='hidden'/>");
        devClone.find(":hidden[name=inId]").val(node.id);
   
    }

    if (flag == 1) {

        var node = wade.tree.selectNode(treeid);
        devClone.find('[name=custom1]').val(node.custom1).attr('disabled', 'disabled');
        devClone.find("[name=typeName]").val(node.name);
        devClone.find("[name=note]").val(node.note);
        devClone.find("[name=carFull]").val(node.carFull);
        devClone.find("[name=maxstop]").val(node.maxstop);
        devClone.find("[name=status]").val(node.status);
        devClone.find("[name=custom2]").val(node.custom2);
       // devClone.find("[name=status]").attr('disabled', 'disabled');
        devClone.find('[name=custom1]').trigger('change');
        if (node.inId == null) 
        	devClone.find('[name=inId]').val('-1').attr('disabled', 'disabled');
        else
        	devClone.find('[name=inId]').val(node.inId).attr('disabled', 'disabled');

    }

    /** **********车辆类型验证模块Start********** */
    devClone.find('#add').on("click", verify);
    function verify(event) {
        var name = devClone.find("input[name='typeName']").val();
        if (!verifyTypename(name)) {

            event.stopImmediatePropagation(); // 
            return false;
        }
        var note = devClone.find("[name='note']").val();
        if (!verifyNote(note)) {

            event.stopImmediatePropagation(); // 
            return false;
        }

    }
    devClone.find('input[name=typeName]').on('change keyup',
    function() {
        devClone.find("#typeNameNotice").hide();
    });
    devClone.find('input[name=typeName]').on("blur",
    function() {
        var val = $(this).val();
        if(val!="")
        verifyTypename(val);

    });
    function verifyTypename(val) {

        if (val.trim()=="") {
            devClone.find('.point-out').hide();
            devClone.find("#typeNameNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + "名称不能为空" + '</span>').show();
            return false;
        }
        else if(val.indexOf(" ")!=-1){
        	  devClone.find('.point-out').hide();
              devClone.find("#typeNameNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + "不能含空格" + '</span>').show();
              return false;
        }
        else if(val.trim().length>20){
        	 devClone.find('.point-out').hide();
             devClone.find("#typeNameNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + "请不要超过20个字符" + '</span>').show();
             return false;
        }
        return true;
    }
    devClone.find('[name=note]').on('change keyup',
    	    function() {
    	        devClone.find("#noteNotice").hide();
    	    });
    	    devClone.find('[name=note]').on("blur",
    	    function() {
    	        var val = $(this).val();
    	        if(val!="")
    	        	verifyNote(val);

    	    });
    	    function verifyNote(val) {

    	        if (val.length>50) {
    	            devClone.find('.point-out').hide();
    	            devClone.find("#noteNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + "请不要超过50个字符" + '</span>').show();
    	            return false;
    	        }
    	        
    	        return true;
    	    }
    
    /** **********车辆类型验证模块END********** */

}

/** ****************************** */
/** *function 获取车位信息** */
function getCarSpace(parkinglotid, devClone) {
    // 可用车位
    $.ajax({
        url: '/pfm/carmanage/getparkspacelist.do',
        type: 'post',
        async: false,
        data: {
            "parkingLotId": parkinglotid
        },
        success: function(data) {

            var result = JSON.parse(data);
            if (result.success) {
                var htm = '<option value="-1">不绑定车位</option>';
                if (result.object.length != 0) {
                    var length = result.object.length;

                    for (var i = 0; i < length; i++) {
                        var obj = result.object[i];
                        htm += '<option value="' + obj.pfmParkSpace + '">' + obj.parkSpaceCode + '</option>';

                    }

                } else {
                    htm += "<optgroup label=\"无可用车位\"></optgroup>"
                }
                devClone.find('#carSpaceId').empty();
                devClone.find('#carSpaceId').append(htm);

            }

        }

    });

}

/** *************车辆信息增加修改模态框出现后执行********************** */
function add_shown(devClone, flag, sel_obj) {
	//devClone.hide();
    if (flag == 0) {
        if (wade.tree.selectNode(ztreeConf.id) == undefined) {
            devClone.modal("hide");
            wade.libs.alert("请先选择车辆类型！",2);
            return false;
        }
    }

    // iCheckRadio初始化
    $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
        checkboxClass: 'icheckbox_minimal-blue',
        radioClass: 'iradio_minimal-blue'
    });
    //换成my97控件
/*    devClone.find("#time").datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd',
        todayBtn: true,
        todayHighlight: true,
        language: 'zh-CN',
        startDate:'1000-01-01'

    }).datepicker('setDate',new Date());*/
    //my97控件需要id
    devClone.find("[name=enableTime]").attr("id",'begintime');
    devClone.find("[name=disableTime]").attr("id",'endtime');
    // 车辆类型获取初始化select2
    if (flag == 0) { // 新增时设置默认性别
        devClone.find("[name=sex][value=0]").iCheck('check');
        devClone.find("[name=status][value=0]").iCheck('check');
        // 车辆类型
        var node = wade.tree.selectNode(ztreeConf.id);
        if (node != undefined) {
            devClone.find('#cartypeselect').val(node.id);

        }
        devClone.find("#cartypeselecta").val(node.name);
        devClone.find('#menuBtn').hide();
        var a = new Date();
        devClone.find("[name=enableTime]").val(a.Format("yyyy-MM-dd"));
        a.setFullYear(new Date().getFullYear()+30);
        devClone.find("[name=disableTime]").val(a.Format("yyyy-MM-dd"));
        
    } else {
        // 修改可以选择车辆类型
    		devClone.find('#cartypeselect').val(sel_obj.carTypeId);
    		 var nowid = devClone.find("#cartypeselect").val();
    		 var treeObj = $.fn.zTree.getZTreeObj(ztreeConf.id);
    		 var nodes = treeObj.getNodesByParam("id", nowid, null);
       		devClone.find("#cartypeselecta").val(nodes[0].name);
        	 function onClick(e, treeId, treeNode) {
                 var zTree = $.fn.zTree.getZTreeObj(treeId),
                 nodes = zTree.getSelectedNodes(),
                 v = "";
                 nodes.sort(function compare(a, b) {
                     return a.id - b.id;
                 });
                 for (var i = 0,
                 l = nodes.length; i < l; i++) {
                     v += nodes[i].name + ",";
                 }
                 if (v.length > 0) v = v.substring(0, v.length - 1);
                devClone.find("#cartypeselecta").val(v);
                 devClone.find("[name='carTypeId']").val(treeNode.id);
             }

             function showMenu() {
                 var cityObj = devClone.find("#cartypeselecta");
                 var cityOffset = devClone.find("#cartypeselecta").offset();

                 $("#menuContent").css({
                     left: cityOffset.left,
                     top: cityOffset.top + cityObj.outerHeight()
                 }).slideDown("fast");

                 $("body").bind("mousedown", onBodyDown);
             }
             function hideMenu() {
                 $("#menuContent").fadeOut("fast");
                 $("body").unbind("mousedown", onBodyDown);
             }
             function onBodyDown(event) {
                 if (! (event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length > 0)) {
                     hideMenu();
                 }
             }
             devClone.find("#menuBtn").hide();
             devClone.find("#menuBtn").click(function() {
                 showMenu();
                 return false;
             })

             var ztreeConf1 = $.extend({},
             {
                 id: "cartypetree",
                 url: "/pfm/cartype/getcttree.do",
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
                     callback: {

                         onClick: onClick
                     }
                 }

             });

             wade.libs.tree(ztreeConf1);

        // 修改时从选中的对象中获取性别，状态信息
        if (sel_obj.sex == "男") {
            devClone.find('input[name="sex"][value="0"]').iCheck("check");
        } else {
            devClone.find('input[name="sex"][value="1"]').iCheck("check");
        }

        if (sel_obj.status == "正常") {
            devClone.find('input[name="status"][value="0"]').iCheck("check");
        } else if (sel_obj.status == "挂失") {
            devClone.find('input[name="status"][value="1"]').iCheck("check");
        } else if (sel_obj.status == "白名单") {
            devClone.find('input[name="status"][value="2"]').iCheck("check");

        } else {

            devClone.find('input[name="status"][value="3"]').iCheck("check");
        }

        // 当personid不为null时 ，一些数据无法修改，因为从bim——person表中获取的
        if (sel_obj.personId != null) {
            devClone.find('input[name=phone]').attr('disabled', 'disabled');
            devClone.find('input[name=addres]').attr('disabled', 'disabled');
            devClone.find('input[name=certId]').attr('disabled', 'disabled');
            devClone.find('input[name=sex]').attr('disabled', 'disabled');
            devClone.find('input[name=personName]').attr('disabled', 'disabled');

        }

    }
    // 获取停车场信息
    $.ajax({
        url: '/pfm/carmanage/getparklot.do',
        type: 'post',
        async: false,
        success: function(data) {
            var result = JSON.parse(data);
            if (result.success) {
                var htm = '';
                if (result.object.length != 0) {
                    var length = result.object.length;
                    for (var i = 0; i < length; i++) {
                        var obj = result.object[i];
                        htm += '<option value="' + obj.pfmParkingLotId + '">' + obj.parkingLotName + '</option>';

                    }

                } else {
                    htm += "<optgroup label=\"无车场数据\"></optgroup>"
                }
                devClone.find('#currentParkLot').empty();
                devClone.find('#currentParkLot').append(htm);
                var parkinglotid = devClone.find('#currentParkLot').val();

                if (parkinglotid != null) {

                    getCarSpace(parkinglotid, devClone);
                } else {
                    devClone.find('#carSpaceId').attr('disabled', 'disbaled');
                }

            }

        }

    });
    devClone.find("#currentParkLot").on('change',
    function(e) {
        var parkinglotid = $(this).val();
        getCarSpace(parkinglotid, devClone);

    });
    if (flag == 1) {
        devClone.find('#cartypeselect').val(sel_obj.carTypeId);
        devClone.find('#currentParkLot').val(sel_obj.pfmParkingLotId);
        if (sel_obj.pfmParkingLotId == null) { // 如果想要修改的车辆没有车位信息则车场车位都为null
            devClone.find('#carSpaceId').empty();
        } else { // 如果存在车位信息，则触发change并设置车位
            devClone.find('#currentParkLot').trigger("change");

            devClone.find('#carSpaceId').val(sel_obj.pfmParkSpace)
        }
    }

    // 验证
    devClone.find("#add").on('click', verifyFormat);

    function verifyFormat(event) {
        var name = devClone.find('input[name=personName]').val();
        if (!verifyPersonName(name)) {
            event.stopImmediatePropagation(); // 
            return false;
        }
        var carNumber = devClone.find('input[name=carNumber]').val();
        if (!verifyCarNumber(carNumber)) {
            event.stopImmediatePropagation(); // 
            return false;
        }
        var phone = devClone.find('input[name=phone]').val();
        if (phone != '' && !verifyPhone(phone)) {
            event.stopImmediatePropagation(); // 
            return false;
        }
        var t1 = devClone.find('input[name=enableTime]').val();
        if (!verifyEnableTime(t1)) {
            event.stopImmediatePropagation(); // 
            return false;
        }
        var t2 = devClone.find('input[name=disableTime]').val(); 
        if (!verifyDisableTime(t2)) {
            event.stopImmediatePropagation(); // 
            return false;
        }
        var cardId = devClone.find('input[name=cardId]').val(); 
        if (!verifycardId(cardId)) {
            event.stopImmediatePropagation(); // 
            return false;
        }
        var certId = devClone.find('input[name=certId]').val(); 
        if (!verifycertId(certId)) {
            event.stopImmediatePropagation(); // 
            return false;
        }
        var addres = devClone.find('input[name=addres]').val(); 
        if (!verifyaddres(addres)) {
            event.stopImmediatePropagation(); // 
            return false;
        }
        var note = devClone.find('input[name=note]').val(); 
        if (!verifynote(note)) {
            event.stopImmediatePropagation(); // 
            return false;
        }
      //  devClone.find("#add").attr("disabled","disabled");
    }
    /** **********************姓名验证*************** */
    devClone.find('input[name=personName]').on('change keyup',
    function() {
        devClone.find("#personNameNotice").hide();
    });
    devClone.find('input[name=personName]').on("blur",
    function() {
        var val = $(this).val();
        if(val!="")
        verifyPersonName(val);

    });
    // 验证姓名格式
    function verifyPersonName(data) {
        var result = checkName(data);
        if (result != 100) {
            devClone.find('.point-out').hide();
            devClone.find("#personNameNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + result + '</span>').show();
            return false
        }
        return true;

    }
    /** **********************END*************** */
    /** **********************车牌号码验证*************** */
    devClone.find('input[name=carNumber]').on('change keyup',
    function() {
        devClone.find("#carNumberNotice").hide();
    });
    devClone.find('input[name=carNumber]').on("blur",
    function() {
        var val = $(this).val();
        if(val!="")
        verifyCarNumber(val);

    });
    // 验证车牌格式
    function verifyCarNumber(data) {
        var result = checkCarNum(data);

        if (result == 100) {

            if (flag == 0) {
                sel_obj = {};
                sel_obj.carInfoId = null
            }
            $.ajax({
                type: 'post',
                url: '/pfm/carmanage/validatecarnum',
                data: "carNum=" + data + "&flag=" + flag + "&carinfoid=" + sel_obj.carInfoId,
                async: false,
                success: function(data) {

                    var result1 = JSON.parse(data);
                    if (result1.success) {

                        result = 100;
                    } else {

                        result = result1.msg;

                    }
                }

            });
        }
        if (result != 100) {
            devClone.find('.point-out').hide();
            devClone.find("#carNumberNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + result + '</span>').show();
            return false
        }
        return true;

    }
    /** **********************END*************** */
    /** **********************手机号*************** */
    devClone.find('input[name=phone]').on('change keyup',
    function() {
        devClone.find("#phoneNotice").hide();
    });
    devClone.find('input[name=phone]').on("blur",
    function() {
        var val = $(this).val();
        if (val != '') {
            verifyPhone(val);
        }
    });
    // 验证手机号格式
    function verifyPhone(data) {

        var result = checkPhone(data);

        if (result != 100) {
            devClone.find('.point-out').hide();
            devClone.find("#phoneNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + result + '</span>').show();
            return false
        }
        return true;

    }
    /** **********************END*************** */
    /** **********************启用时间*************** */
    devClone.find('input[name=enableTime]').on('change keyup',
    		function() {
    	devClone.find('#enableTimeNotice').hide();
    });
    devClone.find('input[name=enableTime]').on('blur',
    function() {
        var val = $(this).val();
        if(val!="")
          verifyEnableTime(val);
       
    });
    
  
    // 验证启用时间格式
    function verifyEnableTime(data) {
        var result = ""
       // var success = true;
      
        if (data == "") {
            result = "启用时间不能为空";
            success = false;
        } /*else {
                var val = devClone.find('input[name=disableTime]').val();
                if(val!=""){
                    var a = devClone.find('input[name=enableTime]').val();
                    var b = devClone.find('input[name=disableTime]').val();

                    var disableArr = b.split('-');
                    var disableTime = new Date(disableArr[0], disableArr[1] - 1, disableArr[2]);
                    var enableArr = a.split('-');
                    var enableTime = new Date(enableArr[0], enableArr[1] - 1, enableArr[2]);
                    if (disableTime.getTime() < enableTime.getTime()) {
                    	result = "启用时间必须小于停用时间";
                    	success = false;

                    } else {
                    	success = true;
                    }
                }
               
            
        }*/
       
        if (result) {
        	 devClone.find('.point-out').hide();
            devClone.find("#enableTimeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + result + '</span>').show();
            return false
          }
        
        return true;
    }

    /** **********************END*************** */
    /** **********************停用时间*************** */

    devClone.find('input[name=disableTime]').on('change keyup',
    function() {
      
        devClone.find('#disableTimeNotice').hide();
  

    });
    devClone.find('input[name=disableTime]').on('blur',
    	    function() {
    	        var val = $(this).val();
    	        if(val!="")
    	        verifyDisableTime(val);
    	    });
    
    // 验证停用时间格式
    function verifyDisableTime(data) {
        var result = ""
      //  var success = true;
        if (data == "") {
            result = "停用时间不能为空";
           // success = false;
        }/* else {

            var a = devClone.find('input[name=enableTime]').val();
            var b = devClone.find('input[name=disableTime]').val();

            var disableArr = b.split('-');
            var disableTime = new Date(disableArr[0], disableArr[1] - 1, disableArr[2]);
            var enableArr = a.split('-');
            var enableTime = new Date(enableArr[0], enableArr[1] - 1, enableArr[2]);
            if (disableTime.getTime() < enableTime.getTime()) {
            	result = "停用时间必须大于启用时间";
            	success = false;

            } else {
            	success = true;
            }

            
        }*/
       
        if (result) {
        	 devClone.find('.point-out').hide();
            devClone.find("#disableTimeNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + result + '</span>').show();
            return false;
        }
        return true;

    }

    /** **********************END*************** */
    /** **********************卡号验证*************** */
    devClone.find('input[name=cardId]').on('change keyup',
    function() {
        devClone.find("#cardIdNotice").hide();
    });
    devClone.find("[name=cardId]").blur(function() {
        var name = $(this).val();
        if(name!="")
        verifycardId(name);

    });
    function verifycardId(val) {
        var bret = true;
        var sret = "";
       if(val.indexOf(" ") != -1){
    	   sret="卡号请不要携带空格";
    	   bret=false;
       }else if(val.length>15){
    	   sret="卡号请不要超过15个字符";
		   bret=false;
       }
        if (!bret) {
            devClone.find('.point-out').hide();
            devClone.find("#cardIdNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + sret + '</span>').show();
        }
        return bret;
    }
    /** **********************卡号验证*************** */
    /** **********************证件号验证*************** */
    devClone.find('input[name=certId]').on('change keyup',
    function() {
        devClone.find("#certIdNotice").hide();
    });
    devClone.find("[name=certId]").blur(function() {
        var name = $(this).val();
        if(name!="")
        verifycertId(name);

    });
    function verifycertId(val) {
        var bret = true;
        var sret = "";
       if(val.indexOf(" ") != -1){
    	   sret="证件号请不要携带空格";
    	   bret=false;
       }else if(val.length>20){
    	   sret="证件号请不要超过20个字符";
		   bret=false;
       }
        if (!bret) {
            devClone.find('.point-out').hide();
            devClone.find("#certIdNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + sret + '</span>').show();
        }
        return bret;
    }
    /** **********************证件验证*************** */
    /** **********************备注验证*************** */
    devClone.find('input[name=note]').on('change keyup',
    function() {
        devClone.find("#noteNotice").hide();
    });
    devClone.find("[name=note]").blur(function() {
        var name = $(this).val();
        if(name!="")
        verifynote(name);

    });
    function verifynote(val) {
        var bret = true;
        var sret = "";
        if(val.length>20){
    	   sret="备注请不要超过20个字符";
		   bret=false;
       }
        if (!bret) {
            devClone.find('.point-out').hide();
            devClone.find("#noteNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + sret + '</span>').show();
        }
        return bret;
    }
    /** **********************备注验证*************** */
    /** **********************地址验证*************** */
    devClone.find('input[name=addres]').on('change keyup',
    function() {
        devClone.find("#addresNotice").hide();
    });
    devClone.find("[name=addres]").blur(function() {
        var name = $(this).val();
        if(name!="")
        verifyaddres(name);

    });
    function verifyaddres(val) {
        var bret = true;
        var sret = "";
        if(val.length>20){
    	   sret="地址请不要超过20个字符";
		   bret=false;
       }
        if (!bret) {
            devClone.find('.point-out').hide();
            devClone.find("#addresNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + sret + '</span>').show();
        }
        return bret;
    }
    /** **********************地址验证*************** */
    //添加附属车牌 开始
    devClone.find("#addFuShuBtn").click(function(event){
    	var devClone2 = $("#addFuShuModal").clone(true);
		devClone2.on('shown.bs.modal',function(){
		})
		devClone2.modal({backdrop: 'static', keyboard: false}).on('hidden.bs.modal',function(event){
			$(this).remove();
		});
		devClone2.find(".btn-primary").click(function() { // 保存按钮添加事件
			var carNumber=devClone2.find("#fuShuCarNum").val();
			if(validateIfHasCarNum(carNumber)){
				alert("请勿重复添加相同车牌");
				return;
			}
			if(checkCarNum(carNumber)==100){
				carNumArr.push(carNumber);
				devClone.find("#subsidiaryCarNum").append("<option value='"+carNumber+"'>"+carNumber+"</option>");
				var carNum="";
				$.each(carNumArr,function(index,value){
					if(index==0){
						carNum=carNum+value;
					}
					else{
						carNum=carNum+";"+value;
					}
				});
				devClone.find("[name='subsidiaryCarNum']").val(carNum);
				alert("添加成功")
			}
			else{
				alert("请检查车牌格式,例:京A12345")
			}
		});
    });
    //删除
    devClone.find("#delFuShuBtn").click(function(event){
    	if(devClone.find("#subsidiaryCarNum :selected").length==0){
    		alert("请选择要删除的车牌");
    		return;
    	}
    	
    	var car = "";
    	$.each(devClone.find("#subsidiaryCarNum :selected"),function(index,value){
    		
    		car+=value.value;
    		
    	});
    	var res = confirm("您确定要删除车牌号："+car+" 吗？");
    	if(res==true){
    		
    	}
    	else{
    		return;
    	}
    	$.each(devClone.find("#subsidiaryCarNum :selected"),function(index,value){
    		
    		carNumArr.remove(value.value);
    		
    	});
    	devClone.find("#subsidiaryCarNum :selected").remove();
    	
    	var carNum="";
		$.each(carNumArr,function(index,value){
			if(index==0){
				carNum=carNum+value;
			}
			else{
				carNum=carNum+";"+value;
			}
		});
		devClone.find("[name='subsidiaryCarNum']").val(carNum);
    	
    });
    var carNumArr = new Array();
    //验证是否已经存在车牌true存在false不存在
    function validateIfHasCarNum(carNum){
    	var result=false;
    	$.each(carNumArr,function(index,value){
    		if(value==carNum){
    			result=true;
    			return false;
    		}
    	});
    	return result;
    }
    //修改操作时请求
    if(flag==1){
    	
    	 $.ajax({
             type: 'get',
             url: '/pfm/carmanage/getrelatedcarno',
             data: "carNum="+sel_obj.carInfoId,
             success: function(data) {
            	 var result= JSON.parse(data);
            	 carNumArr=[];
            	 $.each(result,function(index,value){
            		 carNumArr.push(value.carNumber);
            	 });
            	 var carNum="";
            	 $.each(carNumArr,function(index,value){
            		 
 					if(index==0){
 						carNum=carNum+value;
 					}
 					else{
 						carNum=carNum+";"+value;
 					}
 					
 					devClone.find("#subsidiaryCarNum").append("<option value='"+value+"'>"+value+"</option>");
 					
 				});
            	
 				devClone.find("[name=subsidiaryCarNum]").val(carNum);
            
             }

         });
    }
    devClone.show();
}
//////新增 修改车辆信息
/** ********导入车辆信息************** */

function import_() {

    var devClone = $("#uploadModal").clone(true);
    var devClone2 = $("#progressModal").clone(true);
    devClone.modal({backdrop: 'static', keyboard: false}).on('hidden.bs.modal',
    function(event) {
        $(this).remove();
    });
    if (! (window.File || window.FileReader || window.FileList || window.Blob)) {
        alert('请更换google或firefox');
    }
    devClone.find('form').submit(function(event) {
        event.preventDefault();

        var form = $(this);

        var formData = new FormData(this);
        
        $.ajax({
            type: form.attr('method'),
            url: form.attr('action'),
            data: formData,
            contentType: false,
            cache: false,
            processData: false,
            success:function(data) {
            var result = JSON.parse(data);
            if (result.success) {
                alert("恭喜！上传成功！");
                devClone.modal('hide');
                devClone2.modal('hide');
            } else {
            	 devClone2.modal('hide');
                alert(result.msg);
            }

            // 成功提交
        }

    });
    });
    devClone.find(".btn-primary").unbind("click").click(function() {
    	if(devClone.find("#file").val()==""){
    		alert("请选择文件！");
    		return;
    	}

    	devClone.find('form').submit();
    	devClone2.on('hide.bs.modal',
                function(event) {
                    $(this).remove();
                });
    	devClone2.modal({backdrop: 'static', keyboard: false});
    	devClone2.find('#progress').shCircleLoader({
    		keyframes:
    			"0%   {background:red}\
    			40%  {background:transparent}\
    			60%  {background:transparent}\
    			100% {background:red}"
    	});

    });

}
/** ********************** */

/** ********导出车辆信息************** */
function export_() {
	var cartypeid;
	if(wade.tree.selectNode("tree")==undefined){
		cartypeid="";
	}
	else{
		cartypeid=wade.tree.selectNode("tree").id;
	}
	var order = $("#"+config.id).DataTable().order()[0][1];
	var orderby = 'a.car_info_id';
	switch($("#"+config.id).DataTable().order()[0][0]){
	case 1:orderby = 'person_name';break;
	case 2:orderby = 'car_number';break;;
	case 5:orderby='phone';break;
	case 6:orderby='type_name';break;
	case 7:orderby='rule_name';break;
	case 9:orderby='status';break;
	case 10:orderby='note';break;
	}
	var searchval = $('[name=searchValue0_search]').val();
	window.open("/pfm/carmanage/download.do?cartypeid="+cartypeid+"&orderby="+orderby+" "+order+"&searchval="+searchval);
}

/** ********************** */
/** *********修改车辆类型************* */
function changetype_(sel_tr, all_tr, sel_obj_arr) {
    if (sel_obj_arr.length == 0) {

        wade.libs.alert("请选择需要调换类型的车辆!",2);
        return;
    }

    var devClone = $("#changetype").clone(true);
    var tree = devClone.find("#treec");
    treeid = new Date().getTime().toString();
    tree.attr("id", treeid);

    devClone.modal('show').on('hidden.bs.modal',
    function(event) {
        $(this).remove();
    });
    var ztreeConf = $.extend({},
    {
        id: tree.attr('id'),
        url: "/pfm/cartype/getcttree.do",
        // 获取所有节点
        btns: [],
        expand:true,
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
            callback: {
                beforeClick:beforeClick
            }
        }
    });

    wade.libs.tree(ztreeConf);

    devClone.find(".btn-primary").unbind("click").click(function() {

        if (!wade.tree.selectNode(treeid)) {

            wade.libs.alert("请选择车辆类型！", 0, devClone.find('div:first'));
        } else {
            var condition = "[";
            $.each(sel_obj_arr,
            function(index, obj) {
                var cartyperelid = obj.carTypeRelId;
                if (index == 0)

                condition += cartyperelid;
                else condition += ',' + cartyperelid

            });
            condition += "]";
            // 提交表单
            var cartypeid = wade.tree.selectNode(treeid).id;

            $.ajax({
                url: "/pfm/carmanage/changecartype.do",
                data: "condition=" + condition + "&cartypeid=" + cartypeid,
                // 默认获取div下第一个from数据
                success: function(data) {
                    result = JSON.parse(data);
                    if (result.success) {
                        wade.libs.alert('修改成功');
                        $(".paginate_button.active").click(); // 点击分页获取最新数据
                        devClone.modal('hide');
                    } else {
                        wade.libs.alert(result.msg, 0, devClone.find('div:first'));
                        $(".paginate_button.active").click(); // 点击分页获取最新数据
                    }
                }
            });

        }

    });

}
/** ********************** */

/** *********车辆信心表格参数对象************ */
var config = $.extend({},
{
    btns: new Array({
        'id': '20001',
        'name': 'add',
        'fun': null
    },
    {
        'id': '20002',
        'name': 'edit',
        "fun": null
    },
    // 删除
    {
        'id': "20003",
        'name': 'del',
        "fun": null
    },
    {
        'id': "20004",
        'name': '调换车辆类型',
        "fun": changetype_
    },
    // 修改
    {
        'id': '20005',
        'name': '导入',
        'fun': import_
    },
    // 导入
    {
        'id': '20006',
        'name': '导出',
        'fun': export_
    }),
    search: new Array({
        'type': 'search',
        'placeholder': '车牌/手机号/姓名',
        'url': null,
        'index': 0
    },{'type': 'search_hidden',
        'placeholder': '',
        'url': null,
        'index': 1}),
    // 查询条件
    id: "example1",
    // table id
    addDiv: "addModal",
    // 新增弹出的层 ID
    editDiv: "addModal",
    // 编辑 弹出层 ID
    getUrl: "/pfm/carmanage/getcarmanageinfo.do",
    // ajax获取数据请求地址
    delUrl: "/pfm/carmanage/delete.do",
    // ajax删除角色请求地址
    saveUrl: "/pfm/carmanage/saveCarInfo.do",
    // 保存请求地址
    columns: new Array("carInfoId", "personName", "carNumber", "cardId", "sex", "phone", "typeName","ruleName", "parkSpaceCode", "status", "note", "userName", "carTypeRelId", "parkSpaceCarRelId", "pfmParkingLotId", "pfmParkSpace", "carTypeId", "enableTime", "disableTime", "personId", "addres", "certId"),
    add_shown: add_shown,
    // 弹出新增或编辑对话框后执行的方法,
    defs: new Array("carTypeRelId", "parkSpaceCarRelId", "carInfoId","cardId", "pfmParkingLotId", "sex", "pfmParkSpace", "carTypeId","parkSpaceCode", "enableTime", "disableTime", "personId", "addres", "certId","userName"),

});
wade.libs.datatable(config); // 初始化datatable
$("#" + config.id).css("width","100%");//使用tab页，表格宽度被固定，此处设为100%，自动拉伸
/** ***********规则配置******************************************************************************************************************** */

var config2 = $.extend({},
{
    btns: new Array({
        'id': '20001',
        'name': 'add',
        'fun': null
    },
    {
        'id': "20002",
        'name': 'edit',
        "fun": null
    },
    {
        'id': "20003",
        'name': 'del',
        "fun": null
    }),
    search: new Array({'type': 'search_hidden',
        'placeholder': '',
        'url': null,
        'index': 0}),
    // 查询条件
    id: "example2",
    // table id
    addDiv: "addchargeruleModal",
    // 新增弹出的层 ID
    editDiv: "addchargeruleModal",
    // 编辑 弹出层 ID
    getUrl: "/pfm/carmanage/getchargerule.do",
    // ajax获取数据请求地址
    delUrl: "/pfm/carmanage/deletecr.do",
    // ajax删除角色请求地址
    saveUrl: "",
    // 保存请求地址
    columns: new Array("ruleName","tempMatch","typeName","ruleType","updateTime","userName","ruleId","ruleTypeIndex","jianmianId","yichangId"),
    add_shown: ruleadd_shown,
    // 弹出新增或编辑对话框后执行的方法,
    defs: new Array("ruleId","ruleTypeIndex","jianmianId","yichangId"),

});
wade.libs.datatable(config2); // 初始化datatable

$(".nav-tabs-custom .nav-tabs li:first").addClass("active");
$(".nav-tabs-custom .tab-content .tab-pane:first").addClass("active");
// ///////////////////////限制金额输入
$(document).on('keyup','.money,.topmoney',function () {
    var reg = $(this).val().match(/\d+\.?\d{0,2}/);
    var txt = '';
    if (reg != null) {
        txt = reg[0];
    }
    
    $(this).val(txt);
}).on('change','.money,.topmoney',function () {
    $(this).keypress();
    var v = $(this).val();
       if (/\.$/.test(v))
    {
        $(this).val(v.substr(0, v.length - 1));
    }
});
// ///////////////////////限制金额输入
var chargeruleHtml = '<div>\
	<table id="chargerule" style="width:100% ;table-layout:fixed">\
<tr height="35">\
	<td style="width:20%"><label>临时车是否匹配：</label></td>\
	<td style="width:30%"><select name="tempMatch">\
			<option value="0">是</option>\
			<option value="1">否</option>\
	</select></td>\
	<td style="width:20%"><label>车辆类型：</label></td>\
	<td style="width:30%"><input  type="hidden" name="carType" value="-1"/><input id="typesel" style="cursor:not-allowed" type="text" value="不选择车辆类型" readonly value="" />\
	</div></td>\
</tr>\
<tr height="35">\
	<td><label>规则名称：</label></td>\
	<td><div style="position:relative"><div id="ruleNameNotice" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div>\
	<input  type="text" name="ruleName" /></div></td>\
	<td><label>收费类型：</label></td>\
	<td><select id="type" name="ruleType" >\
			<option value="0">分时收费</option>\
			<option value="1">分时段收费</option>\
			<option value="2">按单位时段收费</option>\
			<option value="3">按次收费</option>\
			<option value="4">按时收费</option>\
			<option value="5">包期收费</option>\
			<option value="6">不收费</option>\
			<option value="7">日夜分时按次收费</option>\
			<option value="8">派车</option>\
	</select></td>\
</tr>\
</table></div>\
	<div id="bottom">\
		</div>\
';
var chargeruleHtml0 = '	\
	<table style="width:100%;table-layout: fixed">\
	<tr height="35">\
<td style="width:20%"><label>免费停车时长：</label></td>\
<td style="width:30%"><div style="position:relative"><div id="freeTimeNotice" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div><input name="freeTime" type="text"></div></td>\
<td style="width:20%"><label>（分钟）</label></td>\
<td style="width:30%"></td>\
</tr>\
<tr height="35">\
<td colspan="2"><a id="timebtn" class="btn bg-blue btn-sm"><span>分时收费</span></a></td>\
<td></td>\
<td></td>\
</tr>\
<tr height="35">\
<td style="width:20%"><label>启用每天封顶金额：</label></td>\
	<td style="width:30%"><input id="useTop1" name="useTopMoney" type="checkbox" value="1"></td>\
	<td id="topmoneylabel" style="width:20%;display:none" >封顶金额：</td>\
	<td id="topmoney"style="width:30%;display:none"><div style="display:inline-block;position:relative;"><div id="topMoneyNotice" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div><input style="width:70%" type="text" name="topMoney"/><label>（元）</label></div></td>\
</tr>\
	<tr height="35">\
	<td style="width:20%"><label>启用24小时封顶金额：</label></td>\
	<td style="width:30%"><input id="useTop2" name="useTopMoney" type="checkbox" value="2"/></td>\
	<td></td>\
	<td></td>\
	</tr>\
</table>'; // 分时收费
var chargeruleHtml1 = '\
	<table style="width:100%;table-layout: fixed">\
	<tr height="35">\
	<td style="width:20%"><label>免费停车时长：</label></td>\
	<td  style="width:30%"><div style="position:relative"><div id="freeTimeNotice" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div><input name="freeTime"  type="text"></div></td>\
<td  style="width:20%"><label>（分钟）</label></td>\
<td  style="width:30%"></td>\
</tr>\
<tr height="35">\
<td colspan="2"><a id="timebtn" class="btn bg-blue btn-sm"><span>分时段收费</span></a></td>\
<td></td>\
<td></td>\
</tr>\
	<tr height="35">\
	<td colspan="4" ><label>停车时间超过最大时段收费金额：</label><div style="display:inline-block;position:relative;"><div id="maxMoneyNotice" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div><input style="width:70%;" class="money"  name="maxMoney"  type="text"><label>（元）</label></div></td>\
</tr>\
<tr height="35">\
<td style="width:20%"><label>是否启用封顶金额：</label></td>\
	<td style="width:30%"><input  name="useTopMoney" type="checkbox"/></td>\
	<td id="topmoneylabel" style="width:20%;display:none" >封顶金额：</td>\
	<td id="topmoney"style="width:30%;display:none"><div style="display:inline-block;position:relative;"><div id="topMoneyNotice" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div><input style="width:70%" type="text" name="topMoney"/><label>（元）</label></div></td>\
</tr>\
</table>'; // 分时段收费
var chargeruleHtml2 = '	\
	<table style="width:100%;table-layout: fixed">\
	<tr height="35">\
<td  style="width:20%"><label>免费停车时长：</label></td>\
<td  style="width:30%"><div style="position:relative"><div id="freeTimeNotice" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div><input  name="freeTime" type="text"></div></td>\
<td  style="width:20%"><label>（分钟）</label></td>\
<td  style="width:30%"></td>\
</tr>\
<tr height="35">\
<td colspan="2"><a id="timebtn" class="btn bg-blue btn-sm"><span>时段收费</span></a></td>\
<td></td>\
<td></td>\
<tr height="35">\
<td style="width:20%"><label>是否启用封顶金额：</label></td>\
	<td style="width:30%"><input name="useTopMoney" type="checkbox"/></td>\
	<td id="topmoneylabel" style="width:20%;display:none" >封顶金额：</td>\
	<td id="topmoney"style="width:30%;display:none"><div style="display:inline-block;position:relative;"><div id="topMoneyNotice" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div><input style="width:70%" type="text" name="topMoney"/><label>（元）</label></div></td>\
</tr>\
	</tr>\
	<tr height="35">\
	<td><label>是否包括免费时间：</label></td>\
	<td><select name="includeFreeTime"><option value="0">是</option><option value="1">否</option></select></td>\
	<td></td>\
	<td></td>\
	</tr>\
</table>'; // 按单位时段收费
var chargeruleHtml3 = '<table style="width:100%;table-layout: fixed">\
	<tr height="35">\
	<td  style="width:20%"><label>支付金额：</label></td>\
	<td  style="width:30%"><div style="position:relative"><div id="payMoneyNotice" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div><input class="money" name="danjia" type="text"></div></td>\
	<td  style="width:20%"><label>（元）</label></td>\
	<td  style="width:30%"></td>\
	</tr>\
	</table>'; // 按次收费
var chargeruleHtml4 = '<table style="width:100%;table-layout: fixed">\
	<tr height="35">\
	<td  style="width:20%"><label>免费停车时长：</label></td>\
	<td  style="width:30%"><div style="position:relative"><div id="freeTimeNotice" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div><input name="freeTime" type="text"></div></td>\
	<td  style="width:20%"><label>（分钟）</label></td>\
	<td  style="width:30%"></td>\
	</tr>\
	<tr height="35">\
	<td><label>首段收费时长：</label></td>\
	<td><div style="position:relative"><div id="firstTimeNotice" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div><input name="firstTime" style="width:60%" type="text"><label>（分钟）</label></div></td>\
	<td><label>首段收费金额：</label></td>\
	<td><div style="position:relative"><div id="firstMoneyNotice" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div><input name="firstMoney" class="money" style="width:70%" type="text"><label>（元）</label></div></td>\
	</tr>\
	<tr height="35">\
	<td><label>间隔收费时长：</label></td>\
	<td><div style="position:relative"><div id="intervalTimeNotice" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div><input style="width:60%" name="intervalTime" type="text"><label>（分钟）</label></div></td>\
	<td><label>间隔收费金额：</label></td>\
	<td><div style="position:relative"><div id="intervalMoneyNotice" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div><input style="width:70%" class="money" name="intervalMoney" type="text"><label>（元）</label></div></td>\
	</tr>\
	<tr height="35">\
	<td style="width:20%"><label>启用每天封顶金额：</label></td>\
	<td style="width:30%"><input id="useTop1" name="useTopMoney" type="checkbox" value="1"></td>\
	<td id="topmoneylabel" style="width:20%;display:none" >封顶金额：</td>\
	<td id="topmoney"style="width:30%;display:none"><div style="display:inline-block;position:relative;"><div id="topMoneyNotice" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div><input style="width:70%" type="text" name="topMoney"/><label>（元）</label></div></td>\
	</tr>\
	<tr height="35">\
	<td style="width:20%"><label>启用24小时封顶金额：</label></td>\
	<td style="width:30%"><input id="useTop2" name="useTopMoney" type="checkbox" value="2"/></td>\
	<td></td>\
	<td></td>\
	</tr>\
	</table>'; // 按时收费
var chargeruleHtml5 = '\
	<table style="width:100%;table-layout: fixed">\
	<tr height="35">\
	<td style="width:20%"><label>规则类型：</label></td>\
	<td style="width:30%"><select name="type" ><option value="0"> 包月</option><option value="1"> 包年</option></select></td>\
	<td style="width:20%"></td>\
	<td style="width:30%"></td>\
	</tr>\
	<tr height="35">\
	<td><label>包期金额：</label></td>\
	<td><div style="position:relative"><div id="moneyNotice" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div><input class="money" name="money" type="text" /></div></td>\
	<td><label>（元）</label></td>\
	<td></td>\
	</tr>\
	</table>\
'; // 包期收费
var chargeruleHtml6 = ''; // 不收费
var chargeruleHtml8 = ''; // 派车
var chargeruleHtml7 = '<table style="width:100%;table-layout: fixed">\
	<tr>\
	<td style="width:20%"></td>\
	<td style="width:30%"></td>\
	<td style="width:20%"></td>\
	<td style="width:30%"></td>\
	</tr>\
	<tr height="20">\
	<td><label >白天</label></td>\
	<td colspan="3"><hr style="height:2px;border:none;border-top:2px solid #185598;margin-top:8px;margin-bottom:8px;"/></td>\
	</tr>\
	<tr height="20">\
	<td><label>按时段收费开始时间：</label></td>\
	<td><div class="bootstrap-timepicker"><input name="periodStartTime"  class="timepicker"  type="text"/></div></td>\
	<td></td>\
	<td></td>\
	</tr>\
	<tr height="20">\
	<td><label>首段时间：</label></td>\
	<td><div style="position:relative"><div id="firstPeriodTimeNotice" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div><input name="firstPeriodTime" style="width:60%" type="text"/><label>（分钟）</label></div></td>\
	<td><label>首段时间收费：</label></td>\
	<td><div style="position:relative"><div id="firstPeriodMoneyNotice" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div><input class="money" name="firstPeriodMoney" style="width:70%" type="text"/><label>（元）</label></div></td>\
	</tr>\
	<tr height="20">\
	<td><label>单位时间：</label></td>\
	<td><div style="position:relative"><div id="unitTimeNotice" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div><input name="unitTime" style="width:60%" type="text"/><label>（分钟）</label></div></td>\
	<td><label>单位时间收费：</label></td>\
	<td><div style="position:relative"><div id="unitMoneyNotice" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div><input name="unitMoney" class="money" style="width:70%" type="text"/><label>（元）</label></div></td>\
	</tr>\
	<tr height="20">\
	<td><label>晚上</label></td>\
	<td colspan="3"><hr style="height:2px;border:none;border-top:2px solid #185598;margin-top:8px;margin-bottom:8px;"/></td>\
	</tr>\
	<tr height="20">\
	<td><label>按次开始收费时间：</label></td>\
	<td><div  class="bootstrap-timepicker"><input name="anciStartTime" class="timepicker"   type="text"/></div></td>\
	<td><label>每次收费：</label></td>\
	<td><div style="position:relative"><div id="meiciMoneyNotice" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div><input name="meiciMoney" class="money" style="width:70%" type="text"/><label>（元）</label></div></td>\
	</tr>\
	<tr height="20">\
	<td><label>24小时</label></td>\
	<td colspan="3"><hr style="height:2px;border:none;border-top:2px solid #185598;margin-top:8px;margin-bottom:8px;"/></td>\
	</tr>\
	<tr height="20">\
	<td><label>免费时长：</label></td>\
	<td><div style="position:relative"><div id="freeTimeNotice" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div><input name="freeTime" style="width:60%" type="text"><label>（分钟）</label></div></td>\
	<td colspan="2"><label>超出免费时长是否收费：</label><input name="overFreeTimeCharge" type="checkbox"/></td>\
	</tr>\
	<tr height="20">\
	<td  style="width:20%"><label>24小时内是否启用封顶金额：</label></td>\
	<td style="width:30%"><input name="useTopMoneyin24" type="checkbox"/></td>\
	<td id="topmoneylabel" style="width:20%;display:none" >封顶金额：</td>\
	<td id="topmoney"style="width:30%;display:none"><div style="display:inline-block;position:relative;"><div id="topMoneyNotice" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div><input style="width:70%" type="text" name="topMoney"/><label>（元）</label></div></td>\
	</tr>\
	<tr height="20">\
	<td colspan="4"><label>24小时内存在按时段收费则全按时段收费：</label><input name="byPeriodin24" type="checkbox"/></td>\
	</tr>\
	</table>'; // 日夜分时按次收费
var reduceruleHtml = '<div>\
	<table id="reduceruleRule" style="width:100%;table-layout:fixed">\
	<tr height="35px">\
	<td style="width:20%;"><label>规则名称：</label></td>\
	<td style="width:30%;"><div style="position:relative"><div id="ruleNameNotice" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div><input name="ruleName" type="text"/></div></td>\
	<td style="width:20%;"><label>减免方式：</label></td>\
	<td style="width:30%;"><select name="jianmianfangshi"><option value="1">减免金额</option><option value="2">打折</option><option value="3">全免</option></select></td>\
	</tr>\
	<tr id="jianmiantr" height="35px">\
	<td><label>金额：</label></td>\
	<td><div style="position:relative"><div id="jianmianNotice1" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div><input id="jianmian" name="jianmian" class="money" style="width:70%" type="text"/><label>（元）</label></div></td>\
	<td></td>\
	<td></td>\
	</tr>\
	<tr id="zhekoutr" height="35px">\
	<td><label>折扣：</label></td>\
	<td><div style="position:relative"><div id="jianmianNotice2" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div><input id="zhekou" name="jianmian" class="money" style="width:70%" type="text"/><label>（%）</label></div></td>\
	<td></td>\
	<td></td>\
	</tr>\
	<tr id="qiyong" height="35px">\
	<td><label>是否启用：</label></td>\
	<td><select id="shifouqiyong" name="custom1" class="" style="width:70%"><option value="1">否</option><option value="0">是</option></select></td>\
	<td></td>\
	<td></td>\
	</tr>\
	<tr height="35px">\
	<td><label>开始时间：</label></td>\
	<td><div class="date">\
	<input type="text" class="mydatepicker Wdate" id="begintime1" onClick="WdatePicker({onpicked:function(){$(this).trigger(\'change\');},dateFmt:\'yyyy-MM-dd HH:mm\',maxDate:\'#F{$dp.$D(\\\'endtime1\\\',{M:0})}\'})"   name="startdate" >\
	</div></td>\
	<td><label>结束时间：</label></td>\
	<td><div class="date" >\
	<input type="text" class="mydatepicker Wdate" id="endtime1"  onClick="WdatePicker({onpicked:function(){$(this).trigger(\'change\');},dateFmt:\'yyyy-MM-dd HH:mm\',minDate:\'#F{$dp.$D(\\\'begintime1\\\',{M:0})}\'})"   name="enddate"  >\
	</div></td>\
	</tr>\
	<tr height="80px">\
	<td><label>描述：</label></td>\
	<td colspan="3"><div style="position:relative"><div id="noteNotice" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div><textarea id="note" style="width:100%;height:100%;resize:none" ></textarea></div></td>\
	<td></td>\
	<td></td>\
	</tr>\
	<tr height="80px">\
	<td><label style="color:red;">提示：</label></td>\
	<td colspan="3">如若当前页“是否启用”选择“否”，您在减免规则所填写的信息将得不到保存且不会生效。</td>\
	<td></td>\
	<td></td>\
	</tr>\
	</table></div>';
var mobilereduceruleHtml = '<div>\
	<table id="mobilereduceruleRule" style="width:100%;table-layout:fixed">\
	<tr height="35px">\
	<td style="width:20%;"><label>规则名称：</label></td>\
	<td style="width:30%;"><div style="position:relative"><div id="ruleNameNotice" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div><input name="ruleName" type="text"/></div></td>\
	<td style="width:20%;"><label>减免方式：</label></td>\
	<td style="width:30%;"><select name="jianmianfangshi"><option value="4">减免金额</option><option value="5">打折</option></select></td>\
	</tr>\
	<tr id="jianmiantr" height="35px">\
	<td><label>金额：</label></td>\
	<td><div style="position:relative"><div id="jianmianNotice3" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div><input id="jianmian" name="jianmian" class="money" style="width:70%" type="text"/><label>（元）</label></div></td>\
	<td></td>\
	<td></td>\
	</tr>\
	<tr id="zhekoutr" height="35px">\
	<td><label>折扣：</label></td>\
	<td><div style="position:relative"><div id="jianmianNotice4" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div><input id="zhekou" name="jianmian" class="money" style="width:70%" type="text"/><label>（%）</label></div></td>\
	<td></td>\
	<td></td>\
	</tr>\
	<tr id="qiyong" height="35px">\
	<td><label>是否启用：</label></td>\
	<td><select id="shifouqiyong" name="custom1" class="" style="width:70%"><option value="1">否</option><option value="0">是</option></select></td>\
	<td></td>\
	<td></td>\
	</tr>\
	<tr height="35px">\
	<td><label>开始时间：</label></td>\
	<td><div class="date">\
	<input type="text" class="mydatepicker Wdate" id="begintime3" onClick="WdatePicker({onpicked:function(){$(this).trigger(\'change\');},dateFmt:\'yyyy-MM-dd HH:mm\',maxDate:\'#F{$dp.$D(\\\'endtime3\\\',{M:0})}\'})"   name="startdate" >\
	</div></td>\
	<td><label>结束时间：</label></td>\
	<td><div class="date" >\
	<input type="text" class="mydatepicker Wdate" id="endtime3"  onClick="WdatePicker({onpicked:function(){$(this).trigger(\'change\');},dateFmt:\'yyyy-MM-dd HH:mm\',minDate:\'#F{$dp.$D(\\\'begintime3\\\',{M:0})}\'})"   name="enddate"  >\
	</div></td>\
	</tr>\
	<tr height="80px">\
	<td><label>描述：</label></td>\
	<td colspan="3"><div style="position:relative"><div id="noteNotice" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div><textarea id="note" style="width:100%;height:100%;resize:none" ></textarea></div></td>\
	<td></td>\
	<td></td>\
	</tr>\
	<tr height="80px">\
	<td><label style="color:red;">提示：</label></td>\
	<td colspan="3">如若当前页“是否启用”选择“否”，您在移动支付减免规则所填写的信息将得不到保存且不会生效。</td>\
	<td></td>\
	<td></td>\
	</tr>\
	</table></div>';
var exceptionruleHtml = '<div>\
	<table id="exceptionRule" style="width:100%;table-layout:fixed">\
	<tr height="35px">\
	<td style="width:20%;"><label>规则名称：</label></td>\
	<td style="width:30%;"><div style="position:relative"><div id="ruleNameNotice" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div><input name="ruleName" type="text"/></div></td>\
	<td style="width:20%;"><label>金额：</label></td>\
	<td style="width:30%;"><div style="position:relative"><div id="jianmianNotice" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div><input name="jianmian"  class="money" style="width:70%" type="text"/><label>（元）</label></div></td>\
	</tr>\
	<tr id="qiyong" height="35px">\
	<td><label>是否启用：</label></td>\
	<td><select id="shifouqiyong" name="custom1" class="" style="width:70%"><option value="1">否</option><option value="0">是</option></select></td>\
	<td></td>\
	<td></td>\
	</tr>\
	<tr height="35px">\
	<td><label>开始时间：</label></td>\
	<td><div class="date">\
	<input type="text" class="mydatepicker  Wdate" id="begintime2" onClick="WdatePicker({onpicked:function(){$(this).trigger(\'change\');},dateFmt:\'yyyy-MM-dd HH:mm\',maxDate:\'#F{$dp.$D(\\\'endtime2\\\',{M:0})}\'})"   name="startdate" >\
	</div></td>\
	<td><label>结束时间：</label></td>\
	<td><div class="date" >\
	<input type="text" class="mydatepicker  Wdate" id="endtime2" onClick="WdatePicker({onpicked:function(){$(this).trigger(\'change\');},dateFmt:\'yyyy-MM-dd HH:mm\',minDate:\'#F{$dp.$D(\\\'begintime2\\\',{M:0})}\'})"   name="enddate" >\
	</div></td>\
	</tr>\
	<tr height="80px">\
	<td><label>描述：</label></td>\
	<td colspan="3"><div style="position:relative"><div id="noteNotice" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div><textarea id="note" style="width:100%;height:100%;resize:none" ></textarea></div></td>\
	<td></td>\
	<td></td>\
	</tr>\
	<tr height="80px">\
	<td><label style="color:red;">提示：</label></td>\
	<td colspan="3">如若当前页“是否启用”选择“否”，您在异常规则所填写的信息将得不到保存且不会生效。</td>\
	<td></td>\
	<td></td>\
	</tr>\
	</table></div>';
function ruleadd_shown(devClone, flag,sel_obj) {
	

	function refresh(){
		if($("#" + config2.id+'_wrapper').find(".paginate_button.active").size() >0){
        	$("#" + config2.id+'_wrapper').find(".paginate_button.active").click(); //点击分页按钮获取新数据
		} else{
			$("#" + config2.id+'_wrapper').find('#' + config2.id + " th:first").click(); //数据表中没有数据，通过排序列获取数据
		}	
	}
	
	if(flag==0){
		var selnode = wade.tree.selectNode(ztreeConf.id);
		devClone.hide();
	    if (selnode == undefined) {
	        devClone.modal('hide');
	        wade.libs.alert("请先选择车辆类型",2);
	        return false;
	    }
	    var res;
	    $.ajax({
			url:"/pfm/chargerule/getifhasrule?carTypeId="+selnode.id,
			type:"get",
			async:false,
			success:function(data){
				var result = JSON.parse(data);
				res=result.success;
			}
		});
	    if(!res){
	    	 devClone.modal('hide');
	    	 alert("当前车辆类型已存在收费规则");
	    	return false;
	    }
	    devClone.show();
        }
	var curTab;//记录当前tab页 0收费规则 1 减免规则 2异常放行规则
	  /** ****************验证函数Start************** */
    function verifyRuleName(form,id,data) {
    	var result=100;
    	
        if (data.trim() == "") {
        	result="请输入规则名称";
        } else if (data.trim() != data) {
        	result="请不要带空格";
        }
        else if(data.length>15){
        	result="请不要超过15个字符";
        }
        if(result!=100){
        	 devClone.find('.point-out').hide();
             devClone.find("#"+form).find("#"+id).html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>'+result+'</span>').show();

             return false
        }
        devClone.find('#'+id+'.point-out').hide();
        return true;

    }
    //金额表单通用验证,2位小数
    function verifyMoney(id,data){
    	var result=checkMoney(data);  
        if(result!=100){
        	devClone.find('.point-out').hide();
            devClone.find("#"+id).html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>'+result+'</span>').show();

            return false;
        }
        devClone.find('#'+id+'.point-out').hide();
        return true;
    }
    function verifyMoney2(devClone,$element,data){//表格中金额验证
    	var result=checkMoney(data);  
        if(result!=100){
        	devClone.find('.point-out').hide();
        	var id= new Date().getTime();
        	if($element.parent().css("position")!='relative'){
        		$element.wrap('<div style="position:relative"></div>');
        	}
        	$element.prev().remove();
        	$element.before('<div id="'+id+'" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div>');            devClone.find("#"+id).html('<span class="point-box errorbgTop dropdown" ><i class="sprite icon_arrow_up2 pa"></i>'+result+'</span>').show();

            return false;
        }
        $element.prev().remove();
        return true;
    }
    //分钟表单通用验证,分钟
    function verifyTimeMinute(id,data){
    	var result=100;
        if (data.trim() == "") {
        	result="不能为空！";
        } else if (!RegExpObj.isNumber(data)) {
        	result="请输入正确的整数！";
        }else{
        	var it = parseInt(data);
        	if(it>10000){
        		result="请输入10000以内的整数！"
        	}
        }
        if(result!=100){
        	devClone.find('.point-out').hide();
            devClone.find("#"+id).html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>'+result+'</span>').show();

            return false;
        }
        devClone.find('#'+id+'.point-out').hide();
        return true;
    }
    //验证备注 减免规则 异常规则里的
    function verifyNote(form,id,data){
    	var result=100;
        if (data.length>50) {
        	result="不能超过50个字符！";
        }        
        if(result!=100){
        	devClone.find('.point-out').hide();
            devClone.find("#"+form+" #"+id).html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>'+result+'</span>').show();

            return false;
        }
        devClone.find("#"+form+" #"+id).hide();
        return true;
    }
    function verifyTimeMinute2(devClone,$element,data){
    	var result=100;
        if (data.trim() == "") {
        	result="不能为空！";
        } else if (!RegExpObj.isNumber(data)) {
        	result="请输入正确的整数！";
        }
        if(result!=100){
        	devClone.find('.point-out').hide();
        	var id= new Date().getTime();
        	if($element.parent().css("position")!='relative'){
        		$element.wrap('<div style="position:relative"></div>');
        	}
        	$element.prev().remove();
        	$element.before('<div id="'+id+'" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div>');
            devClone.find("#"+id).html('<span class="point-box errorbgTop dropdown" ><i class="sprite icon_arrow_up2 pa"></i>'+result+'</span>').show();

            return false;
        }
        
        $element.prev().remove();
        return true;
    }
    //验证时间控件input不能为空
    function verifyMinute(devClone,$element,data){
    	var result=100;
        if (data.trim() == "") {
        	result="不能为空！";
        } 
        if(result!=100){
        	devClone.find('.point-out').hide();
        	var id= new Date().getTime();
        	if($element.parent().css("position")!='relative'){
        		$element.wrap('<div style="position:relative"></div>');
        	}
        	$element.prev().remove();
        	$element.before('<div id="'+id+'" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div>');            devClone.find("#"+id).html('<span class="point-box errorbgTop dropdown" ><i class="sprite icon_arrow_up2 pa"></i>'+result+'</span>').show();

            return false;
        }
       
        $element.prev().remove();
          
        return true;
    }
    /** ****************验证函数END**************** */
    //填充form
    devClone.find("#chargeForm").empty();
    devClone.find("#chargeForm").append(chargeruleHtml);
    devClone.find("#reduceForm").empty();
    devClone.find("#reduceForm").append(reduceruleHtml);
    devClone.find("#mobilereduceForm").empty();
    devClone.find("#mobilereduceForm").append(mobilereduceruleHtml);
    devClone.find("#exceptionForm").empty();
    devClone.find("#exceptionForm").append(exceptionruleHtml);
    
    
    var times=null;//记录时段和规则信息
    var fenshiYanzheng;
    var fenshiduanYanzheng;
    var danweishiduanYanzheng;
    var anciYanzheng;
    var anshiYanzheng ;
    var  baoqiYanzheng ;
    var noYanzheng ;
    var  riyeYanzheng;
    function reduceexceptionFormData(){
    	
    var jsonuserinfo = devClone.find("#reduceForm").serializeObject();
        $.extend(jsonuserinfo,{note:devClone.find("#reduceForm").find("#note").val()});
        if(flag==0){
        	$.extend(jsonuserinfo,{carTypeId:selnode.id});
        }
        if(flag==1){
        	$.extend(jsonuserinfo,{jianmianyichangId:sel_obj.jianmianId});
        }
        var formdata2 = "condition2=" + JSON.stringify(jsonuserinfo);
        //
	jsonuserinfo = devClone.find("#exceptionForm").serializeObject();
        $.extend(jsonuserinfo,{note:devClone.find("#exceptionForm").find("#note").val()});
        if(flag==0){
        	$.extend(jsonuserinfo,{carTypeId:selnode.id});
        }
        if(flag==1){
        	$.extend(jsonuserinfo,{jianmianyichangId:sel_obj.yichangId});
        }
        var formdata3 = "condition3=" + JSON.stringify(jsonuserinfo);
        //
    jsonuserinfo = devClone.find("#mobilereduceForm").serializeObject();
        $.extend(jsonuserinfo,{note:devClone.find("#mobilereduceForm").find("#note").val()});
        if(flag==0){
        	$.extend(jsonuserinfo,{carTypeId:selnode.id});
        }
        if(flag==1){
        	$.extend(jsonuserinfo,{jianmianyichangId:sel_obj.mobilejianmianId});//\
        }
        var formdata4 = "condition4=" + JSON.stringify(jsonuserinfo);
        return "&"+formdata2+"&"+formdata3+"&"+formdata4;
    }
    
    devClone.find("#chargeForm").find('input[name=ruleName]').on("blur",
            function() {
                var val = $(this).val();
                if(val!="")
                verifyRuleName('chargeForm','ruleNameNotice',val);
            }).on("change keyup",function(){devClone.find("#chargeForm #ruleNameNotice").hide();});
    
    
    devClone.find("#chargeForm").find("#type").on('change',
            function() {
    	 
                switch ($(this).val()) {
              
                case '0'://分时
                    {
                        devClone.find("#chargeForm").find("#bottom").empty().append(chargeruleHtml0);
                        devClone.find("#chargeForm").find("[name=tempMatch]").val(0);
                        devClone.find("#chargeForm").find("[name=tempMatch]").removeAttr('disabled');
                        devClone.find("#chargeForm").find(":hidden[name=tempMatch]").remove();

                        devClone.find("[href='#reducerule']").parent().show();
                        devClone.find("[href='#mobilereducerule']").parent().show();
                        devClone.find("[href='#exceptionrule']").parent().show();
                        /** **********验证***************** */
                        devClone.find("#chargeForm").find('input[name=freeTime]').on("blur",
                        function() {
                            var val = $(this).val();
                            if(val!="")
                            verifyTimeMinute('freeTimeNotice',val);

                        }).on("change keyup",function(){ devClone.find("#freeTimeNotice").hide();});
                        devClone.find("#chargeForm").find('input[name=topMoney]').on("blur",
                                function() {
                                    var val = $(this).val();
                                    if(val!="")
                                    verifyMoney('topMoneyNotice',val);

                                }).on("change keyup",function(){ devClone.find("#topMoneyNotice").hide();});
                        fenshiYanzheng=verifyFenshi;
                        // 分时收费，提交确认规则
                        function verifyFenshi(event) {
                            var val1 = devClone.find("#chargeForm").find('input[name=ruleName]').val();

                            if (!verifyRuleName("chargeForm",'ruleNameNotice',val1)) {
                                event.stopImmediatePropagation(); // 
                                return false;
                            }
                            var val2 = devClone.find("#chargeForm").find('input[name=freeTime]').val();

                            if (!verifyTimeMinute('freeTimeNotice',val2)) {
                                event.stopImmediatePropagation(); // 
                                return false;
                            }
                            if(devClone.find("#chargeForm").find('#useTop1').prop('checked')||devClone.find("#chargeForm").find('#useTop2').prop('checked'))
                            {
                            var val3 = devClone.find("#chargeForm").find('input[name=topMoney]').val();
                            if (!verifyMoney('topMoneyNotice',val3)) {
                                event.stopImmediatePropagation(); // 
                                return false;
                            }
                            }
                            if(timearr.length==0){
                            	alert("请添加分时时段")
                            	 event.stopImmediatePropagation(); // 
                                 return false;
                            }

                            return true;
                        }
                        /** **************验证**************** */
                     
                        var timearr = [];
                        if(flag==1){
                        	$.ajax({
                        		url:"/pfm/chargerule/getfenshitimearr",
                        		type:"get",
                        		data:"ruleid="+sel_obj.ruleId,
                        		success:function(data){
                        			var result = JSON.parse(data);
                           			
                        			$.each(result,function(index,value){
                        				if(value!=null){
                        				 timearr[index] = {};
                                         timearr[index].start =value.startTime.substring(11,16);
                                         timearr[index].end =value.endTime.substring(11,16);
                                         timearr[index].money =value.chargeMoney;
                        				}
                        			});
                        		}
                        	});
                        }
                        devClone.find("#chargeForm").find("#useTop1").on('click',function(){
                        	
                        	if($(this).prop("checked")){
                        		devClone.find("#chargeForm").find("#topmoneylabel").show();
                        		devClone.find("#chargeForm").find("#topmoney").show();
                        		devClone.find("#chargeForm").find("#useTop2").prop('checked',false);
                        	}
                        	else{
                        		devClone.find("#chargeForm").find("#topmoneylabel").hide();
                        		devClone.find("#chargeForm").find("#topmoney").hide();
                        	}
                        });
                        devClone.find("#chargeForm").find("#useTop2").on('click',function(){
                        	
                        	if($(this).prop("checked")){
                        		devClone.find("#chargeForm").find("#topmoneylabel").show();
                        		devClone.find("#chargeForm").find("#topmoney").show();
                        		devClone.find("#chargeForm").find("#useTop1").prop('checked',false);
                        	}
                        	else{
                        		devClone.find("#chargeForm").find("#topmoneylabel").hide();
                        		devClone.find("#chargeForm").find("#topmoney").hide();
                        	}
                        });
                        var num = 0;
                        devClone.find("#chargeForm").find("#timebtn").on('click',
                        function() {

                            var devClone2 = $("#timeModal").clone(true);
                            devClone2.find('.modal-title').text("分时计费配置");
                            devClone2.find('.modal-body').append("<a id='add' class='btn bg-blue btn-sm'><span>新增时间段</span></a><a id='delete' class='btn bg-red btn-sm' style='margin-left:30px'><span>删除时间段</span></a><table id='times' style='margin-top:10px;width:100%'><tr><td align='center' width='10%'><input name='allcheck' type='checkbox'/></td><td align='center' width='15%'>序号</td><td align='center' width='25%'>起始时间</td><td align='center' width='25%'>结束时间</td><td align='center' width='25%'>收费金额（元）</td></tr></table>");
                       
                            devClone2.find('#add').on('click',
                            function() {
                                var n = devClone2.find(".data:last td").eq(1).text();
                                if (n == "") {
                                    n = 1;
                                } else {
                                    n++;
                                }
                             
                               var last=  devClone2.find("#times").find(".data:last .timepicker.end").val();
                             
                               if(last===undefined){
                                   devClone2.find("#times").append('<tr class="data" height="30px"><td align="center"><input type="checkbox"/></td><td align="center">' + n + '</td><td align="center"><div  class="bootstrap-timepicker"><input style="width:80%" type="text"  class="timepicker start" value="00:00" onClick="WdatePicker({dateFmt:\'HH:mm\',onpicked:function(){$(this).prev().remove();}})"/></div></td><td align="center"><div  class="bootstrap-timepicker"><input style="width:80%" type="text"  class="timepicker end"  onClick="WdatePicker({dateFmt:\'HH:mm\',onpicked:function(){$(this).prev().remove();}})"/></div></td><td align="center"><input style="width:80%" type="text" class="money" /></td></tr>');
 
                               }
                               else{
                            	   
                               devClone2.find("#times").append('<tr class="data" height="30px"><td align="center"><input type="checkbox"/></td><td align="center">' + n + '</td><td align="center"><div  class="bootstrap-timepicker"><input style="width:80%" type="text"  class="timepicker start" value="'+last+'" onClick="WdatePicker({dateFmt:\'HH:mm\',onpicked:function(){$(this).prev().remove();}})"/></div></td><td align="center"><div  class="bootstrap-timepicker"><input style="width:80%" type="text"  class="timepicker end"  onClick="WdatePicker({dateFmt:\'HH:mm\',onpicked:function(){$(this).prev().remove();}})"/></div></td><td align="center"><input style="width:80%" type="text" class="money" /></td></tr>');
                              
                               }

                               
                                devClone2.find(".data:last").find(".money").on("blur",
                                        function() {
                                            var val = $(this).val();
                                            if(val!="")
                                            verifyMoney2(devClone2,$(this),val)

                                        }).on("change keyup",function(){$(this).prev().remove();});
                                devClone2.find(".data:last").find(".timepicker").on("blur",
                                		function() {
                                	var val = $(this).val();
                                	if(val!="")
                                	verifyMinute(devClone2,$(this),val)

                                }).on("change keyup",function(){$(this).prev().remove();});
                                if (devClone2.find("[name='allcheck']").prop("checked")) {
                                    devClone2.find(":checkbox").prop("checked", true);
                                }

                                devClone2.find(":checkbox:not([name='allcheck'])").unbind('click').click(function() {
                                    if (!$(this).prop('checked')) {
                                        devClone2.find('[name="allcheck"]').prop("checked", false);
                                    } else {
                                        if (devClone2.find(":checkbox:checked").length == (devClone2.find(":checkbox").length - 1)) devClone2.find('[name="allcheck"]').prop("checked", true);
                                    }
                                });

                            });
                            
                            function verifyTimeArr(){
                            	var len= $("tr.data").length;
                            	var startarr=[];
                            	var endarr=[];
                            	var err="";
                            	$(".timepicker").each(function(index,val){
                            		 if($(val).hasClass("start")){
                            			 startarr.push($(val).val());
                            		 }
                            		 else{
                            			 endarr.push($(val).val());
                            		 }
                            	});
                            	for(var i=0;i<startarr.length;i++){
                            		if(i==0){
                            			if(startarr[i]!=endarr[endarr.length-1]){
                            				err+="请保证第一行的起始时间和最后一行的结束时间一致。\r\n"
                            					
                            			}
                            			continue;
                            		}
                            		if(startarr[i]!=endarr[i-1]){
                        				err+=("第"+(i+1)+"行的起始时间和上一行的结束时间不一致，请保持连续。\r\n");
                        			}
                            	}
                            	//验证是否所有时段相加是一个24小时 24*60=1440
                            	var totalm=0;
                            	
                            	for(var i=0;i<startarr.length;i++){
                            		
                            	
                            		var starth = startarr[i].split(":")[0];
                            		var startm=startarr[i].split(":")[1];
                            		var starttm = Number(starth)*60+Number(startm);
                            		var endh = endarr[i].split(":")[0];
                            		var endm=endarr[i].split(":")[1];
                            		var endtm = Number(endh)*60+Number(endm);
                            		if(starttm==endtm){
                            			totalm+=1440;
                            		}
                            		if(starttm<endtm){
                            			totalm+=(endtm-starttm);
                            		}
                            		if(starttm>endtm){
                            			totalm+=(endtm+(1440-starttm));	
                            		}
                            	}
                            	if(totalm>1440){
                            		err+="您所输入的时间段包含重复时间，请修改。\r\n";
                            	}
                            	if(err!=""){
                            		alert(err);
                            		return false;
                            	}
                            	else{
                            		return true;
                            	}
                            }
                            devClone2.find("[name='allcheck']").click(function() {
                                if ($(this).prop("checked")) {
                                    devClone2.find(":checkbox").prop("checked", true);
                                } else {
                                    devClone2.find(":checkbox").prop("checked", false);
                                }
                            });

                            devClone2.find("#delete").on("click",
                            function() {
                                if (devClone2.find(":checkbox:checked").length == 0) {
                                    alert("请选择要删除的行");
                                }

                                devClone2.find(":checkbox:checked:not([name='allcheck'])").parent().parent().remove();

                            });
                           
                            devClone2.modal({backdrop: 'static', keyboard: false}).on('hidden.bs.modal',
                            function(event) {
                                $(this).remove();
                            });
                            
                            devClone2.find("#save").click(function() {
                                var err=0;
                                num = 0;
                                devClone2.find("#times .data").each(function(index, val) {
                                	
                                	 if(!verifyMinute(devClone2,$(val).find(".timepicker"),$(val).find(".timepicker").val())){
                                		 err++;
                                         return false;
                                	 }
                                      if(  ! verifyMoney2(devClone2,$(val).find(".money"),$(val).find(".money").val()))
                                      {
                                            err++;
                                            return false;
                                       }
                                      
                                      
                                });
                               
                                if(!err){
                                	 if(!verifyTimeArr()){ 
                                 	  	err++;
                                 	  	return false;
                                   		}
                                	timearr = [];
                                	devClone2.find("#times .data").each(function(index, val) {
                                		
                                        num++;
                                        timearr[index] = {};
                                        timearr[index].start = $(val).find(".start").val();
                                        timearr[index].end = $(val).find(".end").val();
                                        timearr[index].money = $(val).find(".money").val();
                                    });
                                	devClone2.modal('hide');
                                }
                            });

                            $.each(timearr,
                            function(index, val) {

                                devClone2.find("#times").append('<tr class="data" height="30px"><td align="center"><input type="checkbox"/></td><td align="center">' + (index + 1) + '</td><td align="center"><div  class="bootstrap-timepicker"><input style="width:80%" type="text"  class="timepicker start" onClick="WdatePicker({dateFmt:\'HH:mm\',onpicked:function(){$(this).prev().remove();}})"/></div></td><td align="center"><div  class="bootstrap-timepicker"><input style="width:80%"  type="text" class="timepicker end" onClick="WdatePicker({dateFmt:\'HH:mm\',onpicked:function(){$(this).prev().remove();}})"/></div></td><td align="center"><input style="width:80%" type="text" class="money"  /></td></tr>');
                                devClone2.find(".data:last").find(".start").val(val.start);
                                devClone2.find(".data:last").find(".end").val(val.end);
                                devClone2.find(".data:last").find(".money").val(val.money);
                                devClone2.find(".data:last").find(".money").on("blur",function() {
                                            var val = $(this).val();
                                            if(val!="")
                                            verifyMoney2(devClone2,$(this),val)

                                        }).on("change keyup",function(){$(this).prev().remove();});
                                devClone2.find(".data:last").find(".timepicker").on("blur",
                                		function() {
                                	var val = $(this).val();
                                	if(val!="")
                                	verifyMinute(devClone2,$(this),val)

                                }).on("change keyup",function(){$(this).prev().remove();});
                            });

                            devClone2.find(":checkbox:not([name='allcheck'])").click(function() {
                                if (!$(this).prop('checked')) {
                                    devClone2.find('[name="allcheck"]').prop("checked", false);
                                } else {
                                    if (devClone2.find(":checkbox:checked").length == (devClone2.find(":checkbox").length - 1)) devClone2.find('[name="allcheck"]').prop("checked", true);
                                }
                            });

                        });
                        devClone.find("#dosubmit").unbind('click').on('click',
                        function(e) {
                            if (!verifyFenshi(e)) {
                                return false;
                            }
                            var jsonuserinfo = devClone.find("#chargeForm").serializeObject();

                            var formdata = "condition=" + JSON.stringify(jsonuserinfo);
                            var times = "times=" + JSON.stringify(timearr);

                            $.ajax({ // 分时收费
                                type: 'post',
                                url: "/pfm/chargerule/add",
                                data: formdata + "&" + times+"&ruleId="+(flag==1?sel_obj.ruleId:"")+reduceexceptionFormData(),
                                success: function(data) {

                                    var result = JSON.parse(data);

                                    if (result.success) {
                                        devClone.modal('hide');
                                        flag==0?wade.libs.alert('添加成功'):wade.libs.alert('修改成功');
                                        refresh();
                                    } else {
                                        wade.libs.alert(result.msg, 0, devClone.find('div:first'));
                                        devClone.find("#submit").removeAttr("disabled");
                                    }

                                }
                            });
                            e.stopImmediatePropagation(); // 阻止lib提交
                        });
                        if(flag==1){//修改数据
                        	$.ajax({
                        		url:'/pfm/chargerule/getchargerulebyid',
                        		type:'get',
                        		data:"ruleid="+sel_obj.ruleId,
                        		success:function(data){
                        			var result = JSON.parse(data);
                        			var ruleType = result.ruleType;
                        			if(ruleType==0){
                        				devClone.find("#chargeForm").find("#type").val(0);
                           				devClone.find("#chargeForm").find("[name=tempMatch]").val(result.rule.tempMatch);
                        				devClone.find("#chargeForm").find("[name=ruleName]").val(result.rule.ruleName);
                        				devClone.find("#chargeForm").find("[name=freeTime]").val(result.timecharge.freeTime);
                        				devClone.find("#chargeForm").find("#typesel").val(sel_obj.typeName);
                        				devClone.find("#chargeForm").find("[name=carType]").val(result.rule.carTypeId);
                           				if(result.timecharge.useTopMoney==1){
                        					devClone.find("#chargeForm").find("#useTop1").prop("checked",true);
                        					devClone.find("#chargeForm").find('[name=topMoney]').val(result.timecharge.topMoney)
                            				devClone.find("#chargeForm").find("#topmoneylabel").show();
                                    		devClone.find("#chargeForm").find("#topmoney").show();
                        				}
                           				if(result.timecharge.useTopMoney==2){
                        					devClone.find("#chargeForm").find("#useTop2").prop("checked",true);
                        					devClone.find("#chargeForm").find('[name=topMoney]').val(result.timecharge.topMoney)
                            				devClone.find("#chargeForm").find("#topmoneylabel").show();
                                    		devClone.find("#chargeForm").find("#topmoney").show();
                        				}
                        			}
                        		}
                        	});
                        	
                        }

                    }
                    break;

                case '1'://分时段
                    {
                        devClone.find("#chargeForm").find("#bottom").empty().append(chargeruleHtml1);
                        devClone.find("#chargeForm").find("[name=tempMatch]").val(0);
                        devClone.find("#chargeForm").find("[name=tempMatch]").removeAttr('disabled');
                        devClone.find("#chargeForm").find(":hidden[name=tempMatch]").remove();

                        devClone.find("[href='#reducerule']").parent().show();
                        devClone.find("[href='#exceptionrule']").parent().show();
                        devClone.find("[href='#mobilereducerule']").parent().show();
                        /** **********验证***************** */
                        fenshiduanYanzheng=verifyFenshiDuan;
                        // 分时段
                        function verifyFenshiDuan(event) {
                            var val1 = devClone.find("#chargeForm").find('input[name=ruleName]').val();

                            if (!verifyRuleName('chargeForm','ruleNameNotice',val1)) {
                                event.stopImmediatePropagation(); // 
                                return false;
                            }
                            var val2 = devClone.find("#chargeForm").find('input[name=freeTime]').val();

                            if (!verifyTimeMinute('freeTimeNotice',val2)) {
                                event.stopImmediatePropagation(); // 
                                return false;
                            }
                            var val3 = devClone.find("#chargeForm").find('input[name=maxMoney]').val();

                            if (!verifyMoney('maxMoneyNotice',val3)) {
                                event.stopImmediatePropagation(); // 
                                return false;
                            }
                            if(devClone.find("#chargeForm").find('[name=useTopMoney]').prop('checked'))
                            {
                            var val4 = devClone.find("#chargeForm").find('input[name=topMoney]').val();

                            if (!verifyMoney('topMoneyNotice',val4)) {
                                event.stopImmediatePropagation(); // 
                                return false;
                            }
//                            var max=0;
//                    		$.each(timearr,function(index,value){
//                				
//                				 max+=Number(value.money);
//                				
//                			});
//                            if(max>Number(val4)){
//                            alert("封顶金额应大于分时段收费各时段总额!");
//                            event.stopImmediatePropagation(); // 
//                            return false;
//                            }
                            }
                            if(timearr.length==0){
                            	alert("请添加分时时段")
                            	 event.stopImmediatePropagation(); // 
                                 return false;
                            }
                            if(timearr[0].start!=devClone.find("#chargeForm").find("[name=freeTime]").val()){
                            	alert("免费停车时长应和第一条时段的起始时段相等")
                           	 	event.stopImmediatePropagation(); // 
                                return false;
                            }

                            return true;
                        }
                        devClone.find("#chargeForm").find("[name=useTopMoney]").on('click',function(){
                        	
                        	if($(this).prop("checked")){
                        		devClone.find("#chargeForm").find("#topmoneylabel").show();
                        		devClone.find("#chargeForm").find("#topmoney").show();
                        	}
                        	else{
                        		devClone.find("#chargeForm").find("#topmoneylabel").hide();
                        		devClone.find("#chargeForm").find("#topmoney").hide();
                        	}
                        });
                        devClone.find("#chargeForm").find('input[name=freeTime]').on("blur",
                        function() {
                            var val = $(this).val();
                            if(val!="")
                            verifyTimeMinute('freeTimeNotice',val);

                        }).on("change keyup",function(){devClone.find("#freeTimeNotice").hide();});
                        devClone.find("#chargeForm").find('input[name=maxMoney]').on("blur",
                        function() {
                            var val = $(this).val();
                            if(val!="")
                            verifyMoney('maxMoneyNotice',val);

                        }).on("change keyup",function(){devClone.find("#maxMoneyNotice").hide();});
                        devClone.find("#chargeForm").find('input[name=topMoney]').on("blur",
                                function() {
                                    var val = $(this).val();
                                    if(val!="")
                                    verifyMoney('topMoneyNotice',val);

                                }).on("change keyup",function(){devClone.find("#topMoneyNotice").hide();});
                        /** ****************************** */
                        var timearr = [];
                        var num = 0;
                        if(flag==1){
                        	$.ajax({
                        		url:"/pfm/chargerule/getfenshiduantimearr",
                        		type:"get",
                        		data:"ruleid="+sel_obj.ruleId,
                        		success:function(data){
                        			var result = JSON.parse(data);
                           			
                        			$.each(result,function(index,value){
                        				if(value!=null){
                        				 timearr[index] = {};
                                         timearr[index].start =value.startPeriod;
                                         timearr[index].end =value.endPeriod;
                                         timearr[index].money =value.chargeMoney;
                        				}
                        			});
                        		}
                        	});
                        }
                        devClone.find("#timebtn").on('click',
                        function() {

                            var devClone2 = $("#timeModal").clone(true);
                            devClone2.find('.modal-title').text("分时段计费配置");
                            devClone2.find('.modal-body').append("<a id='add' class='btn bg-blue btn-sm'><span>新增时段</span></a><a id='delete' class='btn bg-red btn-sm' style='margin-left:30px'><span>删除时段</span></a><table id='times' style='margin-top:10px;width:100%'><tr><td align='center' width='10%'><input name='allcheck' type='checkbox'/></td><td align='center' width='15%'>序号</td><td align='center' width='25%'>起始时段（分钟）</td><td align='center' width='25%'>结束时段（分钟）</td><td align='center' width='25%'>收费金额（元）</td></tr></table>");
                            devClone2.find('#add').on('click',
                            function() {
                                var n = devClone2.find(".data:last td").eq(1).text();
                                if (n == "") {
                                    n = 1;
                                } else {
                                    n++;
                                }
                                var last=  devClone2.find("#times").find(".data:last .end").val();
                                
                                if(last===undefined){
                                    devClone2.find("#times").append('<tr class="data" height="30px"><td align="center"><input type="checkbox"/></td><td align="center">' + n + '</td><td align="center"><div  ><input style="width:80%" type="text" class="start"/></div></td><td align="center"><div><input style="width:80%" type="text" class="end"/></div></td><td align="center"><input style="width:80%" type="text" class="money"/></td></tr>');
  
                                }
                                else{
                             	   
                                    devClone2.find("#times").append('<tr class="data" height="30px"><td align="center"><input type="checkbox"/></td><td align="center">' + n + '</td><td align="center"><div  ><input style="width:80%" type="text" value="'+last+'" class="start"/></div></td><td align="center"><div><input style="width:80%" type="text" class="end"/></div></td><td align="center"><input style="width:80%" type="text" class="money"/></td></tr>');
                               
                                }
                                devClone2.find(".data:last").find(".start").on("blur",function() {
                                    var val = $(this).val();
                                    if(val!="")
                                    verifyTimeMinute2(devClone2,$(this),val);
                                }).on("change keyup",function(){$(this).prev().remove();});
                                devClone2.find(".data:last").find(".end").on("blur",function() {
                                    var val = $(this).val();
                                    if(val!="")
                                    verifyTimeMinute2(devClone2,$(this),val);
                                }).on("change keyup",function(){$(this).prev().remove();});
                                devClone2.find(".data:last").find(".money").on("blur",function() {
                                    var val = $(this).val();
                                    if(val!="")
                                    verifyMoney2(devClone2,$(this),val);
                                }).on("change keyup",function(){$(this).prev().remove();});
                                if (devClone2.find("[name='allcheck']").prop("checked")) {
                                    devClone2.find(":checkbox").prop("checked", true);
                                }

                                devClone2.find(":checkbox:not([name='allcheck'])").unbind('click').click(function() {
                                    if (!$(this).prop('checked')) {
                                        devClone2.find('[name="allcheck"]').prop("checked", false);
                                    } else {
                                        if (devClone2.find(":checkbox:checked").length == (devClone2.find(":checkbox").length - 1)) devClone2.find('[name="allcheck"]').prop("checked", true);
                                    }
                                });

                            });
                            function verifyTimeArr(){
                            	var len= $("tr.data").length;
                            	var startarr=[];
                            	var endarr=[];
                            	var err="";
                            	$("tr.data input").each(function(index,val){
                            		 if($(val).hasClass("start")){
                            			 startarr.push($(val).val());
                            		 }
                            		 if($(val).hasClass("end")){
                            			 endarr.push($(val).val());
                            		 }
                            	});
                            	for(var i=0;i<startarr.length;i++){
                            		if(i==0){
//                            			if(startarr[i]!=endarr[endarr.length-1]){
//                            				err+="请保证第一行的起始时段和最后一行的结束时段一致。\r\n"
//                            					
//                            			}
                            			
                            		}
                            		else{
                            		if(startarr[i]!=endarr[i-1]){
                        				err+=("第"+(i+1)+"行的起始时段和上一行的结束时段不一致，请保持连续。\r\n");
                        			}
                            		}
                            		if(Number(startarr[i])>=Number(endarr[i])){
                            			err+=("第"+(i+1)+"行结束时段必须大于起始时段.\r\n");
                            		}
//                            		if(Number(startarr[i])>=1440){
//                            			err+=("第"+(i+1)+"行起始时段必须小于1440.\r\n");
//                            		}
                            		if(Number(endarr[i])>1440){
                            			err+=("第"+(i+1)+"行结束时段必须小于等于1440.\r\n");
                            		}
                            	}
                            	//验证是否所有时段相加是一个24小时 24*60=1440
                            	var totalm=0;
                            	
                            	for(var i=0;i<startarr.length;i++){
                            		
                            	
//                            		var starth = startarr[i].split(":")[0];
//                            		var startm=startarr[i].split(":")[1];
                            		var starttm = Number(startarr[i]);
//                            		var endh = endarr[i].split(":")[0];
//                            		var endm=startarr[i].split(":")[1];
                            		var endtm =Number(endarr[i]);
                            	
                            		if(starttm==endtm){
                            			totalm+=1440;
                            		}
                            		if(starttm<endtm){
                            			totalm+=(endtm-starttm);
                            		}
                            		if(starttm>endtm){
                            			totalm+=(endtm+(1440-starttm));	
                            		}
                            	}
//                            	if(totalm>1440){
//                            		err+="您所输入的时段包含重复时段，请修改。\r\n";
//                            	}
                            	if(err!=""){
                            		alert(err);
                            		return false;
                            	}
                            	else{
                            		return true;
                            	}
                            }
                            devClone2.find("[name='allcheck']").click(function() {
                                if ($(this).prop("checked")) {
                                    devClone2.find(":checkbox").prop("checked", true);
                                } else {
                                    devClone2.find(":checkbox").prop("checked", false);
                                }
                            });

                            devClone2.find("#delete").on("click",
                            function() {
                                if (devClone2.find(":checkbox:checked").length == 0) {
                                    alert("请选择要删除的行");
                                }

                                devClone2.find(":checkbox:checked:not([name='allcheck'])").parent().parent().remove();

                            });

                            devClone2.modal({backdrop: 'static', keyboard: false}).on('hidden.bs.modal',
                            function(event) {
                                $(this).remove();
                            });
                            devClone2.find("#save").click(function() {
                               
                                var err=0;
                                num = 0;
                                devClone2.find("#times .data").each(function(index, val) {
                                    
                                	if(!verifyTimeMinute2(devClone2,$(val).find(".start"),$(val).find(".start").val())){
                                				err++;
                                				return false;
                            		}
                            		if(!verifyTimeMinute2(devClone2,$(val).find(".end"),$(val).find(".end").val())){
                            					err++;
                            					return false;
                        			}
                            		if(!verifyMoney2(devClone2,$(val).find(".money"),$(val).find(".money").val())){
                            					err++;
                            					return false;
                            		}
                                });
                               
                                if(!err){
                                	 if(!verifyTimeArr()){ 
                     					err++;
                     					return false;
                                	 }
                                	timearr = [];
                                devClone2.find("#times .data").each(function(index, val) {
                                    num++;
                                    timearr[index] = {};
                                    timearr[index].start = $(val).find(".start").val();
                                    timearr[index].end = $(val).find(".end").val();
                                    timearr[index].money = $(val).find(".money").val();
                                });

                                devClone2.modal('hide');
                                }
                            });

                            $.each(timearr,
                            function(index, val) {

                                devClone2.find("#times").append('<tr class="data" height="30px"><td align="center"><input type="checkbox"/></td><td align="center">' + (index + 1) + '</td><td align="center"><div  ><input style="width:80%" type="text" class="start"/></div></td><td align="center"><div><input style="width:80%" type="text" class="end"/></div></td><td align="center"><input style="width:80%" type="text" class="money"  /></td></tr>');
                                devClone2.find(".data:last").find(".start").val(val.start).on("blur",function() {
                                    var val = $(this).val();
                                    if(val!="")
                                    verifyTimeMinute2(devClone2,$(this),val);
                                }).on("change keyup",function(){$(this).prev().remove();});
                               
                                devClone2.find(".data:last").find(".end").val(val.end).on("blur",function() {
                                    var val = $(this).val();
                                    if(val!="")
                                    verifyTimeMinute2(devClone2,$(this),val);
                                }).on("change keyup",function(){$(this).prev().remove();});
                                devClone2.find(".data:last").find(".money").val(val.money).on("blur",function() {
                                    var val = $(this).val();
                                    if(val!="")
                                    verifyMoney2(devClone2,$(this),val);
                                }).on("change keyup",function(){$(this).prev().remove();});
                              
                            });

                            devClone2.find(":checkbox:not([name='allcheck'])").click(function() {
                                if (!$(this).prop('checked')) {
                                    devClone2.find('[name="allcheck"]').prop("checked", false);
                                } else {
                                    if (devClone2.find(":checkbox:checked").length == (devClone2.find(":checkbox").length - 1)) devClone2.find('[name="allcheck"]').prop("checked", true);
                                }
                            });

                        });
                        devClone.find("#dosubmit").unbind('click').on('click',
                        function(e) {
                            if (!verifyFenshiDuan(e)) {
                                return false;
                            }
                            var jsonuserinfo = devClone.find("#chargeForm").serializeObject();

                            var formdata = "condition=" + JSON.stringify(jsonuserinfo);
                            var times = "times=" + JSON.stringify(timearr);

                            $.ajax({ // 分时段收费
                                type: 'post',
                                url: "/pfm/chargerule/add",
                                data: formdata + "&" + times+"&ruleId="+(flag==1?sel_obj.ruleId:"")+reduceexceptionFormData(),
                                success: function(data) {

                                    var result = JSON.parse(data);

                                    if (result.success) {
                                        devClone.modal('hide');
                                        flag==0?wade.libs.alert('添加成功'):wade.libs.alert('修改成功');
                                        refresh();
                                    } else {
                                        wade.libs.alert(result.msg, 0, devClone.find('div:first'));
                                        devClone.find("#submit").removeAttr("disabled");
                                    }

                                }
                            });
                            e.stopImmediatePropagation(); // 阻止lib提交
                        });
                        if(flag==1){//修改数据
                        	$.ajax({
                        		url:'/pfm/chargerule/getchargerulebyid',
                        		type:'get',
                        		data:"ruleid="+sel_obj.ruleId,
                        		success:function(data){
                        			var result = JSON.parse(data);
                        			var ruleType = result.ruleType;
                        			if(ruleType==1){
                        				devClone.find("#chargeForm").find("#type").val(1);
                           				devClone.find("#chargeForm").find("[name=tempMatch]").val(result.rule.tempMatch);
                        				devClone.find("#chargeForm").find("[name=ruleName]").val(result.rule.ruleName);
                        				devClone.find("#chargeForm").find("#typesel").val(sel_obj.typeName);
                        				devClone.find("#chargeForm").find("[name=carType]").val(result.rule.carTypeId);
                        				devClone.find("#chargeForm").find("[name=freeTime]").val(result.period.freeTime);
                        				devClone.find("#chargeForm").find("[name=maxMoney]").val(result.period.parkOverMaxPeriodMoney);
                        				if(result.period.useTopMoney==0){
                        				
                        					devClone.find("#chargeForm").find("[name=useTopMoney]").prop("checked",true);
                        					devClone.find("#chargeForm").find('[name=topMoney]').val(result.period.topMoney)
                            				devClone.find("#chargeForm").find("#topmoneylabel").show();
                                    		devClone.find("#chargeForm").find("#topmoney").show();
                        				}
                        				
                        			
                        			}
                        		}
                        	});
                        	
                        }

                    }
                    break;
                case '2'://单位时段
                    {
                        devClone.find("#chargeForm").find("#bottom").empty().append(chargeruleHtml2);
                        devClone.find("#chargeForm").find("[name=tempMatch]").val(0);
                        devClone.find("#chargeForm").find("[name=tempMatch]").removeAttr('disabled');
                        devClone.find("#chargeForm").find(":hidden[name=tempMatch]").remove();

                        devClone.find("[href='#reducerule']").parent().show();
                        devClone.find("[href='#exceptionrule']").parent().show();
                        devClone.find("[href='#mobilereducerule']").parent().show();
                        /** **********验证***************** */
                        // 按单位时段时段
                        danweishiduanYanzheng=verifyDanweiShiDuan;
                        function verifyDanweiShiDuan(event) {
                            var val1 = devClone.find("#chargeForm").find('input[name=ruleName]').val();

                            if (!verifyRuleName('chargeForm','ruleNameNotice',val1)) {
                                event.stopImmediatePropagation(); // 
                                return false;
                            }
                            var val2 = devClone.find("#chargeForm").find('input[name=freeTime]').val();

                            if (!verifyTimeMinute('freeTimeNotice',val2)) {
                                event.stopImmediatePropagation(); // 
                                return false;
                            }
                            if(devClone.find("#chargeForm").find('[name=useTopMoney]').prop('checked'))
                            {
                            var val4 = devClone.find("#chargeForm").find('input[name=topMoney]').val();

                            if (!verifyMoney('topMoneyNotice',val4)) {
                                event.stopImmediatePropagation(); // 
                                return false;
                            }
//                            var max=0;
//                    		$.each(timearr,function(index,value){
//                				
//                				 max+=Number(value.topmoney);
//                				
//                			});
//                            if(max>Number(val4)){
//                            alert("封顶金额应大于按单位时段收费各时间段封顶金额总额!");
//                            event.stopImmediatePropagation(); // 
//                            return false;
//                            }
                            }
                            if(timearr.length==0){
                            	alert("请添加单位时间段")
                            	 event.stopImmediatePropagation(); // 
                                 return false;
                            }
                            return true;
                        }
                        devClone.find("#chargeForm").find('input[name=freeTime]').on("blur",
                        function() {
                            var val = $(this).val();
                            if(val!="")
                            verifyTimeMinute('freeTimeNotice',val);
                        }).on("change keyup",function(){devClone.find("#freeTimeNotice").hide();});
                        devClone.find("#chargeForm").find('input[name=topMoney]').on("blur",
                                function() {
                                    var val = $(this).val();
                                    if(val!="")
                                    verifyMoney('topMoneyNotice',val);

                                }).on("change keyup",function(){devClone.find("#topMoneyNotice").hide();});
                        devClone.find("#chargeForm").find("[name=useTopMoney]").on('click',function(){
                        	
                        	if($(this).prop("checked")){
                        		devClone.find("#chargeForm").find("#topmoneylabel").show();
                        		devClone.find("#chargeForm").find("#topmoney").show();
                        	}
                        	else{
                        		devClone.find("#chargeForm").find("#topmoneylabel").hide();
                        		devClone.find("#chargeForm").find("#topmoney").hide();
                        	}
                        });

                        /** ****************************** */
                        var timearr = [];
                        var num = 0;
                        if(flag==1){
                        	$.ajax({
                        		url:"/pfm/chargerule/getdanweishiduantimearr",
                        		type:"get",
                        		data:"ruleid="+sel_obj.ruleId,
                        		success:function(data){
                        			var result = JSON.parse(data);
                           			
                        			$.each(result,function(index,value){
                        				if(value!=null){
                        					timearr[index] = {};
                                            timearr[index].start = value.startTime.substring(11,16);
                                            timearr[index].end = value.endTime.substring(11,16);
                                            timearr[index].unit =value.unit;
                                            timearr[index].money = value.chargeMoney;
                                            timearr[index].topmoney = value.topMoney?value.topMoney:"";
                                            timearr[index].mintime = value.leastOverPeriodTime;
                        				}
                        			});
                        		}
                        	});
                        }
                        devClone.find("#chargeForm").find("#timebtn").on('click',
                        function() {

                            var devClone2 = $("#timeModal").clone(true);
                            devClone2.find('.modal-title').text("按单位时段收费配置");
                            devClone2.find('.modal-dialog').css("width", 800);
                            devClone2.find('.modal-body').append("<a id='add' class='btn bg-blue btn-sm'><span>新增时间段</span></a><a id='delete' class='btn bg-red btn-sm' style='margin-left:30px'><span>删除时间段</span></a><table id='times' style='margin-top:10px;width:100%;'><tr><td align='center' width='5%'><input name='allcheck' type='checkbox'/></td><td align='center'  width='5%'>序号</th><td align='center'  width='15%'>起始时间</td><td align='center' width='15%'>结束时间</td><td align='center' width='15%'>收费单位（分钟）</td><td align='center' width='15%'>收费金额（元）</td><td align='center' width='15%'>封顶金额（元）</td><td align='center'  width='15%'>最小跨段时间（分钟）</td></tr></table>");
                            devClone2.find('#add').on('click',
                            function() {
                                var n = devClone2.find(".data:last td").eq(1).text();
                                if (n == "") {
                                    n = 1;
                                } else {
                                    n++;
                                }

                                var last=  devClone2.find("#times").find(".data:last .timepicker.end").val();
                              
                                if(last===undefined){
                                    devClone2.find("#times").append('<tr class="data" height="30px"><td align="center"><input type="checkbox"/></td><td align="center">' + n + '</td><td align="center"><div  class="bootstrap-timepicker"><input style="width:80%" type="text" class="timepicker start" onClick="WdatePicker({dateFmt:\'HH:mm\',onpicked:function(){$(this).prev().remove();}})"/></div></td><td align="center"><div  class="bootstrap-timepicker"><input style="width:80%" type="text"  class="timepicker end" onClick="WdatePicker({dateFmt:\'HH:mm\',onpicked:function(){$(this).prev().remove();}})"/></div></td><td align="center"><input style="width:80%" type="text" class="unit" /></td><td align="center"><input style="width:80%" type="text" class="money"/></td><td align="center"><input style="width:80%" type="text" class="topmoney"/></td><td align="center"><input style="width:80%" type="text" class="mintime" /></td></tr>');
  
                                }
                                else{
                             	   
                                    devClone2.find("#times").append('<tr class="data" height="30px"><td align="center"><input type="checkbox"/></td><td align="center">' + n + '</td><td align="center"><div  class="bootstrap-timepicker"><input style="width:80%" type="text" value="'+last+'" class="timepicker start" onClick="WdatePicker({dateFmt:\'HH:mm\',onpicked:function(){$(this).prev().remove();}})"/></div></td><td align="center"><div  class="bootstrap-timepicker"><input style="width:80%" type="text"  class="timepicker end" onClick="WdatePicker({dateFmt:\'HH:mm\',onpicked:function(){$(this).prev().remove();}})"/></div></td><td align="center"><input style="width:80%" type="text" class="unit" /></td><td align="center"><input style="width:80%" type="text" class="money"/></td><td align="center"><input style="width:80%" type="text" class="topmoney"/></td><td align="center"><input style="width:80%" type="text" class="mintime" /></td></tr>');
                               
                                }
                                devClone2.find(".data:last").find(".unit").on("blur",function() {
                                    var val = $(this).val();
                                    if(val!="")
                                    verifyTimeMinute2(devClone2,$(this),val);
                                }).on("change keyup",function(){$(this).prev().remove();});
                                devClone2.find(".data:last").find(".mintime").on("blur",function() {
                                    var val = $(this).val();
                                    if(val!="")
                                    verifyTimeMinute2(devClone2,$(this),val);
                                }).on("change keyup",function(){$(this).prev().remove();});
                                devClone2.find(".data:last").find(".money").on("blur",function() {
                                    var val = $(this).val();
                                    if(val!="")
                                    verifyMoney2(devClone2,$(this),val);
                                }).on("change keyup",function(){$(this).prev().remove();});
                                devClone2.find(".data:last").find(".topmoney").on("blur",function() {
                                    var val = $(this).val();
                                    if($.trim(val)!=""){
                                    verifyMoney2(devClone2,$(this),val);
                                    }
                                }).on("change keyup",function(){$(this).prev().remove();});
   
                                devClone2.find(".data:last").find(".timepicker").on("blur",
                                		function() {
                                	var val = $(this).val();
                                	if(val!="")
                                	verifyMinute(devClone2,$(this),val)

                                }).on("change keyup",function(){$(this).prev().remove();});
                                if (devClone2.find("[name='allcheck']").prop("checked")) {
                                    devClone2.find(":checkbox").prop("checked", true);
                                }

                                devClone2.find(":checkbox:not([name='allcheck'])").unbind('click').click(function() {
                                    if (!$(this).prop('checked')) {
                                        devClone2.find('[name="allcheck"]').prop("checked", false);
                                    } else {
                                        if (devClone2.find(":checkbox:checked").length == (devClone2.find(":checkbox").length - 1)) devClone2.find('[name="allcheck"]').prop("checked", true);
                                    }
                                });

                            });
                            devClone2.find("[name='allcheck']").click(function() {
                                if ($(this).prop("checked")) {
                                    devClone2.find(":checkbox").prop("checked", true);
                                } else {
                                    devClone2.find(":checkbox").prop("checked", false);
                                }
                            });

                            devClone2.find("#delete").on("click",
                            function() {
                                if (devClone2.find(":checkbox:checked").length == 0) {
                                    alert("请选择要删除的行");
                                }

                                devClone2.find(":checkbox:checked:not([name='allcheck'])").parent().parent().remove();

                            });

                            devClone2.modal({backdrop: 'static', keyboard: false}).on('hidden.bs.modal',
                            function(event) {
                                $(this).remove();
                            });
                            function verifyTimeArr(){
                            	var len= $("tr.data").length;
                            	var startarr=[];
                            	var endarr=[];
                            	var err="";
                            	devClone2.find(".timepicker").each(function(index,val){
                            		 if($(val).hasClass("start")){
                            			 startarr.push($(val).val());
                            		 }
                            		 else{
                            			 endarr.push($(val).val());
                            		 }
                            	});
                            	for(var i=0;i<startarr.length;i++){
                            		if(i==0){
                            			if(startarr[i]!=endarr[endarr.length-1]){
                            				err+="请保证第一行的起始时间和最后一行的结束时间一致。\r\n"
                            					
                            			}
                            			continue;
                            		}
                            		if(startarr[i]!=endarr[i-1]){
                        				err+=("第"+(i+1)+"行的起始时间和上一行的结束时间不一致，请保持连续。\r\n");
                        			}
                            	}
                            	//验证是否所有时段相加是一个24小时 24*60=1440
                            	var totalm=0;
                            	
                            	for(var i=0;i<startarr.length;i++){
                            		
                            	
                            		var starth = startarr[i].split(":")[0];
                            		var startm=startarr[i].split(":")[1];
                            		var starttm = Number(starth)*60+Number(startm);
                            		var endh = endarr[i].split(":")[0];
                            		var endm=endarr[i].split(":")[1];
                            		var endtm = Number(endh)*60+Number(endm);
                            		
                            		if(starttm==endtm){
                            			totalm+=1440;
                            		}
                            		if(starttm<endtm){
                            			totalm+=(endtm-starttm);
                            		}
                            		if(starttm>endtm){
                            			totalm+=(endtm+(1440-starttm));	
                            		}
                            	}
                            	
                            	if(totalm>1440){
                            		err+="您所输入的时间段包含重复时间，请修改。\r\n";
                            	}
                            	
                            	if(err!=""){
                            		alert(err);
                            		return false;
                            	}
                            	else{
                            		return true;
                            	}
                            }
                            devClone2.find("#save").click(function() {
                                
                                var err=0;
                                num = 0;
                                	devClone2.find("#times .data").each(function(index, val) {
                                	if(!verifyMinute(devClone2,$(val).find(".start"),$(val).find(".start").val())){
                                		err++;
                        				return false;
                                	}
                                	if(!verifyMinute(devClone2,$(val).find(".end"),$(val).find(".end").val())){
                                		err++;
                        				return false;
                                	}
                                    
                                	if(!verifyTimeMinute2(devClone2,$(val).find(".unit"),$(val).find(".unit").val())){
                                		err++;
                                		return false;
                            		}
                            
                                	if(!verifyMoney2(devClone2,$(val).find(".money"),$(val).find(".money").val())){
                    					err++;
                    					return false;
                                	}
                                	if($.trim($(val).find(".topmoney").val())!=""){
                                	if(!verifyMoney2(devClone2,$(val).find(".topmoney"),$(val).find(".topmoney").val())){
                  							err++;
            							return false;
                                	}
                                	if($(val).find(".topmoney").val()=='0'||$(val).find(".topmoney").val()=='0.0'||$(val).find(".topmoney").val()=='0.00'){
                                		err++;
                                		devClone2.find('.point-out').hide();
                                    	var id= new Date().getTime();
                                    	if($(val).find(".topmoney").parent().css("position")!='relative'){
                                    		$(val).find(".topmoney").wrap('<div style="position:relative"></div>');
                                    	}
                                    	$(val).find(".topmoney").prev().remove();
                                    	$(val).find(".topmoney").before('<div id="'+id+'" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div>');     
                                    	devClone2.find("#"+id).html('<span class="point-box errorbgTop dropdown" ><i class="sprite icon_arrow_up2 pa"></i>'+"金额不能为0"+'</span>').show();
            							return false;
                                	}
                                	}
                            		if(!verifyTimeMinute2(devClone2,$(val).find(".mintime"),$(val).find(".mintime").val())){
                            					err++;
                            					return false;
                        			}
                            		
                            	
                                });
                                if(!err){
                                	if(!verifyTimeArr()){ 
                     					err++;
                     					return false;
                                	 }
                                	devClone2.find(".unit").each(function(index,val){
                                		if(Number($(this).val())==0){
                                			alert("第"+(index+1)+"行收费单位不能为0");
                                			err++;
                                			return false;
                                		}
                                	});
                                	if(err){
                                		return false;
                                	}
                                	devClone2.find("tr.data").each(function(index,val){
                                		if($(val).find(".unit").val()<$(val).find(".mintime").val()){
                                			alert("第"+(index+1)+"行最小跨段时间不能大于收费单位");
                                			err++;
                                			return false;
                                		}
                                	});
                                	if(err){
                                		return false;
                                	}
                                	timearr = [];
                                	devClone2.find("#times .data").each(function(index, val) {
                                	num++;
                                    timearr[index] = {};
                                    timearr[index].start = $(val).find(".start").val();
                                    timearr[index].end = $(val).find(".end").val();
                                    timearr[index].unit = $(val).find(".unit").val();
                                    timearr[index].money = $(val).find(".money").val();
                                    timearr[index].topmoney = $(val).find(".topmoney").val();
                                    timearr[index].mintime = $(val).find(".mintime").val();
                                });

                                devClone2.modal('hide');
                                	}
                            });

                            $.each(timearr,
                            function(index, val) {

                                devClone2.find("#times").append('<tr class="data" height="30px"><td align="center"><input type="checkbox"/></td><td align="center">' + (index + 1) + '</td><td align="center"><div  class="bootstrap-timepicker"><input style="width:80%" type="text" class="timepicker start" onClick="WdatePicker({dateFmt:\'HH:mm\',onpicked:function(){$(this).prev().remove();}})"/></div></td><td align="center"><div  class="bootstrap-timepicker"><input style="width:80%" type="text" class="timepicker end" onClick="WdatePicker({dateFmt:\'HH:mm\',onpicked:function(){$(this).prev().remove();}})"/></div></td><td align="center"><input style="width:80%" type="text" class="unit"/></td><td align="center"><input style="width:80%" type="text" class="money"/></td><td align="center"><input style="width:80%" type="text" class="topmoney" /></td><td align="center"><input style="width:80%" type="text" class="mintime"  /></td></tr>');
                                devClone2.find(".data:last").find(".start").val(val.start);
                                devClone2.find(".data:last").find(".end").val(val.end);
                                devClone2.find(".data:last").find(".money").val(val.money).on("blur",function() {
                                    var val = $(this).val();
                                    if(val!="")
                                    verifyMoney2(devClone2,$(this),val);
                                }).on("change keyup",function(){$(this).prev().remove();});
                                devClone2.find(".data:last").find(".topmoney").val(val.topmoney).on("blur",function() {
                                    var val = $(this).val();
                                    if($.trim(val)!=""){
                                    verifyMoney2(devClone2,$(this),val);
                                	if(val=='0'||val=='0.0'||val=='0.00'){
                                		devClone2.find('.point-out').hide();
                                    	var id= new Date().getTime();
                                    	if($(this).parent().css("position")!='relative'){
                                    		$(this).wrap('<div style="position:relative"></div>');
                                    	}
                                    	$(this).prev().remove();
                                    	$(this).before('<div id="'+id+'" class="point-out"style="width: 250px; top: -38px; left: 0px; display: none;"></div>');     
                                    	devClone2.find("#"+id).html('<span class="point-box errorbgTop dropdown" ><i class="sprite icon_arrow_up2 pa"></i>'+"金额不能为0"+'</span>').show();

                                	}
                                    }
                                }).on("change keyup",function(){$(this).prev().remove();});
                                devClone2.find(".data:last").find(".mintime").val(val.mintime).on("blur",function() {
                                    var val = $(this).val();
                                    if(val!="")
                                    verifyTimeMinute2(devClone2,$(this),val);
                                }).on("change keyup",function(){$(this).prev().remove();});
                                devClone2.find(".data:last").find(".unit").val(val.unit).on("blur",function() {
                                    var val = $(this).val();
                                    if(val!="")
                                    verifyTimeMinute2(devClone2,$(this),val);
                                    
                                }).on("change keyup",function(){$(this).prev().remove();});
                                devClone2.find(".data:last").find(".timepicker").on("blur",
                                		function() {
                                	var val = $(this).val();
                                	if(val!="")
                                	verifyMinute(devClone2,$(this),val)

                                }).on("change keyup",function(){$(this).prev().remove();});
                            });

                            devClone2.find(":checkbox:not([name='allcheck'])").click(function() {
                                if (!$(this).prop('checked')) {
                                    devClone2.find('[name="allcheck"]').prop("checked", false);
                                } else {
                                    if (devClone2.find(":checkbox:checked").length == (devClone2.find(":checkbox").length - 1)) devClone2.find('[name="allcheck"]').prop("checked", true);
                                }
                            });

                        });
                        devClone.find("#dosubmit").unbind('click').on('click',
                        function(e) {
                            if (!verifyDanweiShiDuan(e)) {
                                return false;
                            }
                            var jsonuserinfo = devClone.find("#chargeForm").serializeObject();

                            var formdata = "condition=" + JSON.stringify(jsonuserinfo);
                            var times = "times=" + JSON.stringify(timearr);

                            $.ajax({ // 按单位时段收费
                                type: 'post',
                                url: "/pfm/chargerule/add",
                                data: formdata + "&" + times+"&ruleId="+(flag==1?sel_obj.ruleId:"")+reduceexceptionFormData(),
                                success: function(data) {

                                    var result = JSON.parse(data);

                                    if (result.success) {
                                        devClone.modal('hide');
                                        flag==0?wade.libs.alert('添加成功'):wade.libs.alert('修改成功');
                                        refresh();
                                    } else {
                                        wade.libs.alert(result.msg, 0, devClone.find('div:first'));
                                        devClone.find("#submit").removeAttr("disabled");
                                    }

                                }
                            });
                            e.stopImmediatePropagation(); // 阻止lib提交
                        });
                        if(flag==1){//修改数据
                        	$.ajax({
                        		url:'/pfm/chargerule/getchargerulebyid',
                        		type:'get',
                        		data:"ruleid="+sel_obj.ruleId,
                        		success:function(data){
                        			var result = JSON.parse(data);
                        			var ruleType = result.ruleType;
                        			if(ruleType==2){
                        				devClone.find("#chargeForm").find("#type").val(2);
                           				devClone.find("#chargeForm").find("[name=tempMatch]").val(result.rule.tempMatch);
                        				devClone.find("#chargeForm").find("[name=ruleName]").val(result.rule.ruleName);
                        				devClone.find("#chargeForm").find("#typesel").val(sel_obj.typeName);
                        				devClone.find("#chargeForm").find("[name=carType]").val(result.rule.carTypeId);
                        				devClone.find("#chargeForm").find("[name=freeTime]").val(result.unitperiod.freeTime);
                        				devClone.find("#chargeForm").find("[name=includeFreeTime]").val(result.unitperiod.includeFreeTime);
                        				if(result.unitperiod.useTopMoney==0){
                        					devClone.find("#chargeForm").find("[name=useTopMoney]").prop("checked",true);
                        					devClone.find("#chargeForm").find('[name=topMoney]').val(result.unitperiod.topMoney)
                            				devClone.find("#chargeForm").find("#topmoneylabel").show();
                                    		devClone.find("#chargeForm").find("#topmoney").show();
                        				}
                        			}
                        		}
                        	});
                        	
                        }

                    }
                    break;
                case '3'://按次
                    {
                		devClone.find("#chargeForm").find("#bottom").empty().append(chargeruleHtml3);
                		devClone.find("#chargeForm").find("[name=tempMatch]").val(0);
                		  devClone.find("#chargeForm").find("[name=tempMatch]").removeAttr('disabled');
                          devClone.find("#chargeForm").find(":hidden[name=tempMatch]").remove();

                		 devClone.find("[href='#reducerule']").parent().show();
                		 devClone.find("[href='#mobilereducerule']").parent().show();
                         devClone.find("[href='#exceptionrule']").parent().show();
                       anciYanzheng = verifyAnci;
                        function verifyAnci(event) {
                            var val1 = devClone.find("#chargeForm").find('input[name=ruleName]').val();
                            if (!verifyRuleName('chargeForm','ruleNameNotice',val1)) {
                                event.stopImmediatePropagation(); // 
                                return false;
                            }
                            var val2 = devClone.find("#chargeForm").find('input[name=danjia]').val();
                            if (!verifyMoney('payMoneyNotice',val2)) {
                                event.stopImmediatePropagation(); // 
                                return false;
                            }
                            return true;

                        }
                        devClone.find("#chargeForm").find('input[name=danjia]').on("blur",
                        function() {
                            var val = $(this).val();
                            if(val!="")
                            verifyMoney('payMoneyNotice',val);
                        }).on("change keyup",function(){devClone.find("#payMoneyNotice").hide();});
                        devClone.find("#dosubmit").unbind('click').on('click',
                        function(e) {
                            if (!verifyAnci(e)) {
                                return false;
                            }
                            var jsonuserinfo = devClone.find("#chargeForm").serializeObject();

                            var formdata = "condition=" + JSON.stringify(jsonuserinfo);
                            $.ajax({ // 按次
                                type: 'post',
                                url: "/pfm/chargerule/add",
                                data: formdata+"&ruleId="+(flag==1?sel_obj.ruleId:"")+reduceexceptionFormData(),
                                success: function(data) {

                                    var result = JSON.parse(data);

                                    if (result.success) {
                                        devClone.modal('hide');
                                        flag==0?wade.libs.alert('添加成功'):wade.libs.alert('修改成功');
                                        refresh();
                                    } else {
                                        wade.libs.alert(result.msg, 0, devClone.find('div:first'));
                                        devClone.find("#submit").removeAttr("disabled");
                                    }

                                }
                            });
                            e.stopImmediatePropagation(); // 阻止lib提交
                        });
                        if(flag==1){//修改数据
                        	$.ajax({
                        		url:'/pfm/chargerule/getchargerulebyid',
                        		type:'get',
                        		data:"ruleid="+sel_obj.ruleId,
                        		success:function(data){
                        			var result = JSON.parse(data);
                        			var ruleType = result.ruleType;
                        			if(ruleType==3){
                        				devClone.find("#chargeForm").find("#type").val(3);
                           				devClone.find("#chargeForm").find("[name=tempMatch]").val(result.rule.tempMatch);
                        				devClone.find("#chargeForm").find("[name=ruleName]").val(result.rule.ruleName);
                        				devClone.find("#chargeForm").find("#typesel").val(sel_obj.typeName);
                        				devClone.find("#chargeForm").find("[name=carType]").val(result.rule.carTypeId);
                        				devClone.find("#chargeForm").find("[name=danjia]").val(result.pertime.chargeMoney);
                        			}
                        		}
                        	});
                        	
                        }
                    }
                    break;
                case '4': //按时
                    {
                        devClone.find("#chargeForm").find("#bottom").empty().append(chargeruleHtml4);
                        devClone.find("#chargeForm").find("[name=tempMatch]").val(0);
                        devClone.find("#chargeForm").find("[name=tempMatch]").removeAttr('disabled');
                        devClone.find("#chargeForm").find(":hidden[name=tempMatch]").remove();

                        devClone.find("[href='#reducerule']").parent().show();
                        devClone.find("[href='#exceptionrule']").parent().show();
                        devClone.find("[href='#mobilereducerule']").parent().show();
                        anshiYanzheng=verifyAnshi;
                        function verifyAnshi(event) {
                            var val1 = devClone.find("#chargeForm").find('input[name=ruleName]').val();
                            if (!verifyRuleName('chargeForm','ruleNameNotice',val1)) {
                                event.stopImmediatePropagation(); // 
                                return false;
                            }
                            var val2 = devClone.find("#chargeForm").find('input[name=freeTime]').val();
                            if (!verifyTimeMinute('freeTimeNotice',val2)) {
                                event.stopImmediatePropagation(); // 
                                return false;
                            }
                            var val3 = devClone.find("#chargeForm").find('input[name=firstTime]').val();
                            if (!verifyTimeMinute('firstTimeNotice',val3)) {
                                event.stopImmediatePropagation(); // 
                                return false;
                            }
                            var val4 = devClone.find("#chargeForm").find('input[name=firstMoney]').val();
                            if (!verifyMoney('firstMoneyNotice',val4)) {
                                event.stopImmediatePropagation(); // 
                                return false;
                            }
                            var val5 = devClone.find("#chargeForm").find('input[name=intervalTime]').val();
                            if (!verifyTimeMinute('intervalTimeNotice',val5)) {
                                event.stopImmediatePropagation(); // 
                                return false;
                            }
                            var val6 = devClone.find("#chargeForm").find('input[name=intervalMoney]').val();
                            if (!verifyMoney('intervalMoneyNotice',val6)) {
                                event.stopImmediatePropagation(); // 
                                return false;
                            }
                            if(devClone.find("#chargeForm").find('#useTop1').prop('checked')||devClone.find("#chargeForm").find('#useTop2').prop('checked'))
                            {
                            var val7= devClone.find("#chargeForm").find('input[name=topMoney]').val();

                            if (!verifyMoney('topMoneyNotice',val7)) {
                                event.stopImmediatePropagation(); // 
                                return false;
                            }
                            }
                            return true;

                        }
                        devClone.find("#chargeForm").find('input[name=freeTime]').on("blur",
                                function() {
                                    var val = $(this).val();
                                    if(val!="")
                                    verifyTimeMinute('freeTimeNotice',val)
                                }).on("change keyup",function(){devClone.find("#freeTimeNotice").hide();});
                        devClone.find("#chargeForm").find('input[name=firstMoney]').on("blur",
                                function() {
                                    var val = $(this).val();
                                    if(val!="")
                                    verifyMoney('firstMoneyNotice',val)
                                }).on("change keyup",function(){devClone.find("#firstMoneyNotice").hide();});
                        devClone.find("#chargeForm").find('input[name=firstTime]').on("blur",
                                function() {
                                    var val = $(this).val();
                                    if(val!="")
                                    verifyTimeMinute('firstTimeNotice',val)
                                }).on("change keyup",function(){devClone.find("#firstTimeNotice").hide();});
                        devClone.find("#chargeForm").find('input[name=intervalTime]').on("blur",
                                function() {
                                    var val = $(this).val();
                                    if(val!="")
                                    verifyTimeMinute('intervalTimeNotice',val)
                                }).on("change keyup",function(){devClone.find("#intervalTimeNotice").hide();});
                        devClone.find("#chargeForm").find('input[name=intervalMoney]').on("blur",
                                function() {
                                    var val = $(this).val();
                                    if(val!="")
                                    verifyMoney('intervalMoneyNotice',val)
                                }).on("change keyup",function(){devClone.find("#intervalMoneyNotice").hide();});
                        devClone.find("#chargeForm").find('input[name=topMoney]').on("blur",
                                function() {
                                    var val = $(this).val();
                                    if(val!="")
                                    verifyMoney('topMoneyNotice',val);

                                }).on("change keyup",function(){devClone.find("#topMoneyNotice").hide();});
                        devClone.find("#chargeForm").find("[name=useTopMoney]").on('click',function(){
                        	
                        	if($(this).prop("checked")){
                        		devClone.find("#chargeForm").find("#topmoneylabel").show();
                        		devClone.find("#chargeForm").find("#topmoney").show();
                        	}
                        	else{
                        		devClone.find("#chargeForm").find("#topmoneylabel").hide();
                        		devClone.find("#chargeForm").find("#topmoney").hide();
                        	}
                        });
                        devClone.find("#chargeForm").find("#useTop1").on('click',function(){
                        	
                        	if($(this).prop("checked")){
                        		devClone.find("#chargeForm").find("#topmoneylabel").show();
                        		devClone.find("#chargeForm").find("#topmoney").show();
                        		devClone.find("#chargeForm").find("#useTop2").prop('checked',false);
                        	}
                        	else{
                        		devClone.find("#chargeForm").find("#topmoneylabel").hide();
                        		devClone.find("#chargeForm").find("#topmoney").hide();
                        	}
                        });
                        devClone.find("#chargeForm").find("#useTop2").on('click',function(){
                        	
                        	if($(this).prop("checked")){
                        		devClone.find("#chargeForm").find("#topmoneylabel").show();
                        		devClone.find("#chargeForm").find("#topmoney").show();
                        		devClone.find("#chargeForm").find("#useTop1").prop('checked',false);
                        	}
                        	else{
                        		devClone.find("#chargeForm").find("#topmoneylabel").hide();
                        		devClone.find("#chargeForm").find("#topmoney").hide();
                        	}
                        });
                        devClone.find("#dosubmit").unbind('click').on('click',
                        function(e) {
                          
    								 if(!verifyAnshi(e)) {
    									 return false; }
    																	 
                            var jsonuserinfo = devClone.find("#chargeForm").serializeObject();

                            var formdata = "condition=" + JSON.stringify(jsonuserinfo);
                            $.ajax({ // 按时
                                type: 'post',
                                url: "/pfm/chargerule/add",
                                data: formdata+"&ruleId="+(flag==1?sel_obj.ruleId:"")+reduceexceptionFormData(),
                                success: function(data) {

                                    var result = JSON.parse(data);

                                    if (result.success) {
                                        devClone.modal('hide');
                                        flag==0?wade.libs.alert('添加成功'):wade.libs.alert('修改成功');
                                        refresh();
                                    } else {
                                        wade.libs.alert(result.msg, 0, devClone.find('div:first'));
                                        devClone.find("#submit").removeAttr("disabled");
                                    }

                                }
                            });
                            e.stopImmediatePropagation(); // 阻止lib提交
                        });
                        if(flag==1){//修改数据
                        	$.ajax({
                        		url:'/pfm/chargerule/getchargerulebyid',
                        		type:'get',
                        		data:"ruleid="+sel_obj.ruleId,
                        		success:function(data){
                        			var result = JSON.parse(data);
                        			var ruleType = result.ruleType;
                        			if(ruleType==4){
                        				devClone.find("#chargeForm").find("#type").val(4);
                           				devClone.find("#chargeForm").find("[name=tempMatch]").val(result.rule.tempMatch);
                        				devClone.find("#chargeForm").find("[name=ruleName]").val(result.rule.ruleName);
                        				devClone.find("#chargeForm").find("#typesel").val(sel_obj.typeName);
                        				devClone.find("#chargeForm").find("[name=carType]").val(result.rule.carTypeId);
                        				devClone.find("#chargeForm").find("[name=freeTime]").val(result.anshi.freeTime);
                        				devClone.find("#chargeForm").find("[name=firstTime]").val(result.anshi.firstPartChargeTime);
                        				devClone.find("#chargeForm").find("[name=firstMoney]").val(result.anshi.firstPartChargeMoney);
                        				devClone.find("#chargeForm").find("[name=intervalTime]").val(result.anshi.separateChargeTime);
                        				devClone.find("#chargeForm").find("[name=intervalMoney]").val(result.anshi.separateChargeMoney);
                        		
                        				if(result.anshi.useTopMoney==1){
                        					devClone.find("#chargeForm").find("#useTop1").prop("checked",true);
                        					devClone.find("#chargeForm").find('[name=topMoney]').val(result.anshi.topMoney)
                            				devClone.find("#chargeForm").find("#topmoneylabel").show();
                                    		devClone.find("#chargeForm").find("#topmoney").show();
                        				}
                           				if(result.anshi.useTopMoney==2){
                        					devClone.find("#chargeForm").find("#useTop2").prop("checked",true);
                        					devClone.find("#chargeForm").find('[name=topMoney]').val(result.anshi.topMoney)
                            				devClone.find("#chargeForm").find("#topmoneylabel").show();
                                    		devClone.find("#chargeForm").find("#topmoney").show();
                        				}
                        			}
                        		}
                        	});
                        }

                    }
                    break;
                case '5':
                    { // 包期
                        devClone.find("#chargeForm").find("#bottom").empty().append(chargeruleHtml5);
                        devClone.find("[href='#reducerule']").parent().hide();
                        devClone.find("[href='#exceptionrule']").parent().hide();
                        
                        devClone.find("[href='#mobilereducerule']").parent().hide();
                        //包期收费临时匹配默认为否且不可配置
                        devClone.find("#chargeForm").find("[name=tempMatch]").val(1);
                        devClone.find("#chargeForm").find("[name=tempMatch]").attr('disabled','disabled');
                        devClone.find("#chargeForm").find("[name=tempMatch]").after("<input type='hidden' name='tempMatch' value='1' />");
                        baoqiYanzheng = verifyBaoqi;
                        function verifyBaoqi(event){
                        	 var val1 = devClone.find("#chargeForm").find('input[name=ruleName]').val();
                             if (!verifyRuleName('chargeForm','ruleNameNotice',val1)) {
                                 event.stopImmediatePropagation(); // 
                                 return false;
                             }
                             var val2 = devClone.find("#chargeForm").find('input[name=money]').val();
                             if (!verifyMoney('moneyNotice',val2)) {
                                 event.stopImmediatePropagation(); // 
                                 return false;
                             }
                             return true;
                        }
                        devClone.find("#chargeForm").find("input[name=money]").on('blur',function(){
                        	var val = $(this).val();
                        	if(val!="")
                        	verifyMoney('moneyNotice',val);
                        }).on("change keyup",function(){devClone.find("#moneyNotice").hide();});
                        devClone.find("#dosubmit").unbind('click').on('click',
                        function(e) {
                            
    						 if(!verifyBaoqi(e)) {
    							 return false; 
    							 }     
    						 var jsonuserinfo = devClone.find("#chargeForm").serializeObject();

                            var formdata = "condition=" + JSON.stringify(jsonuserinfo);
                            $.ajax({ // 按单位时段收费
                                type: 'post',
                                url: "/pfm/chargerule/add",
                                data: formdata+"&ruleId="+(flag==1?sel_obj.ruleId:"")+reduceexceptionFormData(),
                                success: function(data) {

                                    var result = JSON.parse(data);

                                    if (result.success) {
                                        devClone.modal('hide');
                                        refresh();
                                        flag==0?wade.libs.alert('添加成功'):wade.libs.alert('修改成功');
                                    } else {
                                        wade.libs.alert(result.msg, 0, devClone.find('div:first'));
                                        devClone.find("#submit").removeAttr("disabled");
                                    }

                                }
                            });
                            e.stopImmediatePropagation(); // 阻止lib提交
                        });
                        if(flag==1){//修改数据
                        	
                        	$.ajax({
                        		url:'/pfm/chargerule/getchargerulebyid',
                        		type:'get',
                        		data:"ruleid="+sel_obj.ruleId,
                        		success:function(data){
                        			var result = JSON.parse(data);
                        			var ruleType = result.ruleType;
                        			if(ruleType==5){
                        				devClone.find("#chargeForm").find("#type").val(5);
                           				devClone.find("#chargeForm").find("[name=tempMatch]").val(result.rule.tempMatch);
                        				devClone.find("#chargeForm").find("[name=ruleName]").val(result.rule.ruleName);
                        				devClone.find("#chargeForm").find("#typesel").val(sel_obj.typeName);
                        				devClone.find("#chargeForm").find("[name=carType]").val(result.rule.carTypeId);
                        				devClone.find("#chargeForm").find("[name=type]").val(result.baoqi.type);
                        				devClone.find("#chargeForm").find("[name=money]").val(result.baoqi.money);
                        			}
                        		}
                        	});
                        	
                        }
                    }
                    break;
                case '6'://不收费
                    {
                        devClone.find("#chargeForm").find("#bottom").empty().append(chargeruleHtml6);
                        devClone.find("#chargeForm").find("[name=tempMatch]").val(0);
                        devClone.find("#chargeForm").find("[name=tempMatch]").removeAttr('disabled');
                        devClone.find("#chargeForm").find(":hidden[name=tempMatch]").remove();

                        devClone.find("[href='#reducerule']").parent().hide();
                        devClone.find("[href='#exceptionrule']").parent().hide();
                        devClone.find("[href='#mobilereducerule']").parent().hide();

                        noYanzheng = verifyNo;
                        function verifyNo(event){
                        	 var val1 = devClone.find("#chargeForm").find('input[name=ruleName]').val();
                             if (!verifyRuleName('chargeForm','ruleNameNotice',val1)) {
                                 event.stopImmediatePropagation(); // 
                                 return false;
                             }
                             return true;
                        }
                        devClone.find("#dosubmit").unbind('click').on('click',
                        function(e) {
                        	if(!verifyNo(e)) {
    							return false; 
    							}										
                            var jsonuserinfo = devClone.find("#chargeForm").serializeObject();
                            var formdata = "condition=" + JSON.stringify(jsonuserinfo);
                            $.ajax({ // 按单位时段收费
                                type: 'post',
                                url: "/pfm/chargerule/add",
                                data: formdata+"&ruleId="+(flag==1?sel_obj.ruleId:"")+reduceexceptionFormData(),
                                success: function(data) {

                                    var result = JSON.parse(data);

                                    if (result.success) {
                                        devClone.modal('hide');
                                        refresh();
                                        flag==0?wade.libs.alert('添加成功'):wade.libs.alert('修改成功');
                                    } else {
                                        wade.libs.alert(result.msg, 0, devClone.find('div:first'));
                                        devClone.find("#submit").removeAttr("disabled");
                                    }

                                }
                            });
                            e.stopImmediatePropagation(); // 阻止lib提交
                        });
                        if(flag==1){//修改数据
                        	$.ajax({
                        		url:'/pfm/chargerule/getchargerulebyid',
                        		type:'get',
                        		data:"ruleid="+sel_obj.ruleId,
                        		success:function(data){
                        			var result = JSON.parse(data);
                        			var ruleType = result.ruleType;
                        			if(ruleType==6){
                        				devClone.find("#chargeForm").find("#type").val(6);
                           				devClone.find("#chargeForm").find("[name=tempMatch]").val(result.rule.tempMatch);
                        				devClone.find("#chargeForm").find("[name=ruleName]").val(result.rule.ruleName);
                        				devClone.find("#chargeForm").find("#typesel").val(sel_obj.typeName);
                        				devClone.find("#chargeForm").find("[name=carType]").val(result.rule.carTypeId);
                           			}
                        		}
                        	});
                        	
                        }

                    }
                    break;
                case '8'://派车
                {
                    devClone.find("#chargeForm").find("#bottom").empty().append(chargeruleHtml8);
                    devClone.find("#chargeForm").find("[name=tempMatch]").val(0);
                    devClone.find("#chargeForm").find("[name=tempMatch]").removeAttr('disabled');
                    devClone.find("#chargeForm").find(":hidden[name=tempMatch]").remove();

                    devClone.find("[href='#reducerule']").parent().show();
                    devClone.find("[href='#exceptionrule']").parent().show();
                    devClone.find("[href='#mobilereducerule']").parent().show();

                    noYanzheng=verifyNo;
                    function verifyNo(event){
                    	 var val1 = devClone.find("#chargeForm").find('input[name=ruleName]').val();
                         if (!verifyRuleName('chargeForm','ruleNameNotice',val1)) {
                             event.stopImmediatePropagation(); // 
                             return false;
                         }
                         return true;
                    }
                    devClone.find("#dosubmit").unbind('click').on('click',
                    function(e) {
                    	if(!verifyNo(e)) {
    						return false; 
    						}										
                        var jsonuserinfo = devClone.find("#chargeForm").serializeObject();
                        var formdata = "condition=" + JSON.stringify(jsonuserinfo);
                        $.ajax({ // 按派车
                            type: 'post',
                            url: "/pfm/chargerule/add",
                            data: formdata+"&ruleId="+(flag==1?sel_obj.ruleId:"")+reduceexceptionFormData(),
                            success: function(data) {

                                var result = JSON.parse(data);

                                if (result.success) {
                                    devClone.modal('hide');
                                    flag==0?wade.libs.alert('添加成功'):wade.libs.alert('修改成功');
                                    refresh();
                                
                                } else {
                                    wade.libs.alert(result.msg, 0, devClone.find('div:first'));
                                    devClone.find("#submit").removeAttr("disabled");
                                }

                            }
                        });
                        e.stopImmediatePropagation(); // 阻止lib提交
                    });
                    if(flag==1){//修改数据
                    	$.ajax({
                    		url:'/pfm/chargerule/getchargerulebyid',
                    		type:'get',
                    		data:"ruleid="+sel_obj.ruleId,
                    		success:function(data){
                    			var result = JSON.parse(data);
                    			var ruleType = result.ruleType;
                    			if(ruleType==8){
                    				devClone.find("#chargeForm").find("#type").val(8);
                       				devClone.find("#chargeForm").find("[name=tempMatch]").val(result.rule.tempMatch);
                    				devClone.find("#chargeForm").find("[name=ruleName]").val(result.rule.ruleName);
                    				devClone.find("#chargeForm").find("#typesel").val(sel_obj.typeName);
                    				devClone.find("#chargeForm").find("[name=carType]").val(result.rule.carTypeId);
                       			}
                    		}
                    	});
                    	
                    }

                }
                break;
                case '7'://日夜分时
                    {
                        devClone.find("#chargeForm").find("#bottom").empty().append(chargeruleHtml7);
                        devClone.find("#chargeForm").find("[name=tempMatch]").val(0);
                        devClone.find("#chargeForm").find("[name=tempMatch]").removeAttr('disabled');
                        devClone.find("#chargeForm").find(":hidden[name=tempMatch]").remove();

                        
                        devClone.find("[href='#reducerule']").parent().show();
                        devClone.find("[href='#exceptionrule']").parent().show();
                        devClone.find("[href='#mobilereducerule']").parent().show();


                        riyeYanzheng = verifyRiye;
                        function verifyRiye(event){
                       	 var val1 = devClone.find("#chargeForm").find('input[name=ruleName]').val();
                            if (!verifyRuleName('chargeForm','ruleNameNotice',val1)) {
                                event.stopImmediatePropagation(); // 
                                return false;
                            } 	
                            if(!verifyMinute(devClone,devClone.find("[name=periodStartTime]"),devClone.find("[name=periodStartTime]").val())){
                            	 event.stopImmediatePropagation(); // 
                				return false;
                        	}
                        
                            var val2 = devClone.find("#chargeForm").find('input[name=firstPeriodTime]').val();
                            if (!verifyTimeMinute('firstPeriodTimeNotice',val2)) {
                                event.stopImmediatePropagation(); // 
                                return false;
                            }
                            var val3 = devClone.find("#chargeForm").find('input[name=firstPeriodMoney]').val();
                            if (!verifyMoney('firstPeriodMoneyNotice',val3)) {
                                event.stopImmediatePropagation(); // 
                                return false;
                            }
                            var val4 = devClone.find("#chargeForm").find('input[name=unitTime]').val();
                            if (!verifyTimeMinute('unitTimeNotice',val4)) {
                                event.stopImmediatePropagation(); // 
                                return false;
                            }
                            var val5 = devClone.find("#chargeForm").find('input[name=unitMoney]').val();
                            if (!verifyMoney('unitMoneyNotice',val5)) {
                                event.stopImmediatePropagation(); // 
                                return false;
                            }
                            if(!verifyMinute(devClone,devClone.find("[name=anciStartTime]"),devClone.find("[name=anciStartTime]").val())){
                              	 event.stopImmediatePropagation(); // 
                  				return false;
                               }
                            var val6 = devClone.find("#chargeForm").find('input[name=meiciMoney]').val();
                            if (!verifyMoney('meiciMoneyNotice',val6)) {
                                event.stopImmediatePropagation(); // 
                                return false;
                            }
                            var val7 = devClone.find("#chargeForm").find('input[name=freeTime]').val();
                            if (!verifyTimeMinute('freeTimeNotice',val7)) {
                                event.stopImmediatePropagation(); // 
                                return false;
                            }
                            if(devClone.find("#chargeForm").find('[name=useTopMoneyin24]').prop('checked'))
                            {
                            var val8= devClone.find("#chargeForm").find('input[name=topMoney]').val();

                            if (!verifyMoney('topMoneyNotice',val8)) {
                                event.stopImmediatePropagation(); // 
                                return false;
                            }
                            }
                            return true;
                       }
                        devClone.find("#chargeForm").find("[name=periodStartTime]").on('blur',function(){
                        	var val = devClone.find("[name=periodStartTime]").val();
                        	if(val!="")
                        	verifyMinute(devClone,devClone.find("[name=periodStartTime]"),val)
                        }).on("change keyup",function(){$(this).prev().remove();});
                        devClone.find("#chargeForm").find("input[name=firstPeriodTime]").on('blur',function(){
                        	var val = $(this).val();
                        	if(val!="")
                        	verifyTimeMinute('firstPeriodTimeNotice',val);
                        }).on("change keyup",function(){devClone.find("#firstPeriodTimeNotice").hide();});
                        devClone.find("#chargeForm").find("input[name=firstPeriodMoney]").on('blur',function(){
                        	var val = $(this).val();
                        	if(val!="")
                        	verifyMoney('firstPeriodMoneyNotice',val);
                        }).on("change keyup",function(){devClone.find("#firstPeriodMoneyNotice").hide();});
                        devClone.find("#chargeForm").find("input[name=unitTime]").on('blur',function(){
                        	var val = $(this).val();
                        	if(val!="")
                        	verifyTimeMinute('unitTimeNotice',val);
                        }).on("change keyup",function(){devClone.find("#unitTimeNotice").hide();});
                        devClone.find("#chargeForm").find("input[name=unitMoney]").on('blur',function(){
                        	var val = $(this).val();
                        	if(val!="")
                        	verifyMoney('unitMoneyNotice',val);
                        }).on("change keyup",function(){devClone.find("#unitMoneyNotice").hide();});
                        devClone.find("#chargeForm").find("[name=anciStartTime]").on('blur',function(){
                        	var val = devClone.find("[name=anciStartTime]").val()
                        	if(val!="")
                        	verifyMinute(devClone,devClone.find("[name=anciStartTime]"),val)
                        }).on("change keyup",function(){$(this).prev().remove();});
                        devClone.find("#chargeForm").find("input[name=meiciMoney]").on('blur',function(){
                        	var val = $(this).val();
                        	if(val!="")
                        	verifyMoney('meiciMoneyNotice',val);
                        }).on("change keyup",function(){devClone.find("#meiciMoneyNotice").hide();});
                        devClone.find("#chargeForm").find("input[name=freeTime]").on('blur',function(){
                        	var val = $(this).val();
                        	if(val!="")
                        	verifyTimeMinute('freeTimeNotice',val);
                        }).on("change keyup",function(){devClone.find("#freeTimeNotice").hide();});
                        devClone.find("#chargeForm").find('input[name=topMoney]').on("blur",
                                function() {
                                    var val = $(this).val();
                                    if(val!="")
                                    verifyMoney('topMoneyNotice',val);

                                }).on("change keyup",function(){devClone.find("#topMoneyNotice").hide();});
                        devClone.find("#chargeForm").find("[name=useTopMoneyin24]").on('click',function(){
                        	
                        	if($(this).prop("checked")){
                        		devClone.find("#chargeForm").find("#topmoneylabel").show();
                        		devClone.find("#chargeForm").find("#topmoney").show();
                        	}
                        	else{
                        		devClone.find("#chargeForm").find("#topmoneylabel").hide();
                        		devClone.find("#chargeForm").find("#topmoney").hide();
                        	}
                        });
               
                        devClone.find("#chargeForm").find(".timepicker").attr("onClick","WdatePicker({dateFmt:'HH:mm',onpicked:function(){$(this).trigger('change');}});");
                        devClone.find("#dosubmit").unbind('click').on('click',
                        function(e) {
                            if(!verifyRiye(e)) {
                            	return false;
                            	}
                            var jsonuserinfo = devClone.find("#chargeForm").serializeObject();
                            var formdata = "condition=" + JSON.stringify(jsonuserinfo);
                            $.ajax({ // 按单位时段收费
                                type: 'post',
                                url: "/pfm/chargerule/add",
                                data: formdata+"&ruleId="+(flag==1?sel_obj.ruleId:"")+reduceexceptionFormData(),
                                success: function(data) {

                                    var result = JSON.parse(data);

                                    if (result.success) {
                                        devClone.modal('hide');
                                        flag==0?wade.libs.alert('添加成功'):wade.libs.alert('修改成功');
                                        refresh();
                                    } else {
                                        wade.libs.alert(result.msg, 0, devClone.find('div:first'));
                                        devClone.find("#submit").removeAttr("disabled");
                                    }

                                }
                            });
                            e.stopImmediatePropagation(); // 阻止lib提交
                        });
                        if(flag==1){//修改数据
                        	$.ajax({
                        		url:'/pfm/chargerule/getchargerulebyid',
                        		type:'get',
                        		data:"ruleid="+sel_obj.ruleId,
                        		success:function(data){
                        			var result = JSON.parse(data);
                        			var ruleType = result.ruleType;
                        			if(ruleType==7){
                        				devClone.find("#chargeForm").find("#type").val(7);
                           				devClone.find("#chargeForm").find("[name=tempMatch]").val(result.rule.tempMatch);
                        				devClone.find("#chargeForm").find("[name=ruleName]").val(result.rule.ruleName);
                        				devClone.find("#chargeForm").find("#typesel").val(sel_obj.typeName);
                        				devClone.find("#chargeForm").find("[name=carType]").val(result.rule.carTypeId);
                        				//devClone.find("[name=periodStartTime]").val(result.daynight.periodStartTime);
                        				devClone.find("#chargeForm").find("[name=periodStartTime]").val( result.daynight.periodStartTime.substring(0,5));
                        				devClone.find("#chargeForm").find("[name=firstPeriodTime]").val(result.daynight.firstTime);
                        				devClone.find("#chargeForm").find("[name=firstPeriodMoney]").val(result.daynight.firstTimeCharge);
                        				devClone.find("#chargeForm").find("[name=unitTime]").val(result.daynight.unitTime);
                        				devClone.find("#chargeForm").find("[name=unitMoney]").val(result.daynight.unitTimeCharge);
                        				//devClone.find("[name=anciStartTime]").val(result.daynight.pertimeStartTime);
                        				devClone.find("#chargeForm").find("[name=anciStartTime]").val( result.daynight.pertimeStartTime.substring(0,5));
                        				devClone.find("#chargeForm").find("[name=meiciMoney]").val(result.daynight.pertimeCharge);
                        				devClone.find("#chargeForm").find("[name=freeTime]").val(result.daynight.freeTime);
                        				if(result.daynight.ifChargeOverFreeTime==0){
                        					devClone.find("#chargeForm").find("[name=overFreeTimeCharge]").prop("checked",true);
                        				}
                        				if(result.daynight.useTopMomeyIn24==0){
                        					devClone.find("#chargeForm").find("[name=useTopMoneyin24]").prop("checked",true);
                        					devClone.find("#chargeForm").find('[name=topMoney]').val(result.daynight.topMoney)
                            				devClone.find("#chargeForm").find("#topmoneylabel").show();
                                    		devClone.find("#chargeForm").find("#topmoney").show();
                        				}
                        				if(result.daynight.oneOne==0){
                        					devClone.find("#chargeForm").find("[name=oneSuanOne]").prop("checked",true);
                        				}
                        				if(result.daynight.byTimePeriod==0){
                        					devClone.find("#chargeForm").find("[name=byPeriodin24]").prop("checked",true);
                        				}
                        			}
                        		}
                        	});
                        	
                        }

                    }

                    break;
                }
                var textWidth = devClone.find("#chargeForm").find("[name='ruleName']").css("width");
                var textHeight = devClone.find("#chargeForm").find("[name='ruleName']").css("height");
                devClone.find("#chargeForm").find("select").css("width", textWidth);
                devClone.find("#chargeForm").find("select").css("height", textHeight);

            });
    
    //不启用时隐藏验证框
    
	devClone.find("#reduceForm").find("#shifouqiyong").on("change",function(){
		if($(this).val()==1){
			devClone.find("#reduceForm").find(".point-out").hide();
		}
	});
	devClone.find("#exceptionForm").find("#shifouqiyong").on("change",function(){
		if($(this).val()==1){
			devClone.find("#exceptionForm").find(".point-out").hide();
		}
	});
	devClone.find("#mobilereduceForm").find("#shifouqiyong").on("change",function(){
		if($(this).val()==1){
			devClone.find("#mobilereduceForm").find(".point-out").hide();
		}
	});
    //减免规则验证
	function verifyJianmian(event){
		var val1 = devClone.find("#reduceForm").find('input[name=ruleName]').val();
        if (!verifyRuleName('reduceForm','ruleNameNotice',val1)) {
            event.stopImmediatePropagation(); // 
            return false;
        }
        if(devClone.find("#reduceForm").find("[name='jianmianfangshi']").val()==1)
        	{
        var val2 = devClone.find("#reduceForm").find('#jianmian').val();
        if (!verifyMoney('jianmianNotice1',val2)) {
            event.stopImmediatePropagation(); // 
            return false;
        }
        	}
        if(devClone.find("#reduceForm").find("[name='jianmianfangshi']").val()==2||devClone.find("#reduceForm").find("[name='jianmianfangshi']").val()==3)
        	{
        var val3 = devClone.find("#reduceForm").find('#zhekou').val();
        if (!verifyZhekou(val3)) {
            event.stopImmediatePropagation(); // 
            return false;
        }
        }
        if(!verifyMinute(devClone,devClone.find("#reduceForm").find("[name=startdate]"),devClone.find("#reduceForm").find("[name=startdate]").val())){

        	event.stopImmediatePropagation(); // 
            return false;
         
        }
        if(!verifyMinute(devClone,devClone.find("#reduceForm").find("[name=enddate]"),devClone.find("#reduceForm").find("[name=enddate]").val())){

        	event.stopImmediatePropagation(); // 
        	return false;
      
        }
        if(!verifyNote("reduceForm","noteNotice",devClone.find("#reduceForm").find("#note").val())){
       	 event.stopImmediatePropagation(); // 
            return false;
       }
        return true;
	}
    function verifyZhekou(val){
    	if(/^[1-9]$|^[1-9][0-9]$/.test(val)){
    		if(parseInt(val)==0){
    			devClone.find("#reduceForm").find('.point-out').hide();
                devClone.find("#reduceForm").find("#jianmianNotice2").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>请输入1到99的整数</span>').show();

                return false;
    		}else{
    			devClone.find("#reduceForm").find("#zhekou").val(parseInt(val));
    			devClone.find("#reduceForm").find('#jianmianNotice2.point-out').hide();
    		return true;
    		}
    	}
    	else{
    		 devClone.find("#reduceForm").find('.point-out').hide();
             devClone.find("#reduceForm").find("#jianmianNotice2").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>请输入1到99的整数</span>').show();

    		return false;
    	}
    }
	
	devClone.find("#reduceForm").find("input[name=ruleName]").on('blur',function(){
		if(devClone.find("#reduceForm").find("#shifouqiyong").val()==0){
    	var val = $(this).val();
    	if(val!="")
    	verifyRuleName('reduceForm','ruleNameNotice',val);
		}
    }).on("change keyup",function(){devClone.find("#reduceForm #ruleNameNotice").hide();});
    devClone.find("#reduceForm").find("#jianmian").on('blur',function(){
    	if(devClone.find("#reduceForm").find("#shifouqiyong").val()==0){
    	var val = $(this).val();
    	if(val!="")
    	verifyMoney('jianmianNotice1',val);
    	}
    }).on("change keyup",function(){devClone.find("#jianmianNotice1").hide();});
    devClone.find("#reduceForm").find("#zhekou").on('blur',function(){
    	if(devClone.find("#reduceForm").find("#shifouqiyong").val()==0){
    	var val = $(this).val();
    	if(val!="")
    	verifyZhekou(val);
    	}
    }).on("change keyup",function(){devClone.find("#jianmianNotice2").hide();});
    devClone.find("#reduceForm").find("[name=startdate]").on('blur',function(){
    	if(devClone.find("#reduceForm").find("#shifouqiyong").val()==0){
    	var val = $(this).val();
    	if(val!="")
    	verifyMinute(devClone,devClone.find("#reduceForm").find("[name=startdate]"),devClone.find("#reduceForm").find("[name=startdate]").val())
    			
    	}
    }).on('change keyup',function(){$(this).prev().remove();});
    devClone.find("#reduceForm").find("[name=enddate]").on('blur',function(){
    	if(devClone.find("#reduceForm").find("#shifouqiyong").val()==0){
    	var val = $(this).val();
    	if(val!="")
    	verifyMinute(devClone,devClone.find("#reduceForm").find("[name=enddate]"),devClone.find("#reduceForm").find("[name=enddate]").val())

    	}
    }).on('change keyup',function(){$(this).prev().remove();});
    devClone.find("#reduceForm").find("#note").on('blur',function(){
    	if(devClone.find("#reduceForm").find("#shifouqiyong").val()==0){
    	var val = $(this).val();
    	if(val!="")
    		verifyNote("reduceForm","noteNotice",val);
    	}
    }).on("change keyup",function(){devClone.find("#reduceForm #noteNotice").hide();});

//    ////
	   //移动支付减免规则验证
	function verifyMobileJianmian(event){
		var val1 = devClone.find("#mobilereduceForm").find('input[name=ruleName]').val();
        if (!verifyRuleName('mobilereduceForm','ruleNameNotice',val1)) {
            event.stopImmediatePropagation(); // 
            return false;
        }
        if(devClone.find("#mobilereduceForm").find("[name='jianmianfangshi']").val()==4)
        	{
        var val2 = devClone.find("#mobilereduceForm").find('#jianmian').val();
        if (!verifyMoney('jianmianNotice3',val2)) {
            event.stopImmediatePropagation(); // 
            return false;
        }
        	}
        if(devClone.find("#mobilereduceForm").find("[name='jianmianfangshi']").val()==5)
        	{
        var val3 = devClone.find("#mobilereduceForm").find('#zhekou').val();
        if (!verifyZhekoumobile(val3)) {
            event.stopImmediatePropagation(); // 
            return false;
        }
        }
        if(!verifyMinute(devClone,devClone.find("#mobilereduceForm").find("[name=startdate]"),devClone.find("#mobilereduceForm").find("[name=startdate]").val())){

        	event.stopImmediatePropagation(); // 
            return false;
         
        }
        if(!verifyMinute(devClone,devClone.find("#mobilereduceForm").find("[name=enddate]"),devClone.find("#mobilereduceForm").find("[name=enddate]").val())){

        	event.stopImmediatePropagation(); // 
        	return false;
      
        }
        if(!verifyNote("mobilereduceForm","noteNotice",devClone.find("#mobilereduceForm").find("#note").val())){
       	 event.stopImmediatePropagation(); // 
            return false;
       }
        return true;
	}
    function verifyZhekoumobile(val){
    	if(/^[1-9]$|^[1-9][0-9]$/.test(val)){
    		if(parseInt(val)==0){
    			devClone.find("#mobilereduceForm").find('.point-out').hide();
                devClone.find("#mobilereduceForm").find("#jianmianNotice4").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>请输入1到99的整数</span>').show();

                return false;
    		}else{
    			devClone.find("#mobilereduceForm").find("#zhekou").val(parseInt(val));
    			devClone.find("#mobilereduceForm").find('#jianmianNotice4.point-out').hide();
    		return true;
    		}
    	}
    	else{
    		 devClone.find("#mobilereduceForm").find('.point-out').hide();
             devClone.find("#mobilereduceForm").find("#jianmianNotice4").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>请输入1到99的整数</span>').show();

    		return false;
    	}
    }
    
	devClone.find("#mobilereduceForm").find("input[name=ruleName]").on('blur',function(){
		if(devClone.find("#mobilereduceForm").find("#shifouqiyong").val()==0){
    	var val = $(this).val();
    	if(val!="")
    	verifyRuleName('mobilereduceForm','ruleNameNotice',val);
		}
    }).on("change keyup",function(){devClone.find("#reduceForm #ruleNameNotice").hide();});
    devClone.find("#mobilereduceForm").find("#jianmian").on('blur',function(){
    	if(devClone.find("#mobilereduceForm").find("#shifouqiyong").val()==0){
    	var val = $(this).val();
    	if(val!="")
    	verifyMoney('jianmianNotice3',val);
    	}
    }).on("change keyup",function(){devClone.find("#jianmianNotice3").hide();});
    devClone.find("#mobilereduceForm").find("#zhekou").on('blur',function(){
    	if(devClone.find("#mobilereduceForm").find("#shifouqiyong").val()==0){
    	var val = $(this).val();
    	if(val!="")
    		verifyZhekoumobile(val);
    	}
    }).on("change keyup",function(){devClone.find("#jianmianNotice4").hide();});
    devClone.find("#mobilereduceForm").find("[name=startdate]").on('blur',function(){
    	if(devClone.find("#mobilereduceForm").find("#shifouqiyong").val()==0){
    	var val = $(this).val();
    	if(val!="")
    	verifyMinute(devClone,devClone.find("#mobilereduceForm").find("[name=startdate]"),devClone.find("#mobilereduceForm").find("[name=startdate]").val())
    			
    	}
    }).on('change keyup',function(){$(this).prev().remove();});
    devClone.find("#mobilereduceForm").find("[name=enddate]").on('blur',function(){
    	if(devClone.find("#mobilereduceForm").find("#shifouqiyong").val()==0){
    	var val = $(this).val();
    	if(val!="")
    	verifyMinute(devClone,devClone.find("#mobilereduceForm").find("[name=enddate]"),devClone.find("#mobilereduceForm").find("[name=enddate]").val())

    	}
    }).on('change keyup',function(){$(this).prev().remove();});
    devClone.find("#mobilereduceForm").find("#note").on('blur',function(){
    	if(devClone.find("#mobilereduceForm").find("#shifouqiyong").val()==0){
    	var val = $(this).val();
    	if(val!="")
    		verifyNote("mobilereduceForm","noteNotice",val);
    	}
    }).on("change keyup",function(){devClone.find("#mobilereduceForm #noteNotice").hide();});


//减免方式绑定事件 select
    devClone.find("#reduceForm").find("[name='jianmianfangshi']").on('change',function(){
    	if($(this).val()==1){
    		devClone.find("#reduceForm").find("#jianmiantr").show();
    		devClone.find("#reduceForm").find("#zhekoutr").hide();
    		devClone.find("#reduceForm").find("#zhekoutr #zhekou").attr("disabled","disabled");
    		devClone.find("#reduceForm").find("#jianmiantr #jianmian").removeAttr("disabled");
    		
    		
    	}else if($(this).val()==2){
    		devClone.find("#reduceForm").find("#jianmiantr").hide();
    		devClone.find("#reduceForm").find("#zhekoutr").show();
    		devClone.find("#reduceForm").find("#zhekoutr #zhekou").removeAttr("disabled");
    		devClone.find("#reduceForm").find("#jianmiantr #jianmian").attr("disabled","disabled");
    	}else if($(this).val()==3){
    		devClone.find("#reduceForm").find("#jianmiantr").hide();
    		devClone.find("#reduceForm").find("#zhekoutr").hide();
    		devClone.find("#reduceForm").find("#zhekoutr #zhekou").removeAttr("disabled").val(1);
    		devClone.find("#reduceForm").find("#jianmiantr #jianmian").attr("disabled","disabled");
    	}
    });
  //移动支付减免方式绑定事件 select
    devClone.find("#mobilereduceForm").find("[name='jianmianfangshi']").on('change',function(){
    	if($(this).val()==4){
    		devClone.find("#mobilereduceForm").find("#jianmiantr").show();
    		devClone.find("#mobilereduceForm").find("#zhekoutr").hide();
    		devClone.find("#mobilereduceForm").find("#zhekoutr #zhekou").attr("disabled","disabled");
    		devClone.find("#mobilereduceForm").find("#jianmiantr #jianmian").removeAttr("disabled");
    		
    		
    	}else if($(this).val()==5){
    		devClone.find("#mobilereduceForm").find("#jianmiantr").hide();
    		devClone.find("#mobilereduceForm").find("#zhekoutr").show();
    		devClone.find("#mobilereduceForm").find("#zhekoutr #zhekou").removeAttr("disabled");
    		devClone.find("#mobilereduceForm").find("#jianmiantr #jianmian").attr("disabled","disabled");
    	}
    });
    
    

    //异常规则验证
	function verifyException(event){
		var val1 = devClone.find("#exceptionForm").find('input[name=ruleName]').val();
        if (!verifyRuleName('exceptionForm','ruleNameNotice',val1)) {
            event.stopImmediatePropagation(); // 
            return false;
        }
        var val2 = devClone.find("#exceptionForm").find('input[name=jianmian]').val();
        if (!verifyMoney('jianmianNotice',val2)) {
            event.stopImmediatePropagation(); // 
            return false;
        }
        if(!verifyMinute(devClone,devClone.find("#exceptionForm").find("[name=startdate]"),devClone.find("#exceptionForm").find("[name=startdate]").val())){

        	   event.stopImmediatePropagation(); // 
               return false;
            
        }
        if(!verifyMinute(devClone,devClone.find("#exceptionForm").find("[name=enddate]"),devClone.find("#exceptionForm").find("[name=enddate]").val())){

     	   event.stopImmediatePropagation(); // 
            return false;
         
        }

        if(!verifyNote("exceptionForm","noteNotice",devClone.find("#exceptionForm").find("#note").val())){
        	 event.stopImmediatePropagation(); // 
             return false;
        }
        return true;
      
	}
	//异常规则验证单个验证
	devClone.find("#exceptionForm").find("input[name=ruleName]").on('blur',function(){
		if(devClone.find("#exceptionForm").find("#shifouqiyong").val()==0){
    	var val = $(this).val();
    	if(val!="")
    	verifyRuleName('exceptionForm','ruleNameNotice',val);
		}
    }).on("change keyup",function(){devClone.find("#exceptionForm #ruleNameNotice").hide();});
	 devClone.find("#exceptionForm").find("input[name=jianmian]").on('blur',function(){
		 if(devClone.find("#exceptionForm").find("#shifouqiyong").val()==0){
     	var val = $(this).val();
     	if(val!="")
     	verifyMoney('jianmianNotice',val);
		 }
     }).on("change keyup",function(){devClone.find("#exceptionForm #jianmianNotice").hide();});
	 devClone.find("#exceptionForm").find("[name=startdate]").on('blur',function(){
	    	if(devClone.find("#exceptionForm").find("#shifouqiyong").val()==0){
	    	var val = $(this).val();
	    	if(val!="")
	    	verifyMinute(devClone,devClone.find("#exceptionForm").find("[name=startdate]"),devClone.find("#exceptionForm").find("[name=startdate]").val())
	    			
	    	}
	    }).on('change keyup',function(){$(this).prev().remove();});
	 devClone.find("#exceptionForm").find("[name=enddate]").on('blur',function(){
	    	if(devClone.find("#exceptionForm").find("#shifouqiyong").val()==0){
	    	var val = $(this).val();
	    	if(val!="")
	    	verifyMinute(devClone,devClone.find("#exceptionForm").find("[name=enddate]"),devClone.find("#exceptionForm").find("[name=enddate]").val())
	    			
	    	}
	    }).on('change keyup',function(){$(this).prev().remove();});
	 devClone.find("#exceptionForm").find("#note").on('blur',function(){
	    	if(devClone.find("#exceptionForm").find("#shifouqiyong").val()==0){
	    	var val = $(this).val();
	    	if(val!="")
	    	verifyNote("exceptionForm","noteNotice",val);
	    	}
	    }).on("change keyup",function(){devClone.find("#exceptionForm #noteNotice").hide();});

 
    if(flag==0){
    	var endt = new Date();
    	endt = endt.setMonth(endt.getMonth()+1);
    	//减免方式初始化日期控件
    devClone.find("#reduceForm").find("[name=startdate]").val(new Date().Format("yyyy-MM-dd hh:mm"));
	devClone.find("#reduceForm").find("[name=enddate]").val(new Date(endt).Format("yyyy-MM-dd hh:mm"));
	devClone.find("#reduceForm").find("#jianmiantr").show();
	devClone.find("#reduceForm").find("#zhekoutr").hide();
	devClone.find("#reduceForm").find("#zhekoutr #zhekou").attr("disabled","disabled");
	devClone.find("#reduceForm").find("#jianmiantr #jianmian").removeAttr("disabled");
		//移动支付减免方式初始化日期控件
    devClone.find("#mobilereduceForm").find("[name=startdate]").val(new Date().Format("yyyy-MM-dd hh:mm"));
	devClone.find("#mobilereduceForm").find("[name=enddate]").val(new Date(endt).Format("yyyy-MM-dd hh:mm"));
	devClone.find("#mobilereduceForm").find("#jianmiantr").show();
	devClone.find("#mobilereduceForm").find("#zhekoutr").hide();
	devClone.find("#mobilereduceForm").find("#zhekoutr #zhekou").attr("disabled","disabled");
	devClone.find("#mobilereduceForm").find("#jianmiantr #jianmian").removeAttr("disabled");
		//异常放行方式初始化日期控件
	devClone.find("#exceptionForm").find("[name=startdate]").val(new Date().Format("yyyy-MM-dd hh:mm"));
	devClone.find("#exceptionForm").find("[name=enddate]").val(new Date(endt).Format("yyyy-MM-dd hh:mm"));

    }

    //点击tab页时 模态框响应
    devClone.find(".nav-tabs a[href='#chargerule']").click(function(e) {
    	if(curTab==0){
    		return true;
    	}
    	switch(curTab){
    	case 1: if(devClone.find("#reduceForm").find("#shifouqiyong").val()==0){if(!verifyJianmian(e)){return;}}break;
    	case 2: if(devClone.find("#exceptionForm").find("#shifouqiyong").val()==0){if(!verifyException(e)){return;}}break;
    	case 3: if(devClone.find("#mobilereduceForm").find("#shifouqiyong").val()==0){if(!verifyMobileJianmian(e)){return;}}break;
    	}
    	curTab=0;
    	if(flag==0){
    	devClone.find(".modal-title").text("添加收费规则");
    	}else if(flag==1){
    		devClone.find(".modal-title").text("修改收费规则");
    		
    	}
    	devClone.find("#chargeForm").show();
    	devClone.find("#reduceForm").hide();
    	devClone.find("#exceptionForm").hide();
    	devClone.find("#mobilereduceForm").hide();
    	
        return true;;

    }).trigger("click");
    
    if(flag==0){
    	devClone.find("#chargeForm").find("#typesel").val(selnode.name);
 		devClone.find("#chargeForm").find("[name=carType]").val(selnode.id);
    	devClone.find("#chargeForm").find("#type").val(0).trigger("change");
    }
    if(flag==1){
    	devClone.find("#chargeForm").find("#type").val(sel_obj.ruleTypeIndex).trigger("change");
    }
    devClone.find(".nav-tabs a[href='#reducerule']").click(function(event) {
    	
    	if(curTab==1){
    		return true;
    	}
    	switch(curTab){
    	case 0: {var a = devClone.find("#chargeForm").find("#type").val();
    	switch(a){
    	case "0":{ if(!fenshiYanzheng(event)){return;}}break;
    	case "1":{ if(!fenshiduanYanzheng(event)){return;}}break;
    	case "2":{ if(!danweishiduanYanzheng(event)){return;}}break;
    	case "3":{ if(!anciYanzheng(event)){return;}}break;
    	case "4":{ if(!anshiYanzheng(event)){return;}}break;
    	case "5":{ if(!baoqiYanzheng(event)){return;}}break;
    	case "6":{ if(!noYanzheng(event)){return;}}break;
    	case "7":{ if(!riyeYanzheng(event)){return;}}break;
    	case "8":{ if(!noYanzheng(event)){return;}}break;
    	}}break;
    	case 2: if(devClone.find("#exceptionForm").find("#shifouqiyong").val()==0){if(!verifyException(event)){return;}}break;
    	case 3: if(devClone.find("#mobilereduceForm").find("#shifouqiyong").val()==0){if(!verifyMobileJianmian(event)){return;}}break;

    	
    	}
    	curTab=1;
    	devClone.find(".modal-title").text("添加减免规则");
    	if(flag==1){
    		devClone.find(".modal-title").text("修改减免规则");
    	}
    	//隐藏其他的form
    	devClone.find("#reduceForm").show();
    	devClone.find("#chargeForm").hide();
    	devClone.find("#exceptionForm").hide();
    	devClone.find("#mobilereduceForm").hide();
    	
    	 var textWidth = devClone.find("#reduceForm").find("[name='ruleName']").css("width");
 	    var textHeight = devClone.find("#reduceForm").find("[name='ruleName']").css("height");
 	    devClone.find("#reduceForm").find("select").css("width", textWidth);
 	    devClone.find("#reduceForm").find("select").css("height", textHeight);
       
      
    });
    
    devClone.find(".nav-tabs a[href='#mobilereducerule']").click(function(event) {
    	
    	if(curTab==3){
    		return true;
    	}
    	switch(curTab){
    	case 0: {var a = devClone.find("#chargeForm").find("#type").val();
    	switch(a){
    	case "0":{ if(!fenshiYanzheng(event)){return;}}break;
    	case "1":{ if(!fenshiduanYanzheng(event)){return;}}break;
    	case "2":{ if(!danweishiduanYanzheng(event)){return;}}break;
    	case "3":{ if(!anciYanzheng(event)){return;}}break;
    	case "4":{ if(!anshiYanzheng(event)){return;}}break;
    	case "5":{ if(!baoqiYanzheng(event)){return;}}break;
    	case "6":{ if(!noYanzheng(event)){return;}}break;
    	case "7":{ if(!riyeYanzheng(event)){return;}}break;
    	case "8":{ if(!noYanzheng(event)){return;}}break;
    	}}break;
    	case 1: if(devClone.find("#reduceForm").find("#shifouqiyong").val()==0){if(!verifyJianmian(event)){return;}}break;
    	case 2: if(devClone.find("#exceptionForm").find("#shifouqiyong").val()==0){if(!verifyException(event)){return;}}break;
    	
    	}
    	curTab=3;
    	devClone.find(".modal-title").text("添加移动支付减免规则");
    	if(flag==1){
    		devClone.find(".modal-title").text("修改移动支付减免规则");
    	}
    	
    	devClone.find("#mobilereduceForm").show();
    	devClone.find("#reduceForm").hide();
    	devClone.find("#chargeForm").hide();
    	devClone.find("#exceptionForm").hide();
    
    	 var textWidth = devClone.find("#mobilereduceForm").find("[name='ruleName']").css("width");
 	    var textHeight = devClone.find("#mobilereduceForm").find("[name='ruleName']").css("height");
 	    devClone.find("#mobilereduceForm").find("select").css("width", textWidth);
 	    devClone.find("#mobilereduceForm").find("select").css("height", textHeight);
       
      
    });
   
    
    devClone.find(".nav-tabs a[href='#exceptionrule']").click(function(event) {
    	if(curTab==2){
    		return true;
    	}
    	switch(curTab){
    	case 0: {var a = devClone.find("#chargeForm").find("#type").val();
    	switch(a){
    	case "0":{ if(!fenshiYanzheng(event)){return;}}break;
    	case "1":{ if(!fenshiduanYanzheng(event)){return;}}break;
    	case "2":{ if(!danweishiduanYanzheng(event)){return;}}break;
    	case "3":{ if(!anciYanzheng(event)){return;}}break;
    	case "4":{ if(!anshiYanzheng(event)){return;}}break;
    	case "5":{ if(!baoqiYanzheng(event)){return;}}break;
    	case "6":{ if(!noYanzheng(event)){return;}}break;
    	case "7":{ if(!riyeYanzheng(event)){return;}}break;
    	case "8":{ if(!noYanzheng(event)){return;}}break;
    	}}break;
    	case 1: if(devClone.find("#reduceForm").find("#shifouqiyong").val()==0){if(!verifyJianmian(event)){return;}}break;
    	case 3: if(devClone.find("#mobilereduceForm").find("#shifouqiyong").val()==0){if(!verifyMobileJianmian(event)){return;}}break;

    	}
    	curTab=2;
    	devClone.find(".modal-title").text("添加异常放行规则");
    	if(flag==1){
    		devClone.find(".modal-title").text("修改异常放行规则");
    	}
     	devClone.find("#exceptionForm").show();
    	devClone.find("#chargeForm").hide();
    	devClone.find("#reduceForm").hide();
    	devClone.find("#mobilereduceForm").hide();
    	
    	 var textWidth = devClone.find("#exceptionForm").find("[name='ruleName']").css("width");
    	    var textHeight = devClone.find("#exceptionForm").find("[name='ruleName']").css("height");
    	    devClone.find("#exceptionForm").find("select").css("width", textWidth);
    	    devClone.find("#exceptionForm").find("select").css("height", textHeight);
    	    

    });
    
    
    //验证并且提交，四条规则一起提交  TODO
    devClone.find("#submit").unbind("click").on("click",function(event){
    	
    	var a = devClone.find("#chargeForm").find("#type").val();
    	switch(a){
    	case "0":{ if(!fenshiYanzheng(event)){devClone.find(".nav-tabs a[href='#chargerule']").click();return;}}break;
    	case "1":{ if(!fenshiduanYanzheng(event)){devClone.find(".nav-tabs a[href='#chargerule']").click();return;}}break;
    	case "2":{ if(!danweishiduanYanzheng(event)){devClone.find(".nav-tabs a[href='#chargerule']").click();return;}}break;
    	case "3":{ if(!anciYanzheng(event)){devClone.find(".nav-tabs a[href='#chargerule']").click();return;}}break;
    	case "4":{ if(!anshiYanzheng(event)){devClone.find(".nav-tabs a[href='#chargerule']").click();return;}}break;
    	case "5":{ if(!baoqiYanzheng(event)){devClone.find(".nav-tabs a[href='#chargerule']").click();return;}}break;
    	case "6":{ if(!noYanzheng(event)){devClone.find(".nav-tabs a[href='#chargerule']").click();return;}}break;
    	case "7":{ if(!riyeYanzheng(event)){devClone.find(".nav-tabs a[href='#chargerule']").click();return;}}break;
    	case "8":{ if(!noYanzheng(event)){devClone.find(".nav-tabs a[href='#chargerule']").click();return;}}break;
    	}
    	if(devClone.find("#reduceForm").find("#shifouqiyong").val()==0){
    	if(!verifyJianmian(event)){
    		devClone.find(".nav-tabs a[href='#reducerule']").click();
    		 return;
    	}
    	}
    	if(devClone.find("#mobilereduceForm").find("#shifouqiyong").val()==0){
        	if(!verifyMobileJianmian(event)){
        		devClone.find(".nav-tabs a[href='#mobilereducerule']").click();
        		 return;
        	}
        	}
    	if(devClone.find("#exceptionForm").find("#shifouqiyong").val()==0){
    	if(!verifyException(event)){
    		 devClone.find(".nav-tabs a[href='#exceptionrule']").click();
    		return;
    	}
    	}
    	//提交
    	devClone.find("#dosubmit").click();
    	$(this).attr("disabled","disabled");
    	event.stopImmediatePropagation();
    	
    });
    
    //修改规则填充modal
    if(flag==1){
    	//减免规则填充
    	$.ajax({
    		url:'/pfm/chargerule/getjianmianrulebyid',
    		type:'get',
    		data:"ruleid="+sel_obj.jianmianId,
    		success:function(data){
    			var result = JSON.parse(data);
    			var rule = result.jianmianyichangrule;
    			
    			devClone.find("#reduceForm").find("[name=ruleName]").val(rule.ruleName);
    			devClone.find("#reduceForm").find("[name=jianmianfangshi]").val(rule.type).trigger('change');
    		
    			if(rule.ruleName==''){
    				var endt = new Date();
    		    	endt = endt.setMonth(endt.getMonth()+1);
    		    devClone.find("#reduceForm").find("[name=startdate]").val(new Date().Format("yyyy-MM-dd hh:mm"));
    			devClone.find("#reduceForm").find("[name=enddate]").val(new Date(endt).Format("yyyy-MM-dd hh:mm"));
    			}
    			else{
    				devClone.find("#reduceForm").find("[name=startdate]").val(rule.startTime.substring(0,16));
        			devClone.find("#reduceForm").find("[name=enddate]").val(rule.endTime.substring(0,16));
    			}
    			devClone.find("#reduceForm").find("#note").val(rule.note);
    			devClone.find("#reduceForm").find("#shifouqiyong").val(rule.custom1);//是否启用
    			if(rule.custom1==0){
    			if(rule.type==1){
    				devClone.find("#reduceForm").find("#jianmian").val(rule.money);
    			}
    			else if(rule.type==2){
    				devClone.find("#reduceForm").find("#zhekou").val(rule.money);
    			}
    			}
    		 
    			}
    		
    	});
      	//移动支付减免规则填充
    
    	$.ajax({
    		url:'/pfm/chargerule/getjianmianrulebyid',
    		type:'get',
    		data:"ruleid="+sel_obj.mobilejianmianId,
    		success:function(data){
    			var result = JSON.parse(data);
    			var rule = result.jianmianyichangrule;
    			devClone.find("#mobilereduceForm").find("[name=ruleName]").val(rule.ruleName);
    			devClone.find("#mobilereduceForm").find("[name=jianmianfangshi]").val(rule.type).trigger('change');

    			if(rule.ruleName==''){
    				var endt = new Date();
    		    	endt = endt.setMonth(endt.getMonth()+1);
    		    devClone.find("#mobilereduceForm").find("[name=startdate]").val(new Date().Format("yyyy-MM-dd hh:mm"));
    			devClone.find("#mobilereduceForm").find("[name=enddate]").val(new Date(endt).Format("yyyy-MM-dd hh:mm"));
    			}
    			else{
    				devClone.find("#mobilereduceForm").find("[name=startdate]").val(rule.startTime.substring(0,16));
        			devClone.find("#mobilereduceForm").find("[name=enddate]").val(rule.endTime.substring(0,16));
    			}
    			devClone.find("#mobilereduceForm").find("#note").val(rule.note);
    			devClone.find("#mobilereduceForm").find("#shifouqiyong").val(rule.custom1);//是否启用
    			if(rule.custom1==0){
        			if(rule.type==4){
        				devClone.find("#mobilereduceForm").find("#jianmian").val(rule.money);
        			}
        			else if(rule.type==5){
        				devClone.find("#mobilereduceForm").find("#zhekou").val(rule.money);
        			}
        			}
    			}
    		
    	});
    	
    	
    	//异常规则填充
    	$.ajax({
    		url:'/pfm/chargerule/getjianmianrulebyid',
    		type:'get',
    		data:"ruleid="+sel_obj.yichangId,
    		success:function(data){
    			var result = JSON.parse(data);
    			var rule = result.jianmianyichangrule;
    			devClone.find("#exceptionForm").find("[name=ruleName]").val(rule.ruleName);
    			if(rule.ruleName==''){
    				var endt = new Date();
    		    	endt = endt.setMonth(endt.getMonth()+1);
    		    devClone.find("#exceptionForm").find("[name=startdate]").val(new Date().Format("yyyy-MM-dd hh:mm"));
    			devClone.find("#exceptionForm").find("[name=enddate]").val(new Date(endt).Format("yyyy-MM-dd hh:mm"));
    			}
    			else{
    				devClone.find("#exceptionForm").find("[name=startdate]").val(rule.startTime.substring(0,16));
        			devClone.find("#exceptionForm").find("[name=enddate]").val(rule.endTime.substring(0,16));
    			}
    			devClone.find("#exceptionForm").find("#note").val(rule.note);
    			devClone.find("#exceptionForm").find("#shifouqiyong").val(rule.custom1);//是否启用
    			if(rule.custom1==0){
        			devClone.find("#exceptionForm").find("[name=jianmian]").val(rule.money);
    			}
    			}
    		
    	});
  
    }
   
}
