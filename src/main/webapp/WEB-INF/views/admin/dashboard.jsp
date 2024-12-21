<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container my-5">
    <h1 class="text-center mb-4">Admin Dashboard</h1>
    <a href="<%= request.getContextPath() %>/admin/create" class="btn btn-primary mb-3">Create Agent</a>

    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Role</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="agent" items="${requestScope.agents}">
            <tr>
                <td>${agent.id}</td>
                <td>${agent.username}</td>
                <td>${agent.email}</td>
                <td>${agent.role}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/create?action=edit&id=${agent.id}" class="btn btn-warning btn-sm">Edit</a>
                    <a href="${pageContext.request.contextPath}/admin/create?action=delete&id=${agent.id}" class="btn btn-danger btn-sm">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="navbar navbar-expand-lg navbar-light bg-light">
    <a href="${pageContext.request.contextPath}/logout" class="navbar-brand">Logout</a>
    <h2 class="ms-auto">Visitor Dashboard</h2>
</div>
</body>
</html>

