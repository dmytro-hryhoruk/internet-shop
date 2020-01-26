package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.library.Dao;
import mate.academy.internetshop.model.Item;
import org.apache.log4j.Logger;

@Dao
public class ItemDaoJdbcImpl extends AbstractDao<Item> implements ItemDao {
    private static String DB_NAME = "internetshop";
    private static String TABLE = "internetshop.items";
    private static Logger logger = Logger.getLogger(ItemDaoJdbcImpl.class);

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Item create(Item item) {
        String query = "INSERT INTO items(name, price) VALUES(?,?);";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, item.getName());
            stmt.setDouble(2, item.getPrice());
            stmt.executeUpdate();
            ResultSet resultSet = stmt.getGeneratedKeys();
            while (resultSet.next()) {
                item.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            logger.error("couldn't add item " + item, e);
        }
        return item;
    }

    @Override
    public Optional<Item> get(Long entityId) {
        String query = "SELECT * FROM items WHERE item_id = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, entityId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Long item_id = rs.getLong("item_id");
                String name = rs.getString("name");
                Double price = rs.getDouble("price");
                Item item = new Item();
                item.setName(name);
                item.setPrice(price);
                item.setId(item_id);
                return Optional.ofNullable(item);
            }
        } catch (SQLException e) {
            logger.warn("Can't get item with id = " + entityId, e);
        }
        return Optional.empty();

    }

    @Override
    public Item update(Item entity) {
        String query = "UPDATE items SET name=?, price=? WHERE item_id=?;";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, entity.getName());
            stmt.setDouble(2, entity.getPrice());
            stmt.setLong(3, entity.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.warn("Item couldn't be update", e);
        }
        return entity;
    }

    @Override
    public boolean deleteById(Long itemId) {
        String query = "DELETE FROM items WHERE item_id=?;";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, itemId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.warn("Couldn't delete item with id=" + itemId, e);
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Item item) {
        return deleteById(item.getId());
    }

    @Override
    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM items;";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Long item_id = rs.getLong("item_id");
                String name = rs.getString("name");
                Double price = rs.getDouble("price");
                Item item = new Item();
                item.setName(name);
                item.setPrice(price);
                item.setId(item_id);
                items.add(item);
            }
        } catch (SQLException e) {
            logger.warn("Couldn't get all items", e);
        }
        return items;
    }
}
