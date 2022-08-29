<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglib.jsp"%>

<c:url var="StaffAPI" value="/api/staff"/>
<c:url var="StaffURL" value="/admin/staff/list"/>
<c:url var="CreateStaffURL" value="./edit" />
<c:url var="SearchStaffURL" value="./search" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách nhân viên</title>
</head>
<body>
<c:if test="${not empty message}">
	<div class="alert alert-${alert}" id="messageAndAlert">
		${contentMessage}
	</div>
</c:if>
<c:if test="${empty message}">
	<c:if test="${param.message != null}">
		<div class="alert alert-${param.alert}" id="messageAndAlert">
			${contentMessage}
		</div>
	</c:if>
</c:if>
	<div class="widget-box table-filter">
		<div class="table-btn-controls">
			<div class="pull-right tableTools-container">
				<h1 class="text-center" style="color: yellowgreen;">Danh sách nhân viên</h1>
				<form:form modelAttribute="searchAdvanceRequest" method="POST" style="padding: 20px;" action="/admin/staff/searchAdvance">
					<form:label path="holot">Họ lót:</form:label>
					<form:input path="holot" type="text" id="inputholot" style="color: black"/>
					
					<form:label path="ten">Tên:</form:label>
					<form:input path="ten" type="text" id="inputten" style="color: black"/>
					
					<form:label path="taikhoan">Tài khoản:</form:label>
					<form:input path="taikhoan" type="text" id="inputtaikhoan" style="color: black"/>
					<br />
					
					<form:label path="trangthai">Trạng thái:</form:label>
					<form:select id="trangthai" path="trangthai">
				  	 	<form:option value="" label="--- Chọn trạng thái ---" />
				  	 	<form:options items="${statuses}" />
					</form:select>
					
					<form:label path="loai">Loại:</form:label>
					<form:select id="loai" path="loai">
				  	 	<form:option value="" label="--- Chọn loại nhân viên ---" />
				  	 	<form:options items="${categoryStaffs}" />
					</form:select>
					<br />
					
					<form:label path="trinhdo">Trình độ:</form:label>
					<form:select id="trinhdo" path="trinhdo">
				  	 	<form:option value="" label="--- Chọn trình độ ---" />
				  	 	<form:options items="${levels}" />
					</form:select>
					
					<form:label path="chucvu">Chức vụ:</form:label>
					<form:select id="chucvu" path="chucvu">
				  	 	<form:option value="" label="--- Chọn chức vụ ---" />
				  	 	<form:options items="${positions}" />
					</form:select>
					<br />
					
					<form:label path="donvi">Đơn vị:</form:label>
					<form:select id="donvi" path="donvi">
				  	 	<form:option value="" label="--- Chọn đơn vị ---" />
				  	 	<form:options items="${units}" />
					</form:select>
					<br />
					<button class="btn btn-primary" type="submit">
						Tìm kiếm <i class="fas fa-search"></i>
					</button>
				</form:form>
				
				<div class="dt-buttons btn-overlap btn-group" style="float: right; padding-bottom: 10px;">
					
					<a flag="info"
						class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
						data-toggle="tooltip" title='Thêm nhân viên' href='${CreateStaffURL}'>
						<span> <i class="fa fa-plus-circle bigger-110 purple"></i>
					</span>
					</a>
					<button id="btnDelete" type="button"
						onclick="warningBeforeDelete()"
						class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
						data-toggle="tooltip" title='Xóa nhân viên' style="border-left: 1px solid white" disabled>
						<ion-icon name="trash-outline"></ion-icon>
					</button>
				</div>
			</div>
		</div>
	</div>
