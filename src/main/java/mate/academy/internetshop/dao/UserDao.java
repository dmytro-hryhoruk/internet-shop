package mate.academy.internetshop.dao;

import mate.academy.internetshop.model.User;
import java.util.Optional;

public interface UserDao {
    User create(User user);

    Optional<User> get(Long userId);

    User update(User user);

    boolean deleteById(Long userId);

    boolean delete(User user);
}
