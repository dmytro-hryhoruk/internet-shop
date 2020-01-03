package mate.academy.internetshop.library;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.factory.Factory;

import java.util.HashMap;
import java.util.Map;

public class AnnotatedClassMap {
    private static final Map<Class, Object> classMap = new HashMap<>();

    static {
        classMap.put(UserDao.class, Factory.getUserDao());
        classMap.put(BucketDao.class, Factory.getBucketDao());
        classMap.put(ItemDao.class, Factory.getItemDao());
        classMap.put(OrderDao.class, Factory.getOrderDao());
    }

    public static Object getImplementation(Class interfaceClass) {
        return classMap.get(interfaceClass);
    }
}