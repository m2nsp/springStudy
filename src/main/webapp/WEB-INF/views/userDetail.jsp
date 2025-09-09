<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>User Detail</title>
</head>
<body>
<h2>User Detail</h2>

<p>ID: ${user.id}</p>
<p>Name: ${user.name}</p>
<p>Email: ${user.email}</p>

<h3>Edit User</h3>
<form action="${pageContext.request.contextPath}/user/update" method="post">
    <input type="hidden" name="id" value="${user.id}"/>
    Name: <input type="text" name="name" value="${user.name}"/><br/><br/>
    Email: <input type="text" name="email" value="${user.email}"/><br/><br/>
    <input type="submit" value="Update User"/>
</form>

<br/>
<a href="${pageContext.request.contextPath}/user/list">Back to List</a>
</body>
</html>
