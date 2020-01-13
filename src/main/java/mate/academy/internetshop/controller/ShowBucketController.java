package mate.academy.internetshop.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import mate.academy.internetshop.library.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;

public class ShowBucketController extends HttpServlet {
    @Inject
    private static BucketService bucketService;
    private static final Long USER_ID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Bucket bucket = bucketService.getByUserId(USER_ID);
        List<Item> items = bucket.getItems();
        req.setAttribute("items", items);
        req.setAttribute("bucket", bucket);
        req.getRequestDispatcher("/WEB-INF/views/bucket.jsp").forward(req, resp);
    }
}
