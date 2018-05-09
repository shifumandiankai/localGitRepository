<%@page import="com.wadejerry.scms.frame.constant.user.ConstSystem"%>
<%@page import="com.wadejerry.scms.frame.entity.LoginInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal open" id="permissionModel" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="exampleModalLabel">权限管理</h4>
			</div>
			<div class="modal-body">
			<div class="nav-tabs-custom">
								<ul class="nav nav-tabs"><!-- 子操作员可以查看权限，不能修改权限 -->
								<li class="active"><a href="#funauth" data-toggle="tab">功能权限</a></li>
							<c:if test="<%=LoginInfo.getSubSystem().contains(ConstSystem.SUB_SYSTEM_CODE_PFM) %>">
							<li><a href="#typeauth" data-toggle="tab">车辆类型权限</a></li>	
							<li><a href="#churukouauth" data-toggle="tab">出入口权限</a></li>
							</c:if>	
							<c:if test="<%=LoginInfo.getSubSystem().contains(ConstSystem.SUB_SYSTEM_CODE_ACM) %>"><li><a href="#areaauth" data-toggle="tab">门禁区域权限</a></li></c:if>	
							<c:if test="<%=LoginInfo.getSubSystem().contains(ConstSystem.SUB_SYSTEM_CPDE_BIM) %>"><li><a href="#deptauth" data-toggle="tab">部门权限</a></li></c:if>
								
						<%-- 	<c:if test="<%=LoginInfo.getSubSystem().contains(ConstSystem.SUB_SYSTEM_CODE_PFM) %>"><li><a href="#areaauth" data-toggle="tab">门禁区域权限</a></li></c:if>	
							<c:if test="<%=LoginInfo.getSubSystem().contains(ConstSystem.SUB_SYSTEM_CODE_PFM) %>"><li><a href="#deptauth" data-toggle="tab">部门权限</a></li></c:if> --%>
								</ul>
								<div class="tab-content">
									<!-- 功能权限 -->
									<div class="tab-pane active" id="funauth">
										  <ul id="authTree" class="ztree authTree"></ul>
									</div>
								
									
									<c:if test="<%=LoginInfo.getSubSystem().contains(ConstSystem.SUB_SYSTEM_CODE_PFM) %>">
									<!-- 停车场权限 -->
										<div class="tab-pane" id="typeauth">
									 		<ul id="typeTree" class="ztree typeTree"></ul>
										</div>
								
										<div class="tab-pane" id="churukouauth">
									 		<ul id="churukouTree" class="ztree churukouTree"></ul>
										</div>
									</c:if>	
									
									<c:if test="<%=LoginInfo.getSubSystem().contains(ConstSystem.SUB_SYSTEM_CODE_ACM) %>">
									<!-- 门禁区域权限 -->
										<div class="tab-pane" id="areaauth">
										
									 		<ul id="areaTree" class="ztree areaTree"></ul>
										</div>
									</c:if>	
									
									<c:if test="<%=LoginInfo.getSubSystem().contains(ConstSystem.SUB_SYSTEM_CPDE_BIM) %>">
									<!-- 一卡通部门权限 -->
										<div class="tab-pane" id="deptauth">
									 		<ul id="deptTree" class="ztree deptTree"></ul>
										</div>
									</c:if>	
								</div>
								<!-- /.tabcontent -->
							</div>
							<!-- /.nav-tabs-custom -->
			
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary">保存</button>
			</div>
		</div>
	</div>
</div>