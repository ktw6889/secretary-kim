<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/include-header.jsp" %>


<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Main</title>

</head>
<body>
  Hello World!!

  <spring:eval expression="@configProperties['mysql.username']"> </spring:eval>
  <p>LABEL.APPNAME : <spring:message code="LABEL.APPNAME" text="default text" /></p>

  <p> <a href="<c:url value="/i18n.do?lang=ko" />">한국어</a> <a href="<c:url value="/i18n.do?lang=en" />">English</a>
  <p>site.count using EL : <spring:message code="LABEL.APPNAME" arguments="${siteCount}" text="default text" /></p>
  ${siteCount}



	<kang:kangspan color="yellow" iterNum="5">
        ktw
    </kang:kangspan>

    <%
    request.setAttribute("sumResult2", 100);
%>

   <!--태그실행 -->
    <kangong:sum  num1="300" num2="50" />

    <!-- 실행후 공유된 변수값 출력 -->
    덧셈 결과 : ${sumResult}<br/>

     <!--태그실행 -->
    <kangong:sum2 attrName="mySumResult" num1="30" num2="10" />

    <!-- 실행후 공유된 변수값 출력 -->
    덧셈 결과 : ${mySumResult}<br/>

    <kangong:dynamicSelect banana="바나나" apple="사과" grape="포도"   />

	<kangong:tospan color="blueviolet" iterNum="5">
        hello
    </kangong:tospan>

	<kang:kangspan color="yellow" iterNum="5">
        ktw
    </kang:kangspan>

</body>
</html>