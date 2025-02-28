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
	width: 350px;
}
</style>
<script type="text/javascript">
	let bCheck = true;
	
	$(function() {
		$('tr:eq(1)').hide();
		//$('tr:nth-child(2)').hide();
		$('.btn-sm').on('click', function() {
			if (bCheck === true) {
				$('tr:eq(1)').show();
				$(this).val('취소');
				bCheck = false;
			} else {
				$('tr:eq(1)').hide();
				$(this).val('삭제');
				bCheck = true;
			}
		});
	});
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<h3 class="text-center">show / hide</h3>		
			<table class="table">
				<tr>
					<td class="text-right">
						<input type="button" value="삭제" class="btn-sm btn-danger">
					</td>
				</tr>
				<tr>
					<td>
						<input type="password" size="20" class="input-sm">
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>