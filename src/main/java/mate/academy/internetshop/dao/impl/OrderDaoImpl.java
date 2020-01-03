package mate.academy.internetshop.dao.impl;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.Storage;
import mate.academy.internetshop.library.Dao;
import mate.academy.internetshop.model.Order;

import java.util.NoSuchElementException;
import java.util.Optional;

@Dao
public class OrderDaoImpl implements OrderDao {
    private static Long orderId = 1L;

    @Override
    public Order create(Order order) {
        order.setId(orderId++);
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
        Order oldOrder = Storage.orders
                .stream()
                .filter(o -> o.getId().equals(order.getId()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Couldn't find element"));
        Storage.orders.remove(oldOrder);
        Storage.orders.add(order);
        return order;
    }

    @Override
    public boolean delete(Long id) {
        Order toBeDeletedOrder = Storage.orders
                .stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Couldn't find element"));
        return Storage.orders.remove(toBeDeletedOrder);
    }

    @Override
    public boolean delete(Order order) {
        Order toBeDeletedOrder = Storage.orders
                .stream()
                .filter(o -> o.getId().equals(order.getId()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Couldn't find element"));
        return Storage.orders.remove(toBeDeletedOrder);
    }
}
