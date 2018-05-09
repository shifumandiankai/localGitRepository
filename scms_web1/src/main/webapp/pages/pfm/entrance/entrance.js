var wade = wade || {};
wade.entrance = wade.entrance || {};

var ztreeConf = $.extend({},
{
    id: "tree",
    url: "/entrance/gettree.do",
    expand:true,
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
           onClick: nodeClick,
           //onExpand: expandNode,
           beforeClick:beforeClick
        }
    }
});

function beforeClick(treeId, treeNode, clickFlag){
	if(!treeNode.hasPermission){
		alert("您没有权限，请联系管理员");
		return false;
		
	}
}

wade.libs.tree(ztreeConf);//初始化树控件
//$.fn.zTree.getZTreeObj(ztreeConf.id).expandAll(false);
//点击节点触发事件
function nodeClick(event, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("tree");
	var selectNode=zTree.getSelectedNodes()[0];//选中的节点
	var entranceid=selectNode.entranceid;//选中出入口的编号(4个中的一个出入口)
	var awayid=selectNode.awayid;//选中车道的编号(4个中的一个车道)
	if(entranceid!=null){
		//点击出入口节点
		//点击节点触发tab事件
		$('#btnsmt').attr('disabled',false);
		
		
		$('input[name="enabled"]').attr('disabled',false);
		$('#myTab li:eq(0) a').tab('show');//默认切换tab页 
		$('input[name="entranceName"]').val(selectNode.name);
		$('input[name="timeInterval"]').val(selectNode.timeInterval);
		if(selectNode.entranceenabled==0){
			ChangEnabledNoCheck();
		}
		else{
			ChangEnabledCheck();
		}
		addTimeId(selectNode);
		
		var childnodes=zTree.getNodesByParam("pId",selectNode.id, null);//车道节点
		$('input[name="carriagewayName"]').val(childnodes[0].name);
		if(childnodes[0].carrawayenabled==0){
			ChangCDEnabledNoCheck();
		}
		else{
			ChangCDEnabledCheck();
		}
		zTree.selectNode(childnodes[0]);
		addCarryaway(childnodes[0]);
		addDeviceId(childnodes[0]);
	}
	if(awayid!=null){
		$('#btnsmt').attr('disabled',false);
		//点击车道节点
		$('input[name="enabled"]').attr('disabled',false)
		
		$("#myTab li:eq('"+(awayid-1)+"') a").tab('show');
		var parentnode=zTree.getNodeByParam("id",selectNode.pId, null);//出入口节点
		//console.log(parentnode);
		$('input[name="entranceName"]').val(parentnode.name);
		$('input[name="timeInterval"]').val(parentnode.timeInterval);
		addCarryaway(selectNode);
		addTimeId(parentnode);
		$('input[name="carriagewayName"]').val(selectNode.name);
		$('input[name="carriageTimeInterval"]').val(selectNode.carriageTimeInterval);
		addDeviceId(selectNode);
		if(parentnode.entranceenabled==0){
			ChangEnabledNoCheck();
		}
		else{
			ChangEnabledCheck();
		}
		if(selectNode.carrawayenabled==0){
			ChangCDEnabledNoCheck();
			
		}else{
			ChangCDEnabledCheck();
		}
		
		/*if(parentnode.entranceenable==0){
			ChangEnabledNoCheck();
		}
		else{
			ChangEnabledCheck();
		}
		if(selectNode.carriagewayenable==0){
			ChangCDEnabledNoCheck();
		}
		else{
			ChangCDEnabledCheck();
		}*/
		//alert(selectNode.name);
		/*$('input[name="carriagewayName"]').val(selectNode.name);
		$('input[name="carriageTimeInterval"]').val(selectNode.carriageTimeInterval);
		addDeviceId(selectNode);*/
		
		
	}
		
	
};
//展开节点
function expandNode(event, treeId, treeNode){
	
	var val=treeNode.id;
	var str=val.substring(0,1);
	var zTree = $.fn.zTree.getZTreeObj("tree");
	if(str=='e'&&treeNode.hasPermission){
		$('input[name="enabled"]').attr('disabled',false);
		$('#myTab li:eq(0) a').tab('show');//默认切换tab页 
		$('input[name="entranceName"]').val(treeNode.name);
		$('input[name="timeInterval"]').val(treeNode.timeInterval);
		if(treeNode.entranceenable==0){
			ChangEnabledNoCheck();
		}
		else{
			ChangEnabledCheck();
		}
		if(treeNode.entranceenabled==0){
			ChangEnabledNoCheck();
		}
		else{
			ChangEnabledCheck();
		}
		addTimeId(treeNode);
		var nodes=zTree.getNodesByParam("pId",treeNode.id, null);//车道节点
		$('input[name="carriagewayName"]').val(nodes[0].name);
		$('input[name="carriageTimeInterval"]').val(nodes[0].carriageTimeInterval);
		if(nodes[0].carrawayenabled==0){
			ChangCDEnabledNoCheck();
		}
		else{
			ChangCDEnabledCheck();
		}
		
		if(nodes[0].carriagewayenable==0){
			ChangCDEnabledNoCheck();
		}
		else{
			ChangCDEnabledCheck();
		}
		zTree.selectNode(nodes[0]);
		addCarryaway(nodes[0]);
		addDeviceId(nodes[0]);
		
		
		

	}
}
//填充车道内容
function addCarryaway(node){
	$('select[name="carriagewayType"]').val(node.carryType);
	if(node.carryType==3){
		$('#div1').hide();
		$('#div2').show();
		$('#in1').hide();
		if(node.autoFixed==1){
			$('input[name="autoReleaseFixed"]').prop('checked',true);
		}else{
			$('input[name="autoReleaseFixed"]').prop('checked',false);
		}
		if(node.autoTemp==1){
			$('input[name="autoReleaseTemp"]').prop('checked',true);
		}else{
			$('input[name="autoReleaseTemp"]').prop('checked',false);
		}
		if(node.autoCharge==1){
			$('input[name="autoCharge"]').prop('checked',true);
		}else{
			$('input[name="autoCharge"]').prop('checked',false);
		}
	}
	else{
		$('#div2').hide();
		$('#div1').show();
		$('#in1').show();
		if(node.releaseFixed==1){
			$('input[name="releaseFixed"]').prop('checked',true);
		}else{
			$('input[name="releaseFixed"]').prop('checked',false);
		}
		if(node.releaseTemp==1){
			$('input[name="releaseTemp"]').prop('checked',true);
		}else{
			$('input[name="releaseTemp"]').prop('checked',false);
		}
		if(node.inOut==1){
			$('input[name="inOut"]').prop('checked',true);
		}else{
			$('input[name="inOut"]').prop('checked',false);
		}
	}
	if(node.custom3==1){
		$('input[name="custom3"]').prop('checked',true);
	}else{
		$('input[name="custom3"]').prop('checked',false);
	}
	$('input[name="carriageTimeInterval"]').val(node.carriageTimeInterval);
	
	
}

