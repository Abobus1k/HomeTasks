package edu.hw7.task3;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ImbalanceCacheService implements PersonDatabase {
    private final Map<Integer, Person> idCache = new HashMap<>();
    private final Map<String, Person> nameCache = new HashMap<>();
    private final Map<String, Person> addressCache = new HashMap<>();
    private final Map<String, Person> phoneCache = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void add(Person person) {
        lock.writeLock().lock();
        try {
            idCache.put(person.id(), person);
            nameCache.put(person.name(), person);
            addressCache.put(person.address(), person);
            phoneCache.put(person.phoneNumber(), person);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        lock.writeLock().lock();
        try {
            Person person = idCache.remove(id);
            if (person != null) {
                nameCache.remove(person.name());
                addressCache.remove(person.address());
                phoneCache.remove(person.phoneNumber());
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public Person findByName(String name) {
        lock.readLock().lock();
        try {
            return nameCache.get(name);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public Person findByAddress(String address) {
        lock.readLock().lock();
        try {
            return addressCache.get(address);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public Person findByPhone(String phone) {
        lock.readLock().lock();
        try {
            return phoneCache.get(phone);
        } finally {
            lock.readLock().unlock();
        }
    }
}
