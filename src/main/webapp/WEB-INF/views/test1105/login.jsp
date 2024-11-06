<%@ page
        contentType="text/html"
        pageEncoding="UTF-8"
%>
<html>
<head>
  <title>Title</title>
</head>
<body>
<form action="/user/login" method="post">
  <label>
    <b>아이디</b> <input type="text" name="id">
  </label>
  <label>
    <b>비밀번호</b> <input type="text" name="pw">
  </label>
  <label>
    <b>이름</b> <input type="text" name="name">
  </label>
  <button>로그인</button>
  <a href="/user/register">회원가입</a>
</form>
</body>
</html>
