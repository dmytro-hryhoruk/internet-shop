package mate.academy.internetshop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.library.Inject;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;

public class DeleteItemFromBucketController extends HttpServlet {
    @Inject
    private static BucketService bucketService;
    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String itemId = req.getParameter("item_id");
        Long userId = (Long) req.getSession().getAttribute("userId");
        try {
            Bucket bucket = bucketService.getByUserId(userId);
            Item item = itemService.get(Long.valueOf(itemId));
            bucketService.deleteItem(bucket, item);
        } catch (DataProcessingException e) {
            req.setAttribute("errMsg", e);
            req.getRequestDispatcher("/WEB-INF/views/dbErrorPage.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("/servlet/showBucket").forward(req, resp);
    }
}
