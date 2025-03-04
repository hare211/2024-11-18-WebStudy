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
	let httpRequest = null;
	
	function getXMLHttpRequest() { // 브라우저에서 HttpXMLRequest 객체 읽기
		// 브라우저마다 객체명이 다르다
		if (window.XMLHttpRequest) {
			return new XMLHttpRequest;
			// XMLHttpRequest: 서버에 요청하여 결과값을 읽어오는 역할
			// $.ajax({})
			
		} else {
			return null;
		}
	};
	function sendRequest(url, params, callback, method) {
		/*
			$.ajax({
				url: ,
				type: ,
				data: {},
				success: function() {
					
				}
			});
		
			axiox.get(url, {params}).then(function(){})
			axiox.post(url, {params}).then(function(){})
				
		*/
		// 1. 객체 생성
		httpRequest = getXMLHttpRequest();
		// <form action=""> -> GET <form method="POTS"> -> 오타 -> GET
		let httpMethod = method ? method : 'GET';
		
		if (httpMethod != 'GET' && httpMethod != 'POST') {
			httpMethod = 'GET';
		}
		
		// Param 처리 -> ?id=admin
		let httpParams = (params === null || params === "") ? null : params;
		// URL 처리
		let httpUrl = url;
		// GET 방식이고 전송할 데이터가 있는 경우
		if (httpMethod === 'GET' && httpParams != null) {
			httpUrl = httpUrl + "?" + httpParams;
		}
		
		// 서버 연결
		httpRequest.open(httpMethod, httpUrl, true);
		// true -> 비동기, false -> 동기
		
		// 한글 처리
		httpRequest.setRequestHeader('Context-Type', 'application/x-www-from-urlencoded');
		
		// 자동 호출되는 함수 지정
		httpRequest.onreadystatechange = callback;
		
		// 데이터 전송
		httpRequest.send(httpMethod === 'POST' ? httpParams : null);
		
		// 데이터 읽기: responseText(HTML) / responseXML(JSON)
		
		
		
	};
	function send() {
		sendRequest('sub.jsp', null, ok, 'POST');
	};
	function ok() {
		/*
			0: 서버 연결 준비
			1: 서버 연결 중
			2: 서버 연결 완료
			3: 데이터 전송 준비 중
			4: 데이터 전송 완료
		*/
		if (httpRequest.readyState === 4) {
			if (httpRequest.status === 200) {
				let div = document.querySelector('#print');
				div.innerHTML = httpRequest.responseText;
			}
		}
	};
</script>
</head>
<body>
	<button onclick="send()">전송</button>
	<div id="print">
	
	</div>
</body>
</html>