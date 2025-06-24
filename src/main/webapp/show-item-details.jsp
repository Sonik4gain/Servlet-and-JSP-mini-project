<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Item Details Saved</title></head>
<body>
    <h2>Item Details Saved Successfully!</h2>
    <p>Item ID: <%= request.getParameter("itemId") %></p>
    <a href="add-item-details.jsp">Add Another</a>
</body>
</html>
