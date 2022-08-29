<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Navbar Start -->
<nav
	class="navbar navbar-expand bg-secondary navbar-dark sticky-top px-4 py-0">

	<a href="#" class="sidebar-toggler flex-shrink-0"> <i
		class="fa fa-bars"></i>
	</a>
	<div class="navbar-nav align-items-center ms-auto">
		<div class="nav-item dropdown">
			<a href="#" class="nav-link dropdown-toggle"
				data-bs-toggle="dropdown"> <i class="fa fa-bell me-lg-2"></i> <span
				class="d-none d-lg-inline-flex">Thông báo</span>
			</a>
			<div
				class="dropdown-menu dropdown-menu-end bg-secondary border-0 rounded-0 rounded-bottom m-0">
				<a href="Task?method=taskById" class="dropdown-item">
					<h6 class="fw-normal mb-0">Công việc đã được duyệt</h6> <small>15
						phút trước</small>
				</a>
			</div>

		</div>
	</div>
	<div class="nav-item dropdown">
		<a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
			<img class="rounded-circle me-lg-2" src="<c:url value="/assets/common/img/user.jpg"/>" style="width: 40px; height: 40px;"> 
			<span class="d-none d-lg-inline-flex">John Doe</span>
		</a>
		<div
			class="dropdown-menu dropdown-menu-end bg-secondary border-0 rounded-0 rounded-bottom m-0">
			<a href="#" class="dropdown-item">My Profile</a> 
			<a href="#"	class="dropdown-item">Settings</a> 
			<a href="/TrainingManagementSystem/logout" class="dropdown-item">Log Out</a>
		</div>
	</div>


</nav>
