package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.Storage;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.exceptions.AuthenticationException;
import mate.academy.internetshop.library.Dao;
import mate.academy.internetshop.model.User;

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
    public Optional<User> getByToken(String token) {
        return Storage.users.stream()
                .filter(user -> user.getToken().equals(token))
                .findFirst();
    }

    @Override
    public User update(User user) {
        for (int i = 0; i < Storage.users.size(); i++) {
            if (Storage.users.get(i).getId().equals(user.getId())) {
                Storage.users.get(i).setId(user.getId());
            }
        }
        return user;
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<User> toBeDeletedUser = Storage.users
                .stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
        return Storage.users.remove(toBeDeletedUser.get());
    }

    @Override
    public boolean delete(User user) {
        Optional<User> toBeDeletedUser = Storage.users
                .stream()
                .filter(u -> u.getId().equals(user.getId()))
                .findFirst();
        return Storage.users.remove(toBeDeletedUser.get());
    }

    @Override
    public List<User> getAll() {
        return Storage.users;
    }

    @Override
    public Optional<User> getByLogin(String login) {
        return Storage.users.stream()
                .filter(u -> u.getLogin().equals(login))
                .findFirst();
    }
}
