package mate.academy.internetshop.service;

import java.util.List;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.impl.GenericService;

public interface ItemService extends GenericService<Item, Long> {
    List<Item> getAllItems();
}
