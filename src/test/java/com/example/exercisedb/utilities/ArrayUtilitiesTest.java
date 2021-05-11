package com.example.exercisedb.utilities;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.example.exercisedb.utilities.ArrayUtilities.*;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

class ArrayUtilitiesTest {

    Integer[] integerArray = new Integer[] {4, 5, 1, 8, 16, 7, 3};
    Long[] longArray = new Long[] {233L, 67L, 157L, 262L, 255L, 75L, 297L, 124L, 197L, 43L};
    String[] stringArray = new String[] {"abc", "abbb", "dgf", "abcd", "rtyu", "zk34", "ttrd", "yghd", "pioj", "qwerty"};
    Double[] ascendingArray = new Double[] {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7, 8.8, 9.9, 10.1};
    Integer[] descendingArray = new Integer[] {100, 99, 88, 77, 66, 55, 44, 33, 22, 11};
    Integer[] oneElemArray = new Integer[] {100};

    @Test
    void getLongestIncreasingSubsequenceWrongInputTest() {
        assertNull(getLongestIncreasingSubsequence(null));
        assertNull(getLongestIncreasingSubsequence(new Integer[0]));
        assertNull(getLongestIncreasingSubsequence(new BigDecimal[0]));
        assertNull(getLongestIncreasingSubsequence(new String[0]));
    }

    @Test
    void getLongestIncreasingSubsequenceIntegerTest() {
        Integer[] integerLIS = new Integer[] {4, 5, 8, 16};
        assertEquals(asList(integerLIS), getLongestIncreasingSubsequence(integerArray));
    }

    @Test
    void getLongestIncreasingSubsequenceLongTest() {
        Long[] longLIS = new Long[] {67L, 157L, 262L, 297L};
        assertEquals(asList(longLIS), getLongestIncreasingSubsequence(longArray));
    }

    @Test
    void getLongestIncreasingSubsequenceStirngTest() {
        String[] stringLIS = new String[] {"abc", "dgf", "rtyu", "ttrd", "yghd"};
        assertEquals(asList(stringLIS), getLongestIncreasingSubsequence(stringArray));
    }

    @Test
    void getLongestIncreasingSubsequenceLimitCasesTest() {
        assertEquals(asList(ascendingArray), getLongestIncreasingSubsequence(ascendingArray));
        assertEquals(singletonList(descendingArray[0]), getLongestIncreasingSubsequence(descendingArray));
        assertEquals(singletonList(oneElemArray[0]), getLongestIncreasingSubsequence(oneElemArray));
    }

    @Test
    void longestIncreasingSubsequenceWrongInputTest() {
        assertThrows(IllegalArgumentException.class, () -> longestIncreasingSubsequence(null, null, null));
        assertThrows(IllegalArgumentException.class, () -> longestIncreasingSubsequence(integerArray, null, null));
        assertThrows(IllegalArgumentException.class, () -> longestIncreasingSubsequence(integerArray, new int[integerArray.length], null));
        assertThrows(IllegalArgumentException.class, () -> longestIncreasingSubsequence(integerArray, new int[integerArray.length + 1], new int[integerArray.length + 1]));
    }

    @Test
    void longestIncreasingSubsequenceIntegerTest() {
        int[] startingLIS = new int[integerArray.length];
        int[] startingPredecessors = new int[integerArray.length];
        int[] endingLIS = {1, 2, 1, 3, 4, 3, 2}; // Every element contains the LIS length until its own index
        int[] endingPredecessors = {0, 0, 2, 1, 3, 1, 2}; // Every element contains the index of its predecessor in its own LIS
        checkLISAndPredecessors(integerArray, startingLIS, startingPredecessors, endingLIS, endingPredecessors);
    }

    @Test
    void longestIncreasingSubsequenceLongTest() {
        int[] startingLIS = new int[longArray.length];
        int[] startingPredecessors = new int[longArray.length];
        int[] endingLIS = {1, 1, 2, 3, 3, 2, 4, 3, 4, 1}; // Every element contains the LIS length until its own index
        int[] endingPredecessors = {0, 1, 1, 2, 2, 1, 3, 5, 7, 9}; // Every element contains the index of its predecessor in its own LIS
        checkLISAndPredecessors(longArray, startingLIS, startingPredecessors, endingLIS, endingPredecessors);
    }

    @Test
    void longestIncreasingSubsequenceStringTest() {
        int[] startingLIS = new int[stringArray.length];
        int[] startingPredecessors = new int[stringArray.length];
        int[] endingLIS = {1, 1, 2, 2, 3, 4, 4, 5, 3, 4}; // Every element contains the LIS length until its own index
        int[] endingPredecessors = {0, 1, 0, 0, 2, 4, 4, 6, 2, 8}; // Every element contains the index of its predecessor in its own LIS
        checkLISAndPredecessors(stringArray, startingLIS, startingPredecessors, endingLIS, endingPredecessors);
    }

    @Test
    void longestIncreasingSubsequenceDescendingArrayTest() {
        int[] startingLIS = new int[descendingArray.length];
        int[] startingPredecessors = new int[descendingArray.length];
        int[] endingLIS = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}; // Every element contains the LIS length until its own index
        int[] endingPredecessors = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}; // Every element contains the index of its predecessor in its own LIS
        checkLISAndPredecessors(descendingArray, startingLIS, startingPredecessors, endingLIS, endingPredecessors);
    }

    @Test
    void longestIncreasingSubsequenceAscendingArrayTest() {
        int[] startingLIS = new int[ascendingArray.length];
        int[] startingPredecessors = new int[ascendingArray.length];
        int[] endingLIS = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; // Every element contains the LIS length until its own index
        int[] endingPredecessors = {0, 0, 1, 2, 3, 4, 5, 6, 7, 8}; // Every element contains the index of its predecessor in its own LIS
        checkLISAndPredecessors(ascendingArray, startingLIS, startingPredecessors, endingLIS, endingPredecessors);
    }

    @Test
    void longestIncreasingSubsequenceOneElemArrayTest() {
        int[] startingLIS = new int[oneElemArray.length];
        int[] startingPredecessors = new int[oneElemArray.length];
        int[] endingLIS = {1}; // Every element contains the LIS length until its own index
        int[] endingPredecessors = {0}; // Every element contains the index of its predecessor in its own LIS
        checkLISAndPredecessors(oneElemArray, startingLIS, startingPredecessors, endingLIS, endingPredecessors);
    }

    <T extends Comparable<T>> void checkLISAndPredecessors(T[] source, int[] startingLIS, int[] startingPredecessors, int[] endingLIS, int[] endingPredecessors) {
        longestIncreasingSubsequence(source, startingLIS, startingPredecessors);
        assertArrayEquals(endingLIS, startingLIS);
        assertArrayEquals(endingPredecessors, startingPredecessors);
    }
}