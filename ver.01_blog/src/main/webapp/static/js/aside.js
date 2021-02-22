const categoryList = document.getElementById('categoryList');
const userId = document.getElementById('userId').value;
const userBody = document.getElementById('userBody');
const userFooter = document.getElementById('userFooter');

const categorySave = document.getElementById('categorySave');
const categoryEdit = document.getElementById('categoryEdit');
const categoryAdd = document.getElementById('categoryAdd');

function listeners() {
	if (categoryAdd != null) {
		categoryAdd.addEventListener('click', appendList, false);
		categoryEdit.addEventListener('click', editCategory, false);
		categorySave.addEventListener('click', saveCategory, false);
	}
}

function appendList() {
	let list = document.createElement('li');
	let input = document.createElement('input');
	let span = document.createElement('span');
	let hidden = document.createElement('input');

	list.className='list-group-item d-flex justify-content-between align-items-center';

	input.id='newCategory';
	input.type='text';
	input.className='form-control form-control-sm mr-3'
	input.placeholder = 'category_name'

	span.className='badge badge-danger badge-pill';
	span.textContent='-';

	hidden.type='hidden';
	hidden.value = null;

	list.appendChild(input);
	list.appendChild(span);
	list.appendChild(hidden);

	span.addEventListener('click', removeThis, false);
	categoryList.appendChild(list);
}

function removeThis() {
	let input = this.parentNode.children[0];

	if(input.id !== 'newCategory') {
		fetch(`/rest/${userId}/deleteCategory/${input.id}`, {
 			method: 'DELETE',
		}).then(res => res.json())
		.then((res) => {
			if (res.result) {
				console.log(input.id + 'remove');
				categoryList.removeChild(this.parentNode);
			}
		});
	} else {
		console.log(input.id + 'remove');
		categoryList.removeChild(this.parentNode);
	}
}

function createAppendList(no, name, count, src) {
	let list = document.createElement("li");
	let anchor = document.createElement("a");
	let span = document.createElement("span");
	let input = document.createElement('input');

	anchor.setAttribute("href", src);
	anchor.setAttribute("role", "button");
	anchor.textContent = name;

	span.className = "badge badge-secondary badge-pill";
	span.textContent = count;
	
	input.type='hidden';
	input.value=no;

	list.className = "list-group-item d-flex justify-content-between align-items-center";

	list.appendChild(anchor);
	list.appendChild(span);
	list.appendChild(input);

	categoryList.appendChild(list);
}

function editButtons(flg) {
	if (categoryAdd == null)
		return;	
	
	if(flg) {	// true
		categoryAdd.className = 'badge badge-success badge-pill w-100 mb-1 d-none';
		categorySave.className = 'btn btn-secondary btn-sm float-right d-none';
		categoryEdit.className = 'btn btn-secondary btn-sm float-right';
	} else {	//false 
		categoryAdd.className = 'badge badge-success badge-pill w-100 mb-1';
		categorySave.className = 'btn btn-secondary btn-sm float-right';
		categoryEdit.className = 'btn btn-secondary btn-sm float-right d-none';
	}
}

function editCategory() {
	let children = [...categoryList.children];
	categoryList.innerHTML = '';

	for(let i = 1; i < children.length; ++i) {
		let list = document.createElement('li');
		let input = document.createElement('input');
		let span = document.createElement('span');
		let hidden = document.createElement('input');

		list.className='list-group-item d-flex justify-content-between align-items-center';

		input.id = children[i].children[0].textContent;
		input.type = 'text';
		input.className = 'form-control form-control-sm mr-3';
		input.placeholder = 'category_name';
		input.value = children[i].children[0].textContent;

		span.className='badge badge-danger badge-pill';
		span.textContent='-';
		
		hidden.type='hidden';
		hidden.value = children[i].children[2].value;

		list.appendChild(input);
		list.appendChild(span);
		list.appendChild(hidden);

		span.addEventListener('click', removeThis, false);
		categoryList.appendChild(list);
	}
	
	editButtons(false);
}

function saveCategory() {
	let children = [...categoryList.children];

	for(let i = 0; i < children.length; ++i) {
		let categoryName = children[i].children[0].value;
		let categoryNo = children[i].children[2].value;
		
		let uri;
		
		if (categoryNo !== null) {
			uri = `/rest/${userId}/UpdateCategory/${categoryName}/${categoryNo}`;
		} else {
			uri = `/rest/${userId}/UpdateCategory/${categoryName}`;
		}
		
		fetch(uri, {
 			method: 'PUT',
		}).then(res => res.json())
		.then((res) => {
			if (res.result) {
				console.log(categoryName + 'update');
			}
		});
	}
	
	window.location.href='/' + userId;
}

function initCategory() {
	categoryList.innerHTML = '';
	
	fetch(`/rest/${userId}/category/list`)
		.then(function(res) {
			return res.json();
		})
		.then(function(res) {
			let name = res.name;
			let count = res.count;
			let total = res.total;
			
			createAppendList(null, "total", total, `/${userId}`);

			for (let i = 0; i < name.length; ++i) {
				let src = `/${userId}/category/${name[i].categoryName}/1`;
				createAppendList(name[i].categoryNo, name[i].categoryName, count[i], src);
			}
		});
		
	editButtons(true);
}

function initUser() {
	//userBody.innerHTML = '';
	userFooter.innerHTML = '';
	
	fetch(`/rest/${userId}`)
		.then(function(res) {
			return res.json();
		})
		.then(function(res) {
			let user = res.user;
			let para = document.createElement('p');
			let small = document.createElement('small');
			
			para.className = 'mb-0';
			
			para.textContent = user.userName;
			small.textContent = user.userEmail;
			
			
			userFooter.appendChild(para);
			userFooter.appendChild(small);
		});
}

listeners();
initUser();
initCategory();