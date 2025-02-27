<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 100%;
}
</style>
<script type="text/javascript">
$(function() {
	$('input[type=text]').val("admin")
	$('input[type=password]').val("1234")
});
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<h3 class="text-center">Login</h3>
			<table class="table">
				<tr>
					<th class="text-center" width=20%>ID</th>
					<td width=80%>
						<input type="text" size="15" class="input-sm">
					</td>
				</tr>
				<tr>
					<th class="text-center" width=20%>PW</th>
					<td width=80%>
						<input type="password" size="15" class="input-sm">
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>