package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.library.Dao;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import org.apache.log4j.Logger;

@Dao
public class UserDaoJdbcImpl extends AbstractDao<User> implements UserDao {
    private static String DB_NAME = "internetshop";
    private static String TABLE = "internetshop.orders";
    private static Logger logger = Logger.getLogger(OrderDaoJdbcImpl.class);

    public UserDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<User> getByLogin(String login) {
        String query = "SELECT * FROM users " +
                "JOIN users_roles ON users.login= ? " +
                "AND users.user_id = users_roles.user_id " +
                "JOIN roles ON users_roles.role_id = roles.role_id;";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = getUserFromResultSet(rs);
                return Optional.of(user);
            }
        } catch (SQLException e) {
            logger.error("couldn't get user with login = " + login, e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getByToken(String token) {
        String query = "SELECT * FROM users " +
                "JOIN users_roles ON users.token= ? " +
                "AND users.user_id = users_roles.user_id " +
                "JOIN roles ON users_roles.role_id = roles.role_id;";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, token);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = getUserFromResultSet(rs);
                return Optional.of(user);
            }
        } catch (SQLException e) {
            logger.error("couldn't get user with token = " + token, e);
        }
        return Optional.empty();
    }

    @Override
    public User create(User user) {
        String query = "INSERT INTO users(name,surname,login,password,token) VALUES(?,?,?,?,?);";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getLogin());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getToken());
            stmt.executeUpdate();
            ResultSet resultSet = stmt.getGeneratedKeys();
            resultSet.next();
            Long userId = resultSet.getLong(1);
            user.setId(userId);
            setRoles(user);
            return user;
        } catch (SQLException e) {
            logger.error("Couldn't add user " + user, e);
        }
        return null;
    }

    private void setRoles(User user) {
        String query = "INSERT INTO users_roles (user_id, role_id) VALUES"
                + " (?, (SELECT role_id FROM roles WHERE role_name = ?));";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            for (Role role : user.getRoles()) {
                stmt.setLong(1, user.getId());
                stmt.setString(2, String.valueOf(role.getRoleName()));
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error("Couldn't set roles for user = " + user, e);
        }
    }

    private boolean deleteRoles(User user) {
        String query = "DELETE FROM users_roles WHERE user_id = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, user.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error("couldn't delete roles for user " + user, e);
        }
        return false;
    }

    @Override
    public Optional<User> get(Long userId) {
        String query = "SELECT * FROM users " +
                "JOIN users_roles ON users.user_id = ? AND users.user_id = users_roles.user_id " +
                "JOIN roles ON users_roles.role_id = roles.role_id;";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = getUserFromResultSet(rs);
                return Optional.of(user);
            }
        } catch (SQLException e) {
            logger.error("couldn't get user with id = " + userId, e);
        }
        return Optional.empty();
    }

    @Override
    public User update(User user) {
        String query = "UPDATE users SET name = ?, surname = ?,login = ?, password = ?, token = ? "
                + "WHERE user_id = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getLogin());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getToken());
            stmt.setLong(6, user.getId());
            stmt.executeUpdate();
            deleteRoles(user);
            setRoles(user);
            return user;
        } catch (SQLException e) {
            logger.error("couldn't update user " + user, e);
        }
        return null;
    }

    @Override
    public boolean deleteById(Long userId) {
        String query = "DELETE FROM users WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, userId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error("couldn't delete user with id = " + userId, e);
        }
        return false;
    }

    @Override
    public boolean delete(User user) {
        return deleteById(user.getId());
    }

    @Override
    public List<User> getAll() {
        String query = "SELECT * FROM users " +
                "JOIN users_roles ON  users.user_id = users_roles.user_id " +
                "JOIN roles ON users_roles.role_id = roles.role_id;";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            List<User> users = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(getUserFromResultSet(rs));
            }
            return users;
        } catch (SQLException e) {
            logger.error("couldn't get users ", e);
        }
        return null;
    }

    private User getUserFromResultSet(ResultSet rs) throws SQLException {
        Long userId = rs.getLong("user_id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String login = rs.getString("login");
        String password = rs.getString("password");
        String token = rs.getString("token");
        String role = rs.getString("role_name");
        User user = new User();
        user.setId(userId);
        user.setName(name);
        user.setSurname(surname);
        user.setLogin(login);
        user.setPassword(password);
        user.setToken(token);
        user.getRoles().add(Role.of(role));
        return user;
    }
}
