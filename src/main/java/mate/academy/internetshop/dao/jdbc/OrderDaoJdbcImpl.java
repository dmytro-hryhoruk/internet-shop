package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.library.Dao;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import org.apache.log4j.Logger;

@Dao
public class OrderDaoJdbcImpl extends AbstractDao<Order> implements OrderDao {
    private static String DB_NAME = "internetshop";
    private static String TABLE = "internetshop.orders";
    private static Logger logger = Logger.getLogger(OrderDaoJdbcImpl.class);

    public OrderDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Order create(Order order) {
        String query = "INSERT INTO orders(user_id) VALUES(?);";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, order.getUserId());
            stmt.executeUpdate();
            ResultSet resultSet = stmt.getGeneratedKeys();
            resultSet.next();
            Long orderId = resultSet.getLong(1);
            order.setId(orderId);
            setOrderItems(order);
        } catch (SQLException e) {
            logger.error("couldn't add order " + order, e);
        }

        return order;
    }

    private void setOrderItems(Order order) {
        String query = "INSERT INTO orders_items(order_id,item_id) VALUES(?,?);";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            for (Item item : order.getItems()) {
                stmt.setLong(1, order.getId());
                stmt.setLong(2, item.getId());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error("couldn't set order items " + order, e);
        }
    }

    @Override
    public Optional<Order> get(Long orderId) {
        String query = "SELECT * FROM orders " +
                "WHERE order_id =? ";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, orderId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Long user_id = rs.getLong("user_id");
                Order order = new Order();
                order.setItems(getOrderItems(orderId));
                order.setId(orderId);
                order.setUserId(user_id);
                return Optional.of(order);
            }
        } catch (SQLException e) {
            logger.warn("Can't get order with id = " + orderId, e);
        }
        return Optional.empty();
    }

    private List<Item> getOrderItems(Long orderId) {
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM orders_items o " +
                "JOIN items i ON o.order_id =? AND o.item_id = i.item_id;";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, orderId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Long item_id = rs.getLong("item_id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                Item item = new Item();
                item.setName(name);
                item.setPrice(price);
                item.setId(item_id);
                items.add(item);
            }
            return items;
        } catch (SQLException e) {
            logger.warn("Can't get order items = " + orderId, e);
        }
        return null;
    }

    @Override
    public Order update(Order order) {
        deleteOrderItems(order);
        setOrderItems(order);
        return order;
    }

    private Boolean deleteOrderItems(Order order) {
        String deleteOrderItems =
                "delete from orders_items where order_id =?;";
        try (PreparedStatement statement = connection.prepareStatement(deleteOrderItems)) {
            statement.setLong(1, order.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.warn("Can't update order", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteById(Long orderId) {
        String query = "DELETE FROM orders WHERE order_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, orderId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.warn("Can't get delete order with id = " + orderId, e);
        }
        return false;
    }

    @Override
    public boolean delete(Order order) {
        return deleteById(order.getId());
    }

    @Override
    public List<Order> getAll() {
        String query = "SELECT * FROM orders;";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ResultSet resultSet = stmt.executeQuery();
            return getOrdersFromResultSet(resultSet);
        } catch (SQLException e) {
            logger.warn("Can't get orders ", e);
        }
        return null;
    }

    @Override
    public List<Order> getUserOrders(User user) {
        String query = "SELECT * FROM orders WHERE user_id = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, user.getId());
            ResultSet resultSet = stmt.executeQuery();
            return getOrdersFromResultSet(resultSet);
        } catch (SQLException e) {
            logger.warn("Can't get user orders  " + user, e);
        }
        return null;
    }

    private List<Order> getOrdersFromResultSet(ResultSet resultSet) throws SQLException {
        List<Order> orders = new ArrayList<>();
        while (resultSet.next()) {
            Long order_id = resultSet.getLong("order_id");
            Order order = get(order_id).get();
            orders.add(order);
        }
        return orders;
    }
}
