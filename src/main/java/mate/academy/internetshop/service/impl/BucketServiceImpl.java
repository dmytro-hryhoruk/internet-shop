package mate.academy.internetshop.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.library.Inject;
import mate.academy.internetshop.library.Service;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;

@Service
public class BucketServiceImpl implements BucketService {
    @Inject
    private static BucketDao bucketDao;

    @Override
    public Bucket create(Bucket bucket) throws DataProcessingException {
        return bucketDao.create(bucket);
    }

    @Override
    public Bucket get(Long bucketId) throws DataProcessingException {
        return bucketDao.get(bucketId)
                .orElseThrow(() -> new DataProcessingException("Couldn't find element",
                        new NoSuchElementException()));
    }

    @Override
    public Bucket getByUserId(Long userId) throws DataProcessingException {
        return bucketDao.getByUserId(userId).get();
    }

    @Override
    public Bucket update(Bucket bucket) throws DataProcessingException {
        return bucketDao.update(bucket);
    }

    @Override
    public boolean deleteById(Long bucketId) throws DataProcessingException {
        return bucketDao.deleteById(bucketId);
    }

    @Override
    public boolean delete(Bucket bucket) throws DataProcessingException {
        return bucketDao.delete(bucket);
    }

    @Override
    public void addItem(Bucket bucket, Item item) throws DataProcessingException {
        List<Item> items = bucket.getItems();
        items.add(item);
        update(bucket);
    }

    @Override
    public void deleteItem(Bucket bucket, Item item) throws DataProcessingException {
        bucket.getItems().remove(item);
        update(bucket);
    }

    @Override
    public void clear(Bucket bucket) throws DataProcessingException {
        bucket.getItems().clear();
        update(bucket);
    }

    @Override
    public List<Item> getAllItems(Bucket bucket) {
        return bucket.getItems();
    }

    @Override
    public List<Bucket> getAll() throws DataProcessingException {
        return bucketDao.getAll();
    }
}
