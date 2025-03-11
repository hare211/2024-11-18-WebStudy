<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<%--
	<if> <where> <foreach> <trim> <choose> <bind>
 --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/bootstrap/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('.btn-warning').click(function() {
			$('input[type=checkbox]').prop('checked', false);
		})
		$('.btn-info').click(function() {
			$('input[type=checkbox]').prop('checked', true);
		})
		$('.btn-danger').click(function() {
			let len = $('input[type=checkbox]:checked').length;
			//alert(len);
			if (len === 0) {
				alert('사원 이름을 선택하세요');
				return;
			}
			$('#frm').submit();
		})
	})
</script>
</head>
<body>
	<div class="container">
		<div class="row" style="width: 450px; margin: 0px auto">
			<h3 class="text-center">사원 이름</h3>
			<form action="../mybatis/emp_result.do" method="post" id="frm">
			<table class="table">
				<tr>
					<td class="text-center">
						<input type="button" value="전체선택" class="btn-sm btn-info">
						<input type="button" value="전체해제" class="btn-sm btn-warning">
					</td>
				</tr>
				<tr>
					<td>
						<c:forEach var="vo" items="${list }">
							<input type="checkbox" value="${vo.empno }" name="empno">${vo.ename }
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td class="text-center">
						<input type="button" value="전송" class="btn-sm btn-danger">
					</td>
				</tr>
			</table>
			</form>
		</div>	
	</div>
</body>
</html>