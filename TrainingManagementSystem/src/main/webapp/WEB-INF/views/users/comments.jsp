<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="baseURL" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<!--  This file has been downloaded from bootdey.com @bootdey on twitter -->
<!--  All snippets are MIT license http://bootdey.com/license -->
<title>blog comments - Bootdey.com</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<link
	href="https://netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container bootstrap snippets bootdey">
		<div class="row">
			<div class="col-md-12">
				<div class="blog-comment">
					<div class="text-white bg-dark">
						<h2 class="card-header">Thông tin báo cáo</h2>
						<div class="card-body">
							<p>Nhân viên: ${report.user.name}</p>
							<p>Ngày báo cáo: ${report.date}</p>
							<p>Công việc đã hoàn thành: ${report.actualTask}</p>
							<p>Công việc dự kiến ngày tiếp theo: ${report.plannedTask}</p>
							<p>Vấn đề: ${report.issue}</p>
						</div>
					</div>
					<hr />
					<h3 class="text-white">Bình luận:</h3>
					<ul class="comments">
						<c:forEach var="comment" items="${report.comments}"
							varStatus="loop">
							<li class="clearfix deleteElement"><img
								src="https://bootdey.com/img/Content/user_1.jpg" class="avatar"
								alt="">
								<div class="post-comments">
									<p class="meta">
										<%-- ${comment.updatedAt} --%>
										<a href="#">${comment.user.name}</a> : <i class="pull-right"><a
											href="#"></a></i>
									</p>
									<p>${comment.content}</p>
									<a
										class="p-1 px-3 rounded clickDelete text-decoration-underline"
										data-bs-toggle="modal" data-bs-target="#deleteModal"
										onClick="clickDeleteComment(${comment.id})"> Xóa </a>
								</div></li>
						</c:forEach>
					</ul>
					<div class="card-footer">
						<form:form action="${baseURL}/users/reports/${idReport}/comments"
							method="POST" modelAttribute="comment">
							<div class="input-group">
								<form:textarea path="content" placeholder="Nhập bình luận..."
									class="form-control width100 bg-white"></form:textarea>
								<span class="input-group-btn">
									<button class="btn btn-secondary">Gửi</button>
								</span>
							</div>
						</form:form>
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
					<div class="modal-body">Bạn có chắc chắn muốn xóa nhân viên
						này khỏi bộ phận của mình?</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Hủy</button>
						<button type="button" class="btn btn-primary" id="deleteBtn"
							data-bs-dismiss="modal">Xóa</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<style type="text/css">
body {
	background: #eee;
}

hr {
	margin-top: 20px;
	margin-bottom: 20px;
	border: 0;
	border-top: 1px solid #FFFFFF;
}

a {
	color: #82b440;
	text-decoration: none;
}

.blog-comment::before, .blog-comment::after, .blog-comment-form::before,
	.blog-comment-form::after {
	content: "";
	display: table;
	clear: both;
}

.blog-comment {
	padding-left: 15%;
	padding-right: 15%;
}

.blog-comment ul {
	list-style-type: none;
	padding: 0;
}

.blog-comment img {
	opacity: 1;
	filter: Alpha(opacity = 100);
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	-o-border-radius: 4px;
	border-radius: 4px;
}

.blog-comment img.avatar {
	position: relative;
	float: left;
	margin-left: 0;
	margin-top: 0;
	width: 65px;
	height: 65px;
}

.blog-comment .post-comments {
	border: 1px solid #eee;
	margin-bottom: 20px;
	margin-left: 85px;
	margin-right: 0px;
	padding: 10px 20px;
	position: relative;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	-o-border-radius: 4px;
	border-radius: 4px;
	background: #fff;
	color: #6b6e80;
	position: relative;
}

.blog-comment .meta {
	font-size: 13px;
	color: #aaaaaa;
	padding-bottom: 8px;
	margin-bottom: 10px !important;
	border-bottom: 1px solid #eee;
}

.blog-comment ul.comments ul {
	list-style-type: none;
	padding: 0;
	margin-left: 85px;
}

.blog-comment-form {
	padding-left: 15%;
	padding-right: 15%;
	padding-top: 40px;
}

.blog-comment h3, .blog-comment-form h3 {
	margin-bottom: 40px;
	font-size: 26px;
	line-height: 30px;
	font-weight: 800;
}
</style>
	<script>
		let commentId;
		let currentRow;
		let deleteUrl
		function clickDeleteComment(id) {
			commentId = id;
		}
		
		$(".deleteElement").click(function(e) {
			currentRow = $(this);
			console.log(currentRow);
			console.log("commentId: ", commentId);
			deleteUrl = '${pageContext.request.contextPath}/users/comments/' + commentId;
		})
		
		$("#deleteBtn").click(function(e) {
			if (commentId) {
				console.log(deleteUrl);
				$.ajax({
					url: deleteUrl,
					type: 'DELETE',
					success: function() {
						currentRow.remove();
					},
					error: function(response) {
						console.log(response);
						/* window.location.href = response.redirect; */
					}
				})
			}
		})	
	</script>
</body>
</html>