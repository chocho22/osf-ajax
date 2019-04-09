/**
 * 
 */
var CommonFunc = function() {
	this.callFunc = function() {
		alert('난 중요한 공통 함수!!');
		callFunc2();
	}
	var callFunc2 = function() {
		alert('난 외부에서 호출 안될걸');
	}
}
var AjaxUtil = function() {
	var xhr = new XMLHttpRequest();
	this.open = function(url, method, async) {
		method = method?method:'GET';
		async = async?async:true;
		xhr.open(method, url, async);
		xhr.onreadystatechange = function () {
			if(xhr.readyState == 4) {
				if(xhr.status == 200) {
					this.callback(xhr.response);
				}
			}
		}
	}
	
	xhr.callback = function(res) {
		console.log(res);
	}
	this.setCallback = function (call) {
		xhr.callback = call;
	}
	this.send = function() {
		xhr.send();
	}
}