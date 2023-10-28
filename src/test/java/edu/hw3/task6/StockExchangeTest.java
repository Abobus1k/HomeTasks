package edu.hw3.task6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StockExchangeTest {
    @Test
    void testOneStock() {
        StockExchange stockExchange = new StockExchange();

        stockExchange.add(new Stock(1));

        Assertions.assertEquals(new Stock(1), stockExchange.mostValuableStock());
    }

    @Test
    void testSomeStocks() {
        StockExchange stockExchange = new StockExchange();

        stockExchange.add(new Stock(1));
        stockExchange.add(new Stock(2));
        stockExchange.add(new Stock(-10));

        Assertions.assertEquals(new Stock(-10), stockExchange.mostValuableStock());
    }

    @Test
    void testMostValuableStockAfterRemoving() {
        StockExchange stockExchange = new StockExchange();

        stockExchange.add(new Stock(1));
        stockExchange.add(new Stock(2));
        stockExchange.add(new Stock(-10));

        Stock stockToDelete = new Stock(-10);
        stockExchange.remove(stockToDelete);

        Assertions.assertEquals(new Stock(1), stockExchange.mostValuableStock());
    }
}
