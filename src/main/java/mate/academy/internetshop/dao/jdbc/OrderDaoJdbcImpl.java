package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.model.Order;

public class OrderDaoJdbcImpl extends AbstractDao implements OrderDao {
    public OrderDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Order create(Order entity) {
        return null;
    }

    @Override
    public Optional<Order> get(Long entityId) {
        return Optional.empty();
    }

    @Override
    public Order update(Order entity) {
        return null;
    }

    @Override
    public boolean deleteById(Long entityId) {
        return false;
    }

    @Override
    public boolean delete(Order entity) {
        return false;
    }

    @Override
    public List<Order> getAll() {
        return null;
    }
}
