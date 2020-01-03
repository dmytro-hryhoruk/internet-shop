package mate.academy.internetshop.service.impl;

import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.library.Inject;
import mate.academy.internetshop.library.Service;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User create(User user) {
        return userDao.create(user);
    }

    @Override
    public User get(Long userId) {
        return userDao.get(userId)
                .orElseThrow(() -> new NoSuchElementException("Couldn't find element with given id"));
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean delete(Long userId) {
        return userDao.delete(userId);
    }

    @Override
    public boolean delete(User user) {
        return userDao.delete(user);
    }
}
