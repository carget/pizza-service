<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="h.jspf" %>
<h1>EDIT PIZZA</h1>
<table>
    <th>id</th>
    <th>name</th>
    <th>price</th>
    <th>type</th>
    <tr>
    <form action="${pageContext.request.contextPath}/app/save/" method="post">
        <td><input type="hidden" name="id" value="${pizza.id}"></td>
        <td><input type="text" name="name" value="${pizza.name}"></td>
        <td><input type="text" name="price" value="${pizza.price}"></td>
        <td><input type="text" name="type" value="${pizza.type}"></td>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
           <button type="submit">SAVE</button>
    </form>
    </tr>
</table>

