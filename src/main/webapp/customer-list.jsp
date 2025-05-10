<%@ page import="com.app.carmaintenance.car_maintenance.model.CustomerModel" %>
<%@ page import="com.app.carmaintenance.car_maintenance.model.UserModel" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
</head>
<body>
<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="mb-0 text-primary"><i class="bi bi-people-fill"></i> All Customers</h2>
        <%
            UserModel currentUser = (UserModel) session.getAttribute("user");
            if (currentUser != null && "ADMIN".equalsIgnoreCase(currentUser.getRole())) {
        %>
        <a href="add-customer.jsp" class="btn btn-success">
            <i class="bi bi-plus-lg"></i> Add New Customer
        </a>
        <% } %>
    </div>

    <% if (request.getAttribute("error") != null) { %>
    <div class="alert alert-danger">${error}</div>
    <% } %>

    <div class="table-responsive">
        <table class="table table-bordered table-hover text-center">
            <thead class="table-dark">
            <tr>
                <th>Name</th>
                <th>Email</th>
                <th>Contact Number</th>
                <% if (currentUser != null && "ADMIN".equalsIgnoreCase(currentUser.getRole())) { %>
                <th>Action</th>
                <% } %>
            </tr>
            </thead>
            <tbody>
            <% for (CustomerModel customer : (List<CustomerModel>) request.getAttribute("customers")) { %>
            <tr>
                <td><%= customer.getName() %></td>
                <td><%= customer.getEmail() %></td>
                <td><%= customer.getPhoneNumber() %></td>
                <% if (currentUser != null && "ADMIN".equalsIgnoreCase(currentUser.getRole())) { %>
                <td>
                    <form action="edit-customer.jsp" method="get" class="d-inline">
                        <input type="hidden" name="name" value="<%= customer.getName() %>">
                        <input type="hidden" name="email" value="<%= customer.getEmail() %>">
                        <input type="hidden" name="phone" value="<%= customer.getPhoneNumber() %>">
                        <button type="submit" class="btn btn-sm btn-warning me-2">
                            <i class="bi bi-pencil"></i> Edit
                        </button>
                    </form>

                    <form action="customersServlet" method="post" class="d-inline">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="name" value="<%= customer.getName() %>">
                        <button type="submit" class="btn btn-sm btn-danger"
                                onclick="return confirm('Are you sure you want to delete this customer?');">
                            <i class="bi bi-trash"></i> Delete
                        </button>
                    </form>
                </td>
                <% } %>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
    <div class="text-center mt-3">
        <a href="index.jsp" class="btn btn-primary">
            <i class="bi bi-arrow-left"></i> Go Back
        </a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>