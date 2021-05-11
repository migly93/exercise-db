package com.example.exercisedb.utilities;

import org.junit.jupiter.api.Test;

import static com.example.exercisedb.utilities.StringUtilities.cleanBlankStrings;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilitiesTest {

    @Test
    void cleanBlankStringsTest() {
        String[] emptyArray = new String[0];
        String[] arrayWithEmpties = new String[] {"Hello", "", "World", "", "", "Hello again"};
        String[] arrayWithNulls = new String[] {null, null, "Hello", "World", "Hello again", null, "World again", null};
        String[] arrayWithNullsAndEmpties = new String[] {"", null, "Hello", "World", null, "", null};
        String[] allEmpties = new String[] {"", "", ""};
        String[] allNulls = new String[] {null, null, null};
        String[] allEmptiesAndNulls = new String[] {null, "", null, "", null, ""};
        String[] fullArray = new String[] {"Hello", "World", "Hello again", "WorldAgain"};

        assertNull(cleanBlankStrings((String[]) null));
        assertNotNull(cleanBlankStrings(emptyArray));

        assertEquals(0, cleanBlankStrings(emptyArray).length);
        assertEquals(0, cleanBlankStrings(allEmpties).length);
        assertEquals(0, cleanBlankStrings(allNulls).length);
        assertEquals(0, cleanBlankStrings(allEmptiesAndNulls).length);
        assertArrayEquals(
                new String[] {"Hello", "World", "Hello again"},
                cleanBlankStrings(arrayWithEmpties)
        );
        assertArrayEquals(
                new String[] {"Hello", "World", "Hello again", "World again"},
                cleanBlankStrings(arrayWithNulls)
        );
        assertArrayEquals(
                new String[] {"Hello", "World"},
                cleanBlankStrings(arrayWithNullsAndEmpties)
        );
        assertArrayEquals(
                fullArray,
                cleanBlankStrings(fullArray)
        );

    }
}