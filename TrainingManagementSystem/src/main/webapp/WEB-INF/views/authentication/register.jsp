<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- Sign Up Start -->
<div class="container-fluid">
<form:form action="/TrainingManagementSystem/register" method="POST" modelAttribute="user">
	<div class="row h-100 align-items-center justify-content-center"
		style="min-height: 100vh;">
		<div class="col-12 col-sm-8 col-md-6 col-lg-5 col-xl-4">
			<div class="bg-secondary rounded p-4 p-sm-5 my-4 mx-3">
				<div class="d-flex align-items-center justify-content-between mb-3">
					<a href="/TrainingManagementSystem/" class="">
						<h3 class="text-primary">
							<i class="fa fa-user-edit me-2"></i>TMS
						</h3>
					</a>
					<h3>Đăng ký</h3>
				</div>
				
				<div class="form-floating mb-3 text-center bg-danger">
					<h5>${message}</h5>
				</div>
				<div class="form-floating mb-3">
					<form:input type="text" class="form-control" id="floatingText" 
						placeholder="jhondoe" path="name"/> 
					<label for="floatingText">Họ và Tên</label>
					<form:errors path="name" cssclass="error"></form:errors>
				</div>
				<div class="form-floating mb-3">
					<form:input type="email" class="form-control" id="floatingInput"
						placeholder="name@example.com" path="email"/> 
					<label for="floatingInput">Email</label>
					<form:errors path="email" cssclass="error"></form:errors>
				</div>
				<div class="form-floating mb-4">
					<form:input type="password" class="form-control" id="floatingPassword"
						placeholder="Password" path="password" /> 
					<label for="floatingPassword">Mật khẩu</label>
					<form:errors path="password" cssclass="error"></form:errors>
				</div>
				<button type="submit" class="btn btn-primary py-3 w-100 mb-4" value="Submit">Đăng ký</button>
				<p class="text-center mb-0">
					Đã có tài khoản? <a href="/TrainingManagementSystem/login">Đăng nhập</a>
				</p>
			</div>
		</div>
	</div>
	</form:form>
</div>
<!-- Sign Up End -->