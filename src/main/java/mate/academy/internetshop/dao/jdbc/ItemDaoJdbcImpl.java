package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
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
    private static String TABLE = "items";
    private static Logger logger = Logger.getLogger(ItemDaoJdbcImpl.class);

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Item create(Item entity) {
        String query = String.format("INSERT INTO %s(name, price) VALUES('%s', %f)",
                TABLE, entity.getName(), entity.getPrice());

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.warn(e);
        }
        return entity;
    }

    @Override
    public Optional<Item> get(Long entityId) {
        String query = String.format("SELECT * FROM %s WHERE item_id=%d", TABLE, entityId);
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
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
            logger.warn(e);
        }
        return Optional.empty();

    }

    @Override
    public Item update(Item entity) {
        String query = String.format("UPDATE items SET name='%s', price=%f WHERE item_id=%d",
                entity.getName(), entity.getPrice(), entity.getId());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.warn(e);
        }
        return entity;
    }

    @Override
    public boolean deleteById(Long entityId) {
        Optional<Item> item = get(entityId);
        if (item.isPresent()) {
            String query = String.format("DELETE FROM items WHERE item_id=%d", entityId);
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(query);
            } catch (SQLException e) {
                logger.warn(e);
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Item entity) {
        Optional<Item> item = get(entity.getId());
        if (item.isPresent()) {
            String query = String.format("DELETE FROM %s.items\n" +
                    "WHERE item_id =%d;", DB_NAME, entity.getId());
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(query);
            } catch (SQLException e) {
                logger.warn(e);
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        String query = String.format("SELECT * FROM %s", TABLE);
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
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
            logger.warn(e);
        }
        return items;
    }
}
