package com.example.fundamentalsofalgorithms.array;

import com.example.fundamentalsofalgorithms.exception.ArrayIsFullException;
import com.example.fundamentalsofalgorithms.exception.ElementNotFoundException;
import com.example.fundamentalsofalgorithms.exception.InvalidIndexException;
import com.example.fundamentalsofalgorithms.exception.NullPointerException;
import com.example.fundamentalsofalgorithms.interfaces.IntegerList;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {

    private Integer[] arrayOfNumbers;
    private int size;

    public IntegerListImpl() {
        arrayOfNumbers = new Integer[1000000];
    }

    public IntegerListImpl(int lineSize) {
        arrayOfNumbers = new Integer[lineSize];
    }

    @Override
    public Integer add(Integer item) {
        growIfNeeded();
        validateItem(item);
        arrayOfNumbers[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        growIfNeeded();
        validateIndex(index);
        validateItem(item);
        if (index == size) {
            arrayOfNumbers[size++]=item;
            return item;
        }
        System.arraycopy(arrayOfNumbers,index,arrayOfNumbers,index+1,size-index);
        arrayOfNumbers[index]=item;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        arrayOfNumbers[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        int i = indexOf(item);
        if (i == -1) {
            throw new ElementNotFoundException();
        }
        if (i != size) {
            System.arraycopy(arrayOfNumbers, i + 1, arrayOfNumbers, i, size - i);
        }
        size--;
        return item;
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);
        int i = index;
        Integer item = arrayOfNumbers[i];
        if (i != size) {
            System.arraycopy(arrayOfNumbers, i + 1, arrayOfNumbers, i, size - i);
        }
        size--;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        Integer[] arrayOfNumbersCopy = toArray();
        sortInsertion(arrayOfNumbersCopy);
        return binarySearch(arrayOfNumbersCopy, item);
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (item.equals(arrayOfNumbers[i])) {
                return i;
            }

        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            if (item.equals(arrayOfNumbers[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        validateIndex(index);
        return arrayOfNumbers[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(arrayOfNumbers, size);
    }

    private boolean binarySearch(Integer[] arr, int item) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item == arr[mid]) {
                return true;
            }

            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    public boolean contains1(Integer item) {
        Integer[] arrayOfNumbersCopy = toArray();
        sortBubble(arrayOfNumbersCopy);
        return binarySearch(arrayOfNumbersCopy, item);
    }

    public boolean contains2(Integer item) {
        Integer[] arrayOfNumbersCopy = toArray();
        sortSelection(arrayOfNumbersCopy);
        return binarySearch(arrayOfNumbersCopy, item);
    }

    public static void sortBubble(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    public static void sortSelection(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    public static void sortInsertion(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Integer temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    private static void swapElements(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    private void sort(Integer[] arr) {
        quickSort(arr,0,arr.length-1);
    }

    private void validateSize() {
        if (size == arrayOfNumbers.length) {
            throw new ArrayIsFullException();
        }
    }

    private void validateItem(Integer item) {
        if (item == null) {
            throw new NullPointerException();
        }
    }

    private void validateIndex(int index) {
        if (index > size || index < 0) {
            throw new InvalidIndexException();
        }
    }

    private void grow() {
        arrayOfNumbers = Arrays.copyOf(arrayOfNumbers, size + size / 2);
    }

    private void growIfNeeded() {
        if (size == arrayOfNumbers.length) {
            grow();
        }
    }

    private void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }
}