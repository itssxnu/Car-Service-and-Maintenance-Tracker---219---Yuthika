<%@ page import="com.app.carmaintenance.car_maintenance.model.VehicleModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String vehicleNumber = request.getParameter("vehicleNumber");
    // TODO: Read from file or backend storage to fetch matching vehicle and its maintenance details
    // Here, dummy values are used
    String lastService = "2024-12-20";
    String serviceNotes = "Changed oil, rotated tires";
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Maintenance Details</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2>Maintenance Details for: <%= vehicleNumber %></h2>
    <form action="UpdateMaintenanceServlet" method="post">
        <input type="hidden" name="vehicleNumber" value="<%= vehicleNumber %>">

        <div class="mb-3">
            <label for="lastService" class="form-label">Last Service Date</label>
            <input type="date" class="form-control" id="lastService" name="lastService" value="<%= lastService %>">
        </div>

        <div class="mb-3">
            <label for="serviceNotes" class="form-label">Service Notes</label>
            <textarea class="form-control" id="serviceNotes" name="serviceNotes" rows="4"><%= serviceNotes %></textarea>
        </div>

        <button type="submit" class="btn btn-success">Update Maintenance</button>
        <a href="dashboard.jsp" class="btn btn-secondary">Back to Dashboard</a>
    </form>
</div>
</body>
</html>
