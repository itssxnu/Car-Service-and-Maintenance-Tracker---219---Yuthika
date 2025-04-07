<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Vehicle Maintenance Tracker</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        form { margin-bottom: 30px; }
        label { display: block; margin-top: 10px; }
        input[type="date"], textarea { width: 300px; padding: 5px; }
        .results, .notice { margin-top: 20px; padding: 15px; border: 1px solid #ccc; border-radius: 8px; }
        .results { background-color: #f9f9f9; }
        .notice { background-color: #fff8dc; }
    </style>
</head>
<body>
<h2>Vehicle Maintenance Input Form</h2>
<form action="MaintenanceServlet" method="post">
    <label>Oil Renew Date:</label>
    <input type="date" name="oilRenewDate" required />

    <label>Last Service Date:</label>
    <input type="date" name="lastServiceDate" required />

    <label>Last Alignment Checking Date:</label>
    <input type="date" name="alignmentCheckDate" required />

    <label>Tyre Renew Date:</label>
    <input type="date" name="tyreRenewDate" required />

    <label>Special Notice:</label>
    <textarea name="specialNotice" rows="4" placeholder="Enter any special notes..."></textarea>

    <br><br>
    <input type="submit" value="Submit" />
</form>

<%
    String bestOilRefillDate = (String) request.getAttribute("bestOilRefillDate");
    String nextServiceDate = (String) request.getAttribute("nextServiceDate");
    String nextAlignmentCheckDate = (String) request.getAttribute("nextAlignmentCheckDate");
    String nextTyreRenewDate = (String) request.getAttribute("nextTyreRenewDate");
    String specialNotice = (String) request.getAttribute("specialNotice");
%>

<% if (bestOilRefillDate != null) { %>
<div class="results">
    <h3>Recommended Dates</h3>
    <p><strong>Next Oil Refill:</strong> <%= bestOilRefillDate %></p>
    <p><strong>Next Service Date:</strong> <%= nextServiceDate %></p>
    <p><strong>Next Alignment Check:</strong> <%= nextAlignmentCheckDate %></p>
    <p><strong>Next Tyre Renewal:</strong> <%= nextTyreRenewDate %></p>
</div>
<% } %>

<% if (specialNotice != null && !specialNotice.trim().isEmpty()) { %>
<div class="notice">
    <h3>Special Notice</h3>
    <p><%= specialNotice %></p>
</div>
<% } %>
</body>
</html>
