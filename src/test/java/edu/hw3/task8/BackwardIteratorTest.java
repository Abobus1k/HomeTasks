package edu.hw3.task8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BackwardIteratorTest {
    private List<Integer> numbers;
    private BackwardIterator<Integer> reverseIterator;

    @BeforeEach
    void setUp() {
        numbers = Arrays.asList(1, 2, 3, 4, 5);
        reverseIterator = new BackwardIterator<>(numbers);
    }

    @Test
    void testHasNext() {
        assertTrue(reverseIterator.hasNext());
        for (int i = 0; i < numbers.size(); i++) {
            reverseIterator.next();
        }
        assertFalse(reverseIterator.hasNext());
    }

    @Test
    void testNext() {
        int expected = 5;
        while (reverseIterator.hasNext()) {
            assertEquals(expected, reverseIterator.next());
            expected--;
        }
    }

    @Test
    void testNextThrowsException() {
        for (int i = 0; i < numbers.size(); i++) {
            reverseIterator.next();
        }
        assertThrows(NoSuchElementException.class, reverseIterator::next);
    }
}
