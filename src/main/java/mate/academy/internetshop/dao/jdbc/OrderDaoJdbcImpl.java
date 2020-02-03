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
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.library.Dao;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;

@Dao
public class OrderDaoJdbcImpl extends AbstractDao<Order> implements OrderDao {

    public OrderDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Order create(Order order) throws DataProcessingException {
        String query = "INSERT INTO orders(user_id) VALUES(?);";
        try (PreparedStatement stmt =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, order.getUserId());
            stmt.executeUpdate();
            ResultSet resultSet = stmt.getGeneratedKeys();
            resultSet.next();
            Long orderId = resultSet.getLong(1);
            order.setId(orderId);
            setOrderItems(order);
            return order;
        } catch (SQLException e) {
            throw new DataProcessingException("couldn't add order " + order, e);
        }
    }

    @Override
    public Optional<Order> get(Long orderId) throws DataProcessingException {
        String query = "SELECT * FROM orders "
                + "WHERE order_id =? ";
        try (PreparedStatement stmt =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, orderId);
            ResultSet rs = stmt.executeQuery();
            Order order = new Order();
            while (rs.next()) {
                Long userId = rs.getLong("user_id");
                order.setItems(getOrderItems(orderId));
                order.setId(orderId);
                order.setUserId(userId);
            }
            return Optional.of(order);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get order with id = " + orderId, e);
        }
    }

    @Override
    public Order update(Order order) throws DataProcessingException {
        deleteOrderItems(order);
        setOrderItems(order);
        return order;
    }

    @Override
    public boolean deleteById(Long orderId) throws DataProcessingException {
        String query = "DELETE FROM orders WHERE order_id = ?";
        try (PreparedStatement stmt =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, orderId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get delete order with id = " + orderId, e);
        }
    }

    @Override
    public boolean delete(Order order) throws DataProcessingException {
        return deleteById(order.getId());
    }

    @Override
    public List<Order> getAll() throws DataProcessingException {
        String query = "SELECT * FROM orders;";
        try (PreparedStatement stmt =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ResultSet resultSet = stmt.executeQuery();
            return getOrdersFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get orders ", e);
        }
    }

    @Override
    public List<Order> getUserOrders(User user) throws DataProcessingException {
        String query = "SELECT * FROM orders WHERE user_id = ?;";
        try (PreparedStatement stmt =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, user.getId());
            ResultSet resultSet = stmt.executeQuery();
            return getOrdersFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get user orders  " + user, e);
        }
    }

    private List<Order> getOrdersFromResultSet(ResultSet resultSet) throws SQLException,
            DataProcessingException {
        List<Order> orders = new ArrayList<>();
        while (resultSet.next()) {
            Long orderId = resultSet.getLong("order_id");
            Order order = get(orderId).get();
            orders.add(order);
        }
        return orders;
    }

    private List<Item> getOrderItems(Long orderId) throws DataProcessingException {
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM orders_items o "
                + "JOIN items i ON o.order_id =? AND o.item_id = i.item_id;";
        try (PreparedStatement stmt =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, orderId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Long itemId = rs.getLong("item_id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                Item item = new Item();
                item.setName(name);
                item.setPrice(price);
                item.setId(itemId);
                items.add(item);
            }
            return items;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get order items = " + orderId, e);
        }
    }

    private Boolean deleteOrderItems(Order order) throws DataProcessingException {
        String deleteOrderItems = "DELETE FROM orders_items WHERE order_id =?;";
        try (PreparedStatement statement =
                     connection.prepareStatement(deleteOrderItems)) {
            statement.setLong(1, order.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update order", e);
        }
    }

    private void setOrderItems(Order order) throws DataProcessingException {
        String query = "INSERT INTO orders_items(order_id,item_id) VALUES(?,?);";
        try (PreparedStatement stmt =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            for (Item item : order.getItems()) {
                stmt.setLong(1, order.getId());
                stmt.setLong(2, item.getId());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("couldn't set order items " + order, e);
        }
    }
}
