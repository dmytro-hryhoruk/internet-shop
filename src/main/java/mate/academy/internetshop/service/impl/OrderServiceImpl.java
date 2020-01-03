package mate.academy.internetshop.service.impl;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.library.Inject;
import mate.academy.internetshop.library.Service;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.OrderService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;


    @Override
    public Order get(Long id) {
        return orderDao.get(id)
                .orElseThrow(() -> new NoSuchElementException("Couldn't find element with given id"));
    }

    @Override
    public Order update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public void delete(Long id) {
        orderDao.delete(id);
    }

    @Override
    public void delete(Order order) {

    }

    @Override
    public List getUserOrders(User user) {
        return user.getUserOrders();
    }

    @Override
    public Order completeOrder(List items, User user) {
        Order newOrder = new Order();
        newOrder.setItemsList(items);
        newOrder.setUser(user);
        orderDao.create(newOrder);
        return newOrder;
    }
}
