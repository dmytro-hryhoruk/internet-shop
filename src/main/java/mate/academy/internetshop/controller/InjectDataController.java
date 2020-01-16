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

public class InjectDataController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = new User();
        user.setName("Bob");
        user.setSurname("Martin");
        user.addRole(Role.of("USER"));
        user.setLogin("dima");
        user.setPassword("123");
        userService.create(user);

        User admin = new User();
        admin.setName("Richard");
        admin.setSurname("Hempton");
        admin.addRole(Role.of("ADMIN"));
        admin.setLogin("dima1");
        admin.setPassword("1234");
        userService.create(admin);
        //resp.sendRedirect(req.getContextPath()+"/login ");
        req.getRequestDispatcher("/login").forward(req,resp);
    }
}
