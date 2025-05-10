<%@ page import="com.app.carmaintenance.car_maintenance.model.UserModel" %>
<%@ page import="com.app.carmaintenance.car_maintenance.util.UserUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Customer Details</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-4">
  <div class="d-flex justify-content-between align-items-center mb-4">
    <h2>Customer Details</h2>
    <a href="adminDashboard.jsp" class="btn btn-secondary">Back to Dashboard</a>
  </div>

  <%
    String email = request.getParameter("email");
    UserModel customer = UserUtil.getUserByEmail(email);
    if (customer != null) {
  %>
  <div class="card shadow">
    <div class="card-header bg-primary text-white">
      <h4 class="mb-0"><%= customer.getUsername() %></h4>
    </div>
    <div class="card-body">
      <div class="row mb-3">
        <div class="col-md-6">
          <h5>Contact Information</h5>
          <p><strong>Email:</strong> <%= customer.getUserEmail() %></p>
          <p><strong>Phone:</strong> <%= customer.getUserMobileNumber() %></p>
        </div>
      </div>

      <div class="mt-4">
        <h5>Vehicles</h5>
        <%-- You can add vehicle information here if needed --%>
        <p class="text-muted">No vehicle information available.</p>
      </div>
    </div>
    <div class="card-footer">
      <form action="AdminDashboardServlet" method="post"
            onsubmit="return confirm('Delete this customer?')" class="d-inline">
        <input type="hidden" name="action" value="deleteCustomer">
        <input type="hidden" name="targetUserEmail" value="<%= customer.getUserEmail() %>">
        <button type="submit" class="btn btn-danger">Delete Customer</button>
      </form>
    </div>
  </div>
  <% } else { %>
  <div class="alert alert-danger">Customer not found.</div>
  <% } %>
</div>
</body>
</html>