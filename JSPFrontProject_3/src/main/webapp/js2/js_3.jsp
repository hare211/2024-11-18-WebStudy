<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
	문서 객체
	------
		태그를 가지고 와서 객체로 변환(태그: 객체, 속성: 멤버변수)
		<a href="">
		
		let a = document.querySelector("a");
		a.href = "";
		a.target = "_blank";
		
		=> document.querySelector("");
								 --
								 1. id -> #id명
								 2. class -> .class명
								 3. tag -> tag명
	    => document.getElementById("id");
	    => document.getElementByClassName("class");
	    => document.getElementByTagName("tag");
	    => 같은 태그 여러 개
	    	document.querySelectorAll();
	    ------------------------------------------- 배열로 읽어 온다
	    createElement("태그명") -> 태그 생성
	    createElement("a") -> <a></a>
	    createTextNode("aaa") -> <a>aaa</a>
	    setAttribute() -> 속성 설정 -> $().attr() jquery
	    getAttribute() -> 속성 읽기
	    appendChild() -> 태그 여러 개 생성 -> $("a").append() a -> 태그
	    								  --- document.querySelector()
	    태그와 태그 사이에 값을 첨부
	    tag.innerHTML = "<h1>aaa</h1>" -> aaa
	    	$().html()
	    tag.textContent = "<h1>aaa</h1>" -> <h1>aaa</h1>
	    	$().text()
 -->    	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="img.js"></script>
</head>
<body>

</body>
</html>