<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<c:set var="baseURL" value="${pageContext.request.contextPath}" />
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
					<p>
						<span class="fw-bold">Số lượng nhân viên: </span> <span
							class="userCount">${users.size()}</span>
					</p>
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
								<input class="form-control border-warning" id="searchInput"
									autocomplete="off" type="text"
									placeholder="Tìm kiếm ..." aria-label="Search">
							</div>
						</div>

						<a href="${baseURL}/manager/new"
							class="col col-3 rounded btn btn-success"> Thêm nhân viên <i
							class="fa-solid fa-plus"></i>
						</a>
					</div>


					<div class="row">
						<table class="table mt-2">
							<thead>
								<tr>
									<th scope="col">Tên thành viên</th>
									<th scope="col">Mã nhân viên</th>
									<th scope="col">Ngày vào bộ phận</th>
									<th scope="col">Email</th>
									<th scope="col">Hành động</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="users" items="${users}">
									<tr class="dataRow">
										<td class="userName">${users.name}</td>
										<td class="userId">${users.id}</td>
										<td class="">${users.updatedAt.getDayOfMonth()} - ${users.updatedAt.getMonthValue()} - ${users.updatedAt.getYear()}</td>
										<td>${users.email}</td>
										<td>
											<button class="btn-warning p-1 px-3 rounded detailBtn"
												data-bs-toggle="modal" data-bs-target="#detailModal">
												Xem <i class="fa-regular fa-eye"></i>
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
						<h5 class="modal-title text-danger" id="exampleModalLabel">Xóa
							nhân viên khỏi bộ phận?</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body text-secondary">Bạn có chắc chắn muốn
						xóa nhân viên này khỏi bộ phận của mình?</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Hủy</button>
						<button type="button" class="btn btn-primary" id="deleteBtn"
							data-bs-dismiss="modal">Xóa</button>
					</div>
				</div>
			</div>
		</div>


		<!-- See details of a user -->
		<div class="modal fade" id="detailModal" tabindex="-1"
			aria-labelledby="detailModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title text-secondary text-center"
							id="detailModalLabel">Thông tin nhân viên</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body text-secondary">
						<p>
							<span class="fw-bold">Tên nhân viên: </span> 
							<span class="detail-name"></span>
						</p>
						<p>
							<span class="fw-bold">Mã nhân viên: </span> 
							<span class="detail-id"></span>
						</p>
						<p>
							<span class="fw-bold">Ngày vào bộ phận: </span> 
							<span class="detail-updatedAt"></span>
						</p>
						<p>
							<span class="fw-bold">Email: </span> 
							<span class="detail-email"></span>
						</p>

					</div>
				</div>
			</div>
		</div>
	</div>

	<script>
		// Delete a user from a division	

		let userId;
		let currentRow;

		$(".deleteRow").click(function(e) {
			var userIdElement = $(this).parent().prev().prev().prev();

			// When click the Delete button in reach row, update the userId and currentRow
			userId = userIdElement.html();
			currentRow = userIdElement.parent();
		})

		$("#deleteBtn").click(
				function(e) {
					if (userId) {
						$.ajax({
							url : '${pageContext.request.contextPath}/manager/'
									+ userId + '/remove',
							type : 'POST',
							success : function() {
								currentRow.remove();
								$(".userCount")
										.html($(".userCount").html() - 1);
							},
							error : function(response) {

							}
						})
					}
				})
				
		// See user details info
		
		$(".detailBtn").click(function() {
			console.log($(this));
			let data = $(this).parent().parent().children().map(function(index, element) {
				return ($(this).text());
			})
			$('.detail-name').html(data[0]);
			$('.detail-id').html(data[1]);
			$('.detail-updatedAt').html(data[2]);
			$('.detail-email').html(data[3]);
		})

		// Search function
		$("#searchInput").on("input", function(e) {
			let keyword = e.target.value;

			$(".userName").each(function(index) {
				let convertedName = stringToSlug($(this).text());

				// do not contain keyword				
				if (convertedName.indexOf(stringToSlug(keyword)) == -1 
						&& $(this).text().toLowerCase().indexOf(stringToSlug(keyword)) == -1) {
					$(this).parent().addClass("visually-hidden");
				} else {
					$(this).parent().removeClass("visually-hidden");
				}

			})

		})

		function stringToSlug(str) {
			// remove accents
			var from = "àáãảạăằắẳẵặâầấẩẫậèéẻẽẹêềếểễệđùúủũụưừứửữựòóỏõọôồốổỗộơờớởỡợìíỉĩịäëïîöüûñçýỳỹỵỷ", to = "aaaaaaaaaaaaaaaaaeeeeeeeeeeeduuuuuuuuuuuoooooooooooooooooiiiiiaeiiouuncyyyyy";
			for (var i = 0, l = from.length; i < l; i++) {
				str = str.replace(RegExp(from[i], "gi"), to[i]);
			}

			str = str.toLowerCase().trim().replace(/[^a-z0-9\-]/g, '-')
					.replace(/-+/g, '-');

			return str;
		}
	</script>
</body>
</html>