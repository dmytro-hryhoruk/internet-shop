package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
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
    public Order get(Long id) throws DataProcessingException {
        return orderDao.get(id)
                .orElseThrow(() -> new DataProcessingException("Couldn't find element",
                        new NoSuchElementException()));
    }

    @Override
    public Order update(Order order) throws DataProcessingException {
        return orderDao.update(order);
    }

    @Override
    public Order create(Order order) throws DataProcessingException {
        return orderDao.create(order);
    }

    @Override
    public boolean deleteById(Long id) throws DataProcessingException {
        return orderDao.deleteById(id);
    }

    @Override
    public boolean delete(Order order) throws DataProcessingException {
        return orderDao.delete(order);
    }

    @Override
    public List<Order> getUserOrders(User user) throws DataProcessingException {
        return orderDao.getUserOrders(user);

    }

    @Override
    public Order completeOrder(List<Item> items, User user) throws DataProcessingException {
        Order newOrder = new Order();
        newOrder.setItems(items);
        newOrder.setUserId(user.getId());
        orderDao.create(newOrder);
        return newOrder;
    }

    @Override
    public List<Order> getAll() throws DataProcessingException {
        return orderDao.getAll();
    }
}
