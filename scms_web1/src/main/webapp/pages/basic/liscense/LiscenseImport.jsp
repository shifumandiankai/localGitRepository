<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>导入liscense信息</title>
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<%@ include file="/common/jsp/common_css.jsp"%>
</head>
<body>
<%@ page contentType="text/html; charset=UTF-8"%>
<div class="modal open in" id="exampleModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" style="display:block">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				
				<h4 class="modal-title" id="exampleModalLabel">授权文件上传</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" enctype="multipart/form-data">
				 <div id="tip">${requestScope.tipinfo}</div>
				<input name="liscense" type="file" />
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary">上传</button>
			</div>
		</div>
	</div>
</div>

<%@ include file="/common/jsp/common_js.jsp"%>
<script src="pages/basic/liscense/liscenseup.js"></script>
</body>
</html>