<%@ page
        contentType="text/html"
        pageEncoding="UTF-8"
%>
<html>
<head>
  <title>Title</title>
</head>
<body>
<h1>로그인 서세스!!</h1>
<b>아이디:</b><span>${sessionScope.user.id}</span> <br>
<b>패스워드:</b><span>${sessionScope.user.pw}</span> <br>
<b>이름:</b><span>${sessionScope.user.name}</span> <br>
<a href="/user/logout">로그아웃</a>



</body>
</html>
