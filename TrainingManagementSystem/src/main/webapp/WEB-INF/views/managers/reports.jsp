<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container bootstrap snippets bootdey">
		<div class="row">
			<div class="col-md-12 p-5 m-3">
				<h2>Danh sách báo cáo</h2>
				<table class="table text-center">
					<thead>
						<tr>
							<th scope="col">STT</th>
							<th scope="col">Tên thành viên</th>
							<th scope="col">Ngày báo cáo</th>
							<th scope="col">Việc đã làm</th>
							<th scope="col">Vấn đề</th>
							<th scope="col">Việc dự định</th>
							<th scope="col">Hành động</th>
						</tr>
					</thead>
					<tbody id="reportsTable">
						<tr>
							<td></td>
							<td><form class="d-none d-md-flex">
									<input class="form-control" type="search"
										placeholder="Nhập tên...">
								</form></td>
							<td><form class="d-none d-md-flex">
									<input class="form-control" type="search"
										placeholder="Nhập ngày...">
								</form></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<c:forEach var="std" items="${reports}">
							<tr>
								<td class="table-plus">${std.id}</td>
								<td>${std.user.name}</td>
								<td>${std.date}</td>
								<td>${std.actualTask}</td>
								<td>${std.plannedTask}</td>
								<td>${std.issue}</td>
								<td><a href="reports/${std.id}" class="btn btn-warning">Bình
										luận</a></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>

				<nav aria-label="Page navigation example">
					<ul class="pagination">
						<li class="page-item"><a class="page-link" href="#"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>
						<!-- <li class="page-item active" aria-current="page"><a
							class="page-link" href="?pageNumber=1">1</a></li> -->
						<li class="page-item"><a class="page-link"
							href="?pageNumber=1">1</a></li>
						<li class="page-item"><a class="page-link"
							href="?pageNumber=2">2</a></li>
						<li class="page-item"><a class="page-link"
							href="?pageNumber=3">3</a></li>
						<li class="page-item"><a class="page-link" href="#"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
					</ul>
				</nav>
			</div>
		</div>
	</div>
</body>
</html>