//填充时段
function addTimeId(node){
	
		$('select[name="timeId"]').val(node.timeId);
	
}
//填充设备
function addDeviceId(node){
	
	if(node.deviceId!=null){
		//$.each(node.deviceId,function(index,value){
			$('*[name="deviceId"]').select2().val(node.deviceId).trigger("change");
			//alert($('*[name="deviceId"]').val());
		//});
	}
	else{
		//InitSelect();
		$('*[name="deviceId"]').select2().val('').trigger("change");
		//alert($('*[name="deviceId"]').val());
	}
	
}
// 展开出入口时 显示名称和控制启用
function funEntrance(node){
	if(node.falsename=='(已禁用)'){
		$('input[name="entranceName"]').val('');
	}
	else{
		$('input[name="entranceName"]').val(node.falsename);
	}
	if(node.entranceenable==0){
		wade.tree.updateNodeName("tree",'(已禁用)',node);
		ChangEnabledNoCheck();
	}
	else{
		wade.tree.updateNodeName("tree",node.falsename,node);
		ChangEnabledCheck();
	}
}

//点击车道名称启用按钮
$('input[name="awayenabled"]').click(function(){
//$('input[name="awayenabled"]').on('change',function(){
	var zTree = $.fn.zTree.getZTreeObj("tree");
	var selectNode=zTree.getSelectedNodes()[0];//选中的节点
	var val=selectNode.id;
	var str=val.substring(0,1);
	if(str=='c'){
		if($(this).is(':checked')){
			ChangCDEnabledCheck();
			addCarryaway(selectNode);
			//var name=$('input[name="carriagewayName"]').val();
			//wade.tree.updateNodeCaEnable("tree",1,selectNode);
			
			}
		else{
			ChangCDEnabledNoCheck();
			//$('*[name="deviceId"]').select2().val('').trigger("change");
			//wade.tree.updateNodeCaEnable("tree",0,selectNode);
		}
	}
	
});

