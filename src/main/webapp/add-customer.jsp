<%@ page import="com.app.carmaintenance.car_maintenance.model.UserModel" %>
<%@ page import="com.app.carmaintenance.car_maintenance.dao.CustomerDAO" %>
<%@ page import="com.app.carmaintenance.car_maintenance.model.CustomerModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    UserModel user = (UserModel) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String name = "";
    String phone = "";

    CustomerDAO dao = new CustomerDAO();
    CustomerModel customer = dao.getCustomerByEmail(user.getUserEmail());
    if (customer != null) {
        name = customer.getName();
        phone = customer.getPhoneNumber();
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Add/Edit Customer Details</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">

<h3 class="mb-4">Add / Edit Your Details</h3>

<form action="customer" method="post">
    <input type="hidden" name="action" value="save">

    <div class="mb-3">
        <label for="name" class="form-label">Your Name</label>
        <input type="text" class="form-control" id="name" name="name" value="<%= name %>" required>
    </div>

    <div class="mb-3">
        <label class="form-label">Email</label>
        <input type="text" class="form-control" value="<%= user.getUserEmail() %>" readonly>
    </div>

    <div class="mb-3">
        <label for="number" class="form-label">Contact Number</label>
        <input type="text" class="form-control" id="number" name="number" value="<%= phone %>" required>
    </div>

    <button type="submit" class="btn btn-primary">Save My Details</button>
</form>

</body>
</html>
