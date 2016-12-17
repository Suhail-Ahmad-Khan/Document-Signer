<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Document Details</title>
</head>
<body>

	<center>
		<h1>Document Details</h1>
		<table style="width: 30%">

			<c:forEach var="document" items="${documentDetails}">

				<tr>
					<th>Id</th>
					<td>${document.id}</td>
				</tr>
				<tr>
					<th>User Id</th>
					<td>${document.userId}</td>
				</tr>
				<tr>
					<th>Name</th>
					<td>${document.name}</td>
				</tr>
				<tr>
					<th>Description</th>
					<td>${document.description}</td>
				</tr>
				<tr>
					<th>File Name</th>
					<td>${document.filename}</td>
				</tr>
				<tr>
					<th>Content Type</th>
					<td>${document.contentType}</td>
				</tr>
				<%-- <tr>
					<th>Date Created</th>
					<td>${document.createdDate}</td>
				</tr> --%>
			</c:forEach>
		</table>
	</center>

</body>
</html>