//触发出入口启用按钮
//$('input[name="enabled"]').on('change',function(){
$('input[name="enabled"]').click(function(){
	    var zTree = $.fn.zTree.getZTreeObj("tree");
		var selectNode=zTree.getSelectedNodes()[0];//选中的节点
		var selectId=selectNode.id;
		var str=selectId.substring(0,1);
		var entranceNode=zTree.getNodeByParam("id", selectNode.pId, null);
		var childNodes=zTree.getNodesByParam("pId", selectNode.pId, null);
		if(str=='c'){
			if($(this).is(':checked')){
				ChangEnabledCheck();
				//addTimeId(entranceNode);
				
				
			}
			else{
				//车道和出入口都不启用
				ChangEnabledNoCheck();
				
			}
		}
		
		
});
//点击出入口时判断出入口名称和启用
function judgeEntrance(node){
	$('input[name="entranceName"]').val(node.name);
	//控制启用的开关
	/*if(node.entranceenable==0){
		//不启用
		ChangEnabledNoCheck();
	}
	else{
		ChangEnabledCheck();
	}*/
	if(node.entranceenabled==0){
		//不启用
		ChangEnabledNoCheck();
	}
	else{
		ChangEnabledCheck();
	}
}
//点击车道时判断车道名称和启用
function judgeCarriaway(node){
	$('input[name="carriagewayName"]').val(node.name);
	/*if(node.carriagewayenable==0){
		//不启用
		ChangCDEnabledNoCheck();
	}
	else{
		ChangCDEnabledCheck();
	}*/
	if(node.carrawayenabled==0){
		//不启用
		ChangCDEnabledNoCheck();
	}
	else{
		ChangCDEnabledCheck();
	}
	
	
}
//触发tab事件
$('li[name="li1"]').click(function(){
	var zTree = $.fn.zTree.getZTreeObj("tree");
	 var selectNode=zTree.getSelectedNodes()[0];//选中的节点
	 var nodeId=selectNode.id;
	 var str=nodeId.substring(0,1);
	if(str=='c'){//车道节点
		var nodeIds=selectNode.pId;
		var entranceNode=zTree.getNodeByParam("id",nodeIds, null);
		var idval=$(this).attr('id');
		var Allnodes=zTree.getNodesByParam("pId",nodeIds, null);
		if(idval=='l1'){
			zTree.selectNode(Allnodes[0]);
			addCarryaway(Allnodes[0]);
			addDeviceId(Allnodes[0]);
			judgeCarriaway(Allnodes[0]);
			
		}
		if(idval=='l2'){
			zTree.selectNode(Allnodes[1]);
			addCarryaway(Allnodes[1]);
			addDeviceId(Allnodes[1]);
			judgeCarriaway(Allnodes[1]);
			
		}
		if(idval=='l3'){
			zTree.selectNode(Allnodes[2]);
			addCarryaway(Allnodes[2]);
			addDeviceId(Allnodes[2]);
			judgeCarriaway(Allnodes[2]);
			
		}
		if(idval=='l4'){
	       zTree.selectNode(Allnodes[3]);
	       addCarryaway(Allnodes[3]);
	       addDeviceId(Allnodes[3]);
	       judgeCarriaway(Allnodes[3]);
	      
		}
	}
	
});

