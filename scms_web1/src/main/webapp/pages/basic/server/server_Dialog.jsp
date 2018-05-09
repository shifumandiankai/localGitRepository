<%@page import="com.wadejerry.scms.frame.constant.user.ConstSystem"%>
<%@page import="com.wadejerry.scms.frame.entity.LoginInfo"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal open" id="exampleModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel">
	<div class="modal-dialog" role="document" style="width: 700px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="exampleModalLabel">服务器</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal">
					
					<div class="server-wizard">
					<div id="mutablewizard">
						<ul class="server-wizard-ul">
					
							<li class="server-wizard-li"><a class="selected" isdone="1"
								href="#href1"><span>1、服务器类型</span></a></li>
							<li class="server-wizard-li"><a class="disabled" isdone="0"
								href="#href2"><span>2、ip地址配置</span></a></li>
						</ul>
						</div>
						<div class="step-container">
						
							<div class="step1"><!-- 保持不变 -->
								<div class="servers">
									<table>
										<tr>
											<input type="hidden" name="serverType"><!-- 服务器类型记录 -->
											<%
											boolean pfm=false;
											boolean acm=false;
											for (Integer temp :LoginInfo.getSubSystem() ){
												
												if(temp==ConstSystem.SUB_SYSTEM_CODE_PFM){
													pfm=true;
												}else if(temp==ConstSystem.SUB_SYSTEM_CODE_ACM){
													acm=true;
												}
						
											}%>
											<c:if test="<%=pfm %>"><td class="server-type"><i class="server-1-1"></i><br>停车场服务器</td></c:if>
											<c:if test="<%=acm %>"><td class="server-type"><i class="server-1-2"></i><br>门禁服务器</td></c:if>
											<td></td>
										</tr>
										<tr>
											<td colspan="2"><div class="line"></div>
											<td>
										</tr>
									</table>
								</div>
							</div>
							<div id="mutablesteps">
							<div class="step2" style="display: none;">
								<div class="serverForm">
									<div class="form-group">
										<label for="ipName" class="control-label col-lg-3">服务器名称 *</label>
										<input type="hidden" name="serverId">
										<div class="col-lg-8" style="position: relative;">
											<div id="serverNameNotice" class="point-out"
												style="width: 348px; top: -38px; left: 0px; display: none;"></div>
											<input id="ipName" class="form-control" type="text" name="serverName" />
										</div>
									</div>
									<div class="form-group">
										<label for="ip" class="control-label col-lg-3">ip地址*</label>
										<div class="col-lg-8" style="position: relative;">
											<div id="ipNotice" class="point-out"
												style="width: 348px; top: -38px; left: 0px; display: none;"></div>
											<input id="ip" class="form-control" type="text" name="ip" />
										</div>
									</div>
									<div class="form-group">
										<label for="port" class="control-label col-lg-3">端口号*</label>
										<div class="col-lg-8" style="position: relative;">
											<div id="portNotice" class="point-out"
												style="width: 348px; top: -38px; left: 0px; display: none;"></div>
											<input id="port" class="form-control" type="text" name="port" />
										</div>
									</div>
									<div class="form-group">
										<label for="netZone" class="control-label col-lg-3">网域*</label>
										<div class="col-lg-8" style="position: relative;">
											<select id="netZone" class="form-control"  name="netZone" >
											</select>
										</div>
									</div>
									<div class="form-group">
										<label for="parkId" class="control-label col-lg-3">车场*</label>
										<div class="col-lg-8" style="position: relative;">
											<select id="parkId" class="form-control"  name="parkId" >
											</select>
										</div>
									</div>
								</div>
							</div>
							</div>
						</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default prev disabled">上一步</button>
				<button type="button" class="btn btn-primary next">下一步</button>
				<button type="button" class="btn btn-primary finish disabled">完成</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>

