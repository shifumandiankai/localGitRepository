var configspecialNumber = $.extend({},{
	btns: new Array({'id':'10001','name':'add','fun':null},	//需要显示的按钮
			{'id':'10002','name':'edit',"fun":null},
			{'id':"10003",'name':'del',"fun":null}),
			search:new Array({
		        'type': 'search',
		        'placeholder': '车牌号',
		        'url': null,
		        'index': 0
		    }), //查询条件
			id:"special",	//dataTable ID
			addDiv:"configspecialNumberModal", //新增弹出的DIV ID
			editDiv:"configspecialNumberModal",	//编辑弹出的DIV ID
			getUrl:"/special/getspecialInfo.do",	//获取数据请求路径
			delUrl:"/special/delspecialInfo.do",	//删除数据请求路径
			saveUrl:"/special/addspecialInfo.do",	//保存数据请求路径
			columns: new Array("carNumber","insertTime","updateTime","userName","note","pfmSpecialCarId"),
			defs:new Array("pfmSpecialCarId"),
			add_shown:showSpecialNumberModal, //弹出新增或编辑对话框后执行的方法,
});	
wade.libs.datatable(configspecialNumber);	//初始化datatable

function showSpecialNumberModal(devClone,flag,sel_obj){
	
		devClone.find('input[name=carNumber]').on('change keyup',
    	    function() {
    	        devClone.find("#carNumberNotice").hide();
    	    });
    	    devClone.find('input[name=carNumber]').on("blur",
    	    function() {
    	        var val = $(this).val();
    	        if(val!='')
    	        verifyCarNumber(val);

    	    });
    		devClone.find('[name=note]').on('change keyup',
    	    	    function() {
    	    	        devClone.find("#noteNotice").hide();
    	    	    });
    	    	    devClone.find('[name=note]').on("blur",
    	    	    function() {
    	    	    	 
    	    	        if(devClone.find("[name=note]").val().length>50){
    	    	        	devClone.find('.point-out').hide();
    	    	            devClone.find("#noteNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + "请不要超过50个字符" + '</span>').show();

    	    			}

    	    	    });
    	    function verifyCarNumber(data) {
    	        var result = checkCarNum(data);
    	        
    	        if (result == 100) {

    	            if (flag == 0) {
    	                sel_obj = {};
    	                sel_obj.pfmSpecialCarId = null
    	            }
    	            $.ajax({
    	                type: 'post',
    	                url: '/special/validatecarnum',
    	                data: "carNum=" + data + "&flag=" + flag + "&pfmSpecialCarId=" + sel_obj.pfmSpecialCarId,
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
    	    devClone.find(".btn-primary").on("click",function(event) {
    			
    			var val1 = devClone.find('input[name=carNumber]').val();
    			if (!verifyCarNumber(val1)) {
    				event.stopImmediatePropagation(); // 阻止提交数据
    				return false;
    			}
    			if(devClone.find("[name=note]").val().length>50){
    	            devClone.find("#noteNotice").html('<span class="point-box errorbgTop dropdown"  ><i class="sprite icon_arrow_up2 pa"></i>' + "请不要超过50个字符" + '</span>').show();

    				event.stopImmediatePropagation(); // 阻止提交数据
    			}
    			
    			
    			return true;
    		});
}