//出入口启用
function ChangEnabledCheck(){
	$('.point-out').hide();
	$('input[name="enabled"]').prop('checked',true);
	$('input[name="entranceName"]').attr('disabled',false);
	$('input[name="awayenabled"]').attr('disabled',false);
	$('select[name="timeId"]').attr('disabled',false);
	$('input[name="custom1"]').attr('disabled',false);
	$('input[name="timeInterval"]').attr('disabled',false);
	$('button').attr('disabled',false);
}
//出入口不启用
function ChangEnabledNoCheck(){
	$('.point-out').hide();
	
	var name=$('input[name="entranceName"]').val();
	var hname=$('input[name="timeInterval"]').val();
	var timeid=$('select[name="timeId"] option:selected').val();
	$('input[name="hentranceName"]').val(name);
	$('input[name="htimeInterval"]').val(hname);
	$('input[name="htimeId"]').val(timeid);
	$('input[name="enabled"]').prop('checked',false);
	$('input[name="entranceName"]').attr('disabled',true);
	$('input[name="timeInterval"]').attr('disabled',true);
	$('select[name="timeId"]').attr('disabled',true);
	
}
//车道启用
function ChangCDEnabledCheck(){
	$('.point-out').hide();
	$('input[name="awayenabled"]').prop('checked',true);
	$('input[name="carriagewayName"]').attr('disabled',false);
	$('input[name="awayenabled"]').attr('disabled',false);
	$('select[name="model"]').attr('disabled',false);
	$('select[name="carriagewayType"]').attr('disabled',false);
	$('select[name="deviceId"]').attr('disabled',false);
	$('input[name="inOut"]').attr('disabled',false);
	$('input[name="custom3"]').attr('disabled',false);
	$('input[name="releaseFixed"]').attr('disabled',false);
	$('input[name="releaseTemp"]').attr('disabled',false);
	$('input[name="autoReleaseFixed"]').attr('disabled',false);
	$('input[name="autoReleaseTemp"]').attr('disabled',false);
	$('input[name="autoCharge"]').attr('disabled',false);
	$('input[name="carriageTimeInterval"]').attr('disabled',false);
}
//车道不启用
function ChangCDEnabledNoCheck(){
	$('.point-out').hide();
	var carriagewayName=$('input[name="carriagewayName"]').val();
	var carriagewayType=$('select[name="carriagewayType"] option:selected').val();
	//var deviceId=$('select[name="deviceId"]').select2().val();
	//alert(deviceId);
	//alert(carriagewayName);
	$('input[name="hcarriagewayName"]').val(carriagewayName);
	$('input[name="hcarriagewayType"]').val(carriagewayType);
	$('input[name="hcarriageTimeInterval"]').val($('input[name="carriageTimeInterval"]').val());
	//$('input[name="hdeviceId"]').val(deviceId);
	if($('input[name="inOut"]').is(':checked')){
		$('input[name="hinOut"]').val(1);
	}
	else{
		$('input[name="hinOut"]').val(0);
	}
	if($('input[name="custom3"]').is(':checked')){
		$('input[name="hcustom3"]').val(1);
	}
	else{
		$('input[name="hcustom3"]').val(0);
	}
	if($('input[name="releaseFixed"]').is(':checked')){
		$('input[name="hreleaseFixed"]').val(1);
	}
	else{
		$('input[name="hreleaseFixed"]').val(0);
	}
	if($('input[name="releaseTemp"]').is(':checked')){
		$('input[name="hreleaseTemp"]').val(1);
	}
	else{
		$('input[name="hreleaseTemp"]').val(0);
	}
	if($('input[name="autoReleaseFixed"]').is(':checked')){
		$('input[name="hautoReleaseFixed"]').val(1);
	}
	else{
		$('input[name="hautoReleaseFixed"]').val(0);
	}
	if($('input[name="autoReleaseTemp"]').is(':checked')){
		$('input[name="hautoReleaseTemp"]').val(1);
	}
	else{
		$('input[name="hautoReleaseTemp"]').val(0);
	}
	if($('input[name="autoCharge"]').is(':checked')){
		$('input[name="hautoCharge"]').val(1);
	}
	else{
		$('input[name="hautoCharge"]').val(0);
	}
	$('input[name="awayenabled"]').prop('checked',false);
	$('input[name="carriagewayName"]').attr('disabled',true);
	$('input[name="awayenabled"]').attr('disabled',false);
	$('select[name="model"]').attr('disabled',true);
	$('select[name="carriagewayType"]').attr('disabled',true);
	$('select[name="deviceId"]').attr('disabled',true);
	$('input[name="inOut"]').attr('disabled',true);
	$('input[name="custom3"]').attr('disabled',true);
	$('input[name="releaseFixed"]').attr('disabled',true);
	$('input[name="releaseTemp"]').attr('disabled',true);
	$('input[name="autoReleaseFixed"]').attr('disabled',true);
	$('input[name="autoReleaseTemp"]').attr('disabled',true);
	$('input[name="autoCharge"]').attr('disabled',true);
	$('input[name="carriageTimeInterval"]').attr('disabled',true);
}
//点击车道类型
$(' select[name="carriagewayType"]').change(function(){
	if($(this).find('option:selected').val()==3){
		$('#div1').hide();
		$('#div2').show();
		$('#in1').hide();
		$('#lfdiv').hide();
		//$('#lfdiv').hide();
		$('input[name="inOut"]').attr('disabled',true);
		$('input[name="autoReleaseFixed"]').attr('disabled',false);
		$('input[name="autoReleaseTemp"]').attr('disabled',false);
		$('input[name="autoCharge"]').attr('disabled',false);
		$('input[name="releaseFixed"]').attr('disabled',true);
		$('input[name="releaseTemp"]').attr('disabled',true);
		$('input[name="autoReleaseFixed"]').prop('checked',false);
		$('input[name="autoReleaseTemp"]').prop('checked',false);
		$('input[name="releaseFixed"]').prop('checked',false);
		$('input[name="releaseTemp"]').prop('checked',false);
		
	}
	else{
		$('#div2').hide();
		$('#div1').show();
		$('#in1').show();
		$('#lfdiv').show();
		$('input[name="inOut"]').attr('disabled',false);
		$('input[name="autoReleaseFixed"]').attr('disabled',true);
		$('input[name="autoReleaseTemp"]').attr('disabled',true);
		$('input[name="autoCharge"]').attr('disabled',true);
		$('input[name="releaseFixed"]').attr('disabled',false);
		$('input[name="releaseTemp"]').attr('disabled',false);
		$('input[name="releaseFixed"]').prop('checked',false);
		$('input[name="releaseTemp"]').prop('checked',false);
		$('input[name="autoReleaseFixed"]').prop('checked',false);
		$('input[name="autoReleaseTemp"]').prop('checked',false);
		$('input[name="autoCharge"]').prop('checked',false);
		$('input[name="inOut"]').prop('checked',false);
	
	}
	
});
//设备绑定
	$.ajax({
		 url: "/entrance/configservice.do",
	       dataType: 'json',
	       type:'post',
	       quietMillis: 250,
	       success: function (result) {
	    	   $('*[name="deviceId"]').select2({
	        		//dropdownParent:devClone,
	        		multiple:true,
	        		allowClear: true,
	        		//placeholder:'请选择',
	        		data:result
	        	});
	       	
	    	   
	       }
	});



