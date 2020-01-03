package mate.academy.internetshop.factory;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.dao.impl.BucketDaoImpl;
import mate.academy.internetshop.dao.impl.ItemDaoImpl;
import mate.academy.internetshop.dao.impl.OrderDaoImpl;
import mate.academy.internetshop.dao.impl.UserDaoImpl;

public class Factory {
    private static BucketDao bucketDaoInstance;
    private static ItemDao itemDaoInstance;
    private static OrderDao orderDaoInstance;
    private static UserDao userDaoInstance;

    public static BucketDao getBucketDao() {
        return bucketDaoInstance == null
                ? new BucketDaoImpl() : bucketDaoInstance;
    }


    public static ItemDao getItemDao() {
        return itemDaoInstance == null
                ? new ItemDaoImpl() : itemDaoInstance;
    }

    public static OrderDao getOrderDao() {
        return orderDaoInstance == null
                ? new OrderDaoImpl() : orderDaoInstance;
    }

    public static UserDao getUserDao() {
        return userDaoInstance == null
                ? new UserDaoImpl() : userDaoInstance;
    }
}
