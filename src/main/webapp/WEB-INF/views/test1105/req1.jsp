<%@ page
        contentType="text/html"
        pageEncoding="UTF-8"
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<section>
    <h1>받은 person: ${person} <br> </h1>
    name: ${person.name} <br>
    age: ${person.age} <br>
</section>

<section>
    <h1>받은 person2: ${person2} <br></h1>
    name: ${person2.name} <br>
    age: ${person2.age} <br>
</section>

<section>
    <h1>받은 person3: ${person3} <br></h1>
    name: ${person3.name} <br>
    age: ${person3.age} <br>
</section>

<section>
    <h1>받은 person4: ${person4} <br></h1>
    name: ${person4.name} <br>
    age: ${person4.age} <br>
</section>
<hr>
<h1>세션 사용하기</h1>
<section>
    <b>session데이터:</b> ${sessionScope.data}
</section>




</body>
</html>
