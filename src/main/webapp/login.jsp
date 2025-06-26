<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style>
        body { font-family: 'Segoe UI'; background: #ffebee; padding: 50px; }
        .box { background: white; padding: 30px; max-width: 400px; margin: auto; border-radius: 10px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); }
        input { width: 100%; padding: 10px; margin: 10px 0; border-radius: 5px; border: 1px solid #ccc; }
        .button { background: #d32f2f; color: white; border: none; }
        .button:hover { background: #b71c1c; }
    </style>
</head>
<body>
<div class="box">
    <h2>Login</h2>
    <form method="post" action="LoginServlet">
        <input name="username" type="text" placeholder="Username" required>
        <input name="password" type="password" placeholder="Password" required>
        <input type="submit" class="button" value="Login">
    </form>

    <% String error = request.getParameter("error");
       if ("invalid".equals(error)) { %>
       <p style="color:red;">Invalid username or password.</p>
    <% } %>

    <p>Don't have an account? <a href="signup.jsp">Sign Up</a></p>
</div>
</body>
</html>
