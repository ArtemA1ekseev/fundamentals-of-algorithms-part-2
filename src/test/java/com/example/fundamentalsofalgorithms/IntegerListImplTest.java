package com.example.fundamentalsofalgorithms;

import com.example.fundamentalsofalgorithms.array.IntegerListImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegerListImplTest {

    private IntegerListImpl integerList;

    @BeforeEach
    void setUp() {
        integerList = new IntegerListImpl();
        java.util.Random random = new java.util.Random();
        for (int i = 0; i < 100_000; i++) {
            integerList.add(random.nextInt(100_000));
        }
    }

    @Test
    void addTestFirstWay() {
        integerList.add(5);
        assertEquals(integerList.get(100_000), 5);
        assertEquals(integerList.size(), 100_001);
    }

    @Test
    void addTestSecondWay() {
        integerList.add(2, 50);
        assertEquals(integerList.get(2), 50);
    }

    @Test
    void setTest() {
        integerList.set(6, 150);
        assertEquals(integerList.get(6), 150);
    }

    @Test
    void removeTestFirstWay() {
        integerList.remove(10);
        assertEquals(integerList.size(), 99999);
    }

    @Test
    void removeTestSecondWay() {
        integerList.add(5);
        assertEquals(integerList.get(100_000), 5);
        assertEquals(integerList.size(), 100_001);
        integerList.remove(5);
        assertEquals(integerList.size(), 100_000);
    }

    @Test
    void containsTest() {
        integerList.add(100_001);
        assertEquals(integerList.contains(100_001), true);
    }

    @Test
    void indexOfTest() {
        integerList.add(100_001);
        assertEquals(integerList.indexOf(100_001), 100_000);
    }

    @Test
    void lastIndexOfTest() {
        integerList.add(2, 50);
        assertEquals(integerList.get(2), 50);
        assertEquals(integerList.lastIndexOf(50), 2);
    }

    @Test
    void getTest() {
        integerList.add(2, 50);
        assertEquals(integerList.get(2), 50);
    }

    @Test
    void testEqualsTest() {
        assertEquals(integerList.equals(new IntegerListImpl()), false);
        assertEquals(integerList.equals(integerList), true);
    }

    @Test
    void sizeTest() {
        assertEquals(integerList.size(), 100_000);
    }

    @Test
    void isEmptyTest() {
        assertEquals(integerList.isEmpty(), false);
        integerList.clear();
        assertEquals(integerList.isEmpty(), true);
    }

    @Test
    void clearTest() {
        integerList.clear();
        assertEquals(integerList.size(), 0);
    }

    @Test
    void toArrayTest() {
        assertEquals(integerList.toArray().length, 100_000);
    }

    @Test
    public void speedTest() {
        integerList.add(100_001);
        long start1 = System.currentTimeMillis();
        integerList.contains(100_001);
        System.out.println(System.currentTimeMillis() - start1);

        long start2 = System.currentTimeMillis();
        integerList.contains1(100_001);
        System.out.println(System.currentTimeMillis() - start2);

        long start3 = System.currentTimeMillis();
        integerList.contains2(100_001);
        System.out.println(System.currentTimeMillis() - start3);

        System.out.println("Наилучший метод сортировки: ");
        long start = Math.min(Math.min(start1, start2), start3);
        if (start == start1) {
            System.out.print("Сортировка вставкой");
        } else if (start == start2) {
            System.out.print("Пузырьковая сортировка");
        } else {
            System.out.print("Сортировка выбором");
        }
    }
}
