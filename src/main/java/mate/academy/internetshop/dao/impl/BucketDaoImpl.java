package mate.academy.internetshop.dao.impl;

import java.util.Optional;
import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.Storage;
import mate.academy.internetshop.library.Dao;
import mate.academy.internetshop.model.Bucket;

@Dao
public class BucketDaoImpl implements BucketDao {
    private static Long bucketId = 1L;

    @Override
    public Bucket create(Bucket bucket) {
        bucket.setId(bucketId++);
        Storage.buckets.add(bucket);
        return bucket;

    }

    @Override
    public Optional<Bucket> get(Long id) {
        return Storage.buckets
                .stream()
                .filter(b -> b.getId().equals(id))
                .findFirst();
    }

    @Override
    public Bucket update(Bucket bucket) {
        for (int i = 0; i < Storage.buckets.size(); i++) {
            if (Storage.buckets.get(i).getId().equals(bucket.getId())) {
                Storage.buckets.get(i).setId(bucket.getId());
                Storage.buckets.get(i).setUserId(bucket.getUserId());
                Storage.buckets.get(i).setItems(bucket.getItems());
            }
        }
        return bucket;
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Bucket> toBeDeletedBucket = Storage.buckets
                .stream()
                .filter(b -> b.getId().equals(id))
                .findFirst();
        return Storage.buckets.remove(toBeDeletedBucket.get());
    }

    @Override
    public boolean delete(Bucket bucket) {
        Optional<Bucket> toBeDeletedBucket = Storage.buckets
                .stream()
                .filter(b -> b.getId().equals(bucket.getId()))
                .findFirst();
        return Storage.buckets.remove(toBeDeletedBucket.get());
    }
}
