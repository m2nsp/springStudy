<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Add User</title>
</head>
<body>
<h2>Add User</h2>

<form action="${pageContext.request.contextPath}/user/add" method="post">
    Name: <input type="text" name="name"/><br/><br/>
    Email: <input type="text" name="email"/><br/><br/>
    <input type="submit" value="Add User"/>
</form>

<br/>
<a href="${pageContext.request.contextPath}/user/list">Back to List</a>
</body>
</html>