//时间段绑定
	//window.onload=function(){
		
		$.ajax({
			 url: "/entrance/configtime.do",
		      dataType: 'json',
		      type:'post',
		      success: function (data) {
		      	var result=eval(data);
		      	if(result.length!=0){
		      		for(var i in result){
		      			if(result[i].id!=undefined){
		      				$('select[name="timeId"]').append('<option value="'+result[i].id+'">'+result[i].text+'</option>');	
		      			}
		          	
		          	}
		  		}
		      	
		      }
		});
	//}
	


//点击保存按钮
function SaveInfo(){
	var zTree = $.fn.zTree.getZTreeObj("tree");
	var selectNode=zTree.getSelectedNodes()[0];//选中的节点
	var selectNodeId=selectNode.id;
	var parentnode = zTree.getNodeByParam("id",selectNode.pId, null);//根据车道节点找到出入口节点
	var parentNodeId=parentnode.id;
	$('input[name="selectId"]').val(selectNodeId);//节点id
	$('input[name="parentId"]').val(parentNodeId);//出入口id
	var entranceName=$('input[name="entranceName"]').val();
	$('input[name="hdeviceId"]').val($('select[name="deviceId"]').select2().val());
	//alert($('input[name="hdeviceId"]').val(selectNode.deviceId));
	var carriagewayName=$('input[name="carriagewayName"]').val();
	
	var jsonObject = $('#form1').serializeObject(); 
	var formdata =  "condition="+JSON.stringify(jsonObject);
	//$('body').animate({scorllTop:0},1000);//回到顶端 
	$.ajax({
		 url: "/entrance/saveInfo.do",
	      dataType: 'json',
	      type:'post',
	      //async : false,//设置为同步操作就可以给全局变量赋值成功 
	      data:formdata,
	      success: function (data) {
	      	var result=eval(data);
	      	if(result.success){ 
	      		wade.libs.alert(result.msg);
	      		wade.tree.updateParentNode('tree',parentnode);
	      		wade.tree.updateChildNode('tree',selectNode);
	      		
	      	}
	      	else{
	      		 wade.libs.alert(result.msg,0);
	      	}
	      }
	});
	
}
//校验
$('#btnsmt').click(function(){
	var re=/^\d+(\.{0,1}\d+){0,1}$/;
	var entrancenName=$('input[name="entranceName"]').val().trim();
	var carriagewayName=$('input[name="carriagewayName"]').val().trim();
	var timeInterval=$('input[name="timeInterval"]').val();
	var carriageTimeInterval=$('input[name="carriageTimeInterval"]').val();
	if(entrancenName==''){
		var errMsg='出入口名称不能为空';
		$("#entranceNameNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
		return false;
	}
	if(carriagewayName==''){
		var errMsg='车道名称不能为空';
		$("#carriagewayNameNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
		return false;
	}
	if(entrancenName.length>32){
		var errMsg='出入口名称长度不能超过32个字';
		$("#entranceNameNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
		return false;
	}
	if(carriagewayName.length>32){
		var errMsg='车道名称长度不能超过32个字';
		$("#carriagewayNameNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
		return false;
	}
	if(timeInterval==''){
		var errMsg='间隔时间不能为空';
		$("#timeIntervalNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
		return false;
	}
	/*if(timeInterval>3600){
		var errMsg='间隔时间不能大于1小时';
		$("#timeIntervalNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
		return false;
	}*/
	if(timeInterval.length>16){
		var errMsg='间隔时间长度不能大于16';
		$("#timeIntervalNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
		return false;
	}
	if(carriageTimeInterval==''){
		var errMsg='间隔时间不能为空';
		$("#carriageTimeIntervalNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
		return false;
	}
	/*if(carriageTimeInterval>3600){
		var errMsg='间隔时间不能大于1小时';
		$("#carriageTimeIntervalNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
		return false;
	}*/
	if(carriageTimeInterval.length>16){
		var errMsg='间隔时间长度不能大于16';
		$("#carriageTimeIntervalNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
		return false;
	}
	if(!re.test(timeInterval)){
		var errMsg='请输入非负整数';
		$("#timeIntervalNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
		return false;
	}
	if(!re.test(carriageTimeInterval)){
		var errMsg='请输入非负整数';
		$("#carriageTimeIntervalNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
		return false;
	}
	SaveInfo();
});
//失去焦点
$('input[name="entranceName"]').blur(function(){
	//$("#entranceNameNotice").hide();
	
	var entrancenName=$('input[name="entranceName"]').val().trim();
	if(entrancenName==''){
		var errMsg='出入口名称不能为空';
		$("#entranceNameNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
	}
	if(entrancenName.length>32){
		var errMsg='出入口名称长度不能超32个字';
		$("#entranceNameNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
	}
});

$('input[name="entranceName"]').focus(function(){
	$("#entranceNameNotice").hide();
});

$('input[name="carriagewayName"]').blur(function(){
	//$("#carriagewayNameNotice").hide();
	var carriagewayName=$('input[name="carriagewayName"]').val().trim();
	if(carriagewayName==''){
		var errMsg='车道名称不能为空';
		$("#carriagewayNameNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
	}
	if(carriagewayName.length>32){
		var errMsg='车道名称长度不能超过32个字';
		$("#carriagewayNameNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
	}
});

$('input[name="carriagewayName"]').focus(function(){
	$("#carriagewayNameNotice").hide();
});



$('input[name="timeInterval"]').blur(function(){
	//$("#timeIntervalNotice").hide();
	var timeInterval=$('input[name="timeInterval"]').val();
	var re=/^\d+(\.{0,1}\d+){0,1}$/;
	if(timeInterval==''){
		var errMsg='间隔时间不能为空';
		$("#timeIntervalNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
	}
	/*if(timeInterval>3600){
		var errMsg='间隔时间不能大于1小时';
		$("#timeIntervalNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
	}*/
	if(timeInterval.length>16){
		var errMsg='间隔时间长度不能大于16';
		$("#timeIntervalNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
	}
	if(timeInterval!=''&&!re.test(timeInterval)){
		var errMsg='请输入非负整数';
		$("#timeIntervalNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
		return false;
	}
	
});
$('input[name="timeInterval"]').focus(function(){
	$("#timeIntervalNotice").hide();
});
/*$('input[name="timeInterval"]').change(function(){
	$("#timeIntervalNotice").hide();
});*/
$('input[name="carriageTimeInterval"]').blur(function(){
	//$("#carriageTimeIntervalNotice").hide();
	var carriageTimeInterval=$('input[name="carriageTimeInterval"]').val();
	var re=/^\d+(\.{0,1}\d+){0,1}$/;
	if(carriageTimeInterval==''){
		var errMsg='间隔时间不能为空';
		$("#carriageTimeIntervalNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
	}
	/*if(carriageTimeInterval>3600){
		var errMsg='间隔时间不能大于1小时';
		$("#carriageTimeIntervalNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
	}*/
	if(carriageTimeInterval.length>16){
		var errMsg='间隔时间长度不能大于16';
		$("#carriageTimeIntervalNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
	}
	if(carriageTimeInterval!=''&&!re.test(carriageTimeInterval)){
		var errMsg='请输入非负整数';
		$("#carriageTimeIntervalNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + errMsg + '</span>').show();
	}
});

$('input[name="carriageTimeInterval"]').focus(function(){
	$("#carriageTimeIntervalNotice").hide();
});

wade.tree.updateChildNode=function(treeId,node){//车道节点
	var treeObj = $.fn.zTree.getZTreeObj(treeId);
	var updateNode = node? node:treeObj.getSelectedNodes()[0];
	updateNode.name=$('*[name="carriagewayName"]').val();
	var carriageTimeInterval=$('*[name="carriageTimeInterval"]').val();
	updateNode.carriageTimeInterval=parseInt(carriageTimeInterval);
	updateNode.carryType=$('*[name="carriagewayType"]').val();
	if($('*[name="awayenabled"]').is(':checked')){
		updateNode.carrawayenabled=1;
	}else{
		updateNode.carrawayenabled=0;
	}
	if($('*[name="carriagewayType"]').val()==3){
		updateNode.releaseFixed=0;
      	updateNode.releaseTemp=0;
      	if($('*[name="autoReleaseFixed"]').is(':checked')){
      		//alert(1);
      		updateNode.autoFixed=1;
      	}else{
      		//alert(0);
      		updateNode.autoFixed=0;
      	}
      	if($('*[name="autoReleaseTemp"]').is(':checked')){
      		updateNode.autoTemp=1;
      	}else{
      		updateNode.autoTemp=0;
      	}
      	if($('*[name="autoCharge"]').is(':checked')){
      		updateNode.autoCharge=1;
      	}else{
      		updateNode.autoCharge=0;
      	}
	}else{
		//alert($('*[name="carriagewayType"]').val());
		updateNode.autoFixed=0;
		updateNode.autoTemp=0;
		if($('*[name="releaseFixed"]').is(':checked')){
      		updateNode.releaseFixed=1;
      	}else{
      		updateNode.releaseFixed=0;
      	}
      	if($('*[name="releaseTemp"]').is(':checked')){
      		updateNode.releaseTemp=1;
      	}else{
      		updateNode.releaseTemp=0;
      	}
    	if($('*[name="inOut"]').is(':checked')){
      		updateNode.inOut=1;
      	}else{
      		updateNode.inOut=0;
      	}
	}
	//更新军警车辆
	if($('*[name="custom3"]').is(':checked')){
		updateNode.custom3=1;
	}else{
		updateNode.custom3=0;
	}
	updateNode.deviceId=$('select[name="deviceId"]').select2().val();
	
	treeObj.updateNode(updateNode);
	//console.log(updateNode);
	
	
}

wade.tree.updateParentNode=function(treeId,node){//车场节点
	var treeObj = $.fn.zTree.getZTreeObj(treeId);
	var updateNode = node? node:treeObj.getSelectedNodes()[0];
	var timeInterval=$('*[name="timeInterval"]').val();
	updateNode.name=$('*[name="entranceName"]').val();
	updateNode.timeInterval=parseInt(timeInterval);
	if($('*[name="enabled"]').is(':checked')){
		updateNode.entranceenabled=1;
	}else{
		updateNode.entranceenabled=0;
	}
	updateNode.timeId=$('*[name="timeId"]').val();
	treeObj.updateNode(updateNode);
	
	
}
