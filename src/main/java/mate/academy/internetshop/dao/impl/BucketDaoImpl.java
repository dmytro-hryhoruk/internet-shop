package mate.academy.internetshop.dao.impl;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.Storage;
import mate.academy.internetshop.library.Dao;
import mate.academy.internetshop.model.Bucket;

import java.util.NoSuchElementException;
import java.util.Optional;

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
        Bucket oldBucket = Storage.buckets
                .stream()
                .filter(b -> b.getId().equals(bucket.getId()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Couldn't find element"));
        Storage.buckets.remove(oldBucket);
        Storage.buckets.add(bucket);
        return bucket;
    }


    @Override
    public boolean delete(Long id) {
        Bucket toBeDeletedBucket = Storage.buckets
                .stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Couldn't find element"));
        return Storage.buckets.remove(toBeDeletedBucket);
    }

    @Override
    public boolean delete(Bucket bucket) {
        Bucket toBeDeletedBucket = Storage.buckets
                .stream()
                .filter(b -> b.getId().equals(bucket.getId()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Couldn't find element"));
        return Storage.buckets.remove(toBeDeletedBucket);
    }
}
