package com.example.exercisedb.utilities;

import java.util.Arrays;

public class StringUtilities {

    /**
     * Removes null and empty strings from a collection of strings.
     * Returns the new clean String []
     * @param source Source elements
     * @return The new clean String [] without null and empty strings
     */
    public static String[] cleanBlankStrings(String... source) {
        if(source != null) {
            source = Arrays.stream(source)
                    .filter(elem -> elem != null && !elem.isEmpty())
                    .toArray(String[]::new);
        }
        return source;
    }

}
