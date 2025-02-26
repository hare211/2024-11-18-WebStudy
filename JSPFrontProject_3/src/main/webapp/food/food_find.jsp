<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container-fluid{
	margin-top: 50px;
}
.row{
	margin-left: 20px;
	width: 80%;
}
p{
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
}
img:hover, a:hover{
	cursor: hover;
}
</style>
<script type="text/javascript" src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="text/javascript" src="food.js"></script>
<script type="text/javascript">
/*
	let food_list = [];
	let startPage = 0;
	let endPage = 0;
	let curPage = 0;
	let totalPage = 0;
	// 디폴트 목록 출력
	window.onload = function() {
		let html = '';
		// axios.get() axios.post()
		axios.get('http://localhost/JSPFrontProject_3/food/find_js.do')
		.then((response) => { // callback 함수
			console.log(response.data);
			food_list = response.data;
			curPage = response.data[0].curPage;
			totalPage = response.data[0].totalPage;
			startPage = response.data[0].startPage;
			endPage = response.data[0].endPage;
			
			console.log("curPage = " + curPage);
			console.log("totalPage = " + totalPage);
			console.log("startage = " + startPage);
			console.log("endPage = " + endPage);
			
			food_list.map(function(vo) {
				html += '<div class="col-sm-4">'
					 + '<div class="thumbnail">'
					 + '<img src="'+ vo.poster + '" style="width:100%" onclick="detail(' + vo.fno + ')">'
					 + '<p>' + vo.name + '</p>'
					 + '</div>'
					 + '</div>';
			});
			// CSS selector
			let main = document.querySelector("#poster");
			main.innerHTML = html;
		});
	};
	*/
	/*
		document.querySelector("#fd") -> id
		=> document.getElementById("id")
		=> $('#fd')
		document.querySelector(".fd") -> class
		=> document.getElementByClassName("fd")
		=> $('.fd')
		document.querySelector("div") -> 태그명
		=> document.getElementByTagName("div")
		=> $('div')
	*/
	// 검색 후 목록 출력
	/*
	function foodFind() {
		let addr = document.querySelector("#fd").value;
		if (addr.trim() === "") {
			alert("지역을 입력하세요");
			document.querySelector("#fd").focus();
			return;
		}
		let html = '';
		
		axios.get('http://localhost/JSPFrontProject_3/food/find_js.do', {
			params:{
				page:1,
				fd:addr
			}
		})
		.then((response) => {
			console.log(response.data);
			food_list = response.data;
			curPage = response.data[0].curPage;
			totalPage = response.data[0].totalPage;
			startPage = response.data[0].startPage;
			endPage = response.data[0].endPage;
			
			console.log("curpage = " + curPage);
			console.log("totalpage = " + totalPage);
			console.log("startPage = " + startPage);
			console.log("endPage = " + endPage);
			
			food_list.map(function(vo){
				html += '<div class="col-sm-4">'
				     + '<div class="thumbnail">'
				     + '<img src="'+vo.poster+'" style="width:100%">'
				     + '<p>'+vo.name+'</p>'
				     + '</div>'
				     + '</div>'
			}) ;
			let main = document.querySelector("#poster");
			// CSS selector 
			main.innerHTML = html;
		});
	};
	
	let detail = (fno) => {
		axios.get("http://localhost/JSPFrontProject_3/food/detail_js.do", {
			params:{
				fno:fno
			}
		}).then((res) => {
			console.log(res.data);
		});
	};
	*/
</script>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<input type="text" id="fd" size="20" class="input-sm">
			<input type="button" id="btn" value="검색" class="btn-sm btn-success" onclick="foodFind()">
		</div>
		<div class="row" style="margin-top:20px">
			<div class="col-sm-8" id="poster">
			
			</div>
			<div class="col-sm-4" id="detail" style="display:none">
				<table class="table">
					<tr>
						<td width=30% class="text-center" rowspan="8" id="poster1">
						
						</td>
						<td colspan="2">
							<h3><span id="title"></span>&nbsp;<span id="score" style="color:orange"></span></h3>
						</td>
					</tr>
					<tr>
						<td width=15% style="color:gray">음식종류</td>
						<td width=55% id="type"></td>
					</tr>
					<tr>
						<td width=15% style="color:gray">주소</td>
						<td width=55% id="address"></td>
					</tr>
					<tr>
						<td width=15% style="color:gray">전화</td>
						<td width=55% id="phone"></td>
					</tr>
					<tr>
						<td width=15% style="color:gray">가격대</td>
						<td width=55% id="price"></td>
					</tr>
					<tr>
						<td width=15% style="color:gray">주차</td>
						<td width=55% id="parking"></td>
					</tr>
					<tr>
						<td width=15% style="color:gray">영업시간</td>
						<td width=55% id="time"></td>
					</tr>
					<tr>
						<td width=15% style="color:gray">테마</td>
						<td width=55% id="theme"></td>
					</tr>
				</table>
				<table class="table">
					<tr>
						<td id="content"></td>
					</tr>
				</table>
			</div>
		</div>
		<div class="row" style="margin-top:10px">
			<div class="text-center" id="pages">
			
			</div>
		</div>
	</div>
</body>
</html>