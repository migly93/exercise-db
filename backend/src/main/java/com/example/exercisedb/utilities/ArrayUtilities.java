package com.example.exercisedb.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.exercisedb.constants.ErrorMessages.*;

public class ArrayUtilities {

    /**
     * Will find the Longest Increasing Sequence (LIS) for the source array,
     * then returns a list of the longest increasing subsequence of a source array. <br>
     * For null or empty input will return null
     * @param source Source of values
     * @param <T> Source Type, must extend Comparable
     * @return a list of the longest increasing subsequence of a source array, null for null or empty input.
     */
    public static <T extends Comparable<T>> List<T> getLongestIncreasingSubsequence(T[] source) {
        if(source == null || source.length == 0) return null;

        int[] lis = new int[source.length]; //Contains the Longest Increasing Sequence size for every element
        int[] predecessors = new int[source.length]; //Contains the predecessor of each element in the Longest Increasing Sequence
        longestIncreasingSubsequence(source, lis, predecessors);
        return getLISValues(source, lis, predecessors);
    }

    /**
     * Will find the Longest Increasing Sequence (LIS) for the source array. <br>
     * Will throw {@link IllegalArgumentException} if: <br>
     *  - source is empty or null <br>
     *  - lis is null or it has not the same length of the source array <br>
     *  - predecessors is null or it has not the same length of the source array <br>
     * @param source The source array to search for the LIS.
     * @param lis Will contain the LIS length for each element
     * @param predecessors Will contain the predecessor of each element in its own LIS
     * @param <T> Source Type, must extend Comparable
     */
    public static <T extends Comparable<T>> void longestIncreasingSubsequence(T[] source, int[] lis, int[] predecessors) {
        validateLISParameters(source, lis, predecessors);

        Arrays.fill(lis, 1);
        initArrayWithIndexValues(predecessors);

        //The LIS length until the element at index 0 it's 1. I can skip the first element.
        for(int i = 1; i < source.length; i++) {
            //I have to confront the element of index i with all the previous source
            for (int j = 0; j < i; j++) {

                // if source[i] > source[j]: the element can be part of a possible increasing sequence (always j < i)
                boolean isElemGtPrevious = source[i].compareTo(source[j]) > 0;

                // if lis[i] < lis[j] + 1: the element at index j is the predecessor of the one at index i for one LIS
                boolean isPrevLISLteActual = lis[i] <= lis[j];

                if (isElemGtPrevious && isPrevLISLteActual) {
                    lis[i] = lis[j] + 1; // increasing the LIS length for the element of index i
                    predecessors[i] = j; // setting j as predecessor of i
                }
            }
        }
    }

    /**
     * Validates parameters for Longest Increasing Subsequence algorithm.
     * Will throw {@link IllegalArgumentException} if: <br>
     *  - source is empty or null <br>
     *  - lis is null or it has not the same length of the source array <br>
     *  - predecessors is null or it has not the same length of the source array <br>
     * @param source The source array to search for the LIS.
     * @param lis Will contain the LIS length for each element
     * @param predecessors Will contain the predecessor of each element in its own LIS
     * @param <T> Source Type, must extend Comparable
     */
    private static <T extends Comparable<T>> void validateLISParameters(T[] source, int[] lis, int[] predecessors) {
        if(source == null || source.length == 0) throw new IllegalArgumentException(SOURCE_LIS_ERROR_MESSAGE);
        if(lis == null || predecessors == null) throw new IllegalArgumentException(LIS_PREDECESSORS_NULL_ERROR_MESSAGE);
        if(source.length != lis.length || source.length != predecessors.length) throw new IllegalArgumentException(LIS_PREDECESSORS_LENGTH_ERROR_MESSAGE);

    }

    /**
     * Sets the index value for each element of the array.
     * array[1] = 1, array[2] = 2, ..., array[n] = n
     * @param array Array to populate
     */
    private static void initArrayWithIndexValues(int[] array) {
        for(int i = 0; i < array.length; i++)
            array[i] = i;
    }

    /**
     * Returns a list of the longest increasing subsequence of a source array
     * @param source Source of values
     * @param lis Contains the LIS length for each element
     * @param predecessors Contains the predecessor of each element in its own LIS
     * @param <T> Source Type, needs to extend Comparable
     * @return A list of the longest increasing subsequence of a source array
     */
    private static <T extends Comparable<T>> List<T> getLISValues(T[] source, int[] lis, int[] predecessors) {
        int nextElemIndex = getMaxElementIndex(lis);

        List<T> lisValues = new ArrayList<>();
        lisValues.add(source[nextElemIndex]);

        while(nextElemIndex != predecessors[nextElemIndex]) {
            nextElemIndex = predecessors[nextElemIndex];
            //The source are in reverse order, I have to add every element on top ov the list
            lisValues.add(0, source[nextElemIndex]);
        }

        return lisValues;
    }

    /**
     * Returns the index of the maximum element of the source
     * @param source Source of values
     * @return the index of the maximum element of the source
     */
    private static int getMaxElementIndex(int[] source) {
        int maxElemIndex = 0;
        for(int i = 0; i < source.length; i++)
            if(source[i] > source[maxElemIndex]) maxElemIndex = i;
        return maxElemIndex;
    }
}
