package mate.academy.internetshop.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import mate.academy.internetshop.library.Inject;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

public class MenuController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("userId");
        User user = userService.get(userId);
        if (user.getRoles()
                .stream()
                .anyMatch(r -> r.getRoleName().equals(Role.RoleName.ADMIN))) {
            resp.sendRedirect(req.getContextPath() + "/servlet/adminMenu");
        } else {
            resp.sendRedirect(req.getContextPath() + "/servlet/mainMenu");
        }

    }
}
