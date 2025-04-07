<%--
  Created by IntelliJ IDEA.
  User: sanus
  Date: 4/7/2025
  Time: 10:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.app.carmaintenance.car_maintenance.model.UserModel" %>
<%@ page import="com.app.carmaintenance.car_maintenance.model.VehicleModel" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    UserModel user = (UserModel) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("UserModel.jsp");
        return;
    }

    List<VehicleModel> vehicles = (List<VehicleModel>) request.getAttribute("vehicles");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2>Welcome, <%= user.getUsername().toUpperCase() %>!</h2>
    <hr>

    <div class="mb-3">
        <a href="newcaradding.jsp" class="btn btn-success">Add New Vehicle</a>
        <a href="LogoutServlet" class="btn btn-secondary">Logout</a>
    </div>

    <h4>Your Vehicles</h4>
    <%
        if (vehicles == null || vehicles.isEmpty()) {
    %>
    <p>You haven't added any vehicles yet.</p>
    <%
    } else {
    %>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Vehicle Number</th>
            <th>Model</th>
            <th>Service History</th>
        </tr>
        </thead>
        <tbody>
        <% for (VehicleModel vehicle : vehicles) { %>
        <tr>
            <td><%= vehicle.getVehicleNumber() %></td>
            <td><%= vehicle.getModel() %></td>
            <td>
                <a href="ViewServiceServlet?vehicleNumber=<%= vehicle.getVehicleNumber() %>" class="btn btn-info btn-sm">View History</a>
                <a href="addService.jsp?vehicleNumber=<%= vehicle.getVehicleNumber() %>" class="btn btn-warning btn-sm">Add Service</a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <%
        }
    %>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
