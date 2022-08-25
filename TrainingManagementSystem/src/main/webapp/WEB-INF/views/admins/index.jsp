<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="baseURL" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

</head>
<body>

	<div class="container mt-3">


		<h1 class="text-center">Danh sách các bộ phận</h1>



		<h3 class="mb-5 mt-5 addDivisionButton">

			<a href="${baseURL}/admin/divisions/new"><i class="fas fa-plus"></i>
				Thêm bộ phận</a>
		</h3>
		<table class="table table-striped table-dark">
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Tên</th>
					<th scope="col">Manager ID</th>
					<th scope="col">Hành động</th>
					<th scope="col"></th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listDivisions}" var="division">
					<tr>
						<th scope="row">${division.id}</th>
						<td>${division.name}</td>
						<td>${division.getManager().getId()}</td>
						<td><i class="fas fa-edit EditIcon me-3"></i> <i
							class="fas fa-trash-alt TrashIcon"></i></td>
						<td><a class="btn btn-warning seeDetailButton"
							href="${baseURL}/admin/DivisionInfomation?id=${division.id}"
							role="button">Xem chi tiết <i class="fas fa-eye"></i></a> <!-- Button trigger modal -->
							<button type="button" class="btn btn-primary"
								data-bs-toggle="modal" data-bs-target="#exampleModal">
								Xóa</button> <!-- Modal -->
							<div class="modal fade" id="exampleModal" tabindex="-1"
								aria-labelledby="exampleModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="exampleModalLabel"
												style="color: black">Xóa bộ phận</h5>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body" style="color: black">Bạn có muốn
											xóa bộ phận?</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">Đóng</button>
											<a class="btn btn-warning "
												href="${baseURL}/admin/divisions/remove?id=${division.id}"
												role="button">Đồng ý </a>
										</div>
									</div>
								</div>
							</div></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>




</body>
</html>