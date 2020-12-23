<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>

<title><spring:message code='login.title' /></title>

<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<link
	href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css"
	rel="stylesheet">


<script type="text/javascript">
	$(document).ready(function() {
		var selItem = localStorage.getItem("locales");
		$('#locales').val(selItem ? selItem : 'en');
		$("#locales").change(function() {
			var selectedOption = $('#locales').val();
			if (selectedOption) {
				window.location.replace('?lang=' + selectedOption);
				localStorage.setItem("locales", selectedOption);
			}
		});
	});
</script>

</head>
<body>
<body>




	<div class="container-fluid">

		<!-- Sidebar -->
		<div class="w3-sidebar" style="width: 10%">

			<div class="list-group" style="margin-top: 40px">

				<div class="list-group-item active">
					<div>
						<h3>
							<spring:message code='login.title' />
						</h3>
					</div>
					<div>${pageContext.request.userPrincipal.name}</div>
				</div>

				<a href="/home" class="list-group-item"> <i
					class="fa fa-comment-o"></i> <spring:message code='sidebar.home' />
				</a>

				<security:authorize access="hasRole('ROLE_ADMIN')">
					<a href="/create-dossier" class="list-group-item"> <i
						class="fa fa-search"></i>
					<spring:message code='sidebar.create_dossier' />
					</a>
				</security:authorize>

				<security:authorize access="hasRole('ROLE_STUDENT')">
					<a href="/buckets" class="list-group-item"> <i
						class="fa fa-search"></i> <spring:message code='sidebar.bucket' />
					</a>
				</security:authorize>


				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<form id="logoutForm" method="POST" action="${contextPath}/logout">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>

					<a class="list-group-item"
						onclick="document.forms['logoutForm'].submit()"
						style="cursor: pointer"> <i class="fa fa-search"></i> <spring:message
							code='sidebar.logout' />
					</a>

				</c:if>

			</div>
		</div>


		<!-- Page Content -->
		<div style="margin-left: 10%">
			<div class="w3-container">
				<form:form method="POST" action="${contextPath}/addDossier"
					enctype="multipart/form-data" style="margin:10%">
					
					<div class="form-group">
						<label for="name"><spring:message code='dossier.name'/></label> 
						<input class="form-control"
							aria-describedby="name" name="name" placeholder="<spring:message code='dossier.name'/>">
					</div>
					
					
					<div class="form-group">
						<label for="Description"><spring:message code='dossier.description'/></label> 
						<input class="form-control"
							aria-describedby="Description" name="Description" placeholder="<spring:message code='dossier.description'/>">
					</div>
					
					<div class="form-group">
						<label for="Points"><spring:message code='dossier.points'/></label> 
						<input class="form-control"
							aria-describedby="Dossier" name="points" placeholder="<spring:message code='dossier.points'/>">
					</div>
					
					<div class="form-group">
						<label for="photo"><spring:message code='dossier.select_photo'/></label> 
						<input class="form-control"
							aria-describedby="photo" name="photo" type="file" placeholder="<spring:message code='dossier.photo'/>">
					</div>
					
					  <button type="submit" class="btn btn-primary"><spring:message code='dossier.submit'/></button>
					
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form:form>
			</div>
		</div>

	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</body>
</html>