<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
    <style>
        body { font-family: 'Segoe UI'; background: #fff3e0; padding: 50px; }
        .box { background: white; padding: 30px; max-width: 400px; margin: auto; border-radius: 10px; box-shadow: 0 4px 15px rgba(0,0,0,0.1); }
        input { width: 100%; padding: 10px; margin: 10px 0; border-radius: 5px; border: 1px solid #ccc; }
        .button { background: #d32f2f; color: white; border: none; }
        .button:hover { background: #b71c1c; }
    </style>
</head>
<body>
<div class="box">
    <h2>Sign Up</h2>
    <form method="post" action="SignupServlet">
        <input name="username" type="text" placeholder="Username" required>
        <input name="password" type="password" placeholder="Password" required>
        <input type="submit" class="button" value="Register">
    </form>

    <% String msg = request.getParameter("error");
       if ("exists".equals(msg)) { %>
        <p style="color:red;">Username already taken.</p>
    <% } else if ("signup_failed".equals(msg)) { %>
        <p style="color:red;">Signup failed. Try again.</p>
    <% } %>

    <p>Already have an account? <a href="login.jsp">Login</a></p>
</div>
</body>
</html>
