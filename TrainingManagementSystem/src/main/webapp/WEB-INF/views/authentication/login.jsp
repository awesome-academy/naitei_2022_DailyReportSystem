<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container-fluid">
	<form:form action="/TrainingManagementSystem/login" method="POST" modelAttribute="user">
	<div class="row h-100 align-items-center justify-content-center"
		style="min-height: 100vh;">
		<div class="col-12 col-sm-8 col-md-6 col-lg-5 col-xl-4">
			<div class="bg-secondary rounded p-4 p-sm-5 my-4 mx-3">
				<div class="d-flex align-items-center justify-content-between mb-3">
					<a href="/TrainingManagementSystem/login" class="">
						<h3 class="text-primary">
							<i class="fa fa-user-edit me-2"></i>TMS
						</h3>
					</a>
					<h3>Đăng nhập</h3>
				</div>
				
				<div class="form-floating mb-3 text-center bg-danger">
					<h5>
						<c:if test="${param.error != null}"> 
							Đăng nhập thất bại
						</c:if>
					</h5>
				</div>
				
				<div class="form-floating mb-3">
					<form:input path="email" type="email" class="form-control" id="floatingInput"
						placeholder="name@example.com"/> <label for="floatingInput">Email address</label>
				</div>
				<div class="form-floating mb-4">
					<form:input path="password" type="password" class="form-control" id="floatingPassword"
						placeholder="Password"/> <label for="floatingPassword">Password</label>
				</div>
				<div class="d-flex align-items-center justify-content-between mb-4">
					<div class="form-check">
						<input type="checkbox" class="form-check-input" id="exampleCheck1">
						<label class="form-check-label" for="exampleCheck1">Ghi nhớ tài khoản</label>
					</div>
				</div>
				<div class="d-flex align-items-center justify-content-between mb-4">
					<a href="">Quên mật khẩu?</a>
				</div>
				<button type="submit" class="btn btn-primary py-3 w-100 mb-4">Đăng nhập</button>
				<p class="text-center mb-0">
					Chưa có tài khoản? <a href="/TrainingManagementSystem/register">Đăng ký</a>
				</p>
			</div>
		</div>
	</div>
	</form:form>
</div>