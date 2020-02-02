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
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.library.Dao;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;

@Dao
public class UserDaoJdbcImpl extends AbstractDao<User> implements UserDao {

    public UserDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<User> getByLogin(String login) throws DataProcessingException {
        String query = "SELECT * FROM users "
                + "JOIN users_roles ON users.login= ? "
                + "AND users.user_id = users_roles.user_id "
                + "JOIN roles ON users_roles.role_id = roles.role_id;";
        try (PreparedStatement stmt =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            User user = new User();
            while (rs.next()) {
                user = getUserFromResultSet(rs);
            }
            return Optional.of(user);
        } catch (SQLException e) {
            throw new DataProcessingException("couldn't get user with login = " + login, e);
        }
    }

    @Override
    public Optional<User> getByToken(String token) throws DataProcessingException {
        String query = "SELECT * FROM users "
                + "JOIN users_roles ON users.token= ? "
                + "AND users.user_id = users_roles.user_id "
                + "JOIN roles ON users_roles.role_id = roles.role_id;";
        try (PreparedStatement stmt =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, token);
            ResultSet rs = stmt.executeQuery();
            User user = new User();
            while (rs.next()) {
                user = getUserFromResultSet(rs);
            }
            return Optional.of(user);
        } catch (SQLException e) {
            throw new DataProcessingException("couldn't get user with token = " + token, e);
        }
    }

    @Override
    public User create(User user) throws DataProcessingException {
        String query = "INSERT INTO users(name,surname,login,password,token,salt) "
                + "VALUES(?,?,?,?,?,?);";
        try (PreparedStatement stmt =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getLogin());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getToken());
            stmt.setBytes(6, user.getSalt());
            stmt.executeUpdate();
            ResultSet resultSet = stmt.getGeneratedKeys();
            resultSet.next();
            Long userId = resultSet.getLong(1);
            user.setId(userId);
            setRoles(user);
            return user;
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't add user " + user, e);
        }
    }

    private void setRoles(User user) throws DataProcessingException {
        String query = "INSERT INTO users_roles (user_id, role_id) VALUES"
                + " (?, (SELECT role_id FROM roles WHERE role_name = ?));";
        try (PreparedStatement stmt =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            for (Role role : user.getRoles()) {
                stmt.setLong(1, user.getId());
                stmt.setString(2, String.valueOf(role.getRoleName()));
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't set roles for user = " + user, e);
        }
    }

    private boolean deleteRoles(User user) throws DataProcessingException {
        String query = "DELETE FROM users_roles WHERE user_id = ?;";
        try (PreparedStatement stmt =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, user.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("couldn't delete roles for user " + user, e);
        }
    }

    @Override
    public Optional<User> get(Long userId) throws DataProcessingException {
        String query = "SELECT * FROM users "
                + "JOIN users_roles ON users.user_id = ? AND users.user_id = users_roles.user_id "
                + "JOIN roles ON users_roles.role_id = roles.role_id;";
        try (PreparedStatement stmt =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, userId);
            ResultSet rs = stmt.executeQuery();
            User user = new User();
            while (rs.next()) {
                user = getUserFromResultSet(rs);
            }
            return Optional.of(user);
        } catch (SQLException e) {
            throw new DataProcessingException("couldn't get user with id = " + userId, e);
        }
    }

    @Override
    public User update(User user) throws DataProcessingException {
        String query = "UPDATE users SET name = ?, surname = ?,login = ?,"
                + "password = ?, token = ?, salt = ? "
                + "WHERE user_id = ?;";
        try (PreparedStatement stmt =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getLogin());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getToken());
            stmt.setBytes(6, user.getSalt());
            stmt.setLong(7, user.getId());
            stmt.executeUpdate();
            deleteRoles(user);
            setRoles(user);
            return user;
        } catch (SQLException e) {
            throw new DataProcessingException("couldn't update user " + user, e);
        }
    }

    @Override
    public boolean deleteById(Long userId) throws DataProcessingException {
        String query = "DELETE FROM users WHERE user_id = ?";
        try (PreparedStatement stmt =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, userId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("couldn't delete user with id = " + userId, e);
        }
    }

    @Override
    public boolean delete(User user) throws DataProcessingException {
        return deleteById(user.getId());
    }

    @Override
    public List<User> getAll() throws DataProcessingException {
        String query = "SELECT * FROM users "
                + "JOIN users_roles ON  users.user_id = users_roles.user_id "
                + "JOIN roles ON users_roles.role_id = roles.role_id;";
        try (PreparedStatement stmt =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            List<User> users = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(getUserFromResultSet(rs));
            }
            return users;
        } catch (SQLException e) {
            throw new DataProcessingException("couldn't get users ", e);
        }
    }

    private User getUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        Long userId = rs.getLong("user_id");
        user.setId(userId);
        String name = rs.getString("name");
        user.setName(name);
        String surname = rs.getString("surname");
        user.setSurname(surname);
        String login = rs.getString("login");
        user.setLogin(login);
        String password = rs.getString("password");
        user.setPassword(password);
        String token = rs.getString("token");
        user.setToken(token);
        String role = rs.getString("role_name");
        user.getRoles().add(Role.of(role));
        byte[] salt = rs.getBytes("salt");
        user.setSalt(salt);
        return user;
    }
}
