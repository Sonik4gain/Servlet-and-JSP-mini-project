<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Item Details</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; }
        .container { max-width: 600px; margin: auto; border: 1px solid #ccc; padding: 20px; border-radius: 8px; }
        h2 { text-align: center; }
        p { font-size: 1.1em; }
        a { display: block; text-align: center; margin-top: 20px; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Item Details</h2>
        <c:if test="${not empty item}">
            <p><strong>ID:</strong> ${item.id}</p>
            <p><strong>Name:</strong> ${item.name}</p>
            <p><strong>Price:</strong> ${item.price}</p>
            <p><strong>Total Number:</strong> ${item.totalNumber}</p>
            <p><strong>Description:</strong> ${item.description}</p>
        </c:if>
        <c:if test="${empty item}">
            <p>Item details not found.</p>
        </c:if>
        <a href="${pageContext.request.contextPath}/ItemController?action=load-items">Back to Items List</a>
    </div>
</body>
</html>