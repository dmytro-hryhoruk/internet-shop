package mate.academy.internetshop.dao.impl;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.dao.Storage;
import mate.academy.internetshop.library.Dao;
import mate.academy.internetshop.model.Item;

import java.util.NoSuchElementException;
import java.util.Optional;

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
        Item oldItem = Storage.items
                .stream()
                .filter(i -> i.getId().equals(item.getId()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Couldn't find element"));
        Storage.items.remove(oldItem);
        Storage.items.add(item);
        return item;
    }

    @Override
    public boolean delete(Long id) {
        Item toBeDeletedItem = Storage.items
                .stream()
                .filter(item1 -> item1.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Couldn't find element"));
        return Storage.items.remove(toBeDeletedItem);
    }

    @Override
    public boolean delete(Item item) {
        Item toBeDeletedItem = Storage.items
                .stream()
                .filter(item1 -> item1.getId().equals(item.getId()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Couldn't find element"));
        return Storage.items.remove(toBeDeletedItem);
    }

}
