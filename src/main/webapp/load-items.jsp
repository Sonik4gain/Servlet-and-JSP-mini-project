<%@ page import="java.util.List" %>
<%@ page import="com.item.model.Item" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Items List</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background: linear-gradient(145deg, #ffe6e6, #ffffff);
            margin: 0;
            padding: 30px;
        }

        .layer {
            width: 90%;
            margin: auto;
            background: white;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15);
        }

        h1 {
            text-align: center;
            color: #d32f2f; /* red */
            margin-bottom: 25px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            font-size: 15px;
        }

        thead {
            background-color: #d32f2f;
            color: white;
        }

        th, td {
            padding: 14px 16px;
            text-align: left;
            border-bottom: 1px solid #ccc;
        }

        tbody tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tbody tr:hover {
            background-color: #ffe6e6;
        }

        a {
            color: #d32f2f;
            font-weight: bold;
            text-decoration: none;
            margin-right: 10px;
        }

        a:hover {
            text-decoration: underline;
        }

        .f {
            text-align: center;
            margin-top: 30px;
        }

        .f a {
            display: inline-block;
            padding: 12px 30px;
            background-color: #d32f2f;
            color: white;
            border-radius: 5px;
            font-size: 16px;
            text-decoration: none;
            transition: background 0.3s ease;
        }

        .f a:hover {
            background-color: #b71c1c;
        }

        em {
            color: #999;
        }
    </style>
</head>
<body>
<div class="layer">
    <h1>Items</h1>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>NAME</th>
            <th>PRICE</th>
            <th>TOTAL</th>
            <th>DESCRIPTION</th>
            <th>ACTION</th>
        </tr>
        </thead>
        <tbody>
        <%
        List<Item> items = (List<Item>) request.getAttribute("itemsData");
        if (items != null) {
            for (Item item : items) {
        %>
        <tr>
            <td><%= item.getId() %></td>
            <td><%= item.getName() %></td>
            <td>$<%= item.getPrice() %></td>
            <td><%= item.getTotalNumber() %></td>
            <td>
                <%
                    String desc = item.getDescription();
                    if (desc != null && !desc.trim().isEmpty()) {
                        out.print(desc.length() > 50 ? desc.substring(0, 50) + "..." : desc);
                    } else {
                        out.print("<em>No Description</em>");
                    }
                %>
            </td>
            <td>
                <a href="ItemController?action=update-item-form&id=<%= item.getId() %>">Update</a>
                <a href="ItemController?action=remove-item&id=<%= item.getId() %>">Delete</a>
                <%
                    if (desc == null || desc.trim().isEmpty()) {
                %>
                    <a href="ItemDetailsController?action=show-add-form&itemId=<%= item.getId() %>">Add Description</a>
                <%
                    } else {
                %>
                    <a href="ItemController?action=show-details&id=<%= item.getId() %>">View Details</a>
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

    <div class="f">
        <a href="add-item.html">➕ Add New Item</a>
        <!-- here I added logout button..  -->
        <form method="post" action="LogoutServlet" style="display:inline;">
        <input type="submit" class="button" value="🚪 Logout"
               style="background-color: #f44336; border: none; padding: 10px 20px;
               color: white; font-weight: bold; margin-left: 10px; border-radius: 5px;">
    </div>
</div>
</body>
</html>
