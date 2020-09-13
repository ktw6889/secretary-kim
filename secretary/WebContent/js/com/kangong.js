
var kangong = kangong || {};


kangong.check = {
	isNull : function(str){
			if(str == '' || str == null ||  str == undefined || str == 0 ||  str ==NaN){ // ...
				return true;
			}else{
				return false;
			}
	},
	isNullObject : function(obj){
		if(obj == null ||  obj == undefined || Object.keys(obj).length === 0){
			return true;
		}else{
			return false;
		}
	}
};

kangong.form = {
	submitPost : function(url, params) {
		method = "post";
		var form = document.createElement("form");
		form.setAttribute("method", method);
		form.setAttribute("action", url);

		for(var key in params) {
			var hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", key);
			hiddenField.setAttribute("value", params[key]);
			form.appendChild(hiddenField);
		}

		document.body.appendChild(form);
		form.submit();
	}


};

//Form Input Object 만들기
	(function($) {
		/*  Input Object 만들기
		 *
		 * options
		 *  strArray:['strsHobby']   form명 strsHobby를 Object의 배열로 넣는다
		 *  loopTagName:'tr'  'tr'태그 loop를 돌면서 input tag를 Objec로 만듦
		 */
		$.fn.inputToObject = function(options) {
			var obj = null;
			var arr = $(this).find(":input").checkSelectValue();
			obj = toObject(arr,options);
			return obj;
		};

		/* Input을 Array로 담아오기
		 *
		 */
		$.fn.inputToArray = function(options) {
			var arrayObj = new Array();

			$(this).find(options.loopTagName).each( function(index) {
				var arr =  $(this).find(":input").checkSelectValue();
				var obj = toObject(arr,options);
				arrayObj.push(obj);
			});

			return arrayObj;
		};


		/*
		 * Object 만들기
		 *
		 * arr : Array
		 * options
		 *   strArray : input name이 strArray에 속해있으면 array로 담아 옮
		 */
		function toObject(arr, options){
			var obj = {};
			if(arr){
				jQuery.each(arr, function() {
					if(!kangong.check.isNullObject(options) && options.hasOwnProperty("strArray") && options.strArray.indexOf(this.name) > -1 )
					{
						if(obj.hasOwnProperty(this.name)){
							obj[this.name].push(this.value);
						}else{
							obj[this.name] = [this.value];
						}
					}else{
						obj[this.name] = this.value;
					}

				});
			}
			return obj;
		};

		/*
		 * check된 값만 array만 담아서 옮
		 * 현재는 checkbox만
		 */
		$.fn.checkSelectValue = function(){
			var resultArray = new Array();
			var self = this;
			$(self).each( function(index,item) {
				if($(this).attr("type")=="checkbox" && !$(this).is(":checked") ){
				}else{
					resultArray.push(this);
				}
			});
			return resultArray;
		}
	 }(jQuery));

//table  addrow, deleterow
(function($) {
	$.fn.addRow = function(templateId) {
		var templateUserInfoItemTr = $.templates("#"+templateId);

		var htmlUserInfoItemTr = templateUserInfoItemTr.render();
		$(this).find("tr").each(function(index){
			// td가 1개 인 것 삭제 (데이터가 없습니다)
			if( $(this).find("td").length==1) $(this).remove();
		});
		$(this).append(htmlUserInfoItemTr);
	};
	$.fn.deleteRow = function(checkboxName, rowFlagName) {
		if(!checkboxName) checkboxName = 'checkBoxList';
		if(!rowFlagName) rowFlagName = 'rowFlag';

		$(this).find("input[name='"+checkboxName+"']:checked").each(function (i){
			$trObj = $(this).parents("tr:first");
			$rowFlagObj = $trObj.find("input[name='"+rowFlagName+"']");

			if($rowFlagObj.val() == 'C') {
				$trObj.remove();
			}else{
				$rowFlagObj.val('D');
			}

			if($(this).next().val() == 'C') {
				$(this).parents("tr:first").remove();
			} else {
				$(this).next().val('D');
				$trObj.css("display","none");
			}
		});
	};

}(jQuery));


/**
var kangong = kangon || {};

kangong.util = {

};


var kangong_myobj; // 이 변수에 객체를 할당한다.

(function(){
	// 비공개 멤버
	var name = "my, oh my";
	// 공개될 부분을 구현한다.
	// var를 사용하지 않는다.

	kangong_myobj = {
		//특권 메서드
		getName : function(){
			return name;
		}
	};
}());

//myobj.getName();

var kangong.myObj2 = function(){
	// 고유 멤버(private member)가 될 스코프
	var sayHi = "안녕하세요!!";
	var intCnt = 0;
	var hi = function() {
		intCnt += 1; // hi 함수를 호출할 때마다 1씩 증가하도록 함
		return sayHi;
		}; var

		cnt = function() {
			return intCnt; // closure를 통해서 증가된 intCnt값을 반환하도록 해준다.
			}
			// 특권/공용메소드와 프로퍼티 (공용 인터페이스)=>외부에 공개하기 위한 역할
			// 객체 리터럴을 함수 값으로 반환(반환되는 객체리터럴에는 공용이 될 프로퍼티와 메소드만 소유)
			return {
				getHi : function() {
					return sayHi; // 고유멤버에 접근이 가능(특권 메소드)
					},
				getHi2 : function() {
					return '반갑습니다~~!!';
					},
				getHi3 : hi,
				getCnt : intCnt,
				// intCnt를 직접 외부에 반환시켜주면 증가된 값이 출력되지 않는다.
				getCnt2 : cnt
			 }
	}();


var kangong_arr;
(function () {
	var astr = '[object Array]';
	var toString = Object.prototype.toString;
	function isArray(a) {
		return toString.call(a) === astr;
	}
	function indexOf(haystack, needle) {
		var i = 0, max = haystack.length;
		for (; i < max; i += 1) {
			if (haystack[i] === needle ) {
				return i;
			}
		}
		return -1;
	}
	kangong_arr = { isArray : isArray, indexOf : indexOf, inArray : indexOf }
})();


kangon.utilities.array = function () {
	return {
		inArray : function (needle, haystack) {
			// ...
		},
		isArray : function (a) {
			// ...
		}
	}
}();

 */



