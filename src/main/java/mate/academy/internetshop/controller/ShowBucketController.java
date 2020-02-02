package mate.academy.internetshop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.library.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;

public class ShowBucketController extends HttpServlet {
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("userId");
        Bucket bucket = null;
        try {
            bucket = bucketService.getByUserId(userId);
        } catch (DataProcessingException e) {
            req.setAttribute("errMsg", e);
            req.getRequestDispatcher("/WEB-INF/views/dbErrorPage.jsp").forward(req, resp);
        }
        List<Item> items = bucket.getItems();
        req.setAttribute("items", items);
        req.setAttribute("bucket", bucket);
        req.getRequestDispatcher("/WEB-INF/views/bucket.jsp").forward(req, resp);
    }
}
