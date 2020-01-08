package mate.academy.internetshop.service;

import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<Order> get(Long id);

    Order update(Order order);

    boolean delete(Long id);

    boolean delete(Order order);

    List getUserOrders(User user);

    Order completeOrder(List items, User user);
}
