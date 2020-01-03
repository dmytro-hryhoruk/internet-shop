package mate.academy.internetshop.model;

import java.util.List;

public class User {
    private Long id;
    private List<Order> userOrders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Order> getUserOrders() {
        return userOrders;
    }

    public void setUserOrders(List<Order> userOrders) {
        this.userOrders = userOrders;
    }
}
