<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
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
						<span class="fw-bold">Tên bộ phận: </span>
						${division.name}
					</p>

					<p>
						<span class="fw-bold">Mô tả: </span>${division.description}</p>
					<p>
						<span class="fw-bold">Số lượng nhân viên: </span>${users.size()}</p>
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


						<button class="col col-3 rounded">
							Thêm nhân viên <i class="fa-solid fa-plus"></i>
						</button>
					</div>


					<div class="row">
						<table class="table mt-2">
							<thead>
								<tr>
									<th scope="col">STT</th>
									<th scope="col">Tên thành viên</th>
									<th scope="col">Mã nhân viên</th>
									<th scope="col">Email</th>
									<th scope="col">Hành động</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="users" items="${users}" varStatus="loop">
									<tr>
										<td>${loop.index + 1}</td>
										<td>${users.name}</td>
										<td>${users.id}</td>
										<td>${users.email}</td>
										<td>
											<button class="btn-warning p-1 px-3 rounded">
												Xem chi tiết <i class="fa-regular fa-eye"></i>
											</button>
											<button class="btn-danger p-1 px-3 rounded">
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

	</div>
</body>
</html>