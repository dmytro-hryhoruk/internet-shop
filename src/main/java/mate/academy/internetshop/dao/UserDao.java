package mate.academy.internetshop.dao;

import java.util.Optional;
import mate.academy.internetshop.exceptions.AuthenticationException;
import mate.academy.internetshop.model.User;

public interface UserDao extends GenericDao<User, Long> {
    Optional<User> getByLogin(String login, String password) throws AuthenticationException;

    Optional<User> getByToken(String token);
}
