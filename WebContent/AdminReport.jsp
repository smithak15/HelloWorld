<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
		<script src="js/jquery-1.12.1.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<title>Registration Page</title>
	</head>
	<body>
		<div class="col-md-8 col-md-offset-2 row">
			<table class="table table-striped">
  				<thead class="thead-inverse">
  					<tr>
	  					<th>First Name</th>
	  					<th>Last Name</th>
	  					<th>Address1</th>
	  					<th>Address2</th>
	  					<th>City</th>
	  					<th>State</th>
	  					<th>Zip</th>
	  					<th>Country</th>
	  					<th>Date</th>
  					</tr>
  				</thead>
  				<tbody>
  					<c:forEach var="user" items="${registrationList}">
						<tr>
							<td><c:out value="${user.firstName}"></c:out></td>
							<td><c:out value="${user.lastName}"></c:out></td>
							<td><c:out value="${user.address1}"></c:out></td>
							<td><c:out value="${user.address2}"></c:out></td>
							<td><c:out value="${user.city}"></c:out></td>
							<td><c:out value="${user.state}"></c:out></td>
							<td><c:out value="${user.zip}"></c:out></td>
							<td><c:out value="${user.country}"></c:out></td>
							<td><c:out value="${user.registrationDate}"></c:out></td>
						</tr>
		    		</c:forEach>
  				</tbody>
  			</table>
		</div>
</body>
</html>