package mate.academy.internetshop.service;

import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;

import java.util.List;

public interface OrderService {
    Order get(Long id);

    Order update(Order order);

    void delete(Long id);

    void delete(Order order);

    List getUserOrders(User user);

    Order completeOrder(List items, User user);
}
