<%@ page contentType="text/html; charset=UTF-8"%>
<div class="tab-pane fade in" id="park">
	<table id="parktable" class="table table-bordered table-striped">
		<thead>
			<tr>	
				<th>编号</th>
				<th>车场名称</th>
				<th>车场序号</th>
				<th>车位数量</th>
				<th>归属车场</th>
				<th>备注</th>
				<th></th>
				<th></th>		
			</tr>
		</thead>
		<tbody>						
		</tbody>
		<tfoot>						
		</tfoot>
	</table>
</div>
<%@ include file="park_dialog.jsp"%> 
<script src="/pages/pfm/parkinglot/park.js"></script>
<script type="text/javascript">
	function Buttonpermission(){
		<shiro:lacksPermission name="ParkingAdd">$("#parktable_wrapper a[name='wade.libs.datatable_add']").remove();</shiro:lacksPermission>
		<shiro:lacksPermission name="ParkingDelete">$("#parktable_wrapper a[name='wade.libs.datatable_del']").remove();</shiro:lacksPermission>
		<shiro:lacksPermission name="ParkingEdit">$("#parktable_wrapper a[name='wade.libs.datatable_edit']").remove();</shiro:lacksPermission>
		
	}
	function Seepermission(){
		<shiro:lacksPermission name="ParkingSee">$("#parktable tbody td").text("没有浏览权限");</shiro:lacksPermission>
	
	}
</script>