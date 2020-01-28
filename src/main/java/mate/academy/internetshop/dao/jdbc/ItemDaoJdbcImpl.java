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
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.library.Dao;
import mate.academy.internetshop.model.Item;

@Dao
public class ItemDaoJdbcImpl extends AbstractDao<Item> implements ItemDao {

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Item create(Item item) throws DataProcessingException {
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
            throw new DataProcessingException("couldn't add item " + item, e);
        }
        return item;
    }

    @Override
    public Optional<Item> get(Long itemId) throws DataProcessingException {
        String query = "SELECT * FROM items WHERE item_id = ?;";
        try (PreparedStatement stmt =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, itemId);
            ResultSet rs = stmt.executeQuery();
            Item item = new Item();
            while (rs.next()) {
                Long item_id = rs.getLong("item_id");
                String name = rs.getString("name");
                Double price = rs.getDouble("price");
                item.setName(name);
                item.setPrice(price);
                item.setId(item_id);
            }
            return Optional.of(item);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get item with id = " + itemId, e);
        }
    }

    @Override
    public Item update(Item item) throws DataProcessingException {
        String query = "UPDATE items SET name=?, price=? WHERE item_id=?;";
        try (PreparedStatement stmt =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, item.getName());
            stmt.setDouble(2, item.getPrice());
            stmt.setLong(3, item.getId());
            stmt.executeUpdate();
            return item;
        } catch (SQLException e) {
            throw new DataProcessingException("Item couldn't be update", e);
        }
    }

    @Override
    public boolean deleteById(Long itemId) throws DataProcessingException {
        String query = "DELETE FROM items WHERE item_id=?;";
        try (PreparedStatement stmt =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, itemId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't delete item with id=" + itemId, e);
        }
    }

    @Override
    public boolean delete(Item item) throws DataProcessingException {
        return deleteById(item.getId());
    }

    @Override
    public List<Item> getAll() throws DataProcessingException {
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
            return items;
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't get all items", e);
        }
    }
}
