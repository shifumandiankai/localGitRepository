<%@ page contentType="text/html; charset=UTF-8"%>
<div class="modal open" id="userModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="exampleModalLabel">用户信息</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal">
						<input type="hidden" name="bimUserId">
						<div class="form-group">		
							<label class="col-lg-2 control-label">用户名*:</label>			
							<div class="col-lg-4">
								<div id ="loginnameNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
								<input type="text" class="form-control"  name="userName" value="" maxlength='32'>
							</div>
							<label class="col-lg-2 control-label">姓名:</label>
							<div class="col-lg-4">
								<input type="text" class="form-control" name="eealName" value="" maxlength='32'>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label">密码*:</label>
							<div class="col-lg-4">
								<div id ="passwordNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
								<input type="password" class="form-control"   name="password" value="">
							</div>
							<label class="col-lg-2 control-label">确认密码*:</label>
							<div class="col-lg-4">
								<div id ="confirmpasswordNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
								<input type="password" class="form-control"  name="confirmPassword" value="">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label">手机号:</label>
							<div class="col-lg-4">
								<div id ="phoneNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
								<input type="text" class="form-control"   name="phone" value="">
							</div>
						<label class="col-lg-2 control-label">邮箱:</label>
							<div class="col-lg-4">
								<div id ="emailNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
								<input type="text" class="form-control"  name="email" value="">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label">到期时间:</label>
							<div class="col-lg-4">
									<div class="input-group date">
										<input type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  name="disableTime" class="form-control pull-right">
									</div>
							</div>
						<label class="col-lg-2 control-label">状态:</label>
							<div class="col-lg-4">
								<select class="form-control" name ="status"> </select>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label">角色:</label>
							<div class="col-lg-10">
								<select class="form-control" name="selRoleIds"> </select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-2 control-label">备注信息:</label>
							<div class="col-lg-10">
								 <textarea class="form-control" id="message-text" name ="note"></textarea>
							</div>
						</div>
						
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary"  id ="user_submit">保存</button>
			</div>
		</div>
	</div>
</div>

<div class="modal open" id="userEditModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="exampleModalLabel">用户信息</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal">
						<input type="hidden" name="bimUserId"> 
						<div class="form-group">		
							<label class="col-lg-2 control-label">用户名*:</label>			
							<div class="col-lg-4">
								<div id ="loginnameNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
								<input type="text" class="form-control"   name="userName" value="" maxlenght='32'>
							</div>
							<label class="col-lg-2 control-label">姓名:</label>
							<div class="col-lg-4">
								<input type="text" class="form-control" name="eealName" value="" maxlength='32'>
							</div>
						</div>					
				<!-- 		<div class="form-group">
							<label class="col-lg-2 control-label">密码*:</label>
							<div class="col-lg-4">
								<div id ="passwordNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
								<input type="password" class="form-control"   name="password" value="">
							</div>
							<label class="col-lg-2 control-label">确认密码*:</label>
							<div class="col-lg-4">
								<div id ="confirmpasswordNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
								<input type="password" class="form-control"  name="confirmPassword" value="">
							</div>
						</div>
 -->						
						<div class="form-group">
							<label class="col-lg-2 control-label">手机号:</label>
							<div class="col-lg-4">
								<div id ="phoneNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
								<input type="text" class="form-control"   name="phone" value="">
							</div>
						<label class="col-lg-2 control-label">邮箱:</label>
							<div class="col-lg-4">
								<div id ="emailNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
								<input type="text" class="form-control"  name="email" value="">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label">到期时间:</label>
							<div class="col-lg-4">
									<div class="input-group date">
										<input type="text"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  name="disableTime" class="form-control pull-right">
									</div>
							</div>
						<label class="col-lg-2 control-label">状态:</label>
							<div class="col-lg-4">
								<select class="form-control" name ="status"> </select>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label">角色:</label>
							<div class="col-lg-10">
								<select class="form-control" name="selRoleIds"> </select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-2 control-label">备注信息:</label>
							<div class="col-lg-10">
								 <textarea class="form-control" id="message-text" name ="note"  maxlength="252"></textarea>
							</div>
						</div>
						
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary"  id ="user_submit">保存</button>
			</div>
		</div>
	</div>
</div>

<div class="modal open" id="userChangePasswordModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="exampleModalLabel">修改密码</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal">
						<input type="hidden" name="bimUserId">
						<div class="form-group">
							<label class="col-lg-2 control-label">密码*:</label>
							<div class="col-lg-4">
								<div id ="passwordNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
								<input type="password" class="form-control"   name="password" value="">
							</div>
							<label class="col-lg-2 control-label">确认密码*:</label>
							<div class="col-lg-4">
								<div id ="confirmpasswordNotice" class="point-out" style="width: 348px;top:-38px; left:0px; display: none;"></div>
								<input type="password" class="form-control"  name="confirmPassword" value="">
							</div>
						</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary"  id ="user_submit">保存</button>
			</div>
		</div>
	</div>
</div>