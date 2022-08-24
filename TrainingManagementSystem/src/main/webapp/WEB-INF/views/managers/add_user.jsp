<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Thêm nhân viên</title>
</head>
<body>
	<div class="container-fluid px-4 mt-4">
		<div class="row">
			<div class="card bg-secondary mt-4">
				<h2 class="card-header">Danh sách nhân viên mới</h2>

				<div class="card-body">
					<div class="row">
						<div class="search col col-12">
							<!-- Search form -->
							<div class="md-form mt-0">
								<input class="form-control border-warning" type="text"
									placeholder="Search" aria-label="Search">
							</div>
						</div>
					</div>


					<div class="row">
						<table class="table mt-2">
							<thead>
								<tr>
									<th scope="col">Tên nhân viên</th>
									<th scope="col">Ngày tham gia</th>
									<th scope="col">Mã nhân viên</th>
									<th scope="col">Email</th>
									<th scope="col">Hành động</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="users" items="${users}">
									<tr>
										<td>${users.name}</td>
										<td>${users.createdAt.getDayOfMonth()} - ${users.createdAt.getMonthValue()} - ${users.createdAt.getYear()}</td>
										<td class="userId">${users.id}</td>
										<td>${users.email}</td>
										<td>
											<button class="btn-success p-1 px-3 rounded addRow"
												data-bs-toggle="modal" data-bs-target="#addModal">
												Thêm nhân viên <i class="fa-solid fa-user-plus"></i>
											</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>

			<!-- Delete Confirm Modal -->
			<div class="modal fade" id="addModal" tabindex="-1"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title text-success" id="exampleModalLabel">Thêm
								nhân viên</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body text-secondary">Bạn có chắc chắn muốn thêm nhân viên
							này vào bộ phận của mình?</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Hủy</button>
							<button type="button" class="btn btn-success" id="addBtn"
								data-bs-dismiss="modal">Thêm</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script>
		let userId;
		let currentRow;

		$(".addRow").click(function(e) {
			var userIdElement = $(this).parent().prev().prev();

			// When click the Delete button in reach row, update the userId and currentRow
			userId = userIdElement.html();
			currentRow = userIdElement.parent();
		})

		$("#addBtn").click(function(e) {
			console.log(userId);
			$.ajax({
				url: "${pageContext.request.contextPath}/manager/add-user/1/" + userId,
				type: "POST",
				success: function() {
					console.log("success");
					currentRow.remove();
				},
				error: function() {
					
				}
			})
		})

	</script>
</body>
</html>