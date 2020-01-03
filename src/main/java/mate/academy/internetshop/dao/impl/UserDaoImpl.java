package mate.academy.internetshop.dao.impl;

import mate.academy.internetshop.dao.Storage;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.library.Dao;
import mate.academy.internetshop.model.User;

import java.util.NoSuchElementException;
import java.util.Optional;

@Dao
public class UserDaoImpl implements UserDao {
    private static Long userId = 1L;

    @Override
    public User create(User user) {
        user.setId(userId++);
        Storage.users.add(user);
        return user;
    }

    @Override
    public Optional<User> get(Long id) {
        return Storage.users
                .stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    @Override
    public User update(User user) {
        User oldUser = Storage.users
                .stream()
                .filter(u -> u.getId().equals(user.getId()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Couldn't find element"));
        Storage.users.remove(oldUser);
        Storage.users.add(user);
        return user;
    }

    @Override
    public boolean delete(Long id) {
        User toBeDeletedUser = Storage.users
                .stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Couldn't find element"));
        return Storage.users.remove(toBeDeletedUser);
    }

    @Override
    public boolean delete(User user) {
        User toBeDeletedUser = Storage.users
                .stream()
                .filter(u -> u.getId().equals(user.getId()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Couldn't find element"));
        return Storage.users.remove(toBeDeletedUser);
    }
}
