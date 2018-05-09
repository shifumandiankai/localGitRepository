var filterType;// p 按车场 e 按出入口过滤 c 按车道过滤
var filterId;
var amq = org.activemq.Amq;
// firstLoad 来自safety_index.jsp, 重复加载导致事件重复
if(firstLoad) { 
	 amq.init({ 
		    uri: '/amq', 
		    logging: true,
		    timeout: 3,
		    clientId:(new Date()).getTime().toString()
	
		  });
    amq.addListener("showCallback","topic://PfmMonitor",callback);
	firstLoad=false;
	
	
}
setInterval("heartbeat()",2*60*1000);//5分钟
function heartbeat(){
	$.ajax({

	url:"/heartbeat",
	dataType:'json',
	success:function(data){
		//alert(data.status);
	}
});
}
 
  var records = [];
  function callback(message)
  {
	  if(message.data==""){
		  return;
	  }   
	  var mqInfo = null;
	  //标识是否点击事件
	  var isClick =false;
	  //mq 消息 
	  if(typeof(message)=="object") {
		  mqInfo = JSON.parse(message.data);
	  } else {
		  //点击 事件
		  mqInfo = JSON.parse(message);
		  isClick = true;
	  }
	  //获取事件 类型
	  var recordType =judgeruorchu(mqInfo.recordType)
	  
	  //原始数据处理一下 
	  var carNum =(mqInfo.carNum == null?'无':mqInfo.carNum);
	  var chargeFee ='无';
	  var parkingduration ='无';
	  //没有车辆信息就没有收费金额和停车时长,平且是出场记录
	  if(carNum != '无' &&　recordType==2) {
		 chargeFee = (mqInfo.chargeFee == null ?'无':mqInfo.chargeFee+"元");
		 parkingduration = (mqInfo.parkingduration==null ? '无' :mqInfo.parkingduration+"分钟");
	  }
	  var evenetInfo =resultstatus(mqInfo.result.iRet,mqInfo.recordType);
	  if(recordType ==1) {
		  evenetInfo="入场-"+evenetInfo;
	  } else {
		  evenetInfo="出场-"+evenetInfo;
	  }
	  var typeName =((mqInfo.carTypeDto==null || mqInfo.carTypeDto.typeName== null)?'无':mqInfo.carTypeDto.typeName);
	  var eventTime = mqInfo.eventTime.substring(0,19).replace("T"," ");
	  var inTime = ((mqInfo.inRecordDto==null || mqInfo.inRecordDto.inTime==null) ?'无 ':mqInfo.inRecordDto.inTime.substring(0,19).replace("T"," "));
	  var boothName=mqInfo.boothDto.boothName;
	  var entranceName = ((mqInfo.entranceDto==null || mqInfo.entranceDto.entranceName==null)?"无":mqInfo.entranceDto.entranceName);
	  var userName = (mqInfo.userName==null?"无":mqInfo.userName);

	  //填充数据到显示框 
	  if(recordType == 1) { //入口
		 $("#inEntrance").text(entranceName);//入口出入口名
		 $("#inTime").text(eventTime);//事件时间
		 $("#inCarNum").text(carNum);//车牌号
		 if(mqInfo.photo == null) {
			 $("#inPhoto").attr("src","/photo/car_default.png");//默认图片
		 } else {
			 $("#inPhoto").attr("src","data:image/jpg;base64,"+mqInfo.photo);//照片 
		 }
		 $("#inEventInfo").text(resultstatus(mqInfo.result.iRet,mqInfo.recordType));//状态
		 $("#inBooth").text(boothName);//岗亭
		 $("#inCarType").text(typeName);//车辆类型
		 $("#inOperator").text(userName);//操作员
	  } else if(recordType == 2) { //出口 
		 $("#outEntrance").text(entranceName);//出口出入口名
		 $("#outTime").text(eventTime);//事件时间
		 $("#outCarNum").text(carNum);//车牌号
		 if(mqInfo.photo == null) {
			 $("#outPhoto").attr("src","/photo/car_default.png");	//照片
		 } else {
			 $("#outPhoto").attr("src","data:image/jpg;base64,"+mqInfo.photo);	//照片
		 }
		 $("#outEventInfo").text(resultstatus(mqInfo.result.iRet,mqInfo.recordType));//状态
		 $("#outBooth").text(boothName);//岗亭
		 $("#outCarType").text(typeName);//车辆类型
		 $("#outOperator").text(userName);//操作员
		 $("#outInTime").text(inTime);//入场时间
		 $("#tingcheshichang").text(parkingduration);//停车时长
		 $("#chargeFee").text(chargeFee);//收费金额
	  }
	  //填充数据到表格 
	  if(!isClick) {
		  if(records.length==9){
			  records.shift();
			  $("#tongxingitems tr:last").remove();
		  }
		  records.push(mqInfo);
		  $("#tongxingitems tbody").prepend("<tr class='cardata' data-info='"+message.data+"'><td>"
				  + evenetInfo+"</td><td>"
				  + carNum+"</td><td>"
				  + chargeFee +"</td><td>"
				  + typeName +"</td><td>"
				  + eventTime + "</td><td>"
				  + inTime +"</td><td>"
				  + parkingduration +"</td><td>"
				  + boothName +"</td><td>"
				  + entranceName +'</td><td>'
				  + userName+'</td></tr>');	
	  }
  }
  $("#tongxingitems").on("click","tr.cardata",function(){
	  var info = $(this).attr("data-info");
	  //当前点击行颜色标注 
	  
	  callback(info);
  });
  
  /**出入口初始化**/
