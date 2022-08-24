<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<c:set var="baseURL" value="${pageContext.request.contextPath}"/> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Quản lý bộ phận</title>
</head>
<body>
	<div class="container-fluid px-4 mt-4">
		<div class="row">
			<div class="card bg-secondary">
				<h2 class="card-header">Thông tin bộ phận</h2>

				<div class="card-body">
					<p>
						<span class="fw-bold">Tên bộ phận: </span> ${division.name}
					</p>

					<p>
						<span class="fw-bold">Mô tả: </span>${division.description}</p>
					<p">
						<span class="fw-bold">Số lượng nhân viên: </span> <span class="userCount">${users.size()}</span></p>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="card bg-secondary mt-4">
				<h2 class="card-header">Danh sách nhân viên</h2>

				<div class="card-body">
					<div class="row">
						<div class="search col col-9">
							<!-- Search form -->
							<div class="md-form mt-0">
								<input class="form-control border-warning" type="text"
									placeholder="Search" aria-label="Search">
							</div>
						</div>

							<a href="${baseURL}/manager/new" class="col col-3 rounded btn btn-success">
								Thêm nhân viên <i class="fa-solid fa-plus"></i>
							</a>						
					</div>


					<div class="row">
						<table class="table mt-2">
							<thead>
								<tr>
									<th scope="col">Tên thành viên</th>
									<th scope="col">Mã nhân viên</th>
									<th scope="col">Email</th>
									<th scope="col">Hành động</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="users" items="${users}">
									<tr>
										<td>${users.name}</td>
										<td class="userId">${users.id}</td>
										<td>${users.email}</td>
										<td>
											<button class="btn-warning p-1 px-3 rounded">
												Xem chi tiết <i class="fa-regular fa-eye"></i>
											</button>
											<button class="btn-danger p-1 px-3 rounded deleteRow"
												data-bs-toggle="modal" data-bs-target="#deleteModal">
												Xóa <i class="fa-solid fa-trash-can"></i>
											</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>

			</div>

		</div>

		<!-- Delete Confirm Modal -->
		<div class="modal fade" id="deleteModal" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title text-danger" id="exampleModalLabel">Xóa nhân viên khỏi bộ phận?</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body text-secondary">Bạn có chắc chắn muốn xóa nhân viên này khỏi bộ phận của mình?</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Hủy</button>
						<button type="button" class="btn btn-primary" id="deleteBtn" data-bs-dismiss="modal">Xóa</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script>
	
		let userId;
		let currentRow;
		
		$(".deleteRow").click(function(e) {
			var userIdElement = $(this).parent().prev().prev();
			
			// When click the Delete button in reach row, update the userId and currentRow
			userId = userIdElement.html();
			currentRow = userIdElement.parent();
		})
	
		$("#deleteBtn").click(function(e) {
			if (userId) {
				$.ajax({
					url: '${pageContext.request.contextPath}/manager/' + userId + '/remove',
					type: 'POST',
					success: function() {
						currentRow.remove();
						$(".userCount").html($(".userCount").html() - 1);
					},
					error: function(response) {
						
					}
				})
			}
		})	
	</script>
</body>
</html>