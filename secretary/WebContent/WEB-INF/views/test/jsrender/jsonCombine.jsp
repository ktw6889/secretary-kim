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
	{{if trList}}
		 {{for trList}}
			<tr>
				{{if tdList}}
		 			{{for tdList}}
						<td>
							<input type="{{:inputType}}" name="{{:columnName}}" max="{{:columnMax}}" value="{{:columnValue}}" />
						</td>
		 			{{/for}}
				{{/if}}
			</tr>
    	{{/for}}
	{{/if}}
</script>


<script type="text/x-jsrender" id="templateTr">
	{{if trList}}
		 {{for trList}}
				{{include tmpl="#templateItemTr"/}}
		 {{/for}}
	{{/if}}

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




$(document).ready(function(){
	//헤더 생성
	var templateHeadTr = $.templates("#templateHeadTr");
	var htmlHeadTr = templateHeadTr.render(testData);
	$("#table").append(htmlHeadTr);

	//Json 데이터 합치기
	var combineData = getCombineJson(userData.resultList, testData.dataList);

	//Column정보로 Tr템플릿 만들기
	var templateItemTr = $.templates("#templateItemTr");
	var htmlItemTr = templateItemTr.render(combineData);

	$("#table").append(htmlItemTr);
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

function getCombineJson(userList, columnList){
	var tdData =  _.map(userList, function(udata){
		 return $.map(Object.keys(udata), function(data){
			//return {[data] :udata[data]};
			return {columnName : data, columnValue : udata[data]};
		});
	});

	var tdColumnData = _.map(tdData, function(udata){
		//console.log("udata",udata);
		var tdArray= _.map(udata, function(u2data){
			return $.extend({}, _.filter(columnList,function(columnData){
				return columnData.columnName == u2data.columnName;
			})[0], u2data) ;
		});
		return {tdList : tdArray};
	});

	return{trList :tdColumnData };
}

var combineData = getCombineJson3(userData.resultList, testData.dataList);
console.log("combineData",combineData);


function getCombineJson2(userList, columnList){
	var tdData =  _.map(userList, function(udata){
		 return $.map(Object.keys(udata), function(data){
			//return {[data] :udata[data]};
			return {columnName : data, columnValue : udata[data]};
		});
	});

	var tdColumnData = _.map(tdData, function(udata){
		//console.log("udata",udata);

		var tdArray = secondMap();
		return {tdList : tdArray};

		function secondMap(){
			return  _.map(udata, function(u2data){
				return $.extend({}, _.filter(columnList,function(columnData){
					return columnData.columnName == u2data.columnName;
				})[0], u2data) ;
		});
		};
	});

	return {trList :tdColumnData };
}

function getCombineJson3(userList, columnList){
	var tdData =  _.map(userList, function(udata){
		 return $.map(Object.keys(udata), function(data){
			//return {[data] :udata[data]};
			return {columnName : data, columnValue : udata[data]};
		});
	});

	var tdColumnData = _.map(tdData, function(udata){
		//console.log("udata",udata);
		return _.map(udata, function(u2data){
			return $.extend({}, _.filter(columnList,function(columnData){
				return columnData.columnName == u2data.columnName;
			})[0], u2data) ;
		});
	});

	return tdColumnData;
}
</script>
