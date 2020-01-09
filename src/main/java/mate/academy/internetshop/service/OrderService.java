package mate.academy.internetshop.service;

import java.util.List;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.impl.GenericService;

public interface OrderService extends GenericService<Order, Long> {
    List getUserOrders(User user);

    Order completeOrder(List items, User user);
}
