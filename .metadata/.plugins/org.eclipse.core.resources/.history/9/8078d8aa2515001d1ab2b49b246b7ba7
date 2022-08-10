<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Staff</title>
</head>
<body>
	<div class="panel panel-primary" style="padding: 20px;">
		<div class="panel-heading">
		
			<c:if test="${empty staffDTO.manv}">
					<h3 class="panel-title" style="color: yellowgreen; padding-bottom: 20px;">Thêm mới nhân viên</h3>
			</c:if>
			<c:if test="${not empty staffDTO.manv}">
					<h3 class="panel-title" style="color: yellowgreen; padding-bottom: 20px;">Cập nhật nhân viên</h3>
			</c:if>
		
		</div>
		<div class="panel-body">
			<c:if test="${not empty message}">
				<div class="alert alert-${alert}" id="messageAndAlert">
					<spring:message code="${message}" />
				</div>
			</c:if>
			<form:form modelAttribute="staffDTO" method="POST" enctype="multipart/form-data">
				<form:errors cssClass="error" />
				<div class="form-group">
					<form:label path="holot">Họ lót:</form:label>
					<form:input path="holot" type="text" class="form-control"
						id="inputholot" placeholder="Nguyễn Văn" />
					<form:errors path="holot" cssClass="error" />
				</div>
				<div class="form-group">
					<form:label path="ten">Tên:</form:label>
					<form:input path="ten" type="text" class="form-control"
						id="inputten" placeholder="A" />
					<form:errors path="ten" cssClass="error" />
				</div>
				<div class="form-group">
					<form:label path="taikhoan">Tài khoản</form:label>
					<form:input path="taikhoan" type="text" class="form-control"
						id="inputtaikhoan" />
					<form:errors path="taikhoan" cssClass="error" />
				</div>
				<div class="form-group">
					<form:label path="matkhau">Mật khẩu:</form:label>
					<form:input path="matkhau" type="text" class="form-control"
						id="inputmatkhau" />
					<form:errors path="matkhau" cssClass="error" />
				</div>
				<div class="form-group">
					<form:label path="loai">Loại:</form:label>
					<form:select id="loai" path="loai">
				  	 	<form:option value="" label="--- Chọn loại nhân viên ---" />
				  	 	<form:options items="${categoryStaffs}" />
					</form:select>
					<form:errors path="loai" cssClass="error" />
				</div>
				<div class="form-group">
					<form:label path="trinhdo">Trình độ:</form:label>
					<form:select id="trinhdo" path="trinhdo">
				  	 	<form:option value="" label="--- Chọn trình độ ---" />
				  	 	<form:options items="${levels}" />
					</form:select>
					<form:errors path="trinhdo" cssClass="error" />
				</div>
				<div class="form-group">
					<form:label path="mobile">Điện thoại</form:label>
					<form:input path="mobile" type="text" class="form-control"
						id="inputmobile" />
					<form:errors path="mobile" cssClass="error" />
				</div>
				<div class="form-group">
					<form:label path="email">Email:</form:label>
					<form:input path="email" type="email" class="form-control"
						id="inputemail" />
					<form:errors path="email" cssClass="error" />
				</div>
				
				<form:hidden path="manv" id="manv"/>
				<c:if test="${not empty staffDTO.manv}">
					<button type="submit" class="btn btn-primary">Cập nhật</button>
				</c:if>
				<c:if test="${empty staffDTO.manv}">
					<button type="submit" class="btn btn-primary">Thêm mới</button>
				</c:if>
				<button class="btn btn-danger" type="reset">
					<i class="ace-icon fa fa-undo bigger-110"></i>
						Hủy
				</button>
			</form:form>
		</div>
	</div>
<script>
	var editor = ""; // data cua editor khi thay the cho textarea
	$(document).ready(function(){
		editor = CKEDITOR.replace('content');
	});
	$('#messageAndAlert').fadeOut(5000);
</script>
</body>
</html>