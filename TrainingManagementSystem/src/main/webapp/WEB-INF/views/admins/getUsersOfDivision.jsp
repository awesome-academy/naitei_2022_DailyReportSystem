<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<c:set var="baseURL" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Chi tiết bộ phận</title>
</head>
<body>
	<h1 class="text-center addDivision mt-5">Chi tiết bộ phận</h1>
	<div class="container-fluid px-4 mt-4">
		<div class="row">
			<div class="card bg-secondary mt-4">
				<div class="card-body">
					<div class="row">
						<div class="search col col-12">
							<!-- Search form -->
							<div class="md-form mt-0">
								<input class="form-control border-warning" type="text"
									id="searchInput" placeholder="Tìm kiếm nhân viên" aria-label="Tìm kiếm ...">
							</div>
						</div>
					</div>
					<div class="row mt-5 mb-5">
						<p>
							<span class="fw-bold">Tên bộ phận: </span>
							${getDivisionById.name}
						</p>
						<p>
							<span class="fw-bold">Mã bộ phận: </span>
							${getDivisionById.id}
						</p>

						<p>
							<span class="fw-bold">Mô tả: </span>${getDivisionById.description}</p>
						<p>
							<span class="fw-bold">Số lượng nhân viên: </span>${getUserByDivisionId.size()}</p>
						<p>
							<span class="fw-bold">Quản lý: </span>${getDivisionById.getManager().getName()}
							- ID: ${getDivisionById.getManager().getId()}
						</p>
					</div>
					<div class="row">
						<table class="table mt-2">
							<thead>
								<tr>
									<th scope="col">Tên nhân viên</th>
									<th scope="col">Mã nhân viên</th>
									<th scope="col">Email</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="users" items="${getUserByDivisionId}">
									<tr>
										<td class="userName">${users.name}</td>
										<td class="userId">${users.id}</td>
										<td>${users.email}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>


	<script>
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