/**
 * 
 */
let bCheck=true
$(function(){
	$('.ups').hide()
	$('.update').click(function(){
		let rno=$(this).attr('data-rno') // span 태그(.update) 여러 개 중에 사용자가 클릭한 것을 가져온다
		$('.ups').hide();
		if(bCheck==true){
			$('#m' + rno).show();
			$(this).text("취소");
			bCheck=false;
		} else{
			$('#m' + rno).hide();
			$(this).text("수정");
			bCheck=true;
		}
	})		
})