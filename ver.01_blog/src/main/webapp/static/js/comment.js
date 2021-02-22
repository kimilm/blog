const postNo = document.getElementById('postNo').value;
const updateSpan = document.getElementById('commentUpdate');
const deleteSpan = document.getElementById('commentDelete');
const updateFlag = true;

function listeners() {
	if (updateSpan != null) {
		updateSpan.addEventListener('click', commentUpdate, false);
		deleteSpan.addEventListener('click', commentDelete, false);
	}
}

function commentUpdate() {
	let textParent = updateSpan.parentNode.parentNode.parentNode.children[0];
	
	//<p class="card-text" th:text="${i.commentContent}"></p>
	
	//<input class="card-body" type="text">
	
	if (updateFlag) {
		const textChild = updateSpan.parentNode.parentNode.parentNode.children[0].children[0];
		const text = textChild.textContent;
		
		textParent.removeChild(textChild);
		
		let input = document.createElement('input');
		input.className = 'card-body focus'
		input.type='text';
		input.value=text;
		
		textChild.appendChild(input);
		
		updateFlag = false;
	} else {
		const inputChild = updateSpan.parentNode.parentNode.parentNode.children[0].children[0];
		const input = textChild.value;
		
		textParent.removeChild(inputChild);
		
		let para = document.createElement('p');
		para.className = 'card-body focus'
		para.textContent = input.value;
		
		
		textChild.appendChild(para);
		
		updateFlag = true;
	}
}

function commentDelete() {
	let commentNo = deleteSpan.parentNode.parentNode.children[1].value;
	fetch(`/rest/${userId}/deleteComment/${commentNo}`, {
 			method: 'DELETE',
		}).then(res => res.json())
		.then((res) => {
			if (res.result) {
				console.log(commentNo + 'remove');
				
				window.location.href='/' + userId + '/post?postNo=' + postNo;
			}
		});
}