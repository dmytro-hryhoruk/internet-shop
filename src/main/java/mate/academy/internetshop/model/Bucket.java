package mate.academy.internetshop.model;

import java.util.List;

public class Bucket {
    private Long userId;
    private Long id;
    private List<Item> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = this.id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
