<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agent Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
</head>
<body>
<div class="container my-5">
    <h1 class="text-center mb-4">Agent Dashboard</h1>


    <h2>Your Hotels</h2>
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th>Hotel Name</th>
            <th>City</th>
            <th>Stars</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="hotel" items="${hotels}">
            <tr>
                <td>${hotel.name}</td>
                <td>${hotel.city}</td>
                <td>${hotel.stars}</td>
                <td>
                    <!-- View Details -->
                    <a href="${pageContext.request.contextPath}/hotelDetails?id=${hotel.id}" class="btn btn-info btn-sm">
                        <i class="fas fa-eye"></i> View
                    </a>

                    <!-- Edit Hotel -->
                    <a href="${pageContext.request.contextPath}/agent/hotel/edit?hotelId=${hotel.id}" class="btn btn-warning btn-sm">
                        <i class="fas fa-edit"></i> Edit
                    </a>

                    <!-- Delete Hotel -->
                    <a href="${pageContext.request.contextPath}/agent/hotel/delete?hotelId=${hotel.id}" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this hotel?');">
                        <i class="fas fa-trash-alt"></i> Delete
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <h2>Create New Hotel</h2>
    <form action="${pageContext.request.contextPath}/agent/hotel/create?username=${account.username}" method="POST" enctype="multipart/form-data">
        <div class="mb-3">
            <label for="hotelName" class="form-label">Hotel Name:</label>
            <input type="text" id="hotelName" name="name" class="form-control" required>
        </div>

        <div class="mb-3">
            <label for="city" class="form-label">City:</label>
            <input type="text" id="city" name="city" class="form-control" required>
        </div>

        <div class="mb-3">
            <label for="stars" class="form-label">Stars:</label>
            <input type="number" id="stars" name="stars" class="form-control" required min="1" max="5">
        </div>

        <div class="mb-3">
            <label for="description" class="form-label">Description:</label>
            <textarea id="description" name="description" class="form-control"></textarea>
        </div>

        <div class="mb-3">
            <label for="image" class="form-label">Image:</label>
            <input type="file" id="image" name="image" class="form-control">
        </div>


        <button type="submit" class="btn btn-primary">Create Hotel</button>
    </form>
</div>

<div class="navbar navbar-expand-lg navbar-light bg-light">
    <a href="${pageContext.request.contextPath}/logout" class="navbar-brand">Logout</a>
</div>
</body>
</html>
