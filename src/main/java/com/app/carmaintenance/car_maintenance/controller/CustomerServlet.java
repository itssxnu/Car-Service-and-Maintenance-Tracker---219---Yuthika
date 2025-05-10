package     com.app.carmaintenance.car_maintenance.controller;

import com.app.carmaintenance.car_maintenance.dao.CustomerDAO;
import com.app.carmaintenance.car_maintenance.model.CustomerModel;
import com.app.carmaintenance.car_maintenance.model.UserModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
    private final CustomerDAO customerDAO = new CustomerDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession(false);
        UserModel user = (UserModel) (session != null ? session.getAttribute("user") : null);

        if (user == null) {
            response.sendRedirect("UserModel.jsp");
            return;
        }

        if ("form".equals(action)) {
            CustomerModel customer = customerDAO.getCustomerByEmail(user.getUserEmail());
            request.setAttribute("customer", customer);
            request.getRequestDispatcher("customer-form.jsp").forward(request, response);
        } else {
            response.sendRedirect("DashboardServlet");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession(false);
        UserModel user = (UserModel) (session != null ? session.getAttribute("user") : null);

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        if ("save".equals(action)) {
            String name = request.getParameter("name");
            String number = request.getParameter("number");
            CustomerModel customer = new CustomerModel(name, user.getUserEmail(), number);
            customerDAO.saveOrUpdateCustomer(customer);
            response.sendRedirect("DashboardServlet?message=Customer+details+saved");
        }
    }
}
