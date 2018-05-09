/**
 * [datatable报表插件封装]
 * @param  {[type} config [配置参数]
 * @return {[type]}        [无]
 */
wade.libs.report = function(config) {
	var colCount = config.columns.length;
	var colNameArr = new Array(colCount);
	for (var i = 0; i < colCount; i++) {
		colNameArr[i] = {
			"data" : config.columns[i]
		};
	}
	
	var table = $("#" + config.id).DataTable({
		language : { // 语言中文
			"url" : "/common/json/Chinese.json"
		},
		processing : true, // 显示加载进度
		serverSide : true, // 服务器分页
		ajax :  config.getUrl, // 请求数据地址
		columns : colNameArr, // 绑定数据列
		lengthMenu : [ [ 10, 25, 50, -1 ], [ 10, 25, 50, "全部" ] ],
		// autoFill:true,
		//	bAutoWidth:false ,	//是否自动结算列宽度
		scrollX:true,	//x轴滚动
		searching:false 
	});
}