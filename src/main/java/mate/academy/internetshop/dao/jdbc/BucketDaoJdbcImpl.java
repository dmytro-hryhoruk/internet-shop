package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.library.Dao;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;

@Dao
public class BucketDaoJdbcImpl extends AbstractDao<Bucket> implements BucketDao {

    public BucketDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Bucket create(Bucket bucket) throws DataProcessingException {
        String query = "INSERT INTO buckets(user_id) VALUES(?);";
        try (PreparedStatement stmt =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, bucket.getUserId());
            stmt.executeUpdate();
            ResultSet resultSet = stmt.getGeneratedKeys();
            resultSet.next();
            Long bucketId = resultSet.getLong(1);
            bucket.setId(bucketId);
            setBucketItems(bucket);
        } catch (SQLException e) {
            throw new DataProcessingException("couldn't creat bucket", e);
        }
        return bucket;
    }

    @Override
    public Optional<Bucket> get(Long bucketId) throws DataProcessingException {
        String query = "SELECT * FROM buckets "
                + "WHERE bucket_id =? ";
        return findByParameter(query, bucketId);
    }

    @Override
    public Optional<Bucket> getByUserId(Long userId) throws DataProcessingException {
        String query = "SELECT * FROM buckets "
                + "WHERE user_id =? ";
        return findByParameter(query, userId);
    }

    @Override
    public Bucket update(Bucket bucket) throws DataProcessingException {
        deleteBucketItems(bucket);
        setBucketItems(bucket);
        return bucket;
    }

    @Override
    public boolean deleteById(Long bucketId) throws DataProcessingException {
        String query = "DELETE FROM buckets WHERE bucket_id = ?";
        try (PreparedStatement stmt =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, bucketId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete bucket with id = " + bucketId, e);
        }
    }

    @Override
    public boolean delete(Bucket bucket) throws DataProcessingException {
        return deleteById(bucket.getId());
    }

    @Override
    public List<Bucket> getAll() throws DataProcessingException {
        String query = "SELECT * FROM buckets;";
        try (PreparedStatement stmt =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ResultSet resultSet = stmt.executeQuery();
            List<Bucket> buckets = new ArrayList<>();
            while (resultSet.next()) {
                Long bucketId = resultSet.getLong("order_id");
                Bucket bucket = get(bucketId).get();
                buckets.add(bucket);
            }
            return buckets;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all buckets  ", e);
        }
    }

    private Optional<Bucket> findByParameter(String query, Long parameter)
            throws DataProcessingException {
        try (PreparedStatement stmt =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, parameter);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Long userId = rs.getLong("user_id");
                Long bucketId = rs.getLong("bucket_id");
                Bucket bucket = new Bucket();
                bucket.setItems(getBucketItems(bucketId));
                bucket.setId(bucketId);
                bucket.setUserId(userId);
                return Optional.of(bucket);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get bucket with parameter = " + parameter, e);
        }
        return Optional.empty();
    }

    private Boolean deleteBucketItems(Bucket bucket) throws DataProcessingException {
        String deleteBucketItems =
                "DELETE FROM buckets_items WHERE bucket_id =?;";
        try (PreparedStatement statement =
                     connection.prepareStatement(deleteBucketItems)) {
            statement.setLong(1, bucket.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update bucket", e);
        }
    }

    private void setBucketItems(Bucket bucket) throws DataProcessingException {
        String query = "INSERT INTO buckets_items(bucket_id,item_id) VALUES(?,?);";
        try (PreparedStatement stmt =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            for (Item item : bucket.getItems()) {
                stmt.setLong(1, bucket.getId());
                stmt.setLong(2, item.getId());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("couldn't set bucket's items " + bucket, e);
        }
    }

    private List<Item> getBucketItems(Long bucketId) throws DataProcessingException {
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM buckets_items o "
                + "JOIN items i ON o.bucket_id =? AND o.item_id = i.item_id;";
        try (PreparedStatement stmt =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, bucketId);
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
            throw new DataProcessingException("Can't get bucket items " + bucketId, e);
        }
    }
}
