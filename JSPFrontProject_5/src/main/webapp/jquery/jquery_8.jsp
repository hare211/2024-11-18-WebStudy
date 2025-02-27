<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
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
	let fileIndex = 0;
	$(function() {
		$('.btn-warning').click(function() {
			$('.user-table tbody').append( // html() 을 사용할 시 같은 자리에 덮어 써버린다
				'<tr id="m' + (fileIndex) + '">'
			+	'<td>File ' + (fileIndex + 1) + '<input type=file size=20></td>'
			+	'</tr>'
			);
			fileIndex++;
		});
		
		$('.btn-info').on('click', function() {
			if (fileIndex > 0) {
				$('#m' + (fileIndex - 1)).remove();
				fileIndex--;
			}
		});
	});
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<table class="table">
				<tr>
					<td class="text-right" colspan="2">
						<input type="button" value="add" class="btn-xs btn-warning">
						<input type="button" value="remove" class="btn-xs btn-info">
					</td>
				</tr>
				<tr>
					<th width=15%>첨부파일</th>
					<td width=85%>
						<table class="table user-table">
							<tbody>
							
							</tbody>
						</table>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>