<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container my-5">
    <h1 class="text-center">Create an Account</h1>
    <form action="<%= request.getContextPath() %>/signup" method="post" class="mt-4">
        <div class="mb-3">
            <label  class="form-label">Username</label>
            <input type="text" class="form-control" name="username" required>
        </div>
        <div class="mb-3">
            <label  class="form-label">Password</label>
            <input type="password" class="form-control" name="password" required>
        </div>
        <div class="mb-3">
            <label  class="form-label">Email</label>
            <input type="email" class="form-control" name="email" required>
        </div>
        <button type="submit" class="btn btn-success">Sign Up</button>
    </form>
    <div class="mt-3">
        <p>Already have an account? <a href="<%= request.getContextPath() %>/login">Sign In</a></p>
    </div>
</div>
</body>
</html>