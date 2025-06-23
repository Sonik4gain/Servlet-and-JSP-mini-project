<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Item Details</title>
</head>
<body>

<h2>Add Item Description</h2>

<form action="AddItemDetailsServlet" method="post">

    <label for="itemId">Item ID:</label>
    <input type="number" name="itemId" id="itemId" required />

    <label for="description">Description:</label>
    <textarea name="description" id="description" required></textarea>

    <button type="submit">Add Details</button>
</form>


</body>
</html>
