package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.library.Inject;
import mate.academy.internetshop.library.Service;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private static OrderDao orderDao;


    @Override
    public Order get(Long id) {
        return orderDao.get(id)
                .orElseThrow(() -> new NoSuchElementException("Couldn't find element"));
    }

    @Override
    public Order update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public Order create(Order order) {
        return orderDao.create(order);
    }

    @Override
    public boolean deleteById(Long id) {
        return orderDao.deleteById(id);
    }

    @Override
    public boolean delete(Order order) {
        return orderDao.delete(order);
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return orderDao.getUserOrders(user);

    }

    @Override
    public Order completeOrder(List<Item> items, User user) {
        Order newOrder = new Order();
        newOrder.setItems(items);
        newOrder.setUserId(user.getId());
        orderDao.create(newOrder);
        return newOrder;
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }
}
