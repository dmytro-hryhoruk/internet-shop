package mate.academy.internetshop.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.library.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;

public class CompleteOrderController extends HttpServlet {
    @Inject
    private static BucketService bucketService;
    @Inject
    private static OrderService orderService;
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("userId");
        List<Order> userOrder = null;
        try {
            User user = userService.get(userId);
            Bucket bucket = bucketService.getByUserId(userId);
            orderService.completeOrder(bucket.getItems(), user);
            bucketService.clear(bucket);
            userOrder = orderService.getUserOrders(user);
        } catch (DataProcessingException e) {
            String errMsg = e.getMessage() + e.getCause().getMessage();
            req.setAttribute("errMsg", errMsg);
            req.getRequestDispatcher("/WEB-INF/views/dbErrorPage.jsp").forward(req, resp);
        }
        req.setAttribute("orders", userOrder);
        req.getRequestDispatcher("/WEB-INF/views/allOrders.jsp").forward(req, resp);
    }
}
