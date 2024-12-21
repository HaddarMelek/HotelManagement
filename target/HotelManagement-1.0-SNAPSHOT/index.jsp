<%@ page import="tn.hotelmanagement.model.Hotel" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hotels</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f9f9f9;
        color: #333;
    }

    header {
        background-color: #4CAF50;
        color: white;
        padding: 1rem 2rem;
        text-align: center;
    }

    nav a {
        color: white;
        margin: 0 10px;
        text-decoration: none;
    }

    nav a:hover {
        text-decoration: underline;
    }

    main {
        max-width: 800px;
        margin: 20px auto;
        padding: 20px;
        background: #fff;
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    .filter-form label {
        display: block;
        margin-bottom: 5px;
    }

    .filter-form input, .filter-form button {
        margin-bottom: 10px;
    }

    .hotel-list {
        list-style: none;
        padding: 0;
    }

    .hotel-list li {
        padding: 10px;
        border-bottom: 1px solid #ddd;
        display: flex;
        justify-content: space-between;
    }

    footer {
        text-align: center;
        padding: 10px;
        background-color: #4CAF50;
        color: white;
    }

</style>

</head>
<body>
<header>
    <h1>Hotel Listings</h1>
    <nav>
        <a href="<%= request.getContextPath() %>/login">Sign In</a> |
        <a href="<%= request.getContextPath() %>/signup">Sign Up</a>
    </nav>
</header>
<main>
    <form method="post" action="<%= request.getContextPath() %>/hotels" class="filter-form">
        <label for="city">City:</label>
        <input type="text" id="city" name="city">

        <label for="stars">Stars:</label>
        <input type="number" id="stars" name="stars" min="0" max="5">

        <label for="roomType">Room Type:</label>
        <input type="text" id="roomType" name="roomType">

        <label for="minPrice">Min Price:</label>
        <input type="number" id="minPrice" name="minPrice">

        <label for="maxPrice">Max Price:</label>
        <input type="number" id="maxPrice" name="maxPrice">

        <button type="submit">Filter</button>
    </form>

    <h2>Available Hotels</h2>
    <%
        List<Hotel> hotels = (List<Hotel>) request.getAttribute("hotels");
        if (hotels != null && !hotels.isEmpty()) {
    %>
    <ul class="hotel-list">
        <% for (Hotel hotel : hotels) { %>
        <li>
            <span><%= hotel.getName() %></span>
            <span><%= hotel.getCity() %></span>
            <span><%= hotel.getStars() %> stars</span>
            <a href="<%= request.getContextPath() %>/hotelDetails?id=<%= hotel.getId() %>">View Details</a>
        </li>
        <% } %>
    </ul>
    <% } else { %>
    <p>No hotels available.</p>
    <% } %>
</main>
<footer>
    &copy; 2024 Roomify
</footer>
</body>
</html>
