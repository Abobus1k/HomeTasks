package edu.hw7.task3;

import java.util.HashMap;
import java.util.Map;

public class CacheService implements PersonDatabase {
    private final Map<Integer, Person> idCache = new HashMap<>();
    private final Map<String, Person> nameCache = new HashMap<>();
    private final Map<String, Person> addressCache = new HashMap<>();
    private final Map<String, Person> phoneCache = new HashMap<>();

    @Override
    public void add(Person person) {
        synchronized (this) {
            idCache.put(person.id(), person);
            nameCache.put(person.name(), person);
            addressCache.put(person.address(), person);
            phoneCache.put(person.phoneNumber(), person);
        }
    }

    @Override
    public void delete(int id) {
        synchronized (this) {
            Person person = idCache.remove(id);
            if (person != null) {
                nameCache.remove(person.name());
                addressCache.remove(person.address());
                phoneCache.remove(person.phoneNumber());
            }
        }
    }

    @Override
    public Person findByName(String name) {
        return nameCache.get(name);
    }

    @Override
    public Person findByAddress(String address) {
        return addressCache.get(address);
    }

    @Override
    public Person findByPhone(String phone) {
        return phoneCache.get(phone);
    }
}
