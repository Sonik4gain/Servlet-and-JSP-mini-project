<html>
<head>
    <title>Add Item Details</title>
    <style>
        body {
            font-family: Arial;
            background-color: #f0f0f0;
            padding: 20px;
        }

        form {
            background: #fff;
            padding: 20px;
            border-radius: 10px;
            max-width: 400px;
            margin: auto;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        label {
            display: block;
            margin-top: 10px;
        }

        input, textarea {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
        }

        button {
            margin-top: 15px;
            padding: 10px;
            width: 100%;
            background: #28a745;
            color: white;
            border: none;
            border-radius: 5px;
        }
    </style>
</head>
<body>

<h2>Add Description</h2>

<form action="ItemDetailsController" method="get">
    <input type="hidden" name="action" value="add-item-details"/>
    <input type="hidden" name="itemId" value="${itemId}"/>
    <label>Description:</label>
    <input type="text" name="description" required/>
    <br/>
    <input type="submit" value="Save Description"/>
</form>

<br/>
<a href="ItemController">Back to Items List</a>
</body>

</html>
