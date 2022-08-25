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
<title>Thêm bộ phận</title>


</head>
<body>

	<div class="container mt-3">
		<c:set var="Income" scope="session"
			value="${baseURL}/admin/divisions/create" />
		<h1 class="text-center addDivision">Thêm bộ phận</h1>
		<form:form action="${baseURL}/admin/divisions/create" method="POST"
			modelAttribute="division" class="createDivisionForm">
			<form:hidden path="id" />
			<form:label path="name">Tên:</form:label>
			<form:input path="name" class="form-control" />
			<br />
			<form:label path="description">Mô tả:</form:label>
			<form:input path="description" class="form-control" />
			<br />


			<form:label path="manager.id">Manager ID:</form:label>
			<form:select path="manager.id">

				<c:forEach items="${loadUsersNotinManagerID}" var="user">
					<form:option value="${user.id}" label="${user.id} - ${user.name}" />
				</c:forEach>
			</form:select>

			<br />

			<br />
			<button type="submit"
				class="btn btn-info d-grid gap-2 col-4 mx-auto submitCreateDivision mt-5">Tạo</button>
		</form:form>

	</div>

</body>
</html>