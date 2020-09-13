<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/include-header.jsp" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/board.css" />

<h2>게시판 목록</h2>
<article>
<div class="container">
<div class="table-responsive">
<table class="table table-striped table-sm">
		<colgroup>
			<col style="width:5%;" />
			<col style="width:auto;" />
			<col style="width:15%;" />
			<col style="width:10%;" />
			<col style="width:10%;" />
		</colgroup>
		<thead>
			<tr>
				<th>NO</th>
				<th>글제목</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${empty boardList }" >
					<tr><td colspan="5" align="center">데이터가 없습니다.</td></tr>
				</c:when>
				<c:when test="${!empty boardList}">
					<c:forEach var="list" items="${boardList}">
						<tr>
							<td><c:out value="${list.id}"/></td>
							<td> <a href="javascript:kangong.board.openBoard('${list.id}');"> <c:out value="${list.title}"/> </a></td>
							<td><c:out value="${list.createUser}"/></td>
							<td><c:out value="${list.viewCnt}"/></td>
							<td><c:out value="${list.createDate}"/></td>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>
		</tbody>
	</table>
	</div>

	<div >
		<button type="button" class="btn btn-sm btn-primary" id="btnWriteForm">글쓰기</button>
   </div>

   <kangong:paging url="${pageContext.request.contextPath}/board/list.do" page="${pagination.page}" range="${pagination.range}" rangeSize="${pagination.rangeSize}" startPage="${pagination.startPage}" endPage="${pagination.endPage}" next="${pagination.next}" prev="${pagination.next}" />


   <!-- search{s} -->
		<div class="form-group row justify-content-center">
			<div class="w100" style="padding-right:10px">
				<select class="form-control form-control-sm" name="searchType" id="searchType">
					<option value="title">제목</option>
					<option value="content">본문</option>
					<option value="createrUser">작성자</option>
				</select>
			</div>

			<div class="w300" style="padding-right:10px">
				<input type="text" class="form-control form-control-sm" name="keyword" id="keyword">
			</div>

			<div>
				<button class="btn btn-sm btn-primary" name="btnSearch" id="btnSearch">검색</button>
			</div>
		</div>
 <!-- search{e} -->



</div>
</article>

<c:url var="listURL" value="/board/list.do"></c:url>
<c:url var="editURL" value="/board/edit.do"></c:url>
<c:url var="viewURL" value="/board/view.do"></c:url>

<script>
$( document ).ready(function() {
	$("#btnWriteForm").on('click', function(e){
		e.preventDefault();
		kangong.form.submitPost('${editURL}');
	});

	$('#btnSearch').on('click', function(e){
		e.preventDefault();
		var paramObj ={};
		 paramObj[$('#searchType').val()] =  $('#keyword').val();

		kangong.form.submitPost('${listURL}',paramObj);
	});

});

kangong.board = {
	openBoard :function (id){
			var paramObj ={};
			paramObj.id =  id;

			kangong.form.submitPost('${viewURL}',paramObj);
		}
}
<!--
function openBoard2(id){
	var form = document.createElement('form');
	var objs;
	objs = document.createElement('input');
	objs.setAttribute('type', 'hidden');
	objs.setAttribute('name', 'id');
	objs.setAttribute('value', id);
	form.appendChild(objs);

	form.setAttribute('method', 'post');
	form.setAttribute('action', "${pageContext.request.contextPath}/board/view.do");
	document.body.appendChild(form);

	form.submit();
}
-->

</script>