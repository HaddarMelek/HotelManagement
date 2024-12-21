<%@ page import="tn.hotelmanagement.model.RoomType" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hotel Details</title>
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
        h1, h2 {
            text-align: center;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }
        li:last-child {
            border-bottom: none;
        }
        img {
            max-width: 100%;
            height: auto;
            display: block;
            margin: 10px 0;
        }
    </style>
</head>
<body>
<header>
    <h1>Hotel Details</h1>
</header>
<main>
    <%
        tn.hotelmanagement.model.Hotel hotel = (tn.hotelmanagement.model.Hotel) request.getAttribute("hotel");
        if (hotel != null) {
    %>
    <h2><%= hotel.getName() %></h2>
    <p><strong>City:</strong> <%= hotel.getCity() %></p>
    <p><strong>Stars:</strong> <%= hotel.getStars() %></p>
    <p><strong>Description:</strong> <%= hotel.getDescription() %></p>
    <img src="<%= hotel.getImage() %>" alt="<%= hotel.getName() %>">

    <h2>Room Types</h2>
    <ul>
        <% for (RoomType room : hotel.getRoomTypes()) { %>
        <li>
            <strong>Type:</strong> <%= room.getLabel() %><br>
            <strong>Capacity:</strong> <%= room.getCapacity() %> persons<br>
            <strong>Price:</strong> $<%= room.getPrice() %>/night
        </li>
        <% } %>
    </ul>
    <%
    } else {
    %>
    <p>Hotel not found.</p>
    <%
        }
    %>
</main>
</body>
</html>
