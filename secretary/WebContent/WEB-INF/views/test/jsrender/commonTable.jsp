<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/include-header.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jsrender-1.0.7.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.14.1/moment.min.js"></script>
<script src="https://www.jsviews.com/download/jsviews.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/underscore@1.11.0/underscore-min.js"></script>

<table id="table" class="table">
</table>
<script type="text/x-jsrender" id="templateHeadTr">
<tr>
	{{if dataList}}
		 {{for dataList}}
			<td>
				{{:columnCommnet}}
			</td>
		 {{/for}}
	{{/if}}
</tr>
</script>

<script type="text/x-jsrender" id="templateItemTr">
<tr>
	{{if dataList}}
		 {{for dataList}}
			<td>
				<input type="{{:inputType}}" name="{{:columnName}}" max="{{:columnMax}}" value="{{:columnName}}" />
			</td>
		 {{/for}}
	{{/if}}
</tr>
</script>

<script type="text/x-jsrender" id="templateItemTrResult">
</script>

<script type="text/x-jsrender" id="templateTr">
<tr>
	{{if resultList}}
		 {{for resultList}}
			<td>
				{{include tmpl="#templateItemTrResult"/}}
			</td>
		 {{/for}}
	{{/if}}
</tr>
</script>

<script>
var testData = {
		dataList : [
			{ columnName:"userName", inputType:"text", columnMax:"10", columnCommnet:"사용자명", orderNo:1},
			{ columnName:"age", inputType:"number", columnMax:"10", columnCommnet:"나이", orderNo:2},
			{ columnName:"city", inputType:"text", columnMax:"5", columnCommnet:"도시", orderNo:3}
		]
};

var userData = {
		resultList : [
			{userName:"김태우",age:"48",city:"부산",orderNo:1},
			{userName:"김송윤",age:"46",city:"부산",orderNo:2},
			{userName:"김예똥",age:"15",city:"서울",orderNo:3},
			{userName:"김예뽕",age:"14",city:"수원",orderNo:4}
		]
};
</script>

<script>
var uudata = [{userName:"김태우",age:"48",city:"부산",orderNo:1}];
var ccdata = {columnName:"userData", columnValue:"김태우"};
var edata = _.extend({},uudata[0],ccdata);
console.log("edata",edata);
var keyArray = new Array();
var resultData2 = $.map(Object.keys(uudata), function(data,idx){
	 keyArray.push(data);
});
//console.log("keyArray",keyArray);
//console.log("resultData2",resultData2);

var tdData =  _.map(userData.resultList, function(udata){
	 return $.map(Object.keys(udata), function(data){
		//return {[data] :udata[data]};
		return {columnName : data, columnValue : udata[data]};
	});

});
console.log("tdData",tdData);

var tdColumnData = _.map(tdData, function(udata){
	//console.log("udata",udata);
	return _.map(udata, function(u2data){
		return $.extend({}, _.filter(testData.dataList,function(columnData){
			return columnData.columnName == u2data.columnName;
		})[0], u2data) ;
	});
});
console.log("tdColumnData",tdColumnData);
/* var ttt=	 _.map(Object.keys(udata),function(ukey){
		 return _.extend({
			  column: _.find(testData.dataList, function(column){
				return ukey ==  column.columnName;
			 })
		 }, {columnValue : udata[ukey]});
	 });
console.log(ttt); */
	/* var comment2 = _map(comments, function(comment){
            return _.extend({
                user: _find(users, function(user){
                    return user.id == comment.user_id;
                })
            },comment)
        });
     */


var temp =  _.find(testData.dataList, function(column){
	return 'userName' ==  column.columnName;
});

console.log("temp",temp);

var temp2 = $.extend({},temp,{columnValue:"김태우"});
console.log("temp2",temp2);


$(document).ready(function(){
	//헤더 생성
	var templateHeadTr = $.templates("#templateHeadTr");
	var htmlHeadTr = templateHeadTr.render(testData);
	$("#table").append(htmlHeadTr);

	//Column정보로 Tr템플릿 만들기
	var templateItemTr = $.templates("#templateItemTr");
	var htmlItemTr = templateItemTr.render(testData);
	$("#templateItemTrResult").append(htmlItemTr);

	//value={{:columnName}} 테플릿에 넣기
	$("#templateItemTrResult").html(generateTemplateTr("templateItemTrResult"));

	//Data 셋업
	var templateTr = $.templates("#templateTr");
	var htmlTr = templateTr.render(userData);
	$("#table").append(htmlTr);
});

function generateTemplateTr(idName){
	var trHtml = "<tr>";
	$("#"+idName).find(":input").each(function(){
		trHtml += "<td>";
		trHtml += "<input type='"+this.type+"' name='"+this.name+"' max='"+this.max+"' value='{{:"+this.name+"}}' />";
		trHtml += "</td>";
	});
		trHtml += "</tr>";
	return trHtml;
}

</script>



<div id="result"></div>

<script id="theTmpl" type="text/x-jsrender">
<div>
  <label>Edit: <input type="checkbox" data-link="editable"/></label>
  <em>Name:</em> {^{>name}}
  {^{if showNickname && nickname}}
    (Goes by <em data-link="nickname"></em>)
  {{/if}}
  {^{if editable}}
    <div>
      <input data-link="name"/>
      <input data-link="nickname"/>
      Show nickname: <input type="checkbox" data-link="showNickname"/>
    </div>
  {{/if}}
</div>
</script>

<script>
var data = [
  {
    "name": "Robert",
    "nickname": "Bob",
    "showNickname": true
  },
  {
    "name": "Susan",
    "nickname": "Sue",
    "showNickname": false
  }
];

var template = $.templates("#theTmpl");

template.link("#result", data);
</script>