package de.olafj.vaadinplayground.grid;

import de.olafj.vaadinplayground.grid.GridItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;

public class GridItemCollection {

    private final ArrayList<GridItem> innerList = new ArrayList<>();

    public GridItemCollection() {
    }

    public void addItem(GridItem item) {
        innerList.add(item);
    }

    Stream<GridItem> stream() {
       return innerList.stream();
    }

    public boolean isEmpty() {
        return innerList.isEmpty();
    }

    public int size() {
        return innerList.size();
    }

    public void clear() {
        innerList.clear();
    }

    public Iterator<GridItem> iterator() {
        return innerList.iterator();
    }
}
