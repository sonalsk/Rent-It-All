<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    	
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Rent It All Items</title>
</head>

<body>
	
	<h1 style="text-align:center;"> ITEMS FOR RENTING </h1>
	<h2 style="text-align:center;">
		<a href="/new">Add New Item</a>
		&nbsp;&nbsp;&nbsp;
		<a href="/list">List All Items</a>
	</h2>
	
	<div align="center">
        <table border="1">
            <caption>
            	 List of Items
            </caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Category</th>
                <th>Price</th>
                <th>Duration</th>
                <th>Security Deposit </th>
                <th>Actions</th>
            </tr>
            
            <c:forEach var="ITEMS" items="${listItem}">
                <tr>
                    <td><c:out value="${ITEMS.ID}" /></td>
                    <td><c:out value="${ITEMS.Name}" /></td>
                    <td><c:out value="${ITEMS.Category}" /></td>
                    <td><c:out value="${ITEMS.PricePerDay}" /></td>
                    <td><c:out value="${ITEMS.Duration}" /></td>
                    <td><c:out value="${ITEMS.SecurityDepo}" /></td>
                    <td>
                        <a href="/edit?ID=<c:out value='${ITEMS.ID}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/delete?id=<c:out value='${ITEMS.ID}' />">Delete</a>                     
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   

</body>
</html>