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
<style>
    .btn-group .btn {
        transition: 0.2s ease-in-out;
    }
    .btn-danger:hover {
        background-color: #c82333;
    }
    .btn-success:hover {
        background-color: #218838;
    }
    .btn-group form {
        margin: 0;
    }
    .btn-group form .btn {
        border-radius: 0;
    }
</style>
<body>
<div class="container mt-5">
    <h2>Welcome, <%= user.getUsername().toUpperCase() %>!</h2>
    <hr>

    <div class="mb-4 text-center">
        <div class="btn-group" role="group" aria-label="User Actions">
            <a href="newcaradding.jsp" class="btn btn-success me-2">Add Vehicle ➕</a>
            <a href="appointments" class="btn btn-primary me-2">Make Appointment 📅</a>
        </div>

        <div class="btn-group" role="group" aria-label="Account Actions">
            <a href="LogoutServlet" class="btn btn-secondary">Logout</a>

            <!-- Inline form so it's visually inside the group -->
            <form action="DeleteAccountServlet" method="post"
                  onsubmit="return confirm('Are you sure you want to delete your account? This action cannot be undone.');">
                <button type="submit" class="btn btn-danger">Delete My Account</button>
            </form>
        </div>


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
                <a href="ViewServiceServlet?vehicleNumber=<%= vehicle.getVehicleNumber() %>" class="btn btn-info">View History</a>
                <a href="addServices.jsp?vehicleNumber=<%= vehicle.getVehicleNumber() %>" class="btn btn-warning btn-sm">Add Service</a>
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
