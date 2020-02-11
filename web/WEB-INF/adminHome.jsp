<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form>
    <input type="button" value="Профиль" onClick='location.href="${pageContext.request.contextPath}/user/home"'>
</form>
<br>
<table border="1" cellspacing="0" cellpadding="2">
    <tr>
        <td>Name</td>
        <td>password</td>
        <td>age</td>
    </tr>
    <form id="addForm" method="post" action="${pageContext.request.contextPath}/admin/home" style="margin: 0;">
        <tr>
            <td>
                <input type="text" name="name">
            </td>

            <td>
                <input type="text" name="pass">
            </td>

            <td>
                <input type="number" name="age">
            </td>

            <td>
                <select form="addForm" name="role" autofocus>
                    <option value="user">user</option>
                    <option value="admin">admin</option>
                </select>
            </td>

            <td>
                <button type="submit">add</button>
            </td>
        </tr>
    </form>
</table>

<c:if test="${fn:length(users) > 0}">
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

        <jsp:useBean id="users" scope="request" type="java.util.List"/>
        <c:forEach items="${users}" var="user">

                <tr>
                    <td>
                            ${user.id}
                    </td>
                    <td>
                            ${user.name}
                    </td>

                    <td>
                            ${user.pass}
                    </td>

                    <td>
                            ${user.age}
                    </td>

                    <td>
                            ${user.role}
                    </td>

                    <td>
                        <input type="button" value="update" onClick='location.href="${pageContext.request.contextPath}/admin/update/"+${user.id}'>
                    </td>

                    </form>
                    <td>
                             <form method="post" action="${pageContext.request.contextPath}/admin/user" style="margin: 0;">
                                 <input type="text" name="id" value="${user.id}" hidden>
                                 <button type="submit">delete</button>
                             </form>
                    </td>
                </tr>

        </c:forEach>
    </table>
</c:if>

