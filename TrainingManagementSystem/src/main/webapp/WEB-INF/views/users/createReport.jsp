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
<title>Tạo báo cáo</title>


</head>
<body>

	<div class="container-fluid bootstrap snippets bootdey">
		<div class="row">
			<div class="col-md-12 p-5 m-3">
				<h1 class="text-center fw-bold">Báo cáo hằng ngày</h1>
				<form:form action="${baseURL}/users/reports" method="POST"
					modelAttribute="report" class="report-input">
					<form:hidden path="id" />
					<form:label path="date" class="fw-bold mb-2">Ngày báo cáo:</form:label>
					<br />
					<form:input path="date" class="form-control" type="date"></form:input>
					<br />
					<form:label path="actualTask" class="fw-bold mb-2">Bạn đã làm những gì?</form:label>
					<br />
					<form:textarea path="actualTask" class="form-control" />
					<br />
					<form:label path="plannedTask" class="fw-bold mb-2">Việc dự định vào ngày tiếp theo:</form:label>
					<br />
					<form:textarea path="plannedTask" class="form-control" />
					<br />
					<form:label path="issue" class="fw-bold mb-2">Vấn đề gặp phải:</form:label>
					<br />
					<form:textarea path="issue" class="form-control" />
					<br />
					<button type="submit"
						class="btn btn-info d-grid gap-2 col-4 mx-auto mt-5">Tạo</button>
				</form:form>

			</div>
		</div>
	</div>
	<style>
.report-input input, textarea {
	background-color: white !important;
	width: 100%;
	/* text-align: center; */
	position: relative;
	margin: auto;
	position: relative;
}
</style>
</body>
</html>