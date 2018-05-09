var wade = wade || {};
wade.role = wade.role || {};
var config2 = new Object();
var ztreeConf = $.extend({},
{
    id: "tree",
    url: "/report/carreport/gettree.do",
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
           onClick: nodeClick
           
           
        }
    }
});
wade.libs.tree(ztreeConf);//初始化树控件
config2.columns = new Array("carNumber","strdirection","personName","carTypeName","parkName","entranceName","eventTime","carriagewayName","boothName","deviceName","cardId","userName");
config2.id="normalexample";
config2.getUrl="/report/selectNormalListByPage.do";
config2.btns=[];
config2.search = new Array(
		{
			'type' : 'search_hidden',
			'placeholder' : null,
			'url' : null,
			'index' : 0
		}
		);
wade.libs.datatable(config2);

//点击节点触发事件
function nodeClick(event, treeId, treeNode) {
	var dev=$('#normalexample_wrapper');
	var val=treeNode.id;
	var str=val.substring(0,1);
	//alert(parkid);
	var zTree = $.fn.zTree.getZTreeObj("tree");
	if(str=='p'){
		
		
		var parkid=val.substring(1);
		$('*[name="parkId"]').val(parkid);
		var jsonuserinfo = $("#form1").serializeObject();
		var formdata =JSON.stringify(jsonuserinfo);
		//alert(formdata);
		dev.find('*[name="searchValue0_search_hidden"]').val(formdata);
		dev.find('*[name="searchValue0_search_hidden"]').trigger("keyup.DT");
		$('*[name="parkId"]').val("");
		$('*[name="entranceId"]').val("");
		$('*[name="carriagewayId"]').val("");
	}
	if(str=='e'){
		var entranceId=val.substring(1);
		var parentnode=zTree.getNodeByParam("id",treeNode.pId, null);//车道节点
		var parkval=parentnode.id;
		$('*[name="parkId"]').val(parkval.substring(1));
		$('*[name="entranceId"]').val(entranceId);
		var jsonuserinfo = $("#form1").serializeObject();
		var formdata =JSON.stringify(jsonuserinfo);
		//alert(formdata);
		dev.find('*[name="searchValue0_search_hidden"]').val(formdata);
		dev.find('*[name="searchValue0_search_hidden"]').trigger("keyup.DT");
		$('*[name="parkId"]').val("");
		$('*[name="entranceId"]').val("");
		$('*[name="carriagewayId"]').val("");
	}
	
	if(str=='c'){
		var carriagewayId=val.substring(1);
		var entrancenode=zTree.getNodeByParam("id",treeNode.pId, null);//出入口节点
		var parknode=zTree.getNodeByParam("id",entrancenode.pId, null);//车场节点
		var parkval=parknode.id;
		var entranceval=entrancenode.id;
		$('*[name="parkId"]').val(parkval.substring(1));
		$('*[name="entranceId"]').val(entranceval.substring(1));
		$('*[name="carriagewayId"]').val(carriagewayId);
		var jsonuserinfo = $("#form1").serializeObject();
		var formdata =JSON.stringify(jsonuserinfo);
		//alert(formdata)
		dev.find('*[name="searchValue0_search_hidden"]').val(formdata);
		dev.find('*[name="searchValue0_search_hidden"]').trigger("keyup.DT");
		$('*[name="parkId"]').val("");
		$('*[name="entranceId"]').val("");
		$('*[name="carriagewayId"]').val("");
		
	}
	
}