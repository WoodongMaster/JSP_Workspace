
// 화면에서 등록한 댓글내용을 컨트롤러로 전송 -> DB저장
async function postCommentToServer(cmtData) {
	try {
		const url = "/cmt/post";
		const config = {	// config : 설정 
			method: 'post',	// method : 'post' 인지 'get'
			headers: {		// headers : 'content-Type' 어떤 형태의 데이터인지 헤더에 붙혀서 보냄
				'content-Type': 'application/json; charset=utf-8'
				 // application/json : JSON형식
				 // application/x-www-form-urlencoded : 브라우저-서버 사이 사용
			},
			body: JSON.stringify(cmtData) // cmtData를 JSON 형태로 변경
		};
		const resp = await fetch(url, config); // await : 응답대기
		const result = await resp.text(); // isOk로 보낼예정 0 or 1
		return result;
	} catch (error) {
		console.log(error);
	}
	
}

// 미리 보낼 데이터를 만들어서 함수로 전달 cmtData
document.getElementById('cmtAddBtn').addEventListener('click', () => {
	const cmtText = document.getElementById('cmtText').value;
	console.log(cmtText);
	if (cmtText == null || cmtText == "") {
		alert('댓글을 입력해주세요');
		return false;
	}
	else {
		let cmtData = {
			bnum: bnumVal,
			writer: document.getElementById('cmtWriter').value,
			comment: cmtText
		}; // 프로미스 객체? 프로미스 객체의 .then 메서드 => 연속처리가 필요할때
		postCommentToServer(cmtData).then(result => {
			if (result > 0) {
				alert('댓글 등록 성공 !!!');
				document.getElementById('cmtText').value = "";
			}
			// 댓글 표시
			printCommentList(cmtData.bnum);
		})
	}
})

// 댓글 가져오기
// 컨트롤러에 가서 댓글 리스트 요청 (게시글 번호 (bnum)에 해당하는 댓글만)
async function getCommentListFromServer(bnum) {
	try {
		const resp = await fetch('/cmt/list/' + bnum);	// get방식 파라미터 전달
		const cmtList = await resp.json();
		return cmtList;

	} catch (error) {
		console.log(error);
	}
}

function printCommentList(bnum) {
	getCommentListFromServer(bnum).then(result => {
		console.log(result);
		if (result.length > 0) { // 등록된 댓글 있음
			spreadCommentList(result);
		} else {
			let div = document.getElementById('accordionFlushExample');
			div.innerHTML = "등록된 댓글이 없습니다.";
		}
	})
}

function spreadCommentList(result) { // 댓글 list
	console.log("spreadCommentList : " +result);
	let div = document.getElementById('accordionFlushExample');
	div.innerHTML = "";
	for (let i = 0; i < result.length; i++) {
		let html = `<div class="accordion-item">`;
		html += `<h2 class="accordion-header" id="flush-heading${i}">`;
		html += `<button class="accordion-button collapsed" `;
		html += `type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapse${i}" aria-expanded="false" aria-controls="flush-collapse${i}">`;
		html += `작성자 : ${result[i].writer} </button></h2>`;
		html += `<div id="flush-collapse${i}" class="accordion-collapse collapse" aria-labelledby="flush-heading${i}" data-bs-parent="#accordionFlushExample">`;
		html += `<div class="accordion-body">`;
		html += `<button type="button" data-cnum="${result[i].cnum}" data-writer="${result[i].writer}" class="btn btn-sm btn-outline-warning cmtModifyBtn">%</button>`;
		html += `<button type="button" data-cnum="${result[i].cnum}" data-writer="${result[i].writer}" class="btn btn-sm btn-outline-danger cmtDelBtn">X</button>`;
		html += `<input type="text" class="form-control" id="cmtText1" value="${result[i].comment}">`;
		html += `${result[i].reg_date}</div></div></div>`;
		div.innerHTML += html;
	}
}

async function removeCommentFromServer(cnumVal){
	try{
		const url = '/cmt/remove?cnumVal='+cnumVal;	// post 방식
		const config={
			method:'post'
		};
		const resp = await fetch(url, config);
		const result = await resp.text(); // isOk 값
		return result;
	}catch(error){
		console.log(error);
	}
}

async function updateCommentFromServer(cnumVal, cmtText1, writer){
	try{
		const url = "/cmt/modify";
		const config = {
			method : "post",
			headers : {
				'Content-type' : 'application/json; charset=utf-8'
			},
			body : JSON.stringify({cnum:cnumVal, comment:cmtText1, writer:writer})
		}
		const resp = await fetch(url, config);
		const result = await resp.text(); // update 니까 isOk 값 받아옴
		return result;
	}catch(error){
		console.log(error);
	}
	
}

document.addEventListener('click', (e)=>{
	if(e.target.classList.contains('cmtModifyBtn')){ // 댓글 수정 : 기존 위치의 값을 읽어들여 DB에 업데이트 후 변경사항 표시
		let cnumVal = e.target.dataset.cnum;
		console.log(cnumVal);
		// 현재 수정하고자 하는 값( input box의 value값)을 찾기위한 작업
		let div = e.target.closest('div'); // closest -> 타겟을 기준으로 가장 가까운 값 찾기
		console.log("div : "+div);
		let cmtText1 = div.querySelector('#cmtText1').value;
		let writer = e.target.dataset.writer;
		
		updateCommentFromServer(cnumVal, cmtText1, writer).then(result=>{
			if(result>0){
				alert('댓글 수정 완료!');
				printCommentList(bnumVal);
				console.log(bnumVal);
			}else{
				alert('댓글 수정 실패!');
			}
		})
	}
	if(e.target.classList.contains('cmtDelBtn')){ // 댓글 삭제
		let cnumVal = e.target.dataset.cnum;
		console.log(cnumVal);
		removeCommentFromServer(cnumVal).then(result =>{
			if(result>0){
				alert('댓글 삭제 완료!');
				printCommentList(bnumVal);
				console.log(bnumVal);
			}
		})		
	}
	
})




