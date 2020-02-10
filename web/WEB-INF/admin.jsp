<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<label>Вы успешно авторизовались как </label><label><c:out value="${sessionScope.role}"/></label><br><br>
<table border="1" cellspacing="0" cellpadding="2">
    <tr>
        <td>Name</td>
        <td>password</td>
        <td>age</td>
    </tr>
    <form method="post" action="/web5/admin" style="margin: 0;">
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
                <input type="text" name="role" value="user">
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
        <c:forEach items="${users}" var="user">

                <tr>
                    <form method="get" action="/web5/admin/user/" style="margin: 0;">
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
                        <input type="text" name="role" value="${user.role}">
                    </td>

                    <td>
                        <button type="submit">update</button>
                    </td>

                    </form>
                    <td>
                             <form method="post" action="/web5/admin/user/" style="margin: 0;">
                                 <input type="text" name="id" value="${user.id}" hidden>
                                 <button type="submit">delete</button>
                             </form>
                    </td>
                </tr>

        </c:forEach>
    </table>
</c:if>

