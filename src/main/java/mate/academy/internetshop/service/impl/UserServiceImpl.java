package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.exceptions.AuthenticationException;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.library.Inject;
import mate.academy.internetshop.library.Service;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;
import mate.academy.internetshop.util.HashUtil;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private static UserDao userDao;

    @Override
    public User create(User user) throws DataProcessingException {
        user.setSalt(HashUtil.getSalt());
        user.setPassword(HashUtil.hashPassword(user.getPassword(),user.getSalt()));
        user.setToken(getToken());
        return userDao.create(user);
    }

    @Override
    public User get(Long userId) throws DataProcessingException {
        return userDao.get(userId)
                .orElseThrow(() -> new DataProcessingException("Couldn't find element",
                        new NoSuchElementException()));
    }

    @Override
    public Optional<User> getByToken(String token) throws DataProcessingException {
        return userDao.getByToken(token);
    }


    private String getToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public User update(User user) throws DataProcessingException {
        return userDao.update(user);
    }

    @Override
    public boolean deleteById(Long userId) throws DataProcessingException {
        return userDao.deleteById(userId);
    }

    @Override
    public boolean delete(User user) throws DataProcessingException {
        return userDao.delete(user);
    }

    @Override
    public List<User> getAll() throws DataProcessingException {
        return userDao.getAll();
    }

    @Override
    public User login(String login, String password)
            throws AuthenticationException, DataProcessingException {
        Optional<User> user = userDao.getByLogin(login);
        if (user.isEmpty() || !isValid(user.get(), password)) {
            throw new AuthenticationException("Incorrect user name or password");
        }
        return user.get();
    }

    private boolean isValid(User user, String password) {
        return HashUtil.hashPassword(password, user.getSalt())
                .equals(user.getPassword());
    }
}
