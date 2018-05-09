<%@ page contentType="text/html; charset=UTF-8"%>
	<div class="tab-pane fade in" id="time">
		<table id="timetable" class="table table-bordered table-striped">
			<thead>
				<tr>
					<th>时段ID</th>
					<th>编号</th>
					<th>时段名称</th>		
					<th>启动星期</th>
				</tr>
			</thead>
			<tbody>						
			</tbody>
			<tfoot>						
			</tfoot>
		</table>
	</div>
	<%@ include file="time_dialog.jsp"%>
	<script src="/pages/pfm/time/time.js"></script>
	<script type="text/javascript">
		function Buttonpermission(){
			<shiro:lacksPermission name="PeriodAdd">
				$("#timetable_wrapper a[name='wade.libs.datatable_add']").remove();
			</shiro:lacksPermission>
			<shiro:lacksPermission name="PeriodDelete">
				$("#timetable_wrapper a[name='wade.libs.datatable_del']").remove();
				</shiro:lacksPermission>
			<shiro:lacksPermission name="PeriodEdit">
				$("#timetable_wrapper a[name='wade.libs.datatable_edit']").remove();
				</shiro:lacksPermission>
		}
		function Seepermission(){
			<shiro:lacksPermission name="PeriodSee">
				$("#timetable tbody td").text("没有浏览权限");
			</shiro:lacksPermission>
		
		}
	</script>
	<script src="/plugins/My97DatePicker/WdatePicker.js"></script>
