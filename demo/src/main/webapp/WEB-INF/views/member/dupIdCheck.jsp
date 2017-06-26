<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
function goId(id, dupCheck){
	if(dupCheck){
		wondow.opener.fr.id.value=id;
		window.close();
	}else{
		alert('중복된 아이디입니다.');
	}
	
}
</script>
</head>
<body>
<form action="<c:url value='/memebr/dubIdCheck'/>">
아이디: <input type="text">
<input type="submit" value="중복확인">
<c:if test="${dupCheck}">
<input type="button" value="사용하기" onclick="goId('${dupId}','${dupCheck}')">
</c:if>
</form>
</body>
</html>