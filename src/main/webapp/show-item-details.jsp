<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.item.model.Item" %>
<%@ page import="com.item.model.ItemDetails" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Item Details</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f5f7fa;
            padding: 40px;
            margin: 0;
        }

        .container {
            background: white;
            max-width: 600px;
            margin: auto;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 8px 25px rgba(0,0,0,0.1);
        }

        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
            border-bottom: 3px solid #007bff;
            padding-bottom: 15px;
        }

        .item-info {
            background-color: #f8f9fa;
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 25px;
        }

        .info-row {
            display: flex;
            justify-content: space-between;
            margin-bottom: 15px;
            border-bottom: 1px solid #dee2e6;
            padding-bottom: 10px;
        }

        .info-label {
            font-weight: bold;
            color: #495057;
        }

        .info-value {
            color: #6c757d;
        }

        .description-section {
            background-color: #e3f2fd;
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 25px;
        }

        .description-title {
            font-weight: bold;
            color: #1976d2;
            margin-bottom: 15px;
            font-size: 18px;
        }

        .description-text {
            color: #333;
            line-height: 1.6;
            font-size: 16px;
        }

        .no-description {
            color: #dc3545;
            font-style: italic;
            text-align: center;
            padding: 20px;
        }

        .back-link {
            display: block;
            text-align: center;
            margin-top: 30px;
            color: #007bff;
            text-decoration: none;
            font-weight: bold;
            padding: 12px 25px;
            border: 2px solid #007bff;
            border-radius: 25px;
            transition: all 0.3s;
        }

        .back-link:hover {
            background-color: #007bff;
            color: white;
        }
    </style>
</head>
<body>
<div class="container">
    <%
        // TASK 4: Handle show-item-details.jsp to display selected item details
        Item item = (Item) request.getAttribute("item");
        if (item != null) {
    %>
        <h1>Item Details</h1>
        
        <!-- Display basic item information -->
        <div class="item-info">
            <div class="info-row">
                <span class="info-label">ID:</span>
                <span class="info-value"><%=item.getId()%></span>
            </div>
            <div class="info-row">
                <span class="info-label">Name:</span>
                <span class="info-value"><%=item.getName()%></span>
            </div>
            <div class="info-row">
                <span class="info-label">Price:</span>
                <span class="info-value">$<%=item.getPrice()%></span>
            </div>
            <div class="info-row">
                <span class="info-label">Total Number:</span>
                <span class="info-value"><%=item.getTotalNumber()%></span>
            </div>
        </div>

        <!-- TASK 4: Display description only if the item has description -->
        <%
            if (item.getDescription() != null && !item.getDescription().trim().isEmpty()) {
        %>
            <div class="description-section">
                <div class="description-title">Description:</div>
                <div class="description-text"><%=item.getDescription()%></div>
            </div>
        <%
            } else {
        %>
            <div class="no-description">
                This item has no description available.
            </div>
        <%
            }
        %>
    <%
        } else {
    %>
        <h1>Error</h1>
        <div class="no-description">
            Item not found or invalid item ID.
        </div>
    <%
        }
    %>
    
    <a href="ItemController?action=load-items" class="back-link">← Back to Items List</a>
</div>
</body>
</html>