/*  var ztreeConf = $.extend({},
  {
      id: "tree",
      expand:true,
      url: "/entrance/gettree.do?filter=1", //过滤未启用的出入口
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
function nodeClick(event, treeId, treeNode){//点击节点 记录根据什么过滤
	if(treeNode.id.indexOf('p')!=-1){
		filterType="p";
		filterId=treeNode.id.substring(1);
	}else if(treeNode.id.indexOf('e')!=-1){
		filterType="e";
		filterId=treeNode.id.substring(1);
	}else if(treeNode.id.indexOf('c')!=-1){
		filterType="c";
		filterId=treeNode.id.substring(1);
	}else{
		
	}
	
}
  wade.libs.tree(ztreeConf);//初始化树控件
*/  
  
/*  function displayInfo(data){
	 var recordType = judgeruorchu(data.recordType);
	 switch(recordType){
	 case 1:{
		 $("#inEntrance").text(data.entranceDto==null?"无":data.entranceDto.entranceName);//入口出入口名
		 $("#inTime").text(data.eventTime.replace('T',' ').substring(0,19));//事件时间
		 $("#inCarNum").text(data.carNum == null?'无':data.carNum );//车牌号
		 if(data.photo == null) {
			 $("#inPhoto").attr("src","/photo/car_default.png");//默认图片
		 } else {
			 $("#inPhoto").attr("src","data:image/jpg;base64,"+data.photo);//照片 
		 }
		 $("#inEventInfo").text(resultstatus(data.result.iRet));//状态
		 $("#inBooth").text(data.boothDto.boothName);//岗亭
		 $("#inCarType").text(data.carTypeDto== null ?"无" : data.carTypeDto.typeName);//车辆类型
		 $("#inOperator").text(data.userName);//操作员
	 }
	 break;
	 case 2:{
		 $("#outEntrance").text(data.entranceDto==null?"无出入口信息":data.entranceDto.entranceName);//出口出入口名
		 $("#outTime").text(data.eventTime.replace("T"," "));//事件时间
		 $("#outCarNum").text(data.carNum);//车牌号
		 if(data.photo == null) {
			 $("#outPhoto").attr("src","/photo/car_default.png");	//照片
		 } else {
			 $("#outPhoto").attr("src","data:image/jpg;base64,"+data.photo);	//照片
		 }
		 $("#outEventInfo").text(resultstatus(data.result.iRet));//状态
		 $("#outBooth").text(data.boothDto.boothName);//岗亭
		 $("#outCarType").text(data.carTypeDto.typeName);//车辆类型
		 $("#outOperator").text(data.userName);//操作员
		 $("#outInTime").text(data.inRecordDto==null?"无数据":data.inRecordDto.inTime.replace("T"," "));//入场时间
		 $("#tingcheshichang").text(data.parkingduration);//停车时长
		 $("#chargeFee").text(data.chargeFee);//收费金额
	 }break;
	 case 3:break;
	 default:break;
	 }
	 return recordType;
  }*/
  
  
  //判断是入场还是出场记录 1 入场 2 出场 3其他
  function judgeruorchu(recordType){
	  if(recordType==20002
			  ||recordType==20004
			  ||recordType==40001
			  ||recordType==40002
			  ||recordType==40004
			  ||recordType==20003
			  ||recordType==20006
			  ||recordType==20007 
			  ||recordType==40003){
		  return 1;
	  }
	  else if(recordType==30001
			  ||recordType==30003
			  ||recordType==30002
			  ||recordType==30004
			  ||recordType==50002
			  ||recordType==50003
			){
		  return 2;
	  }
	  else{//监控不显示的情况 CONSUME_QUERY_RESULT = 50001(监控不显示); //收费金额查询
		  return 3;
	  }
  }
  
  function resultstatus(iret,recordType){
	  var result;
	  switch(iret){
	  case 0:result="车辆放行成功";break;
	  case 1:result="车辆类型不存在";break;
	  case 2:result="不在通行时段内";break;
	  case 3:result="黑名单车辆";break;
	  case 4:result="车辆未启用";break;
	  case 5:result="账户不存在";break;
	  case 6:result="余额不足";break;
	  case 7:result="到期";break;
	  case 8:result="临时车无入场记录异常收费";break;
	  case 9:result="开闸失败";break;
	  case 10:result="临时车保期规则无效";break;
	  case 11:result="设备未绑定出入口";break;
	  case 12:result="车辆请求放行";break;
	  case 13:result="入场未出";break;
	  case 14:result="未入场";break;
	  case 15:result="包期车辆异常放行余额不足";break;
	  case 16:result="储值车异常放行余额不足";break;
	  case 17:result="车位已满";break;
	  default:break;
	  }
	  if(recordType =="50003" || recordType=="40001") {
		  result=result+"(手动开闸)";
	  }
	  return result;
  }
  
/*  function filter(record){//过滤记录 如果需要显示则返回此记录 否则返回false  record类型是mq对象
	  var ifreturn=false;
	  //先根据出入口进行权限判断
	  if(record.entranceDto==null|record.carriagewayDto==null){
		  return true;
	  }
	  var entranceid = 'e'+record.entranceDto.pfmEntranceId;
	  var treeObj = $.fn.zTree.getZTreeObj("tree");
	  var node = treeObj.getNodeByParam("id", entranceid, null);
	  if(node==null||!node.hasPermission){
		  return false;
	  }
	  
	  //然后根据选择的车场车道或者出入口过滤
	  switch(filterType){
	  case 'p':{if(record.parkDto.parkId==filterId){ifreturn=true;}}break;
	  case 'e':{if(record.entranceDto.pfmEntranceId==filterId){ifreturn=true;}}break;
	  case 'c':{if(record.carriagewayDto.carriagewayId==filterId){ifreturn=true;}}break;
	  case undefined:{ifreturn=true;}break;
	  default:break;
	  }
	  if(ifreturn){
		  return record;
	  }
	  else{
		 return false;
	  }
	  
  }*/
  