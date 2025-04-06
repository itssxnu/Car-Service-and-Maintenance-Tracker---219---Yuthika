<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Car Booking Interface</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Segoe UI', sans-serif;
        }
        .car-card {
            border-radius: 20px;
            overflow: hidden;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
            margin-bottom: 20px; /* Add some space between cards if they wrap */
            height: 100%; /* Make cards equal height */
        }
        .car-img {
            width: 100%;
            height: 200px; /* Fixed height for images */
            object-fit: cover;
        }
        .btn-book {
            border-radius: 20px;
            padding: 10px 30px;
        }
    </style>
</head>
<body>
<div class="container py-5">
    <h2 class="text-center mb-4">Your Cars</h2>
    <div class="row g-4">

        <!-- First Car -->
        <div class="col-md-4">
            <div class="card car-card">
                <img src="images/i7bm.jpg" alt="Car Image" class="car-img">
                <div class="card-body text-center">
                    <h5 class="card-title">BMW I7</h5>
                    <form action="BookCarServlet" method="post">
                        <input type="hidden" name="carName" value="Luxury Sedan">
                        <button type="submit" class="btn btn-primary btn-book">Select</button>
                    </form>
                </div>
            </div>
        </div>

        <!-- Second Car -->
        <div class="col-md-4">
            <div class="card car-card">
                <img src="images/mini1.jpg" alt="Car Image" class="car-img">
                <div class="card-body text-center">
                    <h5 class="card-title">Wife's Car</h5>
                    <form action="BookCarServlet" method="post">
                        <input type="hidden" name="carName" value="Wife's Car">
                        <button type="submit" class="btn btn-primary btn-book">Select</button>
                    </form>
                </div>
            </div>
        </div>

        <!-- Add more cars here with the same col-md-4 structure -->
    </div>
</div>
</body>
</html>