package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.Storage;
import mate.academy.internetshop.library.Dao;
import mate.academy.internetshop.model.IdGenerator;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;

@Dao
public class OrderDaoImpl implements OrderDao {

    @Override
    public Order create(Order order) {
        order.setId(IdGenerator.getNewOrderId());
        Storage.orders.add(order);
        return order;
    }

    @Override
    public Optional<Order> get(Long id) {
        return Storage.orders
                .stream()
                .filter(o -> o.getId().equals(id))
                .findFirst();
    }

    @Override
    public Order update(Order order) {
        for (int i = 0; i < Storage.orders.size(); i++) {
            if (Storage.orders.get(i).getId().equals(order.getId())) {
                Storage.orders.get(i).setId(order.getId());
                Storage.orders.get(i).setUserId(order.getUserId());
                Storage.orders.get(i).setItems(order.getItems());
            }
        }
        return order;
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Order> toBeDeletedOrder = Storage.orders
                .stream()
                .filter(o -> o.getId().equals(id))
                .findFirst();
        return Storage.orders.remove(toBeDeletedOrder.get());
    }

    @Override
    public boolean delete(Order order) {
        Optional<Order> toBeDeletedOrder = Storage.orders
                .stream()
                .filter(o -> o.getId().equals(order.getId()))
                .findFirst();
        return Storage.orders.remove(toBeDeletedOrder.get());
    }

    @Override
    public List<Order> getAll() {
        return Storage.orders;
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return Storage.orders.stream()
                .filter(o -> o.getUserId().equals(user.getId()))
                .collect(Collectors.toList());
    }
}
