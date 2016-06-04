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
		<div class="well col-md-8 col-md-offset-2 row">
			<h2 class="text-center">Registration Form</h2>
			<div id="serverErr">
				<c:if test="${not empty errList}">
					<c:forEach var="err" items="${errList}">
						<span style="color: red;"><c:out value="${err}"></c:out><br></span>
					</c:forEach>
				</c:if>
			</div>
			<form class="form-horizontal" id="regForm" role="form" method="post" action="registrationSubmit.do">
				<div class="form-group">
		    		<label class="control-label col-sm-6">First Name*:</label>
		    		<div class="col-sm-4">
		      			<input type="text" class="form-control" id="fname" name="fname">
		    		</div>
		    		<div id="fnameError" style="display: none">
		    		</div>
		  		</div>
		  		<div class="form-group">
		    		<label class="control-label col-sm-6">Last Name*:</label>
		    		<div class="col-sm-4">
		      			<input type="text" class="form-control" id="lname" name="lname">
		    		</div>
		    		<div id="lnameError" style="display: none">
		    		</div>
		  		</div>
		  		<div class="form-group">
		    		<label class="control-label col-sm-6">Address1*:</label>
		    		<div class="col-sm-4">
		      			<input type="text" class="form-control" id="address1" name="address1">
		    		</div>
		    		<div id="addr1Error" style="display: none">
		    		</div>
		  		</div>
		  		<div class="form-group">
		    		<label class="control-label col-sm-6">Address2:</label>
		    		<div class="col-sm-4">
		      			<input type="text" class="form-control" id="address1" name="address1">
		    		</div>
		    		<div id="addr2Error" style="display: none">
		    		</div>
		  		</div>
		  		<div class="form-group">
		    		<label class="control-label col-sm-6">City*:</label>
		    		<div class="col-sm-4">
		      			<input type="text" class="form-control" id="city" name="city">
		    		</div>
		    		<div id="cityError" style="display: none">
		    		</div>
		  		</div>
		  		<div class="form-group">
		    		<label class="control-label col-sm-6">State*:</label>
		    		<div class="col-sm-4">
		      			<input type="text" class="form-control" id="state" name="state">
		    		</div>
		    		<div id="stateError" style="display: none">
		    		</div>
		  		</div>
		  		<div class="form-group">
		    		<label class="control-label col-sm-6">Zip*:</label>
		    		<div class="col-sm-4">
		      			<input type="text" class="form-control" id="zip" name="zip">
		    		</div>
		    		<div id="zipError" style="display: none">
		    		</div>
		  		</div>
		  		<div class="form-group">
		    		<label class="control-label col-sm-6" id="country">Country:</label>
		    		<div class="col-sm-4">
		      			<input type="text" class="form-control" id="cntry" name="cntry" placeholder="US" disabled>
		      			<input type="hidden" id="country" name="country" value="US">
		    		</div>
		  		</div>
				<div class="form-group"> 
		  			<div class="col-md-offset-6 col-md-6">
		      			<button type="button" id="sub" class="btn btn-success col-md-4">Submit</button>
		    		</div>
		  		</div>
			</form>
		</div>
	</body>
	<script type="text/javascript">
		$("#sub").click(function(){
			var fname = $("#fname").val();
			var lname = $("#lname").val();
			var address1 = $("#address1").val();
			var address2 = $("#address2").val();
			var city = $("#city").val();
			var state = $("#state").val();
			var zip = $("#zip").val();
			var error = "false";
			$("#serverErr").css('display','none');
			$("#fnameError").css('display','none');
			$("#lnameError").css('display','none');
			$("#addr1Error").css('display','none');
			$("#cityError").css('display','none');
			$("#stateError").css('display','none');
			$("#zipError").css('display','none');
			if(fname.trim() == ""){
				$("#fnameError").html("<small>First Name is required</small>"); 
				$("#fnameError").css('display','inline');
				$("#fnameError").css('color','red');
				error = "true";
			}
			if(lname.trim() == ""){
				$("#lnameError").html("<small>Last Name is required</small>"); 
				$("#lnameError").css('display','inline');
				$("#lnameError").css('color','red');
				error = "true";
			}
			if(address1.trim() == ""){
				$("#addr1Error").html("<small>Address1 is required</small>"); 
				$("#addr1Error").css('display','inline');
				$("#addr1Error").css('color','red');
				error = "true";
			}
			if(city.trim() == ""){
				$("#cityError").html("<small>City is required</small>"); 
				$("#cityError").css('display','inline');
				$("#cityError").css('color','red');
				error = "true";
			}
			if(state.trim() == ""){
				$("#stateError").html("<small>State is required</small>"); 
				$("#stateError").css('display','inline');
				$("#stateError").css('color','red');
				error = "true";
			}
			if(zip.trim() == ""){
				$("#zipError").html("<small>Zip is required</small>"); 
				$("#zipError").css('display','inline');
				$("#zipError").css('color','red');
				error = "true";
			}else if(!(zip.trim().length == 5 || zip.trim().length == 9)){
				$("#zipError").html("<small>Zip should have 5 or 9 digits</small>"); 
				$("#zipError").css('display','inline');
				$("#zipError").css('color','red');
				error = "true";
			}
			if(error == "false"){
				$("#regForm").submit();
			}
		})
	</script>
</html>