<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Customer</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">Edit Customer</h2>
    <form action="customersServlet" method="post">
        <input type="hidden" name="action" value="updateCustomer">
        <input type="hidden" name="originalName" value="<%= request.getParameter("name") %>">

        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control" id="name" name="name"
                   value="<%= request.getParameter("name") %>" required>
        </div>

        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email"
                   value="<%= request.getParameter("email") %>" required>
        </div>

        <div class="mb-3">
            <label for="number" class="form-label">Phone Number</label>
            <input type="text" class="form-control" id="number" name="number"
                   value="<%= request.getParameter("phone") %>" required>
        </div>

        <button type="submit" class="btn btn-primary">Update Customer</button>
        <a href="customer-list.jsp" class="btn btn-secondary">Cancel</a>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>