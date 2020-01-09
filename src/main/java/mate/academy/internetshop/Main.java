package mate.academy.internetshop;

import java.util.List;
import mate.academy.internetshop.library.Inject;
import mate.academy.internetshop.library.Injector;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;

public class Main {
    @Inject
    private static ItemService itemService;

    @Inject
    private static BucketService bucketService;

    @Inject
    private static OrderService orderService;

    @Inject
    private static UserService userService;

    static {
        try {
            Injector.injectDependency();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Item item = new Item();
        Bucket bucket = new Bucket();
        Order order = new Order();
        User user = new User();
        userService.create(user);
        item.setPrice(50.00);
        item.setName("Some Phone");
        itemService.create(item);
        orderService.completeOrder(List.of(item), user);
        System.out.println(orderService.getUserOrders(user));
    }
}
