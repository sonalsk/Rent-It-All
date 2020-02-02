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
	<h1 style="text-align:center;">Items Management</h1>
        <h2 style="text-align:center;">
            <a href="/new">Add New Item</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/list">List All Items</a>
             
        </h2>
        
        <div align="center">
        <c:if test="${ITEMS != null}">
            <form action="update" method="post"> 
        </c:if>
        <c:if test="${ITEMS == null}">
            <form action="insert" method="post"> 
        </c:if>
        
        <table border="1">
            <caption>
                <h2>
                    <c:if test="${ITEMS != null}">
                        Edit Item
                    </c:if>
                    <c:if test="${ITEMS == null}">
                        Add New Item
                    </c:if>
                </h2>
            </caption>
                <c:if test="${ITEMS != null}">
                    <input type="hidden" name="id" value="<c:out value='${ITEMS.ID}' />" />
                </c:if>           
            <tr>
                <th>Name: </th>
                <td>
                    <input type="text" name="name" size="45"
                            value="<c:out value='${ITEMS.Name}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Category: </th>
                <td>
                    <input type="text" name="category" size="45"
                            value="<c:out value='${ITEMS.Category}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Price: </th>
                <td>
                    <input type="text" name="price" size="5"
                            value="<c:out value='${ITEMS.PricePerDay}' />"
                    />
                </td>
            </tr>
            
            <tr>
                <th>Duration: </th>
                <td>
                    <input type="text" name="duration" size="5"
                            value="<c:out value='${ITEMS.Duration}' />"
                    />
                </td>
            </tr>
            
            <tr>
                <th>Security Deposit: </th>
                <td>
                    <input type="text" name="deposit" size="5"
                            value="<c:out value='${ITEMS.SecurityDepo}' />"
                    />
                </td>
            </tr>
            
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>