<!-- 停车场服务器 -->
<script id="wiz-park"  type="text/html">
							<ul class="server-wizard-ul">
					
							<li class="server-wizard-li"><a class="selected" isdone="1"
								href="#href1"><span>1、服务器类型</span></a></li>
							<li class="server-wizard-li"><a class="disabled" isdone="0"
								href="#href2"><span>2、ip地址配置</span></a></li>
						</ul>


</script>
<script id="stp-park"  type="text/html">
							<div class="step2" style="display: none;">
								<div class="serverForm">
									<div class="form-group">
										<label for="ipName" class="control-label col-lg-3">服务器名称 *</label>
										<input type="hidden" name="serverId">
										<div class="col-lg-8" style="position: relative;">
											<div id="serverNameNotice" class="point-out"
												style="width: 348px; top: -38px; left: 0px; display: none;"></div>
											<input id="ipName" class="form-control" type="text" name="serverName" />
										</div>
									</div>
									<div class="form-group">
										<label for="ip" class="control-label col-lg-3">IP地址*</label>
										<div class="col-lg-8" style="position: relative;">
											<div id="ipNotice" class="point-out"
												style="width: 348px; top: -38px; left: 0px; display: none;"></div>
											<input id="ip" class="form-control" type="text" name="ip" />
										</div>
									</div>
									<div class="form-group">
										<label for="port" class="control-label col-lg-3">端口号*</label>
										<div class="col-lg-8" style="position: relative;">
											<div id="portNotice" class="point-out"
												style="width: 348px; top: -38px; left: 0px; display: none;"></div>
											<input id="port" class="form-control" type="text" name="port" />
										</div>
									</div>
									<div class="form-group">
										<label for="netZone" class="control-label col-lg-3">网域*</label>
										<div class="col-lg-8" style="position: relative;">
											<select id="netZone" class="form-control"  name="netZone" >
											</select>
										</div>
									</div>
									<div class="form-group">
										<label for="parkId" class="control-label col-lg-3">车场*</label>
										<div class="col-lg-8" style="position: relative;">
											<select id="parkId" class="form-control"  name="parkId" >
											</select>
										</div>
									</div>
								</div>
							</div>
</script>
<!-- 门禁服务器 -->
<script id="wiz-menjin"  type="text/html">
						<ul class="server-wizard-ul">
					
							<li class="server-wizard-li"><a class="selected" isdone="1"
								href="#href1"><span>1、服务器类型</span></a></li>
							<li class="server-wizard-li"><a class="disabled" isdone="0"
								href="#href2"><span>2、ip地址配置</span></a></li>
						</ul>


</script>
<script id="stp-menjin"  type="text/html">
							<div class="step2" style="display: none;">
								<div class="serverForm">
									<div class="form-group">
										<label for="ipName" class="control-label col-lg-3">服务器名称 *</label>
										<input type="hidden" name="serverId">
										<div class="col-lg-8" style="position: relative;">
											<div id="serverNameNotice" class="point-out"
												style="width: 348px; top: -38px; left: 0px; display: none;"></div>
											<input id="ipName" class="form-control" type="text" name="serverName" />
										</div>
									</div>
									<div class="form-group">
										<label for="ip" class="control-label col-lg-3">IP地址*</label>
										<div class="col-lg-8" style="position: relative;">
											<div id="ipNotice" class="point-out"
												style="width: 348px; top: -38px; left: 0px; display: none;"></div>
											<input id="ip" class="form-control" type="text" name="ip" />
										</div>
									</div>
									<div class="form-group">
										<label for="port" class="control-label col-lg-3">端口号*</label>
										<div class="col-lg-8" style="position: relative;">
											<div id="portNotice" class="point-out"
												style="width: 348px; top: -38px; left: 0px; display: none;"></div>
											<input id="port" class="form-control" type="text" name="port" />
										</div>
									</div>
									<div class="form-group">
										<label for="netZone" class="control-label col-lg-3">网域*</label>
										<div class="col-lg-8" style="position: relative;">
											<select id="netZone" class="form-control"  name="netZone" >
											</select>
										</div>
									</div>
									
								</div>
							</div>


</script>

