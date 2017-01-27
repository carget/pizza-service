<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="h.jspf" %>
<table>
    <th>id</th>
    <th>name</th>
    <th>price</th>
    <th>type</th>
    <th>edit</th>
    <c:forEach var="pizza" items="${requestScope.pizzas}">
        <tr>
            <td>${pizza.id}</td>
            <td>${pizza.name}</td>
            <td>${pizza.price}</td>
            <td>${pizza.type}</td>
            <td>
            <form action="${pageContext.request.contextPath}/app/edit/${pizza.id}" method="get">
                 <button type="submit">EDIT</button>
            </form>
            </td>
        </tr>
    </c:forEach>
</table>

