package mate.academy.internetshop.dao.impl;

import java.util.Optional;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.dao.Storage;
import mate.academy.internetshop.library.Dao;
import mate.academy.internetshop.model.Item;

@Dao
public class ItemDaoImpl implements ItemDao {
    private static Long itemId = 1L;

    @Override
    public Item create(Item item) {
        item.setId(itemId++);
        Storage.items.add(item);
        return item;
    }

    @Override
    public Optional<Item> get(Long id) {
        return Storage.items
                .stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();
    }

    @Override
    public Item update(Item item) {
        for (int i = 0; i < Storage.items.size(); i++) {
            if (Storage.items.get(i).getId().equals(item.getId())) {
                Storage.items.get(i).setId(item.getId());
                Storage.items.get(i).setName(item.getName());
                Storage.items.get(i).setPrice(item.getPrice());
            }
        }
        return item;
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Item> toBeDeletedItem = Storage.items
                .stream()
                .filter(item1 -> item1.getId().equals(id))
                .findFirst();
        return Storage.items.remove(toBeDeletedItem.get());
    }

    @Override
    public boolean delete(Item item) {
        Optional<Item> toBeDeletedItem = Storage.items
                .stream()
                .filter(item1 -> item1.getId().equals(item.getId()))
                .findFirst();
        return Storage.items.remove(toBeDeletedItem.get());
    }
}
