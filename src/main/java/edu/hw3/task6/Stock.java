package edu.hw3.task6;

import java.util.Objects;

public class Stock {
    private final double price;

    public Stock(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Double.compare(price, stock.price) == 0;
    }
}
