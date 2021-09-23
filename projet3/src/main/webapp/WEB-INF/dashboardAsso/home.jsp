<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" --%>
<%--     pageEncoding="UTF-8"%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
Je suis le dashboard Association
${result}
<br />
Informations association connectée
<ul>
<li>${association.id}</li>
<li>${association.name}</li>
<li>${association.address}</li>
<li>${association.rna}</li>
</ul>


</body>
</html>