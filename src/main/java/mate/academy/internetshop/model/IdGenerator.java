package mate.academy.internetshop.model;

public class IdGenerator {
    private static Long userId = 2L;
    private static Long bucketId = 1L;
    private static Long orderId = 1L;
    private static Long itemId = 1L;
    private static Long roleId = 1L;

    public static Long getNewUserId() {
        return userId++;
    }

    public static Long getNewBucketId() {
        return bucketId++;
    }

    public static Long getNewOrderId() {
        return orderId++;
    }

    public static Long getNewItemId() {
        return itemId++;
    }

    public static Long getNewRoleId() {
        return roleId++;
    }
}
