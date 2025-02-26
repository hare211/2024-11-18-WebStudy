let food_list = [];
let startPage = 0;
let endPage = 0;
let curPage = 1;
let totalPage = 0;
onload = () => {
	let input = document.querySelector("#fd");
	input.value = '마포';
	dataRecv("마포", curPage);
};
function foodFind() {
	let fd = document.querySelector('#fd').value;
	if (fd === '') {
		alert("검색어를 입력하세요");
		document.querySelector('#fd').focus();
		return;
	}
	dataRecv(fd, 1);
};
/*
address"서울 마포구 도화동 340"
content"마포 역에 위치한 즉석 떡볶이 전문점 다락 떡볶이를 소개한다. 멸치육수만을 사용해서 MSG를 전혀 사용하지 않고 깊은 맛을 자랑하며, 단계별로 매운맛의 정도를 정할 수 있다. 매콤한 즉석 떡볶이에 순대, 튀김, 계란, 쫄면, 치즈 사리까지 한가득 듬뿍 담겨서 먹고 난 후 볶음밥까지 먹으면 다락 떡볶이의 진정한 맛을 제대로 느낄 수 있다. 어린아이부터 어른들까지 모두 좋아하는 맛집으로 부담 없이 푸짐하게 즐길 수 있다."
name"다락떡볶이"
parking"주차안됨"
phone"(02) 706-0519"
poster"https://www.menupan.com/restaurant/restimg/003/zzmenuimg/b1022731_z.jpg"
score4.8
theme"아이와 함께라면 , 술 못마시는 친구와 함께 , 일상데이트 , 점심식사 , 포장 가능 , 가성비 좋은 맛집 , 매운음식 , 떡볶이 , 기타 분식"
time"09:30 ~ 22:00 일요일 : 09:30 ~ 21:30"
type"분식"
*/
function foodDetail(fno) {
	let div = document.querySelector('#detail');
	div.style.display = '';
	axios.get("http://localhost/JSPFrontProject_3/food/detail_js.do", {
		params:{
			fno:fno
		}
	}).then((res) => {
		console.log(res.data);
		let food_detail = res.data;
		let html = '<img src="' + food_detail.poster + '" style="width:100%">'
		document.querySelector('#poster1').innerHTML = html;
		document.querySelector('#title').textContent = food_detail.name;
		document.querySelector('#score').textContent = food_detail.score;
		document.querySelector('#address').textContent = food_detail.address;
		document.querySelector('#phone').textContent = food_detail.phone;
		document.querySelector('#type').textContent = food_detail.type;
		document.querySelector('#price').textContent = food_detail.price;
		document.querySelector('#parking').textContent = food_detail.parking;
		document.querySelector('#time').textContent = food_detail.time;
		document.querySelector('#theme').textContent = food_detail.theme;
		document.querySelector('#content').textContent = food_detail.content;
	});
};
/*
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
function pageChange(page) {
	let fd = document.querySelector('#fd').value;
	dataRecv(fd, page);
}
function dataRecv(fd, page) {
	let html = '';
			// axios.get() axios.post()
			axios.get('http://localhost/JSPFrontProject_3/food/find_js.do', {
				params: {
					page:page,
					fd:fd
				}
			})
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
						 + '<img src="'+ vo.poster + '" style="width:100%" onclick="foodDetail(' + vo.fno + ')">'
						 + '<p>' + vo.name + '</p>'
						 + '</div>'
						 + '</div>';
				});
				// CSS selector
				let main = document.querySelector("#poster");
				main.innerHTML = html;
				
				let pages = document.querySelector("#pages");
				let pp = '<ul class="pagination">';
				
				if (startPage > 1) {
					pp += '<li><a onclick="pageChange(' + (startPage - 1) + ')">&lt;</a></li>';
				}
				for(let i = startPage; i <= endPage; i++) {
					let style = '';
					if (i === curPage) {
						style = 'class=active';
					}
					pp += '<li ' + style + '><a onclick="pageChange(' + i + ')">' + i + '</a></li>';
				}
				
				if (endPage < totalPage) {
					pp += '<li><a onclick="pageChange(' + (endPage + 1) + ')">&gt;</a></li>';
				}
				pp += '</ul>'
				
				pages.innerHTML = pp;
				
			});
};