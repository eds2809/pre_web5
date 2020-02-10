<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: eds2809
  Date: 10.02.2020
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update User</title>
</head>
<body>
<table border="1" cellspacing="0" cellpadding="2">
    <br>
    <br>
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>password</td>
        <td>age</td>
        <td>role</td>
    </tr>
    <tr>
        <form method="get" action="${pageContext.request.contextPath}/admin/user">
            <td>
                <input type="text" name="id" value="${user.id}" hidden>
                ${user.id}
            </td>
            <td>
                <input type="text" name="name" value="${user.name}">
            </td>

            <td>
                <input type="text" name="pass" value="${user.pass}">
            </td>

            <td>
                <input type="number" name="age" value="${user.age}">
            </td>

            <td>
                <select name="role" autofocus>
                    <option value="user" ${user.role == 'user' ? 'selected' : ''}>user</option>
                    <option value="admin" ${user.role == 'admin' ? 'selected' : ''}>admin</option>
                </select>
            </td>

            <td>
                <button type="submit">update</button>
            </td>

        </form>
    </tr>
</table>

</body>
</html>
