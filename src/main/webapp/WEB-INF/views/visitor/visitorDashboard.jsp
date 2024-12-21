<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-datepicker@1.9.0/dist/css/bootstrap-datepicker.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap-datepicker@1.9.0/dist/js/bootstrap-datepicker.min.js"></script>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Visitor Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="navbar navbar-expand-lg navbar-light bg-light">
    <a href="${pageContext.request.contextPath}/logout" class="navbar-brand">Logout</a>
    <h2 class="ms-auto">Visitor Dashboard</h2>
</div>

<div class="container my-5">
    <h3>Make a Reservation</h3>
    <form action="${pageContext.request.contextPath}/book/hotel" method="POST" class="mt-4">
        <div class="mb-3">
            <label for="hotel" class="form-label">Hotel</label>
            <select name="hotel" id="hotel" class="form-select">
                <c:forEach var="hotel" items="${hotels}">
                    <option value="${hotel.id}">${hotel.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label  class="form-label">Stay Dates</label>
            <input type="text" name="dates" class="form-control" id="dates" placeholder="YYYY-MM-DD to YYYY-MM-DD">
        </div>
        <div class="mb-3">
            <label  class="form-label">Name</label>
            <input type="text" name="name"  class="form-control" required>
        </div>
        <div class="mb-3">
            <label  class="form-label">Email</label>
            <input type="email" name="email"  class="form-control" required>
        </div>
        <div class="mb-3">
            <label  class="form-label">Phone</label>
            <input type="text" name="phone"  class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Reserve</button>
    </form>

    <h3 class="mt-5">Your Profile</h3>
    <form action="${pageContext.request.contextPath}/visitor/dashboard" method="POST" class="mt-4">
        <div class="mb-3">
            <label for="username" class="form-label">Username</label>
            <input type="text" name="username" id="username" class="form-control" value=${username} disabled>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" name="email" id="email" class="form-control" value=${account.email} required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Phone</label>
            <input type="password" name="password" id="password" class="form-control" value=${account.password} required>
        </div>
        <button type="submit" class="btn btn-success">Update Profile</button>
    </form>
</div>
</body>
<script>
    $(document).ready(function(){
        $('#dates').datepicker({
            format: 'yyyy-mm-dd',
            startView: 2,
            minViewMode: 1,
            todayBtn: 'linked',
            clearBtn: true,
            orientation: 'bottom auto',
            autoclose: true,
            multidate: false,
            todayHighlight: true,
        });
    });
</script>

</html>
