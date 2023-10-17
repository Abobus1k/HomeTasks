package edu.hw3.task8;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BackwardIterator<T> implements Iterator<T> {

    private final List<T> collection;
    private int index;

    public BackwardIterator(List<T> collection) {
        this.collection = collection;
        this.index = collection.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return index >= 0;
    }

    @Override
    public T next() {
        if (hasNext()) {
            T element = collection.get(index);
            index--;
            return element;
        }
        throw new NoSuchElementException();
    }
}
