package mate.academy.internetshop.service.impl;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.dao.Storage;
import mate.academy.internetshop.library.Inject;
import mate.academy.internetshop.library.Service;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.ItemService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ItemServiceImpl implements ItemService {
    @Inject
    private ItemDao itemDao;

    @Override
    public Item create(Item item) {
        return itemDao.create(item);
    }

    @Override
    public Item get(Long id) {
        return itemDao.get(id)
                .orElseThrow(() -> new NoSuchElementException("Couldn't find element with given id"));
    }

    @Override
    public Item update(Item item) {
        return itemDao.update(item);
    }

    @Override
    public void delete(Long id) {
        itemDao.delete(id);
    }

    @Override
    public void delete(Item item) {
        itemDao.delete(item);
    }

    @Override
    public List<Item> getAllItems() {
        return Storage.items;
    }
}
