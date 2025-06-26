<%@page import="java.util.ArrayList"%>
<%@page import="com.item.model.Item"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Show Items</title>
    <link rel="stylesheet" href="css/show-items.css">
</head>
<body>
<div class="layer">
    <table>
        <h1>Items</h1>
        <thead>
        <tr>
            <th>ID</th>
            <th>NAME</th>
            <th>PRICE</th>
            <th>TOTAL_NUMBER</th>
            <th>DESCRIPTION</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <%
        List<Item> items = (List<Item>) request.getAttribute("itemsData");
        if (items != null) {
            for(Item item: items){
        %>
        <tr>
            <td><strong><%=item.getId() %></strong></td>
            <td><%=item.getName() %></td>
            <td><%=item.getPrice() %></td>
            <td><%=item.getTotalNumber()%></td>
            <td>
                <!-- ADDED: Show description if exists, otherwise show "No Description" -->
                <%
                    if (item.getDescription() != null && !item.getDescription().trim().isEmpty()) {
                %>
                    <%=item.getDescription().substring(0, Math.min(item.getDescription().length(), 50))%>
                    <% if (item.getDescription().length() > 50) { %>...<%}%>
                <%
                    } else {
                %>
                    <em>No Description</em>
                <%
                    }
                %>
            </td>
            <td>
                <a href='ItemController?action=update-item-form&id=<%=item.getId() %>'>Update</a>
                <a href='ItemController?action=remove-item&id=<%=item.getId() %>'>Delete</a>
                
                <!-- TASK 1 & 2: Add "Add Description" action only if item has no existing details -->
                <%
                    if (item.getDescription() == null || item.getDescription().trim().isEmpty()) {
                %>
                    <a href="ItemDetailsController?action=show-add-form&itemId=<%=item.getId() %>">Add Description</a>
                <%
                    } else {
                        // TASK 3: Add "View Details" action to redirect to show-item-details.jsp
                %>
                    <a href="ItemController?action=show-details&id=<%=item.getId() %>">View Details</a>
                <%
                    }
                %>
            </td>
        </tr>
        <%
            }
        }
        %>
        </tbody>
    </table>

    <button class="f"><a href="add-item.html">Add Item</a></button>
</div>
</body>
</html>