<form action="<c:url value='/admin/staff/list'/>" id="formSubmit" method="get">
	<div class="table-responsive">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>All<br> <input type="checkbox" onClick="checkAll(this)"></th>
					<th>Họ lót</th>
					<th>Tên</th>
					<th>Tài khoản</th>
					<th>SĐT</th>
					<th>Email</th>
					<th>Trạng thái</th>
					<th>Loại NV</th>
					<th>Trình độ</th>
					<th>Đơn vị</th>
					<th>Chức vụ</th>
					<th>Thao tác</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${list_staff.results}">
					<tr>
						<td><input type="checkbox" id="checkbox_${item.manv}"
							value="${item.manv}"></td>
						<td>${item.holot}</td>
						<td>${item.ten}</td>
						<td>${item.taikhoan}</td>
						<td>${item.mobile}</td>
						<td>${item.email}</td>
						<td>${item.trangthai}</td>
						<td>${item.loai}</td>
						<td>${item.trinhdo}</td>
						<td>${item.donvi}</td>
						<td>${item.chucvu}</td>
						<td style="text-align: center;">
							<c:url var="updateStaffURL" value="./edit">
								<c:param name="manv" value="${item.manv}"></c:param>
							</c:url>
							<a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
								title="Cập nhật nhân viên" href='${updateStaffURL}'> <ion-icon
									name="create-outline" title="Cập nhật nhân viên"></ion-icon>
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<ul style="padding-bottom: 20px;" class="pagination" id="pagination"></ul>
		<input type="hidden" value="" id="page" name="page"/>
		<input type="hidden" value="" id="limit" name="limit"/>	
	</div>
</form>
<script type="text/javascript">
	var totalPages = ${list_staff.totalPage};
	var currentPage = ${list_staff.page};
	var limit = ${list_staff.limit};
    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: 10,
            startPage: currentPage,
            onPageClick: function (event, page) {
            	if (currentPage != page) {
        			$('#limit').val(limit);
        			$('#page').val(page);
        			$('#formSubmit').submit();
            	}
            }
        }).on('page', function (event, page) {
            console.info(page + ' (from event listening)');
        });
    });
    
    $("tbody input[type=checkbox]").on("change", function(){
    	  if ($("tbody input[type=checkbox]:checked").length > 0)
    	  {
    	      $("#btnDelete").removeAttr('disabled','disabled');
    	      // console.log($("tbody input[type=checkbox]:checked").length);
    	  }
    	  else
    	  {
    	      $("#btnDelete").attr('disabled','disabled');
    	  }
    });
    
    function checkAll(source) {
    	  checkboxes = $('tbody input[type=checkbox]');
    	  for(var i=0, n=checkboxes.length;i<n;i++) {
    	    checkboxes[i].checked = source.checked;
    	  }
    	  if ($("tbody input[type=checkbox]:checked").length > 0)
    	  {
    	      $("#btnDelete").removeAttr('disabled','disabled');
    	      console.log($("tbody input[type=checkbox]:checked").length);
    	  }
    	  else
    	  {
    	      $("#btnDelete").attr('disabled','disabled');
    	  }
    }
    
    function warningBeforeDelete() {
		swal({
			  title: "Xác nhận xóa",
			  text: "Bạn có chắc chắn muốn xóa hay không",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonClass: "btn-success",
			  cancelButtonClass: "btn-danger",
			  confirmButtonText: "Xác nhận",
			  cancelButtonText: "Hủy bỏ",
			}).then(function(isConfirm) {
			  if (isConfirm.value == true) {
				  	// get data
					var ids = $('tbody input[type=checkbox]:checked').map(function () {
			            return $(this).val();
			        }).get();
					
					// call API DELETE, boot thẳng vào mảng luôn, không cần convert Object, Spring mà đương nhiên mạnh
					deleteNew(ids);
				// console.log('ok!');
			  }
			});
	}
	
	function deleteNew(data) {
        $.ajax({
            url: '${StaffAPI}',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
            	window.location.href = "${StaffURL}?message=delete_film_success&alert=success";
            },
            error: function (error) {
            	window.location.href = "${StaffURL}?message=delete_film_fail&alert=danger";
            }
        });
	}
	
	$('#messageAndAlert').fadeOut(5000);
</script>
</body>
</html>