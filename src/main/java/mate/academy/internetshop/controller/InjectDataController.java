package mate.academy.internetshop.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import mate.academy.internetshop.library.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.UserService;

public class InjectDataController extends HttpServlet {
    @Inject
    private static UserService userService;
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = new User();
        user.setName("Bob");
        user.setSurname("Martin");
        user.addRole(Role.of("ADMIN"));
        user.setLogin("dima");
        user.setPassword("123");
        userService.create(user);
        Bucket newBucket = new Bucket();
        newBucket.setUserId(user.getId());
        bucketService.create(newBucket);
    }
}
