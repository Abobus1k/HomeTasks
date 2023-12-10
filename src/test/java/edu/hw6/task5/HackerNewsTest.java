package edu.hw6.task5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HackerNewsTest {

    @Test
    void testHackerNewsTopStories() {
        long[] topStories = HackerNews.hackerNewsTopStories();
        assertTrue(topStories.length > 0);
    }

    @Test
    void testNews() {
        long newsId = 37570037;
        String newsTitle = HackerNews.news(newsId);
        assertEquals("JDK 21 Release Notes", newsTitle);
    }
}
