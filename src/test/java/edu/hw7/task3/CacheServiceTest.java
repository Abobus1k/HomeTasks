package edu.hw7.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CacheServiceTest {

    private CacheService cacheService;

    @BeforeEach
    void setUp() {
        cacheService = new CacheService();
    }

    @Test
    void testAddPerson() {
        Person person = new Person(1, "Name1", "Address1", "Phone1");
        cacheService.add(person);

        assertEquals(person, cacheService.findByName("Name1"));
        assertEquals(person, cacheService.findByAddress("Address1"));
        assertEquals(person, cacheService.findByPhone("Phone1"));
    }

    @Test
    void testDeletePerson() {
        Person person = new Person(2, "Name1", "Address1", "Phone1");
        cacheService.add(person);

        cacheService.delete(2);

        assertNull(cacheService.findByName("Name1"));
        assertNull(cacheService.findByAddress("Address1"));
        assertNull(cacheService.findByPhone("Phone1"));
    }

    @Test
    void testFindByName() {
        Person person = new Person(3, "Name2", "Address2", "Phone2");
        cacheService.add(person);

        assertEquals(person, cacheService.findByName("Name2"));
    }

    @Test
    void testFindByAddress() {
        Person person = new Person(4, "Name3", "Address3", "Phone3");
        cacheService.add(person);

        assertEquals(person, cacheService.findByAddress("Address3"));
    }

    @Test
    void testFindByPhone() {
        Person person = new Person(5, "Name4", "Address4", "Phone4");
        cacheService.add(person);

        assertEquals(person, cacheService.findByPhone("Phone4"));
    }
}
