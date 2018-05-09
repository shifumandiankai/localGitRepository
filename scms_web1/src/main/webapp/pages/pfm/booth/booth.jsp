
<%@ page contentType="text/html; charset=UTF-8"%>
<div class="tab-pane fade in" id="booth">
	<table id="booth_table" class="table table-bordered table-striped">
		<thead>
			<tr>	
<!-- 								("pfmBoothId","boothCode","boothName","phone","parkName","timeName","consumeConfirmresult","centerChargeresult");			 -->
				<th>岗亭ID</th>
				<th>岗亭编号</th>
				<th>岗亭名称</th>
				<th>联系电话</th>
				<th>启用功能</th>
				<th>中央收费岗亭</th>
				<th>客户端IP</th>
				<th>备注信息</th>
				<!-- <th>启用功能结果</th>
				<th>中央收费岗亭结果</th> -->
			</tr>
		</thead>
		<tbody>						
		</tbody>
		<tfoot>						
		</tfoot>
	</table>
</div>
	<%@ include file="booth_dialog.jsp"%> 
	<%@ include file="booth_lprdialog.jsp"%> 
	<script src="/pages/pfm/booth/booth.js"></script>
	<script type="text/javascript">
		function Buttonpermission(){
			<shiro:lacksPermission name="StaffAdd">$("#booth_table_wrapper a[name='wade.libs.datatable_add']").remove();</shiro:lacksPermission>
			<shiro:lacksPermission name="StaffDelete">$("#booth_table_wrapper a[name='wade.libs.datatable_del']").remove();</shiro:lacksPermission>
			<shiro:lacksPermission name="StaffEdit">$("#booth_table_wrapper a[name='wade.libs.datatable_edit']").remove();</shiro:lacksPermission>
			
		}
		function Seepermission(){
			<shiro:lacksPermission name="StaffSee">$("#booth_table tbody td").text("没有浏览权限");</shiro:lacksPermission>
		
		}
	
	</script>