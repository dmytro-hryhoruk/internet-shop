package mate.academy.internetshop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.library.Inject;
import mate.academy.internetshop.service.OrderService;

public class DeleteOrderController extends HttpServlet {
    @Inject
    private static OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        try {
            orderService.deleteById(Long.valueOf(orderId));
        } catch (DataProcessingException e) {
            req.setAttribute("errMsg", e);
            req.getRequestDispatcher("/WEB-INF/views/dbErrorPage.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("/servlet/allOrders").forward(req, resp);
    }
}
