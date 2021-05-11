package com.example.exercisedb.utilities;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static com.example.exercisedb.utilities.QueryUtilities.createPageRequest;
import static org.junit.jupiter.api.Assertions.*;

class QueryUtilitiesTest {
    
    @Test
    void createPageRequestTest() {
        PageRequest defaultPageRequest = PageRequest.of(0, Integer.MAX_VALUE);
        String [] propertiesArray = new String[] {"p1", "p2", "p3"};
        String [] propertiesArrayWithErrors = new String[] {"p1", "", "p2", null, "p3", null, ""};

        assertThrows(
                IllegalArgumentException.class,
                () -> createPageRequest(-4, -10, null, (String[]) null)
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> createPageRequest(null, 0, Sort.Direction.ASC, (String[]) null)
        );
        
        assertEquals(
                defaultPageRequest,
                createPageRequest(null, null, null, (String) null)
        );
        assertEquals(
                defaultPageRequest,
                createPageRequest(null, null, Sort.Direction.ASC, "")
        );
        assertEquals(
                PageRequest.of(1, 10),
                createPageRequest(1, 10, Sort.Direction.ASC, (String) null)
        );
        assertEquals(
                PageRequest.of(0, 10),
                createPageRequest(null, 10, Sort.Direction.DESC, (String) null)
        );
        assertEquals(
                PageRequest.of(2, 30, Sort.by(Sort.Direction.DESC, "property")),
                createPageRequest(2, 30, Sort.Direction.DESC, "property")
        );
        assertEquals(
                PageRequest.of(0, Integer.MAX_VALUE, Sort.by(Sort.Direction.ASC, propertiesArray)),
                createPageRequest(null, null, null, propertiesArray)
        );
        assertEquals(
                PageRequest.of(0, Integer.MAX_VALUE, Sort.by(Sort.Direction.DESC, propertiesArray)),
                createPageRequest(null, null, Sort.Direction.DESC, propertiesArrayWithErrors)
        );
    }
}