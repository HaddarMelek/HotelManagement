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
        main {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            padding: 15px;
            border-bottom: 1px solid #ddd;
            display: flex;
            justify-content: space-between;
        }
        li:last-child {
            border-bottom: none;
        }
        li span {
            font-weight: bold;
        }
        footer {
            text-align: center;
            padding: 10px;
            margin-top: 20px;
            background-color: #4CAF50;
            color: white;
        }
    </style>
</head>
<body>
<header>
    <h1>Hotel Listings</h1>
    <a href="<%= request.getContextPath() %>/login">Sign In</a> | <a href="<%= request.getContextPath() %>/signup">Sign Up</a>
</header>
<main>
    <form method="post" action="<%= request.getContextPath() %>/hotels/find" style="margin-bottom: 20px;">
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

    <h1>All Hotels</h1>
    <%
        List<Hotel> hotels = (List<tn.hotelmanagement.model.Hotel>) request.getAttribute("hotels");
        if (hotels != null && !hotels.isEmpty()) {
    %>
    <ul>
        <% for (tn.hotelmanagement.model.Hotel hotel : hotels) { %>
        <li>
            <span><%= hotel.getName() %></span>
            <span><%= hotel.getCity() %></span>
            <span><%= hotel.getStars() %> stars</span>
            <a href="<%= request.getContextPath() %>/hotelDetails?id=<%= hotel.getId() %>" style="color: #4CAF50; text-decoration: none;">View Details</a>

        </li>
        <% } %>
    </ul>
    <%
    } else {
    %>
    <p>No hotels available. no no no </p>
    <%
        }
    %>
</main>
<footer>
    &copy; 2024 Roomify
</footer>
</body>
</html>
