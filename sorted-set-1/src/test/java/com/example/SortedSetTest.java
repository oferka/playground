package com.example;

import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SortedSetTest {

    @Test
    public void testCreate() {
        SortedSet sortedSet = new TreeSet();
        sortedSet.add(1);
        sortedSet.add(2);
        sortedSet.add(3);
        assertEquals(sortedSet.size(), 3);
    }

    @Test
    public void testSpecifiedComparator() {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        };
        SortedSet sortedSet = new TreeSet(comparator);
        sortedSet.add(1);
        sortedSet.add(2);
        sortedSet.add(3);
        assertEquals(sortedSet.size(), 1);
    }

    @Test
    public void testSpecifiedSortOrder() {
        TreeSet treeSet = new TreeSet();
        treeSet.add(1);
        treeSet.add(2);
        treeSet.add(3);
        Iterator iterator = treeSet.descendingIterator();
        Integer first = (Integer)iterator.next();
        assertEquals(Integer.valueOf(3), first);
    }

    @Test
    public void testGetFirstElement() {
        SortedSet sortedSet = new TreeSet();
        sortedSet.add(1);
        sortedSet.add(2);
        sortedSet.add(3);
        Object first = sortedSet.first();
        assertEquals(Integer.valueOf(1), first);
    }

    @Test
    public void testGetLastElement() {
        SortedSet sortedSet = new TreeSet();
        sortedSet.add(1);
        sortedSet.add(2);
        sortedSet.add(3);
        Object last = sortedSet.last();
        assertEquals(Integer.valueOf(3), last);
    }

    @Test
    public void testIterate() {
        SortedSet sortedSet = new TreeSet();
        sortedSet.add(1);
        sortedSet.add(2);
        sortedSet.add(3);
        Iterator iterator = sortedSet.iterator();
        while (iterator.hasNext()) {
            assertNotNull(iterator.next());
        }
    }

    @Test
    public void testHeadSet() {
        SortedSet sortedSet = new TreeSet();
        sortedSet.add(1);
        sortedSet.add(2);
        sortedSet.add(3);
        sortedSet.add(4);
        sortedSet.add(5);
        SortedSet headSet = sortedSet.headSet(3);
        assertEquals(headSet.size(), 2);
    }

    @Test
    public void testTailSet() {
        SortedSet sortedSet = new TreeSet();
        sortedSet.add(1);
        sortedSet.add(2);
        sortedSet.add(3);
        sortedSet.add(4);
        sortedSet.add(5);
        SortedSet tailSet = sortedSet.tailSet(3);
        assertEquals(tailSet.size(), 3);
    }

    @Test
    public void testSubSet() {
        SortedSet sortedSet = new TreeSet();
        sortedSet.add(1);
        sortedSet.add(2);
        sortedSet.add(3);
        sortedSet.add(4);
        sortedSet.add(5);
        SortedSet subSet = sortedSet.subSet(2, 4);
        assertEquals(subSet.size(), 2);
    }
}
